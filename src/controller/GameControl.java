package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.Dice;
import model.Gameboard;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;

public class GameControl {
	private GameBoardControl gameBoardControl;
	private Gameboard gameboard;
	private MainDA mainDA;
	private int idGame;
	private String username;
	private Player player;
	private Dice dice;
	
	public GameControl(MainDA mainDA) {
		dice = new Dice();
		this.mainDA = mainDA;
		//-----TEST------
		//Frame frame = new Frame();
//		player = new Player(12, "Hagrid", PlayerColor.BLAUW, 2, PlayStatus.UITGEDAAGDE);
		
		
		//----END TEST -----
	}
	
	public void testMethod() {
//		createGame(false);
		idGame = 773;
		joinGame();
	}
	
	/**
	 * Create a game record in the DB AND sets idGame
	 */
	public void createGame(boolean randomBoard) {
		idGame = mainDA.createGame(randomBoard);
		createPlayer();
//		addPlayerToDB(player);
		gameBoardControl = new GameBoardControl(mainDA, idGame);
		gameboard = gameBoardControl.createBoard();
		
	}
	
	/**
	 * Add a player
	 */
	public void joinGame() {
		createPlayer();
		gameBoardControl = new GameBoardControl(mainDA, idGame);
		gameboard = gameBoardControl.loadBoard();
	}
	
	private void createPlayer() {
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
		player = new Player(idGame, username, PlayerColor.valueOf(playerColor), followNR, PlayStatus.valueOf(playStatus));
	}
	
	public void addPlayerToDB(Player player) {
		mainDA.createPlayer(idGame, player.getUsername(), player.getColor().toString(), player.getFollownr(), player.getPlayStatus().toString());
	}

	public ArrayList<String> getMessages() {
		ArrayList<String> messageList = new ArrayList<String>();
		messageList = mainDA.getMessages(idGame);
		return messageList;
	}
	
	public boolean addMessage(String message) {
		if(mainDA.addMessage(username, idGame, message)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void changeRobberInDB(int idTile) {
		this.mainDA.changeRobberLocation(idGame, idTile);
	}
	
	public int getRobberIdTile() {
		int idTile = this.mainDA.getRobberLocation(idGame);
		return idTile;
	}
	
	public void setGameID(int gameID) {
		this.idGame = gameID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Player getPlayer() {
		return player;
	}
		
	public Gameboard getGameboard() {
		return gameboard;
	}
	
	public void editDiceLastThrown() {
		
	}
	
	public int[] getDiceLastThrown() {
		return mainDA.getLastThrows(idGame);
	}

}
