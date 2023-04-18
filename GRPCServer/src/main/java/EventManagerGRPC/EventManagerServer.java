package EventManagerGRPC;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Logger;

import EventManagerGRPC.EventManagerGrpc.EventManagerImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;



public class EventManagerServer extends EventManagerImplBase {
	private static final Logger logger = Logger.getLogger(EventManagerServer.class.getName());
    
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

	private LinkedList mergeEvent(LinkedList splitList) {
        LinkedList <String>newE=new LinkedList<>();
		
		for (int i = 0; i < splitList.size(); i += 4) {
			StringBuilder builder = new StringBuilder();
			builder.append(splitList.get(i)).append(",");
			builder.append(splitList.get(i+1)).append(",");
			builder.append(splitList.get(i+2)).append(",");
	        builder.append(splitList.get(i+3));
	        newE.add(builder.toString());
		}  
		return newE;
	}
	private LinkedList readEventFromFile() {
	    LinkedList values = new LinkedList<String>();
	    try {
	        if (Files.exists(Paths.get(eventPath))) {
	            BufferedReader reader = new BufferedReader(new FileReader(eventPath));
	            String line = reader.readLine();
	            while (line != null && !line.isEmpty()) {
	                String[] value = line.split(",");
	                for (String v : value) {
	                    values.add(v);
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
        		String line =request.getId()+","+request.getName()+","+request.getDescription()+","+Long.toString(request.getDate());
                writer.write(line+"\n");
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

	        LinkedList<String> events = readEventFromFile();
	        LinkedList<String> newE;
  
	        @Override
	        public void onNext(EventModificationRequest request) {
	
	            String eventId = request.getId();
	            boolean found = false;
	            for (int i = 0; i < events.size(); i++) {
	                
	                if (events.get(i).equals(eventId)) {
	                	newE=mergeEvent(events);
	                	newE.set(i/4, request.getId()+","+request.getName()+","+request.getDescription()+","+Long.toString(request.getDate()));
	                    found = true;
	                    break;
	                }
	            }
	            if (found) {
	                try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventPath))) {
	                    for (String event : newE) {
	                        writer.write(event+"\n");
	                    }
	                    
	                    String message = String.format("Event with ID %s has been modified successfully", eventId);
	                    responseObserver.onNext(EventResponse.newBuilder().setSuccess(true).setMessage(message).build());
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            } else {
	                String message = String.format("Event with ID %s not found", eventId);
	                responseObserver.onNext(EventResponse.newBuilder().setSuccess(false).setMessage(message).build());
	            }
	        }

		        @Override
		        public void onError(Throwable t) {
		            
		        	responseObserver.onError(t);
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
	    
	    LinkedList <String>dat=new LinkedList<>();
	    dat=readEventFromFile();
	    LinkedList <String> dd=new LinkedList<>();
	    for(int i=3;i<dat.size();i+=4) {
	    	
	    	if(startTime<=Long.parseLong(dat.get(i))&&endTime>=Long.parseLong(dat.get(i))) {
	    		dd.add(dat.get(i-3)+","+dat.get(i-2)+","+dat.get(i-1)+","+dat.get(i));
	    	    responseObserver.onNext(Event.newBuilder().setId(dat.get(i-3)).setName(dat.get(i-2)).setDescription(dat.get(i-1)).setDate(Long.parseLong(dat.get(i))).build());

	    	}
	    }
	    if(dd.isEmpty()) {
	    	 responseObserver.onNext(Event.newBuilder().setId("not found").setName("not found").setDescription("not found").build());
	    }
	    responseObserver.onCompleted();
	}
	
}
