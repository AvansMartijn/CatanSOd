package view;

import java.awt.CardLayout;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class TradeRespondDialog extends JDialog {

	private TradeRespondDialogPanel tradeRespondDialogPanel1;
	private TradeRespondDialogPanel tradeRespondDialogPanel2;
	private TradeRespondDialogPanel tradeRespondDialogPanel3;

	public TradeRespondDialog() {

		setLayout(new CardLayout());

		this.tradeRespondDialogPanel1 = new TradeRespondDialogPanel("player 1");
		this.tradeRespondDialogPanel2 = new TradeRespondDialogPanel("player 2");
		this.tradeRespondDialogPanel3 = new TradeRespondDialogPanel("player 3");

		add(this.tradeRespondDialogPanel1, "player1");
		add(this.tradeRespondDialogPanel2, "player2");
		add(this.tradeRespondDialogPanel3, "player3");

	}

	public TradeRespondDialogPanel getTradeRespondDialogPanel1() {
		return tradeRespondDialogPanel1;
	}

	public void setTradeRespondDialogPanel1() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "player1");
	}

	public TradeRespondDialogPanel getTradeRespondDialogPanel2() {
		return tradeRespondDialogPanel2;
	}
	
	public void setTradeRespondDialogPanel2() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "player2");
	}

	public TradeRespondDialogPanel getTradeRespondDialogPanel3() {
		return tradeRespondDialogPanel3;
	}

	public void setTradeRespondDialogPanel3() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "player3");
		;
	}

}