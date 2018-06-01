package model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

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
	private Random random;

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
	// public Bank(ArrayList<String> resourceIDs, ArrayList<String>
	// developmentCardIDs, ArrayList<Boolean> played) {
	// resources = new ArrayList<>();
	// developmentCards = new ArrayList<>();
	// random = new Random();
	//// addResourcesFromDatabase(resourceIDs);
	// try {
	//// addDevelopmentCardsFromDatabase(developmentCardIDs, played);
	// } catch (Exception e) {
	// System.out.println("developmentCardIDs and played don't have the same
	// size!");
	// e.printStackTrace();
	// }
	// }

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
		random = new Random();
	}

	/**
	 * @param resourceIDs
	 *            An {@code ArrayList<String>} of all the {@code resourceID}'s as
	 *            found in the database.
	 * 
	 * @since 21 May 2018
	 * @author Jasper Mooren
	 */
	// private void addResourcesFromDatabase(ArrayList<String> resourceIDs) {
	// for (int i = 0; i < resourceIDs.size(); i++) {
	// resources.add(new Resource(resourceIDs.get(i)));
	// }
	// }
	//
	// private void addDevelopmentCardsFromDatabase(ArrayList<String>
	// developmentCardIDs, ArrayList<Boolean> played)
	// throws InvalidParameterException {
	// if (developmentCardIDs.size() == played.size()) {
	// for (int i = 0; i < developmentCardIDs.size(); i++) {
	// developmentCards.add(new DevelopmentCard(developmentCardIDs.get(i),
	// played.get(i)));
	// }
	// } else {
	// throw new InvalidParameterException();
	// }
	// }

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

	// public Resource takeResource(ResourceType rsType) {
	// Resource rsToReturn;
	// for (int i = 0; i < resources.size(); i++) {
	// if (resources.get(i).getRsType() == rsType) {
	// rsToReturn = resources.get(i);
	// resources.remove(i);
	// return rsToReturn;
	// }
	// }
	// return null;
	// }

	public void addMultipleResources(ArrayList<Resource> resourcesToAdd) {
		for (Resource rs : resourcesToAdd) {
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

	
	/**
	 * 
	 * 
	 * @param resourceType the type of resource
	 * @param amount the amount of {@code Resources} that should be taken from the {@code ResourceType}
	 * @return The resources that are taken from the bank, if the bank has less resources of the ResourceType than demanded, all the resources it can return are returned. 
	 * @since 1 Jun 2018
	 * @author Jasper Mooren
	 */
	public ArrayList<Resource> takeMultipleResources(ResourceType resourceType, int amount) {
		ArrayList<Resource> resourcesTaken = new ArrayList<>();
		for(int i = 0; i < amount; i++) {
			try {
				resourcesTaken.add(takeResource(resourceType));				
			}
			catch (Exception e) {
					return resourcesTaken;
			}

	public DevelopmentCard takeDevelopmentCard() {
		int index = random.nextInt(developmentCards.size());
		return developmentCards.remove(index);

	}

// 	public ArrayList<Resource> takeMultipleResources(ResourceType rsType, int amount) {
// 		ArrayList<Resource> rsToReturn = new ArrayList<>();
// 		int counter = 0;
// 		for (int i = 0; i < resources.size(); i++) {
// 			if (resources.get(i).getRsType() == rsType) {
// 				rsToReturn.add(resources.get(i));
// 				System.out.println("add:" + resources.get(i));
// 				counter++;
// 				if (counter == amount) {
// 					break;
// 				}
// 			}
// 		}

// 		if (rsToReturn.size() == amount) {
// 			for (int x = 0; x < resources.size(); x++) {
// 				if (resources.get(x).getRsType() == rsType) {
// 					resources.remove(x);
// 				}
// 			}
// 			System.out.println("tmr return: " + rsToReturn);
// 			return rsToReturn;
// 		}
// 		return resourcesTaken;
// 	}

	
//	public ArrayList<Resource> takeMultipleResources(ResourceType rsType, int amount) {
//		ArrayList<Resource> rsToReturn = new ArrayList<>();
//		int counter = 0;
//		for(int i = 0; i < resources.size(); i++) {
//			if(resources.get(i).getRsType() == rsType) {
//				rsToReturn.add(resources.get(i));
//				System.out.println("add:" + resources.get(i));
//				counter++;
//				if(counter == amount) {
//					break;
//				}
//			}
//		}
//		
//		if(rsToReturn.size() == amount) {
//			for(int x = 0; x < resources.size(); x++) {
//				if(resources.get(x).getRsType() == rsType) {
//					resources.remove(x);			
//				}
//			}
//			System.out.println("tmr return: "+ rsToReturn);
//			return rsToReturn;
//		}
//		return null;
//	}

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
