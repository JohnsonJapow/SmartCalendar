package SpendingTracker_GRPC;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import SpendingTracker_GRPC.SpendingTrackerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class SpendingTrackerClient {
	
	private static Logger logger=Logger.getLogger(SpendingTrackerClient.class.getName());
	// Creating stubs for establishing the connection with server.
	// Blocking stub
	private static SpendingTrackerGrpc.SpendingTrackerBlockingStub blockingStub;
	// Asynch stub
	private static SpendingTrackerGrpc.SpendingTrackerStub asyncStub;
	
	public static void main(String[] args)throws Exception {

		int transactionsCounter;
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
		
		blockingStub=SpendingTrackerGrpc.newBlockingStub(channel);
		asyncStub=SpendingTrackerGrpc.newStub(channel);
		
		recordTransaction();
		
		try {
			channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void recordTransaction() {
		// TODO Auto-generated method stub
		StreamObserver<TransactionResponse> responseObserver=new StreamObserver<TransactionResponse>() {

			@Override
			public void onNext(TransactionResponse value) {
				// TODO Auto-generated method stub
				System.out.println(value.getMessage());
				System.out.println("The current balance is " + value.getBalance());
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("server completed");
				
			}	
		};
		StreamObserver<TransactionRequest> requestObserver=asyncStub.recordTransaction(responseObserver);
		try {
			requestObserver.onNext(TransactionRequest.newBuilder().setIncome(100).setSpending(50).build());
			requestObserver.onNext(TransactionRequest.newBuilder().setIncome(90).setSpending(60).build());
			requestObserver.onNext(TransactionRequest.newBuilder().setIncome(80).setSpending(70).build());
			
			System.out.println("SENDING EMSSAGES");

			// Mark the end of requests
			requestObserver.onCompleted();


			// Sleep for a bit before sending the next one.
			Thread.sleep(new Random().nextInt(1000) + 500);
		} 
		catch (RuntimeException e) {
			e.printStackTrace();
		} 
		catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}

}
