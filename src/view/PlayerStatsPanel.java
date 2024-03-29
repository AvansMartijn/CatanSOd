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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Player;

@SuppressWarnings("serial")
public class PlayerStatsPanel extends JPanel {

	private final int PANEL_WIDTH = 210;
	private final int PANEL_HEIGHT = 240;
	private final int STATS_AMOUNT = 5;
	private final int LARGEST_ARMY_IMAGE_SIZE = 60;
	private final int LONGEST_ROAD_IMAGE_SIZE = 45;
	private final int STAT_LABEL_DIMENSION = 35;

	// Instance variables
	private Color backgroundColor = new Color(223, 190, 172);

	// GUI Components
	private JLabel playerNameLabel;
	private JLabel playerPointsLabel;
	private JLabel buildingPointsLabel;
	private JLabel[] statLabels;
	private JLabel longestRoadLabel;
	private JLabel largestArmyLabel;

	private Player player;
	private boolean isSelfPlayer;
	private GridBagConstraints gridBagConstraints = new GridBagConstraints();

	// Constructor
	public PlayerStatsPanel(Player player, boolean isSelfPlayer) {
		this.statLabels = new JLabel[STATS_AMOUNT];
		this.player = player;
		this.isSelfPlayer = isSelfPlayer;
		this.playerNameLabel = new JLabel(player.getUsername());
		if(this.isSelfPlayer) {
			playerNameLabel.setText(player.getUsername() + " (jij)");
			buildingPointsLabel = new JLabel();
		}
		this.playerPointsLabel = new JLabel();
		this.longestRoadLabel = new JLabel();
		this.largestArmyLabel = new JLabel();

		setBackground(backgroundColor);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setMaximumSize(getPreferredSize());
		setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		setLayout(new GridBagLayout());
		create();
	}

	// Create
	private void create() {
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

		// Add username and points
		playerNameLabel.setForeground(player.getColorObject());
		playerNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		add(playerNameLabel, gridBagConstraints);
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.gridy++;
		playerPointsLabel.setFont(new Font("Arial", Font.BOLD, 18));
		playerPointsLabel.setForeground(player.getColorObject());
		add(playerPointsLabel, gridBagConstraints);
		
		if(isSelfPlayer) {
			gridBagConstraints.gridx = 1;
			buildingPointsLabel.setFont(new Font("Arial", Font.BOLD, 18));
			buildingPointsLabel.setForeground(player.getColorObject());
			add(buildingPointsLabel, gridBagConstraints);
		}

		addSatsImages();
		createLabels();
		updateStats();

		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		addImage(largestArmyLabel, 4, 4, "/images/LargestArmy_Icon.png", LARGEST_ARMY_IMAGE_SIZE,
				LARGEST_ARMY_IMAGE_SIZE);
		addImage(longestRoadLabel, 4, 2, "/images/LongestRoad_Icon.png", LONGEST_ROAD_IMAGE_SIZE,
				LONGEST_ROAD_IMAGE_SIZE);
	}

	// add building images
	private void addSatsImages() {
		// Add images of buildings
		gridBagConstraints.insets = new Insets(0, 5, 0, 2);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridwidth = 1;
		String[] urls = new String[] { "/images/Village-Icon.png", "/images/City-Icon.png", "/images/Road_Icon.png",
				"/images/ResourceCards_Icon.png", "/images/DevelopmentCards_Icon.png" };

		// Add amount icons
		for (int i = 0; i < urls.length; i++) {
			if(i == (urls.length - 1)) {
				gridBagConstraints.insets = new Insets(0, 5, 10, 2);
			}
			Image image = null;
			try {
				URL url = this.getClass().getResource(urls[i]);
				image = ImageIO.read(url);
				image = image.getScaledInstance(STAT_LABEL_DIMENSION, STAT_LABEL_DIMENSION, Image.SCALE_DEFAULT);
			} catch (IOException e) {
			}
			gridBagConstraints.gridy++;
			gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
			add(new JLabel(new ImageIcon(image)), gridBagConstraints);
		}
	}

	// Create labels
	private void createLabels() {
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.weightx = 0.24;
		gridBagConstraints.weighty = 0.24;
		gridBagConstraints.insets = new Insets(0, 5, 0, 2);
		gridBagConstraints.anchor = GridBagConstraints.WEST;

		for (int i = 0; i < statLabels.length; i++) {
			statLabels[i] = new JLabel("", SwingConstants.LEFT);
			statLabels[i].setForeground(Color.WHITE);
			statLabels[i].setFont(new Font("Arial", Font.BOLD, 20));
			add(statLabels[i], gridBagConstraints);
			gridBagConstraints.gridy++;
		}
	}

	// Update stats
	public void updateStats() {
		playerPointsLabel.setText("Punten: " + (player.getVictoryPoints() - player.getVictoryCardAmount()));
		if(isSelfPlayer) {
			buildingPointsLabel.setText("+ " + player.getVictoryCardAmount());
		}
		String[] playerStats = new String[] { "" + player.getAmountBuildVillages(), "" + player.getAmountBuildCities(),
				"" + player.getAmountBuildStreets(), "" + player.getHand().getResources().size(),
				"" + player.getHand().getDevelopmentCards().size() };

		for (int i = 0; i < statLabels.length; i++) {
			statLabels[i].setText(playerStats[i]);
		}

		// Check if player haslongestroad or largestarmy
		if (player.getHasLongestRoad()) {
			longestRoadLabel.setVisible(true);
		} else {
			longestRoadLabel.setVisible(false);
		}
		if (player.getHasLargestArmy()) {
			largestArmyLabel.setVisible(true);
		} else {
			largestArmyLabel.setVisible(false);
		}
	}

	// Add image
	private void addImage(JLabel label, int x, int y, String imageName, int sizeX, int sizeY) {
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.gridheight = 3;
		Image image = null;
		try {
			URL url = this.getClass().getResource(imageName);
			image = ImageIO.read(url);
			image = image.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
		ImageIcon imageIcon = new ImageIcon(image);
		label.setIcon(imageIcon);
		add(label, gridBagConstraints);
	}

	public Player getPlayer() {
		return player;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}
}
