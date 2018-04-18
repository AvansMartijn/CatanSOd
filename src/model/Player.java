package model;

public class Player {
	
	// Instance variables
	private Color color;
	private Hand hand;
	private LargestArmy largestArmy;
	private LargestRoad largestRoad;
	
	// Constructor
	public Player(int playerNr) {
		// Set Player Color
		switch (playerNr) {
		case 0:
			color = color.Red;
			break;
		case 1:
			color = color.White;
			break;
		case 2:
			color = color.Orange;
			break;
		case 3:
			color = color.Blue;
			break;
		default:
			color = null;
		}
	}
}
