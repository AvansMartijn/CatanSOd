package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Catan;
import model.PlayStatus;
import model.Player;

@SuppressWarnings("serial")
public class RecentGamePanel extends JPanel {
	
	private Catan game;
	private Color backgroundColor = new Color(240, 226, 223);
	private Image image;

	public RecentGamePanel(Catan game) { // TODO optimize by putting every label in an array and then a for loop
		this.game = game;
		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(500, 100));
		GridBagConstraints c = new GridBagConstraints();

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(backgroundColor);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 300, 0, 10);
		c.gridx = 5;
		c.gridy = 1;
		JLabel label = new JLabel("Spel ID: " + game.getIdGame());
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(label, c);

		ArrayList<Player> playerArray = game.getPlayers();
		String[] displayStringArr = new String[4];
		String turnUsername = "";
		for (Player p : playerArray) {
			if (p.getIdPlayer() == game.getTurn()) {
				turnUsername = p.getUsername();
			}
			displayStringArr[p.getFollownr() - 1] = String.format("%s) %s: %s ", p.getFollownr(), p.getUsername(), p.getPlayStatus());
		}
		
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 10, 0, 300);
		c.gridy = 2;
		for(int i = 0; i < displayStringArr.length; i++) {
			label = new  JLabel(displayStringArr[i]);
			label.setForeground(Color.WHITE);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			this.add(label, c);
			c.gridy++;
		}
		c.insets = new Insets(0, 300, 0, 10);
		c.gridx = 5;
		c.anchor = GridBagConstraints.EAST;

		label = new JLabel(turnUsername + " is aan de beurt!");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.WHITE);
		this.add(label, c);
		
		URL url = null;
		if(playerArray.get(0).getPlayStatus().equals(PlayStatus.UITGESPEELD)) { // TODO test if this works
			url = this.getClass().getResource("/images/GamePanelFinished.png");
		} else {
			url = this.getClass().getResource("/images/GamePanel.png");
		}
		
		try {
			image = ImageIO.read(url);
			image = image.getScaledInstance((int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight(),
					Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public Catan getGame() {
		return game;
	}
}
