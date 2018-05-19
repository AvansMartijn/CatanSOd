package view;


import java.awt.Component;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class TradeDialog extends JDialog {

	private TradePanel tradePanel;

	public TradeDialog(Component location) {

		setUndecorated(true);

		tradePanel = new TradePanel();

		setContentPane(tradePanel);

		pack();
		setLocationRelativeTo(location);

		setVisible(true);
		toFront();
		requestFocus();

	}

}
