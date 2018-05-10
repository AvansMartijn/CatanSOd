package model;

public class Dice {
	
	/**
	 * The amount of sides a die has. In Catan the normal 6 sided die is used. 
	 */
	private static final int AMOUNT_OF_SIDES = 6;
	
	/**
	 * According to the rules, the game is played by rolling 2 dice. 
	 */
	private static final int AMOUNT_OF_DICE = 2;
	
	/**
	 * An array with the values of all the dice. 
	 */
	private int[] values;
	
	/**
	 * Creates 2 6 sided dice in a int[2] array. 
	 */
	public Dice() {
		values = new int[AMOUNT_OF_DICE];
	}
	
	/**
	 * The 2 dice are both rolled with a random value between 1-6. 
	 */
	public void roll() {
		for(int i = 0; i < values.length; i++) {
			values[i] = dieRoll();			
		}
	}

	/**
	 * @return a random value between 1-6. 
	 */
	private int dieRoll() {
		//Random value between 0-5. 
		int dieRoll = (int) (Math.random() * AMOUNT_OF_SIDES);
		//Random value between 1-6.
		dieRoll++;
		return dieRoll;
	}
	
	/**
	 * @return the int[2] array of both die values (1-6). 
	 */
	public int[] getDieValues() {
		return values;
	}
	
	/**
	 * 
	 * 
	 * @return the sum of both dice. 
	 */
	public int getTotalValue() {
		int totalValue = 0;
		for (int i = 0; i < values.length; i++) {
			totalValue += values[i];
		}
		return totalValue;
	}

	/**
	 * This method only exists because of old code. Use the roll() method instead.
	 * @return a value between 1-6. 
	 */
	
	@Deprecated
	public int rolling() {
		return dieRoll();
	}	
}