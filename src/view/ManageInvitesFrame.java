package view;

import java.util.ArrayList;

import javax.swing.JFrame;

import model.Catan;

@SuppressWarnings("serial")
public class ManageInvitesFrame extends JFrame {
	
	public ManageInvitesPanel panel;
	
	public ManageInvitesFrame(ArrayList<String> availablePlayers, Catan game) {
		panel = new ManageInvitesPanel(availablePlayers, game);
		this.setContentPane(panel);
		this.setUndecorated(true);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
	}
	
	public void UpdatePanel(ArrayList<String> availablePlayers, Catan game) {
		panel = new ManageInvitesPanel(availablePlayers, game);
		this.setContentPane(panel);
		this.pack();
		this.repaint();
	}
}
