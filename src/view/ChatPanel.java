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

	public ChatPanel() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		textArea = new JTextArea(5, 30);
		userInputField = new JTextField(30);
		add(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(380, 100));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.setLayout(new FlowLayout());
		this.add(userInputField, SwingConstants.CENTER);
		this.add(scrollPane, SwingConstants.CENTER);
		this.setSize(400, 200);
		this.setVisible(true);
	}


	public void setMessages(ArrayList<String> messages) {
		for(String s: messages) {
			textArea.append(s);
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
		this.messages = messages;
		repaint();
	}
	
	public JTextField getTextField() {
		return userInputField;
	}
}
