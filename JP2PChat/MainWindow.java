package JP2PChat;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import networking.MessageListener;
import networking.MessageSender;

public class MainWindow extends JFrame implements WritableGUI{
	/*
	 * TODO : cosmetics in GUI
	 */
	
	private static final int width = 683;
	private static final int height = 550;
	
	private static final int readBoxXPos = 20;
	private static final int readBoxYPos = 50;
	private static final int readBoxXSize = 600;
	private static final int readBoxYSize = 400;
	
	private static final int writeBoxXPos = readBoxXPos;
	private static final int writeBoxYPos = readBoxYSize + readBoxYPos + 10;
	private static final int writeBoxXSize = readBoxXSize - 130;
	private static final int writeBoxYSize = 40; // TO SET

	private static final int ipBarXPos = readBoxXPos;
	private static final int ipBarYPos = 10;
	private static final int ipBarXSize = 150;
	private static final int ipBarYSize = 20; // TO SET
	//create variable for separator size to eliminate 10 in code
	private static final int listenPortBarXPos = readBoxXPos + ipBarXSize + 10;
	private static final int listenPortBarYPos = 10;
	private static final int listenPortBarXSize = 75;
	private static final int listenPortBarYSize = ipBarYSize; // TO SET
	
	private static final int sendPortBarXPos = listenPortBarXPos + listenPortBarXSize + 10;
	private static final int sendPortBarYPos = 10;
	private static final int sendPortBarXSize = 75;
	private static final int sendPortBarYSize = ipBarYSize; // TO SET
	
	private static final int nickBarXPos = sendPortBarXPos + sendPortBarXSize + 10;
	private static final int nickBarYPos = 10;
	private static final int nickBarXSize = 125;
	private static final int nickBarYSize = ipBarYSize;
	
	private static final int connectBtnXPos = readBoxXPos + readBoxXSize - 100;
	private static final int connectBtnYPos = 10;
	private static final int connectBtnXSize = 100;
	private static final int connectBtnYSize = 35; // TO SET
	
	//private static final int disconnectBtnXPos = connectBtnXPos + connectBtnXSize + 10;
	//private static final int disconnectBtnYPos = 10;
	//private static final int disconnectBtnXSize = 120;
	//private static final int disconnectBtnYSize = ipBarYSize; 
	// TO SET
	
	private static final int sendBtnXPos = writeBoxXPos + writeBoxXSize + 10;
	private static final int sendBtnYPos = writeBoxYPos;
	private static final int sendBtnXSize = 120;
	private final int sendBtnYSize = 40; // TO SET
	
	private JPanel mainPanel;
	private JTextPane readBox; //append @todo: this should be unwritable for users
	private JScrollPane scrollPanel;
	private JTextField writeBox;
	private JTextField ipBar;
	private JTextField listenPortBar;
	private JTextField sendPortBar;
	private JTextField nickBar;
	private JButton connectBtn;
	//private JButton disconnectBtn;
	private JButton sendBtn;
	
	private List<String> history;
	
	private MessageListener listener;
	private MessageSender transmitter;
	
	//private Dimension screenSize;
	
	public MainWindow() {
		history = new LinkedList<String>();
    	//listener = new MessageListener(this);
	    mainPanel = new JPanel();
		readBox = new JTextPane();
		scrollPanel = new JScrollPane(readBox);
		writeBox = new JTextField();
		ipBar = new JTextField();
		listenPortBar = new JTextField();
		sendPortBar = new JTextField();
		nickBar = new JTextField();
		connectBtn = new JButton("Connect");
//		disconnectBtn = new JButton("disConnect");
		sendBtn = new JButton("Send");

		connectBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				listenButtonActionPerformed(e);
			}
		});
		
		sendBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				sendButtonActionPerformed(e);
			}
		});
		
		scrollPanel.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
		
		//setVisible(true);
		arrangeItems();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent e)
            {
            	Path logFile = Paths.get("C:\\Users\\Ebenezer\\Desktop\\chatlog.txt");
            	try {					//TODO change this directory to something more sensible
					Files.write(logFile, history, Charset.forName("UTF-8"));
				} catch (IOException e1) {
					write(e1.toString(), Color.RED);
				}
            			
//                ListIterator<String> iter = history.listIterator();
//                while(iter.hasNext()) {
//                	System.out.print(iter.next());
//                }
                System.exit(0);
            }
        });
	}
	
	public void arrangeItems () {
		add(mainPanel);
		setSize(width, height);
		setTitle("JP2PChat version 0.1 alpha");
		setLayout(null); //TO CHECK LATER
		
		/*
		readBox.setBounds(readBoxXPos, readBoxYPos,
				readBoxXSize, readBoxYSize);
		//TODO move writing cursor to bottom of text field
		add(readBox);
		*/
		scrollPanel.setBounds(readBoxXPos, readBoxYPos,
				readBoxXSize, readBoxYSize);
		//TODO move writing cursor to bottom of text field
		add(scrollPanel);
		
		writeBox.setBounds(writeBoxXPos, writeBoxYPos,
				writeBoxXSize, writeBoxYSize);
		add(writeBox);
		
		ipBar.setBounds(ipBarXPos, ipBarYPos,
				 ipBarXSize, ipBarYSize);
		add(ipBar);
		
		listenPortBar.setBounds(listenPortBarXPos, listenPortBarYPos,
				listenPortBarXSize, listenPortBarYSize);
		add(listenPortBar);
		
		sendPortBar.setBounds(sendPortBarXPos, sendPortBarYPos,
				sendPortBarXSize, sendPortBarYSize);
		add(sendPortBar);
		
		nickBar.setBounds(nickBarXPos, nickBarYPos, nickBarXSize, nickBarYSize);
		add(nickBar);
		
		connectBtn.setBounds(connectBtnXPos, connectBtnYPos, 
				connectBtnXSize, connectBtnYSize);

		add(connectBtn);
		
		sendBtn.setBounds(sendBtnXPos, sendBtnYPos, 
				sendBtnXSize, sendBtnYSize);
		add(sendBtn);
		
//		disconnectBtn.setBounds(disconnectBtnXPos, disconnectBtnYPos, 
//		   disconnectBtnXSize, disconnectBtnYSize);
//		add(disconnectBtn);
		
		//pack();
		getRootPane().setDefaultButton(sendBtn);
		//sendBtn.setEnabled(false);
	}
	
	public void write (String str, Color col) {
		//readBox.append(str + System.lineSeparator());
		//appendToPane(readBox, str + System.lineSeparator(), Color.RED);
		appendToPane(readBox, str + System.lineSeparator(), col);
		history.add(str);
	}
	
	private String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
	
	private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
	
	private void listenButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if(!listenPortBar.getText().equals("")) {
	//		return;	
			listener = new MessageListener(this, Integer.parseInt(listenPortBar.getText()));
			listener.start();
		}
	}
	
	private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if(!(writeBox.getText().equals("") || ipBar.getText().equals("") 
				|| sendPortBar.getText().equals("") || nickBar.getText().equals(""))) {
			
		String full_statement = "[" + nickBar.getText() + "]"
		+ "[" + getCurrentTimeStamp() + "] " + writeBox.getText();
		
		transmitter = new MessageSender(full_statement, ipBar.getText(), 
									    Integer.parseInt(sendPortBar.getText()),
									    this
										);
		transmitter.start();
		write(full_statement, Color.BLUE);
		}
		writeBox.setText("");
	}
}
