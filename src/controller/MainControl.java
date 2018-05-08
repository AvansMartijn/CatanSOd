package controller;

import dbaccess.MainDA;
import model.Account;


public class MainControl {

	// private MainMenu mainMenu;
	private GameControl gameControl;
	private MainDA mainDA;
	private Account account;

	public MainControl() {
		// mainMenu = new MainMenu();
		mainDA = new MainDA();
		
		gameControl = new GameControl(mainDA);
	}

	public void loginAccount(String username, String password) {
		if (mainDA.login(username, password)) {
			account = new Account(mainDA.getPlayers(username), username);
		} else {
			System.out.println("Failed to login");
		}

	}
	
	public void createAccount(String username, String password) {
		if (mainDA.accountNameExists(username)) {
			System.out.println("Username already exists");
		} else {
			mainDA.createAccount(username, password);
		}
	}
	
	public void testChat(String message) {
		gameControl.addMessage(message);
		gameControl.getMessages();
		gameControl.testprintMessages();
	}

	public void logOut() {
		this.account = null;
	}

	public void createGame() {
		gameControl = new GameControl(mainDA);
		gameControl.createGame(false);
		gameControl.joinGame(account.getUsername());
	}

	public void joinGame(int idGame) {
		if (account != null) {
			gameControl = new GameControl(mainDA);
			gameControl.setGameID(idGame);
			gameControl.joinGame(account.getUsername());
		} else {
			System.out.println("No Account logged in");
		}
	}
}