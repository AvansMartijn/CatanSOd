package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameSouthContainerPanel extends JPanel {
	
	private final int PANEL_HEIGHT = 240;

	// Instance variables
	private Color myBackGroundColor = new Color(240, 226, 223);

	// Constructor
	public GameSouthContainerPanel(PlayerStatsPanel[] playerStatsPanels, DevelopmentCardsPanel developmentCardsPanel) {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(myBackGroundColor);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		 setPreferredSize(new Dimension((int)width, PANEL_HEIGHT));
			
		developmentCardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
		add(playerStatsPanels[0]);
		add(playerStatsPanels[1]);
		add(developmentCardsPanel);
		add(playerStatsPanels[2]);
		add(playerStatsPanels[3]);
	}
}
