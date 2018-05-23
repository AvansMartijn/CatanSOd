package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Hand {

	// Instance variables
	private ArrayList<DevelopmentCard> developmentCards;

	private ArrayList<Resource> resources;
	// private ArrayList<DevelopmentCard> playedDevelopmentCards;

	// Constructor
	public Hand() {
		developmentCards = new ArrayList<DevelopmentCard>();
		resources = new ArrayList<Resource>();
	}

	// Add Resources
	public void addResource(Resource resource, int amount) {
		// For the amount of resources
		for (int i = 0; i < amount; i++) {
			addResource(resource);
		}
	}

	public HashMap<ResourceType, Integer> getAmountOfResources() {
		HashMap<ResourceType, Integer> retMap = new HashMap<ResourceType, Integer>();
		int brick  = 0;
		int wood = 0;
		int wool = 0;
		int iron = 0;
		int wheat = 0;
		
		for(Resource rs: resources) {
			switch(rs.getRsType()) {
			case BAKSTEEN:
				brick++;
				break;
			case ERTS:
				iron++;
				break;
			case WOL:
				wool++;
				break;
			case HOUT:
				wood++;
				break;
			case GRAAN:
				wheat++;
				break;
			case WOESTIJN:
				break;
			default:
				break;
			}
		}
		retMap.put(ResourceType.BAKSTEEN, brick);
		retMap.put(ResourceType.ERTS, iron);
		retMap.put(ResourceType.GRAAN, wheat);
		retMap.put(ResourceType.HOUT, wood);
		retMap.put(ResourceType.WOL, wool);
		return retMap;
	}

	// Add Resource
	public void addResource(Resource resource) {
		resources.add(resource);
	}

	// Get ArrayList resources
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	public DevelopmentCard takeDevelopmentCard(DevelopmentCardType cardType) {
		for (int i = 0; i < developmentCards.size(); i++) {
			if (developmentCards.get(i).getDevelopmentCardType() == cardType) {
				return developmentCards.remove(i);
			}
		}
		// Player has no developmentCards of this type.
		return null;
	}

	// public int getAmountOfDevelopmentCardsPlayed(int cardType) {
	// int amountOfDevelopmentCards = 0;
	// for (int i = 0; i < playedDevelopmentCards.size(); i++) {
	// if(playedDevelopmentCards.get(i).getDevelopmentCardType() == cardType) {
	// amountOfDevelopmentCards++;
	// }
	// }
	// return amountOfDevelopmentCards;
	// }

	// public void playDevelopmentCard(int cardType) {
	// DevelopmentCard developmentCard = takeDevelopmentCard(cardType);
	// playedDevelopmentCards.add(developmentCard);
	// }

	// Add DevelopmentCard
	public void addDevelopmentCard(DevelopmentCard developmentCard) {
		developmentCards.add(developmentCard);
	}

	// TODO randomdraw
	// Randomly draw card
	public void drawCardRandomly() {

	}

	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}

	public void takeresource() {

	}

	public ArrayList<DevelopmentCard> getDevelopmentCards() {
		return developmentCards;
	}

	public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
		this.developmentCards = developmentCards;
	}
}
