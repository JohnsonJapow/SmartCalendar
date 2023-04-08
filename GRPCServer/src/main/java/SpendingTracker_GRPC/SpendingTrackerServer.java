package SpendingTracker_GRPC;

import java.io.IOException;
import java.util.logging.Logger;

import SpendingTracker_GRPC.SpendingTrackerGrpc.SpendingTrackerImplBase;
//import SpendingTracker_GRPC.SpendingTrackerServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class SpendingTrackerServer extends SpendingTrackerImplBase {
	// First we create a logger to show server side logs in the console. logger instance will be used to log different events at the server console.
	private static final Logger logger = Logger.getLogger(SpendingTrackerServer.class.getName());
	private float balance=0;
	public static void main(String[] args){
		
		
		SpendingTrackerServer spendingserver=new SpendingTrackerServer();
		int port=50051;
		Server server;
		try {
			server=ServerBuilder.forPort(port).addService(spendingserver).build().start();
			logger.info("Server started, listening on " + port);
			server.awaitTermination();
		} catch (InterruptedException | IOException e) {	
			e.printStackTrace();
		}
	}
	
	@Override
	public StreamObserver<TransactionRequest> recordTransaction(StreamObserver<TransactionResponse> responseObserver) {
		return new StreamObserver<TransactionRequest>(){

			@Override
			public void onNext(TransactionRequest request) {
				// TODO Auto-generated method stub
				float currentBalance=balance+request.getIncome()-request.getSpending();
				
				TransactionRecord record=TransactionRecord.newBuilder().setIncome(request.getIncome()).setSpending(request.getSpending()).setBalance(currentBalance).build();
				
				
				TransactionResponse response = TransactionResponse.newBuilder().setBalance(currentBalance).addRecords(record).build();
				
				responseObserver.onNext(response);
				
				balance=currentBalance;
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				responseObserver.onCompleted();
				
			}
		
			
		//System.out.println("--- Receiving Request from Client ---");
		
		//TransactionResponse reply=TransactionResponse.newBuilder().setBalance().build());
		
		};
	}
	
}
