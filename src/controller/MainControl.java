package controller;

import java.util.ArrayList;

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