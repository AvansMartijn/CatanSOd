package controller;

import java.util.ArrayList;

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
import model.Street;
import model.StreetLocation;
import model.Village;

public class GameControl {
	private GameBoardControl gameBoardControl;
//	private Gameboard gameboard;
	private MainDA mainDA;
	private String username;
//	private ArrayList<Player> gamePlayers;
//	private int idGame;
	private Catan catanGame;
//	private int playerID;
//	private int account;
//	private Player player;
//	private Bank bank;
//	private Dice dice;

	public GameControl(MainDA mainDA) {
		this.mainDA = mainDA;
		// -----TEST------

		// ----END TEST -----
	}

	public void testMethod() {
		// createGame(false);
//		idGame = 770;
//		playerID = mainDA.getPlayerID(username, idGame);
		joinGame();
	}

	/**
	 * Create a game record in the DB AND sets idGame
	 */
	public void createGame(boolean randomBoard) {
		catanGame.setIdGame(mainDA.createGame(randomBoard));
		createNewPlayer();
		// addPlayerToDB(player);
		gameBoardControl = new GameBoardControl(mainDA, catanGame.getIdGame());
//		gameboard = gameBoardControl.createBoard();

	}

	/**
	 * Add a player
	 */
	public void joinGame() {
//		loadPlayers();
		
//		printPlayerVillages();
	}

	private void createNewPlayer() {
		int lastPlayerNumber = mainDA.getLastPlayerFollowNumber(catanGame.getIdGame());
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
		catanGame.setSelfPlayer(new Player(catanGame.getIdGame(), catanGame.getSelfPlayer().getUsername(), PlayerColor.valueOf(playerColor), followNR,
				PlayStatus.valueOf(playStatus)));
	}

//	private void loadPlayers() {
//		gamePlayers = mainDA.getPlayersFromGame(idGame);
//		for (Player p : gamePlayers) {
//			if (p.getUsername().equals(username)) {
//				player = p;
//				return;
//			}
//		}
//	}

	public void addPlayerToDB(Player player) {
		mainDA.createPlayer(catanGame.getIdGame(), player.getUsername(), player.getColor().toString(), player.getFollownr(),
				player.getPlayStatus().toString());
	}

	public ArrayList<String> getMessages() {
		ArrayList<String> messageList = new ArrayList<String>();
		messageList = mainDA.getMessages(catanGame.getIdGame());
		return messageList;
	}

	public boolean addMessage(String message) {
		if (mainDA.addMessage(catanGame.getSelfPlayer().getIdPlayer(), catanGame.getIdGame(), message)) {
			return true;
		} else {
			return false;
		}
	}

	public void changeRobberInDB(int idTile) {
		this.mainDA.changeRobberLocation(catanGame.getIdGame(), idTile);
	}

	public int getRobberIdTile() {
		int idTile = this.mainDA.getRobberLocation(catanGame.getIdGame());
		return idTile;
	}

//	public void setGameID(int gameID) {
//		this.idGame = gameID;
//	}

	public void makeBank() {
		
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

//	public Player getPlayer() {
//		return player;
//	}

	public Gameboard getGameboard() {
		return catanGame.getGameboard();
	}

	public void editDiceLastThrown(int[] die) {
		mainDA.setLastThrow(die[0], die[1], catanGame.getIdGame());
	}

	public int[] getDiceLastThrown() {
		return mainDA.getLastThrows(catanGame.getIdGame());
	}

	public int[] rollDice() {
		catanGame.rollDice();
		return catanGame.getDice().getDie();
	}

	public void setDiceLastThrown(int[] die) {
		catanGame.getDice().setDie(die);
	}

	public boolean buildVillage(BuildingLocation buildingLocation) {
		Village village = catanGame.getSelfPlayer().getAvailableVillage();
		// check if player has a village to build
		System.out.println(catanGame.getSelfPlayer().getAmountAvailableVillages() <= 0);
		if (catanGame.getSelfPlayer().getAmountAvailableVillages() <= 0) {
			return false;
		}
		// check if nothing is build already on that location
		if (buildingLocation.getVillage() != null || buildingLocation.getCity() != null) {
			return false;
		}
		
		//TODO Check if there is not a building neighboring this location
		//TODO check if there are streets connected to this location

		buildingLocation.setVillage(catanGame.getSelfPlayer().getAvailableVillage());
		village.setBuildingLocation(buildingLocation);

		System.out.println(village.getBuildingLocation().getXLoc() + " " + village.getBuildingLocation().getYLoc());
		return true;
	}

	public boolean buildCity(BuildingLocation buildingLocation) {
		City city = catanGame.getSelfPlayer().getAvailableCity();

		if (catanGame.getSelfPlayer().getAmountAvailableCities() <= 0) {
			return false;
		}
		if (buildingLocation.getCity() != null) {
			return false;
		}
		
		//TODO Check if there is not a building neighbouring this location
		//TODO check if there are streets connected to this location
		
		if (buildingLocation.getVillage() != null) {
			if (buildingLocation.getVillage().getPlayer().equals(catanGame.getSelfPlayer())) {
				// upgrade village to city
				Village village = buildingLocation.getVillage();
				buildingLocation.setVillage(null);
				village.setBuildingLocation(null);
				buildingLocation.setCity(city);
				city.setBuildingLocation(buildingLocation);
			}
		} 
		else {
			// place city
			buildingLocation.setCity(city);
			city.setBuildingLocation(buildingLocation);

		}
		return true;

	}

	public boolean buildRoad(StreetLocation streetLocation) {
		Street street = catanGame.getSelfPlayer().getAvailableStreet();
		if (catanGame.getSelfPlayer().getAmountAvailableStreets() <= 0) {
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
		for (Player p : catanGame.getPlayers()) {
			ArrayList<Village> villageFromPlayer = mainDA.getVillageFromPlayer(p.getIdPlayer());
			p.setVillageArr(villageFromPlayer);
			for (Village v : villageFromPlayer) {
				v.setPlayer(p);
				if (v.getBuildingLocation().getXLoc() == 0 || v.getBuildingLocation().getYLoc() == 0) {
					v.setBuildingLocation(null);
				} else {
					for (BuildingLocation b : catanGame.getGameboard().getBuildingLocArr()) {
						if (b.getXLoc() == v.getBuildingLocation().getXLoc() && b.getYLoc() == v.getBuildingLocation().getYLoc()) {
							v.setBuildingLocation(b);
							b.setVillage(v);
						}
					}
				}
			}
		}
	}

	public void setCatan(Catan game) {
		this.catanGame = game;
		gameBoardControl = new GameBoardControl(mainDA, catanGame.getIdGame());
		Gameboard gameboard = gameBoardControl.loadBoard();
		game.fillCatan(gameboard);
		setVillageArrays();
		
		
	}

	public Catan getCatanGame() {
		return catanGame;
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
