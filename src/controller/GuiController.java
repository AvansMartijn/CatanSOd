package controller;

import java.awt.Color;

import model.Player;
import view.BoardPanel;
import view.Frame;
import view.GameGUIPanel;
import view.GameTopPanel;
import view.MyDevelopmentCardsPanel;
import view.MyResourcesPanel;
import view.PlayerStatsPanel;
import view.playerActionsPanel;

public class GuiController {

	private Color myBackGroundColor = new Color(240, 226, 223);
	
	private Player player;
	private GameGUIPanel myGameGUIPanel;
	private Frame frame;
	
	public GuiController(Player player) {
		frame = new Frame();
		this.player = player;
		myGameGUIPanel = new GameGUIPanel(player);
		frame.setContentPane(myGameGUIPanel);
	}
}
