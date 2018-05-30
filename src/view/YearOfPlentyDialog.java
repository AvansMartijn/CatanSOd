package view;

import javax.swing.JDialog;

import model.Player;
import model.TradeRequest;

@SuppressWarnings("serial")
public class YearOfPlentyDialog extends JDialog {

	private RobberDialogPanel robberDialogPanel;

	public YearOfPlentyDialog(Player player1,Player player2, Player player3) {

		robberDialogPanel = new RobberDialogPanel(player1,player2,player3);
		
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
