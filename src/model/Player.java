package model;

import dbaccess.PlayerDA;

public class Player {
	private PlayerDA playerDA;
	
	public Player() {
		playerDA = new PlayerDA();
	}
	
	public void throwDice() {
		Dice dice = new Dice();
		int firstThrow = dice.rollDice();
		int secondThrow = dice.rollDice();
		System.out.println("Throws: " + firstThrow + " & " + secondThrow);
		playerDA.addThrows(firstThrow, secondThrow);
		
	}
	
	
}
