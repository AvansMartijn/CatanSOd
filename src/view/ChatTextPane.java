package view;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class ChatTextPane extends JTextPane {

	public void append(Color c, String s) { // better implementation--uses
//		// StyleContext
//		StyleContext sc = StyleContext.getDefaultStyleContext();
//		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
//
//		int len = getDocument().getLength(); // same value as
//		// getText().length();
//		setCaretPosition(len); // place caret at the end (with no selection)
//		setCharacterAttributes(aset, false);
//		replaceSelection(s); // there is no selection, so inserts at caret
		System.out.println("test");		
		 StyledDocument doc = this.getStyledDocument();

	        Style style = this.addStyle("Color Style", null);
	        StyleConstants.setForeground(style, c);
	        try {
	            doc.insertString(doc.getLength(), s, style);
	        } 
	        catch (BadLocationException e) {
	            e.printStackTrace();
	        }           
	}

}
