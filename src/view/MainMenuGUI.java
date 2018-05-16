package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

	private JPanel currentGames;
	private JPanel mainPanel;
	private String username;
	private JPanel optionsPanel;
	private JPanel nextPreviousPanel;
	private JScrollPane scrollPane;
	private int pageNr;

	public MainMenuGUI(String username, JPanel optionsPanel, JPanel nextPreviousPanel, JPanel currentGames) {
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(400, 800));
		this.optionsPanel = optionsPanel;
		this.nextPreviousPanel = nextPreviousPanel;
		this.currentGames = currentGames;
		this.username = username;
		pageNr = 0;
//		GridBagLayout gridLayout = new GridBagLayout();
//		GridBagConstraints c = new GridBagConstraints();
//		mainPanel.setLayout(gridLayout);
//		c.gridx = 0;
//		c.gridy = 0;
//		c.ipady = 10;
		mainPanel.add(new Title());
//		c.gridx = 0;
//		c.gridy = 1;

		mainPanel.add(this.optionsPanel);

//		c.gridx = 0;
//		c.gridy = 2;
//		c.gridwidth = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
		
//		mainPanel.add(this.currentGames);
		currentGames.setPreferredSize(new Dimension(400, currentGames.getHeight()));
		scrollPane = new JScrollPane(currentGames, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
		scrollPane.setPreferredSize(new Dimension(420, 700));
		add(scrollPane);
		
//		c.gridx = 0;
//		c.gridy = 3;
//		c.fill = GridBagConstraints.NONE;
//		mainPanel.add(this.nextPreviousPanel);
		
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
