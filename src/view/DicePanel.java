package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Dice;

@SuppressWarnings("serial")
public class DicePanel extends JPanel {
	
	private final int PREF_WIDTH = 250;
	private final int PREF_HEIGHT = 250;
	private final int ARC = 20;
	private final int SQUARE = 100;
	
	
	// instance variables
	protected Dice model;

	// constructor
	public DicePanel(Dice model)
	{
		this.model = model;
		setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRoundRect(20, 70, SQUARE, SQUARE, ARC, ARC);
		g.fillRoundRect(130, 70, SQUARE, SQUARE, ARC, ARC);
	}
}