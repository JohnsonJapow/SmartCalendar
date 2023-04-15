package EventManager_GRPC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.logging.Logger;

import EventManager_GRPC.EventManagerGrpc.EventManagerImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;



public class EventManagerServer extends EventManagerImplBase {
	private static final Logger logger = Logger.getLogger(EventManagerServer.class.getName());
    private LinkedList<Event> events = new LinkedList<>();
    private static String eventPath="event.txt";
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
	private LinkedList<String> readEventFromFile() {
	    LinkedList<String> values = new LinkedList<String>();
	    try {
	        if (Files.exists(Paths.get(eventPath))) {
	            BufferedReader reader = new BufferedReader(new FileReader(eventPath));
	            String line = reader.readLine();
	            while (line != null && !line.isEmpty()) {
	                String[] value = line.split(",");
	                for (String v : value) {
	                    values.add(v.trim());
	                }
	                line = reader.readLine();
	            }
	            reader.close();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return values;
	}
	@Override
	public void addEvent(Event request, StreamObserver<EventResponse> responseObserver) {
		System.out.println("receiving new event request");
		if(!Files.exists(Paths.get(eventPath))){
			try{Files.createFile(Paths.get(eventPath));
			
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		LinkedList<String>ata=readEventFromFile();
        boolean isDuplicate = isDuplicate(request.getId(),ata);
        if (isDuplicate) {
            String message = String.format("Event with ID %s already exists", request.getId());
            responseObserver.onNext(EventResponse.newBuilder().setSuccess(false).setMessage(message).build());
        }
        else {		
        	try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventPath,true))) {
                String line = String.format("%s,%s,%s,%s,%s\n", request.getId(), request.getName(), request.getDescription(), request.getReminder(), request.getDate());
                writer.write(line);
                String message = String.format("Event with ID %s has been added successfully", request.getId());
                responseObserver.onNext(EventResponse.newBuilder().setSuccess(true).setMessage(message).build());
        	}
        	catch(IOException e) {
        		e.printStackTrace();
        	}
        }	
        responseObserver.onCompleted();
        
	}
	private boolean isDuplicate(String id, LinkedList<String> data) {
	    for (String d : data) {
	        String[] values = d.split(",");
	        if (values.length > 0 && values[0].equals(id)) {
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