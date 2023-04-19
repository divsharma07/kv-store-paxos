package server;

import com.google.protobuf.Empty;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;

import io.grpc.Context;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import paxos.PaxosGrpc.PaxosImplBase;
import paxos.PaxosOuterClass;
import util.LoggerUtil;

/**
 * Implementation of the PaxosService based on the proto/paxos.proto
 */
public class PaxosService extends PaxosImplBase {

  private int maxProposalId = 0;
  private LockByKey lock;
  private Map<String, String> kvMap;

  private PaxosOuterClass.AcceptRequest accepted;

  /**
   * Instantiating the PaxosService
   * @param lock the locking class
   * @param kvMap the kvMap that saves the key values for the KVService
   */
  public PaxosService(LockByKey lock, Map<String, String> kvMap) {
    this.kvMap = kvMap;
    this.lock = lock;
  }

  /**
   * Implementation of the response for a received prepare request from a proposer.
   * @param request the prepare request.
   * @param responseObserver the observer used to return the response.
   */
  @Override
  public void promise(PaxosOuterClass.PromiseRequest request, StreamObserver<PaxosOuterClass.PromiseResponse> responseObserver) {
    LoggerUtil.writeLog(Level.INFO, "Received Promise message");
    String key = request.getKeyValueOperation().getKey();
    if (Math.random() < 0.2) {
      LoggerUtil.writeLog(Level.SEVERE, "Random failure occurred");
      responseObserver.onNext(null);
      responseObserver.onCompleted();
      return;
    }
    lock.lock(key);
    if (Context.current().isCancelled()) {
      // if client call waits for too long and client cancels
      responseObserver.onError(Status.CANCELLED.withDescription("Cancelled by client").asRuntimeException());
      lock.unlock(key);
      return;
    }

    try {
      PaxosOuterClass.PromiseResponse.Builder responseBuilder = PaxosOuterClass.PromiseResponse.newBuilder();
      int proposalId = request.getProposalId();
      if (proposalId <= maxProposalId) {
        responseBuilder.setStatus(PaxosOuterClass.Status.REJECTED);
        responseBuilder.setKeyValueOperation(request.getKeyValueOperation());
        responseBuilder.setProposalId(proposalId);
      } else {
        maxProposalId = proposalId;
        if (accepted != null) {
          responseBuilder.setStatus(PaxosOuterClass.Status.ACCEPTED);
          responseBuilder.setProposalId(accepted.getProposalId());
          responseBuilder.setKeyValueOperation(accepted.getKeyValueOperation());
        } else {
          responseBuilder.setStatus(PaxosOuterClass.Status.PROMISED);
          responseBuilder.setKeyValueOperation(request.getKeyValueOperation());
          responseBuilder.setProposalId(request.getProposalId());
        }
      }
      responseObserver.onNext(responseBuilder.build());
      responseObserver.onCompleted();
    }
    finally {
      lock.unlock(key);
    }
  }

  /**
   * Implementation of the response for an accept request from a proposer.
   * @param request the accept request.
   * @param responseObserver the observer used to return the response.
   */
  @Override
  public void accept(PaxosOuterClass.AcceptRequest request, StreamObserver<PaxosOuterClass.AcceptResponse> responseObserver) {
    LoggerUtil.writeLog(Level.INFO, "Received Accept message");
    String key = request.getKeyValueOperation().getKey();
    if (Math.random() < 0.2) {
      LoggerUtil.writeLog(Level.SEVERE, "Random failure occurred");
      responseObserver.onNext(null);
      responseObserver.onCompleted();
      return;
    }
    lock.lock(key);
    try {
      PaxosOuterClass.AcceptResponse.Builder responseBuilder = PaxosOuterClass.AcceptResponse.newBuilder();
      int proposalId = request.getProposalId();
      if (proposalId != maxProposalId) {
        responseBuilder.setSuccess(false);
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
      }

      accepted = PaxosOuterClass.AcceptRequest.newBuilder().setProposalId(proposalId).
              setKeyValueOperation(request.getKeyValueOperation()).build();

      responseBuilder.setSuccess(true);
      responseObserver.onNext(responseBuilder.build());
      responseObserver.onCompleted();
    }
    finally {
      lock.unlock(key);
    }
  }

  /**
   * Implementation of the response for a learn request from a proposer.
   * @param request the learn request.
   * @param responseObserver the observer used to return the response.
   */
  @Override
  public void learn(PaxosOuterClass.LearnRequest request, StreamObserver<PaxosOuterClass.LearnResponse> responseObserver) {
    LoggerUtil.writeLog(Level.INFO, "Received Learn message");
    PaxosOuterClass.KeyValueOperation kvOperation = request.getKeyValueOperation();
    PaxosOuterClass.RequestType requestType = request.getKeyValueOperation().getRequestType();
    String key = kvOperation.getKey();
    PaxosOuterClass.LearnResponse.Builder responseBuilder = PaxosOuterClass.LearnResponse.newBuilder();
    lock.lock(key);

    try {
      if(requestType == PaxosOuterClass.RequestType.PUT) {
        String value = kvOperation.getValue();
        kvMap.put(key, value);
        responseBuilder.setSuccess(true);
        responseBuilder.setResponseCode(Status.OK.toString());
        responseBuilder.setMessage(String.format("PUT operation successful, key : %s has a value: %s", key, value));
        LoggerUtil.writeLog(Level.INFO, "Key " + key + " added and contains value: " + value);
      }
      else if(requestType == PaxosOuterClass.RequestType.DELETE) {
        if(!kvMap.containsKey(key)) {
          responseBuilder.setSuccess(false);
          responseBuilder.setResponseCode(Status.NOT_FOUND.toString());
          responseBuilder.setMessage("Key " + key + " not found");
          LoggerUtil.writeLog(Level.SEVERE, "Client tried to remove key: " + key + " but it was not found");
        } else {
          kvMap.remove(key);
          responseBuilder.setMessage(String.format("Key: %s, deleted", key));
          responseBuilder.setSuccess(true);
          responseBuilder.setResponseCode(Status.OK.toString());
          LoggerUtil.writeLog(Level.INFO, String.format("Client removed key: %s", key));
        }
      }

      responseObserver.onNext(responseBuilder.build());
      responseObserver.onCompleted();
    }
    finally {
      lock.unlock(key);
    }
  }
}
