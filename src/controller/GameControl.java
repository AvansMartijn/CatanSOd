package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import dbaccess.MainDA;
import model.BuildingLocation;
import model.Catan;
import model.City;
import model.Gameboard;
import model.Hand;
import model.Player;
import model.Resource;
import model.ResourceType;
import model.Street;
import model.StreetLocation;
import model.Tile;
import model.TradeRequest;
import model.Village;

import view.ChatPanel;
import view.PlayerStatsPanel;


public class GameControl {

	private static final int HALF_RESOURCES_TAKEN = 2;
	private static final int SEVEN_CARD_RULE = 7;

	private GameBoardControl gameBoardControl;
	private GuiController guiController;
	private MainDA mainDA;
	private ChatPanel chatPanel;
	// private String username;
	private Catan catanGame;
	private Thread tradeRequestThread;

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
		// This will be added in the GuiController(). So it won't be null in the
		// program.
		guiController = null;
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
		return gameID;

	}

	public boolean addMessage(String message, boolean speler) {
		catanGame.getMessages().add(message);
		if (speler == true) {
			System.out.println("speler");
			return playerMessage(message);
			
		} else {
			System.out.println("systeem");
			return systemMessage(message);
			// return addMessageToDB(message);
		}
	}

	public boolean playerMessage(String message) {
		int idPlayer = catanGame.getSelfPlayer().getIdPlayer();
		if (mainDA.addMessage(idPlayer, catanGame.getIdGame(), message, true)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean systemMessage(String message) {
		int idPlayer = catanGame.getSelfPlayer().getIdPlayer();
		if (mainDA.addMessage(idPlayer, catanGame.getIdGame(), message, false)) {
			return true;
		} else {
			return false;
		}
	}

/*	public boolean playerMessage(String message) {
		int idPlayer = catanGame.getSelfPlayer().getIdPlayer();
		if (mainDA.addMessage(idPlayer, catanGame.getIdGame(), message, true)) {
			return true;
		} else {
			return false;
		}
	}*/

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
		int rolledValue = catanGame.getDice().getValue();

		if (rolledValue == 7) {
			setRobber();
			takeAwayHalfResources();
		} else {
			giveResources(rolledValue);
		}

		// Edit database with rolled values
		editDiceLastThrown(catanGame.getDice().getSeperateValues());
		mainDA.setThrownDice(1, catanGame.getIdGame());
		catanGame.setRolledDice(true);
		// return catanGame.getDice().getDie();
	}

	private void setRobber() {
		guiController.getBoardPanel().enableTileButtons();
	}

	// Action Listener in the Tile calls this.
	public void stealCardCauseRobber() {
		Tile robberTile = gameBoardControl.getGameBoard().getRobberTile();
		ArrayList<Player> playersAtRobberTile = getPlayersAroundTile(robberTile);
		guiController.createStealDialog(playersAtRobberTile);
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

	/**
	 * Anytime anyone rolls a 7 this method should be called.
	 * 
	 * @since 26 May 2018
	 * @author Jasper Mooren
	 */
	public void takeAwayHalfResources() {
		Hand selfPlayerHand = catanGame.getSelfPlayer().getHand();
		if (selfPlayerHand.getResources().size() > SEVEN_CARD_RULE) {
			// Resources taken is always rounded down, so this method works.
			int amountOfResourcesToTake = selfPlayerHand.getResources().size() / HALF_RESOURCES_TAKEN;
			HashMap<ResourceType, Integer> amountOfResourcesAvailable = selfPlayerHand.getAmountOfResources();
			guiController.OpenTakeAwayResoucesDialog(amountOfResourcesToTake, amountOfResourcesAvailable);
		}
	}

	/**
	 * The actionListener in the dialog that the guiController makes should call
	 * this.
	 * 
	 * @param amountOfResources
	 *            the Resources that are taken away.
	 * @since 25 May 2018
	 * @author Jasper Mooren
	 */
	public void takeAwayResourcesReturn(HashMap<ResourceType, Integer> amountOfResources) {
		for (ResourceType resourceType : ResourceType.values()) {
			if (amountOfResources.get(resourceType) != null) {
				int takeAwayOfResource = amountOfResources.get(resourceType);
				for (int i = 0; i < takeAwayOfResource; i++) {
					Resource resource = catanGame.getSelfPlayer().getHand().takeResource(resourceType);
					catanGame.getBank().getResources().add(resource);
				}
			}
		}
	}

	private void giveResources(int rolledValue) {
		// Get past all tiles in game
		for (Tile tile : catanGame.getGameboard().getTileArr()) {
			// Only tiles with the rolledValue and which don't have a robber give resources.
			if (tile.getChipNumber() == rolledValue && !tile.hasRobber()) {
				// Get all the building locations on that tile
				for (BuildingLocation buildingLocation : tile.getBuildingLocArr()) {

					// Check for villages on that tile.
					Village village = buildingLocation.getVillage();
					if (village != null) {
						givePlayerResourceFromBank(village.getPlayer(), tile.getRsType());
					} else {
						// Check for cities on that tile.
						City city = buildingLocation.getCity();
						if (city != null) {
							// Each city gives 2 resources.
							for (int i = 0; i < 2; i++) {
								givePlayerResourceFromBank(city.getPlayer(), tile.getRsType());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Takes a resource from the bank and gives it to the player.
	 * 
	 * @param player
	 *            the player that gets the resource
	 * @param resourceType
	 *            the resourceType of the resource that the player gets
	 * @since 25 May 2018
	 * @author Jasper Mooren
	 */
	private void givePlayerResourceFromBank(Player player, ResourceType resourceType) {
		Resource resourceToAdd = catanGame.getBank().takeResource(resourceType);
		if (resourceToAdd != null) {
			player.getHand().addResource(resourceToAdd);
		}
	}

	public void setDiceLastThrown(int[] die) {
		catanGame.getDice().setDie(die);
	}

	// public boolean hasRolledDice() {
	// return mainDA.hasThrown(catanGame.getIdGame());
	// }

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
		payResources(Village.cost);

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

		// TODO check if enough resources
		payResources(Street.cost);

		streetLocation.setStreet(street);
		street.setStreetLocation(streetLocation);
		mainDA.updateStreet(street.getIdBuilding(), street.getPlayer().getIdPlayer(),
				streetLocation.getBlStart().getXLoc(), streetLocation.getBlStart().getYLoc(),
				streetLocation.getBlEnd().getXLoc(), streetLocation.getBlEnd().getYLoc());
		return true;
	}

	private void payResources(ResourceType[] cost) {
		ArrayList<Resource> toPay = new ArrayList<>();
		for(ResourceType rsType : cost) {
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
			addMessage("Je hebt niet genoeg " + resourceTypeToGive.name() + " kaarten", false);
			return;
		}

		if (catanGame.getBank().takeResource(resourceTypeToReceive) == null) {
			addMessage("De bank heeft niet genoeg " + resourceTypeToReceive.name() + " kaarten", false);
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
				+ " kaart met de bank", false);
	}

	// public void createTradeRequest(int stoneGive, int woolGive, int ironGive, int
	// wheatGive, int woodGive,
	// int stoneReceive, int woolReceive, int ironReceive, int wheatReceive, int
	// woodReceive) {
	// mainDA.createTradeRequest(new
	// TradeRequest(getCatanGame().getSelfPlayer().getIdPlayer(), stoneGive,
	// woolGive, ironGive,
	// wheatGive, woodGive, stoneReceive, woolReceive, ironReceive, wheatReceive,
	// woodReceive));
	// }

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
		System.out.println(stoneGive + woolGive + ironGive + wheatGive + woodGive + stoneReceive + woolReceive
				+ ironReceive + wheatReceive + woodReceive);
		for (Player p : catanGame.getPlayers()) {
			if (p.getIdPlayer() != catanGame.getSelfPlayer().getIdPlayer()) {
				mainDA.setShouldRefresh(p.getIdPlayer(), true);
			}
		}

		mainDA.createTradeRequest(new TradeRequest(getCatanGame().getSelfPlayer().getIdPlayer(), stoneGive, woolGive,
				ironGive, wheatGive, woodGive, stoneReceive, woolReceive, ironReceive, wheatReceive, woodReceive, 3));
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
				TradeRequest tr = mainDA.getSingleTradeRequest(catanGame.getIdGame(), p.getIdPlayer());
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

	}

	public void declineTradeRequest(TradeRequest oldtR) {

		TradeRequest tR = new TradeRequest(getCatanGame().getSelfPlayer().getIdPlayer(), oldtR.getW_brick(),
				oldtR.getW_wool(), oldtR.getW_iron(), oldtR.getW_wheat(), oldtR.getW_wood(), oldtR.getG_brick(),
				oldtR.getG_wool(), oldtR.getG_iron(), oldtR.getG_wheat(), oldtR.getG_wood(), 0);

		mainDA.createTradeRequest(tR);

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
		
		System.out.println(wBrick);//0
		System.out.println(wWool);//1
		System.out.println(wIron);//0
		System.out.println(wWheat);//2
		System.out.println(wWood);//0
		
		System.out.println(gBrick);//0
		System.out.println(gWool);//0
		System.out.println(gIron);//1
		System.out.println(gWheat);//0
		System.out.println(gWood);//0

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
		catanGame.setTradeRequests(null);

		// swap resources in code
		// remove traderequests in db
		// remove traderequests in catanGame
	}

	public void playFirstRound() {
		catanGame.setRolledDice(true);
		guiController.getBoardPanel().enableBuildingLocButtons(true);

		// uitdager plaatst eerst een dorp en een aanliggende straat (met
		// afstandsregel?)
		// de rest doet dit ook

		// 2de ronde: plaats nog een dorp en een aanliggende straat met afstandsregel
		// Direct na het bouwen krijg je van het 2de dorp resources
	}

	public void playRound() {
		// normale rondes
	}

	public void endTurn() {
		catanGame.endTurn();
		if (catanGame.getSelfPlayer().getFollownr() == 4) {
			for (Player p : catanGame.getPlayers()) {
				if (p.getFollownr() == 1) {
					mainDA.setTurn(p.getIdPlayer(), catanGame.getIdGame());
					catanGame.setTurn(p.getIdPlayer());
					addMessage(p.getUsername() + " is nu aan de Beurt.");
					mainDA.setThrownDice(0, catanGame.getIdGame());
					catanGame.setRolledDice(false);
					mainDA.setShouldRefresh(p.getIdPlayer(), true);
					break;
				}
			}
		} else {
			for (Player p : catanGame.getPlayers()) {
				if (p.getFollownr() == catanGame.getSelfPlayer().getFollownr() + 1) {
					mainDA.setTurn(p.getIdPlayer(), catanGame.getIdGame());
					catanGame.setTurn(p.getIdPlayer());
					addMessage(p.getUsername() + " is nu aan de Beurt.");
					mainDA.setThrownDice(0, catanGame.getIdGame());
					catanGame.setRolledDice(false);
					mainDA.setShouldRefresh(p.getIdPlayer(), true);
					break;
				}
			}
		}
	}

	public void doTurn() {
		if (!catanGame.hasRolledDice()) {
			guiController.enableDice();
		}
		guiController.enablePlayerActionPanel();
	}
}
