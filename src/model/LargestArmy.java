package model;

public class LargestArmy {


	private boolean largestArmy;
	private int currentLargest;
	
	public boolean checkLargestArmy(int knightAmount) {
		
		if (knightAmount >=3) {
			if (knightAmount > currentLargest) {
				largestArmy = true;
			}
		}
		else {
			largestArmy = false;
		}
		return largestArmy;
	}
	
	public Boolean getLargestArmy() {
		return largestArmy;
	}
}
