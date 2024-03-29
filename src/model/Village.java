package model;

public class Village extends Building {
	
	public final static ResourceType[] cost = new ResourceType[] { ResourceType.BAKSTEEN, ResourceType.HOUT,
			ResourceType.GRAAN, ResourceType.WOL };
	
	private BuildingLocation buildingLocation;
	
	public Village(String dbidpiece) {
		idBuilding = dbidpiece;
		victoryPoint = 1;
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}
	
	public boolean isBuild() {
		if(buildingLocation == null) {			
			return false;
		}
		return true;
	}
}
