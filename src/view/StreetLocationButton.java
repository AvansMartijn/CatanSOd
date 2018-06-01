package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.border.Border;

import model.StreetLocation;

@SuppressWarnings("serial")
public class StreetLocationButton extends JButton {
	private StreetLocation streetLocation;
	private int rotation;

	// TODO: Combineren van de code

	public StreetLocationButton(StreetLocation strLoc, int rotation) {
		this.streetLocation = strLoc;
		this.rotation = rotation;
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setBackground(Color.BLACK);

	}

	int n = 4;
	int x[] = new int[n];
	int y[] = new int[n];
	double angle = 2 * Math.PI / n;

	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(getBackground());
		}
		int x0 = getSize().width / 2;
		int y0 = getSize().height / 2;
		for (int i = 0; i < n; i++) {
			double v = i * angle;
			x[i] = x0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
			y[i] = y0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
		}
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.fillPolygon(x, y, n);
		super.paintComponent(graphics2d);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		int x0 = getSize().width / 2;
		int y0 = getSize().height / 2;
		for (int i = 0; i < n; i++) {
			double v = i * angle;
			x[i] = x0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
			y[i] = y0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
		}
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.drawPolygon(x, y, n);
	}

	Polygon polygon;

	public boolean contains(int x1, int y1) {
		if (polygon == null || !polygon.getBounds().equals(getBounds())) {
			int x0 = getSize().width / 2;
			int y0 = getSize().height / 2;
			for (int i = 0; i < n; i++) {
				double v = i * angle;
				x[i] = x0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
				y[i] = y0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
			}
			polygon = new Polygon(x, y, n);
		}
		return polygon.contains(x1, y1);
	}

	public StreetLocation getStreetLocation() {
		return streetLocation;
	}
}