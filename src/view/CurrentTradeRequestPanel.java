package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import model.Player;

@SuppressWarnings("serial")
public class CurrentTradeRequestPanel extends JPanel {
	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 220;
	private final int BUTTON_HEIGHT = 50;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel;
	

	private JButton requestButton,returnButton;

	public CurrentTradeRequestPanel() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		titleLabel = new JLabel("Handelsverzoeken");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(10, 10, 320, 40);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);

		subTitleLabel = new JLabel("selecteer een handelsverzoek");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setBounds(30, 40, 280, 40);
		subTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(subTitleLabel);

		requestButton = new JButton("handelsverzoek bekijken");
		requestButton.setBounds(60, 130, BUTTON_WIDTH, BUTTON_HEIGHT);
		requestButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		requestButton.setBackground(textBackgroundColor);
		requestButton.setForeground(TextColor);
		add(requestButton);

		returnButton = new JButton("terug");
		returnButton.setBounds(60, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		returnButton.setBackground(textBackgroundColor);
		returnButton.setForeground(TextColor);
		add(returnButton);

	}	
	
	public JButton getReturnButton() {

		return returnButton;

	}
}
