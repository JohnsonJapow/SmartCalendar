package EventManager_GRPC;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

import EventManager_GRPC.EventManagerGrpc.EventManagerImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;



public class EventManagerServer extends EventManagerImplBase {
	private static final Logger logger = Logger.getLogger(EventManagerServer.class.getName());
    private LinkedList<Event> events = new LinkedList<>();
	public static void main(String[] args){
			
			
		EventManagerServer eventserver=new EventManagerServer();
			int port=50052;
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
	public void addEvent(Event request, StreamObserver<EventResponse> responseObserver) {
		System.out.println("receiving new event request");
        boolean isDuplicate = isDuplicate(request.getId());
        if (isDuplicate) {
            String message = String.format("Event with ID %s already exists", request.getId());
            responseObserver.onNext(EventResponse.newBuilder().setSuccess(false).setMessage(message).build());
        } else {
            events.add(request);
            String message = String.format("Event with ID %s has been added successfully", request.getId());
            responseObserver.onNext(EventResponse.newBuilder().setSuccess(true).setMessage(message).build());
        }
        responseObserver.onCompleted();
		
	}
    private boolean isDuplicate(String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

	@Override
	public StreamObserver<EventModificationRequest> modifyEvent(StreamObserver<EventResponse> responseObserver) {
		   return new StreamObserver<EventModificationRequest>() {
		        @Override
		        public void onNext(EventModificationRequest request) {
		            EventResponse response;
		            String eventId = request.getId();
		            Event existingEvent = null;
		            
		            // Search for existing event with same ID
		            for (Event event : events) {
		                if (event.getId().equals(eventId)) {
		                    existingEvent = event;
		                    break;
		                }
		            }
		            
		            if (existingEvent == null) {
		                // Event not found, return error response
		                response = EventResponse.newBuilder()
		                        .setSuccess(false)
		                        .setMessage("Event not found")
		                        .build();
		            } else {
		                // Modify the existing event based on the request
		                String name = existingEvent.getName();
		                String description = existingEvent.getDescription();
		                int reminder = existingEvent.getReminder();
		                long date = existingEvent.getDate();
		                
		                    name = request.getName();
		                    description = request.getDescription();
		                    reminder = request.getReminder();
		                    date = request.getDate();
		                
		                
		                // Create the modified event
		                Event modifiedEvent = Event.newBuilder()
		                        .setId(eventId)
		                        .setName(name)
		                        .setDescription(description)
		                        .setReminder(reminder)
		                        .setDate(date)
		                        .build();
		                
		                // Replace the existing event with the modified event
		                events.remove(existingEvent);
		                events.add(modifiedEvent);
		                
		                // Return success response
		                response = EventResponse.newBuilder()
		                        .setSuccess(true)
		                        .setMessage("Event modified successfully")
		                        .build();
		            }
		            
		            responseObserver.onNext(response);
		        }

		        @Override
		        public void onError(Throwable t) {
		            // Handle error
		        }

		        @Override
		        public void onCompleted() {
		            responseObserver.onCompleted();
		        }
		    };
	}

	@Override
	public void listEvents(DateRange request, StreamObserver<Event> responseObserver) {
	    long startTime = request.getStartDate();
	    long endTime = request.getEndDate();

	    for (Event event : events) {
	        long eventTime = event.getDate();
	        if (eventTime >= startTime && eventTime <= endTime) {
	            responseObserver.onNext(event);
	        }
	    }

	    responseObserver.onCompleted();
	}
	
}


