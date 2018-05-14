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
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ChatPanel extends JPanel {

	private final int PANEL_WIDTH = 300;
	private final int PANEL_HEIGHT = 700;

	private ArrayList<String> messages;

	private JTextArea textArea;
	private JTextField userInputField;

	public ChatPanel(ArrayList<String> messages) {
		this.messages = messages;		
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		
		textArea = new JTextArea(3, 30);
		userInputField = new JTextField(20);
		add(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(300, 650));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setMessages(messages);
		this.setLayout(new FlowLayout());
		this.add(userInputField, SwingConstants.CENTER);
		this.add(scrollPane, SwingConstants.CENTER);
		this.setVisible(true);
	}


	public void setMessages(ArrayList<String> messages) {
		for(String s: messages) {
			if(this.textArea.getText().contains(s)) {
				
			} else {
				textArea.append(s + "\n");
				textArea.setCaretPosition(textArea.getDocument().getLength());
				this.messages = messages;
				repaint();
			}
		}
	}
	
	public void addSystemMessageToChat(String s){
		textArea.append(s + "\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
		repaint();		
		
	}
	
	public JTextField getTextField() {
		return userInputField;
	}
}
