package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Player;

@SuppressWarnings("serial")
public class RobberDialogPanel extends JPanel {

	private final int PANEL_WIDTH = 260;
	private final int PANEL_HEIGHT = 260;

	private final Dimension buttonDimension = new Dimension(220, 50);
	private final Border buttonBorder = BorderFactory.createEmptyBorder(10, 50, 10, 50);

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel;

	private ArrayList<JButton> playerButtons;

	public RobberDialogPanel(ArrayList<Player> playersToRob) {
		
		ArrayList<Player> players = playersToRob;
		
		System.out.println(players);

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(new FlowLayout());

		titleLabel = new JLabel("STRUIKROVER");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		titleLabel.setForeground(TextColor);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);

		subTitleLabel = new JLabel("Van wie wil je grondstoffen stelen?");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		subTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(subTitleLabel);

		playerButtons = new ArrayList<>();

		for (int i = 0; i < players.size(); i++) {
			
			JButton playerButton = new JButton(players.get(i).getUsername());
			playerButton.setFont(new Font("SansSerif", Font.BOLD, 20));
			playerButton.setBackground(textBackgroundColor);
			playerButton.setForeground(TextColor);
			playerButton.setHorizontalAlignment(JLabel.CENTER);
			playerButton.setBorder(buttonBorder);
			playerButton.setSize(buttonDimension);
			playerButton.setEnabled(false);
			playerButtons.add(playerButton);
		}
	}

	public JButton getPlayerButton(int amount) {

		return playerButtons.get(amount);

	}
}