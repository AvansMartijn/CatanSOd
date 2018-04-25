package controller;

import dbaccess.MainDA;

public class GameControl {
	private GameBoardControl gameBoardControl;
	private MainDA mainDA;
	private int idGame;
	
	public GameControl() {
		mainDA = new MainDA();
		createGameRecord(false);
		addPlayer("Chiel");
	}
	
	private void createGameRecord(boolean randomBoard) {
		/**
		 * Create a game 
		 */
		
		idGame = mainDA.createGame(randomBoard);
		mainDA.createPlayer(idGame, "Martijn", "rood", "uitdager", 1);
	}
	
	private void addPlayer(String username) {
		/**
		 * Add a player
		 */
		int lastPlayerNumber = mainDA.getLastPlayerFollowNumber(idGame);
		String playerColor = null;
		int followNR = 0;
		
		if(lastPlayerNumber == 0) {
			System.out.println("Color error");
		} else {
			switch(lastPlayerNumber) {
			case 1:
				playerColor = "wit";
				followNR = 2;
				break;
			case 2:
				playerColor = "blauw";
				followNR = 3;
				break;
			case 3:
				playerColor = "oranje";
				followNR = 4;
				break;
			}
		}
		
		mainDA.createPlayer(idGame, username, playerColor, "uitdager", followNR);
		
	}
}
