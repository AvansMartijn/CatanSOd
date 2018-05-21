package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BottomOptionsPanel extends JPanel {

	private JButton logoutButton;
	private JButton exitButton;

	private Color backgroundColor = new Color(189, 133, 100);
	
	public BottomOptionsPanel() {
		
		setBackground(backgroundColor);
		
		logoutButton = new JButton("Uitloggen");
		logoutButton.setOpaque(true);
		add(logoutButton);

		exitButton = new JButton("Afsluiten");
		logoutButton.setOpaque(true);
		add(exitButton);

	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}
}
