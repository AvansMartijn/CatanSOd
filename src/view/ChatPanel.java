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
	private final int SCROLLPANE_WIDTH = 400;
	private final int SCROLLPANE_HEIGHT = 650;

	private ChatTextPane textPane;
	private JTextField userInputField;

	public ChatPanel(ArrayList<String> messages) {
		messages = new ArrayList<String>();
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

		textPane = new ChatTextPane();
		textPane.setPreferredSize(new Dimension(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT));
		userInputField = new JTextField(20);
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setPreferredSize(new Dimension(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT));
		textPane.setEditable(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setMessages(messages);
		setLayout(new FlowLayout());
		add(userInputField, SwingConstants.CENTER);
		add(scrollPane, SwingConstants.CENTER);
		setVisible(true);
	}

	public void setMessages(ArrayList<String> messages) {
		for (String s : messages) {
			if (this.textPane.getText().contains(s)) {
			} else {
				textPane.append(Color.BLACK, s + "\n");
				textPane.setCaretPosition(textPane.getDocument().getLength());
				repaint();
			}
		}
	}

	public void addSystemMessageToChat(Color c, String s) {
		textPane.append(c, s + "\n");
		textPane.setCaretPosition(textPane.getDocument().getLength());
		repaint();
	}

	public JTextField getTextField() {
		return userInputField;
	}
}
