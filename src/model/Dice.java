package model;

public class Dice {
	public Dice() {
		System.out.println(rollDice());
	}
	
	public int rollDice() {
		int throwAmount = (int) (Math.random()*6 + 1) + (int) (Math.random()*6 + 1);
		return throwAmount;
	}
}
