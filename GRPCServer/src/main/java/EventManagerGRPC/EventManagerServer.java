package EventManagerGRPC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import EventManagerGRPC.EventManagerGrpc.EventManagerImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;



public class EventManagerServer extends EventManagerImplBase {
	private static final Logger logger = Logger.getLogger(EventManagerServer.class.getName());
    // the file to output record data
    private static String eventPath="event.txt";
	public static void main(String[] args){
			
		//create class instance	
		EventManagerServer eventserver=new EventManagerServer();
		//get the properties for this service
		Properties prop=eventserver.getProperties();
		//register the service with JmDNS 
		eventserver.registerService(prop);
			//invoke the port for this service from the properties file
			int port=Integer.valueOf( prop.getProperty("service3_port") );
			
			try {
				//create a server on the port defined in variable "port"
				//and attach a service "eventserver"
				Server server;
				server=ServerBuilder.forPort(port).addService(eventserver).build().start();
				 // Giving a logging information on the server console that server has started
				logger.info("Server started, listening on " + port);
				// Server will be running until externally terminated.
				server.awaitTermination();
			} catch (InterruptedException | IOException e) {	
				e.printStackTrace();
			}
			
	}
	private Properties getProperties() {
		Properties prop=null;
		
		try(InputStream input= new FileInputStream("src/main/resources/SpendingTracker.properties")) {
			prop=new Properties();
			//load a properties file
			prop.load(input);
			System.out.println("Event Manager properties...");
            System.out.println("\t service_type: " + prop.getProperty("service3_type"));
            System.out.println("\t service_name: " +prop.getProperty("service3_name"));
            System.out.println("\t service_description: " +prop.getProperty("service3_description"));
	        System.out.println("\t service_port: " +prop.getProperty("service3_port"));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}
	
	private void registerService(Properties prop) {
		try {
			 // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            
            String service_type = prop.getProperty("service3_type") ;
            String service_name = prop.getProperty("service3_name")  ;
           
            int service_port = Integer.valueOf( prop.getProperty("service3_port") );

            
            String service_description_properties = prop.getProperty("service3_description")  ;
            
            // Register a service
            ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description_properties);
            jmdns.registerService(serviceInfo);
            System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
            
            // Wait a bit
            Thread.sleep(1000);

            // Unregister all services
            //jmdns.unregisterAllServices();
		}
		catch (IOException e) {
            System.out.println(e.getMessage());
        } 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Here we merge the record into a certain format, which allow us to swap the modification easier
	private LinkedList<String> mergeEvent(LinkedList<String> splitList) {
        LinkedList <String>newE=new LinkedList<>();
        //merge the each four datas into a new data, and store the new data in the linkedlist
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
	//Here we read the file and split data by each ",", then store each pieces into linkedlist
	private LinkedList<String> readEventFromFile() {
	    LinkedList<String> values = new LinkedList<String>();
	    try {
	        if (Files.exists(Paths.get(eventPath))) {
	            BufferedReader reader = new BufferedReader(new FileReader(eventPath));
	            String line = reader.readLine();
	            //read the file until the next line is empty
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
	
	//add an event into the explored file, and check whether the new event ID
	// is not duplicated to the existing data in the file
	@Override
	public void addEvent(Event request, StreamObserver<EventResponse> responseObserver) {
		System.out.println("receiving new event request");
		// if the file is not existing , create a new one
		if(!Files.exists(Paths.get(eventPath))){
			try{
				Files.createFile(Paths.get(eventPath));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		//the linked list is invoke the readEventFromFile linkedlist
		LinkedList<String>ata=readEventFromFile();
		//the variable is invoke isDuplicate  boolean
        boolean isDuplicate = isDuplicate(request.getId(),ata);
        //if the request is duplicated then return fail message
        if (isDuplicate) {
            String message = String.format("Event with ID %s already exists", request.getId());
            responseObserver.onNext(EventResponse.newBuilder().setSuccess(false).setMessage(message).build());
        }
        // if it is not duplicate with existing file then return successful message,  and store it at the end of the file 
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
	//check a linkedlist data whether the request ID is not match with the content
	private boolean isDuplicate(String id, LinkedList<String> data) {
	    for (String d : data) {
	        String[] values = d.split(",");
	        if (values.length > 0 && values[0].equals(id)) {
	            return true;
	        }
	    }
	    return false;
	}
	//Here we send a new record that we want to replace the relevant record by ID 
	@Override
	public StreamObserver<EventModificationRequest> modifyEvent(StreamObserver<EventResponse> responseObserver) {
		   
		return new StreamObserver<EventModificationRequest>() {
			//the linked list is invoke the readEventFromFile linkedlist
	        LinkedList<String> events = readEventFromFile();
	        //the  linked list is to store the renew data
	        LinkedList<String> newE;
  
	        @Override
	        public void onNext(EventModificationRequest request) {
	
	            String eventId = request.getId();
	            boolean found = false;
	            //Here we iterate the events linkedlist and check whether the new record is duplicated with same ID
	            for (int i = 0; i < events.size(); i++) {
	                
	                if (events.get(i).equals(eventId)) {
	                	// once we found the record then merge the events into renew linkedlist
	                	newE=mergeEvent(events);
	                	//replace the old record to the new one
	                	newE.set(i/4, request.getId()+","+request.getName()+","+request.getDescription()+","+Long.toString(request.getDate()));
	                    found = true;
	                    break;
	                }
	            }
	            //if we find and renew the data,we write the data and renew the file
	            if (found) {
	                try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventPath))) {
	                    for (String event : newE) {
	                        writer.write(event+"\n");
	                    }
	                    //send the successful response
	                    String message = String.format("Event with ID %s has been modified successfully", eventId);
	                    responseObserver.onNext(EventResponse.newBuilder().setSuccess(true).setMessage(message).build());
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            } 
	            // if we not find the data, send the fail response
	            else {
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
	//Here we accept a start time and an end time and then list the data that are in the period
	@Override
	public void listEvents(DateRange request, StreamObserver<Event> responseObserver) {
		
		//the variables store the request start time and end time
	    long startTime = request.getStartDate();
	    long endTime = request.getEndDate();
	    
		//the linked list is invoke the readEventFromFile linkedlist
	    LinkedList <String>dat=new LinkedList<>();
	    dat=readEventFromFile();
	    
	    //the linked list to store the records in certain period
	    LinkedList <String> dd=new LinkedList<>();
	    
	    //Here we iterate the data  
	    for(int i=3;i<dat.size();i+=4) {
	    	// if the data is in the period then store it into dd linkedlist
	    	if(startTime<=Long.parseLong(dat.get(i))&&endTime>=Long.parseLong(dat.get(i))) {
	    		dd.add(dat.get(i-3)+","+dat.get(i-2)+","+dat.get(i-1)+","+dat.get(i));
	    	    responseObserver.onNext(Event.newBuilder().setId(dat.get(i-3)).setName(dat.get(i-2)).setDescription(dat.get(i-1)).setDate(Long.parseLong(dat.get(i))).build());

	    	}
	    }
	    //if the datas are not matched, send fail response
	    if(dd.isEmpty()) {
	    	 responseObserver.onNext(Event.newBuilder().setId("not found").setName("not found").setDescription("not found").build());
	    }
	    responseObserver.onCompleted();
	}
	
}
