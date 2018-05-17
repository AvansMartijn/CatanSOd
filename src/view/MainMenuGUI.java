package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.JScrollPane;

import model.Catan;
import model.Player;

@SuppressWarnings("serial")
public class MainMenuGUI extends JPanel {

	private RecentGamesPanel currentGames;
	private JPanel mainPanel;
	private String username;
	private JPanel optionsPanel;
	private JPanel nextPreviousPanel;
	private JScrollPane scrollPane;
	private int pageNr;

	public MainMenuGUI(String username, JPanel optionsPanel, JPanel nextPreviousPanel, RecentGamesPanel currentGames) {
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(420, 800));
		this.optionsPanel = optionsPanel;
		this.nextPreviousPanel = nextPreviousPanel;
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
		}
	}

	public int getPageNr() {
		return pageNr;
	}

	public void setPageNr(int pageNr) {
		this.pageNr = pageNr;
	}

	
	

}
