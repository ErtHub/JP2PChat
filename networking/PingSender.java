package networking;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import JP2PChat.MainWindow;

/**
 * 
 * MessageSender class specialization
 * Each instance of class runs in separate thread
 * Class used to send ping messages with time interval for connection status
 */
public class PingSender extends MessageSender {
	
	private long tStart = System.currentTimeMillis();
	
	public PingSender(String hostname, MainWindow gui, Integer port) {
		super(hostname, gui, port);	
	}
	/**
	 * Method overridden from MessageSender (class Thread)
	 * Sends "ping" message to specified port
	 * at specified time intervals
	 */
	public void run () {
		while(true) {	
			try {
				
				long tEnd = System.currentTimeMillis();
				long tDelta = tEnd - tStart;
				double elapsedSeconds = tDelta;
				
				if(elapsedSeconds > 300) {
					message = "ping";
					Socket s = new Socket(hostname, port);
					s.getOutputStream().write(message.getBytes());
					s.close();	
					tStart = System.currentTimeMillis();
				}
			}
		
			catch (IOException e) {
				gui.setIndicator(Color.red);
			}
		}
	}
}