package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Dice;

@SuppressWarnings("serial")
public class DicePanel extends JPanel {
	
	private Dice dice;

	public DicePanel() {
		setPreferredSize(new Dimension(100, 100));
		setBorder(new LineBorder(Color.RED, 5, true));
		setBackground(Color.RED);
		// TODO switch case with image or something
	}
}
