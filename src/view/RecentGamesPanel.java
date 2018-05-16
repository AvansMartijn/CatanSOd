package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Catan;
import model.Player;

@SuppressWarnings("serial")
public class RecentGamesPanel extends JPanel {
	ArrayList<RecentGamePanel> gamePanels;
	
	public RecentGamesPanel(ArrayList<Catan> gameList, int page) {
		gamePanels = new ArrayList<>();
		BoxLayout gridLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(gridLayout);

		for (int i = (3 * page); i < (3 * page) + 3; i++) {
			if (gameList.size() <= i) {
				break;
			}
			Catan game = gameList.get(i);
			RecentGamePanel gamePanel = new RecentGamePanel(game);
			gamePanels.add(gamePanel);
			this.add(gamePanel);
		}
		JLabel label = new JLabel("Pagina: " + page);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(label);
	}
	
	public ArrayList<RecentGamePanel> getGamePanels() {
		return gamePanels;
	}
	
}