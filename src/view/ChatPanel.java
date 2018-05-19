package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

@SuppressWarnings("serial")
public class ChatPanel extends JPanel {

	private final int PANEL_WIDTH = 300;
	private final int PANEL_HEIGHT = 700;

	private ArrayList<String> messages;

	private JTextPane textPane;
	private JTextField userInputField;

	public ChatPanel(ArrayList<String> messages) {
		this.messages = messages;		
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		
		textPane = new JTextPane();
		userInputField = new JTextField(20);
		add(textPane);
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setPreferredSize(new Dimension(300, 650));
		textPane.setEditable(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setMessages(messages);
		this.setLayout(new FlowLayout());
		this.add(userInputField, SwingConstants.CENTER);
		this.add(scrollPane, SwingConstants.CENTER);
		this.setVisible(true);
	}

	private void appendToPane(String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = textPane.getDocument().getLength();
        textPane.setCaretPosition(len);
        textPane.setCharacterAttributes(aset, false);
        textPane.replaceSelection(msg);
    }


	public void setMessages(ArrayList<String> messages) {
		for(String s: messages) {
			if(this.textPane.getText().contains(s)) {
				
			} else {
				appendToPane(s + "\n", Color.BLACK);
				textPane.setCaretPosition(textPane.getDocument().getLength());
//				this.messages = messages;
				repaint();
			}
		}
	}
	
	public void addSystemMessageToChat(String s){
//		textPane.append(s + "\n");
		textPane.setCaretPosition(textPane.getDocument().getLength());
		repaint();		
		
	}
	
	public JTextField getTextField() {
		return userInputField;
	}
}
