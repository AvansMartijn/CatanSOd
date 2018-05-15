package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TradePanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 220;
	private final int BUTTON_HEIGHT = 70;

	private final int BUTTON_OFFSET_X = 60;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel title;
	private JLabel subTitle;
	private JButton bankButton;
	private JButton playerButton;
	private JButton returnButton;

	public TradePanel() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		title = new JLabel("HANDELEN");
		title.setFont(new Font("SansSerif", Font.BOLD, 35));
		title.setForeground(TextColor);
		title.setBounds(75, 20, 200, 40);
		add(title);

		subTitle = new JLabel("Handelen met");
		subTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitle.setForeground(TextColor);
		subTitle.setBounds(135, 50, 140, 40);
		add(subTitle);

		bankButton = new JButton("de bank");
		bankButton.setBounds(BUTTON_OFFSET_X, 90, BUTTON_WIDTH, BUTTON_HEIGHT);
		bankButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		bankButton.setBackground(textBackgroundColor);
		bankButton.setForeground(TextColor);
		add(bankButton);

		playerButton = new JButton("een andere speler");
		playerButton.setBounds(BUTTON_OFFSET_X, 170, BUTTON_WIDTH, BUTTON_HEIGHT);
		playerButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		playerButton.setBackground(textBackgroundColor);
		playerButton.setForeground(TextColor);
		add(playerButton);

		returnButton = new JButton("Terugkeren");
		returnButton.setBounds(BUTTON_OFFSET_X, 270, BUTTON_WIDTH, BUTTON_HEIGHT);
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		returnButton.setBackground(textBackgroundColor);
		returnButton.setForeground(TextColor);
		add(returnButton);

	}

	public JButton getBankButton() {
		return bankButton;
	}

	public JButton getPlayerButton() {
		return playerButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}

}
