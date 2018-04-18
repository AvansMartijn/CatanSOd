package model;

import java.util.ArrayList;

public class Bank {

	// Instance variables
	private ArrayList<ArrayList<Resource>> resources;
	private ArrayList<DevelopmentCard> developmentCards;
	
	// Constructor
	public Bank() {
		resources = new ArrayList<ArrayList<Resource>>();
		developmentCards = new ArrayList<DevelopmentCard>();
	}
}
