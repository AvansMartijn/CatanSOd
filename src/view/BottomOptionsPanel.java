package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BottomOptionsPanel extends JPanel {

	private JButton logoutButton;
	private JButton exitButton;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color buttonBackgroundColor = new Color(223, 190, 172);
	
	public BottomOptionsPanel() {
		
		setBackground(backgroundColor);
		
		logoutButton = new JButton("Uitloggen");
		logoutButton.setOpaque(true);
		logoutButton.setBackground(buttonBackgroundColor);
		add(logoutButton);

		exitButton = new JButton("Afsluiten");
		exitButton.setOpaque(true);
		exitButton.setBackground(buttonBackgroundColor);
		add(exitButton);

	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}
}
