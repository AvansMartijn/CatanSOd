package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerActionPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private Color backgroundColor = new Color(240, 226, 223);
	private PlayerOptionMenuPanel playerOptionMenuPanel;
	private BuildPanel buildPanel;
	private BuyPanel buyPanel;
	private TradePlayerPanel tradePanel;
	private ReturnToBuildPanel returnToBuildPanel;
	private TradeOptionsPanel tradeOptionsPanel;
	private TradeBankPanel tradeBankPanel;
	private CurrentTradeRequestPanel tradeRequestListPanel;

	// Constructor
	public PlayerActionPanel(PlayerOptionMenuPanel playerOptionMenuPanel, BuildPanel buildPanel, BuyPanel buyPanel,
			TradePlayerPanel tradePlayerPanel, TradeBankPanel tradeBankPanel, ReturnToBuildPanel returnToBuildPanel,
			TradeOptionsPanel tradeOptionsPanel, CurrentTradeRequestPanel tradeRequestListPanel) {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(new CardLayout());

		this.playerOptionMenuPanel = playerOptionMenuPanel;
		this.buildPanel = buildPanel;
		this.buyPanel = buyPanel;
		this.tradePanel = tradePlayerPanel;
		this.returnToBuildPanel = returnToBuildPanel;
		this.tradeOptionsPanel = tradeOptionsPanel;
		this.tradeBankPanel = tradeBankPanel;
		this.tradeRequestListPanel = tradeRequestListPanel;

		add(playerOptionMenuPanel, "playerOptionMenuPanel");
		add(buyPanel, "buyPanel");
		add(buildPanel, "buildPanel");
		add(returnToBuildPanel, "returnToBuildPanel");
		add(tradeOptionsPanel, "tradeOptionsPanel");
		add(tradePlayerPanel, "tradePlayerPanel");
		add(tradeBankPanel, "tradeBankPanel");
		add(tradeRequestListPanel, "tradeRequestListPanel");
	}

	public PlayerOptionMenuPanel getPlayerOptionMenuPanel() {
		return playerOptionMenuPanel;
	}

	public void setPlayerOptionMenuPanel() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "playerOptionMenuPanel");
	}

	public BuildPanel getBuildPanel() {
		return buildPanel;
	}

	public void setBuildPanel() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "buildPanel");
	}

	public BuyPanel getBuyPanel() {
		return buyPanel;
	}

	public void setBuyPanel() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "buyPanel");
	}

	public TradePlayerPanel getPlayerTradePanel() {
		return tradePanel;
	}

	public void setTradePlayerPanel() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "tradePlayerPanel");
	}

	public ReturnToBuildPanel getReturnToBuildPanel() {
		return returnToBuildPanel;
	}

	public void setReturnToBuildPanel() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "returnToBuildPanel");
	}

	public TradeOptionsPanel getTradeOptionsPanel() {
		return tradeOptionsPanel;
	}

	public void setTradeOptionsPanel() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "tradeOptionsPanel");
	}

	public TradeBankPanel getTradeBankPanel() {
		return tradeBankPanel;
	}

	public void setTradeBankPanel() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "tradeBankPanel");
	}

	public CurrentTradeRequestPanel getTradeRequestListPanel() {
		return tradeRequestListPanel;
	}

	public void setTradeRequestListPanel() {
		CardLayout cl = (CardLayout) (getLayout());
		cl.show(this, "tradeRequestListPanel");
	}
}
