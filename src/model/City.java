package model;

public class City extends Building {

	private BuildingLocation buildingLocation;
	public final static ResourceType[] cost = new ResourceType[] { ResourceType.GRAAN, ResourceType.GRAAN,
			ResourceType.ERTS, ResourceType.ERTS, ResourceType.ERTS };

	public City(String dbidpiece) {
		idBuilding = dbidpiece;
		victoryPoint = 2;
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}

	public boolean isBuild() {
		if (buildingLocation == null) {
			return false;
		}
		return true;
	}
}
