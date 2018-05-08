package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerStatsPanel extends JPanel {

	private final int PANEL_WIDTH = 210;
	private final int PANEL_HEIGHT = 240;

	// Instance variables
	private Color backgroundColor = new Color(223, 190, 172);
	
	// GUI Components
	private JLabel playerNameLabel;
	private JLabel playerPointsLabel;
	private JLabel playerSettlementsAmountLabel;
	private JLabel playerCitiesAmountLabel;
	private JLabel playerRoadsAmountLabel;
	private JLabel playerCardsAmountLabel;
	
	// Player info TODO might not be needed if everything is stored in playerObject
	private boolean hasLargestArmy;
	private boolean hasLongestRoad;

	// Constructor
	public PlayerStatsPanel(String playerName, int points, int settlements, int cities, int roads, int cards, // TODO or get playerObject for less parameters - which class are points being stored?
			boolean hasLargestArmy, boolean hasLongestRoad) {
		playerNameLabel = new JLabel(playerName + " (jij)");
		playerPointsLabel = new JLabel("Punten: " + points);
		playerSettlementsAmountLabel = new JLabel("" + settlements);
		playerCitiesAmountLabel = new JLabel("" + cities);
		playerRoadsAmountLabel = new JLabel("" + roads);
		playerCardsAmountLabel = new JLabel("" + cards);
		
		setBackground(backgroundColor);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

		setLayout(new GridBagLayout());
		create();
	}

	// Create
	private void create() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

		// Add playerName and its points
		playerNameLabel.setForeground(Color.RED);
		playerNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		add(playerNameLabel, gridBagConstraints);
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.gridy++;
		playerPointsLabel.setFont(new Font("Arial", Font.BOLD, 18));
		playerPointsLabel.setForeground(Color.RED);
		add(playerPointsLabel, gridBagConstraints);
		
		
		// Add images of buildings
		gridBagConstraints.insets = new Insets(2, 5, 2, 2);
		String[] urls = new String[] {"/images/Wood_Icon.png", "/images/chat.png", "/images/Rock_Icon.png", "/images/Wheat_Icon.png"};
		
		for (int i = 0; i < urls.length; i++) {
			Image image = null;
			try {
				URL url = this.getClass().getResource(urls[i]);
				image = ImageIO.read(url);
				image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
				
				gridBagConstraints.gridy++;
				// TEST
				gridBagConstraints.gridx++;
				playerCardsAmountLabel.setForeground(Color.WHITE);
				playerCardsAmountLabel.setFont(new Font("Arial", Font.BOLD, 20));
				add(playerCardsAmountLabel, gridBagConstraints);
				gridBagConstraints.gridx--;
				// END TEST
			} catch (IOException e) {
			}
			add(new JLabel(new ImageIcon(image)), gridBagConstraints);
		}
	}
}
