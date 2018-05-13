package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ChatPanel extends JPanel {

	private final int PANEL_WIDTH = 300;
	private final int PANEL_HEIGHT = 700;
	
	private JTextArea textArea;
	
	public ChatPanel() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		textArea = new JTextArea("type here...", 1, 20);
		add(textArea);
	}
}
