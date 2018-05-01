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
	
	// TODO:
	//create arraylist with building positions ( super array )
	//method building position get x, y (returns building pos)
	
	public GameBoardControl() {
		createBoard();
	}
	
	
	public void createBoard() {
		createTiles();
		createBuildingLocations();
		assignHarbours();
		createStreetLocations();
		printAllTilesAndLocs();

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
	
	private void createStreetLocations() {
		int count = 0;
		while(tileArr.size() > count) {			
			ArrayList<StreetLocation> strLocArr = new ArrayList<>();
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(0), tileArr.get(count).getBuildingLocArr().get(1)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(1), tileArr.get(count).getBuildingLocArr().get(2)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(2), tileArr.get(count).getBuildingLocArr().get(3)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(3), tileArr.get(count).getBuildingLocArr().get(4)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(4), tileArr.get(count).getBuildingLocArr().get(5)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(5), tileArr.get(count).getBuildingLocArr().get(0)));
			
			int strLocCount = 0;
			while(strLocArr.size() > strLocCount) {
				boolean exists = false;
				for(StreetLocation sl : streetLocArr) {
					if(strLocArr.get(strLocCount).getBlStart() == sl.getBlStart() && strLocArr.get(strLocCount).getBlEnd() == sl.getBlEnd() || strLocArr.get(strLocCount).getBlStart() == sl.getBlEnd() && strLocArr.get(strLocCount).getBlEnd() == sl.getBlStart() ) {
						exists = true;
						tileArr.get(count).addStreetLoc(sl);
						break;
					}
				}
				
				if(!exists) {
					tileArr.get(count).addStreetLoc(strLocArr.get(strLocCount));
					streetLocArr.add(strLocArr.get(strLocCount));
				}
				
				strLocCount++;
			}
			count++;
		}
	}
	
	
	//create building locations with X & Y coordinate and neighbouring tiles
	private void createBuildingLocations() {
		 
		int count = 0; 		
		while (tileArr.size() > count) {
			int tileX = tileArr.get(count).getX();
			int tileY = tileArr.get(count).getY();
			ArrayList<BuildingLocation> locArr = new ArrayList<>();
			locArr.add(new BuildingLocation(tileX-1, tileY-1));
			locArr.add(new BuildingLocation(tileX-1, tileY));
			locArr.add(new BuildingLocation(tileX, 	 tileY+1));
			locArr.add(new BuildingLocation(tileX+1, tileY+1));
			locArr.add(new BuildingLocation(tileX+1, tileY));
			locArr.add(new BuildingLocation(tileX,   tileY-1));
			
			int locArrCount = 0;
			while(locArr.size() > locArrCount) {				
				boolean exists = false;
				for (BuildingLocation bl : buildingLocArr) { 		      
					if(locArr.get(locArrCount).getX() == (bl.getX()) && locArr.get(locArrCount).getY() == (bl.getY())) {
						exists = true;
						tileArr.get(count).addBuildingLoc(bl);						
						break;
					}
			    }		
				
				
				if(!exists) {
					tileArr.get(count).addBuildingLoc(locArr.get(locArrCount));
					buildingLocArr.add(locArr.get(locArrCount));
					
				}
				
			
				locArrCount++;
			}		
		    count++;
	  }

		
	}
	
	public void assignHarbours() {
				
		buildingLocArr.get(5).setHarbour(new Harbour(ResourceType.BAKSTEEN));
		buildingLocArr.get(6).setHarbour(new Harbour(ResourceType.BAKSTEEN));
		buildingLocArr.get(2).setHarbour(new Harbour(ResourceType.HOUT));
		buildingLocArr.get(10).setHarbour(new Harbour(ResourceType.HOUT));
		buildingLocArr.get(20).setHarbour(new Harbour(null));
		buildingLocArr.get(21).setHarbour(new Harbour(null));
		buildingLocArr.get(33).setHarbour(new Harbour(ResourceType.GRAAN));
		buildingLocArr.get(34).setHarbour(new Harbour(ResourceType.GRAAN));
		buildingLocArr.get(47).setHarbour(new Harbour(ResourceType.ERTS));
		buildingLocArr.get(50).setHarbour(new Harbour(ResourceType.ERTS));
		buildingLocArr.get(52).setHarbour(new Harbour(null));
		buildingLocArr.get(53).setHarbour(new Harbour(null));
		buildingLocArr.get(47).setHarbour(new Harbour(ResourceType.ERTS));
		buildingLocArr.get(50).setHarbour(new Harbour(ResourceType.ERTS));
		buildingLocArr.get(40).setHarbour(new Harbour(ResourceType.WOL));
		buildingLocArr.get(49).setHarbour(new Harbour(ResourceType.WOL));
		buildingLocArr.get(29).setHarbour(new Harbour(null));
		buildingLocArr.get(30).setHarbour(new Harbour(null));
		buildingLocArr.get(14).setHarbour(new Harbour(null));
		buildingLocArr.get(17).setHarbour(new Harbour(null));
		
	}
	
	public void printAllTilesAndLocs() {
		for (Tile tile : tileArr) {
			System.out.println("---------TILE-------------");
			System.out.println("resource: " + tile.getRsType());
			System.out.println("chip number: " + tile.getChipNumber());
			System.out.println("loc_x: " + tile.getX());
			System.out.println("loc_y: " + tile.getY());
			
			for (BuildingLocation bl : tile.getBuildingLocArr()) {
				System.out.println("------BUILDING LOC---------");
				System.out.println("loc_x: " + bl.getX());
				System.out.println("loc_y: " + bl.getY());
				
				if(bl.getHarbour() != null) {
					System.out.println("harbour: " + bl.getHarbour().getRsType());
				}
				
			}
			
			for(StreetLocation sl : tile.getStreetLocArr()) {
				System.out.println("------STREET LOC---------");
				System.out.println("start: " + sl.getBlStart().getX() + " " + sl.getBlStart().getY());
				System.out.println("end: " + sl.getBlEnd().getX() + " " + sl.getBlEnd().getY());
				
				
			}
			System.out.println("----------------------------");
			System.out.println("");
			System.out.println("");
		} 
	}

}
