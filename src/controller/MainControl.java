package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.Account;
import model.Catan;
import model.Gameboard;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
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
			if (p.getUsername().equals(account.getUsername())) {
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
		// }

	}

	public void setMainMenu() {
		guiController.setMainMenu(account.getUsername());

	}

	public void joinGame(Catan game) {
		gameControl.setCatan(game);
		gameControl.getCatanGame().getDice().setDie(mainDA.getLastThrows(gameControl.getCatanGame().getIdGame()));
		// gameControl.getCatanGame().setMessages(mainDA.getMessages(gameControl.getCatanGame().getIdGame()));
		updateRefreshMessages();
		gameControl.updateBoard();
		gameControl.getCatanGame().getGameboard()
				.setRobber(mainDA.getRobberLocation(gameControl.getCatanGame().getIdGame()));
		for (Player p : gameControl.getCatanGame().getPlayers()) {
			p.getHand().setResources(mainDA.updateResources(gameControl.getCatanGame().getIdGame(), p.getIdPlayer()));
			p.getHand().setDevelopmentCards(
					mainDA.updateDevelopmentCards(gameControl.getCatanGame().getIdGame(), p.getIdPlayer()));
		}

		guiController.setIngameGuiPanel();
		updateRefreshTurn();
		ingame = true;
		ingameTimerThread = new Thread(new Runnable() {

			@Override
			public void run() {

				while (ingame) {
					updateRefreshMessages();
					// System.out.println("ingame: "+ ingame);
					try {
						boolean result = mainDA
								.getShouldRefresh(gameControl.getCatanGame().getSelfPlayer().getIdPlayer());
						if (result) {
							// mainDA.setShouldRefresh(gameControl.getCatanGame().getSelfPlayer().getIdPlayer(),
							// false);
							updateRefreshTurn();
							updateRefreshDice();
							updateRefreshBoard();
							updateRefreshRobber();
							updateRefreshPlayers();
							updateRefreshTradeRequest();

							System.out.println("refresh");
						}
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		});
		ingameTimerThread.start();

	}

	// private void repaintAndValidate() {
	// guiController.repaintAndValidate();
	// }

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
		mainDA.changeRobberLocation(gameID, 10);
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
			System.out.println(i);
		}
		guiController.setInvitePanel(invitedGames);
	}

	public void acceptInvite(Catan gameToAccept) {
		ArrayList<Player> players = getPlayers(gameToAccept.getIdGame());
		int playerId = getSelfPlayer(players).getIdPlayer();
		mainDA.acceptInvite(playerId);
		loadInvites();
	}

	public void declineInvite(Catan gameToDecline) {
		ArrayList<Player> players = getPlayers(gameToDecline.getIdGame());
		int playerId = getSelfPlayer(players).getIdPlayer();
		mainDA.declineInvite(playerId);
		loadInvites();
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
		// TODO Auto-generated method stub
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
		if (mainDA.accountNameExists(username) || username.length() < 3) {
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
			System.out.println("updaterefreshRobber failed");
		}
	}

	public void updateRefreshTurn() {
		try {
			int turn = mainDA.getTurn(gameControl.getCatanGame().getIdGame());
			gameControl.getCatanGame().setTurn(turn);
			System.out.println(
					"idturn" + turn + " idplayer: " + gameControl.getCatanGame().getSelfPlayer().getIdPlayer());
			if (turn == gameControl.getCatanGame().getSelfPlayer().getIdPlayer()) {
				if (mainDA.getFirstRound(gameControl.getCatanGame().getIdGame()) == 1) {
					if (gameControl.isFirstRoundActive() == false) {
						gameControl.setFirstRoundActive(true);
						System.out.println("playFirstsRound");
						gameControl.getCatanGame().setFirstRound(true);

						gameControl.playFirstRound();
					}
				} else {
					gameControl.getCatanGame().setFirstRound(false);
					gameControl.doTurn();
					System.out.println("doTurn");

					// guiController.refreshDice();
				}
			}
		} catch (Exception e) {
			System.out.println("updateRefreshTurn failed");
		}
	}

	public void updateRefreshMessages() {
		try {
			ArrayList<String> messageList = new ArrayList<String>();
			messageList = mainDA.getMessages(gameControl.getCatanGame().getIdGame());
			gameControl.getCatanGame().setMessages(messageList);

			guiController.refreshChat();
		} catch (Exception e) {
			System.out.println("updateRefreshmessages failed");
		}
	}

	private void updateRefreshBoard() {
		try {
			gameControl.updateBoard();
			guiController.refreshBoard();
		} catch (Exception e) {
			System.out.println("updateRefreshBoard failed");
		}
	}

	private void updateRefreshTradeRequest() {
		try {
//			if (mainDA.getAmountOfOpenRequests(gameControl.getCatanGame().getIdGame()) == 1) {
				TradeRequest tr = gameControl.updateTradeRequests();
				if (tr != null && tr.getIdPlayer() != gameControl.getCatanGame().getSelfPlayer().getIdPlayer()) {
					if (mainDA
							.getSingleTradeRequest(gameControl.getCatanGame().getSelfPlayer().getIdPlayer()) == null) {
						gameControl.getCatanGame().addTradeRequest(tr);
						guiController.showTradeReceiveDialog(tr);
					}
				}
//			}
		} catch (Exception e) {
			System.out.println("updateRefreshTradeRequest failed");
		}
	}

	private void updateRefreshDice() {
		try {
			gameControl.getCatanGame().getDice().setDie(mainDA.getLastThrows(gameControl.getCatanGame().getIdGame()));
			gameControl.getCatanGame().setRolledDice(mainDA.hasThrown(gameControl.getCatanGame().getIdGame()));
			guiController.refreshDice();
		} catch (Exception e) {
			System.out.println("updaterefresh dice failed");
		}
	}

	private void updateRefreshPlayers() {
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
			guiController.refreshPlayerResources();
		} catch (Exception e) {
			System.out.println("updateRefreshPlayers failed");
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
}