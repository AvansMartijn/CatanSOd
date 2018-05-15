package controller;

import dbaccess.MainDA;
import model.Account;


public class MainControl {

	// private MainMenu mainMenu;
	private GameControl gameControl;
	private MainDA mainDA;
	private Account account;
	private GuiController guiController;

	public MainControl() {
		mainDA = new MainDA();

		gameControl = new GameControl(mainDA);
		guiController = new GuiController(this, gameControl);

		guiController.setInlogPanel();
//		loginAccount("lesley", "hallo");
	}

	public boolean loginAccount(String username, String password) {
		if (mainDA.login(username, password)) {
			account = new Account(mainDA.getPlayers(username), username);
			gameControl.setUsername(account.getUsername());
			gameControl.testMethod();
			guiController.setGameBoard(gameControl.getGameboard());
			return true;
		} else {
			return false;
		}

	}
	
	public boolean createAccount(String username, String password) {
		if (mainDA.accountNameExists(username)) {
			return false;
		} else {
			mainDA.createAccount(username, password);
			return true;
		}
	}
	
	public void testChat(String message) {
		gameControl.addMessage(message);
		gameControl.getMessages();
	}

	public void logOut() {
		this.account = null;
	}

//	public void createGame() {
//		gameControl = new GameControl(mainDA, account.getUsername());
//		gameControl.createGame(false);
//		gameControl.joinGame(account.getUsername());
//	}
//
//	public void joinGame(int idGame) {
//		if (account != null) {
//			gameControl = new GameControl(mainDA, account.getUsername());
//			gameControl.setGameID(idGame);
//			gameControl.joinGame(account.getUsername());
//		} else {
//			System.out.println("No Account logged in");
//		}
//	}
}