package controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import dbaccess.MainDA;
import model.Account;
import model.Catan;
import model.Gameboard;
import model.Player;
import model.Tile;

public class MainControl {

	private GameControl gameControl;
	private MainDA mainDA;
	private Account account;
	private GuiController guiController;
	private ArrayList<Catan> catanGames;
	private Timer timer;

	public MainControl() {
		mainDA = new MainDA();
		gameControl = new GameControl(mainDA);
		guiController = new GuiController(this, gameControl);
		timer = new Timer();
		
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
// <<<<<<< FixSquad2
	
// 	public void joinGame(Catan game) {
// 		gameControl.setCatan(game);
		
// 		gameControl.getCatanGame().getDice().setDie(mainDA.getLastThrows(gameControl.getCatanGame().getIdGame()));
// 		gameControl.getCatanGame().setMessages(mainDA.getMessages(gameControl.getCatanGame().getIdGame()));
// 		gameControl.updateBoard();
// 		gameControl.getCatanGame().getGameboard().setRobber(mainDA.getRobberLocation(gameControl.getCatanGame().getIdGame()));
		
// 		guiController.setGameBoard(gameControl.getCatanGame().getGameboard());
// 		guiController.setIngameGuiPanel();
// 		timer.schedule(new TimerTask() {
// 			@Override
// 			public void run() {
// 				updateRefreshDice();
// 				updateRefreshRobber();
// 				updateRefreshMessages();
// 				updateRefreshBoard();
				
// 			}
// 		}, 2000);
// 	}
	
// =======

// 	public void createNewGame(ArrayList<String> playerUsernames) {
// 		Catan catanGame;
// 		int gameID = gameControl.createGameInDB(false);
// 		ArrayList<Player> players = new ArrayList<Player>();
// 		for (String s : playerUsernames) {
// 			Player player = gameControl.createNewPlayer(gameID, s);
// 			players.add(player);
// 			gameControl.addPlayerToDB(gameID, player);
// 		}

// 		// createPlayerPiecesInDB(players);
// 		catanGame = new Catan(players, getSelfPlayer(players), players.get(0).getIdPlayer());
// 		mainDA.setThrownDice(0, gameID);
// 		Gameboard gameBoard = gameControl.createBoard(players);
// 		catanGame.fillCatan(gameBoard);
// 		mainDA.changeRobberLocation(gameID, 10);
// 		gameControl.setCatan(catanGame);
// //		guiController.setGameBoard(gameBoard);

// 	}

// 	public void joinGame(Catan game) {
// 		gameControl.setCatan(game);
// //		guiController.setGameBoard(gameControl.getGameboard());
// 		guiController.setIngameGuiPanel();
// 	}

// >>>>>>> development
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
	
	public void updateRefreshRobber() {
		gameControl.getCatanGame().getGameboard().setRobber(mainDA.getRobberLocation(gameControl.getCatanGame().getIdGame()));
		guiController.refreshRobber();
//		for (Tile t : gameControl.getCatanGame().getGameboard().getTileArr()) {
//		if (t.getIdTile() == gameControl.getCatanGame().getGameboard().getRobberIDTile()) {
//			t.setRobber(true);
//
//		} else {
//			t.setRobber(false);
//		}
//	}
//		return idTile;
	}
	
	public void updateRefreshMessages() {
		ArrayList<String> messageList = new ArrayList<String>();
		messageList = mainDA.getMessages(gameControl.getCatanGame().getIdGame());
		gameControl.getCatanGame().setMessages(messageList);
		guiController.refreshChat();
	}
	
	private void updateRefreshBoard() {
		gameControl.updateBoard();
		guiController.refreshBoard();
	}

	private void updateRefreshDice() {
		gameControl.getCatanGame().getDice().setDie(mainDA.getLastThrows(gameControl.getCatanGame().getIdGame()));
		guiController.refreshDice();
	}
	
	public void logOut() {
		this.account = null;
	}
}