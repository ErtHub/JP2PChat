package JP2PChat;

import java.awt.Color;
/**
 * Interface implemented by MainWindow in order to make writing to it
 * and switching the indicator possible
 */
public interface WritableGUI {
	public void write (String str, Color col);
	public void setIndicator(Color col);
	
}
