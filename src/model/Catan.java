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
	private boolean firstRound;
	private boolean roadBuilding;
	private boolean roadBuildingFirst;

	//get the followNr of the player of which it is it's turn. 
	/** Player's turn in order: 1-4 */
	private int turn;
	/** 
	 * {@code true} if the player has rolled the dice for his turn, {@code false} if not 
	 * This is in Catan, mainly because it is in Catan in the database.
	 */
	private boolean rolledDice;
	private ArrayList<String> messages;
	private ArrayList<TradeRequest> tradeRequestArr;
	
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
	public Catan(ArrayList<Player> players, Player selfPlayer, int turn) {
		this.players = players;
		this.selfPlayer = selfPlayer;
		this.idGame = players.get(0).getidGame();
		this.turn = turn;
		this.rolledDice = false;
		this.roadBuilding = false;
		this.roadBuildingFirst = true;
		tradeRequestArr = new ArrayList<TradeRequest>();
		//First player (Players[0]) is the UITDAGER. The Rest is UIGEDAAGDE. 
		//players[0] has already been made, so start at 1. 
		
		
		//Game starts at turn -1, after the setup of the game is complete, 
		//nextTurn() will set it to turn 0, 
		//which is the 1st real turn of the game. 
	}
	
	public boolean isRoadBuildingFirst() {
		return roadBuildingFirst;
	}

	public void setRoadBuildingFirst(boolean roadBuildingFirst) {
		this.roadBuildingFirst = roadBuildingFirst;
	}

	public boolean isFirstRound() {
		return firstRound;
	}

	public void setFirstRound(boolean firstRound) {
		this.firstRound = firstRound;
	}
	
	public void fillCatan(Gameboard gameBoard) {
		this.gameboard = gameBoard;
		dice = new Dice();
		bank = new Bank();
	}
	
	public void addTradeRequest(TradeRequest tradeRequest) {
		tradeRequestArr.add(tradeRequest);
	}
	
	public Player getPlayerByID(int id) {
		for(Player p : players) {
			if(p.getIdPlayer() == id) {
				return p;
			}
		}
		return null;
	}
	
	public void rollDice() {
		dice.roll();	
	}
	
	public int getIdGame() {
		return idGame;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public boolean isRoadBuilding() {
		return roadBuilding;
	}
	
	public void setRoadBuilding(boolean roadBuilding) {
		this.roadBuilding = roadBuilding;
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
	
	public void setTradeRequests(ArrayList<TradeRequest> tradeRequestArr) {
		this.tradeRequestArr = tradeRequestArr;
	}

	public ArrayList<TradeRequest> getTradeRequestArr() {
		return tradeRequestArr;
	}
	
	
}