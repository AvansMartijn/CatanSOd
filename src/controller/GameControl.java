package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.Bank;
import model.BuildingLocation;
import model.City;
import model.Dice;
import model.Gameboard;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import model.Street;
import model.StreetLocation;
import model.Village;

public class GameControl {
	private GameBoardControl gameBoardControl;
	private Gameboard gameboard;
	private MainDA mainDA;
	private ArrayList<Player> gamePlayers;
	private int idGame;
	private int playerID;
	private int account;
	private String username;
	private Player player;
	private Bank bank;
	private Dice dice;

	public GameControl(MainDA mainDA) {
		dice = new Dice();
		this.mainDA = mainDA;
		// -----TEST------

		// ----END TEST -----
	}

	public void testMethod() {
		// createGame(false);
		idGame = 770;
		playerID = mainDA.getPlayerID(username, idGame);
		joinGame();
	}

	/**
	 * Create a game record in the DB AND sets idGame
	 */
	public void createGame(boolean randomBoard) {
		idGame = mainDA.createGame(randomBoard);
		createNewPlayer();
		// addPlayerToDB(player);
		gameBoardControl = new GameBoardControl(mainDA, idGame);
		gameboard = gameBoardControl.createBoard();

	}

	/**
	 * Add a player
	 */
	public void joinGame() {
		loadPlayers();
		gameBoardControl = new GameBoardControl(mainDA, idGame);
		gameboard = gameBoardControl.loadBoard();
		setVillageArrays();
//		printPlayerVillages();
	}

	private void createNewPlayer() {
		int lastPlayerNumber = mainDA.getLastPlayerFollowNumber(idGame);
		String playerColor = null;
		int followNR = -1;
		String playStatus = null;

		if (lastPlayerNumber == -1) {
			System.out.println("Color error");
		} else {
			switch (lastPlayerNumber) {
			case 0:
				playerColor = "ROOD";
				followNR = 1;
				playStatus = "UITDAGER";
				break;
			case 1:
				playerColor = "WIT";
				followNR = 2;
				playStatus = "UITGEDAAGDE";
				break;
			case 2:
				playerColor = "BLAUW";
				followNR = 3;
				playStatus = "UITGEDAAGDE";
				break;
			case 3:
				playerColor = "ORANJE";
				followNR = 4;
				playStatus = "UITGEDAAGDE";
				break;
			}
		}
		player = new Player(idGame, username, PlayerColor.valueOf(playerColor), followNR,
				PlayStatus.valueOf(playStatus));
	}

	private void loadPlayers() {
		gamePlayers = mainDA.getPlayersFromGame(idGame);
		for (Player p : gamePlayers) {
			if (p.getUsername().equals(username)) {
				player = p;
				return;
			}
		}
	}

	public void addPlayerToDB(Player player) {
		mainDA.createPlayer(idGame, player.getUsername(), player.getColor().toString(), player.getFollownr(),
				player.getPlayStatus().toString());
	}

	public ArrayList<String> getMessages() {
		ArrayList<String> messageList = new ArrayList<String>();
		messageList = mainDA.getMessages(idGame);
		return messageList;
	}

	public boolean addMessage(String message) {
		if (mainDA.addMessage(playerID, idGame, message)) {
			return true;
		} else {
			return false;
		}
	}

	public void changeRobberInDB(int idTile) {
		this.mainDA.changeRobberLocation(idGame, idTile);
	}

	public int getRobberIdTile() {
		int idTile = this.mainDA.getRobberLocation(idGame);
		return idTile;
	}

	public void setGameID(int gameID) {
		this.idGame = gameID;
	}

	public void makeBank() {
		
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Player getPlayer() {
		return player;
	}

	public Gameboard getGameboard() {
		return gameboard;
	}

	public void editDiceLastThrown(int[] die) {
		mainDA.setLastThrow(die[0], die[1], idGame);
	}

	public int[] getDiceLastThrown() {
		return mainDA.getLastThrows(idGame);
	}

	public int[] rollDice() {
		dice.roll();
		return dice.getDie();
	}

	public void setDiceLastThrown(int[] die) {
		dice.setDie(die);
	}

	public boolean buildVillage(BuildingLocation buildingLocation) {
		Village village = player.getAvailableVillage();
		// check if player has a village to build
		System.out.println(player.getAmountAvailableVillages() <= 0);
		if (player.getAmountAvailableVillages() <= 0) {
			return false;
		}
		// check if nothing is build already on that location
		if (buildingLocation.getVillage() != null || buildingLocation.getCity() != null) {
			return false;
		}
		
		//TODO Check if there is not a building neighbouring this location
		//TODO check if there are streets connected to this location

		buildingLocation.setVillage(player.getAvailableVillage());
		village.setBuildingLocation(buildingLocation);

		System.out.println(village.getBuildingLocation().getXLoc() + " " + village.getBuildingLocation().getYLoc());
		return true;
	}

	public boolean buildCity(BuildingLocation buildingLocation) {
		City city = player.getAvailableCity();

		if (player.getAmountAvailableCities() <= 0) {
			return false;
		}
		if (buildingLocation.getCity() != null) {
			return false;
		}
		
		//TODO Check if there is not a building neighbouring this location
		//TODO check if there are streets connected to this location
		
		if (buildingLocation.getVillage() != null) {
			if (buildingLocation.getVillage().getPlayer().equals(player)) {
				// upgrade village to city
				Village village = buildingLocation.getVillage();
				buildingLocation.setVillage(null);
				village.setBuildingLocation(null);
				buildingLocation.setCity(city);
				city.setBuildingLocation(buildingLocation);
			}
		} else {
			// place city
			buildingLocation.setCity(city);
			city.setBuildingLocation(buildingLocation);

		}
		return true;

	}

	public boolean buildRoad(StreetLocation streetLocation) {
		Street street = player.getAvailableStreet();
		if (player.getAmountAvailableStreets() <= 0) {
			return false;
		}
		if (streetLocation.getStreet() != null) {
			return false;
		}
		
		
		//TODO check if there is a connected building/street to this location
		
		streetLocation.setStreet(street);
		street.setStreetLocation(streetLocation);

		return true;
	}

	public void setVillageArrays() {
		for (Player p : gamePlayers) {
			ArrayList<Village> villageFromPlayer = mainDA.getVillageFromPlayer(p.getIdPlayer());
			p.setVillageArr(villageFromPlayer);
			for (Village v : villageFromPlayer) {
				v.setPlayer(p);
				if (v.getBuildingLocation().getXLoc() == 0 || v.getBuildingLocation().getYLoc() == 0) {
					v.setBuildingLocation(null);
				} else {
					for (BuildingLocation b : gameboard.getBuildingLocArr()) {
						if (b.getXLoc() == v.getBuildingLocation().getXLoc() && b.getYLoc() == v.getBuildingLocation().getYLoc()) {
							v.setBuildingLocation(b);
							b.setVillage(v);
						}
					}
				}
			}
		}
	}
	
//	public void printPlayerVillages() {
//		for (Player p : gamePlayers) {
//			System.out.println(p.getUsername());
//			for(Village v: p.getVillageArr()) {
//				
//				System.out.println(v.getBuildingLocation().getXLoc() + " " + v.getBuildingLocation().getYLoc());
//			}
//		}
//	}

}
