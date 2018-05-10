package controller;

import model.Player;
import view.Frame;
import view.GameGUIPanel;

public class GuiController {

	private Player player;
	private GameGUIPanel myGameGUIPanel;
	private Frame frame;

	public GuiController() {
		frame = new Frame();
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setGamePanel() {
		myGameGUIPanel = new GameGUIPanel(player);
		frame.setContentPane(myGameGUIPanel);
	}
	
	
}
