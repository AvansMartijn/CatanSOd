package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.BuildingLocation;
import model.Gameboard;
import model.Harbour;
import model.ResourceType;
import model.StreetLocation;
import model.Tile;

public class GameBoardControl {
	private static final int SIDES_ON_A_TILE = 6;
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	//create buildingLocation array and streetLocation array to check if an object for a location 
	//is already made (to make sure we don't have duplicates)
	private ArrayList<BuildingLocation> buildingLocations = new ArrayList<BuildingLocation>();
	private ArrayList<StreetLocation> streetLocations = new ArrayList<StreetLocation>();
	private Gameboard gameBoard;
	private MainDA mainDA;
	private int idGame;
	
	
	public GameBoardControl(MainDA mainDA, int idGame) {		
		this.mainDA = mainDA;
		this.idGame = idGame;
		
	}
	
	public void loadBoard() {
		tiles = mainDA.getTile(idGame);
		createBuildingLocations();
		createStreetLocations();
		assignHarbours();
		gameBoard = new Gameboard(tiles, buildingLocations, streetLocations);
		gameBoard.printAllTilesAndLocs();		
	}
	
	public void addBoardToDB() {
		System.out.println(tiles.size());
		for(int i = 0; i < tiles.size(); i++) {
			Tile tile = tiles.get(i);
			mainDA.addTile(idGame, (i + 1), tile.getX(), tile.getY(), tile.getRsType(), tile.getChipNumber());
		}
	}
	
	
	public void createBoard() {
		createTiles();
		createBuildingLocations();
		assignHarbours();
		createStreetLocations();
		printAllTilesAndLocs();
		gameBoard = new Gameboard(tiles, buildingLocations, streetLocations);
		addBoardToDB();

	}

	//create all tiles with x & y coordinate, ResourceType and number
	private void createTiles() {
		tiles.add(new Tile(2, 4, ResourceType.GRAAN, 12));
		tiles.add(new Tile(3, 3, ResourceType.HOUT, 10));
		tiles.add(new Tile(3, 6, ResourceType.GRAAN, 18));
		tiles.add(new Tile(4, 2, ResourceType.BAKSTEEN, 6));
		tiles.add(new Tile(4, 5, ResourceType.HOUT, 	16));
		tiles.add(new Tile(4, 8, ResourceType.ERTS, 14));
		tiles.add(new Tile(5, 4, ResourceType.ERTS, 2));
		tiles.add(new Tile(5, 7, ResourceType.BAKSTEEN, 8));
		tiles.add(new Tile(6, 3, ResourceType.GRAAN, 9));
		tiles.add(new Tile(6, 6, ResourceType.WOESTIJN, 0));
		tiles.add(new Tile(6, 9, ResourceType.WOL, 1));
		tiles.add(new Tile(7, 5, ResourceType.GRAAN, 5));
		tiles.add(new Tile(7, 8, ResourceType.WOL, 4));
		tiles.add(new Tile(8, 4, ResourceType.WOL, 17));
		tiles.add(new Tile(8, 7, ResourceType.HOUT, 3));
		tiles.add(new Tile(8, 10, ResourceType.HOUT,13));
		tiles.add(new Tile(9, 6, ResourceType.WOL, 7));
		tiles.add(new Tile(9, 9, ResourceType.BAKSTEEN, 15));
		tiles.add(new Tile(10, 8, ResourceType.ERTS, 11));
	}
	
