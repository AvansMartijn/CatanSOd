package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.Player;

@SuppressWarnings("serial")
public class GameGUIPanel extends JPanel {
	
	private final int AMOUNT_OF_PLAYERS = 4;
	
	// Instance variables
	private Color myBackGroundColor = new Color(240, 226, 223);
	
	private GameTopPanel gameTopPanel;
	private playerActionsPanel playerActionsPanel;
	private MyResourcesPanel resourcesPanel;
	private MyDevelopmentCardsPanel developmentCardsPanel;
	private BoardPanel boardPanel;
	
	private PlayerStatsPanel[] playerStatsPanel; // TODO make 4 of them in an array
	
	
	private Player player;
	
	// Constructor
	public GameGUIPanel(Player player) { // TODO array of players as you need 4 playerStatsPanels?
		this.player = player;
		setBackground(myBackGroundColor);
		setLayout(new GridBagLayout());
		/*
		 setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		 */
		gameTopPanel = new GameTopPanel("CatanTest"); // TODO set gamelabel text in constructor
		playerActionsPanel = new playerActionsPanel();
		resourcesPanel = new MyResourcesPanel();
		developmentCardsPanel = new MyDevelopmentCardsPanel();
		boardPanel = new BoardPanel();
		playerStatsPanel = new PlayerStatsPanel[AMOUNT_OF_PLAYERS];
		
		createLayout();
	}
	
	// Create layout
	private void createLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.gridwidth = 4;
		add(gameTopPanel, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
		gridBagConstraints.gridy = 1;
		playerStatsPanel[0] = new PlayerStatsPanel(player);
		add(playerStatsPanel[0], gridBagConstraints);
		
		gridBagConstraints.gridx++;
		playerStatsPanel[1] = new PlayerStatsPanel(player);
		add(playerStatsPanel[1], gridBagConstraints);
		
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.gridx++;
		gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
		playerStatsPanel[2] = new PlayerStatsPanel(player);
		add(playerStatsPanel[2], gridBagConstraints);
		
		gridBagConstraints.gridx++;
		playerStatsPanel[3] = new PlayerStatsPanel(player);
		add(playerStatsPanel[3], gridBagConstraints);
		
		//add(myPlayerActionsPanel, gridBagConstraints);
		
		//add(myResourcesPanel, gridBagConstraints);
		//add(myDevelopmentCardsPanel, gridBagConstraints);
		//add(myBoardPanel, gridBagConstraints);
	}
}
