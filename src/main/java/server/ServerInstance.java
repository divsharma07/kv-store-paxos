package server;

import kvGrpc.KeyValueGrpc;
import paxos.PaxosGrpc;

/**
 * Represents each server instance and stores a gRPC client stub that is reused in the application
 * for server-server communication.
 */
public class ServerInstance {
  private int port;
  private String address;
  private PaxosGrpc.PaxosBlockingStub stub;

  public ServerInstance(int port, String address, PaxosGrpc.PaxosBlockingStub stub) {
    this.port = port;
    this.address = address;
    this.stub = stub;
  }

  public PaxosGrpc.PaxosBlockingStub getStub() {
    return stub;
  }

  public int getPort() {
    return port;
  }

  public String getAddress() {
    return address;
  }
}
