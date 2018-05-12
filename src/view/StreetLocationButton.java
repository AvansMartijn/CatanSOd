package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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
	
	//TODO: Combineren van de code

	public StreetLocationButton(StreetLocation strLoc) {
		this.streetLocation = strLoc;
//		setContentAreaFilled(true);
//		setFocusPainted(false);
//		setBorderPainted(false);

	}

	protected double angle = 45;

	public StreetLocationButton() {
	}

	public void paint(Graphics g) {

		Point oldCenter = new Point(super.getPreferredSize().width / 2, super.getPreferredSize().height / 2);
		Point bigCenter = new Point(getLargest() / 2, getLargest() / 2);
		Point newCenter = new Point(bigCenter.x - oldCenter.x, bigCenter.y - oldCenter.y);

		Image oldImage = createImage(getLargest(), getLargest());
		Graphics oldG = oldImage.getGraphics();
		oldG.setClip(newCenter.x, newCenter.y, super.getPreferredSize().width, super.getPreferredSize().height);

		Image newImage = createImage(getLargest(), getLargest());
		Graphics newG = newImage.getGraphics();

		super.paint(oldG);

		newG.drawImage(oldImage, 0, 0, this);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getParent().getBackground());
		g2.fillRect(0, 0, getLargest(), getLargest());
		g2.rotate(angle, getPreferredSize().width / 2, getPreferredSize().height / 2);
		g2.drawImage(newImage, 0, 0, this);
	}

	protected void paintBorder(Graphics g) {
		if (isBorderPainted()) {
			Border border = getBorder();
			if (border != null) {
				Point oldCenter = new Point(super.getPreferredSize().width / 2, super.getPreferredSize().height / 2);
				Point bigCenter = new Point(getLargest() / 2, getLargest() / 2);
				Point newCenter = new Point(bigCenter.x - oldCenter.x, bigCenter.y - oldCenter.y);
				border.paintBorder(this, g, newCenter.x, newCenter.y, super.getPreferredSize().width,
						super.getPreferredSize().height);
			}
		}
	}
	
	Shape shape;

	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
		}
		return shape.contains(x, y);
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		repaint();
	}

	public Dimension getPreferredSize() {
		Dimension d = new Dimension(getLargest(), getLargest());
		return d;
	}

	protected int getLargest() {
		int w = super.getPreferredSize().width;
		int h = super.getPreferredSize().height;

		if (w > h) {
			return w;
		} else {
			return h;
		}
	}

}

	// protected void paintComponent(Graphics g) {
	// if (getModel().isArmed()) {
	// g.setColor(Color.lightGray);
	// } else {
	// g.setColor(getBackground());
	// }
	// Graphics2D graphics2d = (Graphics2D) g;
	// graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	// RenderingHints.VALUE_ANTIALIAS_ON);
	// g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	// super.paintComponent(g);
	// }
	//
	// protected void paintBorder(Graphics g) {
	// g.setColor(getForeground());
	// Graphics2D graphics2d = (Graphics2D) g;
	// graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	// RenderingHints.VALUE_ANTIALIAS_ON);
	// g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	// }
	//
	// Shape shape;
	// public boolean contains(int x, int y) {
	// if (shape == null || !shape.getBounds().equals(getBounds())) {
	// shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15,
	// 15);
	// }
	// return shape.contains(x, y);
	// }

