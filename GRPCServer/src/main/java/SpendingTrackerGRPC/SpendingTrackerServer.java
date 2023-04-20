package SpendingTrackerGRPC;

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
import java.util.Properties;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import SpendingTrackerGRPC.SpendingTrackerGrpc.SpendingTrackerImplBase;
//import SpendingTracker_GRPC.SpendingTrackerServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class SpendingTrackerServer extends SpendingTrackerImplBase {
	// First we create a logger to show server side logs in the console. logger instance will be used to log different events at the server console.
	private static final Logger logger = Logger.getLogger(SpendingTrackerServer.class.getName());
	private static float currentBalance;
	private static String dataFilePath = "data.txt";
	public static void main(String[] args){
		
		
		SpendingTrackerServer spendingserver=new SpendingTrackerServer();
		
		Properties prop=spendingserver.getProperties();
		spendingserver.registerService(prop);
		
		int port=Integer.valueOf( prop.getProperty("service_port") );// #.50051;
		
		try {
			readCurrentBalanceFromFile();
			Server server=ServerBuilder.forPort(port).addService(spendingserver).build().start();
			logger.info("Server started, listening on " + port);
			server.awaitTermination();
		} catch (InterruptedException | IOException e) {	
			e.printStackTrace();
		}
	}
	
	private Properties getProperties() {
		
		Properties prop=null;
		try (InputStream input= new FileInputStream("src/main/resources/SpendingTracker.properties")){
			
			prop=new Properties();
			
			prop.load(input);
			
			System.out.println("Spending Tracker properties...");
            System.out.println("\t service_type: " + prop.getProperty("service_type"));
            System.out.println("\t service_name: " +prop.getProperty("service_name"));
            System.out.println("\t service_description: " +prop.getProperty("service_description"));
	        System.out.println("\t service_port: " +prop.getProperty("service_port"));
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
            
            String service_type = prop.getProperty("service_type") ;//"_http._tcp.local.";
            String service_name = prop.getProperty("service_name")  ;// "example";
           // int service_port = 1234;
            int service_port = Integer.valueOf( prop.getProperty("service_port") );// #.50051;

            
            String service_description_properties = prop.getProperty("service_description")  ;//"path=index.html";
            
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
	public StreamObserver<TransactionRequest> recordTransaction(final StreamObserver<TransactionResponse> responseObserver) {
		return new StreamObserver<TransactionRequest>(){
			//balance is daily balance
			//currentBalance is the amount of balance
			
			@Override
			public void onNext(TransactionRequest request) {
				
				
				String income = Float.toString(request.getIncome());
				String spending = Float.toString(request.getSpending());	
				
				
				
			    if (!income.matches("[-+]?[0-9]*\\.?[0-9]+") || !spending.matches("[-+]?[0-9]*\\.?[0-9]+")||income.isEmpty()||spending.isEmpty()|| income.endsWith(".") || spending.endsWith(".")) {
			        // Handle invalid input
			    	responseObserver.onNext(TransactionResponse.newBuilder().setMessage("Invalid value").setBalance(0).build());
			    	responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid input: income and spending must only contain digits.").asRuntimeException());
			        return;
			    }
			    
			    float incomeFloat = Float.parseFloat(income);
			    float spendingFloat = Float.parseFloat(spending);
			    // Check that income and spending are not negative
			    if (incomeFloat < 0 || spendingFloat < 0) {
			        // Handle invalid input
			    	responseObserver.onNext(TransactionResponse.newBuilder().setMessage("value must be non-negative").setBalance(0).build());
			        responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid input: income and spending must be non-negative.").asRuntimeException());
			        return;
			    }
			    
			    
				// TODO Auto-generated method stub
				float balance=request.getIncome()-request.getSpending();
				currentBalance+=balance;
				
                // Write the updated balance to a file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFilePath))) {
                    writer.write(Float.toString(currentBalance));
                } catch (IOException e) {
                    e.printStackTrace();
                }
				
				TransactionResponse response = TransactionResponse.newBuilder().setMessage("The balance of this transaction : "+balance).setBalance(currentBalance).build();
				
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
