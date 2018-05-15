package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GameSelect extends JPanel {

	private JRadioButton standardGameButton;
	private JRadioButton randomGameButton;
	private JButton createNewGameButton;
	private JLabel warningLabel;
	private JLabel instructionLabel;

	public GameSelect() {

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		setBorder(new EmptyBorder(15, 15, 15, 15));

		instructionLabel = new JLabel("Selecteer een bordoptie");
		add(instructionLabel);
		
		final ButtonGroup entreeGroup = new ButtonGroup();

		JRadioButton standardGameButton = new JRadioButton("Standaard Spel", true);
		add(standardGameButton);
		entreeGroup.add(standardGameButton);

		JLabel standardGameLabel = new JLabel(
				"Speel een potje Kolonisten van Catan op met de standaard geplaatste landtegels en fiches");
		add(standardGameLabel);

		JRadioButton randomGameButton = new JRadioButton("Willekeurig Spel");
		add(randomGameButton);
		entreeGroup.add(randomGameButton);

		JLabel randomGameLabel = new JLabel(
				"Speel een potje Kolonisten van Catan op speelbord met willekeurig geplaatste landtegels en fiches");
		add(randomGameLabel);

		JButton createNewGameButton = new JButton("Maak bord aan");
		add(createNewGameButton, BorderLayout.CENTER);

		warningLabel = new JLabel("");
		warningLabel.setForeground(Color.RED);
		add(warningLabel);
		
	}

	public JRadioButton getStandardGameButton() {
		return standardGameButton;
	}

	public JRadioButton getRandomGameButton() {
		return randomGameButton;
	}

	public JButton getCreateNewGameButton() {
		return createNewGameButton;
	}

	public JLabel getWarningLabel() {
		return warningLabel;
	}
	
}
