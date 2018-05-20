package controller;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BottomOptionsPanel extends JPanel {

	private JButton logoutButton;
	private JButton exitButton;

	public BottomOptionsPanel() {
		
		logoutButton = new JButton("Uitloggen");
		add(logoutButton);

		exitButton = new JButton("Afsluiten");
		add(exitButton);

	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

}
