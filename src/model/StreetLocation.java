package model;

public class StreetLocation {

	private BuildingLocation[] buildingLocations;
	
	/**
	 * This creates a {@code StreetLocation} based on {@code BuildingLocation}s.
	 * Watch out that you don't make a {@code StreetLocation} that already exists.
	 * Since if bl1 makes this {@code StreetLocation} and 
	 * bl2 makes this {@code StreetLocation} as well, there is an issue.
	 * 
	 * @param bl1 a buildingLocation this StreetLocation is next to
	 * @param bl2 a buildingLocation this StreetLocation is next to
	 * @since 11 May 2018
	 * @author Jasper Mooren
	 */
	public StreetLocation(BuildingLocation bl1, BuildingLocation bl2) {
		buildingLocations = new BuildingLocation[2];
		buildingLocations[0] = bl1;
		buildingLocations[1] = bl2;
	}

	public BuildingLocation getBuildingLocation(int index) {
		return buildingLocations[index];
	}
}
