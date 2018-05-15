package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GameSelect extends JPanel {

	private JButton standardGameButton;
	private JButton randomGameButton;
	
	private JLabel standardGameLabel;
	private JLabel randomGameLabel;

	private JLabel instructionLabel;
	private JLabel title;

	public GameSelect() {

		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

		GridBagLayout grid = new GridBagLayout();
		setLayout(grid);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;

		CenterPanel center = new CenterPanel();

		grid.setConstraints(center, constraints);
		center.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		add(center, constraints);
	}

	public class CenterPanel extends JPanel {

		public CenterPanel() {

			setPreferredSize(new Dimension(390, 230));

		

			setBorder(new EmptyBorder(15, 15, 15, 15));

			title = new JLabel("Bord Keuze");
			title.setFont(new Font("SansSerif", Font.BOLD, 25));
			title.setBorder(new EmptyBorder(2, 35, 2, 35));
			add(title);

			instructionLabel = new JLabel("Selecteer een bordoptie");
			instructionLabel.setBorder(new EmptyBorder(2, 20, 2, 20));
			add(instructionLabel);

			standardGameLabel = new JLabel("Een speelbord met de standaard geplaatste landtegels en fiches");
			standardGameLabel.setBorder(new EmptyBorder(15, 10, 2, 2));
			add(standardGameLabel);

			standardGameButton = new JButton("Standaard Spel");
			add(standardGameButton);

			randomGameLabel = new JLabel("Een speelbord met willekeurig geplaatste landtegels en fiches");
			randomGameLabel.setBorder(new EmptyBorder(25, 10, 2, 2));
			add(randomGameLabel);

			randomGameButton = new JButton("Willekeurig Spel");
			add(randomGameButton);

			setBorder(new EmptyBorder(15, 15, 15, 15));

		}

		public JButton getStandardGameButton() {
			return standardGameButton;
		}

		public JButton getRandomGameButton() {
			return randomGameButton;
		}
	}
}
