package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import JP2PChat.WritableGUI;


public class MessageListener extends Thread{
	private int port = 7788;
	private ServerSocket server;
	private WritableGUI gui;
	
//	private void setServerSocket (int port) {
//		try {
//			server = new ServerSocket(port);
//		}
//		catch (IOException e){
//			Logger.getLogger(MessageListener.class.getName());
//		}
//	}
	
//	public MessageListener () {
//		setServerSocket (port);
//	}
	
	public MessageListener (WritableGUI gui, int port) {
		this.gui = gui;
		this.port = port;
		try {
			server = new ServerSocket(port);
		}
		catch (IOException e){
			Logger.getLogger(MessageListener.class.getName());
		}
	}
	
	public void run () {
		Socket clientSocket;
		try {
			while((clientSocket = server.accept()) != null) {
				InputStream is = clientSocket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = br.readLine();
				
				if(line != null) {
					gui.write(line);
				}
				
			}
			//if there is no clientSocket at all => nullptr exception
			clientSocket.close();
		}
		
		catch (IOException e) {
			Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
