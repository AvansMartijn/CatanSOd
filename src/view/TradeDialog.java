package view;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class TradeDialog extends JDialog {

	private TradePanel tradePanel;

	public TradeDialog(PlayerActionPanel playerActionPanel) {

		setUndecorated(true);

		tradePanel = new TradePanel();

		setContentPane(tradePanel);

		pack();
		setLocationRelativeTo(playerActionPanel);

		setVisible(true);

	}
}
