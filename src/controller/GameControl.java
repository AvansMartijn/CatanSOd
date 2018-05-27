package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import dbaccess.MainDA;
import model.Bank;
import model.BuildingLocation;
import model.Catan;
import model.City;
import model.Dice;
import model.Gameboard;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import model.Resource;
import model.ResourceType;
import model.Street;
import model.StreetLocation;
import model.TradeRequest;
import model.Village;
import view.PlayerStatsPanel;

public class GameControl {
	private GameBoardControl gameBoardControl;
	private MainDA mainDA;
	// private String username;
	private Catan catanGame;

	// private Gameboard gameboard;
	// private ArrayList<Player> gamePlayers;
	// private int idGame;
	// private int playerID;
	// private int account;
	// private Player player;
	// private Bank bank;
	// private Dice dice;

	public GameControl(MainDA mainDA) {
		this.mainDA = mainDA;
	}

	public int createGameInDB(boolean randomBoard) {
		/**
		 * Create a game record in the DB AND sets idGame
		 */
		int gameID = mainDA.createGame(randomBoard);
		// createNewPlayer();
		// addPlayerToDB(player);
		gameBoardControl = new GameBoardControl(mainDA, gameID);

		// TODO add gameboard to db
		// gameboard = gameBoardControl.createBoard();
		return gameID;

	}

	public boolean addMessage(String message) {
		catanGame.getMessages().add(message);
		return addMessageToDB(message);
	}

	public boolean addMessageToDB(String message) {
		int idPlayer = catanGame.getSelfPlayer().getIdPlayer();
		if (mainDA.addMessage(idPlayer, catanGame.getIdGame(), message)) {
			return true;
		} else {
			return false;
		}
	}

	public void changeRobber(int idTile) {
		catanGame.getGameboard().setRobber(idTile);
		changeRobberInDB(idTile);
	}

	public void changeRobberInDB(int idTile) {
		this.mainDA.changeRobberLocation(catanGame.getIdGame(), idTile);
	}

	public void makeBank() {

	}

	// public Gameboard getGameboard() {
	// return catanGame.getGameboard();
	// }

	public void editDiceLastThrown(int[] die) {
		mainDA.setLastThrow(die[0], die[1], catanGame.getIdGame());
	}

	public void rollDice() {
		catanGame.rollDice();
		editDiceLastThrown(catanGame.getDice().getSeperateValues());
		mainDA.setThrownDice(1, catanGame.getIdGame());
		// return catanGame.getDice().getDie();
	}

	public void setDiceLastThrown(int[] die) {
		catanGame.getDice().setDie(die);
	}

	public boolean hasRolledDice() {
		return mainDA.hasThrown(catanGame.getIdGame());
	}

	public boolean buildVillage(BuildingLocation buildingLocation) {
		Village village = catanGame.getSelfPlayer().getAvailableVillage();
		// check if player has a village to build

		if (catanGame.getSelfPlayer().getAmountAvailableVillages() <= 0) {
			System.out.println("not enough villages to build");
			return false;
		}
		// check if nothing is build already on that location
		if (buildingLocation.getVillage() != null || buildingLocation.getCity() != null) {
			return false;
		}

		// TODO Check if enough resources
		// TODO Move resources from player to bank
		// TODO Check if there is not a building neighbouring this location
		// TODO check if there are streets connected to this location
		int thisX = buildingLocation.getXLoc();
		int thisY = buildingLocation.getYLoc();
		for (BuildingLocation b : catanGame.getGameboard().getBuildingLocArr()) {
			boolean isNeighbour = false;
			if (b.getXLoc() == (thisX - 1) && b.getYLoc() == thisY) {
				// x-1
				isNeighbour = true;
			} else if (b.getXLoc() == (thisX + 1) && b.getYLoc() == (thisY + 1)) {
				// x+1 y+1
				isNeighbour = true;
			} else if (b.getXLoc() == thisX && b.getYLoc() == (thisY - 1)) {
				// y-1
				isNeighbour = true;
			} else if (b.getXLoc() == (thisX - 1) && b.getYLoc() == (thisY - 1)) {
				// x-1y-1
				isNeighbour = true;
			} else if (b.getXLoc() == thisX && b.getYLoc() == (thisY + 1)) {
				// y+1
				isNeighbour = true;
			} else if (b.getXLoc() == (thisX + 1) && b.getYLoc() == thisY) {
				// x+1
				isNeighbour = true;
			}

			if (isNeighbour) {
				if (b.getVillage() != null) {
					System.out.println("has neighbouring village");
					return false;
				} else if (b.getCity() != null) {
					System.out.println("has neighbouring city");
					return false;
				}
			}
		}
		// x-1
		// x+1 y+1
		// y-1

		// TODO Check if there is not a building neighboring this location
		// TODO check if there are streets connected to this location

		buildingLocation.setVillage(catanGame.getSelfPlayer().getAvailableVillage());
		village.setBuildingLocation(buildingLocation);
		mainDA.updateBuilding(village.getIdBuilding(), village.getPlayer().getIdPlayer(), buildingLocation.getXLoc(),
				buildingLocation.getYLoc());
		return true;
	}

