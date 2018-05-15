package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BuildPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 220;
	private final int BUTTON_HEIGHT = 70;

	private final int BUTTON_OFFSET_X = 60;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel title;
	private JButton streetButton;
	private JButton villageButton;
	private JButton cityButton;
	private JButton returnButton;

	public BuildPanel() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		title = new JLabel("BOUWEN");
		title.setFont(new Font("SansSerif", Font.BOLD, 35));
		title.setForeground(TextColor);
		title.setBounds(90, 20, 180, 40);
		add(title);

		streetButton = new JButton("Straat");
		streetButton.setBounds(BUTTON_OFFSET_X, 80, BUTTON_WIDTH, BUTTON_HEIGHT);
		streetButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		streetButton.setBackground(textBackgroundColor);
		streetButton.setForeground(TextColor);
		add(streetButton);

		villageButton = new JButton("Dorp");
		villageButton.setBounds(BUTTON_OFFSET_X, 160, BUTTON_WIDTH, BUTTON_HEIGHT);
		villageButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		villageButton.setBackground(textBackgroundColor);
		villageButton.setForeground(TextColor);
		add(villageButton);

		cityButton = new JButton("Stad");
		cityButton.setBounds(BUTTON_OFFSET_X, 240, BUTTON_WIDTH, BUTTON_HEIGHT);
		cityButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		cityButton.setBackground(textBackgroundColor);
		cityButton.setForeground(TextColor);
		add(cityButton);

		returnButton = new JButton("Terugkeren");
		returnButton.setBounds(BUTTON_OFFSET_X, 270, BUTTON_WIDTH, BUTTON_HEIGHT);
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
