package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Catan;

@SuppressWarnings("serial")
public class RecentGamesPanel extends JPanel {
	ArrayList<RecentGamePanel> gamePanels;
	
	public RecentGamesPanel(ArrayList<Catan> gameList, int page) {
		gamePanels = new ArrayList<>();
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