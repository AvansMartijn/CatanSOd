package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BuildPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_OFFSET_X = 60;
	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);
	private JLabel title, subTitleLabel;
	private JButton streetButton, villageButton, cityButton, returnButton;

	public BuildPanel() {
		Image straat = null;
		Image dorp = null;
		Image stad = null;
		try {
			straat = ImageIO.read(getClass().getResource("/images/IMG_1263+straat+2-tone-enhance (1).png"));
			dorp = ImageIO.read(getClass().getResource("/images/IMG_1263 dorp 1.png"));
			stad = ImageIO.read(getClass().getResource("/images/IMG_1263 stad 1.png"));
		} catch (IOException e) {
		}
		ImageIcon street = new ImageIcon(straat);
		ImageIcon villages = new ImageIcon(dorp);
		ImageIcon citys = new ImageIcon(stad);

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);
		setLayout(null);

		title = new JLabel("BOUWEN");
		title.setFont(new Font("SansSerif", Font.BOLD, 35));
		title.setForeground(TextColor);
		title.setBounds(90, 20, 180, 40);
		add(title);

		subTitleLabel = new JLabel("Wat wil je bouwen?");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setBounds(110, 50, 320, 40);
		add(subTitleLabel);

		streetButton = new JButton();
		streetButton.setIcon(street);
		streetButton.setBounds(BUTTON_OFFSET_X, 100, street.getIconWidth(), street.getIconHeight());
		add(streetButton);

		villageButton = new JButton();
		villageButton.setIcon(villages);
		villageButton.setBounds(BUTTON_OFFSET_X, 160, villages.getIconWidth(), villages.getIconHeight());
		add(villageButton);

		cityButton = new JButton();
		cityButton.setIcon(citys);
		cityButton.setBounds(BUTTON_OFFSET_X, 220, citys.getIconWidth(), citys.getIconHeight());
		add(cityButton);

		returnButton = new JButton("TERUG");
		returnButton.setBounds(BUTTON_OFFSET_X, 280, citys.getIconWidth(), citys.getIconHeight());
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		returnButton.setBackground(textBackgroundColor);
		returnButton.setForeground(TextColor);
		add(returnButton);

	}

	public JButton getStreetButton() {
		return streetButton;
	}

	public JButton getVillageButton() {
		return villageButton;
	}

	public JButton getCityButton() {
		return cityButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}

}
