package model;

public class Catan {
	
	//Our version of Catan always has 4 players, that is the requirement. 
	private static final int AMOUNT_OF_PLAYERS = 4;
	
	private Gameboard gameboard;
	private Dice dice;
	private Chat chat;
	private Player[] players;
	private Bank bank;
	
	public Catan() {
		gameboard = new Gameboard();
		dice = new Dice();
		chat = new Chat();
		players = new Player[AMOUNT_OF_PLAYERS];
		
	}
	
}
