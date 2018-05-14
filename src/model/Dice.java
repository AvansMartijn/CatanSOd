package model;

import java.util.Random;

public class Dice
{
	// instance variables
	private int[] die;
	private Random random;

	// constructor
	public Dice()
	{
		die = new int[2];
		random = new Random();
		
		
		roll();
	}

	public void roll()
	{
		die[0] = getnewval(die[0]);
		die[1] = getnewval(die[1]);
		
	}

	private int getnewval(int oldval)
	{
		int newval = 0;
		while (true)
		{
			newval = random.nextInt(6) + 1;
			if (newval != oldval)
			{
				return newval;
			}
		}
	}

	public int[] getSeperateValues(){
		return die;
	}
	
	public int getValue() {
		int ret = 0;
		ret = die[0] = die[1];
		return ret;
	}

	public int[] getDie() {
		return die;
	}

	public void setDie(int[] die) {
		this.die = die;
	}
	
}
