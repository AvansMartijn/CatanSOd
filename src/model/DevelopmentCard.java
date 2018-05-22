package model;

import java.security.InvalidParameterException;

public class DevelopmentCard {

	public static final ResourceType[] CARD_COST = 
		{ResourceType.GRAAN, ResourceType.WOL, ResourceType.ERTS};
	private DevelopmentCardType developmentCardType;
	private String developmentCardID;
	private boolean played;

	public DevelopmentCard(String developmentCardID, boolean played) {
		this.developmentCardID = developmentCardID;
		this.played = played;
		switch(developmentCardID.substring(3, 4)){
		case "r": // ridder
			developmentCardType = DevelopmentCardType.KNIGHT;
			break;
		case "g": //victory point
			developmentCardType = DevelopmentCardType.VICTORY_POINT;
			break;
		case "s": // stratenbouw
			developmentCardType = DevelopmentCardType.ROAD_BUILDING;
			break;
		case "m": // monopoly
			developmentCardType = DevelopmentCardType.MONOPOLY;
			break;
		case "u": // uitvinder
			developmentCardType = DevelopmentCardType.YEAR_OF_PLENTY;
			break;
		}
	}
	
	/**
	 * This creates and returns a {@code new DevelopmentCard} based in an {@code id}.
	 * id goes from 0-24:
	 * o01g, o02g, o03g, o04g, o05g, o06s, o07s, o08m, o09m, o10u, o11u, o12r, o13r, 
	 * o14r, o15r, o16r, o17r, o18r, o19r, o20r, o21r, o22r, o23r, o24r, o25r.
	 * 
	 * @param id the ID of the DevelopmentCard
	 * @return a {@code new DevelopmentCard object}
	 */
	public DevelopmentCard createDevelopmentCard(int id) throws InvalidParameterException {
		if(id < 0 || id > 24) {
			throw new InvalidParameterException();
		}
		String developmentCardID = "o";
		if(id < 9) {
			developmentCardID += "0";
		}
		developmentCardID += (id + 1);
		if(id < 5) {
			developmentCardID += "g";
		}
		else if(id < 7) {
			developmentCardID += "s";
		}
		else if(id < 9) {
			developmentCardID += "m";
		}
		else if(id < 11) {
			developmentCardID += "u";
		}
		else {
			developmentCardID += "r";
		}
		return new DevelopmentCard(developmentCardID, false);
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