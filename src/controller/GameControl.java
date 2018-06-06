package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import dbaccess.MainDA;
import model.Building;
import model.BuildingLocation;
import model.Catan;
import model.City;
import model.DevelopmentCard;
import model.Gameboard;
import model.Player;
import model.Resource;
import model.ResourceType;
import model.Street;
import model.StreetLocation;
import model.Tile;
import model.TradeRequest;
import model.Village;

public class GameControl {

	private static final int HALF_RESOURCES_TAKEN = 2;
	private static final int SEVEN_CARD_RULE = 7;

	private GameBoardControl gameBoardControl;
	private GuiController guiController;
	private MainDA mainDA;
	// private String username;
	private Catan catanGame;
	private Thread tradeRequestThread;
	private boolean firstRoundActive;

	public GameControl(MainDA mainDA) {
		this.mainDA = mainDA;
		// This will be added in the GuiController(). So it won't be null in the
		// program.

		guiController = null;
	}

	public int createGameInDB(boolean randomBoard) {
		/**
		 * Create a game record in the DB AND sets idGame
		 */
		int gameID = mainDA.createGame(randomBoard);
		gameBoardControl = new GameBoardControl(mainDA, gameID);

		// TODO add gameboard to db
		return gameID;

	}

	public void addLogMessage(String message) {
		mainDA.addMessage(catanGame.getSelfPlayer().getIdPlayer(), message);
	}

	public boolean addPlayerMessage(String message) {
		message = catanGame.getSelfPlayer().getUsername() + ": " + message;
		message = message.replace("\'", "");
		message = message.replace("\\", "-");
		if (mainDA.addMessage(catanGame.getSelfPlayer().getIdPlayer(), message)) {
			return true;
		} else {
			return false;
		}
	}

	// public boolean addPlayerMessage(String message, Player player) {
	// message = player.getUsername() + ": " + message;
	// if (mainDA.addMessage(catanGame.getSelfPlayer().getIdPlayer(), message)) {
	// return true;
	// } else {
	// return false;
	// }
	// }

	public void changeRobber(int idTile) {
		catanGame.getGameboard().setRobber(idTile);
		changeRobberInDB(idTile);
		guiController.showRobberDialog();

		enableEveryoneShouldRefresh();

	}

	public void changeRobberInDB(int idTile) {
		this.mainDA.changeRobberLocation(catanGame.getIdGame(), idTile);
	}

	public void makeBank() {

	}

	public void editDiceLastThrown(int[] die) {
		mainDA.setLastThrow(die[0], die[1], catanGame.getIdGame());
	}

	public void rollDice() {
		catanGame.rollDice();
		int rolledValue = catanGame.getDice().getValue();

		// This has to be before the if-statement,
		// otherwise first the message will be who gets the resources, and afterwards
		// the value rolled.
		addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
				+ catanGame.getSelfPlayer().getColor().toString().toLowerCase() + ") heeft " + rolledValue
				+ " gegooid.");
		if (rolledValue == 7) {
			guiController.addSystemMessageToChat(Color.BLUE, "Je hebt 7 gegooit, Verplaats de Rover");
			enableRobber();
			takeAwayHalfResources();
		} else {
			giveTurnResources(rolledValue);
			guiController.enableUnplayedDevelopmentCards();
			guiController.enablePlayerActionPanel();
		}

