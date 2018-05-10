package model;


public class Player {
	
	/**
	 * This Player should be part of a {@code Player[]} in {@code Catan}. 
	 * They way you know this player is from this game is because you get this player through the game. 
	 * Maybe there is a Database reason for this, in which case it's fine.
	 * Either way, the Player should be made through the Catan class.
	 */
	private int idGame;
	
	/**
	 * This is the same username of the {@code Account}, therefore this should be obtained by the {@code Account}, 
	 * which has games ({@code Catan}) and therefore {@code Players}.
	 */
	private String username;
	
	/**
	 * The color of the Player.
	 * PlayerColor's: ROOD, WIT, BLAUW, ORANJE. 
	 * Why is this in Dutch? Shoudn't the DA already turn this into a PlayerColor and make it English?
	 */
	private PlayerColor color;
	
	/**
	 * just a value to connect with the correct player in the Database
	 */
	private int follownr;
	
	/**
	 * This is a value that is straight from the Database. 
	 */
	private PlayStatus playStatus;
	
	/**
	 * The hand contains both the developmentCards and the resources.
	 */
	private Hand hand;
	
	/**
	 * The game should make these and the player should get them.
	 * The dice are 2 dice that have random values between 1-6. 
	 */
	private Dice dice;
	
	/**
	 * Creates a player with only a username. All the other parameters are left empty. 
	 * follownr is set to 0 (default int). 
	 * <p>
	 * Why does this constructor exist? It sets a lot of variables to null, 
	 * which means that you have to check in the entire rest of the program 
	 * if some of the instance variables are null. 
	 * If you only have non-null variables on the constructor, you also can't have {@link NullPointerException}s
	 * 
	 * @param username the name of the player
	 */
	
	@Deprecated
	public Player(String username) {
		this.username = username;
	}
	
	/**
	 * Creates a {@code Player} with all the parameters filled in. 
	 * The {@code PlayerColor} is set with an integer.
	 * {@code hand} will be made by creating a new object of it.
	 * 
	 * @param idGame this is the id of the game that is being played
	 * @param username the username of the Account
	 * @param color 0 = ROOD, 1 = WIT, 2 = BLAUW, 3 = ORANJE
	 * @param follownr The follownr from the database
	 * @param playStatus The playStatus from the database
	 * 
	 * @since 10 May 2018
	 * @author Jasper Mooren 
	 * <p>
	 * (Adding this is super useful, because than you can ask the person who made it 
	 * when you don't understand it or disagree with what he has made!)
	 */
	public Player(int idGame, String username, int color, int follownr, PlayStatus playStatus, Dice dice) {
		this.idGame = idGame;
		this.username = username;
		this.color = PlayerColor.values()[color];
		this.follownr = follownr;
		this.playStatus = playStatus;
		hand = new Hand();
		this.dice = dice;
	}
	
	/**
	 * This method should include the creation of the instance {@code hand} and set the instance {@code dice}.
	 * 
	 * I don't know if this truly should be deprecated, but doing it now to attend people of this. 
	 * 
	 * @param idGame
	 * @param username
	 * @param color
	 * @param follownr
	 * @param playStatus
	 */
	@Deprecated
	public Player(int idGame, String username, PlayerColor color, int follownr, PlayStatus playStatus) {
		this.idGame = idGame;
		this.username = username;
		this.color = color;
		this.follownr = follownr;
		this.playStatus = playStatus;
	}
	
	/**
	 * This method is a VERY bad idea. This way everyone can just create hands, 
	 * while, at any point in the program, the player should have 1, and only 1, hand. 
	 * This should be a part of the constructor, not a public method. 
	 * Outside the fact that it is very suboptimal, it is also dangerous, 
	 * because you can completely empty a hand at any point using this method. 
	 * This method does not only create a hand, it also destroys the previous hand that the player already had. 
	 * (The garbage collector sees no reference to this object and deletes it). 
	 */
	
	@Deprecated
	public void createHand() {
		hand = new Hand();
		
	}
	
	/**
	 * This method has a lot of issues. First of all, it creates a new dice object, why? 
	 * Why doesn't the player just have dice as an instance variable. 
	 * That's why Jasper Mooren made a new method.
	 * <p>
	 * Depricated because other method should be used: rollDice()
	 */
	
	@Deprecated
	public void throwDice() {
		Dice dice = new Dice();
		int firstThrow = dice.rolling();
		int secondThrow = dice.rolling();
		System.out.println("Throws: " + firstThrow + " & " + secondThrow);
	}
	
	/**
	 * This rolls 2 dice and sets the int[2] Array in a different 
	 */
	public void rollDice() {
		dice.roll();
	}

	public void doTurn() {
	}
	
	public void testPrintPlayer() {
		System.out.println(idGame + " " + username + " " + color + " " + follownr + " " + playStatus);
	}

	public Village setUpTurn() {
		return new Village(); //return statement should return the village that has just been built (NOT NEW VILLAGE!!!)
		
	}
	
	/**
	 * Village v is the village that is built, this is important for Catan class. 
	 * 
	 * Shoudn't this be a Controller method? (Jasper asks this)
	 * 
	 * @param v the village that gets the starting resources. 
	 */
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
}