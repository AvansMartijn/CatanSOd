package model;

public class StreetLocation {

	private BuildingLocation blStart;
	private BuildingLocation blEnd;
	private Street street;
	// private int xStart;
	// private int yStart;
	//
	// private int xEnd;
	// private int yEnd;

	public StreetLocation(BuildingLocation blStart, BuildingLocation blEnd) {

		this.blStart = blStart;
		this.blEnd = blEnd;
		// this.xStart = xStart;
		// this.yStart = yStart;
		//
		// this.xEnd = xEnd;
		// this.yEnd = yEnd;

	}

	public BuildingLocation getBlStart() {
		return blStart;
	}

	public void setBlStart(BuildingLocation blStart) {
		this.blStart = blStart;
	}

	public BuildingLocation getBlEnd() {
		return blEnd;
	}

	public void setBlEnd(BuildingLocation blEnd) {
		this.blEnd = blEnd;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public boolean hasAdjecentFriendlySettlement(Player player) {
		boolean hasSettlement = false;
		if (getBlStart().getCity() != null) {
			if (getBlStart().getCity().getPlayer() == player) {
				hasSettlement = true;
			}
		}
		if (getBlStart().getVillage() != null) {
			if (getBlStart().getVillage().getPlayer() == player) {
				hasSettlement = true;
			}
		}
		if (getBlEnd().getCity() != null) {
			if (getBlEnd().getCity().getPlayer() == player) {
				hasSettlement = true;
			}
		}
		if (getBlEnd().getVillage() != null) {
			if (getBlEnd().getVillage().getPlayer() == player) {
				hasSettlement = true;
			}
		}

		return hasSettlement;
	}

	public boolean hasAdjecentFriendlyStreet(Player player) {
		boolean hasStreet = false;

		for (StreetLocation s : getBlStart().getAdjecentStreets()) {
			if (s.getStreet() != null) {
				if (s.getStreet().getPlayer() == player) {
					hasStreet = true;
				}
			}
		}

		for (StreetLocation s : getBlEnd().getAdjecentStreets()) {
			if (s.getStreet() != null) {
				if (s.getStreet().getPlayer() == player) {
					hasStreet = true;
				}
			}
		}

		return hasStreet;
	}

}
