package ScheduleOptimizerGRPC;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import ScheduleOptimizerGRPC.ScheduleOptimizerGrpc.ScheduleOptimizerBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class ScheduleOptimizerClient {
	private static Logger logger= Logger.getLogger(ScheduleOptimizerClient.class.getName());
	
	private static ScheduleOptimizerGrpc.ScheduleOptimizerBlockingStub blockingStub;
	private static ScheduleOptimizerGrpc.ScheduleOptimizerStub asyncStub;
	public static void main(String[] args) throws Exception {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50055).usePlaintext().build();
		
		blockingStub=ScheduleOptimizerGrpc.newBlockingStub(channel);
		
		asyncStub=ScheduleOptimizerGrpc.newStub(channel);
		mysetChallenge();
		try {
			channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("finish");
	}
	public static void mysetChallenge() {
		// TODO Auto-generated method stub
		try {
			Goal request=Goal.newBuilder().setEndDate((long)10000).setIdealBalance((float) 100.0).build();
			GoalResponse response=blockingStub.setChallenge(request); //setChallenge(request);
			
			System.out.println((response.getSuccess())+","+response.getMessage());
		}
		catch(StatusRuntimeException ex) {
			System.out.println( ex.getMessage());
		}
	}

}
