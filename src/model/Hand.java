package model;

import java.util.ArrayList;

public class Hand {
	
	// Instance variables
	private ArrayList<DevelopmentCard> developmentCards;
	private ArrayList<Resource> resources;

	// Constructor
	public Hand() {
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
	
	// Get ArrayList resources
	public ArrayList<Resource> getResources() {
		return resources;
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