		// Edit database with rolled values
		editDiceLastThrown(catanGame.getDice().getSeperateValues());
		mainDA.setThrownDice(1, catanGame.getIdGame());
		catanGame.setRolledDice(true);
		enableEveryoneShouldRefresh();
	}

	public void enableRobber() {
		guiController.getBoardPanel().enableTileButtons();
	}

	private ArrayList<Player> getPlayersAroundTile(Tile tile) {
		ArrayList<BuildingLocation> buildingLocations = tile.getBuildingLocArr();
		ArrayList<Player> playersAtRobberTile = new ArrayList<>();
		for (BuildingLocation buildingLocation : buildingLocations) {
			if (buildingLocation.hasBuilding()) {
				Player newPlayer = buildingLocation.getBuilding().getPlayer();
				boolean playerExistsInArrray = false;
				for (Player player : playersAtRobberTile) {
					if (newPlayer == player) {
						playerExistsInArrray = true;
						break;
					}
				}
				if (!playerExistsInArrray && newPlayer != catanGame.getSelfPlayer()) {
					playersAtRobberTile.add(newPlayer);
				}
			}
		}
		return playersAtRobberTile;
	}

	public void takeAwayHalfResources() {
		for (Player p : catanGame.getPlayers()) {
			if (p.getHand().getResources().size() > SEVEN_CARD_RULE) {
				int amountOfResourcesToTake = p.getHand().getResources().size() / HALF_RESOURCES_TAKEN;
				for (int i = 0; i < amountOfResourcesToTake; i++) {
					Resource rs = p.getHand().takeRandomResource();
					catanGame.getBank().addResource(rs);
					mainDA.removeResource(rs.getResourceID(), catanGame.getIdGame());
				}
			}

		}
		guiController.refreshPlayerResources();

	}

	public void setDiceLastThrown(int[] die) {
		catanGame.getDice().setDie(die);
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

		for (StreetLocation sl : buildingLocation.getAdjacentStreetLocations()) {
			if (sl.getBlEnd().getBuilding() != null) {
				return false;
			} else if (sl.getBlStart().getBuilding() != null) {
				return false;
			}
		}

		boolean hasFriendlyAdjacentStreet = false;
		for (StreetLocation sl : buildingLocation.getAdjacentStreetLocations()) {

			if (sl.getStreet() != null) {
				if (sl.getStreet().getPlayer().equals(catanGame.getSelfPlayer())) {
					hasFriendlyAdjacentStreet = true;
				}
			}
		}

		if (!hasFriendlyAdjacentStreet) {
			return false;
		}

		// if(buildingLocation.getAdjacentStreetLocations())
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

		// TODO check if there are streets connected to this location

		buildingLocation.setVillage(catanGame.getSelfPlayer().getAvailableVillage());
		village.setBuildingLocation(buildingLocation);
		mainDA.updateBuilding(village.getIdBuilding(), village.getPlayer().getIdPlayer(), buildingLocation.getXLoc(),
				buildingLocation.getYLoc());
		payResources(Village.cost);
		enableEveryoneShouldRefresh();
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
		payResources(City.cost);

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
				enableEveryoneShouldRefresh();
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
		payResources(Street.cost);

		streetLocation.setStreet(street);
		street.setStreetLocation(streetLocation);
		mainDA.updateStreet(street.getIdBuilding(), street.getPlayer().getIdPlayer(),
				streetLocation.getBlStart().getXLoc(), streetLocation.getBlStart().getYLoc(),
				streetLocation.getBlEnd().getXLoc(), streetLocation.getBlEnd().getYLoc());
		enableEveryoneShouldRefresh();
		return true;
	}

	private void payResources(ResourceType[] cost) {
		ArrayList<Resource> toPay = new ArrayList<>();
		for (ResourceType rsType : cost) {
			Resource rs = catanGame.getSelfPlayer().getHand().takeResource(rsType);
			toPay.add(rs);
			mainDA.removeResource(rs.getResourceID(), catanGame.getIdGame());
		}
		catanGame.getBank().addMultipleResources(toPay);
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

	public void setCatan(Catan game) {
		this.catanGame = game;
		gameBoardControl = new GameBoardControl(mainDA, catanGame.getIdGame());
		Gameboard gameboard = gameBoardControl.loadBoard();
		game.fillCatan(gameboard);
		catanGame.getBank().setResources(mainDA.updateResources(catanGame.getIdGame(), 0));
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

		resourceCardsToGive = catanGame.getSelfPlayer().getHand().takeMultipleResources(resourceTypeToGive, ratio);
		if (resourceCardsToGive == null) {
			guiController.addSystemMessageToChat(Color.RED,
					"Je hebt niet genoeg " + resourceTypeToGive.name() + " kaarten");
			return;
		}

		Resource resourceCardToReceive = null;
		try {
			resourceCardToReceive = catanGame.getBank().takeResource(resourceTypeToReceive);

		} catch (Exception e) {
			guiController.addSystemMessageToChat(Color.RED,
					"De bank heeft niet genoeg " + resourceTypeToReceive.name() + " kaarten");
			return;
		}

		catanGame.getSelfPlayer().getHand().addResource(resourceCardToReceive);
		catanGame.getBank().addMultipleResources(resourceCardsToGive);
		for (Resource rs : resourceCardsToGive) {
			mainDA.removeResource(rs.getResourceID(), catanGame.getIdGame());
		}
		mainDA.addResourceToPlayer(resourceCardToReceive.getResourceID(), catanGame.getIdGame(),
				catanGame.getSelfPlayer().getIdPlayer());

		addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
				+ catanGame.getSelfPlayer().getColor().toString().toLowerCase() + ") heeft " + ratio + " "
				+ resourceTypeToGive + " geruild voor een " + resourceTypeToReceive + " kaart met de bank");
	}

	public void enableEveryoneShouldRefresh() {
		for (Player p : catanGame.getPlayers()) {
			boolean done = false;
			while (!done) {
				done = mainDA.setShouldRefresh(p.getIdPlayer(), true);
			}
		}
	}

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

		if (buildingLocation.getHarbour().getRsType() != null) {
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
			}
		} else {
			for (int i = 0; i < resources.length; i++) {
				if (resources[i] != 2) {
					resources[i] = 3;
				}
			}
		}
	}

	public void createPlayerTradeRequest(int stoneGive, int woolGive, int ironGive, int wheatGive, int woodGive,
			int stoneReceive, int woolReceive, int ironReceive, int wheatReceive, int woodReceive) {
		System.out.println(stoneGive + woolGive + ironGive + wheatGive + woodGive + stoneReceive + woolReceive
				+ ironReceive + wheatReceive + woodReceive);

		mainDA.createTradeRequest(new TradeRequest(getCatanGame().getSelfPlayer().getIdPlayer(), stoneGive, woolGive,
				ironGive, wheatGive, woodGive, stoneReceive, woolReceive, ironReceive, wheatReceive, woodReceive, 3));
		enableEveryoneShouldRefresh();
	}

	public void countTradeOffers() {
		tradeRequestThread = new Thread(new Runnable() {
			@Override
			public void run() {
				boolean hasAll = false;
				int amountOfOpenRequests = 0;
				while (!hasAll) {
					System.out.println("check Amount of open request");
					amountOfOpenRequests = mainDA.getAmountOfOpenRequests(catanGame.getIdGame());
					System.out.println("!hasall");
					if (amountOfOpenRequests == 4) {
						System.out.println("hasall");
						hasAll = true;
						gatherCounterOffers();
						guiController.fillTradeRequest();
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		tradeRequestThread.start();
	}

	private void gatherCounterOffers() {

		ArrayList<TradeRequest> tradeRequestArr = new ArrayList<>();
		for (Player p : catanGame.getPlayers()) {
			if (p.getIdPlayer() != catanGame.getSelfPlayer().getIdPlayer()) {
				TradeRequest tr = mainDA.getSingleTradeRequest(p.getIdPlayer());
				if (tr != null) {
					tradeRequestArr.add(tr);
				}
			}
		}
		catanGame.setTradeRequests(tradeRequestArr);
	}

	public void acceptTradeRequest(TradeRequest oldtR) {

		TradeRequest tR = new TradeRequest(getCatanGame().getSelfPlayer().getIdPlayer(), oldtR.getW_brick(),
				oldtR.getW_wool(), oldtR.getW_iron(), oldtR.getW_wheat(), oldtR.getW_wood(), oldtR.getG_brick(),
				oldtR.getG_wool(), oldtR.getG_iron(), oldtR.getG_wheat(), oldtR.getG_wood(), 1);

		mainDA.createTradeRequest(tR);
		setShouldRefreshEnabled(oldtR.getIdPlayer());
	}

	public void declineTradeRequest(TradeRequest oldtR) {

		TradeRequest tR = new TradeRequest(getCatanGame().getSelfPlayer().getIdPlayer(), oldtR.getW_brick(),
				oldtR.getW_wool(), oldtR.getW_iron(), oldtR.getW_wheat(), oldtR.getW_wood(), oldtR.getG_brick(),
				oldtR.getG_wool(), oldtR.getG_iron(), oldtR.getG_wheat(), oldtR.getG_wood(), 0);

		mainDA.createTradeRequest(tR);
		setShouldRefreshEnabled(oldtR.getIdPlayer());
	}

	public void deleteTradeRequest() {
		mainDA.deleteTradeRequests(catanGame.getIdGame());
	}

	// check for incoming trade requests
	public TradeRequest updateTradeRequests() {
		return mainDA.getInitialTradeRequest(catanGame.getIdGame());
	}

	public void setGuiController(GuiController guiController) {
		this.guiController = guiController;
	}

	public void commenceTrade(int tradeNumber) {
		// swap resources in db
		TradeRequest tr = catanGame.getTradeRequestArr().get(tradeNumber);
		int wBrick = tr.getG_brick();
		int wWool = tr.getG_wool();
		int wIron = tr.getG_iron();
		int wWheat = tr.getG_wheat();
		int wWood = tr.getG_wood();
		int gBrick = tr.getW_brick();
		int gWool = tr.getW_wool();
		int gIron = tr.getW_iron();
		int gWheat = tr.getW_wheat();
		int gWood = tr.getW_wood();
		int idPlayer = tr.getIdPlayer();
		// Swapped resources to match trade request.

		System.out.println(wBrick);// 0
		System.out.println(wWool);// 1
		System.out.println(wIron);// 0
		System.out.println(wWheat);// 2
		System.out.println(wWood);// 0

		System.out.println(gBrick);// 0
		System.out.println(gWool);// 0
		System.out.println(gIron);// 1
		System.out.println(gWheat);// 0
		System.out.println(gWood);// 0

		ArrayList<Resource> giveArray = new ArrayList<Resource>();
		if (gBrick > 0) {
			for (Resource r : catanGame.getSelfPlayer().getHand().takeMultipleResources(ResourceType.BAKSTEEN,
					gBrick)) {
				giveArray.add(r);
			}
		}
		if (gWool > 0) {
			for (Resource r : catanGame.getSelfPlayer().getHand().takeMultipleResources(ResourceType.WOL, gWool)) {
				giveArray.add(r);
			}
		}
		if (gIron > 0) {
			for (Resource r : catanGame.getSelfPlayer().getHand().takeMultipleResources(ResourceType.ERTS, gIron)) {
				giveArray.add(r);
			}
		}
		if (gWheat > 0) {
			for (Resource r : catanGame.getSelfPlayer().getHand().takeMultipleResources(ResourceType.GRAAN, gWheat)) {
				giveArray.add(r);
			}
		}
		if (gWood > 0) {
			for (Resource r : catanGame.getSelfPlayer().getHand().takeMultipleResources(ResourceType.HOUT, gWood)) {
				giveArray.add(r);
			}
		}

		Player tradePlayer = catanGame.getPlayerByID(idPlayer);

		ArrayList<Resource> receiveArray = new ArrayList<Resource>();
		if (wBrick > 0) {
			for (Resource r : tradePlayer.getHand().takeMultipleResources(ResourceType.BAKSTEEN, wBrick)) {
				receiveArray.add(r);
			}
		}
		if (wWool > 0) {
			for (Resource r : tradePlayer.getHand().takeMultipleResources(ResourceType.WOL, wWool)) {
				receiveArray.add(r);
			}
		}
		if (wIron > 0) {
			for (Resource r : tradePlayer.getHand().takeMultipleResources(ResourceType.ERTS, wIron)) {
				receiveArray.add(r);
			}
		}
		if (wWheat > 0) {
			for (Resource r : tradePlayer.getHand().takeMultipleResources(ResourceType.GRAAN, wWheat)) {
				receiveArray.add(r);
			}
		}
		if (wWood > 0) {
			for (Resource r : tradePlayer.getHand().takeMultipleResources(ResourceType.HOUT, wWood)) {
				receiveArray.add(r);
			}
		}

		for (Resource r : giveArray) {
			tradePlayer.getHand().addResource(r);
			mainDA.addResourceToPlayer(r.getResourceID(), catanGame.getIdGame(), tradePlayer.getIdPlayer());
		}

		for (Resource r : receiveArray) {
			catanGame.getSelfPlayer().getHand().addResource(r);
			mainDA.addResourceToPlayer(r.getResourceID(), catanGame.getIdGame(),
					catanGame.getSelfPlayer().getIdPlayer());
		}

		mainDA.deleteTradeRequests(catanGame.getIdGame());

		catanGame.getTradeRequestArr().clear();
		addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
				+ catanGame.getSelfPlayer().getColor().toString().toLowerCase() + ") heeft het tegenaanbod van "
				+ tradePlayer.getUsername() + " (" + catanGame.getSelfPlayer().getColor().toString().toLowerCase()
				+ ") geaccepteerd");

		// swap resources in code
		// remove traderequests in db
		// remove traderequests in catanGame
		enableEveryoneShouldRefresh();
	}

	public void doDevCardRoadBuilding() {

		// if first time enable

		getCatanGame().setRoadBuilding(true);
		getCatanGame().setRoadBuildingFirst(true);
		if (catanGame.getSelfPlayer().getAmountAvailableStreets() != 0) {
			guiController.enableStreetLocButtons();
		} else {
			guiController.addSystemMessageToChat(Color.RED, "Je hebt niet genoeg straten om te bouwen");
			getCatanGame().setRoadBuilding(false);
		}

	}

	public void doDevCardMonopoly(ResourceType rsType) {
		// get all cards from all player of a certain rsType
		for (Player p : catanGame.getPlayers()) {
			for (Resource r : p.getHand().takeAllResourcesFromRsType(rsType)) {
				catanGame.getSelfPlayer().getHand().addResource(r);
				mainDA.addResourceToPlayer(r.getResourceID(), catanGame.getIdGame(),
						catanGame.getSelfPlayer().getIdPlayer());
			}
		}
		enableEveryoneShouldRefresh();
	}

	public void doDevCardYearOfPlenty(ResourceType rsType1, ResourceType rsType2) {
		Resource rs1 = null;
		try {
			rs1 = catanGame.getBank().takeResource(rsType1);
		} catch (Exception e) {
			guiController.addSystemMessageToChat(Color.RED, "de bank heeft niet genoeg " + rsType1 + " kaarten");
		}
		Resource rs2 = null;
		try {
			rs2 = catanGame.getBank().takeResource(rsType2);
		} catch (Exception e) {
			guiController.addSystemMessageToChat(Color.RED, "de bank heeft niet genoeg " + rsType2 + " kaarten");
		}

		if (rs1 != null) {
			mainDA.addResourceToPlayer(rs1.getResourceID(), catanGame.getIdGame(),
					catanGame.getSelfPlayer().getIdPlayer());
			catanGame.getSelfPlayer().getHand().addResource(rs1);
			addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
					+ catanGame.getSelfPlayer().getColor().toString().toLowerCase()
					+ ") heeft een uitvindingskaart gespeeld");
			addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
					+ catanGame.getSelfPlayer().getColor().toString().toLowerCase() + ") heeft een "
					+ rs1.getRsType().toString() + " kaart ontvangen van de bank");
		}

		if (rs2 != null) {
			mainDA.addResourceToPlayer(rs2.getResourceID(), catanGame.getIdGame(),
					catanGame.getSelfPlayer().getIdPlayer());
			catanGame.getSelfPlayer().getHand().addResource(rs2);
			addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
					+ catanGame.getSelfPlayer().getColor().toString().toLowerCase() + ") heeft een "
					+ rs2.getRsType().toString() + " kaart ontvangen van de bank");
		}

		enableEveryoneShouldRefresh();
	}

	public void playFirstRound() {
		catanGame.setRolledDice(true);
		guiController.disablePlayerActionPanel();
		guiController.getBoardPanel().enableBuildingLocButtons(false);
		guiController.addSystemMessageToChat(Color.BLUE, "Klik op een bouwlocatie om je dorp te bouwen");
	}

	public void playRound() {
		// normale rondes
	}

	public void endTurn() {
		catanGame.setRolledDice(false);
		try {
			if (catanGame.getSelfPlayer().getFollownr() == 4) {
				for (Player p : catanGame.getPlayers()) {
					if (p.getFollownr() == 1) {
						mainDA.setTurn(p.getIdPlayer(), catanGame.getIdGame());
						catanGame.setTurn(p.getIdPlayer());
						addLogMessage(
								p.getUsername() + " (" + p.getColor().toString().toLowerCase()
										+ ") is nu aan de Beurt.");
						mainDA.setThrownDice(0, catanGame.getIdGame());
						enableEveryoneShouldRefresh();
						break;
					}
				}
			} else {
				for (Player p : catanGame.getPlayers()) {
					if (p.getFollownr() == catanGame.getSelfPlayer().getFollownr() + 1) {
						mainDA.setTurn(p.getIdPlayer(), catanGame.getIdGame());
						catanGame.setTurn(p.getIdPlayer());
						addLogMessage(
								p.getUsername() + " (" + p.getColor().toString().toLowerCase()
								+ ") is nu aan de Beurt.");
						mainDA.setThrownDice(0, catanGame.getIdGame());
						enableEveryoneShouldRefresh();
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("endTurn failed");
		}

	}

	public void robberTakeResource(Player player) {

		int robbedPlayerID = player.getIdPlayer();
		Resource randomResource = getCatanGame().getPlayerByID(robbedPlayerID).getHand().takeRandomResource();
		getCatanGame().getSelfPlayer().getHand().addResource(randomResource);
		mainDA.addResourceToPlayer(randomResource.getResourceID(), catanGame.getIdGame(),
				catanGame.getSelfPlayer().getIdPlayer());
		guiController.refreshPlayerResources();
		// TODO check if you have to show the rstype or just tell that he stole a card
		addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
				+ catanGame.getSelfPlayer().getColor().toString().toLowerCase() + ") heeft een "
				+ randomResource.getRsType().toString().toLowerCase() + " van " + player.getUsername() + " ("
				+ player.getColor().toString().toLowerCase() + ") gestolen");
		enableEveryoneShouldRefresh();

	}

	public void doTurn() {
		if (!mainDA.hasThrown(catanGame.getIdGame())) {

			guiController.disablePlayerActionPanel();
			guiController.enableDice();
		}
	}

	public void giveTurnResources(int number) {
		HashMap<Player, ArrayList<Resource>> resourcesHashMap = new HashMap<>();
		for (Tile t : catanGame.getGameboard().getTileArr()) {
			if (!t.hasRobber()) {
				if (t.getChipNumber() == number) {
					for (BuildingLocation bl : t.getBuildingLocArr()) {
						Building building = bl.getBuilding();
						if (building != null) {
							ResourceType rsTypeOfTile = t.getRsType();
							String resourceTypeString = rsTypeOfTile.toString().toLowerCase();
							Player player = building.getPlayer();
							if (bl.getCity() != null) {
								// give two
								ArrayList<Resource> rsArr = catanGame.getBank().takeMultipleResources(rsTypeOfTile, 2);
								for (Resource rs : rsArr) {
									building.getPlayer().getHand().addResource(rs);
									mainDA.addResourceToPlayer(rs.getResourceID(), catanGame.getIdGame(),
											building.getPlayer().getIdPlayer());

									// Add the resource to the HashMap
									if (resourcesHashMap.containsKey(player)) {
										resourcesHashMap.get(player).add(rs);
									} else {
										ArrayList<Resource> resourcesOfPlayer = new ArrayList<>();
										resourcesOfPlayer.add(rs);
										resourcesHashMap.put(player, resourcesOfPlayer);
									}
								}
								if (rsArr.size() == 0) {
									addPlayerMessage("Helaas, de bank heeft geen " + resourceTypeString + "meer.");
								}
							} else if (bl.getVillage() != null) {
								// give one
								try {
									Resource rs = catanGame.getBank().takeResource(rsTypeOfTile);
									building.getPlayer().getHand().addResource(rs);
									mainDA.addResourceToPlayer(rs.getResourceID(), catanGame.getIdGame(),
											building.getPlayer().getIdPlayer());

									// Add the resource to the HashMap
									if (resourcesHashMap.containsKey(player)) {
										resourcesHashMap.get(player).add(rs);
									} else {
										ArrayList<Resource> resourcesOfPlayer = new ArrayList<>();
										resourcesOfPlayer.add(rs);
										resourcesHashMap.put(player, resourcesOfPlayer);
									}
								} catch (Exception e) {
									addPlayerMessage("Helaas, de bank heeft geen " + resourceTypeString + "meer.");
								}
							}
						}
					}
				}
			}
		}

		logResources(resourcesHashMap);
		enableEveryoneShouldRefresh();
		guiController.refreshPlayerResources();
	}

	public void setShouldRefreshEnabled(int idPlayer) {
		mainDA.setShouldRefresh(idPlayer, true);

	}

	public boolean buildInitialVillage(BuildingLocation buildingLocation) {
		Village village = catanGame.getSelfPlayer().getAvailableVillage();
		// check if player has a village to build

		// check if nothing is build already on that location
		if (buildingLocation.getVillage() != null || buildingLocation.getCity() != null) {
			return false;
		}

		for (StreetLocation sl : buildingLocation.getAdjacentStreetLocations()) {
			if (sl.getBlEnd().getBuilding() != null) {
				return false;
			} else if (sl.getBlStart().getBuilding() != null) {
				return false;
			}
		}

		buildingLocation.setVillage(catanGame.getSelfPlayer().getAvailableVillage());
		village.setBuildingLocation(buildingLocation);

		// Player gets from his/her second village the start resources
		if (catanGame.getSelfPlayer().getAmountBuildVillages() == 2) {
			giveStartResources(village);
		}
		mainDA.updateBuilding(village.getIdBuilding(), village.getPlayer().getIdPlayer(), buildingLocation.getXLoc(),
				buildingLocation.getYLoc());
		enableEveryoneShouldRefresh();
		return true;
	}

	/**
	 * This method gives the player the resources at the start.
	 * 
	 * @param village
	 *            the village that the player gets resources from
	 * @since 30 May 2018
	 * @author Jasper Mooren
	 */
	private void giveStartResources(Village village) {
		ArrayList<Resource> resourcesGiven = new ArrayList<>();

		// Add the actual gameBoard
		for (Tile tile : catanGame.getGameboard().getTileArr()) {
			for (BuildingLocation buildingLocation : tile.getBuildingLocArr()) {
				if (village.getBuildingLocation() == buildingLocation) {
					try {
						Resource resource = catanGame.getBank().takeResource(tile.getRsType());
						if (resource.getRsType() != ResourceType.WOESTIJN) {
							// Add to database
							mainDA.addResourceToPlayer(resource.getResourceID(), catanGame.getIdGame(),
									catanGame.getSelfPlayer().getIdPlayer());
							// Add to model
							catanGame.getSelfPlayer().getHand().addResource(resource);
							resourcesGiven.add(resource);
						}
					} catch (Exception e) {
						addPlayerMessage(
								"Helaas, de bank heeft geen " + tile.getRsType().toString().toLowerCase() + "meer.");
					}
				}
			}
		}

		logResources(resourcesGiven);

	}

	/**
	 * @param resources
	 *            the resources that should be logged
	 * @since 1 Jun 2018
	 * @author Jasper Mooren
	 */
	private void logResources(ArrayList<Resource> resources) {
		// Create the HashMap to make the String for the Log
		HashMap<ResourceType, Integer> resourcesGivenHashMap = new HashMap<>();
		for (Resource resource : resources) {
			ResourceType resourceType = resource.getRsType();
			if (resourcesGivenHashMap.containsKey(resourceType)) {
				resourcesGivenHashMap.put(resourceType, 0);
			}
			for (Resource resource2 : resources) {
				if (resourceType == resource2.getRsType()) {
					int value = 0;
					if (resourcesGivenHashMap.get(resourceType) != null) {
						value = resourcesGivenHashMap.get(resourceType);
					}
					resourcesGivenHashMap.put(resourceType, value + 1);
				}
			}
		}

		// Create the String from the HashMap for the Log
		String resourcesGivenString = "";
		for (ResourceType resourceType : ResourceType.values()) {
			if (resourcesGivenHashMap.containsKey(resourceType)) {
				resourcesGivenString += "krijgt " + resourcesGivenHashMap.get(resourceType) + " "
						+ resourceType.toString().toLowerCase() + ", ";
			}
		}

		resourcesGivenString = resourcesGivenString.substring(0, resourcesGivenString.length() - 2);

		addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
				+ catanGame.getSelfPlayer().getColor().toString().toLowerCase() + ") " + resourcesGivenString);
	}

	/**
	 * @param resourcesHashMap
	 *            all the resources of all the players
	 * @since 1 Jun 2018
	 * @author Jasper Mooren
	 */
	private void logResources(HashMap<Player, ArrayList<Resource>> resourcesHashMap) {
		String logMessage = "";

		for (Player player : resourcesHashMap.keySet()) {
			ArrayList<Resource> resources = resourcesHashMap.get(player);

			// Create the HashMap to make the String for the Log
			HashMap<ResourceType, Integer> resourcesGivenHashMap = new HashMap<>();
			for (Resource resource : resources) {
				ResourceType resourceType = resource.getRsType();
				if (resourcesGivenHashMap.containsKey(resourceType)) {
					resourcesGivenHashMap.put(resourceType, 0);
				}
				for (Resource resource2 : resources) {
					if (resourceType == resource2.getRsType()) {
						int value = 0;
						// If the resourceType is not in the HashMap yet, it returns null, therefore the
						// value will be 0
						if (resourcesGivenHashMap.get(resourceType) != null) {
							value = resourcesGivenHashMap.get(resourceType);
						}
						resourcesGivenHashMap.put(resourceType, value + 1);
					}
				}
			}

			// Create the String from the HashMap for the Log
			for (ResourceType resourceType : ResourceType.values()) {
				if (resourcesGivenHashMap.containsKey(resourceType)) {
					logMessage += player.getUsername() + " (" + player.getColor().toString().toLowerCase() + ") krijgt "
							+ resourcesGivenHashMap.get(resourceType) + " " + resourceType.toString().toLowerCase()
							+ ", ";
				}
			}
		}
		logMessage = logMessage.substring(0, logMessage.length() - 2);
		addLogMessage(logMessage);
	}

	public boolean isFirstRoundActive() {
		return firstRoundActive;
	}

	public void setFirstRoundActive(boolean firstRoundActive) {
		this.firstRoundActive = firstRoundActive;
	}

	public void endFirstRoundTurn() {

		int amountOfVillages = 0;

		for (Village v : catanGame.getSelfPlayer().getVillageArr()) {
			if (v.getBuildingLocation() != null) {
				amountOfVillages++;
			}
		}
		System.out.println("amount of villages: " + amountOfVillages);
		if (amountOfVillages < 2) {
			// Turn forward
			if (catanGame.getSelfPlayer().getFollownr() == 4) {
				for (Player p : catanGame.getPlayers()) {
					if (p.getFollownr() == 4) {
						mainDA.setTurn(p.getIdPlayer(), catanGame.getIdGame());
						catanGame.setTurn(p.getIdPlayer());
						System.out.println("1 fw set next turn for " + p.getIdPlayer());
						enableEveryoneShouldRefresh();
						addLogMessage(p.getUsername() + " (" + p.getColor().toString().toLowerCase()
								+ ") is nu aan de Beurt.");
						break;
					}
				}
			} else {
				for (Player p : catanGame.getPlayers()) {
					if (p.getFollownr() == catanGame.getSelfPlayer().getFollownr() + 1) {
						mainDA.setTurn(p.getIdPlayer(), catanGame.getIdGame());
						catanGame.setTurn(p.getIdPlayer());
						System.out.println("2 fw set next turn for " + p.getIdPlayer());
						enableEveryoneShouldRefresh();
						addLogMessage(p.getUsername() + " (" + p.getColor().toString().toLowerCase()
								+ ") is nu aan de Beurt.");
						break;
					}
				}
			}
		} else {
			// Turn backwards
			if (catanGame.getSelfPlayer().getFollownr() == 1) {
				// end first round and start normal round
				mainDA.setTurn(catanGame.getSelfPlayer().getIdPlayer(), catanGame.getIdGame());
				catanGame.setTurn(catanGame.getSelfPlayer().getIdPlayer());

				mainDA.setThrownDice(0, catanGame.getIdGame());

				catanGame.setRolledDice(false);

				catanGame.setFirstRound(false);
				System.out.println("firstround:  " + catanGame.isFirstRound());
				mainDA.setFirstRound(0, catanGame.getIdGame());

				enableEveryoneShouldRefresh();
				addLogMessage(catanGame.getSelfPlayer().getUsername() + " is nu aan de Beurt.");
			} else {
				for (Player p : catanGame.getPlayers()) {
					if (p.getFollownr() == catanGame.getSelfPlayer().getFollownr() - 1) {
						mainDA.setTurn(p.getIdPlayer(), catanGame.getIdGame());
						catanGame.setTurn(p.getIdPlayer());
						System.out.println("3 bw set next turn for " + p.getIdPlayer());

						enableEveryoneShouldRefresh();
						addLogMessage(p.getUsername() + " (" + p.getColor().toString().toLowerCase()
								+ ") is nu aan de Beurt.");
						break;
					}
				}
			}
		}
		firstRoundActive = false;

	}

	public boolean buildInitialStreet(StreetLocation streetLocation) {
		Street street = catanGame.getSelfPlayer().getAvailableStreet();

		if (streetLocation.getStreet() != null) {
			System.out.println("already a street here");
			return false;
		}

		if (!streetLocation.hasAdjacentFriendlySettlement(catanGame.getSelfPlayer())
				&& !streetLocation.hasAdjecentFriendlyStreet(catanGame.getSelfPlayer())) {
			System.out.println("no adjecent friendly street or settlements");
			return false;
		}

		streetLocation.setStreet(street);
		street.setStreetLocation(streetLocation);
		mainDA.updateStreet(street.getIdBuilding(), street.getPlayer().getIdPlayer(),
				streetLocation.getBlStart().getXLoc(), streetLocation.getBlStart().getYLoc(),
				streetLocation.getBlEnd().getXLoc(), streetLocation.getBlEnd().getYLoc());
		enableEveryoneShouldRefresh();

		guiController.refreshPlayerResources();

		return true;
	}

	public DevelopmentCard buyDevelopmentCard() {
		DevelopmentCard developmentCard = catanGame.getBank().takeDevelopmentCard();
		if (developmentCard != null) {
			catanGame.getSelfPlayer().getHand().addDevelopmentCard(developmentCard);
			mainDA.addDevelopmentCardToPlayer(developmentCard.getDevelopmentCardID(),
					catanGame.getSelfPlayer().getIdPlayer(), catanGame.getIdGame());
			payResources(DevelopmentCard.CARD_COST);
		} else {
			guiController.addSystemMessageToChat(Color.RED, "De bank heeft niet genoeg ontwikkelingskaarten");
		}
		return developmentCard;
	}

	public void checkForWinner() {
		Player winner = null;

		for (Player p : catanGame.getPlayers()) {
			if (p.getVictoryPoints() >= 10) {
				winner = p;
				mainDA.finishGame(catanGame.getIdGame());
				addLogMessage(p.getUsername() + " (" + p.getColor().toString().toLowerCase() + ") heeft gewonnen");
				addLogMessage("Het spel is afgelopen");
				guiController.setwinnerDialog(winner);
				return;
			}
		}
	}

	public void updateDevCardInDB(String developmentCardID) {
		mainDA.useDevelopmentCard(developmentCardID, catanGame.getIdGame());
	}

	public void calculateLargestArmy() {
		if (!catanGame.getSelfPlayer().getHasLargestArmy()) {
			if (catanGame.getSelfPlayer().getAmountOfKnights() >= 2) {
				boolean anyoneHas = false;
				for (Player p : catanGame.getPlayers()) {

					if (!p.equals(catanGame.getSelfPlayer())) {
						if (p.getHasLargestArmy()) {
							if (catanGame.getSelfPlayer().getAmountOfKnights() > p.getAmountOfKnights()) {
								p.setHasLargestArmy(false);
								catanGame.getSelfPlayer().setHasLargestArmy(true);
								mainDA.updateLargestArmy(catanGame.getIdGame(),
										catanGame.getSelfPlayer().getIdPlayer());
								addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
										+ catanGame.getSelfPlayer().getColor().toString().toLowerCase()
										+ ") heeft nu de grootste riddermacht");
								anyoneHas = true;
								enableEveryoneShouldRefresh();
								break;
							}
						}
					}
				}
				if (!anyoneHas) {
					catanGame.getSelfPlayer().setHasLargestArmy(true);
					mainDA.updateLargestArmy(catanGame.getIdGame(), catanGame.getSelfPlayer().getIdPlayer());
					addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
							+ catanGame.getSelfPlayer().getColor().toString().toLowerCase()
							+ ") heeft nu de grootste riddermacht");
					anyoneHas = true;
					enableEveryoneShouldRefresh();
				}
			}
		}
	}

	public void calculateLongestTradeRoute() {
		if (!catanGame.getSelfPlayer().getHasLongestRoad()) {
			if (getTradeRouteLength(catanGame.getSelfPlayer().getUsername()) >= 5) {
				boolean anyoneHas = false;
				for (Player p : catanGame.getPlayers()) {
					if (!p.equals(catanGame.getSelfPlayer())) {
						if (p.getHasLongestRoad()) {
							if (getTradeRouteLength(catanGame.getSelfPlayer().getUsername()) > getTradeRouteLength(
									p.getUsername())) {
								p.setHasLongestRoad(false);
								catanGame.getSelfPlayer().setHasLongestRoad(true);
								mainDA.updateLongestTradeRoute(catanGame.getIdGame(),
										catanGame.getSelfPlayer().getIdPlayer());
								addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
										+ catanGame.getSelfPlayer().getColor().toString().toLowerCase()
										+ ") heeft nu de langste handelsroute");
								anyoneHas = true;
								enableEveryoneShouldRefresh();
								break;
							}

						}
					}
				}
				if (!anyoneHas) {
					catanGame.getSelfPlayer().setHasLongestRoad(true);
					mainDA.updateLongestTradeRoute(catanGame.getIdGame(), catanGame.getSelfPlayer().getIdPlayer());
					addLogMessage(catanGame.getSelfPlayer().getUsername() + " ("
							+ catanGame.getSelfPlayer().getColor().toString().toLowerCase()
							+ ") heeft nu de langste handelsroute");
					anyoneHas = true;
					enableEveryoneShouldRefresh();
				}
			}
		}
	}
}
