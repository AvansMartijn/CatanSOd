package model;

import java.security.InvalidParameterException;
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
	private ArrayList<Resource> resources;
	private ArrayList<DevelopmentCard> developmentCards;

	// Constructor

	/**
	 * This constructor should be used to add a {@code Bank} from with all the
	 * information from the Database.
	 * 
	 * @param resourceIDs
	 *            all the ID's of the resources that should be added to the bank.
	 * @param developmentCardIDs
	 *            all the ID's of the development cards that should be added to the
	 *            bank.
	 * @since 21 May 2018
	 * @author Jasper Mooren
	 */
	public Bank(ArrayList<String> resourceIDs, ArrayList<String> developmentCardIDs, ArrayList<Boolean> played) {
		resources = new ArrayList<>();
		developmentCards = new ArrayList<>();
		addResourcesFromDatabase(resourceIDs);
		try {
			addDevelopmentCardsFromDatabase(developmentCardIDs, played);
		} catch (Exception e) {
			System.out.println("developmentCardIDs and played don't have the same size!");
			e.printStackTrace();
		}
	}

	/**
	 * This constructor should be used to create a {@code Bank} in a new game.
	 * 
	 * @since 21 May 2018
	 * @author Jasper Mooren
	 */
	public Bank() {
		resources = new ArrayList<>();
		developmentCards = new ArrayList<>();
		createResourceCards();
		createDevelopmentCards();
	}

	/**
	 * @param resourceIDs
	 *            An {@code ArrayList<String>} of all the {@code resourceID}'s as
	 *            found in the database.
	 * 
	 * @since 21 May 2018
	 * @author Jasper Mooren
	 */
	private void addResourcesFromDatabase(ArrayList<String> resourceIDs) {
		for (int i = 0; i < resourceIDs.size(); i++) {
			resources.add(new Resource(resourceIDs.get(i)));
		}
	}

	private void addDevelopmentCardsFromDatabase(ArrayList<String> developmentCardIDs, ArrayList<Boolean> played)
			throws InvalidParameterException {
		if (developmentCardIDs.size() == played.size()) {
			for (int i = 0; i < developmentCardIDs.size(); i++) {
				developmentCards.add(new DevelopmentCard(developmentCardIDs.get(i), played.get(i)));
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	// Add Resources to resources ArrayList
	private void createResourceCards() {
		String[] resourceType = { "b", "e", "g", "h", "w" };
		String resourceID = "";
		for (int i = 0; i < AMOUNT_OF_DIFFERENT_RESOURCES; i++) {
			for (int j = 0; j < AMOUNT_OF_EACH_RESOURCE; j++) {
				if (j < 9) {
					resourceID = resourceType[i] + "0" + (j + 1);
				} else {
					resourceID = resourceType[i] + (j + 1);
				}
				resources.add(new Resource(resourceID));
			}
		}
	}

	// Add DevelopmentCards to developmentCards ArrayList
	private void createDevelopmentCards() {

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

	public void addMultipleResources(ArrayList<Resource> resourcesToAdd) {
		for (Resource rs : resourcesToAdd) {
			resources.add(rs);
		}
	}
	
	public ArrayList<Resource> getResources(){
		return resources;
	}
	
	public Resource takeResource(ResourceType resourceType) {
		for (int i = 0; i < resources.size(); i++) {
			if(resources.get(i).getRsType() == resourceType) {
				return resources.remove(i);
			}
		}
		return null;
	}
}
