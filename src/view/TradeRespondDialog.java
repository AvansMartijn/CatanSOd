package view;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class TradeRespondDialog extends JDialog {

	private TradeRespondPanels tradeRespondPanels;

	public TradeRespondDialog() {

		tradeRespondPanels = new TradeRespondPanels();

		setContentPane(tradeRespondPanels);
		pack();
		setLocationRelativeTo(null);

		setVisible(true);

	}

}
