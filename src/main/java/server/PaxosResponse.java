package server;

import io.grpc.Status;
import paxos.PaxosOuterClass;

public class PaxosResponse {

  private boolean success;
  private Status code;
  private String message;

  public PaxosResponse(boolean success, Status code, String message) {
    this.success = success;
    this.code = code;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public Status getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
