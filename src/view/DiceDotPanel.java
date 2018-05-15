package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import model.Dice;

/**
 * @author Ger Saris
 * @version 1
 * @date 13-03-2018
 */
@SuppressWarnings("serial")
public class DiceDotPanel extends DicePanel
{
	// constants
	private final Color customBrown = new Color(223, 190, 172);
	private final int DOT = 20;
	private int[] lastThrown;

	// constructor
	public DiceDotPanel(){
		setBackground(customBrown);
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g); // calls MyPanel (is super here)
		
		int d1;
		int d2;
		g.setColor(Color.WHITE);

		// om nette scherpe randjes te krijgen - begin //
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		// om nette scherpe randjes te krijgen - einde //
		
		// stippen tekenen
		d1 = lastThrown[0];
		d2 = lastThrown[1];
		switch (d1)
		{
		case 1:
			g.fillOval(60, 110, DOT, DOT);
			break;
		case 2:
			g.fillOval(60, 80, DOT, DOT);
			g.fillOval(60, 140, DOT, DOT);
			break;
		case 3:
			g.fillOval(30, 80, DOT, DOT);
			g.fillOval(60, 110, DOT, DOT);
			g.fillOval(90, 140, DOT, DOT);
			break;
		case 4:
			g.fillOval(30, 80, DOT, DOT);
			g.fillOval(90, 80, DOT, DOT);
			g.fillOval(30, 140, DOT, DOT);
			g.fillOval(90, 140, DOT, DOT);
			break;
		case 5:
			g.fillOval(30, 80, DOT, DOT);
			g.fillOval(90, 80, DOT, DOT);
			g.fillOval(60, 110, DOT, DOT);
			g.fillOval(30, 140, DOT, DOT);
			g.fillOval(90, 140, DOT, DOT);
			break;
		case 6:
			g.fillOval(30, 80, DOT, DOT);
			g.fillOval(60, 80, DOT, DOT);
			g.fillOval(90, 80, DOT, DOT);
			g.fillOval(30, 140, DOT, DOT);
			g.fillOval(60, 140, DOT, DOT);
			g.fillOval(90, 140, DOT, DOT);
			break;
		}

		switch (d2)
		{
		case 1:
			g.fillOval(170, 110, DOT, DOT);
			break;
		case 2:
			g.fillOval(170, 80, DOT, DOT);
			g.fillOval(170, 140, DOT, DOT);
			break;
		case 3:
			g.fillOval(140, 80, DOT, DOT);
			g.fillOval(170, 110, DOT, DOT);
			g.fillOval(200, 140, DOT, DOT);
			break;
		case 4:
			g.fillOval(140, 80, DOT, DOT);
			g.fillOval(200, 80, DOT, DOT);
			g.fillOval(140, 140, DOT, DOT);
			g.fillOval(200, 140, DOT, DOT);
			break;
		case 5:
			g.fillOval(140, 80, DOT, DOT);
			g.fillOval(200, 80, DOT, DOT);
			g.fillOval(170, 110, DOT, DOT);
			g.fillOval(140, 140, DOT, DOT);
			g.fillOval(200, 140, DOT, DOT);
			break;
		case 6:
			g.fillOval(140, 80, DOT, DOT);
			g.fillOval(170, 80, DOT, DOT);
			g.fillOval(200, 80, DOT, DOT);
			g.fillOval(140, 140, DOT, DOT);
			g.fillOval(170, 140, DOT, DOT);
			g.fillOval(200, 140, DOT, DOT);
			break;
		}
		
	}

	public int[] getLastThrown() {
		return lastThrown;
	}

	public void setLastThrown(int[] lastThrown) {
		this.lastThrown = lastThrown;
	}
	
}