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
	
	// Add Resource
	public void addResource(Resource resource) {
		resources.add(resource);
	}
	
	// Add DevelopmentCard
	public void addDevelopmentCard(DevelopmentCard developmentCard) {
		developmentCards.add(developmentCard);
	}
	
	// TODO add multiple resources with function
	// TODO randomdraw
}
