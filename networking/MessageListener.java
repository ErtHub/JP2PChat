package networking;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import JP2PChat.WritableGUI;

/**
* Class derived from Thread
* Each instance of class runs in separate thread
* MessageListener is used to listen to messages using peer-to-peer
* achieved by socket - serverSocket connection
*/
public class MessageListener extends Thread {
	protected int port;
	protected ServerSocket server;
	protected WritableGUI gui;

	/**
	 * class Constructor
	 * creating object with ServerSocket on specified port
	 * @param gui - gui to write to received message
	 * @param port - port to listen on
	 */
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
	
	/**
	 * Method overridden from Thread
	 * Listens for user messages on specified port
	 */
	public void run () {
		Socket clientSocket;
		try {
			while((clientSocket = server.accept()) != null) {		
				InputStream is = clientSocket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = br.readLine();
				
					if(line != null ) {
						gui.write(line,Color.BLACK);
					}
			clientSocket.close();
			}			
		}
		
		catch (IOException e) {
			gui.write(e.toString(), Color.RED);
		}
		catch(NullPointerException e) {
			gui.write("Cannot use ports already in use!", Color.RED);
		}
	}
}
