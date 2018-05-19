package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Catan;
import model.Player;

@SuppressWarnings("serial")
public class RecentGamePanel extends JPanel {
	private Catan game;
	private Color backgroundColor = new Color(240, 226, 223);

	public RecentGamePanel(Catan game) {
		this.game = game;
		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(400, 100));
		GridBagConstraints c = new GridBagConstraints();

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(backgroundColor);
		c.gridx = 1;
		c.gridy = 1;
//		c.weightx = 1;
		JLabel label = new JLabel("Spel ID: " + game.getIdGame());
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
		c.gridx = 0;
		c.gridy = 2;

		this.add(new JLabel(displayStringArr[0]), c);
		c.gridx = 0;
		c.gridy = 3;
//		c.gridwidth = 1;
//		c.gridheight = 1;
		this.add(new JLabel(displayStringArr[1]), c);
		c.gridx = 0;
		c.gridy = 4;
//		c.gridwidth = 1;
//		c.gridheight = 1;
		this.add(new JLabel(displayStringArr[2]), c);
		c.gridx = 0;
		c.gridy = 5;
//		c.gridwidth = 1;
//		c.gridheight = 1;
		this.add(new JLabel(displayStringArr[3]), c);
		
//		c.ipady = 10;
//		c.gridx = 0;
//		c.gridy++;
//		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 6;
//		c.gridwidth = 1;
//		c.gridheight = 1;
		label = new JLabel(turnUsername + " is aan de beurt!");
		this.add(label, c);
//		c.ipady = 10;
//		c.gridx = 0;
//		c.gridy++;
//		c.gridwidth = 2;
	}

	public Catan getGame() {
		return game;
	}
}
