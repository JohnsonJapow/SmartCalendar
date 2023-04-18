package SpendingTrackerGRPC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import SpendingTrackerGRPC.SpendingTrackerGrpc.SpendingTrackerImplBase;
//import SpendingTracker_GRPC.SpendingTrackerServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class SpendingTrackerServer extends SpendingTrackerImplBase {
	// First we create a logger to show server side logs in the console. logger instance will be used to log different events at the server console.
	private static final Logger logger = Logger.getLogger(SpendingTrackerServer.class.getName());
	private static float currentBalance;
	private static String dataFilePath = "data.txt";
	public static void main(String[] args){
		
		
		SpendingTrackerServer spendingserver=new SpendingTrackerServer();
		int port=50051;
		Server server;
		try {
			readCurrentBalanceFromFile();
			server=ServerBuilder.forPort(port).addService(spendingserver).build().start();
			logger.info("Server started, listening on " + port);
			server.awaitTermination();
		} catch (InterruptedException | IOException e) {	
			e.printStackTrace();
		}
	}
	
	@Override
	public StreamObserver<TransactionRequest> recordTransaction(final StreamObserver<TransactionResponse> responseObserver) {
		return new StreamObserver<TransactionRequest>(){
			//balance is daily balance
			//currentBalance is the amount of balance
			
			@Override
			public void onNext(TransactionRequest request) {
				// TODO Auto-generated method stub
				float balance=request.getIncome()-request.getSpending();
				currentBalance+=balance;
				
                // Write the updated balance to a file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFilePath))) {
                    writer.write(Float.toString(currentBalance));
                } catch (IOException e) {
                    e.printStackTrace();
                }
				
				TransactionResponse response = TransactionResponse.newBuilder().setMessage("Today's  balance : "+balance).setBalance(currentBalance).build();
				
				responseObserver.onNext(response);

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				responseObserver.onError(t);
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
	 private static void readCurrentBalanceFromFile() {
	     try {
	            if (Files.exists(Paths.get(dataFilePath))) {
	                BufferedReader reader = new BufferedReader(new FileReader(dataFilePath));
	                String line = reader.readLine();
	                if (line != null && !line.isEmpty()) {
	                    currentBalance = Float.parseFloat(line);
	                }
	            } else {
	                currentBalance = 0;
	                Files.createFile(Paths.get(dataFilePath));
	                try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFilePath))) {
	                    writer.write(Float.toString(currentBalance));
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }   
	 }

	
}
