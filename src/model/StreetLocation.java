package model;

public class StreetLocation {

	private BuildingLocation blStart;
	private BuildingLocation blEnd;
	private Street street;

	public StreetLocation(BuildingLocation blStart, BuildingLocation blEnd) {

		this.blStart = blStart;
		this.blEnd = blEnd;
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

	public boolean hasAdjacentFriendlySettlement(Player player) {
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

		if (getBlStart().getBuilding() != null) {
			if (getBlStart().getBuilding().getPlayer() == player) {

				for (StreetLocation s : getBlStart().getAdjacentStreetLocations()) {
					if (s.getStreet() != null) {
						if (s.getStreet().getPlayer() == player) {
							hasStreet = true;
						}
					}
				}
			}
		} else {
			for (StreetLocation s : getBlStart().getAdjacentStreetLocations()) {
				if (s.getStreet() != null) {
					if (s.getStreet().getPlayer() == player) {
						hasStreet = true;
					}
				}
			}
		}
		if (getBlEnd().getBuilding() != null) {
			if (getBlEnd().getBuilding().getPlayer() == player) {
				for (StreetLocation s : getBlEnd().getAdjacentStreetLocations()) {
					if (s.getStreet() != null) {
						if (s.getStreet().getPlayer() == player) {
							hasStreet = true;
						}
					}
				}
			}
		} else {
			for (StreetLocation s : getBlEnd().getAdjacentStreetLocations()) {
				if (s.getStreet() != null) {
					if (s.getStreet().getPlayer() == player) {
						hasStreet = true;
					}
				}
			}
		}
		return hasStreet;
	}

}
