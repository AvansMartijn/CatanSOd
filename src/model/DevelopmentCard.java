package model;

public class DevelopmentCard {

	private ResourceType[] cardCost;
	private DevelopmentCardType developmentCardType;
	private String developmentCardID;
	private boolean played;
	
	/**
	 * DevelopmentCard add it from database.
	 * 
	 * @param developmentCardID format: o[0-9][0-9][g,s,m,u,r]. This is from the database. 
	 * @param played if the development card has been played. 
	 * @param developmentCardType which development Card
	 * @since 21 May 2018
	 * @author Jasper Mooren
	 */
	public DevelopmentCard(String developmentCardID, boolean played, DevelopmentCardType developmentCardType) {
		cardCost = new ResourceType[] {ResourceType.GRAAN, ResourceType.WOL, ResourceType.ERTS};
		this.developmentCardID = developmentCardID;
		this.played = played;
		this.developmentCardType = developmentCardType;
	}

	public ResourceType[] getCardCost() {
		return cardCost;
	}
	
	public DevelopmentCardType getDevelopmentCardType() {
		return developmentCardType;
	}

	public String getDevelopmentCardID() {
		return developmentCardID;
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}
	
	public void play() {
		played = true;
	}
}