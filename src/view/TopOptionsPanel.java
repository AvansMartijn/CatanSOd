package view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class TopOptionsPanel extends JPanel {

	private JButton createGameButton;
	private JButton inviteButton;

	private JRadioButton recentButton;
	private JRadioButton closedGameButton;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color buttonBackgroundColor = new Color(223, 190, 172);

	public TopOptionsPanel() {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		setBackground(backgroundColor);
		
		createGameButton = new JButton("Game aanmaken");
		createGameButton.setBackground(buttonBackgroundColor);
		add(createGameButton);

		inviteButton = new JButton("Uitnodigingen");
		inviteButton.setBackground(buttonBackgroundColor);
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
