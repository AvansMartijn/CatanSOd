package view;

import java.util.ArrayList;

import javax.swing.JDialog;

import model.Player;

@SuppressWarnings("serial")
public class RobberDialog extends JDialog {

	private RobberDialogPanel robberDialogPanel;

	public RobberDialog(ArrayList<Player> playersToRob) {

		robberDialogPanel = new RobberDialogPanel(playersToRob);
		
		setUndecorated(true);
		
		setContentPane(robberDialogPanel);
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	public RobberDialogPanel getRobberDialogPanel() {
		return robberDialogPanel;
	}
}
