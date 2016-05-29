package networking;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import JP2PChat.MainWindow;

/**
 * Class derived from Thread
 * Each instance of class runs in separate thread
 * MessageSender is used to send messages using peer-to-peer
 * achieved by socket - serverSocket connection
 */
public class MessageSender extends Thread {
	protected String message;
	protected String hostname;
	protected int port;
	protected MainWindow gui;

	
	/**
	 * Initializes MessageSender for basic message transmission
	 * @param message - String containing message to be transmitted
	 * @param hostname - Name of the host
	 * @param port - Port to send the message through
	 * @param gui - gui to write the message to
	 */
	public MessageSender (String message, String hostname, int port, MainWindow gui) {
		this.message = message;
		this.hostname = hostname;
		this.port = port;
		this.gui = gui;
	}
	
	/**
	 * Overridden constructor used by PingSender child class
	 * @param hostname - host to send messages to
	 * @param gui - gui to write messages to
	 * @param port - port to send to
	 */
	public MessageSender (String hostname, MainWindow gui, Integer port) {
		this.hostname = hostname;
		this.gui = gui;
		this.message = "ping";
		this.port = port;
	}
	
	/**
	 * Method overridden from Thread
	 * Sends user message to specified port
	 */
	public void run () {
		try {
			Socket s = new Socket(hostname, port);
			s.getOutputStream().write(message.getBytes());
			s.close();	
		}
		catch (IOException e) {
			gui.write(e.toString(), Color.RED);
			
		}
	}
}
