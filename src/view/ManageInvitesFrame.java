package view;

import java.util.ArrayList;

import javax.swing.JFrame;

import model.Catan;

public class ManageInvitesFrame extends JFrame{
	
	public ManageInvitesPanel panel;
	
	public ManageInvitesFrame(String selfUsername, ArrayList<String> availablePlayers, Catan game) {
		panel = new ManageInvitesPanel(selfUsername, availablePlayers, game);
		this.setContentPane(panel);
		this.pack();
		this.setVisible(true);
	}
}
