package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Tile;

@SuppressWarnings("serial")
public class TileButton extends JButton {
	private Tile tile;
	private int n = 6;
	private int x[] = new int[n];
	private int y[] = new int[n];
	private int tileNumberWidthHeight = 35;
	private int robberWidthHeight = 50;
	private int robberX = 20;
	private int robberY = 35;
	private int tileNumberX = 100;
	private int tileNumberY = 82;
	private double angle = 2 * Math.PI / n;
	private int tileNumber;

	public TileButton(ImageIcon imageIcon, int tileNumber, Tile tile) {
		this.tileNumber = tileNumber;
		this.tile = tile;
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setIcon(imageIcon);
		setDisabledIcon(imageIcon);
	}

	protected void paintComponent(Graphics g) {
		int x0 = getSize().width / 2;
		int y0 = getSize().height / 2;
		for (int i = 0; i < n; i++) {
			double v = i * angle;
			x[i] = x0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
			y[i] = y0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
		}
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillPolygon(x, y, n);
		super.paintComponent(g);
		if(tile.hasRobber()) {
			g.setColor(Color.BLACK);
			g.fillOval(robberX, robberY, robberWidthHeight, robberWidthHeight);
			g.setColor(Color.WHITE);
			g.drawOval(robberX, robberY, robberWidthHeight, robberWidthHeight);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
			g.drawString("R", robberX + 11, robberY + 40);
			
		}
		if(tileNumber >= 1) {
			g.setColor(Color.WHITE);
			g.fillOval(tileNumberX, tileNumberY, tileNumberWidthHeight, tileNumberWidthHeight);
			if(tileNumber == 6 || tileNumber == 8) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawOval(tileNumberX, tileNumberY, tileNumberWidthHeight, tileNumberWidthHeight);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			if(tileNumber >= 10) {
				g.drawString("" + tileNumber, tileNumberX + 7, tileNumberY + 25);
			} else {
				g.drawString("" + tileNumber, tileNumberX + 12, tileNumberY + 25);
			}
		}
	}

	protected void paintBorder(Graphics g) {
		g.setColor(Color.RED);
		int x0 = getSize().width / 2;
		int y0 = getSize().height / 2;
		for (int i = 0; i < n; i++) {
			double v = i * angle;
			x[i] = x0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
			y[i] = y0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
		}
		g.drawPolygon(x, y, n);
	}
	Polygon polygon;

	public boolean contains(int x1, int y1) {
		if (polygon == null || !polygon.getBounds().equals(getBounds())) {
			int x0 = getSize().width / 2;
			int y0 = getSize().height / 2;
			for (int i = 0; i < n; i++) {
				double v = i * angle;
				x[i] = x0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
				y[i] = y0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
			}
			polygon = new Polygon(x, y, n);
		}
		return polygon.contains(x1, y1);
	}

	public Tile getTile() {
		return tile;
	}

}
