package ScheduleOptimizer_GRPC;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import ScheduleOptimizer_GRPC.ScheduleOptimizerGrpc;
import ScheduleOptimizer_GRPC.ScheduleOptimizerGrpc.ScheduleOptimizerBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ScheduleOptimizerClient {
	private static Logger logger= Logger.getLogger(ScheduleOptimizerClient.class.getName());
	
	private static ScheduleOptimizerBlockingStub blockingStub;
	//ivate static ScheduleOptimizerGrpc.ScheduleOptimizerStub asyncStub;
	public static void main(String[] args) throws Exception {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50055).usePlaintext().build();
		
		blockingStub=ScheduleOptimizerGrpc.newBlockingStub(channel);
		//yncStub=ScheduleOptimizerGrpc.newStub(channel);

		setChallenge();
		//try {
		//	channel.awaitTermination(120, TimeUnit.SECONDS);
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
		//}
		System.out.println("finish");
	}
	private static void setChallenge() {
		// TODO Auto-generated method stub
		Goal request=Goal.newBuilder().setEndDate((int)500).setIdealBalance((float) 100.00).build();
		GoalResponse response=blockingStub.setChallenge(request); //setChallenge(request);
		
		System.out.println((response.getSuccess())+","+response.getMessage());
	}

}
