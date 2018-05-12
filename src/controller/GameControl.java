package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.Bank;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;

public class GameControl {
	private GameBoardControl gameBoardControl;
	private MainDA mainDA;
	private int idGame;
	private ArrayList<String> messageList;
	private Player player;
	private Bank bank;
	
	public GameControl(MainDA mainDA) {
		this.mainDA = mainDA;
		//-----TEST------
//		createGame(false);
//		joinGame("lesley");
		//----END TEST -----
	}
	
	/**
	 * Create a game record in the DB AND sets idGame
	 */
	public void createGame(boolean randomBoard) {
		idGame = mainDA.createGame(randomBoard);
		gameBoardControl = new GameBoardControl(mainDA, idGame);
		gameBoardControl.createBoard();
		
	}
	
	/**
	 * Add a player
	 */
	public void joinGame(String username) {
		int lastPlayerNumber = mainDA.getLastPlayerFollowNumber(idGame);
		String playerColor = null;
		int followNR = -1;
		String playStatus = null;
		
		if(lastPlayerNumber == -1) {
			System.out.println("Color error");
		} else {
			switch(lastPlayerNumber) {
			case 0:
				playerColor = "ROOD";
				followNR = 1;
				playStatus = "UITDAGER";
				break;
			case 1:
				playerColor = "WIT";
				followNR = 2;
				playStatus = "UITGEDAAGDE";
				break;
			case 2:
				playerColor = "BLAUW";
				followNR = 3;
				playStatus = "UITGEDAAGDE";
				break;
			case 3:
				playerColor = "ORANJE";
				followNR = 4;
				playStatus = "UITGEDAAGDE";
				break;
			}
		}
		
		mainDA.createPlayer(idGame, username, playerColor, followNR, playStatus);
		player = new Player(idGame, username, PlayerColor.valueOf(playerColor), followNR, PlayStatus.valueOf(playStatus));
		gameBoardControl = new GameBoardControl(mainDA, idGame);
		gameBoardControl.loadBoard();
	}

	public void getMessages() {
		messageList = mainDA.getMessages(idGame);
	}
	
	public void testprintMessages() {
		for(String s: messageList) {
			System.out.println(s);
		}
	}
	
	public void addMessage(String message) {
		mainDA.addMessage(player.getUsername(), message);
	}
	
	public void setGameID(int gameID) {
		this.idGame = gameID;
	}

	public void makeBank() {
		
	}

}
