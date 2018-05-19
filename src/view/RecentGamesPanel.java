package view;

import java.awt.Color;
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
//		BoxLayout gridLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
//		this.setLayout(gridLayout);
//
//		for (int i = (3 * page); i < (3 * page) + 3; i++) {
//			if (gameList.size() <= i) {
//				break;
//			}
//			
//			Catan game = gameList.get(i);
//			
//			this.add(gamePanel);
//		}
//		JLabel label = new JLabel("Pagina: " + page);
//		label.setAlignmentX(Component.CENTER_ALIGNMENT);
//		this.add(label);
	}
	
	public ArrayList<RecentGamePanel> getGamePanels() {
		return gamePanels;
	}
	
}