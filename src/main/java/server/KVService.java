package server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import kvGrpc.KeyValueGrpc;
import kvGrpc.Keyvalue;
import paxos.PaxosOuterClass;
import util.LoggerUtil;


/**
 * A thread safe grpc based implementation. LockByKey takes care of the thread safety since only
 * one client is allowed to deal with a particular key at a time.
 */
public class KVService extends KeyValueGrpc.KeyValueImplBase {
  private final Map<String, String> kvMap;

  private Map<Integer, String> codeMessageMap = new HashMap<>();

  private int lastProposalNumber;

  private final int REQUEST_REPETITION_THRESHOLD = 3;

  private final LockByKey lock;
  private final List<ServerInstance> servers;

  private final int port;

  /**
   * Instantiating the KVService
   *
   * @param lock
   * @param servers
   * @param kvMap
   * @param port
   */
  public KVService(LockByKey lock, List<ServerInstance> servers, Map<String, String> kvMap, int port) {
    super();
    this.lock = lock;
    this.servers = servers;
    this.kvMap = kvMap;
    this.port = port;
  }

  @Override
  public void put(Keyvalue.PutRequest request, StreamObserver<Keyvalue.PutResponse> responseObserver) {
    String key = request.getKey();
    PaxosOuterClass.KeyValueOperation kvOperation = PaxosOuterClass.KeyValueOperation.newBuilder().setKey(key)
            .setRequestTypeValue(PaxosOuterClass.RequestType.PUT_VALUE).build();
    int proposalId = Integer.parseInt(new SimpleDateFormat("HHmmssSSS").format(new Date()));
    PaxosOuterClass.PromiseRequest Promise = PaxosOuterClass.PromiseRequest.newBuilder().setProposalId(proposalId)
            .setKeyValueOperation(kvOperation).build();
    PaxosResponse response = null;
    try {
      response = orchestratePaxos(Promise);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    Keyvalue.PutResponse.Builder responseBuilder = Keyvalue.PutResponse.newBuilder();
    responseBuilder.setResponseMessage(response.getMessage());
    responseBuilder.setResponseCode(response.getCode().toString());
    responseObserver.onNext(responseBuilder.build());
    responseObserver.onCompleted();
  }

  @Override
  public void get(Keyvalue.GetRequest request, StreamObserver<Keyvalue.GetResponse> responseObserver) {
    String key = request.getKey();
    String value = kvMap.get(key);
    lock.lock(key);
    try {
      Keyvalue.GetResponse.Builder responseBuilder = Keyvalue.GetResponse.newBuilder();
      if (value != null) {
        responseBuilder.setResponseCode(Status.OK.toString());
        responseBuilder.setValue(value);
        responseBuilder.setResponseMessage("The value of key: " + key + " fetched is value: " + value);
        LoggerUtil.writeLog(Level.INFO, "The value of key: " + key + " fetched is value: " + value);
      } else {
        responseBuilder.setResponseCode(Status.NOT_FOUND.toString());
        responseBuilder.setResponseMessage("Key " + key + " not found");
        LoggerUtil.writeLog(Level.SEVERE, "Client tried to fetch key: " + key + " but it was not found");
      }
      // sends value to client
      responseObserver.onNext(responseBuilder.build());
      responseObserver.onCompleted();
    } finally {
      lock.unlock(key);
    }
  }

  @Override
  public void delete(Keyvalue.DeleteRequest request, StreamObserver<Keyvalue.DeleteResponse> responseObserver) {
    String key = request.getKey();
    PaxosOuterClass.KeyValueOperation kvOperation = PaxosOuterClass.KeyValueOperation.newBuilder().setKey(key)
            .setRequestTypeValue(PaxosOuterClass.RequestType.DELETE_VALUE).build();
    int proposalId = Integer.parseInt(new SimpleDateFormat("HHmmssSSS").format(new Date()));
    PaxosOuterClass.PromiseRequest Promise = PaxosOuterClass.PromiseRequest.newBuilder().setProposalId(proposalId)
            .setKeyValueOperation(kvOperation).build();
    PaxosResponse response = null;
    try {
      response = orchestratePaxos(Promise);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    Keyvalue.DeleteResponse.Builder responseBuilder = Keyvalue.DeleteResponse.newBuilder();
    responseBuilder.setResponseMessage(response.getMessage());
    responseBuilder.setResponseCode(response.getCode().toString());
    responseObserver.onNext(responseBuilder.build());
    responseObserver.onCompleted();
  }

  // This method has all the phases of the paxos algorithm
  private PaxosResponse orchestratePaxos(PaxosOuterClass.PromiseRequest PromiseRequest) throws InterruptedException {
    String key = PromiseRequest.getKeyValueOperation().getKey();
    //lock.lock(key);

    int consensusNumber = Math.floorDiv(servers.size(), 2) + 1;
    int positivePromiseResponses = 0;

    // sending Promise requests
    for (ServerInstance peerServer : servers) {
      try {
        PaxosOuterClass.PromiseResponse prepareResponse = peerServer.getStub().withDeadlineAfter(30, TimeUnit.SECONDS).promise(PromiseRequest);
        if (prepareResponse.toString().equals("")) {
          LoggerUtil.writeLog(Level.SEVERE, String.format("Server with address %s and port %d did not respond", peerServer.getAddress(), peerServer.getPort()));
        } else if (prepareResponse.getStatus() == PaxosOuterClass.Status.PROMISED || prepareResponse.getStatus() == PaxosOuterClass.Status.ACCEPTED) {
          positivePromiseResponses++;
          LoggerUtil.writeLog(Level.INFO, String.format("Server with address %s and port %d returned successful promise", peerServer.getAddress(), peerServer.getPort()));
        } else {
          LoggerUtil.writeLog(Level.SEVERE, String.format("Server with address %s and port %d rejected proposal", peerServer.getAddress(), peerServer.getPort()));
        }
      } catch (Exception e) {
        LoggerUtil.writeLog(Level.SEVERE, String.format("Server with address %s and port %d did not respond", peerServer.getAddress(), peerServer.getPort()));
      }
    }

    // mark this as a failure if consensus is not reached
    if (positivePromiseResponses < consensusNumber) {
      LoggerUtil.writeLog(Level.SEVERE, String.format("Consensus not reached after sending prepare requests"));
      return new PaxosResponse(false, Status.FAILED_PRECONDITION, "Internal Error. Unable to reach consensus");
    }

    // sending accept requests with the option of resending accept requests, with exponential backoff
    int positiveAcceptResponses = 0;
    double sleepTimeSeconds = 1;
    int repeatRequestTime = 0;
    int index = 0;
    while (index < servers.size()) {
      ServerInstance peerServer = servers.get(index);
      PaxosOuterClass.AcceptRequest acceptRequest = PaxosOuterClass.AcceptRequest.newBuilder().
              setProposalId(PromiseRequest.getProposalId()).
              setKeyValueOperation(PromiseRequest.getKeyValueOperation()).build();
      PaxosOuterClass.AcceptResponse acceptResponse = peerServer.getStub().withDeadlineAfter(30, TimeUnit.SECONDS).accept(acceptRequest);

      if (acceptResponse.toString().equals("")) {
        LoggerUtil.writeLog(Level.SEVERE, String.format("Server with address %s and port %d did not respond",
                peerServer.getAddress(), peerServer.getPort()));

        // checking if the acceptor has already been pinged too many times
        if (repeatRequestTime >= REQUEST_REPETITION_THRESHOLD) {
          index++;
          continue;
        }
        // mimicking exponential backoff like timeout and sending another accept request right after
        Thread.sleep((long) sleepTimeSeconds * 1000);
        sleepTimeSeconds += (int) (sleepTimeSeconds * 1.5);
        repeatRequestTime++;
      } else if (acceptResponse.getSuccess()) {
        positiveAcceptResponses++;
        index++;
        sleepTimeSeconds = 1;
        LoggerUtil.writeLog(Level.INFO, String.format("Server with address %s and port %d Accepted proposal", peerServer.getAddress(), peerServer.getPort()));
      }
    }


    if (positiveAcceptResponses < consensusNumber) {
      LoggerUtil.writeLog(Level.SEVERE, String.format("Consensus not reached after sending accept requests"));
      return new PaxosResponse(false, Status.FAILED_PRECONDITION, "Internal Error. Unable to reach consensus");
    }

    PaxosOuterClass.LearnResponse learnResponse = null;
    for (ServerInstance peerServer : servers) {
      PaxosOuterClass.LearnRequest learnRequest = PaxosOuterClass.LearnRequest.newBuilder().
              setProposalId(PromiseRequest.getProposalId()).setKeyValueOperation(PromiseRequest.getKeyValueOperation())
              .build();
      learnResponse = peerServer.getStub().withDeadlineAfter(30, TimeUnit.SECONDS).learn(learnRequest);
    }
    return new PaxosResponse(true, Status.OK, learnResponse.getMessage());
  }
}
