package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.Account;
import model.Catan;
import model.Player;
import view.MainMenuGUI;


public class MainControl {

	private GameControl gameControl;
	private MainDA mainDA;
	private Account account;
	private GuiController guiController;

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
		gameControl.setUsername(account.getUsername());
		ArrayList<Integer> gameIDsOfUser = mainDA.getGameIDsFromPlayer(account.getUsername());
		ArrayList<Catan> catanGames = new ArrayList<Catan>();
		
		for(int i = 0; i < gameIDsOfUser.size(); i++) {
			catanGames.add(new Catan(getPlayers(gameIDsOfUser.get(i))));
		}

		guiController.setMainMenu(catanGames, account.getUsername());
		
//		gameControl.testMethod();
//		guiController.setGameBoard(gameControl.getGameboard());
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

	public void logOut() {
		this.account = null;
	}
}