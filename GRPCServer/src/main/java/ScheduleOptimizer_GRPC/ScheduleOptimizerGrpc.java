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
    comments = "Source: ScheduleOptimizer.proto")
public final class ScheduleOptimizerGrpc {

  private ScheduleOptimizerGrpc() {}

  public static final String SERVICE_NAME = "extra_smart.ScheduleOptimizer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ScheduleOptimizer_GRPC.MonthlyGoal,
      ScheduleOptimizer_GRPC.GoalResponse> getSetMonthlyGoalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetMonthlyGoal",
      requestType = ScheduleOptimizer_GRPC.MonthlyGoal.class,
      responseType = ScheduleOptimizer_GRPC.GoalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ScheduleOptimizer_GRPC.MonthlyGoal,
      ScheduleOptimizer_GRPC.GoalResponse> getSetMonthlyGoalMethod() {
    io.grpc.MethodDescriptor<ScheduleOptimizer_GRPC.MonthlyGoal, ScheduleOptimizer_GRPC.GoalResponse> getSetMonthlyGoalMethod;
    if ((getSetMonthlyGoalMethod = ScheduleOptimizerGrpc.getSetMonthlyGoalMethod) == null) {
      synchronized (ScheduleOptimizerGrpc.class) {
        if ((getSetMonthlyGoalMethod = ScheduleOptimizerGrpc.getSetMonthlyGoalMethod) == null) {
          ScheduleOptimizerGrpc.getSetMonthlyGoalMethod = getSetMonthlyGoalMethod = 
              io.grpc.MethodDescriptor.<ScheduleOptimizer_GRPC.MonthlyGoal, ScheduleOptimizer_GRPC.GoalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "extra_smart.ScheduleOptimizer", "SetMonthlyGoal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ScheduleOptimizer_GRPC.MonthlyGoal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ScheduleOptimizer_GRPC.GoalResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ScheduleOptimizerMethodDescriptorSupplier("SetMonthlyGoal"))
                  .build();
          }
        }
     }
     return getSetMonthlyGoalMethod;
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
    public void setMonthlyGoal(ScheduleOptimizer_GRPC.MonthlyGoal request,
        io.grpc.stub.StreamObserver<ScheduleOptimizer_GRPC.GoalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetMonthlyGoalMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetMonthlyGoalMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ScheduleOptimizer_GRPC.MonthlyGoal,
                ScheduleOptimizer_GRPC.GoalResponse>(
                  this, METHODID_SET_MONTHLY_GOAL)))
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
    public void setMonthlyGoal(ScheduleOptimizer_GRPC.MonthlyGoal request,
        io.grpc.stub.StreamObserver<ScheduleOptimizer_GRPC.GoalResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetMonthlyGoalMethod(), getCallOptions()), request, responseObserver);
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
    public ScheduleOptimizer_GRPC.GoalResponse setMonthlyGoal(ScheduleOptimizer_GRPC.MonthlyGoal request) {
      return blockingUnaryCall(
          getChannel(), getSetMonthlyGoalMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<ScheduleOptimizer_GRPC.GoalResponse> setMonthlyGoal(
        ScheduleOptimizer_GRPC.MonthlyGoal request) {
      return futureUnaryCall(
          getChannel().newCall(getSetMonthlyGoalMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_MONTHLY_GOAL = 0;

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
        case METHODID_SET_MONTHLY_GOAL:
          serviceImpl.setMonthlyGoal((ScheduleOptimizer_GRPC.MonthlyGoal) request,
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
              .addMethod(getSetMonthlyGoalMethod())
              .build();
        }
      }
    }
    return result;
  }
}
