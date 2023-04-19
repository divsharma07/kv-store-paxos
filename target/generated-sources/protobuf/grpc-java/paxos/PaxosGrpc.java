package paxos;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: paxos.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PaxosGrpc {

  private PaxosGrpc() {}

  public static final String SERVICE_NAME = "paxos.Paxos";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<paxos.PaxosOuterClass.PromiseRequest,
      paxos.PaxosOuterClass.PromiseResponse> getPromiseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Promise",
      requestType = paxos.PaxosOuterClass.PromiseRequest.class,
      responseType = paxos.PaxosOuterClass.PromiseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<paxos.PaxosOuterClass.PromiseRequest,
      paxos.PaxosOuterClass.PromiseResponse> getPromiseMethod() {
    io.grpc.MethodDescriptor<paxos.PaxosOuterClass.PromiseRequest, paxos.PaxosOuterClass.PromiseResponse> getPromiseMethod;
    if ((getPromiseMethod = PaxosGrpc.getPromiseMethod) == null) {
      synchronized (PaxosGrpc.class) {
        if ((getPromiseMethod = PaxosGrpc.getPromiseMethod) == null) {
          PaxosGrpc.getPromiseMethod = getPromiseMethod =
              io.grpc.MethodDescriptor.<paxos.PaxosOuterClass.PromiseRequest, paxos.PaxosOuterClass.PromiseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Promise"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  paxos.PaxosOuterClass.PromiseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  paxos.PaxosOuterClass.PromiseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PaxosMethodDescriptorSupplier("Promise"))
              .build();
        }
      }
    }
    return getPromiseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<paxos.PaxosOuterClass.AcceptRequest,
      paxos.PaxosOuterClass.AcceptResponse> getAcceptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Accept",
      requestType = paxos.PaxosOuterClass.AcceptRequest.class,
      responseType = paxos.PaxosOuterClass.AcceptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<paxos.PaxosOuterClass.AcceptRequest,
      paxos.PaxosOuterClass.AcceptResponse> getAcceptMethod() {
    io.grpc.MethodDescriptor<paxos.PaxosOuterClass.AcceptRequest, paxos.PaxosOuterClass.AcceptResponse> getAcceptMethod;
    if ((getAcceptMethod = PaxosGrpc.getAcceptMethod) == null) {
      synchronized (PaxosGrpc.class) {
        if ((getAcceptMethod = PaxosGrpc.getAcceptMethod) == null) {
          PaxosGrpc.getAcceptMethod = getAcceptMethod =
              io.grpc.MethodDescriptor.<paxos.PaxosOuterClass.AcceptRequest, paxos.PaxosOuterClass.AcceptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Accept"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  paxos.PaxosOuterClass.AcceptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  paxos.PaxosOuterClass.AcceptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PaxosMethodDescriptorSupplier("Accept"))
              .build();
        }
      }
    }
    return getAcceptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<paxos.PaxosOuterClass.LearnRequest,
      paxos.PaxosOuterClass.LearnResponse> getLearnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Learn",
      requestType = paxos.PaxosOuterClass.LearnRequest.class,
      responseType = paxos.PaxosOuterClass.LearnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<paxos.PaxosOuterClass.LearnRequest,
      paxos.PaxosOuterClass.LearnResponse> getLearnMethod() {
    io.grpc.MethodDescriptor<paxos.PaxosOuterClass.LearnRequest, paxos.PaxosOuterClass.LearnResponse> getLearnMethod;
    if ((getLearnMethod = PaxosGrpc.getLearnMethod) == null) {
      synchronized (PaxosGrpc.class) {
        if ((getLearnMethod = PaxosGrpc.getLearnMethod) == null) {
          PaxosGrpc.getLearnMethod = getLearnMethod =
              io.grpc.MethodDescriptor.<paxos.PaxosOuterClass.LearnRequest, paxos.PaxosOuterClass.LearnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Learn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  paxos.PaxosOuterClass.LearnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  paxos.PaxosOuterClass.LearnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PaxosMethodDescriptorSupplier("Learn"))
              .build();
        }
      }
    }
    return getLearnMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PaxosStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PaxosStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PaxosStub>() {
        @java.lang.Override
        public PaxosStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PaxosStub(channel, callOptions);
        }
      };
    return PaxosStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PaxosBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PaxosBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PaxosBlockingStub>() {
        @java.lang.Override
        public PaxosBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PaxosBlockingStub(channel, callOptions);
        }
      };
    return PaxosBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PaxosFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PaxosFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PaxosFutureStub>() {
        @java.lang.Override
        public PaxosFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PaxosFutureStub(channel, callOptions);
        }
      };
    return PaxosFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class PaxosImplBase implements io.grpc.BindableService {

    /**
     */
    public void promise(paxos.PaxosOuterClass.PromiseRequest request,
        io.grpc.stub.StreamObserver<paxos.PaxosOuterClass.PromiseResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPromiseMethod(), responseObserver);
    }

    /**
     */
    public void accept(paxos.PaxosOuterClass.AcceptRequest request,
        io.grpc.stub.StreamObserver<paxos.PaxosOuterClass.AcceptResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAcceptMethod(), responseObserver);
    }

    /**
     */
    public void learn(paxos.PaxosOuterClass.LearnRequest request,
        io.grpc.stub.StreamObserver<paxos.PaxosOuterClass.LearnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLearnMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPromiseMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                paxos.PaxosOuterClass.PromiseRequest,
                paxos.PaxosOuterClass.PromiseResponse>(
                  this, METHODID_PROMISE)))
          .addMethod(
            getAcceptMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                paxos.PaxosOuterClass.AcceptRequest,
                paxos.PaxosOuterClass.AcceptResponse>(
                  this, METHODID_ACCEPT)))
          .addMethod(
            getLearnMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                paxos.PaxosOuterClass.LearnRequest,
                paxos.PaxosOuterClass.LearnResponse>(
                  this, METHODID_LEARN)))
          .build();
    }
  }

  /**
   */
  public static final class PaxosStub extends io.grpc.stub.AbstractAsyncStub<PaxosStub> {
    private PaxosStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaxosStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PaxosStub(channel, callOptions);
    }

    /**
     */
    public void promise(paxos.PaxosOuterClass.PromiseRequest request,
        io.grpc.stub.StreamObserver<paxos.PaxosOuterClass.PromiseResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPromiseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void accept(paxos.PaxosOuterClass.AcceptRequest request,
        io.grpc.stub.StreamObserver<paxos.PaxosOuterClass.AcceptResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAcceptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void learn(paxos.PaxosOuterClass.LearnRequest request,
        io.grpc.stub.StreamObserver<paxos.PaxosOuterClass.LearnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLearnMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PaxosBlockingStub extends io.grpc.stub.AbstractBlockingStub<PaxosBlockingStub> {
    private PaxosBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaxosBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PaxosBlockingStub(channel, callOptions);
    }

    /**
     */
    public paxos.PaxosOuterClass.PromiseResponse promise(paxos.PaxosOuterClass.PromiseRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPromiseMethod(), getCallOptions(), request);
    }

    /**
     */
    public paxos.PaxosOuterClass.AcceptResponse accept(paxos.PaxosOuterClass.AcceptRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAcceptMethod(), getCallOptions(), request);
    }

    /**
     */
    public paxos.PaxosOuterClass.LearnResponse learn(paxos.PaxosOuterClass.LearnRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLearnMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PaxosFutureStub extends io.grpc.stub.AbstractFutureStub<PaxosFutureStub> {
    private PaxosFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaxosFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PaxosFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<paxos.PaxosOuterClass.PromiseResponse> promise(
        paxos.PaxosOuterClass.PromiseRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPromiseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<paxos.PaxosOuterClass.AcceptResponse> accept(
        paxos.PaxosOuterClass.AcceptRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAcceptMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<paxos.PaxosOuterClass.LearnResponse> learn(
        paxos.PaxosOuterClass.LearnRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLearnMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROMISE = 0;
  private static final int METHODID_ACCEPT = 1;
  private static final int METHODID_LEARN = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PaxosImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PaxosImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PROMISE:
          serviceImpl.promise((paxos.PaxosOuterClass.PromiseRequest) request,
              (io.grpc.stub.StreamObserver<paxos.PaxosOuterClass.PromiseResponse>) responseObserver);
          break;
        case METHODID_ACCEPT:
          serviceImpl.accept((paxos.PaxosOuterClass.AcceptRequest) request,
              (io.grpc.stub.StreamObserver<paxos.PaxosOuterClass.AcceptResponse>) responseObserver);
          break;
        case METHODID_LEARN:
          serviceImpl.learn((paxos.PaxosOuterClass.LearnRequest) request,
              (io.grpc.stub.StreamObserver<paxos.PaxosOuterClass.LearnResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PaxosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PaxosBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return paxos.PaxosOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Paxos");
    }
  }

  private static final class PaxosFileDescriptorSupplier
      extends PaxosBaseDescriptorSupplier {
    PaxosFileDescriptorSupplier() {}
  }

  private static final class PaxosMethodDescriptorSupplier
      extends PaxosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PaxosMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PaxosGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PaxosFileDescriptorSupplier())
              .addMethod(getPromiseMethod())
              .addMethod(getAcceptMethod())
              .addMethod(getLearnMethod())
              .build();
        }
      }
    }
    return result;
  }
}
