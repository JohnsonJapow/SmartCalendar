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
    
    
	public static void main(String[] args) throws IOException, InterruptedException{
			
			
			ScheduleOptimizerServer ScheduleOptimizerserver=new ScheduleOptimizerServer();
			Properties prop=ScheduleOptimizerserver.getProperties();
			ScheduleOptimizerserver.registerService(prop);
			
			
			
			int port=Integer.valueOf( prop.getProperty("service2_port") );
			Server server;
		
			server=ServerBuilder.forPort(port).addService(ScheduleOptimizerserver).build().start();
			logger.info("Server started, listening on " + port);
			server.awaitTermination();
	
			
	}
	
	private Properties getProperties() {
		Properties prop=null;
		
		try(InputStream input= new FileInputStream("src/main/resources/SpendingTracker.properties")) {
			prop=new Properties();
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
		// TODO Auto-generated method stub
		
		System.out.println("receiving new event request");
		try {
			BufferedReader reader=new BufferedReader(new FileReader("data.txt"));
			BufferedReader reader2=new BufferedReader(new FileReader("data.txt"));
			String firstBalance=reader.readLine().trim();
			float oriB=Float.parseFloat(firstBalance);
			Thread.sleep(request.getEndDate());
			String endBalance=reader2.readLine().trim();
			reader.close();
			float endB=Float.parseFloat(endBalance);
			if(request.getIdealBalance()<=(endB-oriB)){
				responseObserver.onNext(GoalResponse.newBuilder().setSuccess(true).setMessage("Good job!! YOu make it!!").build());
			}
			else {
				responseObserver.onNext(GoalResponse.newBuilder().setSuccess(false).setMessage("Allmost there, try it one more time").build());
			}
			
		} catch (InterruptedException|IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//responseObserver.onNext(GoalResponse.newBuilder().setSuccess(false).setMessage("Allmost there, try it one more time"+request.getEndDate()+","+request.getIdealBalance()).build());
		responseObserver.onCompleted();
	}


}
