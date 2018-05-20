package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerActionPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;
	
	private PlayerOptionMenuPanel playerOptionMenuPanel;
	private BuildPanel buildPanel;
	private BuyPanel buyPanel;
	private TradePanel tradePanel;
	
	// Constructor
	public PlayerActionPanel() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setLayout(new CardLayout());
		
		playerOptionMenuPanel = new PlayerOptionMenuPanel();
		buildPanel = new BuildPanel();
		buyPanel = new BuyPanel();
		tradePanel = new TradePanel();
		
		// @Guicontroller: add(playerOptionMenuPanel();
	}


	public void getBuildButton() {
		
		playerOptionMenuPanel.getBuildButton();
		
	}
	
}
