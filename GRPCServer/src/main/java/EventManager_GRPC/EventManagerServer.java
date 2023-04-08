package EventManager_GRPC;

import java.io.IOException;
import java.util.logging.Logger;

import EventManager_GRPC.EventManagerGrpc.EventManagerImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;



public class EventManagerServer extends EventManagerImplBase {
	private static final Logger logger = Logger.getLogger(EventManagerServer.class.getName());

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
		String eventID=request.getId();
		
	}

	@Override
	public StreamObserver<EventModificationRequest> modifyEvent(StreamObserver<EventResponse> responseObserver) {
		// TODO Auto-generated method stub
		return super.modifyEvent(responseObserver);
	}

	@Override
	public void listEvents(DateRange request, StreamObserver<Event> responseObserver) {
		// TODO Auto-generated method stub
		super.listEvents(request, responseObserver);
	}
	
}


