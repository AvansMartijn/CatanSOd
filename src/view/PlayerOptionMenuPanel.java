package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerOptionMenuPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	// Instance variables
	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);

	private ArrayList<JButton> playerActionButtons; // TODO make it an normal array size 4

	public PlayerOptionMenuPanel() {

		// add current one
		setBackground(backgroundColor);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		playerActionButtons = new ArrayList<>();

		constraints.weightx = 1;
		constraints.weighty = 0.25;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.BOTH;

		// Create objects, because creating them once is better than 4 times
		String[] buttonNames = new String[] { "Ontwikkelingskaart kopen", "Handelen", "Bouwen", "Eindig Beurt" };
		JButton actionButton = null;
		Insets insets = new Insets(4, 0, 4, 0);
		Font font = new Font("Arial", Font.BOLD, 16);

		for (int i = 0; i < buttonNames.length; i++) {
			playerActionButtons.add(new JButton(buttonNames[i]));
			actionButton = playerActionButtons.get(i);

			actionButton.setBackground(textBackgroundColor);
			actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			actionButton.setMargin(insets);
			actionButton.setFont(font);
		}

		add(playerActionButtons.get(0), constraints);
		add(playerActionButtons.get(1), constraints);
		add(playerActionButtons.get(2), constraints);
		constraints.insets = new Insets(50, 10, 10, 10);
		add(playerActionButtons.get(3), constraints);
	}

	public JButton getBuyButton() {
		return playerActionButtons.get(0);
	}

	public JButton getTradeButton() {
		return playerActionButtons.get(1);
	}

	public JButton getBuildButton() {
		return playerActionButtons.get(2);
	}

	public JButton getEndTurnButton() {
		return playerActionButtons.get(3);
	}
}
