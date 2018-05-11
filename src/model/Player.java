package model;

import java.util.ArrayList;

public class Player {
	
	private static final int AMOUNT_OF_VILLAGES = 5;
	private static final int AMOUNT_OF_CITIES = 4;
	private static final int AMOUNT_OF_STREETS = 15;
	
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
	private int follownr;
	private PlayStatus playStatus;
	/** The hand contains both the developmentCards and the resources. */
	private Hand hand;
	/**
	 * The game should make these and the player should get them.
	 * The dice are 2 dice that have random values between 1-6. 
	 */
	private Dice dice;
	private ArrayList<City> cities;
	private ArrayList<Village> villages;
	private ArrayList<Street> streets;
	/** Either the only {@code LargetsArmy} card in the game, or {@code null}*/
	private LargestArmy largestArmy;
	/** Either the only {@code LargestRoad} card in the game, or {@code null}*/
	private LargestRoad largestRoad;
	
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
	 * @param playerNr The player in the array, which is used for color: 
	 * {@code 0 = ROOD, 1 = WIT, 2 = BLAUW, 3 = ORANJE} and for the follownr ({@code playerNr + 1})
	 * @param playStatus The play status from the database
	 * 
	 * @since 10 May 2018
	 * @author Jasper Mooren 
	 * <p>
	 * (Adding this is super useful, because than you can ask the person who made it 
	 * when you don't understand it or disagree with what he has made!)
	 */
	public Player(int idGame, String username, int playerNr, PlayStatus playStatus, Dice dice) {
		this.idGame = idGame;
		this.username = username;
		this.color = PlayerColor.values()[playerNr];
		this.follownr = playerNr + 1;
		this.playStatus = playStatus;
		hand = new Hand();
		this.dice = dice;
		
		cities = new ArrayList<>();
		for (int i = 0; i < AMOUNT_OF_CITIES; i++) {
			cities.add(new City(color));
		}
		
		villages = new ArrayList<>();
		for (int i = 0; i < AMOUNT_OF_VILLAGES; i++) {
			villages.add(new Village(color));
		}
		
		streets = new ArrayList<>();
		for (int i = 0; i < AMOUNT_OF_STREETS; i++) {
			streets.add(new Street(color));
		}
		
		largestArmy = null;
		largestRoad = null;
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
	 * Deprecated because other method should be used: rollDice()
	 */
	
	@Deprecated
	public void throwDice() {
		Dice dice = new Dice();
		int firstThrow = dice.rolling();
		int secondThrow = dice.rolling();
		System.out.println("Throws: " + firstThrow + " & " + secondThrow);
	}
	
	/**
	 * This rolls 2 dice and sets the int[2] Array to 2 random values between 1-6. 
	 */
	public void rollDice() {
		dice.roll();
	}

	public void doTurn() {
		// TODO Auto-generated method stub		
	}
	
	public void testPrintPlayer() {
		System.out.println(idGame + " " + username + " " + color + " " + follownr + " " + playStatus);
	}

	public Village setUpTurn() {
		// TODO Auto-generated method stub
		return null;			
	}
	
	/**
	 * See {@link Hand#playDevelopmentCard(int cardType)}
	 * 
	 * @param cardType use {@code DevelopmentCard} constants. 
	 * 
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	public void playDevelopmentCard(int cardType) {
		hand.playDevelopmentCard(cardType);
	}

	/**
	 * Calculates the amount of Victory Points the player has. 
	 * 
	 * @return the amount of victory points the player has. 
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	public int getAmountOfVictoryPoints() {
		int amountOfVictoryPoints = 0;
		
		//Victory Points from Cities. 
		for (int i = 0; i < cities.size(); i++) {
			if(cities.get(i).isBuilt()) {
				amountOfVictoryPoints += City.VICTORY_POINTS;
			}
		}
		
		//Victory Points from Villages. 		
		for (int i = 0; i < villages.size(); i++) {
			if(villages.get(i).isBuilt()) {
				amountOfVictoryPoints += Village.VICTORY_POINTS;
			}
		}
		
		//Victory Points from Victory Point Cards
		amountOfVictoryPoints += getAmountOfDevelopmentCardsPlayed(DevelopmentCard.VICTORY_POINT);
		
		//Victory Points from the Largest Army card
		if(hasLargestArmy()) {
			amountOfVictoryPoints += LargestArmy.VICTORY_POINTS;
		}

		//Victory Points from the Largest Road card
		if(hasLargestRoad()) {
			amountOfVictoryPoints += LargestRoad.VICTORY_POINTS;
		}
		
		return amountOfVictoryPoints;
	}

	/**
	 * @return
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	private int getAmountOfDevelopmentCardsPlayed(int cardType) {
		return hand.getAmountOfDevelopmentCardsPlayed(cardType);
	}
	
	/**
	 * Shoudn't this be a controller method? (Jasper asks this)
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
	
	public boolean hasLargestArmy() {
		if(largestArmy != null) {
			return true;
		}
		return false;
	}
	
	public boolean hasLargestRoad() {
		if(largestRoad != null) {
			return true;
		}
		return false;
	}
}