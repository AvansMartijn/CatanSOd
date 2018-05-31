package view;

import javax.swing.JDialog;

import model.Player;
import model.TradeRequest;

@SuppressWarnings("serial")
public class TradeRespondDialog extends JDialog {

	private TradeRespondPanels tradeRespondPanels;

	public TradeRespondDialog(Player player1, TradeRequest tR1, Player player2, TradeRequest tR2,
			Player player3, TradeRequest tR3) {
		tradeRespondPanels = new TradeRespondPanels(player1, tR1, player2, tR2, player3, tR3);
		setUndecorated(true);
		setContentPane(tradeRespondPanels);
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	public TradeRespondPanels getTradeRespondPanels() {
		return tradeRespondPanels;
	}
}
