package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class DicePanel extends JPanel {
	
	private final int PREF_WIDTH = 250;
	private final int PREF_HEIGHT = 250;
	private final int ARC = 20;
	private final int SQUARE = 100;
	private JButton rollButton;
	
	
	// instance variables

	// constructor
	public DicePanel() {
		setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		rollButton = new JButton();
		rollButton.setText("Gooi");
		this.add(rollButton);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRoundRect(20, 70, SQUARE, SQUARE, ARC, ARC);
		g.fillRoundRect(130, 70, SQUARE, SQUARE, ARC, ARC);
	}
	
	public JButton getButton() {
		return this.rollButton;
	}
	
}