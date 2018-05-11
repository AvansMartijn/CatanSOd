package model;

import java.util.ArrayList;

public class Hand {
		
	// Instance variables
	private ArrayList<DevelopmentCard> developmentCards;
	private ArrayList<Resource> resources;
	private ArrayList<DevelopmentCard> playedDevelopmentCards;
	
	// Constructor
	public Hand() {
		developmentCards = new ArrayList<>();
		playedDevelopmentCards = new ArrayList<>();
		resources = new ArrayList<>();
	}
	
	// Add Resources
	
	/**
	 * This method shoudn't be used, since you are now adding the same object several times. 
	 * This means that the same object has several references instead of several Resource objects.
	 * I understand the idea, but in practice this doesn't work.
	 * <p>
	 * JavaDoc made by Jasper Mooren
	 */
	@Deprecated
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
	
	/**
	 * This method randomly takes any {@code Resource} from the players hand and returns it.
	 * <p>
	 * <b>Always make sure that the return value is put somewhere, 
	 * otherwise you are destroying resources from the game!</b>
	 * 
	 * @return the card that is randomly picked from the player. 
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	public Resource takeResourceRandomly() {
		int random = (int) (Math.random() * resources.size());
		return resources.remove(random);
	}
	
	/**
	 * This method takes the first resource it finds of the specific resourceType.
	 * <p>
	 * <b>Always make sure that the return value is put somewhere, 
	 * otherwise you are destroying resources from the game!</b>
	 * Often the resource will be put into the bank. Sometimes it might be another player. 
	 * 
	 * @param resourceType the type of resource that you want to get a card from. 
	 * @return The {@code Resource}, or {@code null} if the {@code Hand} does not have a {@code Resource} of this type.
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	public Resource takeResource(ResourceType resourceType) {
		for (int i = 0; i < resources.size(); i++) {
			if(resources.get(i).hasResourceType(resourceType)) {
				return resources.remove(i);
			}
		}
		//if this hand has no resources of this type. 
		return null;
	}
	
	/**
	 * This removes the {@code DevelopmentCard} from the players hand and returns it. 
	 * <p>
	 * <b>Always make sure that the return value is put somewhere, 
	 * otherwise you are destroying {@code DevelopmentCard}s from the game!</b>
	 * 
	 * Only the Player class should use this method. 
	 * 
	 * @param cardType the subclass of {@code DevelopmentCard} that should be taken. <br>
	 * Use the constants from the {@code DevelopmentCard} class for the values. 
	 * @return The {@code DevelopmentCard}, or {@code null} if the {@code Hand} does not have this type of {@code DevelopmentCard}.
	 * {@code if(cardType < 0 || cardType > 4} also {@code null} is returned. 
	 * 
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	public DevelopmentCard takeDevelopmentCard(int cardType) {
		for (int i = 0; i < developmentCards.size(); i++) {
			if(developmentCards.get(i).getDevelopmentCardType() == cardType) {
				return developmentCards.remove(i);
			}
		}
		//Player has no developmentCards of this type. 
		return null;
	}
	
	/**
	 * Play a {@code Knight} card.
	 * Removes a {@code Knight} card from the players playable cards and adds it to the {@code knightsPlayed} ArrayList. 
	 * 
	 * @param cardType the subclass of {@code DevelopmentCard} that should be taken. <br>
	 * Use the constants from the {@code DevelopmentCard} class for the values. 
	 * 
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	public void playDevelopmentCard(int cardType) {
		DevelopmentCard developmentCard = takeDevelopmentCard(cardType);
		playedDevelopmentCards.add(developmentCard);
	}
	
	/**
	 * Counts all {@code DevelopmentCard}s of a certain type. 
	 * 
	 * This can mostly be used to count the amount of Knights for the LargestArmy card 
	 * and the amount of VictoryPoints for the amount of points a player has. 
	 * 
	 * @param cardType the subclass of {@code DevelopmentCard} that should be taken. <br>
	 * Use the constants from the {@code DevelopmentCard} class for the values. 
	 * @return the amount of {@code DevelopmentCard}s played of the specific type. 
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	public int getAmountOfDevelopmentCardsPlayed(int cardType) {
		int amountOfDevelopmentCards = 0;
		for (int i = 0; i < playedDevelopmentCards.size(); i++) {
			if(playedDevelopmentCards.get(i).getDevelopmentCardType() == cardType) {
				amountOfDevelopmentCards++;
			}
		}
		return amountOfDevelopmentCards;
	}
}