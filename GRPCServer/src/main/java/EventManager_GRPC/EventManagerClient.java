package EventManager_GRPC;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import EventManager_GRPC.EventManagerGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class EventManagerClient {
	private static Logger logger= Logger.getLogger(EventManagerClient.class.getName());
	
	private static EventManagerGrpc.EventManagerBlockingStub blockingStub;
	private static EventManagerGrpc.EventManagerStub asyncStub;
	
	public static void main(String[] args) {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();
		
		blockingStub=EventManagerGrpc.newBlockingStub(channel);
		asyncStub=EventManagerGrpc.newStub(channel);
		
		addEvent();
		modifyEvent();
		listEvent();
		try {
			channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private static void addEvent() {
		// TODO Auto-generated method stub
		Event request=Event.newBuilder().setId("ABC173").setName("Dating").setDescription("Excellent").setDate(327).build();
		
		EventResponse response=blockingStub.addEvent(request);
		
		System.out.println(response.getSuccess()+","+response.getMessage());
	}
	private static void modifyEvent() {
		StreamObserver<EventResponse> responseObserver = new StreamObserver<EventResponse>() {

			@Override
			public void onNext(EventResponse value) {
				// TODO Auto-generated method stub
				System.out.println("receiving message: " + value.getSuccess()+value.getMessage());
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("completed ");
			}
			
		};
		StreamObserver<EventModificationRequest> requestObserver = asyncStub.modifyEvent(responseObserver);
		try {
			//requestObserver.onNext(EventModificationRequest.newBuilder().setId("ABC123").setName("NCI").setDescription("TABA2").setDate(30).build());
			requestObserver.onNext(EventModificationRequest.newBuilder().setId("ABC133").setName("working").setDescription("I like working").setDate(300).build());

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
		finally {
			
		}

	}
	public static void listEvent() {
		DateRange request = DateRange.newBuilder().setStartDate(0).setEndDate(0).build();
		
		StreamObserver<Event> responseObserver=new StreamObserver<Event>() {
			int count=0;
			@Override
			public void onNext(Event value) {
				System.out.println("receiving messages " + value);
				count += 1;
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed ... received "+ count+ " messages");
			}
		};
		asyncStub.listEvents(request, responseObserver);
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
