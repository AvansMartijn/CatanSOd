package controller;

import java.util.ArrayList;

import model.BuildingLocation;
import model.Harbour;
import model.ResourceType;
import model.StreetLocation;
import model.Tile;

public class GameBoardControl {
	private ArrayList<BuildingLocation> buildingLocArr = new ArrayList<BuildingLocation>();
	private ArrayList<StreetLocation> streetLocArr = new ArrayList<StreetLocation>();
	private ArrayList<Tile> tileArr = new ArrayList<Tile>();
	
	public void createBoard() {
		createTiles();
		createBuildingLocations();
		

	}
	
	private void createStreetLocations() {
		
	}
	
	private void createTiles() {
		tileArr.add(new Tile(2, 4, ResourceType.GRAAN, 9));
		tileArr.add(new Tile(3, 3, ResourceType.HOUT, 8));
		tileArr.add(new Tile(3, 6, ResourceType.GRAAN, 12));
		tileArr.add(new Tile(4, 2, ResourceType.BAKSTEEN, 5));
		tileArr.add(new Tile(4, 5, ResourceType.HOUT, 11));
		tileArr.add(new Tile(4, 8, ResourceType.ERTS, 10));
		tileArr.add(new Tile(5, 4, ResourceType.ERTS, 3));
		tileArr.add(new Tile(5, 7, ResourceType.BAKSTEEN, 6));
		tileArr.add(new Tile(6, 3, ResourceType.GRAAN, 6));
		tileArr.add(new Tile(6, 6, ResourceType.WOESTIJN, 0));
		tileArr.add(new Tile(6, 9, ResourceType.WOL, 2));
		tileArr.add(new Tile(7, 5, ResourceType.GRAAN, 4));
		tileArr.add(new Tile(7, 8, ResourceType.WOL, 4));
		tileArr.add(new Tile(8, 4, ResourceType.WOL, 11));
		tileArr.add(new Tile(8, 7, ResourceType.HOUT, 3));
		tileArr.add(new Tile(8, 10, ResourceType.HOUT, 9));
		tileArr.add(new Tile(9, 6, ResourceType.WOL, 5));
		tileArr.add(new Tile(9, 9, ResourceType.BAKSTEEN, 10));
		tileArr.add(new Tile(10, 8, ResourceType.ERTS, 8));
	}
	//create building locations with X & Y coordinate and neighbouring tiles
	private void createBuildingLocations() {
		buildingLocArr.add(new BuildingLocation( 1,  3, tileArr.get(0), null, null, null));
		buildingLocArr.add(new BuildingLocation( 1,  4, tileArr.get(0), null, null, null));
		buildingLocArr.add(new BuildingLocation( 2,  2, tileArr.get(1), null, null, new Harbour(ResourceType.BAKSTEEN)));
		buildingLocArr.add(new BuildingLocation( 2,  3, tileArr.get(0), tileArr.get(1), null, new Harbour(ResourceType.BAKSTEEN)));
		buildingLocArr.add(new BuildingLocation( 2,  5, tileArr.get(0), tileArr.get(2), null, new Harbour(ResourceType.HOUT)));
		buildingLocArr.add(new BuildingLocation( 2,  6, tileArr.get(2), null, null, new Harbour(ResourceType.HOUT)));
		buildingLocArr.add(new BuildingLocation( 3,  1, tileArr.get(3), null, null, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation( 3,  2, tileArr.get(1), tileArr.get(3), null, null));
		buildingLocArr.add(new BuildingLocation( 3,  4, tileArr.get(0), tileArr.get(1), tileArr.get(4), null));
		buildingLocArr.add(new BuildingLocation( 3,  5, tileArr.get(0), tileArr.get(4), tileArr.get(2), null));
		buildingLocArr.add(new BuildingLocation( 3,  7, tileArr.get(2), tileArr.get(5), null, null));
		buildingLocArr.add(new BuildingLocation( 3,  8, tileArr.get(5), null, null, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation( 4,  1, tileArr.get(3), null, null, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation( 4,  3, tileArr.get(1), tileArr.get(3), tileArr.get(6), null));
		buildingLocArr.add(new BuildingLocation( 4,  4, tileArr.get(1), tileArr.get(4), tileArr.get(6), null));
		buildingLocArr.add(new BuildingLocation( 4,  6, tileArr.get(2), tileArr.get(4), tileArr.get(7), null));
		buildingLocArr.add(new BuildingLocation( 4,  7, tileArr.get(2), tileArr.get(5), tileArr.get(7), null));
		buildingLocArr.add(new BuildingLocation( 4,  9, tileArr.get(5), null, null, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation( 5,  2, tileArr.get(3), tileArr.get(8), null ,null));
		buildingLocArr.add(new BuildingLocation( 5,  3, tileArr.get(3), tileArr.get(6), tileArr.get(8),null));
		buildingLocArr.add(new BuildingLocation( 5,  5, tileArr.get(4), tileArr.get(6), tileArr.get(9),null));
		buildingLocArr.add(new BuildingLocation( 5,  6, tileArr.get(4), tileArr.get(7), tileArr.get(9), null));
		buildingLocArr.add(new BuildingLocation( 5,  8, tileArr.get(5), tileArr.get(7), tileArr.get(10), null));
		buildingLocArr.add(new BuildingLocation( 5,  9, tileArr.get(5), tileArr.get(10), null, null));
		buildingLocArr.add(new BuildingLocation( 6,  2, tileArr.get(8), null, null, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation( 6,  4, tileArr.get(6), tileArr.get(8), tileArr.get(11), null));
		buildingLocArr.add(new BuildingLocation( 6,  5, tileArr.get(6), tileArr.get(9), tileArr.get(11), null));
		buildingLocArr.add(new BuildingLocation( 6,  7, tileArr.get(7), tileArr.get(9), tileArr.get(12), null));
		buildingLocArr.add(new BuildingLocation( 6,  8, tileArr.get(7), tileArr.get(10), tileArr.get(12), null));
		buildingLocArr.add(new BuildingLocation( 6, 10, tileArr.get(10), null, null,  new Harbour(ResourceType.GRAAN)));
		buildingLocArr.add(new BuildingLocation( 7,  3, tileArr.get(8), tileArr.get(13), tileArr.get(0), new Harbour(null)));
		buildingLocArr.add(new BuildingLocation( 7,  4, tileArr.get(8), tileArr.get(11), tileArr.get(13), null));
		buildingLocArr.add(new BuildingLocation( 7,  6, tileArr.get(9), tileArr.get(11), tileArr.get(14), null));
		buildingLocArr.add(new BuildingLocation( 7,  7, tileArr.get(9), tileArr.get(12), tileArr.get(14), null));
		buildingLocArr.add(new BuildingLocation( 7,  9, tileArr.get(10), tileArr.get(12), tileArr.get(15), null));
		buildingLocArr.add(new BuildingLocation( 7, 10, tileArr.get(10), tileArr.get(15), null,  new Harbour(ResourceType.GRAAN)));
		buildingLocArr.add(new BuildingLocation( 8,  3, tileArr.get(13), null, null, null));
		buildingLocArr.add(new BuildingLocation( 8,  5, tileArr.get(11), tileArr.get(13), tileArr.get(16), null));
		buildingLocArr.add(new BuildingLocation( 8,  6, tileArr.get(11), tileArr.get(14), tileArr.get(16), null));
		buildingLocArr.add(new BuildingLocation( 8,  8, tileArr.get(12), tileArr.get(14), tileArr.get(17), null));
		buildingLocArr.add(new BuildingLocation( 8,  9, tileArr.get(12), tileArr.get(15), tileArr.get(17), null));
		buildingLocArr.add(new BuildingLocation( 8, 11, tileArr.get(15), null, null, null));
		buildingLocArr.add(new BuildingLocation( 9,  4, tileArr.get(13), null, null, null));
		buildingLocArr.add(new BuildingLocation( 9,  5, tileArr.get(13), tileArr.get(16), null, new Harbour(ResourceType.WOL)));
		buildingLocArr.add(new BuildingLocation( 9,  7, tileArr.get(14), tileArr.get(16), tileArr.get(18), null));
		buildingLocArr.add(new BuildingLocation( 9,  8, tileArr.get(14), tileArr.get(17), tileArr.get(18), null));
		buildingLocArr.add(new BuildingLocation( 9, 10, tileArr.get(15), tileArr.get(17), null,  new Harbour(ResourceType.ERTS)));
		buildingLocArr.add(new BuildingLocation( 9, 11, tileArr.get(15), null, null,  null));
		buildingLocArr.add(new BuildingLocation(10,  6, tileArr.get(16), null, null, new Harbour(ResourceType.WOL)));
		buildingLocArr.add(new BuildingLocation(10,  7, tileArr.get(16), tileArr.get(18), null,  null));
		buildingLocArr.add(new BuildingLocation(10,  9, tileArr.get(17), tileArr.get(18), null,  null));
		buildingLocArr.add(new BuildingLocation(10, 10, tileArr.get(17), null, null, new Harbour(ResourceType.ERTS)));
		buildingLocArr.add(new BuildingLocation(11,  8, tileArr.get(18), null, null, new Harbour(null)));
		buildingLocArr.add(new BuildingLocation(11,  9, tileArr.get(18), null, null, new Harbour(null)));
		
		
		
	}

}
