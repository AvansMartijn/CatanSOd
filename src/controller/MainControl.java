package controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import dbaccess.MainDA;
import model.Account;
import model.Catan;
import model.Player;


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
	
	
	public void loadProfile() {
		ArrayList<Integer> gameIDsOfUser = mainDA.getGameIDsFromPlayer(account.getUsername());
		catanGames = new ArrayList<Catan>();
		for(int i = 0; i < gameIDsOfUser.size(); i++) {
			ArrayList<Player> players = getPlayers(gameIDsOfUser.get(i));
			Player selfPlayer = null;
			for(Player p: players) {				
				if(p.getUsername().equals(account.getUsername())) {
					selfPlayer = p;
					break;
				}
			}
			catanGames.add(new Catan(players, selfPlayer));
		}

		guiController.setMainMenu(catanGames, account.getUsername());
	}
	
	public void joinGame(Catan game) {
		gameControl.setCatan(game);
		gameControl.getCatanGame().getDice().setDie(mainDA.getLastThrows(gameControl.getCatanGame().getIdGame()));
		gameControl.getCatanGame().setMessages(mainDA.getMessages(gameControl.getCatanGame().getIdGame()));
		guiController.setGameBoard(gameControl.getCatanGame().getGameboard());
		guiController.setIngameGuiPanel();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				updateRefreshDice();
				updateRefreshMessages();
				
			}
		}, 2000);
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
	
	public int getRobberIdTile(int idGame) {
		int idTile = this.mainDA.getRobberLocation(idGame);
		return idTile;
	}
	
	public void updateRefreshMessages() {
		ArrayList<String> messageList = new ArrayList<String>();
		messageList = mainDA.getMessages(gameControl.getCatanGame().getIdGame());
		gameControl.getCatanGame().setMessages(messageList);
		guiController.refreshChat();
	}

	private void updateRefreshDice() {
		gameControl.getCatanGame().getDice().setDie(mainDA.getLastThrows(gameControl.getCatanGame().getIdGame()));
		guiController.refreshDice();
	}
	
	public void logOut() {
		this.account = null;
	}
}