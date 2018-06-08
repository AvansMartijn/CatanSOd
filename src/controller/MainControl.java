package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.Account;
import model.Catan;
import model.Gameboard;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import model.ResourceType;
import model.Tile;
import model.TradeRequest;
import view.Frame;

public class MainControl {

	private GameControl gameControl;
	private MainDA mainDA;
	private Account account;
	private GuiController guiController;
	private ArrayList<Catan> catanGames;
	private boolean ingame;
	private Thread ingameTimerThread;

	public MainControl() {
		mainDA = new MainDA();
		gameControl = new GameControl(mainDA);
		guiController = new GuiController(this, gameControl);
		ingame = false;
	}

	public boolean loginAccount(String username, String password) {
		if (mainDA.login(username, password)) {
			account = new Account(username);
			Frame frame = guiController.getFrame();
			frame.setTitle(username + " - " + frame.getTitle());
			return true;
		} else {
			return false;
		}

	}

	public Player getSelfPlayer(ArrayList<Player> players) {
		Player selfPlayer = null;
		for (Player p : players) {
			if (p.getUsername().toLowerCase().equals(account.getUsername().toLowerCase())) {
				selfPlayer = p;
				break;
			}
		}
		return selfPlayer;
	}

	public void loadProfile(boolean finished) {
		ArrayList<Integer> gameIDsOfUser = mainDA.getGameIDsFromPlayer(account.getUsername(), finished);
		catanGames = new ArrayList<Catan>();
		for (Integer i : gameIDsOfUser) {
			ArrayList<Player> players = getPlayers(i.intValue());

			Player selfPlayer = getSelfPlayer(players);

			catanGames.add(new Catan(players, selfPlayer, mainDA.getTurn(i.intValue())));

		}
		guiController.setGameList(catanGames);
	}

	public void setMainMenu() {
		guiController.setMainMenu(account.getUsername());
	}

