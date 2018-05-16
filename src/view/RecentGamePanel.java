package view;

import java.awt.Color;
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

	public RecentGamePanel(Catan game) {
		this.game = game;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;

		JLabel label = new JLabel("Spel ID: " + game.getIdGame());
		this.add(label, c);
		// c.gridx = 1;
		// c.gridy = 0;
		// label = new JLabel("Ronde Nummer: 0");
		// panel.add(label, c);
		c.ipady = 20;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		ArrayList<Player> playerArray = game.getPlayers();
		String displayString = "";
		String turnUsername = "";
		for (Player p : playerArray) {
			if (p.getFollownr() == game.getPlayerTurn()) {
				turnUsername = p.getUsername();
			}
			displayString = String.format("%s %s: %d ", displayString, p.getUsername(), p.getOverwinningspunten());
		}
		label = new JLabel(displayString);
		this.add(label, c);
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		label = new JLabel(turnUsername + " is aan de beurt!");
		this.add(label, c);
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
	}
	
	public Catan getGame() {
		return game;
	}
}
