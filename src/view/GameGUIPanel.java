package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.Player;

@SuppressWarnings("serial")
public class GameGUIPanel extends JPanel {
	
	// Instance variables
	private Color myBackGroundColor = new Color(240, 226, 223);
	
	private GameTopPanel myGameTopPanel;
	private playerActionsPanel myPlayerActionsPanel;
	private MyResourcesPanel myResourcesPanel;
	private MyDevelopmentCardsPanel myDevelopmentCardsPanel;
	private BoardPanel myBoardPanel;
	private PlayerStatsPanel myPlayerStatsPanel; // TODO make 4 of them in an array
	private Player player;
	
	// Constructor
	public GameGUIPanel(Player player) { // TODO array of players as you need 4 playerStatsPanels?
		this.player = player;
		/*
		 setLayout(new BorderLayout());
		 */
		setBackground(myBackGroundColor);
		/*
		 setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		 */
		myGameTopPanel = new GameTopPanel("CatanTest"); // TODO set gamelabel text in constructor
		myPlayerActionsPanel = new playerActionsPanel();
		myResourcesPanel = new MyResourcesPanel();
		myDevelopmentCardsPanel = new MyDevelopmentCardsPanel();
		myBoardPanel = new BoardPanel();
		myPlayerStatsPanel = new PlayerStatsPanel(player);
		
		add(myGameTopPanel, BorderLayout.NORTH);
		add(myPlayerActionsPanel, BorderLayout.WEST);
		add(myResourcesPanel);
		add(myDevelopmentCardsPanel);
		add(myBoardPanel);
		add(myPlayerStatsPanel);
	}
}
