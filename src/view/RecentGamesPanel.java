package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Catan;

@SuppressWarnings("serial")
public class RecentGamesPanel extends JPanel {
	
	private final int PANEL_WIDTH = 500;
	private final int GAMEPANEL_HEIGHT = 105;
	
	private ArrayList<RecentGamePanel> gamePanels;
	private Color textBackgroundColor = new Color(223, 190, 172);
	
	public RecentGamesPanel(ArrayList<Catan> gameList) {
		gamePanels = new ArrayList<>();
		setBackground(textBackgroundColor);
		for(Catan game: gameList) {
			gamePanels.add(new RecentGamePanel(game));
		}
		
		for(RecentGamePanel g: gamePanels) {
			add(g);
		}
		revalidate();
		
		int height = getGamePanels().size() * GAMEPANEL_HEIGHT;
		setPreferredSize(new Dimension(PANEL_WIDTH, height));
		
	}
	public void UpdateGames(ArrayList<Catan> newGames) {
		for(RecentGamePanel g: gamePanels) {
			g.disposeImage();
			g.removeAll();
		}
		gamePanels.clear();
		this.removeAll();
		setBackground(textBackgroundColor);
		for(Catan game: newGames) {
			gamePanels.add(new RecentGamePanel(game));
		}
		
		for(RecentGamePanel g: gamePanels) {
			add(g);
		}
		revalidate();
	}
	
	public ArrayList<RecentGamePanel> getGamePanels() {
		return gamePanels;
	}
	
}