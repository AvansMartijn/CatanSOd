package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.Account;
import model.Catan;
import model.Gameboard;
import model.Player;

public class MainControl {

	private GameControl gameControl;
	private MainDA mainDA;
	private Account account;
	private GuiController guiController;
	private ArrayList<Catan> catanGames;

	public MainControl() {
		mainDA = new MainDA();
		gameControl = new GameControl(mainDA);
		guiController = new GuiController(this, gameControl);
	}

	public boolean loginAccount(String username, String password) {
		if (mainDA.login(username, password)) {
			account = new Account(username);
			return true;
		} else {
			return false;
		}

	}

	public Player getSelfPlayer(ArrayList<Player> players) {
		Player selfPlayer = null;
		for (Player p : players) {
			if (p.getUsername().equals(account.getUsername())) {
				selfPlayer = p;
				break;
			}
		}
		return selfPlayer;
	}

	public void loadProfile() {
		ArrayList<Integer> gameIDsOfUser = mainDA.getGameIDsFromPlayer(account.getUsername());
		catanGames = new ArrayList<Catan>();
		for(Integer i: gameIDsOfUser) {
			ArrayList<Player> players = getPlayers(i.intValue());
			
			Player selfPlayer = getSelfPlayer(players);
			
			catanGames.add(new Catan(players, selfPlayer, mainDA.getTurn(i.intValue())));
			
		}
//		for (int i = 0; i < gameIDsOfUser.size(); i++) {
//		}

		guiController.setMainMenu(catanGames, account.getUsername());
	}

	public void createNewGame(ArrayList<String> playerUsernames) {
		Catan catanGame;
		int gameID = gameControl.createGameInDB(false);
		ArrayList<Player> players = new ArrayList<Player>();
		for (String s : playerUsernames) {
			Player player = gameControl.createNewPlayer(gameID, s);
			players.add(player);
			gameControl.addPlayerToDB(gameID, player);
		}

		// createPlayerPiecesInDB(players);
		catanGame = new Catan(players, getSelfPlayer(players), players.get(0).getIdPlayer());
		mainDA.setThrownDice(0, gameID);
		Gameboard gameBoard = gameControl.createBoard(players);
		catanGame.fillCatan(gameBoard);
		mainDA.changeRobberLocation(gameID, 10);
		gameControl.setCatan(catanGame);
//		guiController.setGameBoard(gameBoard);

	}

	public void joinGame(Catan game) {
		gameControl.setCatan(game);
//		guiController.setGameBoard(gameControl.getGameboard());
		guiController.setIngameGuiPanel();
	}

	private ArrayList<Player> getPlayers(int idGame) {
		return mainDA.getPlayersFromGame(idGame);
	}

	public boolean createAccount(String username, String password) {
		if (mainDA.accountNameExists(username)) {
			return false;
		} else {
			mainDA.createAccount(username, password);
			return true;
		}
	}

	public ArrayList<String> getAllAccounts() {
		return mainDA.getAllAccounts();
	}

	public void logOut() {
		this.account = null;
	}
}