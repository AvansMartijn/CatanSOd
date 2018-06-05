package view;

import javax.swing.JDialog;

import model.Player;
import model.TradeRequest;

@SuppressWarnings("serial")
public class TradeReceiveDialog extends JDialog {

	private TradeReceiveDialogPanel tradeReceiveDialogPanel;

	public TradeReceiveDialog(Player player,TradeRequest tr) {

		tradeReceiveDialogPanel = new TradeReceiveDialogPanel(player,tr);
		
		setUndecorated(true);
		
		setContentPane(tradeReceiveDialogPanel);
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	public TradeReceiveDialogPanel getTradeReceiveDialogPanel() {
		return tradeReceiveDialogPanel;
	}
}
