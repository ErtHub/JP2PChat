package networking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageSender extends Thread {
	private String message;
	private String hostname;
	private int port;
	
	public MessageSender () {}
	
	public MessageSender (String message, String hostname, int port) {
		this.message = message;
		this.hostname = hostname;
		this.port = port;
	}
	
	public void run () {
		try {
			Socket s = new Socket(hostname, port);
			s.getOutputStream().write(message.getBytes());
			s.close();
			
		}
		catch (IOException e) {
			Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
