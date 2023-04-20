package MainGUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import EventManagerGRPC.EventManagerGrpc;
import ScheduleOptimizerGRPC.Goal;
import ScheduleOptimizerGRPC.GoalResponse;
import ScheduleOptimizerGRPC.ScheduleOptimizerGrpc;
import SpendingTrackerGRPC.SpendingTrackerGrpc;
import SpendingTrackerGRPC.TransactionRequest;
import SpendingTrackerGRPC.TransactionResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class MainGUIApp{
	private static ScheduleOptimizerGrpc.ScheduleOptimizerBlockingStub soblockingStub;

	private static SpendingTrackerGrpc.SpendingTrackerStub stasyncStub;
	
	private ServiceInfo smartInfo;
	private ServiceInfo smartInfo2;
	private static final String main_service_type="_main._tcp.local.";
	private static final String main2_service_type="_main2._tcp.local.";
	private JFrame frame;
	private JTextField textNumber1;
	private JTextField textNumber2;
	private JTextField textNumber3;
	private JTextField textNumber4;
	private JTextArea textResponse ;
	private JTextArea textResponse2 ;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					MainGUIApp window=new MainGUIApp();
					window.frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MainGUIApp() {
		discoverServices(main_service_type);
		discoverServices(main2_service_type);
		
		String host=smartInfo.getHostAddresses()[0];
		int port=smartInfo.getPort();
		String host2=smartInfo2.getHostAddresses()[0];
		int port2=smartInfo2.getPort();

		
		ManagedChannel channel=ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		stasyncStub= SpendingTrackerGrpc.newStub(channel);
		ManagedChannel channe2=ManagedChannelBuilder.forAddress(host2, port2).usePlaintext().build();
		soblockingStub = ScheduleOptimizerGrpc.newBlockingStub(channe2);

		
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
					
                    if (serviceType.equals(main_service_type)) {
                    	smartInfo = serviceinfo;
                    } else if (serviceType.equals(main2_service_type)) {
                    	smartInfo2 = serviceinfo;
                    }
					
					
					
					
					int port = serviceinfo.getPort();
					
					System.out.println("resolving "+ service_type +" with properties ...");
					System.out.println("\t port: "+ port);
					System.out.println("\t type: "+ event.getType());
					System.out.println("\t name: "+ event.getName());
					System.out.println("\t description/properties: "+ serviceinfo.getNiceTextString());
					System.out.println("\t host: "+ serviceinfo.getHostAddresses()[0]);
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
		frame= new JFrame();
		frame.setTitle("Client - Service Controller");
		
		frame.setBounds(100, 100, 700, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout b1=new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(b1);
		
		JPanel panel_service_1 =new JPanel();
		frame.getContentPane().add(panel_service_1);
		panel_service_1.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		
		JLabel lblNewLabel_1=new JLabel("Income");
		panel_service_1.add(lblNewLabel_1);
		
		textNumber1 = new JTextField();
		panel_service_1.add(textNumber1);
		textNumber1.setColumns(10);
		
		JLabel lblNewLabel_2=new JLabel("Expense");
		panel_service_1.add(lblNewLabel_2);
		
		textNumber2 = new JTextField();
		panel_service_1.add(textNumber2);
		textNumber2.setColumns(10);
		
		JButton btnTrack =new JButton("Record my transaction");
		btnTrack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				StreamObserver<TransactionResponse> stResponseObserver =new StreamObserver<TransactionResponse>() {
					
					int count=0;
					
					@Override
					public void onNext(TransactionResponse value) {
						textResponse.append("receiving the "+(count+1)+" response:\n"+value.getMessage()+"\n the account balance "+value.getBalance()+"\n");
						count +=1;
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
						
					}

					@Override
					public void onCompleted() {
						textResponse.append("stream is completed ... received "+ count+ " transaction request \n");
						
					}
					
				};
				StreamObserver<TransactionRequest> requestObserver=stasyncStub.recordTransaction(stResponseObserver);
				try {
					requestObserver.onNext(TransactionRequest.newBuilder().setIncome(Float.parseFloat(textNumber1.getText())).setSpending(Float.parseFloat(textNumber2.getText())).build());
			
					// Mark the end of requests
					requestObserver.onCompleted();

					
					// Sleep for a bit before sending the next one.
					Thread.sleep(new Random().nextInt(1000) + 500);
				} 
				catch (RuntimeException e1) {
					e1.printStackTrace();
				} 
				catch (InterruptedException e1) {			
					e1.printStackTrace();
				}
			};
			
		});
		

		
		JPanel panel_service_2 = new JPanel();
		frame.getContentPane().add(panel_service_2);
		panel_service_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_service_2.add(btnTrack);
		textResponse = new JTextArea(3, 20);
		textResponse .setLineWrap(true);
		textResponse.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textResponse);
		
		panel_service_2.add(scrollPane);
		
		JPanel panel_service_3 = new JPanel();
		frame.getContentPane().add(panel_service_3);
		panel_service_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbNewLabel_3=new JLabel("Set the time to save the target amount");
		panel_service_3.add(lbNewLabel_3);
		
		textNumber3=new JTextField();
		panel_service_3.add(textNumber3);
		textNumber3.setColumns(10);
		
		JLabel lbNewLabel_4=new JLabel("Set your saving target");
		panel_service_3.add(lbNewLabel_4);
		
		textNumber4=new JTextField();
		panel_service_3.add(lbNewLabel_4);
		
		textNumber4=new JTextField();
		panel_service_3.add(textNumber4);
		textNumber4.setColumns(10);
		
		JPanel panel_service_4 = new JPanel();
		frame.getContentPane().add(panel_service_4);
		panel_service_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSetChallenge=new JButton("Challenge Start !!");
		btnSetChallenge.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				Runnable setChallengeTask =new Runnable () {
				public void run() {
				
					// TODO Auto-generated method stub
					Goal request=Goal.newBuilder().setEndDate(Long.parseLong(textNumber3.getText())*1000).setIdealBalance(Float.parseFloat(textNumber4.getText())).build();
					GoalResponse scResponse=soblockingStub.setChallenge(request);
					
					textResponse2.append("receiving the response:"+scResponse.getSuccess()+"\n"+scResponse.getMessage()+"\n");
					}
				};

	            // Create a new ExecutorService with a single thread
	            ExecutorService executor = Executors.newSingleThreadExecutor();
	            // Submit the setChallengeTask to the executor
	            executor.submit(setChallengeTask);
	            // Shutdown the executor after the task is complete
	            executor.shutdown();

				}
			});
		panel_service_4.add(btnSetChallenge);
		
		textResponse2 = new JTextArea(3, 20);
		textResponse2 .setLineWrap(true);
		textResponse2.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane(textResponse2);
		panel_service_4.add(scrollPane2);
		
	}

}
