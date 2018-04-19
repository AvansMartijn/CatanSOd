package model;


public class Player {
	
	public Player() {
	}
	
	public void throwDice() {
		Dice dice = new Dice();
		int firstThrow = dice.rollDice();
		int secondThrow = dice.rollDice();
		System.out.println("Throws: " + firstThrow + " & " + secondThrow);
		
	}
	
	
}
