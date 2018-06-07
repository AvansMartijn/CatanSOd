package view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class RecentGamesTopPanel extends JPanel {

	private JButton createGameButton, inviteButton;
	private JRadioButton recentButton, closedGameButton;
	private Color backgroundColor = new Color(189, 133, 100);

	public RecentGamesTopPanel() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		setBackground(backgroundColor);
		
		createGameButton = new JButton("Spel aanmaken");
		add(createGameButton);

		inviteButton = new JButton("Uitnodigingen");
		add(inviteButton);

		ButtonGroup group = new ButtonGroup();

		recentButton = new JRadioButton("Huidige");
		recentButton.setBackground(backgroundColor);
		group.add(recentButton);

		closedGameButton = new JRadioButton("Afgelopen");
		closedGameButton.setBackground(backgroundColor);
		group.add(closedGameButton);

		add(recentButton);
		add(closedGameButton);
	}

	public JButton getCreateGameButton() {
		return createGameButton;
	}

	public JButton getInviteButton() {
		return inviteButton;
	}

	public JRadioButton getRecentButton() {
		return recentButton;
	}

	public JRadioButton getClosedGameButton() {
		return closedGameButton;
	}
}
