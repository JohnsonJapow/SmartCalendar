package MainGUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import EventManagerGRPC.DateRange;
import EventManagerGRPC.Event;
import EventManagerGRPC.EventManagerGrpc;
import EventManagerGRPC.EventModificationRequest;
import EventManagerGRPC.EventResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class EventGUIApp {
	private static EventManagerGrpc.EventManagerBlockingStub emblockingStub;
	private static EventManagerGrpc.EventManagerStub emasyncStub;
	private ServiceInfo smartInfo;
	private JFrame frame;
	private JTextField textNumber1;
	private JTextField textNumber2;
	private JTextField textNumber3;
	private JTextField textNumber4;
	private JTextField textNumber5;
	private JTextField textNumber6;
	private JTextArea textResponse ;
	private JTextArea textResponse2 ;
	private JTextArea textResponse3 ;
	private static final String main3_service_type="_main3._tcp.local.";
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					EventGUIApp window=new EventGUIApp();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	public EventGUIApp() {
		discoverServices(main3_service_type);
		
		String host=smartInfo.getHostAddresses()[0];
		int port=smartInfo.getPort();
		
		ManagedChannel channel3=ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		emblockingStub=EventManagerGrpc.newBlockingStub(channel3);
		emasyncStub=EventManagerGrpc.newStub(channel3);
		
		initialize();
	}

private void discoverServices(String service_type) {
		
		try {
			JmDNS jmdns =JmDNS.create(InetAddress.getLocalHost());
			
			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Smart Service added:  "+event.getInfo());
					
				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Smart Service removed:  "+event.getInfo());					
				}

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Smart Service resolved:  "+event.getInfo());
					
					ServiceInfo serviceinfo=event.getInfo();
					String serviceType=event.getType();

                    smartInfo = serviceinfo;

					int port = serviceinfo.getPort();
					
					System.out.println("resolving "+ service_type +" with properties ...");
					System.out.println("\t port: "+ port);
					System.out.println("\t type: "+ event.getType());
					System.out.println("\t name: "+ event.getName());
					System.out.println("\t description/properties: "+ smartInfo.getNiceTextString());
					System.out.println("\t host: "+ smartInfo.getHostAddresses()[0]);
				}
				
			});
			
			Thread.sleep(2000);
			
			jmdns.close();
		}
		catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Client - Service Controller");
		frame.setBounds(100, 100, 550, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout b1=new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(b1);
		
		JPanel panel_service_1 =new JPanel();
		frame.getContentPane().add(panel_service_1);
		panel_service_1.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		JLabel lbNewLabel_1=new JLabel("Event ID");
		panel_service_1.add(lbNewLabel_1);
		
		textNumber1=new JTextField();
		panel_service_1.add(textNumber1);
		textNumber1.setColumns(10);
		
		JPanel panel_service_2 =new JPanel();
		frame.getContentPane().add(panel_service_2);
		panel_service_2.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		JLabel lbNewLabel_2=new JLabel("Event Name");
		panel_service_2.add(lbNewLabel_2);
		
		textNumber2=new JTextField();
		panel_service_2.add(textNumber2);
		textNumber2.setColumns(10);
		
		JPanel panel_service_3 =new JPanel();
		frame.getContentPane().add(panel_service_3);
		panel_service_3.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		JLabel lbNewLabel_3=new JLabel("Event Descrption");
		panel_service_3.add(lbNewLabel_3);
		
		textNumber3=new JTextField();
		panel_service_3.add(textNumber3);
		textNumber3.setColumns(10);
		
		JPanel panel_service_4 =new JPanel();
		frame.getContentPane().add(panel_service_4);
		panel_service_4.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		JLabel lbNewLabel_4=new JLabel("Event Date");
		panel_service_4.add(lbNewLabel_4);
		
		textNumber4=new JTextField();
		panel_service_4.add(textNumber4);
		textNumber4.setColumns(10);
		
		JPanel panel_service_5 =new JPanel();
		frame.getContentPane().add(panel_service_5);
		panel_service_5.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		JButton btnAddEvent=new JButton("Add a Event");
		btnAddEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Event request=Event.newBuilder().setId(textNumber1.getText()).setName(textNumber2.getText()).setDescription(textNumber3.getText()).setDate(Long.parseLong(textNumber4.getText())).build();
				EventResponse response=emblockingStub.addEvent(request);
				textResponse.append(response.getSuccess()+","+response.getMessage());		
			}

		});
		panel_service_5.add(btnAddEvent);
		textResponse = new JTextArea(3, 20);
		textResponse .setLineWrap(true);
		textResponse.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textResponse);
		panel_service_5.add(scrollPane);
		
		
		
		JPanel panel_service_6 =new JPanel();
		frame.getContentPane().add(panel_service_6);
		panel_service_6.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		JButton btnModifyEvent=new JButton("Modify the Event by ID");
		btnModifyEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StreamObserver<EventResponse> responseObserver = new StreamObserver<EventResponse>() {
					@Override
						public void onNext(EventResponse value) {
							// TODO Auto-generated method stub
						textResponse2.append("receiving message: " + value.getSuccess()+value.getMessage());
						}
						@Override
						public void onError(Throwable t) {
							// TODO Auto-generated method stub
						}
						@Override
						public void onCompleted() {
						// TODO Auto-generated method stub
							textResponse2.append("completed ");
						}
					};
					StreamObserver<EventModificationRequest> requestObserver = emasyncStub.modifyEvent(responseObserver);
					try {
						requestObserver.onNext(EventModificationRequest.newBuilder().setId(textNumber1.getText()).setName(textNumber2.getText()).setDescription(textNumber3.getText()).setDate(Long.parseLong(textNumber4.getText())).build());
						textResponse2.append("SENDING EMSSAGES");
						// Mark the end of requests
						requestObserver.onCompleted();
						// Sleep for a bit before sending the next one.
						Thread.sleep(new Random().nextInt(1000) + 500);
					}
					catch (RuntimeException d) {
						d.printStackTrace();
					} 
					catch (InterruptedException d) { 
						d.printStackTrace();
					}
				
			}
			
		});
		panel_service_6.add(btnModifyEvent);
		textResponse2 = new JTextArea(3, 20);
		textResponse2 .setLineWrap(true);
		textResponse2.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane(textResponse2);
		panel_service_6.add(scrollPane2);
		
		JPanel panel_service_7 =new JPanel();
		frame.getContentPane().add(panel_service_7);
		panel_service_7.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		JLabel lbNewLabel_7=new JLabel("Start Date");
		panel_service_7.add(lbNewLabel_7);
		
		textNumber5=new JTextField();
		panel_service_7.add(textNumber5);
		textNumber5.setColumns(10);
		
		JPanel panel_service_8 =new JPanel();
		frame.getContentPane().add(panel_service_8);
		panel_service_8.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		JLabel lbNewLabel_8=new JLabel("End Date");
		panel_service_8.add(lbNewLabel_8);
		
		textNumber6=new JTextField();
		panel_service_8.add(textNumber6);
		textNumber6.setColumns(10);
		
		JPanel panel_service_9 =new JPanel();
		frame.getContentPane().add(panel_service_9);
		panel_service_9.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		JButton btnListEvent=new JButton("List the Event in a period");
		btnListEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DateRange request = DateRange.newBuilder().setStartDate(Long.parseLong(textNumber5.getText())).setEndDate(Long.parseLong(textNumber6.getText())).build();
				StreamObserver<Event> responseObserver=new StreamObserver<Event>() {
					int count=0;
					@Override
					public void onNext(Event value) {
						textResponse3.append("receiving messages " + value);
						count += 1;
					}
					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}
					@Override
					public void onCompleted() {
							textResponse3.append("stream is completed ... received "+ count+ " messages");
					}
				};
				emasyncStub.listEvents(request, responseObserver);
				try {
					Thread.sleep(30000);
				} 
				catch (InterruptedException ed) {
				// TODO Auto-generated catch block
					ed.printStackTrace();
				}
				
			}
			
		});
		
		panel_service_9.add(btnListEvent);
		textResponse3 = new JTextArea(5, 20);
		textResponse3 .setLineWrap(true);
		textResponse3.setWrapStyleWord(true);
		
		JScrollPane scrollPane3 = new JScrollPane(textResponse3);
		panel_service_9.add(scrollPane3);
		
	}
}
