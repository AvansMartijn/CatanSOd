package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class MainMenuGUI extends JPanel {

	private RecentGamesPanel currentGames;
	private JPanel mainPanel;
	private String username;
	private JPanel optionsPanel;
	private JScrollPane scrollPane;
	private int pageNr;

	public MainMenuGUI(String username, JPanel optionsPanel, RecentGamesPanel currentGames) {
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(420, 800));
		this.optionsPanel = optionsPanel;
		this.currentGames = currentGames;
		this.username = username;
		pageNr = 0;

		mainPanel.add(new Title());
		mainPanel.add(this.optionsPanel);

		int height = currentGames.getGamePanels().size() * 110;
		currentGames.setPreferredSize(new Dimension(400, height));
		scrollPane = new JScrollPane(currentGames, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
		scrollPane.setPreferredSize(new Dimension(420, 700));
		mainPanel.add(scrollPane);
		
		
		GridBagLayout gridLayout = new GridBagLayout();
		setLayout(gridLayout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;

        gridLayout.setConstraints(mainPanel, constraints);
        add(mainPanel, constraints);
	}
	
	public JPanel getCurrentGamesPanel() {
		return currentGames;
	}

	public class Title extends JPanel {
		public Title() {
			this.add(new JLabel("Welkom terug, " + username + "!")); // Must be logged in user.
			this.setPreferredSize(new Dimension(400, 20));
		}
	}

	public int getPageNr() {
		return pageNr;
	}

	public void setPageNr(int pageNr) {
		this.pageNr = pageNr;
	}

	
	

}
