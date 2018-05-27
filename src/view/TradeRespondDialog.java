package view;

import javax.swing.JDialog;

import model.TradeRequest;

@SuppressWarnings("serial")
public class TradeRespondDialog extends JDialog {

	private TradeRespondPanels tradeRespondPanels;

	public TradeRespondDialog(TradeRequest tR1, TradeRequest tR2, TradeRequest tR3) {
		tradeRespondPanels = new TradeRespondPanels(tR1, tR2, tR3);
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
