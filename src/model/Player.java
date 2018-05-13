package model;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	private int idGame;
	private String username;
	private PlayerColor color;
	private int follownr;
	private PlayStatus playStatus;
	private Hand hand;
	private int points;
	private int settlements;
	private ArrayList<Settlement> settlementArr;
	private int cities;
	private int roads;
	
	public Player(String username) {
		this.username = username;
	}

	public Player(int idGame, String username, PlayerColor color, int follownr, PlayStatus playStatus) {
		this.idGame = idGame;
		this.username = username;
		this.color = color;
		this.follownr = follownr;
		this.playStatus = playStatus;
		settlementArr = new ArrayList<Settlement>();
		for(int i = 0; i<10; i++) {
			settlementArr.add(new Settlement(false));
		}
		for(int i = 0; i<5; i++) {
			settlementArr.add(new Settlement(true));
		}
		
	}

	// Create hand
	public void createHand() {
		hand = new Hand();

	}

	// Throw dice
	public void throwDice() {
		Dice dice = new Dice();
		int firstThrow = dice.roll();
		int secondThrow = dice.roll();
		System.out.println("Throws: " + firstThrow + " & " + secondThrow);
	}

	public void doTurn() {
	}

	public void testPrintPlayer() {
		System.out.println(idGame + " " + username + " " + color + " " + follownr + " " + playStatus);
	}

	public Village setUpTurn() {
		return new Village(); // return statement should return the village that has just been built (NOT NEW
								// VILLAGE!!!)

	}

	// Village v is the village that is built, this is important for Catan class.
	public void getStartResources(Village v) {
		// TODO Auto-generated method stub

	}

	public String getUsername() {
		return username;
	}

	public int getidGame() {
		return idGame;
	}

	public PlayerColor getColor() {
		return color;
	}

	public Color getColorObject() {
		String colorString = color.toString();

		switch (colorString) {
		case "ROOD":
			return Color.RED;
		case "WIT":
			return Color.WHITE;
		case "BLAUW":
			return Color.BLUE;
		case "ORANJE":
			return Color.ORANGE;
		default:
			return Color.BLACK;
		}

	}

	public int getFollownr() {
		return follownr;
	}

	public PlayStatus getPlayStatus() {
		return playStatus;
	}

	public Hand getHand() {
		return hand;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getSettlements() {
		return settlements;
	}

	public void setSettlements(int settlements) {
		this.settlements = settlements;
	}

	public int getCities() {
		return cities;
	}

	public void setCities(int cities) {
		this.cities = cities;
	}

	public int getRoads() {
		return roads;
	}

	public void setRoads(int roads) {
		this.roads = roads;
	}
}
