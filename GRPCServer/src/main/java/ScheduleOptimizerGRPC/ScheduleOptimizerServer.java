package ScheduleOptimizerGRPC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import EventManagerGRPC.EventManagerServer;
import ScheduleOptimizerGRPC.ScheduleOptimizerGrpc.ScheduleOptimizerImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ScheduleOptimizerServer extends ScheduleOptimizerImplBase{

private static final Logger logger = Logger.getLogger(EventManagerServer.class.getName());
    
    
	public static void main(String[] args) throws IOException, InterruptedException{
			
			
			ScheduleOptimizerServer ScheduleOptimizerserver=new ScheduleOptimizerServer();
			int port=50055;
			Server server;
		
			server=ServerBuilder.forPort(port).addService(ScheduleOptimizerserver).build().start();
			logger.info("Server started, listening on " + port);
			server.awaitTermination();
	
			
	}


	@Override
	public void setChallenge(Goal request, StreamObserver<GoalResponse> responseObserver) {
		// TODO Auto-generated method stub
		
		System.out.println("receiving new event request");
		try {
			BufferedReader reader=new BufferedReader(new FileReader("data.txt"));
			BufferedReader reader2=new BufferedReader(new FileReader("data.txt"));
			String firstBalance=reader.readLine().trim();
			float oriB=Float.parseFloat(firstBalance);
			Thread.sleep(request.getEndDate());
			String endBalance=reader2.readLine().trim();
			reader.close();
			float endB=Float.parseFloat(endBalance);
			if(request.getIdealBalance()<=(endB-oriB)){
				responseObserver.onNext(GoalResponse.newBuilder().setSuccess(true).setMessage("Good job!! YOu make it!!").build());
			}
			else {
				responseObserver.onNext(GoalResponse.newBuilder().setSuccess(false).setMessage("Allmost there, try it one more time").build());
			}
			
		} catch (InterruptedException|IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//responseObserver.onNext(GoalResponse.newBuilder().setSuccess(false).setMessage("Allmost there, try it one more time"+request.getEndDate()+","+request.getIdealBalance()).build());
		responseObserver.onCompleted();
	}


}
