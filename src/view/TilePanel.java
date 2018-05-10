package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class TilePanel extends JButton {
	private static final long serialVersionUID = 1L;
	private static final int SIDES = 6;
	private static final int SIDE_LENGTH = 50;
	public static final int LENGTH = 150;
	public static final int WIDTH = 130;
	private int row = 0;
	private int col = 0;

	public TilePanel(int row, int col) {
		setContentAreaFilled(false);
		setFocusPainted(true);
		setBorderPainted(false);
		setPreferredSize(new Dimension(WIDTH, LENGTH));
		this.row = row;
		this.col = col;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Polygon hex = new Polygon();
		for (int i = 0; i < SIDES; i++) {
			hex.addPoint((int) (50 + SIDE_LENGTH * Math.cos(i * 2 * Math.PI / SIDES)), // calculation for side
					(int) (50 + SIDE_LENGTH * Math.sin(i * 2 * Math.PI / SIDES))); // calculation for side
		}
		g.drawPolygon(hex);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

}
