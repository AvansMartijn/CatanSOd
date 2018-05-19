package model;

import java.util.ArrayList;

//import java.util.ArrayList;

public class Catan {
	
	//Our version of Catan always has 4 players, that is the requirement. 
	private ArrayList<Player> players;
	private Player selfPlayer;
	private int idGame;
	private Dice dice;
	private Gameboard gameboard;
	private Bank bank;
	//TODO Make sure that a conversion from playerID is made to the turn 
	//get the followNr of the player of which it is it's turn. 
	/** Player's turn in order: 1-4 */
	private int turn;
	/** 
	 * {@code true} if the player has rolled the dice for his turn, {@code false} if not 
	 * This is in Catan, mainly because it is in Catan in the database.
	 */
	private boolean rolledDice;
	private ArrayList<String> messages;
	
	/**
	 * This creates a catanGame with all its players. 
	 * 
	 * @param idGame the game id as used in the database
	 * @param usernames {@code String[4]} array with the usernames of all the players in the game. 
	 * @param followNrs int[4] array with the followNrs of all the players. 
	 * 
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	public Catan(ArrayList<Player> players, Player selfPlayer) {
		this.players = players;
		this.selfPlayer = selfPlayer;
		this.idGame = players.get(0).getidGame();
		//First player (Players[0]) is the UITDAGER. The Rest is UIGEDAAGDE. 
		//players[0] has already been made, so start at 1. 
		
		
		//Game starts at turn -1, after the setup of the game is complete, 
		//nextTurn() will set it to turn 0, 
		//which is the 1st real turn of the game. 
	}
	
	public void fillCatan(Gameboard gameBoard) {
		this.gameboard = gameBoard;
		dice = new Dice();
		bank = new Bank();
	}
	
	
	//TODO These should be GameControl methods
	/*
	public void setup() {
//		player 0,1,2,3 does their turn.
		for(int playerNr = 0; playerNr < players.length; playerNr++) {
			players[playerNr].setUpTurn();
		}
//		player 3,2,1,0 does their turn
		for(int playerNr = players.length; playerNr > 0; playerNr--) {
			Village v = players[playerNr].setUpTurn();
			players[playerNr].getStartResources(v);
		}
	}

	public void turn() {
		turn++;
		int roll = dice.roll();
//		The distributeResources method makes an array of all players which contains an ArrayList of resources they get. 
		ArrayList<Resource>[] harvest = gameboard.distributeResources(roll);
		distributeResources(harvest);
		players[getPlayerTurn()].doTurn();
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

//	private void distributeResources(ArrayList<Resource>[] harvest) {
//		for(int playerNr = 0; playerNr < harvest.length; playerNr++) {
//			//This is not a for loop, because the harvest[playerNr] ArrayList reduces in size every time an item is removed.
//			//It just continues until the the size of the harvest[playerNr] ArrayList reaches 0. 
//			while(harvest[playerNr].size() > 0) {
//				players[playerNr].getHand().addResource((harvest[playerNr].remove(0)));
//			}
//		}
//	}

//	private int getPlayerTurn() {
//		int playerTurn = turn % AMOUNT_OF_PLAYERS;
//		return playerTurn;
//	}
	
	
	private void distributeResources(ArrayList<Resource>[] harvest) {
		for(int playerNr = 0; playerNr < harvest.length; playerNr++) {
			//This is not a for loop, because the harvest[playerNr] ArrayList reduces in size every time an item is removed.
			//It just continues until the the size of the harvest[playerNr] ArrayList reaches 0. 
			while(harvest[playerNr].size() > 0) {
				players[playerNr].getHand().addResource((harvest[playerNr].remove(0)));
			}
		}
	}

	*/
	
	public void rollDice() {
		dice.roll();	
	}
	
	public int getIdGame() {
		return idGame;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public int getPlayerTurn() {
		int playerTurn = 4;
		return playerTurn;
	}

	public Player getSelfPlayer() {
		return selfPlayer;
	}

	public void setSelfPlayer(Player selfPlayer) {
		this.selfPlayer = selfPlayer;
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public Gameboard getGameboard() {
		return gameboard;
	}

	public void setGameboard(Gameboard gameboard) {
		this.gameboard = gameboard;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public boolean hasRolledDice() {
		return rolledDice;
	}

	public void setRolledDice(boolean rolledDice) {
		this.rolledDice = rolledDice;
	}

	public void endTurn() {
		rolledDice = false;
		/*
		 * Turn 1-4 % 4 -> Turn 0-3
		 * turn++ -> turn 1-4 (turn 4 -> 1)
		 */
		turn = turn % 4;
		turn++;
	}
	
	/**
	 * Set this method to return true for testing, so you can test everything all the time. 
	 * 
	 * @since 18 May 2018
	 * @author Jasper Mooren
	 */
	public boolean isSelfPlayerTurn() {
		if(selfPlayer.getFollownr() == turn && rolledDice) {
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}
	
	
}