	public boolean buildCity(BuildingLocation buildingLocation) {
		City city = catanGame.getSelfPlayer().getAvailableCity();
		System.out.println(city);
		if (catanGame.getSelfPlayer().getAmountAvailableCities() <= 0) {
			return false;
		}
		// if there is no village here return false
		if (buildingLocation.getVillage() == null) {
			return false;
		}

		// TODO Check if enough resources
		// TODO Move resources from player to bank

		// check if player has a village on this buildinglocation to upgrade
		if (buildingLocation.getVillage() != null) {
			if (buildingLocation.getVillage().getPlayer().equals(catanGame.getSelfPlayer())) {
				// upgrade village to city
				Village village = buildingLocation.getVillage();
				buildingLocation.setVillage(null);
				village.setBuildingLocation(null);
				buildingLocation.setCity(city);
				city.setBuildingLocation(buildingLocation);
				mainDA.updateBuilding(village.getIdBuilding(), village.getPlayer().getIdPlayer(), 0, 0);
				mainDA.updateBuilding(city.getIdBuilding(), city.getPlayer().getIdPlayer(), buildingLocation.getXLoc(),
						buildingLocation.getYLoc());
				System.out.println("upgraded to city");
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean buildStreet(StreetLocation streetLocation) {
		Street street = catanGame.getSelfPlayer().getAvailableStreet();
		if (catanGame.getSelfPlayer().getAmountAvailableStreets() <= 0) {
			System.out.println("not enough streets");
			return false;
		}
		if (streetLocation.getStreet() != null) {
			System.out.println("already a street here");
			return false;
		}

		if (!streetLocation.hasAdjacentFriendlySettlement(catanGame.getSelfPlayer())
				&& !streetLocation.hasAdjecentFriendlyStreet(catanGame.getSelfPlayer())) {
			System.out.println("no adjecent friendly street or settlements");
			return false;
		}

		removeResources(Street.cost);

		streetLocation.setStreet(street);
		street.setStreetLocation(streetLocation);
		mainDA.updateStreet(street.getIdBuilding(), street.getPlayer().getIdPlayer(),
				streetLocation.getBlStart().getXLoc(), streetLocation.getBlStart().getYLoc(),
				streetLocation.getBlEnd().getXLoc(), streetLocation.getBlEnd().getYLoc());
		System.out.println("street built");
		return true;
	}

	private void removeResources(ResourceType[] cost) {
		for (ResourceType r : cost) {
			for (Resource rs : catanGame.getSelfPlayer().getHand().getResources()) {
				if (rs.getRsType().equals(r)) {
					catanGame.getSelfPlayer().getHand().getResources().remove(rs);
					mainDA.removeResource(rs.getResourceID(), catanGame.getIdGame());
				}

			}

		}
	}

	public void setVillageArrays() {
		for (Player p : catanGame.getPlayers()) {
			ArrayList<Village> villageFromPlayer = mainDA.getVillageFromPlayer(p.getIdPlayer());
			p.setVillageArr(villageFromPlayer);
			for (Village v : villageFromPlayer) {
				v.setPlayer(p);
				if (v.getBuildingLocation().getXLoc() == 0 || v.getBuildingLocation().getYLoc() == 0) {
					v.setBuildingLocation(null);
				} else {
					for (BuildingLocation b : catanGame.getGameboard().getBuildingLocArr()) {
						if (b.getXLoc() == v.getBuildingLocation().getXLoc()
								&& b.getYLoc() == v.getBuildingLocation().getYLoc()) {
							v.setBuildingLocation(b);
							b.setVillage(v);
						}
					}
				}
			}
		}
	}

	public void setCityArrays() {
		for (Player p : catanGame.getPlayers()) {
			ArrayList<City> cityFromPlayer = mainDA.getCityFromPlayer(p.getIdPlayer());
			p.setCityArr(cityFromPlayer);
			for (City c : cityFromPlayer) {
				c.setPlayer(p);
				if (c.getBuildingLocation().getXLoc() == 0 || c.getBuildingLocation().getYLoc() == 0) {
					c.setBuildingLocation(null);

				} else {
					for (BuildingLocation b : catanGame.getGameboard().getBuildingLocArr()) {
						if (b.getXLoc() == c.getBuildingLocation().getXLoc()
								&& b.getYLoc() == c.getBuildingLocation().getYLoc()) {
							c.setBuildingLocation(b);
							b.setCity(c);
						}
					}
				}
			}
		}
	}

	public void setStreetArrays() {
		for (Player p : catanGame.getPlayers()) {
			ArrayList<Street> streetFromPlayer = mainDA.getStreetsFromPlayer(p.getIdPlayer());
			p.setStreetArr(streetFromPlayer);

			for (Street s : streetFromPlayer) {

				s.setPlayer(p);
				int s_start_x = s.getDb_xfrom();
				int s_start_y = s.getDb_yfrom();
				int s_end_x = s.getDb_xto();
				int s_end_y = s.getDb_yto();
				if (s_start_x == 0 || s_start_y == 0 || s_end_x == 0 || s_end_y == 0) {
					s.setStreetLocation(null);

				} else {
					for (StreetLocation sl : catanGame.getGameboard().getStreetLocArr()) {
						int sl_start_x = sl.getBlStart().getXLoc();
						int sl_start_y = sl.getBlStart().getYLoc();
						int sl_end_x = sl.getBlEnd().getXLoc();
						int sl_end_y = sl.getBlEnd().getYLoc();
						// TODO Check both orientation !!!!!
						if (sl_start_x == s_start_x && sl_start_y == s_start_y && sl_end_x == s_end_x
								&& sl_end_y == s_end_y) {
							s.setStreetLocation(sl);
							sl.setStreet(s);

						}
						if (sl_start_x == s_end_x && sl_start_y == s_end_y && sl_end_x == s_start_x
								&& sl_end_y == s_start_y) {
							s.setStreetLocation(sl);
							sl.setStreet(s);
						}
					}
				}
			}
		}
	}

	public void updateBoard() {
		setVillageArrays();
		setCityArrays();
		setStreetArrays();
	}

	public void setCatan(Catan game) { // , int[] dice, ArrayList<String> chatMessages
		this.catanGame = game;
		// catanGame.getDice().setDie(dice);
		// catanGame.setMessages(chatMessages);
		gameBoardControl = new GameBoardControl(mainDA, catanGame.getIdGame());
		Gameboard gameboard = gameBoardControl.loadBoard();
		game.fillCatan(gameboard);
		// setVillageArrays();
		// setCityArrays();
		// setStreetArrays();

	}

	public Catan getCatanGame() {
		return catanGame;
	}

	public void unloadCatan() {
		// catanGame.setBank(null);
		// catanGame.setDice(null);
		// catanGame.setGameboard(null);
		// catanGame.setMessages(null);
		// for(Player p: catanGame.getPlayers()) {
		// p.unload();
		// }
		catanGame = null;
	}

	public Gameboard createBoardAndAddToDB(ArrayList<Player> players, boolean randomBoard) {
		return gameBoardControl.createBoardAndAddToDB(players, randomBoard);
	}

	public boolean canBuy(ResourceType[] costArray) {
		HashMap<ResourceType, Integer> amountOfResources = catanGame.getSelfPlayer().getHand().getAmountOfResources();
		int ownedBrick = amountOfResources.get(ResourceType.BAKSTEEN);
		int ownedWood = amountOfResources.get(ResourceType.HOUT);
		int ownedIron = amountOfResources.get(ResourceType.ERTS);
		int ownedWool = amountOfResources.get(ResourceType.WOL);
		int ownedWheat = amountOfResources.get(ResourceType.GRAAN);

		int brickCost = 0;
		int woodCost = 0;
		int woolCost = 0;
		int ironCost = 0;
		int wheatCost = 0;

		for (ResourceType rs : costArray) {
			switch (rs) {
			case BAKSTEEN:
				brickCost++;
				break;
			case ERTS:
				ironCost++;
				break;
			case WOL:
				woolCost++;
				break;
			case HOUT:
				woodCost++;
				break;
			case GRAAN:
				wheatCost++;
				break;
			case WOESTIJN:
				break;
			default:
				break;
			}
		}

		if (ownedBrick >= brickCost && ownedIron >= ironCost && ownedWool >= woolCost && ownedWood >= woodCost
				&& ownedWheat >= wheatCost) {
			return true;
		}

		return false;

	}

	private ArrayList<StreetLocation> visitedLocations;

	public int getTradeRouteLength(String username) {
		// if (player != null) {
		// int amount = 0;
		ArrayList<BuildingLocation> buildingLocations = catanGame.getGameboard().getBuildingLocArr();
		ArrayList<StreetLocation> roads = new ArrayList<>();
		sortBuildingLocationList(buildingLocations);

		for (BuildingLocation bl : buildingLocations) {
			for (StreetLocation sl : bl.getAdjacentStreetLocations()) {
				if (sl.getStreet() != null) {
					if (sl.getStreet().getPlayer().getUsername().equals(username)) {
						roads.add(sl);
					}
				}
			}

		}

		for (StreetLocation stl : roads) {
			visitedLocations = new ArrayList<StreetLocation>();
			visitedLocations.add(stl);

		}
		int amount = 0;
		for (StreetLocation r : roads) {
			visitedLocations = new ArrayList<StreetLocation>();

			visitedLocations.add(r);

			int amount_from = getTradeRouteLength(r.getBlStart().getXLoc(), r.getBlStart().getYLoc(), username);
			int amount_to = getTradeRouteLength(r.getBlEnd().getXLoc(), r.getBlEnd().getYLoc(), username);

			amount = Math.max(amount, 1 + amount_from + amount_to);
		}
		System.out.println(amount);
		return amount;

		// }
		// return 0;
	}

	public int getTradeRouteLength(int x, int y, String username) {
		ArrayList<StreetLocation> queue = new ArrayList<StreetLocation>();
		ArrayList<StreetLocation> roads = new ArrayList<StreetLocation>();
		ArrayList<BuildingLocation> buildingLocations = catanGame.getGameboard().getBuildingLocArr();
		sortBuildingLocationList(buildingLocations);
		for (BuildingLocation bl : buildingLocations) {
			for (StreetLocation sl : bl.getAdjacentStreetLocations()) {
				if (sl.getStreet() != null) {
					if (sl.getStreet().getPlayer().getUsername().equals(username)) {
						roads.add(sl);
					}
				}
			}

		}

		for (StreetLocation st : roads) {
			if (!visitedLocations.contains(st) && isConnected(x, y, st)) {
				visitedLocations.add(st);
				queue.add(st);
			}
		}

		int amount = 0;
		for (int i = 0; i < queue.size(); i++) {
			StreetLocation r = queue.get(i);

			int amount_from = getTradeRouteLength(r.getBlStart().getXLoc(), r.getBlStart().getYLoc(), username);
			int amount_to = getTradeRouteLength(r.getBlEnd().getXLoc(), r.getBlEnd().getYLoc(), username);

			amount = Math.max(amount, 1 + amount_from + amount_to);
		}
		// System.out.println(amount);
		return amount;
	}

	private boolean isConnected(int x, int y, StreetLocation r) {
		boolean connected = false;

		connected |= x == r.getBlStart().getXLoc() && y == r.getBlStart().getYLoc();
		connected |= x == r.getBlEnd().getXLoc() && y == r.getBlEnd().getYLoc();

		return connected;
	}

	private void sortBuildingLocationList(ArrayList<BuildingLocation> arrayToSort) {
		Collections.sort(arrayToSort, new Comparator<BuildingLocation>() {
			@Override
			public int compare(BuildingLocation o1, BuildingLocation o2) {
				return o1.getYLoc() - o2.getYLoc();
			}

		});
		Collections.sort(arrayToSort, new Comparator<BuildingLocation>() {
			@Override
			public int compare(BuildingLocation o1, BuildingLocation o2) {
				return o1.getXLoc() - o2.getXLoc();
			}

		});
	}

	public void getBankTradeRequest(int[] resourceRatios, ResourceType resourceTypeToGive,
			ResourceType resourceTypeToReceive) {

		int ratio;
		switch (resourceTypeToGive) {


		case BAKSTEEN:
			ratio = resourceRatios[0];

			break;
		case WOL:
			ratio = resourceRatios[1];
			break;
		case ERTS:
			ratio = resourceRatios[2];
			break;
		case GRAAN:
			ratio = resourceRatios[3];
			break;
		case HOUT:
			ratio = resourceRatios[4];
			break;
		default:
			ratio = 3;
		}


		ArrayList<Resource> resourceCardsToGive = new ArrayList<>();
		Resource resourceCardToReceive;

		resourceCardsToGive = catanGame.getSelfPlayer().getHand().takeMultipleResources(resourceTypeToGive, ratio);
		if (resourceCardsToGive == null) {
			addMessage("Je hebt niet genoeg " + resourceTypeToGive.name() + " kaarten");
			return;
		}

		if (catanGame.getBank().takeResource(resourceTypeToReceive) == null) {
			addMessage("De bank heeft niet genoeg " + resourceTypeToReceive.name() + " kaarten");
			return;
		} else {
			resourceCardToReceive = catanGame.getBank().takeResource(resourceTypeToReceive);
		}

		catanGame.getSelfPlayer().getHand().addResource(resourceCardToReceive);
		catanGame.getBank().addMultipleResources(resourceCardsToGive);
		for (Resource rs : resourceCardsToGive) {
			mainDA.removeResource(rs.getResourceID(), catanGame.getIdGame());
		}
		mainDA.addResourceToPlayer(resourceCardToReceive.getResourceID(), catanGame.getIdGame(),
				catanGame.getSelfPlayer().getIdPlayer());

		addMessage(" ik heb " + ratio + " " + resourceTypeToGive + " kaarten geruild voor een " + resourceTypeToReceive
				+ " kaart met de bank");

// 	public void createTradeRequest(int stoneGive, int woolGive, int ironGive, int wheatGive, int woodGive,
// 			int stoneReceive, int woolReceive, int ironReceive, int wheatReceive, int woodReceive) {
// 		mainDA.createTradeRequest(new TradeRequest(getCatanGame().getSelfPlayer().getIdPlayer(), stoneGive, woolGive, ironGive,
// 				wheatGive, woodGive, stoneReceive, woolReceive, ironReceive, wheatReceive, woodReceive));
// 	}

	public int[] getResourceRatios() {

		int[] resourceRatios = new int[] { 4, 4, 4, 4, 4 };

		ArrayList<Village> villageLocations = catanGame.getSelfPlayer().getVillageArr();
		ArrayList<City> cityLocations = catanGame.getSelfPlayer().getCityArr();

		for (int j = 0; j < villageLocations.size(); j++) {
			if (villageLocations.get(j).getBuildingLocation() != null) {
				if (villageLocations.get(j).getBuildingLocation().getHarbour() != null) {
					setResourceRatio(resourceRatios, villageLocations.get(j).getBuildingLocation());
				}
			}
		}

		for (int i = 0; i < cityLocations.size(); i++) {
			if (cityLocations.get(i).getBuildingLocation() != null) {
				if (cityLocations.get(i).getBuildingLocation().getHarbour() != null) {
					setResourceRatio(resourceRatios, cityLocations.get(i).getBuildingLocation());
				}
			}
		}
		return resourceRatios;
	}

	private void setResourceRatio(int[] resources, BuildingLocation buildingLocation) {

		switch (buildingLocation.getHarbour().getRsType()) {

		case BAKSTEEN:
			resources[0] = 2;
			break;
		case ERTS:
			resources[1] = 2;
			break;
		case WOL:
			resources[2] = 2;
			break;
		case GRAAN:
			resources[3] = 2;
			break;
		case HOUT:
			resources[4] = 2;
			break;
		default:
			for (int i = 0; i < resources.length; i++) {

				if (resources[i] != 2) {
					resources[i] = 3;
				}
			}
		}
	}

	public void createPlayerTradeRequest(int stoneGive, int woolGive, int ironGive, int wheatGive, int woodGive,
			int stoneReceive, int woolReceive, int ironReceive, int wheatReceive, int woodReceive) {

		mainDA.createTradeRequest(new TradeRequest(getCatanGame().getSelfPlayer().getIdPlayer(), stoneGive, woolGive,
				ironGive, wheatGive, woodGive, stoneReceive, woolReceive, ironReceive, wheatReceive, woodReceive));

	}
}
