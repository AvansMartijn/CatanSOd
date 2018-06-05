package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectBoardPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 220;
	private final int BUTTON_HEIGHT = 70;

	private final int BUTTON_OFFSET_X = 60;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel;
	private JLabel subTitleLabel1;
	private JLabel subTitleLabel2;

	private JButton standardButton;
	private JButton randomButton;

	public SelectBoardPanel() {

		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

		GridBagLayout grid = new GridBagLayout();
		setLayout(grid);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;

		CenterPanel center = new CenterPanel();

		grid.setConstraints(center, constraints);
		add(center, constraints);

	}

	public JButton getInlogButton() {
		return standardButton;
	}

	public JButton getRegisterButton() {
		return randomButton;
	}

	public class CenterPanel extends JPanel {

		public CenterPanel() {

			setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			setBackground(backgroundColor);

			setLayout(null);

			titleLabel = new JLabel("BORDSELECTIE");
			titleLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
			titleLabel.setForeground(TextColor);
			titleLabel.setBounds(40, 20, 280, 40);
			add(titleLabel);

			subTitleLabel1 = new JLabel(
					"<html><p>Een bord met de standaard geplaatste \n" + "grondtegels en getalfiches<p></html>");
			subTitleLabel1.setFont(new Font("SansSerif", Font.BOLD, 12));
			subTitleLabel1.setForeground(TextColor);
			subTitleLabel1.setBounds(60, 50, 250, 60);
			add(subTitleLabel1);

			standardButton = new JButton("Standaard");
			standardButton.setBounds(BUTTON_OFFSET_X, 100, BUTTON_WIDTH, BUTTON_HEIGHT);
			standardButton.setFont(new Font("SansSerif", Font.BOLD, 20));
			standardButton.setBackground(textBackgroundColor);
			standardButton.setForeground(TextColor);
			add(standardButton);

			subTitleLabel2 = new JLabel(
					"<html><p>Een speelbord met willekeurig geplaatste \n" + "grondtegels en getalfiches<p></html>");
			subTitleLabel2.setFont(new Font("SansSerif", Font.BOLD, 12));
			subTitleLabel2.setForeground(TextColor);
			subTitleLabel2.setBounds(60, 180, 250, 60);
			add(subTitleLabel2);

			randomButton = new JButton("Willekeurig");
			randomButton.setBounds(BUTTON_OFFSET_X, 230, BUTTON_WIDTH, BUTTON_HEIGHT);
			randomButton.setFont(new Font("SansSerif", Font.BOLD, 20));
			randomButton.setBackground(textBackgroundColor);
			randomButton.setForeground(TextColor);
			add(randomButton);
		}
	}
}
