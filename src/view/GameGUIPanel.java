package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.Player;


@SuppressWarnings("serial")
public class GameGUIPanel extends JPanel {
	
	private final int AMOUNT_OF_PLAYERS = 4;
	
	// Instance variables
	private Color myBackGroundColor = new Color(240, 226, 223);
	
	private GameTopPanel gameTopPanel;
	private PlayerActionPanel playerActionsPanel;
	private MyResourcesPanel resourcesPanel;
	private MyDevelopmentCardsPanel developmentCardsPanel;
	private BoardPanel boardPanel;
	private DicePanel dicePanel;
	private ChatPanel chatPanel;
	
	private PlayerStatsPanel[] playerStatsPanel; // TODO make 4 of them in an array
	
	private Player player;
	
	// Constructor
	public GameGUIPanel(Player player) { // TODO array of players as you need 4 playerStatsPanels?
		this.player = player;
		setBackground(myBackGroundColor);
		setLayout(new GridBagLayout());
		gameTopPanel = new GameTopPanel("CatanTest"); // TODO set gamelabel text in constructor
		playerActionsPanel = new PlayerActionPanel();
		resourcesPanel = new MyResourcesPanel();
		developmentCardsPanel = new MyDevelopmentCardsPanel();
		boardPanel = new BoardPanel();
		playerStatsPanel = new PlayerStatsPanel[AMOUNT_OF_PLAYERS];
		dicePanel = new DicePanel();
		chatPanel = new ChatPanel();
		
		createLayout();
	}
	
	// Create layout
	private void createLayout() {
		// Add Top Panel
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 5;
		add(gameTopPanel, gridBagConstraints);
		
		// Add ChatPanel
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		gridBagConstraints.gridy++;
		add(chatPanel, gridBagConstraints);
		
		// Add Board Panels
		gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
		gridBagConstraints.gridx++;
		add(boardPanel, gridBagConstraints);
		
		// Add PlayerActionsPanel // TODO only if its this players turn
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(200, 0, 0, 100);
		add(playerActionsPanel, gridBagConstraints);
		
		// Add DicePanel
		gridBagConstraints.insets = new Insets(0, 0, 500, 300);
		add(dicePanel, gridBagConstraints);
		
		// Add resourcesPanel
		//gridBagConstraints.gridy++;
		//gridBagConstraints.anchor = GridBagConstraints.SOUTH;
		//add(resourcesPanel, gridBagConstraints);
		
		// Add playerStatsPanels
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
		gridBagConstraints.gridy++;
		playerStatsPanel[0] = new PlayerStatsPanel(player);
		add(playerStatsPanel[0], gridBagConstraints);
		
		gridBagConstraints.gridx++;
		playerStatsPanel[1] = new PlayerStatsPanel(player);
		add(playerStatsPanel[1], gridBagConstraints);
		
		// Add developmentCardsPanel
		//gridBagConstraints.gridx++;
		//gridBagConstraints.insets = new Insets(0, 200, 0, 200);
		//gridBagConstraints.anchor = GridBagConstraints.SOUTH;
		//add(developmentCardsPanel, gridBagConstraints);
		
		gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.gridx++;
		playerStatsPanel[2] = new PlayerStatsPanel(player);
		add(playerStatsPanel[2], gridBagConstraints);
		
		gridBagConstraints.gridx++;
		playerStatsPanel[3] = new PlayerStatsPanel(player);
		add(playerStatsPanel[3], gridBagConstraints);
		
		
	}
}