	private void createStreetLocations() {
		for(int tileIndex = 0; tileIndex < tiles.size(); tileIndex++) {
			//create temporary array with streetLocations
			ArrayList<StreetLocation> strLocations = new ArrayList<>();
			for (int i = 0; i < SIDES_ON_A_TILE; i++) {
				/*
				 * i = 0 -> j = 1
				 * i = 1 -> j = 2
				 * i = 2 -> j = 3
				 * i = 3 -> j = 4
				 * i = 4 -> j = 5
				 * i = 5 -> j = 0
				 */
				int j = (i + 1) % 6;
				strLocations.add(new StreetLocation(tiles.get(tileIndex).getBuildingLocations().get(i), tiles.get(tileIndex).getBuildingLocations().get(j)));
			}
			
			//for all streetLocations in the temporary array
			for(int strLocCount = 0; strLocCount < strLocations.size(); strLocCount++) {
				boolean exists = false;
				//compare streetLocation to main streetLocation array
				for(StreetLocation sl : streetLocations) {
					//if a similar streetLocation already exists in the main array use THAT EXACT 
					//object and put it in the tiles streetlocArray
					if(	(strLocations.get(strLocCount).getBuildingLocation(0) == sl.getBuildingLocation(0) 
						&& strLocations.get(strLocCount).getBuildingLocation(1) == sl.getBuildingLocation(1))
						|| (strLocations.get(strLocCount).getBuildingLocation(0) == sl.getBuildingLocation(1) 
						&& strLocations.get(strLocCount).getBuildingLocation(1) == sl.getBuildingLocation(0)) ) {
						
						exists = true;
						tiles.get(tileIndex).addStreetLocation(sl);
						break;
					}
				}
				
				//if a similar streetLocation does not exist, create it in the main streetLocation array 
				//and also put it in the tiles streetLocArray
				if(!exists) {
					tiles.get(tileIndex).addStreetLocation(strLocations.get(strLocCount));
					streetLocations.add(strLocations.get(strLocCount));
				}
			}
		}
	}
	
	
	//create building locations with X & Y coordinate and neighboring tiles
	private void createBuildingLocations() { 
		//for all tiles
		for(int count = 0; count < tiles.size(); count++) {
			//get coordinates required to generate buildingLocation coordinates
			int tileX = tiles.get(count).getX();
			int tileY = tiles.get(count).getY();
			//create a temporary array for buildingLocations
			ArrayList<BuildingLocation> locArr = new ArrayList<>();
			//generate all buildingLocations temporary
			locArr.add(new BuildingLocation(tileX-1, tileY-1));
			locArr.add(new BuildingLocation(tileX-1, tileY));
			locArr.add(new BuildingLocation(tileX, 	 tileY+1));
			locArr.add(new BuildingLocation(tileX+1, tileY+1));
			locArr.add(new BuildingLocation(tileX+1, tileY));
			locArr.add(new BuildingLocation(tileX,   tileY-1));
			
			//for each temporary building location
			for(int locArrCount = 0; locArrCount < locArr.size(); locArrCount++) {				
				boolean exists = false;
				//compare the buildingLocation to the buildingLocations in the main buildingLocation Array
				for (BuildingLocation bl : buildingLocations) { 	
					//if a buildingLocation already exists in the main buildingLocation array
					if(locArr.get(locArrCount).getX() == (bl.getX()) && locArr.get(locArrCount).getY() == (bl.getY())) {
						exists = true;
						//get that exact buildingLocation object and put it in the tiles buildingLocation Array
						tiles.get(count).addBuildingLocation(bl);						
						break;
					}
			    }		
				
				//if it does not exist, create the object in the main buildinglocation Array and add it to the tiles buildinglocation Array
				if(!exists) {
					tiles.get(count).addBuildingLocation(locArr.get(locArrCount));
					buildingLocations.add(locArr.get(locArrCount));
					
				}
			}		
		}	
	}
	
	//assign Harbours to correct buildingLocations		
	public void assignHarbours() {
		buildingLocations.get(5).setHarbour(new Harbour(ResourceType.BAKSTEEN));
		buildingLocations.get(6).setHarbour(new Harbour(ResourceType.BAKSTEEN));
		buildingLocations.get(2).setHarbour(new Harbour(ResourceType.HOUT));
		buildingLocations.get(10).setHarbour(new Harbour(ResourceType.HOUT));
		buildingLocations.get(20).setHarbour(new Harbour(null));
		buildingLocations.get(21).setHarbour(new Harbour(null));
		buildingLocations.get(33).setHarbour(new Harbour(ResourceType.GRAAN));
		buildingLocations.get(34).setHarbour(new Harbour(ResourceType.GRAAN));
		buildingLocations.get(47).setHarbour(new Harbour(ResourceType.ERTS));
		buildingLocations.get(50).setHarbour(new Harbour(ResourceType.ERTS));
		buildingLocations.get(52).setHarbour(new Harbour(null));
		buildingLocations.get(53).setHarbour(new Harbour(null));
		buildingLocations.get(47).setHarbour(new Harbour(ResourceType.ERTS));
		buildingLocations.get(50).setHarbour(new Harbour(ResourceType.ERTS));
		buildingLocations.get(40).setHarbour(new Harbour(ResourceType.WOL));
		buildingLocations.get(49).setHarbour(new Harbour(ResourceType.WOL));
		buildingLocations.get(29).setHarbour(new Harbour(null));
		buildingLocations.get(30).setHarbour(new Harbour(null));
		buildingLocations.get(14).setHarbour(new Harbour(null));
		buildingLocations.get(17).setHarbour(new Harbour(null));
	}
	
	public void printAllTilesAndLocs() {
		for (Tile tile : tiles) {
			System.out.println("---------TILE-------------");
			System.out.println("resource: " + tile.getRsType());
			System.out.println("chip number: " + tile.getChipNumber());
			System.out.println("loc_x: " + tile.getX());
			System.out.println("loc_y: " + tile.getY());
			
			for (BuildingLocation bl : tile.getBuildingLocations()) {
				System.out.println("------BUILDING LOC---------");
				System.out.println("loc_x: " + bl.getX());
				System.out.println("loc_y: " + bl.getY());
				
				if(bl.getHarbour() != null) {
					System.out.println("harbour: " + bl.getHarbour().getRsType());
				}
				
			}
			
			for(StreetLocation sl : tile.getStreetLocations()) {
				System.out.println("------STREET LOC---------");
				System.out.println("start: " + sl.getBuildingLocation(0).getX() + " " + sl.getBuildingLocation(0).getY());
				System.out.println("end: " + sl.getBuildingLocation(1).getX() + " " + sl.getBuildingLocation(1).getY());
				
				
			}
			System.out.println("----------------------------");
			System.out.println();
			System.out.println();
		} 
	}

}
