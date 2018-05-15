package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.PlayStatus;
import model.Player;
import model.PlayerColor;

@SuppressWarnings("serial")
public class GameSouthContainerPanel extends JPanel {

	// Instance variables
	private Color myBackGroundColor = new Color(240, 226, 223);

	private MyDevelopmentCardsPanel myDevelopmentCardsPanel;

	// Constructor
	public GameSouthContainerPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(myBackGroundColor);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		 setPreferredSize(new Dimension((int)width, 240));
			
//		myResourcesPanel = new MyResourcesPanel();
		myDevelopmentCardsPanel = new MyDevelopmentCardsPanel();
		myDevelopmentCardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
		add(new PlayerStatsPanel(new Player(2, "Barend", PlayerColor.BLAUW, 2, PlayStatus.UITGEDAAGDE))); // TODO placeholder players
		add(new PlayerStatsPanel(new Player(2, "Barend", PlayerColor.BLAUW, 2, PlayStatus.UITGEDAAGDE)));
		add(myDevelopmentCardsPanel);
		add(new PlayerStatsPanel(new Player(2, "Barend", PlayerColor.BLAUW, 2, PlayStatus.UITGEDAAGDE)));
		add(new PlayerStatsPanel(new Player(2, "Barend", PlayerColor.BLAUW, 2, PlayStatus.UITGEDAAGDE)));
	}
}
