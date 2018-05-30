package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BuyPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_SIZE = 150;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JPanel costsPanel;
	private JLabel titleLabel;
	private JLabel subTitleLabel;

	private JLabel[] resourceIconsLabel;
	private JButton yesButton;
	private JButton returnButton;

	public BuyPanel() { // TODO developmentcard costs visually

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);
		createCostsLabel();

		add(costsPanel);

		titleLabel = new JLabel("ONTWIKKELINGSKAART KOPEN");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(10, 10, 320, 40);
		add(titleLabel);

		subTitleLabel = new JLabel("Weet je zeker dat je een ontwikkelingskaart wilt kopen?");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setBounds(10, 60, 320, 40);
		add(subTitleLabel);

		yesButton = new JButton("JA");
		yesButton.setBounds(10, 140, BUTTON_SIZE, BUTTON_SIZE);
		yesButton.setFont(new Font("SansSerif", Font.BOLD, 35));
		yesButton.setBackground(textBackgroundColor);
		yesButton.setForeground(TextColor);
		add(yesButton);

		returnButton = new JButton("NEE");
		returnButton.setBounds(180, 140, BUTTON_SIZE, BUTTON_SIZE);
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 35));
		returnButton.setBackground(textBackgroundColor);
		returnButton.setForeground(TextColor);
		add(returnButton);
	}

	// Create labels
	private void createCostsLabel() {
		costsPanel = new JPanel();
		costsPanel.setBounds(15, 90, 300, 40);
		costsPanel.setBackground(backgroundColor);
		resourceIconsLabel = new JLabel[3];
		
		String[] locations = new String[] { "/images/Rock_Icon.png", "/images/Sheep_Icon.png",
				"/images/Wheat_Icon.png" };

		for (int i = 0; i < resourceIconsLabel.length; i++) {
			// Get Image
			Image image = null;
			try {
				URL url = this.getClass().getResource(locations[i]);
				image = ImageIO.read(url);
				image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			} catch (IOException e) {
			}
			resourceIconsLabel[i] = new JLabel(new ImageIcon(image));
//			resourceIconsLabel[i].setHorizontalAlignment(JLabel.CENTER);
			costsPanel.add(resourceIconsLabel[i]);
		}
	}

	public JButton getYesButton() {
		return yesButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}
}