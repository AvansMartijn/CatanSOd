package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Catan;
import model.Player;

@SuppressWarnings("serial")
public class MainMenuGUI extends JPanel {

	private JPanel currentGames;
	private String username;
	private ArrayList<Catan> gameList;
	private int pageNr;

	public MainMenuGUI(ArrayList<Catan> gameList, String username) {
		this.gameList = gameList;
		this.username = username;
		pageNr = 0;
		GridBagLayout gridLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridLayout);
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		this.add(new Title(), c);
		c.gridx = 0;
		c.gridy = 1;

		this.add(new Options(), c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		currentGames = new RecentGames(gameList, pageNr);
		this.add(currentGames, c);
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		this.add(new NextPrevious(), c);
		UpdateGames(0);
	}

	public void UpdateGames(int pageId) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.remove(currentGames);
		currentGames = new RecentGames(gameList, pageId);
		this.add(currentGames, c);
	}

	public class Title extends JPanel {
		public Title() {
			this.add(new JLabel("Welkom terug, " + username + "!")); // Must be logged in user.
		}
	}

	public class NextPrevious extends JPanel {
		public NextPrevious() {
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			JButton previousButton = new JButton("Vorige");
			previousButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (pageNr > 0) {
						pageNr--;
						UpdateGames(pageNr);
					}
					;

				}
			});
			JButton nextButton = new JButton("Volgende");
			nextButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					pageNr++;
					UpdateGames(pageNr);
				}
			});
			this.add(previousButton);
			this.add(nextButton);
		}
	}

	public class Options extends JPanel {
		public Options() {
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			this.add(new JButton("Game aanmaken"));
			this.add(new JButton("Uitnodigingen bekijken"));
		}
	}

	public class RecentGames extends JPanel {
		public RecentGames(ArrayList<Catan> gameList, int page) {
			BoxLayout gridLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(gridLayout);

			for (int i = (3 * page); i < (3 * page) + 3; i++) {
				JPanel panel = new JPanel();
				panel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				if (gameList.size() <= i) {
					break;
				}
				Catan game = gameList.get(i);
				MouseAdapter mouseListener = new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JOptionPane.showMessageDialog(null, "Clicked Game NR:" + game.getIdGame());
					}

				};

				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				c.gridx = 0;
				c.gridy = 0;
				c.weightx = 1;

				JLabel label = new JLabel("Spel ID: " + game.getIdGame());
				panel.add(label, c);
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
					displayString = String.format("%s %s: %d ", displayString, p.getUsername(),
							p.getOverwinningspunten());
				}
				label = new JLabel(displayString);
				panel.add(label, c);
				c.ipady = 10;
				c.gridx = 0;
				c.gridy = 2;
				c.gridwidth = 2;
				label = new JLabel(turnUsername + " is aan de beurt!");
				panel.add(label, c);
				c.ipady = 10;
				c.gridx = 0;
				c.gridy = 3;
				c.gridwidth = 2;

				panel.addMouseListener(mouseListener);
				this.add(panel);
			}
			JLabel label = new JLabel("Pagina: " + page);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(label);
		}
	}

}
