package model;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

	private ArrayList<Resource> resources;
	private ArrayList<DevelopmentCard> developmentCards;
	private Random random;

	public Bank() {
		resources = new ArrayList<>();
		developmentCards = new ArrayList<>();
		random = new Random();
	}

	public void addMultipleResources(ArrayList<Resource> resourcesToAdd) {
		for (Resource rs : resourcesToAdd) {
			System.out.println(rs.getRsType());
			resources.add(rs);
		}
	}

	public ArrayList<Resource> getResources() {
		return resources;
	}

	public Resource takeResource(ResourceType resourceType) throws Exception {
		for (int i = 0; i < resources.size(); i++) {
			if (resources.get(i).getRsType() == resourceType) {
				return resources.remove(i);
			}
		}
		throw new Exception();
	}

	public ArrayList<Resource> takeMultipleResources(ResourceType resourceType, int amount) {
		ArrayList<Resource> resourcesTaken = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			try {
				resourcesTaken.add(takeResource(resourceType));
			} catch (Exception e) {
				return resourcesTaken;
			}
		}
		return resourcesTaken;
	}

	public DevelopmentCard takeDevelopmentCard() {
		if (developmentCards.size() > 0) {
			System.out.println(developmentCards.size());
			int index = random.nextInt(developmentCards.size());
			System.out.println(index);
			return developmentCards.remove(index);
		} else {
			return null;
		}

	}

	public void addResource(Resource resourceToAdd) {
		resources.add(resourceToAdd);
	}

	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}

	public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
		this.developmentCards = developmentCards;
	}

}