	public void joinGame(Catan game) {
		gameControl.setCatan(game);
		gameControl.getCatanGame().getDice().setDie(mainDA.getLastThrows(gameControl.getCatanGame().getIdGame()));
		gameControl.updateBoard();
		gameControl.getCatanGame().getGameboard()
				.setRobber(mainDA.getRobberLocation(gameControl.getCatanGame().getIdGame()));
		updatePlayers();
		updateMessages();
		guiController.setIngameGuiPanel();
		updateRefreshTurn();
		updateRefreshTradeRequest();
		updateRefreshArmyAndTradeRoute();
		if (!game.getSelfPlayer().getPlayStatus().equals(PlayStatus.UITGESPEELD)) {
			ingame = true;
			ingameTimerThread = new Thread(new Runnable() {

				@Override
				public void run() {

					while (ingame) {
						updateRefreshMessages();
						try {
							boolean result = mainDA
									.getShouldRefresh(gameControl.getCatanGame().getSelfPlayer().getIdPlayer());
							if (result) {
								updateRefreshTurn();
								updateRefreshDice();
								updateRefreshBoard();
								updateRefreshRobber();
								updateRefreshPlayers();
								updateRefreshTradeRequest();
								updateRefreshArmyAndTradeRoute();
							}
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
							}
						} catch (Exception e) {
						}
					}
				}
			});
			ingameTimerThread.start();
		} else {
			guiController.disableAllDevelopmentCards();
			guiController.disableDice();
			guiController.disablePlayerActionPanel();
			updateRefreshMessages();
		}
	}

	private void updateRefreshArmyAndTradeRoute() {
		updateArmyAndTradeRoute();
		guiController.refreshPlayerResources();

	}

	private void updateArmyAndTradeRoute() {
		int idPlayerLongestRoute = mainDA.getPlayerWithLongestTradeRoute(gameControl.getCatanGame().getIdGame());
		int idPlayerLargestArmy = mainDA.getPlayerWithLargestArmy(gameControl.getCatanGame().getIdGame());

		for (Player p : gameControl.getCatanGame().getPlayers()) {
			if (p.getIdPlayer() == idPlayerLongestRoute) {
				p.setHasLongestRoad(true);
			} else {
				p.setHasLongestRoad(false);
			}
			if (p.getIdPlayer() == idPlayerLargestArmy) {
				p.setHasLargestArmy(true);
			} else {
				p.setHasLargestArmy(false);
			}
		}
	}


	public void createNewGame(ArrayList<String> playerUsernames, boolean randomBoard) {
		Catan catanGame;
		int gameID = gameControl.createGameInDB(false);
		ArrayList<Player> players = new ArrayList<Player>();
		for (String s : playerUsernames) {
			Player player = createNewPlayer(gameID, s);
			players.add(player);
			addPlayerToDB(gameID, player);
		}

		guiController.setWaitingRoom(players);
		Gameboard gameBoard = gameControl.createBoardAndAddToDB(players, randomBoard);
		for (Tile t : gameBoard.getTileArr()) {
			if (t.getRsType().equals(ResourceType.WOESTIJN)) {
				mainDA.changeRobberLocation(gameID, t.getIdTile());
			}
		}
		createDevelopmentCardsInDB(gameID);
		createResourceCardsInDB(gameID);
		createPlayerPiecesInDB(players);

		catanGame = new Catan(players, getSelfPlayer(players), players.get(0).getIdPlayer());
		mainDA.setThrownDice(0, gameID);
		mainDA.setTurn(players.get(0).getIdPlayer(), gameID);
		catanGame.fillCatan(gameBoard);
		gameControl.setCatan(catanGame);

	}

	public Player createNewPlayer(int gameID, String username) {
		int lastPlayerNumber = mainDA.getLastPlayerFollowNumber(gameID);
		String playerColor = null;
		int followNR = -1;
		String playStatus = null;

		if (lastPlayerNumber == -1) {
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

		Player player = new Player(mainDA.getLastUsedPlayerID() + 1, gameID, username, PlayerColor.valueOf(playerColor),
				followNR, PlayStatus.valueOf(playStatus));
		return player;
	}

	public void loadInvites() {
		ArrayList<Integer> gameIDsOfUser = mainDA.getInvitesFromPlayer(account.getUsername());
		ArrayList<Catan> invitedGames = new ArrayList<Catan>();
		for (Integer i : gameIDsOfUser) {
			ArrayList<Player> players = getPlayers(i.intValue());

			Player selfPlayer = getSelfPlayer(players);
			invitedGames.add(new Catan(players, selfPlayer, mainDA.getTurn(i.intValue())));
		}
		guiController.setInvitePanel(invitedGames);
	}

	public void acceptInvite(Catan gameToAccept) {
		ArrayList<Player> players = getPlayers(gameToAccept.getIdGame());
		int playerId = getSelfPlayer(players).getIdPlayer();
		mainDA.acceptInvite(playerId);
	}

	public void declineInvite(Catan gameToDecline) {
		ArrayList<Player> players = getPlayers(gameToDecline.getIdGame());
		int playerId = getSelfPlayer(players).getIdPlayer();
		mainDA.declineInvite(playerId);
	}

	public void switchInvites(ArrayList<Player> playersToAdd, ArrayList<Player> playersToRemove, int gameId) {
		for (int i = 0; i < playersToAdd.size(); i++) {
			mainDA.switchPlayer(playersToRemove.get(i).getUsername(), playersToAdd.get(i).getUsername(), gameId);
		}
	}

	public void addPlayerToDB(int idGame, Player player) {
		mainDA.createPlayer(player.getIdPlayer(), idGame, player.getUsername(), player.getColor().toString(),
				player.getFollownr(), player.getPlayStatus().toString());
	}

	private void createDevelopmentCardsInDB(int gameID) {
		String idCard = "o";
		for (int i = 1; i <= 25; i++) {
			if (i <= 5) {
				mainDA.addDevelopmentCard(idCard + "0" + i + "g", gameID);
			}
			if (i > 5 && i <= 7) {
				mainDA.addDevelopmentCard(idCard + "0" + i + "s", gameID);
			}
			if (i > 7 && i <= 9) {
				mainDA.addDevelopmentCard(idCard + "0" + i + "m", gameID);
			}
			if (i > 9 && i <= 11) {
				mainDA.addDevelopmentCard(idCard + i + "u", gameID);
			}
			if (i > 12 && i <= 25) {
				mainDA.addDevelopmentCard(idCard + i + "r", gameID);
			}
		}

	}

	private void createResourceCardsInDB(int gameID) {
		String prefix = "0";
		for (int i = 1; i <= 19; i++) {
			if (i > 9) {
				prefix = "";
			}
			mainDA.addResourceCard("b" + prefix + i, gameID);
			mainDA.addResourceCard("e" + prefix + i, gameID);
			mainDA.addResourceCard("g" + prefix + i, gameID);
			mainDA.addResourceCard("h" + prefix + i, gameID);
			mainDA.addResourceCard("w" + prefix + i, gameID);
		}
	}

	public void createPlayerPiecesInDB(ArrayList<Player> players) {
		
		String idPiece;
		for (Player p : players) {
			for (int i = 1; i <= 5; i++) {
				idPiece = "d0" + i;
				mainDA.addPlayerPiece(idPiece, p.getIdPlayer());
			}
			for (int i = 1; i <= 4; i++) {
				idPiece = "c0" + i;
				mainDA.addPlayerPiece(idPiece, p.getIdPlayer());
			}
			for (int i = 1; i <= 15; i++) {
				if (i < 10) {
					idPiece = "r0" + i;
				} else {
					idPiece = "r" + i;
				}
				mainDA.addPlayerPiece(idPiece, p.getIdPlayer());
			}
		}
	}

	private ArrayList<Player> getPlayers(int idGame) {
		return mainDA.getPlayersFromGame(idGame);
	}

	public boolean createAccount(String username, String password) {
		if (mainDA.accountNameExists(username) || username.length() <= 3 || password.length() <= 3) {
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
		try {
			gameControl.getCatanGame().getGameboard()
					.setRobber(mainDA.getRobberLocation(gameControl.getCatanGame().getIdGame()));
			guiController.refreshRobber();
		} catch (Exception e) {
		}
	}

	public void updateRefreshTurn() {
		try {
			int turn = mainDA.getTurn(gameControl.getCatanGame().getIdGame());
			gameControl.getCatanGame().setTurn(turn);
			if (turn == gameControl.getCatanGame().getSelfPlayer().getIdPlayer()) {
				if (mainDA.getFirstRound(gameControl.getCatanGame().getIdGame()) == 1) {
					if (gameControl.isFirstRoundActive() == false) {
						gameControl.setFirstRoundActive(true);
						gameControl.getCatanGame().setFirstRound(true);

						gameControl.playFirstRound();
					}
				} else {
					gameControl.getCatanGame().setFirstRound(false);
					gameControl.doTurn();
				}
			}
		} catch (Exception e) {
		}
	}

	public void updateRefreshMessages() {
		try {
			updateMessages();
			guiController.refreshChat();
		} catch (Exception e) {
		}
	}

	private void updateMessages() {
		try {
			ArrayList<String> messageList = new ArrayList<String>();
			messageList = mainDA.getMessages(gameControl.getCatanGame().getIdGame());
			gameControl.getCatanGame().setMessages(messageList);
		} catch (Exception e) {
		}
	}

	private void updateRefreshBoard() {
		try {
			gameControl.updateBoard();
			guiController.refreshBoard();
		} catch (Exception e) {
		}
	}

	private void updateRefreshTradeRequest() {
		try {
			TradeRequest tr = gameControl.updateTradeRequests();
			if (tr != null && tr.getIdPlayer() != gameControl.getCatanGame().getSelfPlayer().getIdPlayer()) {
				if (mainDA.getSingleTradeRequest(gameControl.getCatanGame().getSelfPlayer().getIdPlayer()) == null) {
					if(!guiController.isTradeActive()) {
						guiController.setTradeActive(true);
					gameControl.getCatanGame().addTradeRequest(tr);
					
					guiController.showTradeReceiveDialog(tr);
					}
				}
			}
		} catch (Exception e) {
		}
	}

	private void updateRefreshDice() {
		try {
			gameControl.getCatanGame().getDice().setDie(mainDA.getLastThrows(gameControl.getCatanGame().getIdGame()));
			gameControl.getCatanGame().setRolledDice(mainDA.hasThrown(gameControl.getCatanGame().getIdGame()));
			guiController.refreshDice();
		} catch (Exception e) {
		}
	}

	private void updateRefreshPlayers() {
		try {
			updatePlayers();
			guiController.refreshPlayerResources();

		} catch (Exception e) {
		}
	}

	private void updatePlayers() {
		try {
			for (Player p : gameControl.getCatanGame().getPlayers()) {
				p.getHand()
						.setResources(mainDA.updateResources(gameControl.getCatanGame().getIdGame(), p.getIdPlayer()));
				p.getHand().setDevelopmentCards(
						mainDA.updateDevelopmentCards(gameControl.getCatanGame().getIdGame(), p.getIdPlayer()));
			}
			gameControl.getCatanGame().getBank()
					.setResources(mainDA.updateResources(gameControl.getCatanGame().getIdGame(), 0));
			gameControl.getCatanGame().getBank()
					.setDevelopmentCards(mainDA.updateDevelopmentCards(gameControl.getCatanGame().getIdGame(), 0));
			gameControl.checkForWinner();

		} catch (Exception e) {
		}
	}

	public void logOut() {
		this.account = null;
	}

	public boolean shouldRefresh() {
		return mainDA.getShouldRefresh(gameControl.getCatanGame().getSelfPlayer().getIdPlayer());
	}

	public String getAcccountUsername() {
		return account.getUsername();
	}

	public void stopIngameTimer() {
		ingame = false;
	}

	public void abortGame() {
		int[] playerids = new int[4];

		for (int i = 0; i < 4; i++) {
			playerids[i] = gameControl.getCatanGame().getPlayers().get(i).getIdPlayer();
		}
		mainDA.abortGame(playerids);
	}

	public Catan getGameFromId(int gameId) {
		ArrayList<Player> playerArray = mainDA.getPlayersFromGame(gameId);
		return new Catan(playerArray, getSelfPlayer(playerArray), gameId);
	}
}