package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameGUIPanel extends JPanel {
	
	// Instance variables
	private Color myBackGroundColor = new Color(240, 226, 223);
	
	private GameTopPanel myGameTopPanel;
	private playerActionsPanel myPlayerActionsPanel;
	private MyResourcesPanel myResourcesPanel;
	private MyDevelopmentCardsPanel myDevelopmentCardsPanel;
	private BoardPanel myBoardPanel;
	
	// Constructor
	public GameGUIPanel() {
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
		
		
		//add(myGameTopPanel, BorderLayout.NORTH);
		//add(myPlayerActionsPanel, BorderLayout.WEST);
		//add(myResourcesPanel);
		//add(myDevelopmentCardsPanel);
		add(myBoardPanel);
	}
}
