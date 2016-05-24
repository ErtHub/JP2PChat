package networking;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import JP2PChat.MainWindow;

public class PingListener extends MessageListener {
	
	private static int pingPort = 7788;
	//private Timer timer;
	private int timer = 0;
	
	public PingListener(MainWindow gui) {
		super(gui, pingPort);
	}
	
	public void run() {
		Socket clientSocket;	
		try {
			while((clientSocket = server.accept()) != null) {		
				/*InputStream is = clientSocket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = br.readLine();
				*/
				/*if(line != null ) {
					gui.write(line,Color.BLACK);
				}*/
				++timer;
				if(timer > 300)
				{
					gui.setIndicator(Color.red);
					//break;
				}
				else 
				{
					gui.setIndicator(Color.green);
				}
			}
			//if there is no clientSocket at all => nullptr exception
			clientSocket.close();
		}
		catch (IOException e) {
			//Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, e);
			gui.write(e.toString(), Color.RED);
		}
		catch(NullPointerException e) {
			gui.write("Cannot use ports already in use!", Color.RED);
		}
	}
}
