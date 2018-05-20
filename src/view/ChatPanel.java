package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ChatPanel extends JPanel {

	private final int PANEL_WIDTH = 400;
	private final int PANEL_HEIGHT = 700;

//	private ArrayList<String> messages;

	private ChatTextPane textPane;
	private JTextField userInputField;

	public ChatPanel(ArrayList<String> messages) {
//		this.messages = messages;		
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		
		textPane = new ChatTextPane();
		textPane.setPreferredSize(new Dimension(400, 650));
		userInputField = new JTextField(20);
//		add(textPane);
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setPreferredSize(new Dimension(400, 650));
		textPane.setEditable(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setMessages(messages);
		setLayout(new FlowLayout());
		add(userInputField, SwingConstants.CENTER);
		add(scrollPane, SwingConstants.CENTER);
		setVisible(true);
	}

	public void setMessages(ArrayList<String> messages) {
		for(String s: messages) {
			if(this.textPane.getText().contains(s)) {
			} else {
				textPane.append(Color.BLACK, s + "\n");
				textPane.setCaretPosition(textPane.getDocument().getLength());

				repaint();
			}
		}
	}
	
	public void addSystemMessageToChat(Color c, String s){
		textPane.append(c, s + "\n");
		textPane.setCaretPosition(textPane.getDocument().getLength());
		repaint();		
		
	}
	
	public JTextField getTextField() {
		return userInputField;
	}
}
