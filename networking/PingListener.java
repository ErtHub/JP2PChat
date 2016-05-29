package networking;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import JP2PChat.MainWindow;

/**
 * 
 * MessageListener class specialization
 * Each instance of class runs in separate thread
 * Class used to check for ping messages with time interval for connection status
 */
public class PingListener extends MessageListener {
	
	private long tStart = System.currentTimeMillis();
	
	/**
	 * Initializes PingListener for listening on specified port for "ping" messages
	 * @param gui - GUI that hold timeout indicator
	 * @param pingPort - Port to send "ping" messages on
	 */
	public PingListener(MainWindow gui, Integer pingPort) {
		super(gui, pingPort);
	}
	
	/**
	 * Method overridden from MessageListener (class Thread)
	 * Collect "ping" message at specified port
	 * at specified time intervals
	 */
	public void run() {
		Socket clientSocket;	
		try {
			while((clientSocket = server.accept()) != null) {		
				InputStream is = clientSocket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = br.readLine();
				
				long tEnd = System.currentTimeMillis();
				long tDelta = tEnd - tStart;
				double elapsedSeconds = tDelta;
				
				if(elapsedSeconds > 300) {
					if (line != null) {
						gui.setIndicator(Color.green);
					}
					else {
						gui.setIndicator(Color.red);
					}
				}
					
			br.close();
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
