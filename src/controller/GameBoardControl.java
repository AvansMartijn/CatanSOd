package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.BuildingLocation;
import model.Gameboard;
import model.Harbour;
import model.Player;
import model.ResourceType;
import model.StreetLocation;
import model.Tile;
import view.BoardPanel;
import view.Frame;

public class GameBoardControl {
	private ArrayList<Tile> tileArr = new ArrayList<Tile>();
	// create buildinglocation array and streetlocation array to check if an object
	// for a location is already made (to make sure we don't have duplicates)
	private ArrayList<BuildingLocation> buildingLocArr = new ArrayList<BuildingLocation>();
	private ArrayList<StreetLocation> streetLocArr = new ArrayList<StreetLocation>();
	private Gameboard gameBoard;
	private MainDA mainDA;
	private int idGame;

	public GameBoardControl(MainDA mainDA, int idGame) {
		this.mainDA = mainDA;
		this.idGame = idGame;

	}

	public Gameboard loadBoard() {
		tileArr = mainDA.getTile(idGame);
		createBuildingLocations();
		createStreetLocations();
		assignHarbours();
		gameBoard = new Gameboard(tileArr, buildingLocArr, streetLocArr);
		return gameBoard;
	}

	public void addBoardToDB() {
		int count = 0;
		int i = 1;
		System.out.println(tileArr.size());
		while (tileArr.size() > count) {
			Tile tile = tileArr.get(count);
			mainDA.addTile(idGame, i, tile.getX(), tile.getY(), tile.getRsType(), tile.getChipNumber());

			i++;
			count++;
		}

	}

	public Gameboard createBoardAndAddToDB(ArrayList<Player> players) {
		createTiles();
		createBuildingLocations();
		assignHarbours();
		createStreetLocations();
		printAllTilesAndLocs();
		gameBoard = new Gameboard(tileArr, buildingLocArr, streetLocArr);
		addBoardToDB();
//		addPlayerPiecesToDB(players);
		return gameBoard;

	}

	// create all tiles with x & y coordinate, resourcetype and number
	private void createTiles() {
		tileArr.add(new Tile(1, 2, 4, ResourceType.GRAAN, 12));
		tileArr.add(new Tile(2, 3, 3, ResourceType.HOUT, 10));
		tileArr.add(new Tile(3, 3, 6, ResourceType.GRAAN, 18));
		tileArr.add(new Tile(4, 4, 2, ResourceType.BAKSTEEN, 6));
		tileArr.add(new Tile(5, 4, 5, ResourceType.HOUT, 16));
		tileArr.add(new Tile(6, 4, 8, ResourceType.ERTS, 14));
		tileArr.add(new Tile(7, 5, 4, ResourceType.ERTS, 2));
		tileArr.add(new Tile(8, 5, 7, ResourceType.BAKSTEEN, 8));
		tileArr.add(new Tile(9, 6, 3, ResourceType.GRAAN, 9));
		tileArr.add(new Tile(10, 6, 6, ResourceType.WOESTIJN, 0));
		tileArr.add(new Tile(11, 6, 9, ResourceType.WOL, 1));
		tileArr.add(new Tile(12, 7, 5, ResourceType.GRAAN, 5));
		tileArr.add(new Tile(13, 7, 8, ResourceType.WOL, 4));
		tileArr.add(new Tile(14, 8, 4, ResourceType.WOL, 17));
		tileArr.add(new Tile(15, 8, 7, ResourceType.HOUT, 3));
		tileArr.add(new Tile(16, 8, 10, ResourceType.HOUT, 13));
		tileArr.add(new Tile(17, 9, 6, ResourceType.WOL, 7));
		tileArr.add(new Tile(18, 9, 9, ResourceType.BAKSTEEN, 15));
		tileArr.add(new Tile(19, 10, 8, ResourceType.ERTS, 11));
	}

	private void createStreetLocations() {
		int count = 0;
		// for all tiles
		while (tileArr.size() > count) {
			// create temporary array with streetlocations
			ArrayList<StreetLocation> strLocArr = new ArrayList<>();
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(0),
					tileArr.get(count).getBuildingLocArr().get(1)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(1),
					tileArr.get(count).getBuildingLocArr().get(2)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(2),
					tileArr.get(count).getBuildingLocArr().get(3)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(3),
					tileArr.get(count).getBuildingLocArr().get(4)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(4),
					tileArr.get(count).getBuildingLocArr().get(5)));
			strLocArr.add(new StreetLocation(tileArr.get(count).getBuildingLocArr().get(5),
					tileArr.get(count).getBuildingLocArr().get(0)));

