package model;

import java.util.ArrayList;


public class Player {
	
	// Instance variables
	private Color color;
	private Hand hand;
	private LargestArmy largestArmy;
	private LargestRoad largestRoad;
	
	// Constructor
	public Player(int playerNr) {
		
		hand = new Hand();
		
		// Assign Color to Player
		Color[] colors = Color.values();
		color = colors[playerNr];
	}
	
	// Get Hand
	public Hand getHand() {
		return hand;
	}
	
	public void throwDice() {
		Dice dice = new Dice();
		int firstThrow = dice.roll();
		int secondThrow = dice.roll();
		System.out.println("Throws: " + firstThrow + " & " + secondThrow);
		
	}
	
	public Player(int playerNr) {
		// TODO Auto-generated constructor stub
	}

	public void doTurn() {
		// TODO Auto-generated method stub
	}

	public Village setUpTurn() {
		return new Village(); //return statement should return the village that has just been built (NOT NEW VILLAGE!!!)
		// TODO Auto-generated method stub
		
	}

	//Village v is the village that is built, this is important for Catan class. 
	public void getStartResources(Village v) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Resource> getHand() {
		// TODO Auto-generated method stub
		return null;
	}
}
