package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TradeOptionsPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 220;
	private final int BUTTON_HEIGHT = 50;
	private final int BUTTON_OFFSET_X = 60;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel;

	private JButton bankButton, playerButton, backButton;

	public TradeOptionsPanel() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		titleLabel = new JLabel("HANDELEN");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(80, 20, 200, 40);

		add(titleLabel);

		subTitleLabel = new JLabel("handelen met");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setBounds(130, 70, 220, 40);

		add(subTitleLabel);

		bankButton = new JButton("Bank");
		bankButton.setBounds(BUTTON_OFFSET_X, 140, BUTTON_WIDTH, BUTTON_HEIGHT);
		bankButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		bankButton.setBackground(textBackgroundColor);
		bankButton.setForeground(TextColor);
		add(bankButton);

		playerButton = new JButton("Speler");
		playerButton.setBounds(BUTTON_OFFSET_X, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		playerButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		playerButton.setBackground(textBackgroundColor);
		playerButton.setForeground(TextColor);
		add(playerButton);
		
		backButton = new JButton("Terug");
		backButton.setBounds(BUTTON_OFFSET_X, 260, BUTTON_WIDTH, BUTTON_HEIGHT);
		backButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		backButton.setBackground(textBackgroundColor);
		backButton.setForeground(TextColor);
		add(backButton);

	}

	public JButton getBankButton() {
		return bankButton;
	}

	public void setBankButton(JButton bankButton) {
		this.bankButton = bankButton;
	}

	public JButton getPlayerButton() {
		return playerButton;
	}

	public void setPlayerButton(JButton playerButton) {
		this.playerButton = playerButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
	
}
