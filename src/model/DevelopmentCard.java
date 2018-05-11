package model;

public abstract class DevelopmentCard {

	public static final int KNIGHT = 0;
	public static final int YEAR_OF_PLENTY = 1;
	public static final int VICTORY_POINT = 2;
	public static final int ROAD_BUILDING = 3;
	public static final int MONOPOLY = 4;
	
	private Resource[] cardCost;
	
	// Constructor
	public DevelopmentCard() {
		cardCost = new Resource[] {new Resource(ResourceType.GRAAN), new Resource(ResourceType.WOL), new Resource(ResourceType.ERTS)};
	}
	
	public int getDevelopmentCardType() {
		if(this instanceof Knight) {
			return 0;
		}
		else if(this instanceof YearOfPlenty) {
			return 1;
		}
		else if(this instanceof VictoryPoint) {
			return 2;
		}
		else if(this instanceof RoadBuilding) {
			return 3;
		}
		else if(this instanceof Monopoly) {
			return 4;
		}
		//This cannot happen, all possibilities have been taken into account.
		//This only exists because of errors otherwise.
		else return -1;
	}
}
