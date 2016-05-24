package networking;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import JP2PChat.MainWindow;

public class PingSender extends MessageSender {
	
	
	public PingSender(String hostname, MainWindow gui) {
		super(hostname, gui);
	}
	
	public void run () {
			try {
				message = "ping";
				Socket s = new Socket(hostname, port);
				//message = 
				//s.getOutputStream().write(message.getBytes());
				s.close();	
			}
		
			catch (IOException e) {
			//Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, e);
			//JOptionPane.showMessageDialog(gui, e); <- okienko
				//gui.write(e.toString(), Color.RED);
				gui.setIndicator(Color.red);
			}
	}
}