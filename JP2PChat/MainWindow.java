package JP2PChat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import networking.MessageListener;
import networking.MessageSender;

public class MainWindow extends JFrame implements WritableGUI{
	/*
	 * TODO : cosmetics in GUI
	 */
	private static final int width = 800;
	private static final int height = 600;
	
	private static final int msgReadTxtFieldXPos = 20;
	private static final int msgReadTxtFieldYPos = 50;
	private static final int msgReadTxtFieldXSize = 700;
	private static final int msgReadTxtFieldYSize = 300;
	
	private static final int msgWriteTxtFieldXPos = msgReadTxtFieldXPos;
	private static final int msgWriteTxtFieldYPos = msgReadTxtFieldYSize + msgReadTxtFieldYPos + 10;
	private static final int msgWriteTxtFieldXSize = msgReadTxtFieldXSize - 150;
	private static final int msgWriteTxtFieldYSize = 180; // TO SET

	private static final int ipAddressTextFieldXPos = msgReadTxtFieldXPos;
	private static final int ipAddressTextFieldYPos = 10;
	private static final int ipAddressTextFieldXSize = 300;
	private static final int ipAddressTextFieldYSize = 20; // TO SET
	//create variable for separator size to eliminate 10 in code
	private static final int listenportAddressTextFieldXPos = msgReadTxtFieldXPos + ipAddressTextFieldXSize + 10;
	private static final int listenportAddressTextFieldYPos = 10;
	private static final int listenportAddressTextFieldXSize = 150;
	private static final int listenportAddressTextFieldYSize = ipAddressTextFieldYSize; // TO SET
	
	private static final int sendPortAddressTextFieldXPos = listenportAddressTextFieldXPos + listenportAddressTextFieldXSize + 10;
	private static final int sendPortAddressTextFieldYPos = 10;
	private static final int sendPortAddressTextFieldXSize = 150;
	private static final int sendPortAddressTextFieldYSize = ipAddressTextFieldYSize; // TO SET
	
	private static final int connectButtonXPos = sendPortAddressTextFieldXPos + sendPortAddressTextFieldXSize + 10;
	private static final int connectButtonYPos = 10;
	private static final int connectButtonXSize = 100;
	private static final int connectButtonYSize = ipAddressTextFieldYSize; // TO SET
	
	//private static final int disconnectButtonXPos = connectButtonXPos + connectButtonXSize + 10;
	//private static final int disconnectButtonYPos = 10;
	//private static final int disconnectButtonXSize = 120;
	//private static final int disconnectButtonYSize = ipAddressTextFieldYSize; 
	// TO SET
	
	private static final int sendMessageButtonXPos = msgWriteTxtFieldXPos + msgWriteTxtFieldXSize + 10;
	private static final int sendMessageButtonYPos = msgWriteTxtFieldYPos;
	private static final int sendMessageButtonXSize = 120;
	private static final int sendMessageButtonYSize = 40; // TO SET
	
	private JPanel mainPanel;
	private JTextArea messageReadTextField; //append
	private JTextField messageWriteTextField;
	private JTextField ipAddressTextField;
	private JTextField listenPortAddressTextField;
	private JTextField sendPortAddressTextField;
	private JButton connectButton;
	//private JButton disconnectButton;
	private JButton sendMessageButton;
	
	private MessageListener listener;
	private MessageSender transmitter;
	
	public MainWindow() {
		setSize(width, height);
		setTitle("JP2PChat alpha");
		setLayout(null); //TO CHECK LATER
		
		//listener = new MessageListener(this);
		
		mainPanel = new JPanel();
		add(mainPanel);
		
		messageReadTextField = new JTextArea();
		messageReadTextField.setBounds(msgReadTxtFieldXPos, msgReadTxtFieldYPos,
				msgReadTxtFieldXSize, msgReadTxtFieldYSize);
		//TODO move writing cursor to bottom of text field
		add(messageReadTextField);
		
		messageWriteTextField = new JTextField();
		messageWriteTextField.setBounds(msgWriteTxtFieldXPos, msgWriteTxtFieldYPos,
				msgWriteTxtFieldXSize, msgWriteTxtFieldYSize);
		add(messageWriteTextField);
		
		ipAddressTextField = new JTextField();
		ipAddressTextField.setBounds(ipAddressTextFieldXPos, ipAddressTextFieldYPos,
									 ipAddressTextFieldXSize, ipAddressTextFieldYSize);
		add(ipAddressTextField);
		
		listenPortAddressTextField = new JTextField();
		listenPortAddressTextField.setBounds(listenportAddressTextFieldXPos, listenportAddressTextFieldYPos,
				listenportAddressTextFieldXSize, listenportAddressTextFieldYSize);
		add(listenPortAddressTextField);
		
		sendPortAddressTextField = new JTextField();
		sendPortAddressTextField.setBounds(sendPortAddressTextFieldXPos, sendPortAddressTextFieldYPos,
				sendPortAddressTextFieldXSize, sendPortAddressTextFieldYSize);
		add(sendPortAddressTextField);
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(connectButtonXPos, connectButtonYPos, 
								connectButtonXSize, connectButtonYSize);
		
		add(connectButton);
		
//		disconnectButton = new JButton("disConnect");
//		disconnectButton.setBounds(disconnectButtonXPos, disconnectButtonYPos, 
//								   disconnectButtonXSize, disconnectButtonYSize);
//		add(disconnectButton);
		
		sendMessageButton = new JButton("Send");
		sendMessageButton.setBounds(sendMessageButtonXPos, sendMessageButtonYPos, 
									sendMessageButtonXSize, sendMessageButtonYSize);
		
		
		
		
		add(sendMessageButton);
		

		connectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				listenButtonActionPerformed(e);
			}
		});
		
		sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				sendButtonActionPerformed(e);
			}
		});
		
		//setVisible(true);
	}
	
	public void write (String str) {
		messageReadTextField.append(str + System.lineSeparator());
	}
	
	private void listenButtonActionPerformed(java.awt.event.ActionEvent evt) {
		listener = new MessageListener(this, Integer.parseInt(listenPortAddressTextField.getText()));
		listener.start();
	}
	
	private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
		transmitter = new MessageSender(messageWriteTextField.getText(),
			    						  ipAddressTextField.getText(), Integer.parseInt(sendPortAddressTextField.getText()));
		transmitter.start();
	}
}
