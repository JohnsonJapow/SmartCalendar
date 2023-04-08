package SpendingTracker_GRPC;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: SpendingTracker.proto")
public final class SpendingTrackerGrpc {

  private SpendingTrackerGrpc() {}

  public static final String SERVICE_NAME = "extra_smart.SpendingTracker";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SpendingTracker_GRPC.TransactionRequest,
      SpendingTracker_GRPC.TransactionResponse> getRecordTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordTransaction",
      requestType = SpendingTracker_GRPC.TransactionRequest.class,
      responseType = SpendingTracker_GRPC.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<SpendingTracker_GRPC.TransactionRequest,
      SpendingTracker_GRPC.TransactionResponse> getRecordTransactionMethod() {
    io.grpc.MethodDescriptor<SpendingTracker_GRPC.TransactionRequest, SpendingTracker_GRPC.TransactionResponse> getRecordTransactionMethod;
    if ((getRecordTransactionMethod = SpendingTrackerGrpc.getRecordTransactionMethod) == null) {
      synchronized (SpendingTrackerGrpc.class) {
        if ((getRecordTransactionMethod = SpendingTrackerGrpc.getRecordTransactionMethod) == null) {
          SpendingTrackerGrpc.getRecordTransactionMethod = getRecordTransactionMethod = 
              io.grpc.MethodDescriptor.<SpendingTracker_GRPC.TransactionRequest, SpendingTracker_GRPC.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "extra_smart.SpendingTracker", "RecordTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SpendingTracker_GRPC.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SpendingTracker_GRPC.TransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SpendingTrackerMethodDescriptorSupplier("RecordTransaction"))
                  .build();
          }
        }
     }
     return getRecordTransactionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SpendingTrackerStub newStub(io.grpc.Channel channel) {
    return new SpendingTrackerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SpendingTrackerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SpendingTrackerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SpendingTrackerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SpendingTrackerFutureStub(channel);
  }

  /**
   */
  public static abstract class SpendingTrackerImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<SpendingTracker_GRPC.TransactionRequest> recordTransaction(
        io.grpc.stub.StreamObserver<SpendingTracker_GRPC.TransactionResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getRecordTransactionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRecordTransactionMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                SpendingTracker_GRPC.TransactionRequest,
                SpendingTracker_GRPC.TransactionResponse>(
                  this, METHODID_RECORD_TRANSACTION)))
          .build();
    }
  }

  /**
   */
  public static final class SpendingTrackerStub extends io.grpc.stub.AbstractStub<SpendingTrackerStub> {
    private SpendingTrackerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SpendingTrackerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SpendingTrackerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SpendingTrackerStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<SpendingTracker_GRPC.TransactionRequest> recordTransaction(
        io.grpc.stub.StreamObserver<SpendingTracker_GRPC.TransactionResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getRecordTransactionMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class SpendingTrackerBlockingStub extends io.grpc.stub.AbstractStub<SpendingTrackerBlockingStub> {
    private SpendingTrackerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SpendingTrackerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SpendingTrackerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SpendingTrackerBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class SpendingTrackerFutureStub extends io.grpc.stub.AbstractStub<SpendingTrackerFutureStub> {
    private SpendingTrackerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SpendingTrackerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SpendingTrackerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SpendingTrackerFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_RECORD_TRANSACTION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SpendingTrackerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SpendingTrackerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RECORD_TRANSACTION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.recordTransaction(
              (io.grpc.stub.StreamObserver<SpendingTracker_GRPC.TransactionResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SpendingTrackerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SpendingTrackerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SpendingTracker_GRPC.SmartServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SpendingTracker");
    }
  }

  private static final class SpendingTrackerFileDescriptorSupplier
      extends SpendingTrackerBaseDescriptorSupplier {
    SpendingTrackerFileDescriptorSupplier() {}
  }

  private static final class SpendingTrackerMethodDescriptorSupplier
      extends SpendingTrackerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SpendingTrackerMethodDescriptorSupplier(String methodName) {
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
      synchronized (SpendingTrackerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SpendingTrackerFileDescriptorSupplier())
              .addMethod(getRecordTransactionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
