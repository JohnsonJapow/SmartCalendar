package ScheduleOptimizer_GRPC;

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
    comments = "Source: ScheduelOptimizer.proto")
public final class ScheduleOptimizerGrpc {

  private ScheduleOptimizerGrpc() {}

  public static final String SERVICE_NAME = "extra_smart.ScheduleOptimizer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ScheduleOptimizer_GRPC.Goal,
      ScheduleOptimizer_GRPC.GoalResponse> getSetChallengeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetChallenge",
      requestType = ScheduleOptimizer_GRPC.Goal.class,
      responseType = ScheduleOptimizer_GRPC.GoalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ScheduleOptimizer_GRPC.Goal,
      ScheduleOptimizer_GRPC.GoalResponse> getSetChallengeMethod() {
    io.grpc.MethodDescriptor<ScheduleOptimizer_GRPC.Goal, ScheduleOptimizer_GRPC.GoalResponse> getSetChallengeMethod;
    if ((getSetChallengeMethod = ScheduleOptimizerGrpc.getSetChallengeMethod) == null) {
      synchronized (ScheduleOptimizerGrpc.class) {
        if ((getSetChallengeMethod = ScheduleOptimizerGrpc.getSetChallengeMethod) == null) {
          ScheduleOptimizerGrpc.getSetChallengeMethod = getSetChallengeMethod = 
              io.grpc.MethodDescriptor.<ScheduleOptimizer_GRPC.Goal, ScheduleOptimizer_GRPC.GoalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "extra_smart.ScheduleOptimizer", "SetChallenge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ScheduleOptimizer_GRPC.Goal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ScheduleOptimizer_GRPC.GoalResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ScheduleOptimizerMethodDescriptorSupplier("SetChallenge"))
                  .build();
          }
        }
     }
     return getSetChallengeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ScheduleOptimizerStub newStub(io.grpc.Channel channel) {
    return new ScheduleOptimizerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ScheduleOptimizerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ScheduleOptimizerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ScheduleOptimizerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ScheduleOptimizerFutureStub(channel);
  }

  /**
   */
  public static abstract class ScheduleOptimizerImplBase implements io.grpc.BindableService {

    /**
     */
    public void setChallenge(ScheduleOptimizer_GRPC.Goal request,
        io.grpc.stub.StreamObserver<ScheduleOptimizer_GRPC.GoalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetChallengeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetChallengeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ScheduleOptimizer_GRPC.Goal,
                ScheduleOptimizer_GRPC.GoalResponse>(
                  this, METHODID_SET_CHALLENGE)))
          .build();
    }
  }

  /**
   */
  public static final class ScheduleOptimizerStub extends io.grpc.stub.AbstractStub<ScheduleOptimizerStub> {
    private ScheduleOptimizerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ScheduleOptimizerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ScheduleOptimizerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ScheduleOptimizerStub(channel, callOptions);
    }

    /**
     */
    public void setChallenge(ScheduleOptimizer_GRPC.Goal request,
        io.grpc.stub.StreamObserver<ScheduleOptimizer_GRPC.GoalResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetChallengeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ScheduleOptimizerBlockingStub extends io.grpc.stub.AbstractStub<ScheduleOptimizerBlockingStub> {
    private ScheduleOptimizerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ScheduleOptimizerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ScheduleOptimizerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ScheduleOptimizerBlockingStub(channel, callOptions);
    }

    /**
     */
    public ScheduleOptimizer_GRPC.GoalResponse setChallenge(ScheduleOptimizer_GRPC.Goal request) {
      return blockingUnaryCall(
          getChannel(), getSetChallengeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ScheduleOptimizerFutureStub extends io.grpc.stub.AbstractStub<ScheduleOptimizerFutureStub> {
    private ScheduleOptimizerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ScheduleOptimizerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ScheduleOptimizerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ScheduleOptimizerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ScheduleOptimizer_GRPC.GoalResponse> setChallenge(
        ScheduleOptimizer_GRPC.Goal request) {
      return futureUnaryCall(
          getChannel().newCall(getSetChallengeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_CHALLENGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ScheduleOptimizerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ScheduleOptimizerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_CHALLENGE:
          serviceImpl.setChallenge((ScheduleOptimizer_GRPC.Goal) request,
              (io.grpc.stub.StreamObserver<ScheduleOptimizer_GRPC.GoalResponse>) responseObserver);
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

  private static abstract class ScheduleOptimizerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ScheduleOptimizerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ScheduleOptimizer_GRPC.SmartServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ScheduleOptimizer");
    }
  }

  private static final class ScheduleOptimizerFileDescriptorSupplier
      extends ScheduleOptimizerBaseDescriptorSupplier {
    ScheduleOptimizerFileDescriptorSupplier() {}
  }

  private static final class ScheduleOptimizerMethodDescriptorSupplier
      extends ScheduleOptimizerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ScheduleOptimizerMethodDescriptorSupplier(String methodName) {
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
      synchronized (ScheduleOptimizerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ScheduleOptimizerFileDescriptorSupplier())
              .addMethod(getSetChallengeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
