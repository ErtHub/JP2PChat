package JP2PChat;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import networking.PingListener;
import networking.PingSender;

public class MainWindow extends JFrame implements WritableGUI{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int width = 683;
	private static final int height = 550;
	
	private static final int readBoxXPos = 20;
	private static final int readBoxYPos = 50;
	private static final int readBoxXSize = 600;
	private static final int readBoxYSize = 400;
	
	private static final int writeBoxXPos = readBoxXPos;
	private static final int writeBoxYPos = readBoxYSize + readBoxYPos + 10;
	private static final int writeBoxXSize = readBoxXSize - 130;
	private static final int writeBoxYSize = 40; 

	private static final int ipBarXPos = readBoxXPos;
	private static final int ipBarYPos = 10;
	private static final int ipBarXSize = 150;
	private static final int ipBarYSize = 20; 

	private static final int listenPortBarXPos = readBoxXPos + ipBarXSize + 10;
	private static final int listenPortBarYPos = 10;
	private static final int listenPortBarXSize = 75;
	private static final int listenPortBarYSize = ipBarYSize; 
	
	private static final int sendPortBarXPos = listenPortBarXPos + listenPortBarXSize + 10;
	private static final int sendPortBarYPos = 10;
	private static final int sendPortBarXSize = 75;
	private static final int sendPortBarYSize = ipBarYSize; 
	
	private static final int nickBarXPos = sendPortBarXPos + sendPortBarXSize + 10;
	private static final int nickBarYPos = 10;
	private static final int nickBarXSize = 125;
	private static final int nickBarYSize = ipBarYSize;
	
	private static final int connectBtnXPos = readBoxXPos + readBoxXSize - 100;
	private static final int connectBtnYPos = 10;
	private static final int connectBtnXSize = 100;
	private static final int connectBtnYSize = 35; 
	
	private static final int sendBtnXPos = writeBoxXPos + writeBoxXSize + 10;
	private static final int sendBtnYPos = writeBoxYPos;
	private static final int sendBtnXSize = 120;
	private final int sendBtnYSize = 40; 
	
	private static final int indicatorXPos = nickBarXPos + nickBarXSize + 5;
	private static final int indicatorYPos =  nickBarYPos - 15; 
	private static final int indicatorSize = readBoxYPos;
	
	private JPanel mainPanel;
	private JTextPane readBox; //append @todo: this should be unwritable for users
	private JScrollPane scrollPanel;
	private JTextField writeBox;
	private JTextField ipBar;
	private JTextField listenPortBar;
	private JTextField sendPortBar;
	private JTextField nickBar;
	private JButton connectBtn;
	private JButton sendBtn;
	private JLabel indicator;
	
	private List<String> history;
	
	private MessageListener listener;
	private MessageSender transmitter;
	private PingSender pingSender;
	private PingListener pingReceiver;
	
	public MainWindow() {
		history = new LinkedList<String>();
	    mainPanel = new JPanel();
		readBox = new JTextPane();
		scrollPanel = new JScrollPane(readBox);
		writeBox = new JTextField();
		ipBar = new JTextField();
		listenPortBar = new JTextField();
		sendPortBar = new JTextField();
		nickBar = new JTextField();
		connectBtn = new JButton("Connect");
		sendBtn = new JButton("Send");
		indicator = new JLabel("•");

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
		
		arrangeItems();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent e)
            {
            	if(!history.isEmpty()) {
            		Path logFile = Paths.get(getCurrentTimeStamp("yyyy-MM-dd HH-mm-ss") + " chatlog.txt");
            		try {
            			String path = "." + File.separator + "Archive" + File.separator + nickBar.getText() + File.separator + logFile.toString();
            			
            			File f = new File(path);
            			f.getParentFile().mkdirs(); 
            			f.createNewFile();
            			
            			ListIterator<String> iter = history.listIterator();
            			FileWriter fstream = new FileWriter(path);
                        BufferedWriter out = new BufferedWriter(fstream);
            			
            			while(iter.hasNext()) {
            				out.write(iter.next());
            				out.newLine();
            			}
            			out.close();
            		} 
            		catch (IOException e1) {
            			write(e1.toString(), Color.RED);
            		}
            	}
                System.exit(0);
            }
        });
	}
	
	public void arrangeItems () {
		add(mainPanel);
		setSize(width, height);
		setTitle("JP2PChat version 0.9c beta");
		setLayout(null); 
		
		readBox.setEditable(false);
		scrollPanel.setBounds(readBoxXPos, readBoxYPos,
				readBoxXSize, readBoxYSize);
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
		
		indicator.setForeground(Color.red);
		indicator.setBounds(indicatorXPos,indicatorYPos, indicatorSize,indicatorSize);
		indicator.setFont(new Font("•", Font.PLAIN, readBoxYPos));
		add(indicator);
		
		getRootPane().setDefaultButton(sendBtn);
	}
	
	public void write (String str, Color col) {
		//readBox.append(str + System.lineSeparator());
		//appendToPane(readBox, str + System.lineSeparator(), Color.RED);
		readBox.setEditable(true);
		appendToPane(readBox, str + System.lineSeparator(), col);
		history.add(str);
		readBox.setEditable(false);
	}
	
	private String getCurrentTimeStamp(String format) {
	    SimpleDateFormat sdfDate = new SimpleDateFormat(format);
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
		if(!listenPortBar.getText().equals("") && !sendPortBar.getText().equals("") && !ipBar.getText().equals("")) {
			Integer listenPort = Integer.parseInt(listenPortBar.getText());
			Integer sendPort = Integer.parseInt(sendPortBar.getText());
			
			listener = new MessageListener(this, listenPort);
			listener.start();
			
			if (sendPort - listenPort == 1 || sendPort - listenPort == -1 )
			{
				sendPort+=2;
				listenPort+=2;
			}
			else
			{
				++sendPort;
				++listenPort;
			}
			pingReceiver = new PingListener(this,listenPort);
			pingReceiver.start();
			
			pingSender = new PingSender(ipBar.getText(), this, sendPort);
			pingSender.start();
			
		}
	}
	
	private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if(!(writeBox.getText().equals("") || ipBar.getText().equals("") 
				|| sendPortBar.getText().equals("") || nickBar.getText().equals(""))) {
			
		String full_statement = "[" + nickBar.getText() + "]"
		+ "[" + getCurrentTimeStamp("yyyy-MM-dd HH:mm:ss") + "] " + writeBox.getText();
		
		transmitter = new MessageSender(full_statement, ipBar.getText(), 
									    Integer.parseInt(sendPortBar.getText()),
									    this
										);
		transmitter.start();
		write(full_statement, Color.BLUE);
		}
		writeBox.setText("");
		
	}
	
	public void setIndicator(Color color){
		indicator.setForeground(color);
	}
}
