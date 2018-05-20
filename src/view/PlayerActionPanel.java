package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerActionPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;
	
	private Color myBackGroundColor = new Color(240, 226, 223);
	private PlayerOptionMenuPanel playerOptionMenuPanel;
	private BuildPanel buildPanel;
	private BuyPanel buyPanel;
	private TradePanel tradePanel;
	private CardLayout layout;
	
	// Constructor
	public PlayerActionPanel(PlayerOptionMenuPanel playerOptionMenuPanel, BuildPanel buildPanel, BuyPanel buyPanel, TradePanel tradePanel) {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(myBackGroundColor);
		
		CardLayout layout = new CardLayout();
		setLayout(layout);
		
		this.playerOptionMenuPanel = playerOptionMenuPanel;
		this.buildPanel = buildPanel;
		this.buyPanel = buyPanel;
		this.tradePanel = tradePanel;
		
		add(playerOptionMenuPanel);
	}

	public PlayerOptionMenuPanel getPlayerOptionMenuPanel() {
		return playerOptionMenuPanel;
	}

	public void setPlayerOptionMenuPanel(PlayerOptionMenuPanel playerOptionMenuPanel) {
		this.playerOptionMenuPanel = playerOptionMenuPanel;
	}

	public BuildPanel getBuildPanel() {
		return buildPanel;
	}

	public void setBuildPanel() {
	    CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this, "buildPanel");
	}

	public BuyPanel getBuyPanel() {
		return buyPanel;
	}

	public void setBuyPanel() {
		CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this, "buyPanel");
	}

	public TradePanel getTradePanel() {
		return tradePanel;
	}

	public void setTradePanel() {
		CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this, "tradePanel");
	}

	public CardLayout getLayout() {
		return layout;
	}

	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}
	
}
