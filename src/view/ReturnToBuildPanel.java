package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ReturnToBuildPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_SIZE = 160;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel title;
	private JLabel subTitleLabel;

	private JButton returnButton;

	public ReturnToBuildPanel() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		title = new JLabel("BOUWEN");
		title.setFont(new Font("SansSerif", Font.BOLD, 35));
		title.setForeground(TextColor);
		title.setBounds(90, 20, 180, 40);
		add(title);
		
		subTitleLabel = new JLabel("Selecteer een bouwlocatie");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setBounds(90, 60, 320, 40);
		add(subTitleLabel);

		returnButton = new JButton("TERUG");
		returnButton.setBounds(90, 140, BUTTON_SIZE, BUTTON_SIZE);
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 25));
		returnButton.setBackground(textBackgroundColor);
		returnButton.setForeground(TextColor);
		add(returnButton);
	}

	public JButton getReturnButton() {
		return returnButton;
	}
}

