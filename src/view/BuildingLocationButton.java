package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JButton;

import model.BuildingLocation;

@SuppressWarnings("serial")
public class BuildingLocationButton extends JButton {
	private BuildingLocation buildingLocation;
	private int n = 4;
	private int x[] = new int[n];
	private int y[] = new int[n];
	private double angle = 2 * Math.PI / n;
	//if false state is village, if true state is city
	private boolean state;

	

	public BuildingLocationButton(BuildingLocation buildLoc) {
		this.buildingLocation = buildLoc;
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setBackground(Color.black);
	}

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
		g.fillPolygon(x, y, n);
		
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(Color.BLACK);
		int x0 = getSize().width / 2;
		int y0 = getSize().height / 2;
		for (int i = 0; i < n; i++) {
			double v = i * angle;
			x[i] = x0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
			y[i] = y0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
		}
		
		g.drawPolygon(x, y, n);		
		if(buildingLocation.getCity() != null) {
			setCityBorder(g);
		}
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

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}
	
	public void setCityBorder(Graphics g) {
		g.setColor(Color.BLACK);
		int x0 = getSize().width / 2;
		int y0 = getSize().height / 2;
		for (int i = 0; i < n; i++) {
			double v = i * angle;
			x[i] = x0 + (int) Math.round((getWidth() / 4) * Math.cos(v));
			y[i] = y0 + (int) Math.round((getHeight() / 4) * Math.sin(v));
		}
		g.drawPolygon(x, y, n);
		g.fillPolygon(x, y, n);
	}
	
	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
