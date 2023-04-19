package kvGrpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * key-value service definition
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: keyvalue.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class KeyValueGrpc {

  private KeyValueGrpc() {}

  public static final String SERVICE_NAME = "kvGrpc.KeyValue";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kvGrpc.Keyvalue.PutRequest,
      kvGrpc.Keyvalue.PutResponse> getPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Put",
      requestType = kvGrpc.Keyvalue.PutRequest.class,
      responseType = kvGrpc.Keyvalue.PutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kvGrpc.Keyvalue.PutRequest,
      kvGrpc.Keyvalue.PutResponse> getPutMethod() {
    io.grpc.MethodDescriptor<kvGrpc.Keyvalue.PutRequest, kvGrpc.Keyvalue.PutResponse> getPutMethod;
    if ((getPutMethod = KeyValueGrpc.getPutMethod) == null) {
      synchronized (KeyValueGrpc.class) {
        if ((getPutMethod = KeyValueGrpc.getPutMethod) == null) {
          KeyValueGrpc.getPutMethod = getPutMethod =
              io.grpc.MethodDescriptor.<kvGrpc.Keyvalue.PutRequest, kvGrpc.Keyvalue.PutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Put"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kvGrpc.Keyvalue.PutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kvGrpc.Keyvalue.PutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeyValueMethodDescriptorSupplier("Put"))
              .build();
        }
      }
    }
    return getPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kvGrpc.Keyvalue.GetRequest,
      kvGrpc.Keyvalue.GetResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType = kvGrpc.Keyvalue.GetRequest.class,
      responseType = kvGrpc.Keyvalue.GetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kvGrpc.Keyvalue.GetRequest,
      kvGrpc.Keyvalue.GetResponse> getGetMethod() {
    io.grpc.MethodDescriptor<kvGrpc.Keyvalue.GetRequest, kvGrpc.Keyvalue.GetResponse> getGetMethod;
    if ((getGetMethod = KeyValueGrpc.getGetMethod) == null) {
      synchronized (KeyValueGrpc.class) {
        if ((getGetMethod = KeyValueGrpc.getGetMethod) == null) {
          KeyValueGrpc.getGetMethod = getGetMethod =
              io.grpc.MethodDescriptor.<kvGrpc.Keyvalue.GetRequest, kvGrpc.Keyvalue.GetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kvGrpc.Keyvalue.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kvGrpc.Keyvalue.GetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeyValueMethodDescriptorSupplier("Get"))
              .build();
        }
      }
    }
    return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kvGrpc.Keyvalue.DeleteRequest,
      kvGrpc.Keyvalue.DeleteResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Delete",
      requestType = kvGrpc.Keyvalue.DeleteRequest.class,
      responseType = kvGrpc.Keyvalue.DeleteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kvGrpc.Keyvalue.DeleteRequest,
      kvGrpc.Keyvalue.DeleteResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<kvGrpc.Keyvalue.DeleteRequest, kvGrpc.Keyvalue.DeleteResponse> getDeleteMethod;
    if ((getDeleteMethod = KeyValueGrpc.getDeleteMethod) == null) {
      synchronized (KeyValueGrpc.class) {
        if ((getDeleteMethod = KeyValueGrpc.getDeleteMethod) == null) {
          KeyValueGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<kvGrpc.Keyvalue.DeleteRequest, kvGrpc.Keyvalue.DeleteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kvGrpc.Keyvalue.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kvGrpc.Keyvalue.DeleteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeyValueMethodDescriptorSupplier("Delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KeyValueStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyValueStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyValueStub>() {
        @java.lang.Override
        public KeyValueStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyValueStub(channel, callOptions);
        }
      };
    return KeyValueStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KeyValueBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyValueBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyValueBlockingStub>() {
        @java.lang.Override
        public KeyValueBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyValueBlockingStub(channel, callOptions);
        }
      };
    return KeyValueBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KeyValueFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyValueFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyValueFutureStub>() {
        @java.lang.Override
        public KeyValueFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyValueFutureStub(channel, callOptions);
        }
      };
    return KeyValueFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * key-value service definition
   * </pre>
   */
  public static abstract class KeyValueImplBase implements io.grpc.BindableService {

    /**
     */
    public void put(kvGrpc.Keyvalue.PutRequest request,
        io.grpc.stub.StreamObserver<kvGrpc.Keyvalue.PutResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPutMethod(), responseObserver);
    }

    /**
     */
    public void get(kvGrpc.Keyvalue.GetRequest request,
        io.grpc.stub.StreamObserver<kvGrpc.Keyvalue.GetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void delete(kvGrpc.Keyvalue.DeleteRequest request,
        io.grpc.stub.StreamObserver<kvGrpc.Keyvalue.DeleteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPutMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                kvGrpc.Keyvalue.PutRequest,
                kvGrpc.Keyvalue.PutResponse>(
                  this, METHODID_PUT)))
          .addMethod(
            getGetMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                kvGrpc.Keyvalue.GetRequest,
                kvGrpc.Keyvalue.GetResponse>(
                  this, METHODID_GET)))
          .addMethod(
            getDeleteMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                kvGrpc.Keyvalue.DeleteRequest,
                kvGrpc.Keyvalue.DeleteResponse>(
                  this, METHODID_DELETE)))
          .build();
    }
  }

  /**
   * <pre>
   * key-value service definition
   * </pre>
   */
  public static final class KeyValueStub extends io.grpc.stub.AbstractAsyncStub<KeyValueStub> {
    private KeyValueStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyValueStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyValueStub(channel, callOptions);
    }

    /**
     */
    public void put(kvGrpc.Keyvalue.PutRequest request,
        io.grpc.stub.StreamObserver<kvGrpc.Keyvalue.PutResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(kvGrpc.Keyvalue.GetRequest request,
        io.grpc.stub.StreamObserver<kvGrpc.Keyvalue.GetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(kvGrpc.Keyvalue.DeleteRequest request,
        io.grpc.stub.StreamObserver<kvGrpc.Keyvalue.DeleteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * key-value service definition
   * </pre>
   */
  public static final class KeyValueBlockingStub extends io.grpc.stub.AbstractBlockingStub<KeyValueBlockingStub> {
    private KeyValueBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyValueBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyValueBlockingStub(channel, callOptions);
    }

    /**
     */
    public kvGrpc.Keyvalue.PutResponse put(kvGrpc.Keyvalue.PutRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public kvGrpc.Keyvalue.GetResponse get(kvGrpc.Keyvalue.GetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public kvGrpc.Keyvalue.DeleteResponse delete(kvGrpc.Keyvalue.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * key-value service definition
   * </pre>
   */
  public static final class KeyValueFutureStub extends io.grpc.stub.AbstractFutureStub<KeyValueFutureStub> {
    private KeyValueFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyValueFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyValueFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kvGrpc.Keyvalue.PutResponse> put(
        kvGrpc.Keyvalue.PutRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kvGrpc.Keyvalue.GetResponse> get(
        kvGrpc.Keyvalue.GetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kvGrpc.Keyvalue.DeleteResponse> delete(
        kvGrpc.Keyvalue.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PUT = 0;
  private static final int METHODID_GET = 1;
  private static final int METHODID_DELETE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final KeyValueImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(KeyValueImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PUT:
          serviceImpl.put((kvGrpc.Keyvalue.PutRequest) request,
              (io.grpc.stub.StreamObserver<kvGrpc.Keyvalue.PutResponse>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((kvGrpc.Keyvalue.GetRequest) request,
              (io.grpc.stub.StreamObserver<kvGrpc.Keyvalue.GetResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((kvGrpc.Keyvalue.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<kvGrpc.Keyvalue.DeleteResponse>) responseObserver);
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

  private static abstract class KeyValueBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KeyValueBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kvGrpc.Keyvalue.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KeyValue");
    }
  }

  private static final class KeyValueFileDescriptorSupplier
      extends KeyValueBaseDescriptorSupplier {
    KeyValueFileDescriptorSupplier() {}
  }

  private static final class KeyValueMethodDescriptorSupplier
      extends KeyValueBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    KeyValueMethodDescriptorSupplier(String methodName) {
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
      synchronized (KeyValueGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KeyValueFileDescriptorSupplier())
              .addMethod(getPutMethod())
              .addMethod(getGetMethod())
              .addMethod(getDeleteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
