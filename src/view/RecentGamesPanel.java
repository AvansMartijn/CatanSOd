package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Catan;

@SuppressWarnings("serial")
public class RecentGamesPanel extends JPanel {
	private ArrayList<RecentGamePanel> gamePanels;
	private Color textBackgroundColor = new Color(223, 190, 172);
	
	public RecentGamesPanel(ArrayList<Catan> gameList, int page) {
		gamePanels = new ArrayList<>();
		setBackground(textBackgroundColor);
		for(Catan game: gameList) {
			gamePanels.add(new RecentGamePanel(game));
		}
		
		for(RecentGamePanel g: gamePanels) {
			add(g);
		}
		revalidate();
		
		int height = getGamePanels().size() * 110;
		setPreferredSize(new Dimension(500, height));
		
	}
	
	public ArrayList<RecentGamePanel> getGamePanels() {
		return gamePanels;
	}
	
}