package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyResourcesPanel extends JPanel {

	private final int AMOUNT_OF_RESOURCES = 5;
	private final int PANEL_WIDTH = 500;
	private final int PANEL_HEIGHT = 60;

	// Instance variables
	private JLabel[] myResourceIconLabels;
	private JLabel[] myResourceAmountLabels;
	private Color myBackgroundColor = new Color(189, 133, 100);

	// Constructor
	public MyResourcesPanel() { // TODO get resources amount from player
		setLayout(new GridLayout(0, 10));
		setBackground(myBackgroundColor);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

		myResourceIconLabels = new JLabel[AMOUNT_OF_RESOURCES];
		myResourceAmountLabels = new JLabel[AMOUNT_OF_RESOURCES];
		createLabels();
	}
	
	// Create labels
	private void createLabels() {
		String[] locations = new String[] { "/images/Wood_Icon.png", "/images/Rock_Icon.png", "/images/Sheep_Icon.png",
				"/images/Wheat_Icon.png", "/images/Brick_Icon.png" };
		
		for (int i = 0; i < myResourceIconLabels.length; i++) {
			// Get Image
			Image image = null;
			try {
				URL url = this.getClass().getResource(locations[i]);
				image = ImageIO.read(url);
				image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
			} catch (IOException e) {
			}
			myResourceIconLabels[i] = new JLabel(new ImageIcon(image));
			myResourceAmountLabels[i] = new JLabel("0"); // TODO change labeltext to something like // player.getResources[ResourceType]
			myResourceAmountLabels[i].setFont(new Font("Arial", Font.BOLD, 20));
			myResourceAmountLabels[i].setHorizontalAlignment(JLabel.CENTER);
			add(myResourceIconLabels[i]);
			add(myResourceAmountLabels[i]);
		} // TODO update function
	}
}
