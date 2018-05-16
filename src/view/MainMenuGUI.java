package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import model.Catan;
import model.Player;

@SuppressWarnings("serial")
public class MainMenuGUI extends JPanel {

	private JPanel currentGames;
	private JPanel mainPanel;
	private String username;
	private JPanel optionsPanel;
	private JPanel nextPreviousPanel;
	private int pageNr;

	public MainMenuGUI(String username, JPanel optionsPanel, JPanel nextPreviousPanel, JPanel currentGames) {
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		mainPanel = new JPanel();
		this.optionsPanel = optionsPanel;
		this.nextPreviousPanel = nextPreviousPanel;
		
		this.username = username;
		pageNr = 0;
		GridBagLayout gridLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		mainPanel.setLayout(gridLayout);
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		mainPanel.add(new Title(), c);
		c.gridx = 0;
		c.gridy = 1;

		mainPanel.add(optionsPanel);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		mainPanel.add(currentGames, c);
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		mainPanel.add(nextPreviousPanel, c);
		this.add(mainPanel);
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
