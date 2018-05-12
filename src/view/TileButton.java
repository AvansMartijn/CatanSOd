import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class TileButton extends JButton {
	private int n = 6;
	private int x[] = new int[n];
	private int y[] = new int[n];
	private int tileNumberWidthHeight = 35;
	int tileNumberX = 100;
	int tileNumberY = 82;
	private double angle = 2 * Math.PI / n;
	private int tileNumber;
	
	public TileButton(ImageIcon imageIcon, int tileNumber) {
		this.tileNumber = tileNumber;
		setContentAreaFilled(false);
		 setFocusPainted(false);
		 setBorderPainted(false);
//		 setPreferredSize(new Dimension(WIDTH, LENGTH));
		 setIcon(imageIcon);
		setDisabledIcon(imageIcon);

//		 setBackground(Color.black);
	}

	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
//			g.setColor(Color.lightGray);
		} else {
//			g.setColor(getBackground());
		}
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
		g.setColor(Color.WHITE);
		g.fillOval(tileNumberX, tileNumberY, tileNumberWidthHeight, tileNumberWidthHeight);
		g.setColor(Color.BLACK);
		g.drawOval(tileNumberX, tileNumberY, tileNumberWidthHeight, tileNumberWidthHeight);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString("" + tileNumber, tileNumberX + 12, tileNumberY + 25);
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
	// private static final int SIDES = 6;
	// private static final int SIDE_LENGTH = 60;
	// public static final int LENGTH = 150;
	// public static final int WIDTH = 150;
	//
	// public HexagonButton(Image image) {
	// setContentAreaFilled(false);
	// setFocusPainted(true);
	// setBorderPainted(false);
	// setPreferredSize(new Dimension(WIDTH, LENGTH));
	//// setIcon(new ImageIcon(image));
	// setBackground(Color.black);
	//
	// }
	//
	// @Override
	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// Polygon hex = new Polygon();
	// for (int i = 0; i < SIDES; i++) {
	// hex.addPoint((int) (50 + SIDE_LENGTH * Math.sin(i * 2 * Math.PI / SIDES)),
	// (int) (50 + SIDE_LENGTH * Math.cos(i * 2 * Math.PI / SIDES))) // calculation
	// for side
	// ; // calculation for side
	// }
	// g.drawPolygon(hex);
	// }

}
