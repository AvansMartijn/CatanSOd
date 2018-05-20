package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerActionPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;
	
	// Instance variables
	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	
	private PlayerOptionMenuPanel playerOptionMenuPanel;
	private BuildPanel buildPanel;
	private BuyPanel buyPanel;
	private TradePanel tradePanel;
	
	// Constructor
	public PlayerActionPanel() {
		setLayout(new CardLayout());
		
		// create buy screen
		// build
		// trade
		// playeroptionmenupanel
		
		// @Guicontroller: add(playerOptionMenuPanel();
	}


	
	
}
