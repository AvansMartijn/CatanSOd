package view;

import javax.swing.JDialog;

import model.TradeRequest;

@SuppressWarnings("serial")
public class TradeReceiveDialog extends JDialog {

	private TradeReceiveDialogPanel tradeReceiveDialogPanel;

	public TradeReceiveDialog(TradeRequest tr) {

		tradeReceiveDialogPanel = new TradeReceiveDialogPanel(tr);
		
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
