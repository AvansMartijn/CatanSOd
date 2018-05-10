package model;

//import java.util.ArrayList;

public class Catan {
	
	//Our version of Catan always has 4 players, that is the requirement. 
	private static final int AMOUNT_OF_PLAYERS = 4;
	
	/**
	 * These are the {@code Dice} that are used in the game. Every game should have only 1 set of dice. 
	 * The players should get their dice from the game. 
	 */
	private Dice dice;
	private Chat chat;
	private Player[] players;
	private Gameboard gameboard;
	private Bank bank;
	private int turn;
	
	public Catan() {
		dice = new Dice();
		chat = new Chat();
		players = new Player[AMOUNT_OF_PLAYERS];
		for(int playerNr = 0; playerNr < players.length; playerNr++) {
			//The player has to have a playerNr, otherwise the color of the player can't be set.
			players[playerNr] = new Player(playerNr);
		}
		gameboard = new Gameboard(null, null, null);
		bank = new Bank();
		
		//Game starts at turn -1, after the setup of the game is complete, 
		//nextTurn() will set it to turn 0, 
		//which is the 1st real turn of the game. 
		turn = -1;
	}
	
	
	//TODO These should be a GameControl method
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
	
	private int getPlayerTurn() {
		int playerTurn = turn % AMOUNT_OF_PLAYERS;
		return playerTurn;
	}
}