package model;

import java.util.ArrayList;

public class Gameboard {
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	//create buildingLocation array and streetLocation array to check if an object for a location is already made 
	//(to make sure we don't have duplicates)
	private ArrayList<BuildingLocation> buildingLocations = new ArrayList<BuildingLocation>();
	private ArrayList<StreetLocation> streetLocations = new ArrayList<StreetLocation>();

	public Gameboard(ArrayList<Tile> tileList, ArrayList<BuildingLocation> buildingList, ArrayList<StreetLocation> streetList) {
		this.tiles = tileList;
		this.buildingLocations = buildingList;
		this.streetLocations = streetList;
	}
	
	/**
	 * Test Method. This method should not be used in the final product. <br>
	 * Deprecated just to make it stand out, so it won't be used in the final product. 
	 * <p>
	 * JavaDoc added by Jasper Mooren
	 */
	@Deprecated
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
			System.out.println("");
			System.out.println("");
		} 
	}
}