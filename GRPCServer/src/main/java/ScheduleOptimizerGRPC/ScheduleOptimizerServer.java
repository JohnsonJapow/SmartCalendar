package ScheduleOptimizerGRPC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import EventManagerGRPC.EventManagerServer;
import ScheduleOptimizerGRPC.ScheduleOptimizerGrpc.ScheduleOptimizerImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ScheduleOptimizerServer extends ScheduleOptimizerImplBase{

private static final Logger logger = Logger.getLogger(EventManagerServer.class.getName());
    
    
	public static void main(String[] args){
			
			//create the class instance
			ScheduleOptimizerServer ScheduleOptimizerserver=new ScheduleOptimizerServer();
			//get the properties for this service
			Properties prop=ScheduleOptimizerserver.getProperties();
			//register the service with JmDNS 
			ScheduleOptimizerserver.registerService(prop);
			//invoke the port for this service from the properties file
			int port=Integer.valueOf( prop.getProperty("service2_port") );

			try {
				//create a server on the port defined in variable "port"
				//and attach a service "ScheduleOptimizerserver"
				Server server;
				server=ServerBuilder.forPort(port).addService(ScheduleOptimizerserver).build().start();
				 // Giving a logging information on the server console that server has started
				logger.info("Server started, listening on " + port);
				// Server will be running until externally terminated.				
				server.awaitTermination();
			}
			catch (InterruptedException | IOException e) {	
				e.printStackTrace();
			}
			
	}
	
	private Properties getProperties() {
		Properties prop=null;
		
		try(InputStream input= new FileInputStream("src/main/resources/SpendingTracker.properties")) {
			prop=new Properties();
			//load a properties file
			prop.load(input);
			System.out.println("Schedule Optimizer properties...");
            System.out.println("\t service_type: " + prop.getProperty("service2_type"));
            System.out.println("\t service_name: " +prop.getProperty("service2_name"));
            System.out.println("\t service_description: " +prop.getProperty("service2_description"));
	        System.out.println("\t service_port: " +prop.getProperty("service2_port"));
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
            
            String service_type = prop.getProperty("service2_type") ;
            String service_name = prop.getProperty("service2_name")  ;
           
            int service_port = Integer.valueOf( prop.getProperty("service2_port") );

            
            String service_description_properties = prop.getProperty("service2_description")  ;
            
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
	
	@Override
	public void setChallenge(Goal request, StreamObserver<GoalResponse> responseObserver) {
		
		System.out.println("receiving new event request");
		try {
			//read the file from spending tracker exported, this file have the current balance
			//reader 1 will read the file when the method implemented
			BufferedReader reader=new BufferedReader(new FileReader("data.txt"));
			//reader 2 will read the file after the timer which set by user
			BufferedReader reader2=new BufferedReader(new FileReader("data.txt"));
			//read the content from the file and eliminate space
			String firstBalance=reader.readLine().trim();
			//the float store the original balance from file 
			float oriB=Float.parseFloat(firstBalance);
			//wait the certain time that set by user
			Thread.sleep(request.getEndDate());
			//read the content from the file and eliminate space
			String endBalance=reader2.readLine().trim();
			reader.close();
			//the float store the final balance from file
			float endB=Float.parseFloat(endBalance);
			
			//compare the result whether achieve the saving target
			if(request.getIdealBalance()<=(endB-oriB)){
				responseObserver.onNext(GoalResponse.newBuilder().setSuccess(true).setMessage(" : ) Good job !! You make it  !!").build());
			}
			else {
				responseObserver.onNext(GoalResponse.newBuilder().setSuccess(false).setMessage(" : ( Allmost there, try it one more time").build());
			}
			
		} catch (InterruptedException|IOException|NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//responseObserver.onNext(GoalResponse.newBuilder().setSuccess(false).setMessage("Allmost there, try it one more time"+request.getEndDate()+","+request.getIdealBalance()).build());
		responseObserver.onCompleted();
	}


}
