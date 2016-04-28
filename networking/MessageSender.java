package networking;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MessageSender extends Thread {
	private String message;
	private String hostname;
	private int port;
	private JPanel gui;
	
	public MessageSender () {}
	
	public MessageSender (String message, String hostname, int port, JPanel gui) {
		this.message = message;
		this.hostname = hostname;
		this.port = port;
		this.gui = gui;
	}
	
	public void run () {
		try {
			Socket s = new Socket(hostname, port);
			s.getOutputStream().write(message.getBytes());
			s.close();	
		}
		catch (IOException e) {
			//Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, e);
			JOptionPane.showMessageDialog(gui, e);
		}
	}
}
