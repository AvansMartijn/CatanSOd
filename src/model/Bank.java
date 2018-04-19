package model;

public class Bank {

import java.util.ArrayList;

public class Bank {

	private final static int AMOUNT_OF_DIFFERENT_RESOURCES = 5;
	private final static int AMOUNT_OF_EACH_RESOURCE = 19;
	private final static int AMOUNT_OF_KNIGHTS = 14;
	private final static int AMOUNT_OF_MONOPOLY = 2;
	private final static int AMOUNT_OF_ROADBUILDING = 2;
	private final static int AMOUNT_OF_YEAROFPLENTY = 2;
	private final static int AMOUNT_OF_VICTORYPOINT = 5;

	// Instance variables
	private ArrayList<ArrayList<Resource>> resources;
	private ArrayList<DevelopmentCard> developmentCards;

	// Constructor
	public Bank() {
		resources = new ArrayList<ArrayList<Resource>>();
		createResourceCards();
		developmentCards = new ArrayList<DevelopmentCard>();
		createDevelopmentCards();
	}

	// Add Resources to resources ArrayList
	private void createResourceCards() {

		// For the amount of different resources
		for (int i = 0; i < AMOUNT_OF_DIFFERENT_RESOURCES; i++) {
			resources.add(new ArrayList<Resource>());
			// For the amount needed of each resource
			for (int j = 0; j < AMOUNT_OF_EACH_RESOURCE; j++) {
				ResourceType[] resourceTypes = ResourceType.values();
				resources.get(i).add(new Resource(resourceTypes[i]));
			}
		}
	}

	// Add DevelopmentCards to developmentCards ArrayList
	private void createDevelopmentCards() {

		// Arrays
		DevelopmentCard[] typeCard = new DevelopmentCard[] { new Knight(), new Monopoly(), new RoadBuilding(),
				new YearOfPlenty(), new VictoryPoint() };
		int amountOfCards[] = new int[] { AMOUNT_OF_KNIGHTS, AMOUNT_OF_MONOPOLY, AMOUNT_OF_ROADBUILDING,
				AMOUNT_OF_YEAROFPLENTY, AMOUNT_OF_VICTORYPOINT };

		// for the amount of typeCards
		for (int i = 0; i < typeCard.length; i++) {
			// for the amount of cards needed of a typeCard
			for (int j = 0; j < amountOfCards[i]; j++) {
				developmentCards.add(typeCard[i]);
			}
		}
	}
}
