package controller;

import java.util.ArrayList;

import model.BuildingLocation;
import model.Harbour;
import model.ResourceType;

public class GameBoardControl {
	private ArrayList<BuildingLocation> buildingLocArr = new ArrayList<BuildingLocation>();
	
	public void createBoard() {
		createBuildingLocations();

	}
	
	private void createBuildingLocations() {
		buildingLocArr.add(new BuildingLocation(1, 3, null));
		buildingLocArr.add(new BuildingLocation(1, 4, null));
		buildingLocArr.add(new BuildingLocation(2, 2, new Harbour(ResourceType.BAKSTEEN)));
		buildingLocArr.add(new BuildingLocation(2, 3, new Harbour(ResourceType.BAKSTEEN)));
		buildingLocArr.add(new BuildingLocation(2, 5, new Harbour(ResourceType.HOUT)));
		buildingLocArr.add(new BuildingLocation(2, 6, new Harbour(ResourceType.HOUT)));
		buildingLocArr.add(new BuildingLocation(3, 1, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation(3, 2, null));
		buildingLocArr.add(new BuildingLocation(3, 4, null));
		buildingLocArr.add(new BuildingLocation(3, 5, null));
		buildingLocArr.add(new BuildingLocation(3, 7, null));
		buildingLocArr.add(new BuildingLocation(3, 8, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation(4, 1, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation(4, 3, null));
		buildingLocArr.add(new BuildingLocation(4, 4, null));
		buildingLocArr.add(new BuildingLocation(4, 6, null));
		buildingLocArr.add(new BuildingLocation(4, 7, null));
		buildingLocArr.add(new BuildingLocation(4, 9, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation(5, 2, null));
		buildingLocArr.add(new BuildingLocation(5, 3, null));
		buildingLocArr.add(new BuildingLocation(5, 5, null));
		buildingLocArr.add(new BuildingLocation(5, 6, null));
		buildingLocArr.add(new BuildingLocation(5, 8, null));
		buildingLocArr.add(new BuildingLocation(5, 9, null));
		buildingLocArr.add(new BuildingLocation(6, 2, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation(6, 4, null));
		buildingLocArr.add(new BuildingLocation(6, 5, null));
		buildingLocArr.add(new BuildingLocation(6, 7, null));
		buildingLocArr.add(new BuildingLocation(6, 8, null));
		buildingLocArr.add(new BuildingLocation(6, 10, new Harbour(ResourceType.GRAAN)));
		buildingLocArr.add(new BuildingLocation(7, 3, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation(7, 4, null));
		buildingLocArr.add(new BuildingLocation(7, 6, null));
		buildingLocArr.add(new BuildingLocation(7, 7, null));
		buildingLocArr.add(new BuildingLocation(7, 9, null));
		buildingLocArr.add(new BuildingLocation(7, 10, new Harbour(ResourceType.GRAAN)));
		buildingLocArr.add(new BuildingLocation(8, 3, null));
		buildingLocArr.add(new BuildingLocation(8, 5, null));
		buildingLocArr.add(new BuildingLocation(8, 6, null));
		buildingLocArr.add(new BuildingLocation(8, 8, null));
		buildingLocArr.add(new BuildingLocation(8, 9, null));
		buildingLocArr.add(new BuildingLocation(8, 11, null));
		buildingLocArr.add(new BuildingLocation(9, 4, null));
		buildingLocArr.add(new BuildingLocation(9, 5, new Harbour(ResourceType.WOL)));
		buildingLocArr.add(new BuildingLocation(9, 7, null));
		buildingLocArr.add(new BuildingLocation(9, 8, null));
		buildingLocArr.add(new BuildingLocation(9, 10, new Harbour(ResourceType.ERTS)));
		buildingLocArr.add(new BuildingLocation(9, 11, null));
		buildingLocArr.add(new BuildingLocation(10, 6, new Harbour(ResourceType.WOL)));
		buildingLocArr.add(new BuildingLocation(10, 7, null));
		buildingLocArr.add(new BuildingLocation(10, 9, null));
		buildingLocArr.add(new BuildingLocation(10, 10, new Harbour(ResourceType.ERTS)));
		buildingLocArr.add(new BuildingLocation(11, 8, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation(11, 9, new Harbour(null)));
		
		
		
	}

}
