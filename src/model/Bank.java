package model;

import java.util.ArrayList;

public class Bank {

	private final static int AMOUNT_OF_DIFFERENT_RESOURCES = 5;
	private final static int AMOUNT_OF_EACH_RESOURCE = 19;
	private final static int AMOUNT_OF_KNIGHTS = 14;
	private final static int AMOUNT_OF_MONOPOLY = 6;
	private final static int AMOUNT_OF_VICTORYPOINT = 5;	
	
	// Instance variables
	private ArrayList<ArrayList<Resource>> resources;
	private ArrayList<DevelopmentCard> developmentCards;
	
	// Constructor
	public Bank() {
		resources = new ArrayList<ArrayList<Resource>>();
		
		// For the amount of different resources
		for(int i = 0; i < AMOUNT_OF_DIFFERENT_RESOURCES; i++) {
			resources.add(new ArrayList<Resource>());
			
			for(int j = 0; j < AMOUNT_OF_EACH_RESOURCE; j++) {
				ResourceType[] resourceTypes = ResourceType.values(); 
				resources.get(i).add(new Resource(resourceTypes[i]));
			}
		}
		
		developmentCards = new ArrayList<DevelopmentCard>();
	}
	
	// Create
	private void create() {
		
		// Add Knights to developmentCards
		for(int i = 0; i < AMOUNT_OF_KNIGHTS; i++) {
			resources.add(Knight);
		}
		// Add Monopoly to developmentCards
		for(int i = 0; i < AMOUNT_OF_MONOPOLY; i++) {
			resources.add(Monopoly);
		}
		// Add VictoryPoint to developmentCards
		for(int i = 0; i < AMOUNT_OF_VICTORYPOINT; i++) {
			resources.add(VictoryPoint);
		}
	}
}
