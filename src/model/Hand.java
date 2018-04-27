package model;

import java.util.ArrayList;

public class Hand {
	
	// Instance variables
	private ArrayList<DevelopmentCard> developmentCards;
	private ArrayList<Resource> resources;
	private Player player;
	// Constructor
	public Hand() {
		player = new Player(4);
		developmentCards = new ArrayList<DevelopmentCard>();
		resources = new ArrayList<Resource>();
	}
	
	// Add Resources
	public void addResource(Resource resource, int amount) {
		// For the amount of resources
		for(int i = 0; i < amount; i++) {
			addResource(resource);
		}
	}
	
	// Add Resource
	public void addResource(Resource resource) {
		resources.add(resource);
	}
	
	// Add DevelopmentCard
	public void addDevelopmentCard(DevelopmentCard developmentCard) {
		developmentCards.add(developmentCard);
	}
	
	// TODO randomdraw
	// Randomly draw card
	public void drawCardRandomly() {
		
	}
}
