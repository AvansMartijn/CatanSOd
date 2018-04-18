package model;

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
}
