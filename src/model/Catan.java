package model;

import java.util.ArrayList;

//import java.util.ArrayList;

public class Catan {
	
	//Our version of Catan always has 4 players, that is the requirement. 
	private static final int AMOUNT_OF_PLAYERS = 4;
	
//	private Dice dice;
//	private Chat chat;
	private Player[] players = new Player[4];
//	private Gameboard gameboard;
//	private Bank bank;
//	private int turn;
	/** The idGame as used in the database. */
	private int idGame;
	/**
	 * These are the {@code Dice} that are used in the game. Every game should have only 1 set of dice. 
	 * The players should get their dice from the game. 
	 */
	private Dice dice;
	private Gameboard gameboard;
	private Bank bank;
	private int turn;
	
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
	public Catan(int idGame, String[] usernames, int[] followNrs) {
		this.idGame = idGame;
		dice = new Dice();
		players = new Player[AMOUNT_OF_PLAYERS];
		//First player (Players[0]) is the UITDAGER. The Rest is UIGEDAAGDE. 
		//players[0] has already been made, so start at 1. 
		gameboard = new Gameboard(null, null, null);
		bank = new Bank();
		
		//Game starts at turn -1, after the setup of the game is complete, 
		//nextTurn() will set it to turn 0, 
		//which is the 1st real turn of the game. 
		turn = -1;
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

<<<<<<< HEAD
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
	
	
=======
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
	
	public int getIdGame() {
		return idGame;
	}
	
	public int getPlayerTurn() {
		int playerTurn = turn % AMOUNT_OF_PLAYERS;
		return playerTurn;
	}


	public ArrayList<Player> getPlayers() {
//		// TODO Auto-generated method stub playerarray
		return null;
	}
}