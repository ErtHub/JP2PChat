package JP2PChat;

import javax.swing.*;

public class MainWindow extends JFrame {
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
	private static final int portAddressTextFieldXPos = msgReadTxtFieldXPos + ipAddressTextFieldXSize + 10;
	private static final int portAddressTextFieldYPos = 10;
	private static final int portAddressTextFieldXSize = 150;
	private static final int portAddressTextFieldYSize = ipAddressTextFieldYSize; // TO SET
	
	private static final int connectButtonXPos = portAddressTextFieldXPos + portAddressTextFieldXSize + 10;
	private static final int connectButtonYPos = 10;
	private static final int connectButtonXSize = 100;
	private static final int connectButtonYSize = ipAddressTextFieldYSize; // TO SET
	
	private static final int disconnectButtonXPos = connectButtonXPos + connectButtonXSize + 10;
	private static final int disconnectButtonYPos = 10;
	private static final int disconnectButtonXSize = 120;
	private static final int disconnectButtonYSize = ipAddressTextFieldYSize; // TO SET
	
	private static final int sendMessageButtonXPos = msgWriteTxtFieldXPos + msgWriteTxtFieldXSize + 10;
	private static final int sendMessageButtonYPos = msgWriteTxtFieldYPos;
	private static final int sendMessageButtonXSize = 120;
	private static final int sendMessageButtonYSize = 40; // TO SET
	

	private JPanel mainPanel;
	private JTextField messageReadTextField;
	private JTextField messageWriteTextField;
	private JTextField ipAddressTextField;
	private JTextField portAddressTextField;
	private JButton connectButton;
	private JButton disconnectButton;
	private JButton sendMessageButton;
	
	public MainWindow() {
		setSize(width, height);
		setLayout(null); //TO CHECK LATER
		
		mainPanel = new JPanel();
		add(mainPanel);
		
		messageReadTextField = new JTextField();
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
		
		portAddressTextField = new JTextField();
		portAddressTextField.setBounds(portAddressTextFieldXPos, portAddressTextFieldYPos,
									   portAddressTextFieldXSize, portAddressTextFieldYSize);
		add(portAddressTextField);
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(connectButtonXPos, connectButtonYPos, 
								connectButtonXSize, connectButtonYSize);
		add(connectButton);
		
		disconnectButton = new JButton("disConnect");
		disconnectButton.setBounds(disconnectButtonXPos, disconnectButtonYPos, 
								   disconnectButtonXSize, disconnectButtonYSize);
		add(disconnectButton);
		
		sendMessageButton = new JButton("Send");
		sendMessageButton.setBounds(sendMessageButtonXPos, sendMessageButtonYPos, 
									sendMessageButtonXSize, sendMessageButtonYSize);
		add(sendMessageButton);
		
		setVisible(true);
	}
}
