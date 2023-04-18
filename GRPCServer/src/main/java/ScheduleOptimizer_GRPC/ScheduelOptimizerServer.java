package ScheduleOptimizer_GRPC;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Logger;

import EventManager_GRPC.EventManagerServer;
import EventManager_GRPC.EventResponse;
import ScheduleOptimizer_GRPC.ScheduleOptimizerGrpc.ScheduleOptimizerImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ScheduelOptimizerServer extends ScheduleOptimizerImplBase{

private static final Logger logger = Logger.getLogger(EventManagerServer.class.getName());
    
    
	public static void main(String[] args){
			
			
		EventManagerServer eventserver=new EventManagerServer();
			int port=50055;
			Server server;
			try {
				server=ServerBuilder.forPort(port).addService(eventserver).build().start();
				logger.info("Server started, listening on " + port);
				server.awaitTermination();
			} catch (InterruptedException | IOException e) {	
				e.printStackTrace();
			}
			
	}


	@Override
	public void setChallenge(Goal request, StreamObserver<GoalResponse> responseObserver) {
		System.out.println("receiving new event request");
		try {
			BufferedReader reader=new BufferedReader(new FileReader("data.txt"));
			String firstBalance=reader.readLine();
			float oriB=Long.parseLong(firstBalance);
			long unixDate=request.getEndDate();
			Thread.sleep(unixDate);
			//Timer timer=new Timer();
				//timer.schedule(checkFinal, unixDate);
			
			String endBalance=reader.readLine();
			float endB=Long.parseLong(endBalance);
			reader.close();
			if(request.getIdealBalance()==(endB-oriB)){
				responseObserver.onNext(GoalResponse.newBuilder().setSuccess(true).setMessage("Good job!! YOu make it!!").build());
			}
			else {
				responseObserver.onNext(GoalResponse.newBuilder().setSuccess(false).setMessage("Allmost there, try it one more time").build());
			}
			
		} catch (InterruptedException|IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		responseObserver.onCompleted();
		
	}
	public float checkFinal() {
		float c = 0;
		try {
			BufferedReader reader=new BufferedReader(new FileReader("data.txt"));
			String endBalance=reader.readLine();
			float endB=Long.parseLong(endBalance);
			c=endB;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}
}
