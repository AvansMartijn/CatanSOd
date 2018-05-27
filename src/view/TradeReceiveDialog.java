package view;

import javax.swing.JDialog;

import model.TradeRequest;

@SuppressWarnings("serial")
public class TradeReceiveDialog extends JDialog {

	private TradeReceiveDialogPanel tradeReceiveDialogPanel;

	public TradeReceiveDialog(TradeRequest tr) {

		tradeReceiveDialogPanel = new TradeReceiveDialogPanel(tr);

		setContentPane(tradeReceiveDialogPanel);
		pack();
		setLocationRelativeTo(null);

		setVisible(true);

	}

	public TradeReceiveDialogPanel getTradeReceiveDialogPanel() {
		return tradeReceiveDialogPanel;
	}

}
