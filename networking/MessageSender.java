package networking;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import JP2PChat.MainWindow;

public class MessageSender extends Thread {
	private String message;
	private String hostname;
	private int port;
	private MainWindow gui;
	
	public MessageSender () {}
	
	public MessageSender (String message, String hostname, int port, MainWindow gui) {
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
			//JOptionPane.showMessageDialog(gui, e); <- okienko
			gui.write(e.toString(), Color.RED);
			
		}
	}
}
