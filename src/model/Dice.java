package model;

import java.util.Random;

public class Dice
{
	// instance variables
	private int[] die;
	private Random random;

	// constructor
	public Dice() {
		die = new int[2];
		die[0] = 6; //Just for the GUI, so it isn't at 0, which would make it impossible to visualize. 
		die[1] = 3; //Just for the GUI, so it isn't at 0, which would make it impossible to visualize. 
		random = new Random();
	}

	public void roll() {
		die[0] = rollRandom();
		die[1] = rollRandom();
	}

	private int rollRandom() {
		return (random.nextInt(6) + 1);
	}

	public int[] getSeperateValues() {
		return die;
	}
	
	public int getValue() {
		return die[0] + die[1];
	}

	public int[] getDie() {
		return die;
	}

	public void setDie(int[] die) {
		this.die = die;
	}
	
}
