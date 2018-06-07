package view;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class ChatTextPane extends JTextPane {

	public void append(Color c, String s) {
		 StyledDocument doc = this.getStyledDocument();

	        Style style = this.addStyle("Color Style", null);
	        StyleConstants.setForeground(style, c);
	        try {
	            doc.insertString(doc.getLength(), s, style);
	        } 
	        catch (BadLocationException e) {
	        }           
	}

}
