package model;


public class Player {
	
	private int idGame;
	private String username;
	private PlayerColor color;
	private int follownr;
	private PlayStatus playStatus;
	private Hand hand;
	private int overwinningspunten;
	
	public Player(String username) {
		this.username = username;
	}
	
	public Player(int idGame, String username, PlayerColor color, int follownr, PlayStatus playStatus) {
		this.idGame = idGame;
		this.username = username;
		this.color = color;
		this.follownr = follownr;
		this.playStatus = playStatus;
	}
	
	public void createHand() {
		hand = new Hand();
		
	}
	
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
		return new Village(); //return statement should return the village that has just been built (NOT NEW VILLAGE!!!)
		
	}

	//Village v is the village that is built, this is important for Catan class. 
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
	public int getFollownr() {
		return follownr;
	}
	public PlayStatus getPlayStatus() {
		return playStatus;
	}
	public Hand getHand() {
		return hand;
	}

	public int getOverwinningspunten() {
		return overwinningspunten;
	}

	public void setOverwinningspunten(int overwinningspunten) {
		this.overwinningspunten = this.overwinningspunten + overwinningspunten;
	}
	
	
}
