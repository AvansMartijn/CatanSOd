package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	
	// Constructor
	public PlayerActionPanel() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(myBackGroundColor);
		setLayout(new CardLayout());
		
		playerOptionMenuPanel = new PlayerOptionMenuPanel();
		buildPanel = new BuildPanel();
		buyPanel = new BuyPanel();
		tradePanel = new TradePanel();
		
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

	public void setBuildPanel(BuildPanel buildPanel) {
		this.buildPanel = buildPanel;
	}

	public BuyPanel getBuyPanel() {
		return buyPanel;
	}

	public void setBuyPanel(BuyPanel buyPanel) {
		this.buyPanel = buyPanel;
	}

	public TradePanel getTradePanel() {
		return tradePanel;
	}

	public void setTradePanel(TradePanel tradePanel) {
		this.tradePanel = tradePanel;
	}
	
}
