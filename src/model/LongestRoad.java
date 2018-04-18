package model;

public class LongestRoad {

	private boolean longestRoad;
	private int currentLongest;
	
	public void checkLargestArmy(int roadLength) {
		
		if (roadLength >=5) {
			if (roadLength > currentLongest) {
				currentLongest = roadLength;
				longestRoad = true;
			}
		}
		else {
			longestRoad = false;
		}
	}
	
	public Boolean getLargestArmy() {
		return longestRoad;
	}
}

