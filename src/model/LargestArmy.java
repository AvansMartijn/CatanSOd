package model;

public class LargestArmy {

	private boolean largestArmy;
	private int currentLargest;
	
	public void checkLargestArmy(int knightAmount) {
		
		if (knightAmount >=3) {
			if (knightAmount > currentLargest) {
				currentLargest = knightAmount;
				largestArmy = true;
			}
		}
		else {
			largestArmy = false;
		}
	}
	
	public Boolean getLargestArmy() {
		return largestArmy;
	}
}