			int strLocCount = 0;
			// for all streetlocations in the temporary array
			while (strLocArr.size() > strLocCount) {
				boolean exists = false;
				// compare streetlocation to main streetlocation array
				for (StreetLocation sl : streetLocArr) {
					// if a similar streetlocation already exists in the main array use THAT EXACT
					// object and put it in the tiles streetlocArray
					if (strLocArr.get(strLocCount).getBlStart() == sl.getBlStart()
							&& strLocArr.get(strLocCount).getBlEnd() == sl.getBlEnd()
							|| strLocArr.get(strLocCount).getBlStart() == sl.getBlEnd()
									&& strLocArr.get(strLocCount).getBlEnd() == sl.getBlStart()) {
						exists = true;
						tileArr.get(count).addStreetLoc(sl);
						sl.getBlStart().getAdjacentStreets().add(sl);
						sl.getBlEnd().getAdjacentStreets().add(sl);
						break;
					}
				}

				// if a similar streetlocation does not exist, create it in the main
				// streetlocation array and also put it in the tiles streetLocArray
				if (!exists) {
					tileArr.get(count).addStreetLoc(strLocArr.get(strLocCount));
					streetLocArr.add(strLocArr.get(strLocCount));
					strLocArr.get(strLocCount).getBlStart().getAdjacentStreets().add(strLocArr.get(strLocCount));
					strLocArr.get(strLocCount).getBlEnd().getAdjacentStreets().add(strLocArr.get(strLocCount));
				}
				//assign streets to buildinglocations adjecentstreets array
				
				
				
				strLocCount++;
			}
			count++;
		}
	}

	// create building locations with X & Y coordinate and neighboring tiles
	private void createBuildingLocations() {

		int count = 0;
		// for all tiles
		while (tileArr.size() > count) {
			// get coordinates required to generate buildinglocation coordinates
			int tileX = tileArr.get(count).getX();
			int tileY = tileArr.get(count).getY();
			// create a temporary array for buildinglocations
			ArrayList<BuildingLocation> locArr = new ArrayList<>();
			// generate all buildinglocations temporary
			locArr.add(new BuildingLocation(tileX - 1, tileY - 1));
			locArr.add(new BuildingLocation(tileX - 1, tileY));
			locArr.add(new BuildingLocation(tileX, tileY + 1));
			locArr.add(new BuildingLocation(tileX + 1, tileY + 1));
			locArr.add(new BuildingLocation(tileX + 1, tileY));
			locArr.add(new BuildingLocation(tileX, tileY - 1));

			int locArrCount = 0;
			// for each temporary building location
			while (locArr.size() > locArrCount) {
				boolean exists = false;
				// compare the buildinglocation to the buildinglocations in the main
				// buildinglocation Array
				for (BuildingLocation bl : buildingLocArr) {
					// if a buildinglocation already exists in the main buildinglocation array
					if (locArr.get(locArrCount).getXLoc() == (bl.getXLoc())
							&& locArr.get(locArrCount).getYLoc() == (bl.getYLoc())) {
						exists = true;
						// get that exact buildinglocation object and put it in the tiles
						// buildinglocation Array
						tileArr.get(count).addBuildingLoc(bl);
						break;
					}
				}

				// if it does not exist, create the object in the main buildinglocation Array
				// and add it to the tiles buildinglocation Array
				if (!exists) {
					tileArr.get(count).addBuildingLoc(locArr.get(locArrCount));
					buildingLocArr.add(locArr.get(locArrCount));

				}

				locArrCount++;
			}
			count++;
		}

	}

	public void assignHarbours() {
		// assign harbours to correct buildinglocations
		Harbour brickHarbour = new Harbour(ResourceType.BAKSTEEN);
		Harbour woodHarbour = new Harbour(ResourceType.HOUT);
		Harbour generalHarbour = new Harbour(null);
		Harbour wheatHarbour = new Harbour(ResourceType.GRAAN);
		Harbour ironHarbour = new Harbour(ResourceType.ERTS);
		Harbour woolHarbour = new Harbour(ResourceType.WOL);

		buildingLocArr.get(5).setHarbour(brickHarbour);
		buildingLocArr.get(6).setHarbour(brickHarbour);
		buildingLocArr.get(2).setHarbour(woodHarbour);
		buildingLocArr.get(10).setHarbour(woodHarbour);
		buildingLocArr.get(20).setHarbour(generalHarbour);
		buildingLocArr.get(21).setHarbour(generalHarbour);
		buildingLocArr.get(33).setHarbour(wheatHarbour);
		buildingLocArr.get(34).setHarbour(wheatHarbour);
		buildingLocArr.get(47).setHarbour(ironHarbour);
		buildingLocArr.get(50).setHarbour(ironHarbour);
		buildingLocArr.get(52).setHarbour(generalHarbour);
		buildingLocArr.get(53).setHarbour(generalHarbour);
		buildingLocArr.get(47).setHarbour(ironHarbour);
		buildingLocArr.get(50).setHarbour(ironHarbour);
		buildingLocArr.get(40).setHarbour(woolHarbour);
		buildingLocArr.get(49).setHarbour(woolHarbour);
		buildingLocArr.get(29).setHarbour(generalHarbour);
		buildingLocArr.get(30).setHarbour(generalHarbour);
		buildingLocArr.get(14).setHarbour(generalHarbour);
		buildingLocArr.get(17).setHarbour(generalHarbour);

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
				System.out.println("loc_x: " + bl.getXLoc());
				System.out.println("loc_y: " + bl.getYLoc());

				if (bl.getHarbour() != null) {
					System.out.println("harbour: " + bl.getHarbour().getRsType());
				}

			}

			for (StreetLocation sl : tile.getStreetLocArr()) {
				System.out.println("------STREET LOC---------");
				System.out.println("start: " + sl.getBlStart().getXLoc() + " " + sl.getBlStart().getYLoc());
				System.out.println("end: " + sl.getBlEnd().getXLoc() + " " + sl.getBlEnd().getYLoc());

			}
			System.out.println("----------------------------");
			System.out.println("");
			System.out.println("");
		}
	}

//	public void addPlayerPiecesToDB(ArrayList<Player> players) {
//		String idPiece;
//		for (Player p : players) {
//
//			for (int i = 1; i <= 5; i++) {
//				idPiece = "d0" + i;
//				mainDA.addPlayerPiece(idPiece, p.getIdPlayer());
//			}
//			for (int i = 1; i <= 4; i++) {
//				idPiece = "c0" + i;
//				mainDA.addPlayerPiece(idPiece, p.getIdPlayer());
//			}
//			for (int i = 1; i <= 15; i++) {
//				if (i > 9) {
//					idPiece = "r" + i;
//				} else {
//					idPiece = "r0" + i;
//				}
//				mainDA.addPlayerPiece(idPiece, p.getIdPlayer());
//			}
//		}
//
//	}

}
