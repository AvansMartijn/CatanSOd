package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Hand {

	// Instance variables
	private ArrayList<DevelopmentCard> developmentCards;

	private ArrayList<Resource> resources;
	// private ArrayList<DevelopmentCard> playedDevelopmentCards;

	public Hand() {
		developmentCards = new ArrayList<DevelopmentCard>();
		resources = new ArrayList<Resource>();
	}

	// public void addResource(Resource resource, int amount) {
	// for (int i = 0; i < amount; i++) {
	// addResource(resource);
	// }
	// }s

	public HashMap<ResourceType, Integer> getAmountOfResources() {
		HashMap<ResourceType, Integer> retMap = new HashMap<ResourceType, Integer>();
		int brick = 0;
		int wood = 0;
		int wool = 0;
		int iron = 0;
		int wheat = 0;

		for (Resource rs : resources) {
			switch (rs.getRsType()) {
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

	public void addResource(Resource resource) {
		resources.add(resource);
	}

	public ArrayList<Resource> getResources() {
		return resources;
	}

	public Resource getResource(ResourceType resourceType) {
		for (Resource resource : resources) {
			if (resource.getRsType() == resourceType) {
				return resource;
			}
		}
		return null;
	}

	public DevelopmentCard takeDevelopmentCard(DevelopmentCardType cardType) {
		for (int i = 0; i < developmentCards.size(); i++) {
			if (developmentCards.get(i).getDevelopmentCardType() == cardType) {
				return developmentCards.remove(i);
			}
		}
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

	public Resource takeRandomResource() {
		Resource rsToReturn;
		Random random = new Random();
		int randomInt = random.nextInt(resources.size());
		rsToReturn = resources.get(randomInt);
		resources.remove(randomInt);
		return rsToReturn;
	}
	public Resource takeResource(ResourceType rsType) {
		Resource rsToReturn;
		for (int i = 0; i < resources.size(); i++) {
			if (resources.get(i).getRsType() == rsType) {
				rsToReturn = resources.get(i);
				resources.remove(i);
				return rsToReturn;
			}
		}
		return null;
	}

	public ArrayList<Resource> takeMultipleResources(ResourceType rsType, int amount) {
		ArrayList<Resource> rsToReturn = new ArrayList<>();
		int counter = 0;
		for (int i = 0; i < resources.size(); i++) {
			if (resources.get(i).getRsType() == rsType) {
				rsToReturn.add(resources.get(i));
				System.out.println("add:" + resources.get(i));
				counter++;
				if (counter == amount) {
					break;
				}
			}
		}

		if (rsToReturn.size() == amount) {
			for (int x = 0; x < resources.size(); x++) {
				if (resources.get(x).getRsType() == rsType) {
					resources.remove(x);
				}
			}
			System.out.println("tmr return: " + rsToReturn);
			return rsToReturn;
		}
		return null;
	}

	public ArrayList<Resource> takeAllResourcesFromRsType(ResourceType rsType) {
		ArrayList<Resource> rsToReturn = new ArrayList<>();

		for (int i = 0; i < resources.size(); i++) {
			if (resources.get(i).getRsType() == rsType) {
				rsToReturn.add(resources.get(i));
			}
		}
		for (int x = 0; x < resources.size(); x++) {
			if (resources.get(x).getRsType() == rsType) {
				resources.remove(x);
			}
		}
		return rsToReturn;

	}

	public ArrayList<DevelopmentCard> getDevelopmentCards() {
		return developmentCards;
	}

	public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
		this.developmentCards = developmentCards;
	}
}
