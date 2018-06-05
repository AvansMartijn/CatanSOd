package model;

import java.awt.Color;
import java.util.ArrayList;
public class Player {

	private int idPlayer;
	private int idGame;
	private String username;
	private PlayerColor color;
	private int follownr;
	private PlayStatus playStatus;
	private Hand hand;
	private int overwinningspunten;
	private int points;
	private boolean hasLargestArmy;
	private boolean hasLongestRoad;

	//arrays with streets cities roads
	private ArrayList<Village> villageArr;
	private ArrayList<City> cityArr;
	private ArrayList<Street> streetArr;
	
		
	public Player(String username) {
		this.username = username;
	}
	private int settlements;
//	private ArrayList<Settlement> settlementArr;
	private int cities;
	private int roads;
	

//	public Player(int idGame, String username, PlayerColor color, int follownr, PlayStatus playStatus) {	
//		this.idPlayer = idPlayer;
//		this.idGame = idGame;
//		this.username = username;
//		this.color = color;
//		this.follownr = follownr;
//		this.playStatus = playStatus;
//		
//		villageArr = new ArrayList<Village>();
//		cityArr = new ArrayList<City>();
//		streetArr = new ArrayList<Street>();
//		
////		for(int i = 0; i < 10; i++) {
////			villageArr.add(new Village());
////		}
////		for(int i = 0; i < 10; i++) {
////			streetArr.add(new Street());
////		}
////		for(int i = 0; i < 5; i++) {
////			cityArr.add(new City());
////		}
////		settlementArr = new ArrayList<Settlement>();
////		for(int i = 0; i<10; i++) {
////			settlementArr.add(new Settlement(false));
////		}
////		for(int i = 0; i<5; i++) {
////			settlementArr.add(new Settlement(true));
////		}
//		
//	}
	
	public Player(int idPlayer, int idGame, String username, PlayerColor color, int follownr, PlayStatus playStatus) {
		this.idPlayer = idPlayer;
		this.idGame = idGame;
		this.username = username;
		this.color = color;
		this.follownr = follownr;
		this.playStatus = playStatus;
		
		villageArr = new ArrayList<Village>();
		cityArr = new ArrayList<City>();
		streetArr = new ArrayList<Street>();
		createHand();
		
//		for(int i = 0; i < 10; i++) {
//			villageArr.add(new Village());
//		}
//		for(int i = 0; i < 10; i++) {
//			streetArr.add(new Street());
//		}
//		for(int i = 0; i < 5; i++) {
//			cityArr.add(new City());
//		}
	}

	// Create hand
	public void createHand() {
		hand = new Hand();
	}

	// Throw dice
	public void throwDice() {
		Dice dice = new Dice();
		dice.roll();
		int[] die = dice.getSeperateValues();
		int firstThrow = die[0];
		int secondThrow = die[1];
		System.out.println("Throws: " + firstThrow + " & " + secondThrow);
	}

	public void doTurn() {
	}

	public void testPrintPlayer() {
		System.out.println(idGame + " " + username + " " + color + " " + follownr + " " + playStatus);
	}

//	public Village setUpTurn() {
//		return new Village(); // return statement should return the village that has just been built (NOT NEW
//								// VILLAGE!!!)
//
//	}

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

	public int getOverwinningspunten() {
		return overwinningspunten;
	}

	public void setOverwinningspunten(int overwinningspunten) {
		this.overwinningspunten = this.overwinningspunten + overwinningspunten;
	}
	
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public ArrayList<Village> getVillageArr() {
		return villageArr;
	}

	public void setVillageArr(ArrayList<Village> villageArr) {
		this.villageArr = villageArr;
	}

	public ArrayList<City> getCityArr() {
		return cityArr;
	}

	public void setCityArr(ArrayList<City> cityArr) {
		this.cityArr = cityArr;
	}

	public ArrayList<Street> getStreetArr() {
		return streetArr;
	}

	public void setStreetArr(ArrayList<Street> streetArr) {
		this.streetArr = streetArr;
	}
	public int getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}
	
	public int getAmountBuildVillages() {
		int available = 0;
		for(Village v : villageArr) {			
			if(v.isBuild()) {
				available++;
			}
		}
		return  available;
	}
	
	public int getAmountBuildCities() {
		int available = 0;
		for(City c : cityArr) {			
			if(c.isBuild()) {
				available++;
			}
		}
		return  available;
	}
	
	public int getAmountBuildStreets() {
		int available = 0;
		for(Street s : streetArr) {			
			if(s.isBuild()) {
				available++;
			}
		}
		return  available;
	}
	
	
	
	public int getAmountAvailableVillages() {
		int available = 0;
		for(Village v : villageArr) {			
			if(!v.isBuild()) {
				available++;
			}
		}
		return  available;
	}
	
	public int getAmountAvailableCities() {
		int available = 0;
		for(City c : cityArr) {			
			if(!c.isBuild()) {
				available++;
			}
		}
		return  available;
	}
	
	public int getAmountAvailableStreets() {
		int available = 0;
		for(Street s : streetArr) {			
			if(!s.isBuild()) {
				available++;
			}
		}
		return  available;
	}
	
	public Village getAvailableVillage() {
		for(Village v : villageArr) {			
			if(!v.isBuild()) {
				return v;
			}
		}
		return null;
	}
	
	public City getAvailableCity() {
		for(City c : cityArr) {				
			if(!c.isBuild()) {				
				return c;
			}
		}
		return null;
	}
	
	public Street getAvailableStreet() {		
		for(Street s : streetArr) {				
			if(!s.isBuild()) {				
				return s;
			}
		}
		return null;
	}
	
	public boolean getHasLargestArmy() {
		return hasLargestArmy;
	}
	
	public void setHasLargestArmy(boolean hasLargestArmy) {
		this.hasLargestArmy = hasLargestArmy;
	}
	
	public boolean getHasLongestRoad() {
		return hasLongestRoad;
	}
	
	public void setHasLongestRoad(boolean hasLongestRoad) {
		this.hasLongestRoad = hasLongestRoad;
	}

	public int getSettlements() {
		return settlements;
	}

	public int getCities() {
		return cities;
	}

	public int getRoads() {
		return roads;
	}

	public void unload() {
		color                 = null;
		playStatus            = null;
		hand                  = null; 
		overwinningspunten    = 0; 
		points                = 0;
		villageArr            = null;
		cityArr               = null;
		streetArr             = null;
		                           
		
	}
	
	public int getVictoryPoints() {
		int victoryPoints = 0;
		
		//1 point for every village a player has built. 
		for(Village village : villageArr) {
			if(village.isBuild()) {
				victoryPoints++;
			}
		}
		
		//2 points for every city a player has built. 
		for(City city : cityArr) {
			if (city.isBuild()) {
				victoryPoints += 2;
			}
		}
		
		//1 point for every Victory Point card the player has played. 
		for(DevelopmentCard developmentCard : hand.getDevelopmentCards()) {
			if(developmentCard.getDevelopmentCardType() 
					== DevelopmentCardType.VICTORY_POINT) {
				victoryPoints++;
			}
		}
		
		if(hasLargestArmy) {
			victoryPoints += 2;
		}
		
		if(hasLongestRoad) {
			victoryPoints +=2;
		}
		
		return victoryPoints;
	}

	public int getAmountOfKnights() {
		int ret = 0;
		for(DevelopmentCard d : getHand().getDevelopmentCards()) {
			if(d.getDevelopmentCardType() == DevelopmentCardType.KNIGHT && d.isPlayed()) {
				ret++;
			}
		}
		
		return ret;
		
	}
	
	
}
