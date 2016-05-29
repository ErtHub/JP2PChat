package networking;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import JP2PChat.MainWindow;

public class PingSender extends MessageSender {
	
	private long tStart = System.currentTimeMillis();
	public PingSender(String hostname, MainWindow gui, Integer port) {
		super(hostname, gui, port);
		
	}
	
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