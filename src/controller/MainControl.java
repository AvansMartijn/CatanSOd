package controller;

import dbaccess.MainDA;
import model.Account;
import model.MainMenu;
import model.Player;

public class MainControl {
	
	private MainMenu mainMenu;
	private GameControl gameControl;
	private MainDA mainDA;
	private Account account;
	
	public MainControl() {
		mainMenu = new MainMenu();
		mainDA = new MainDA();
		
		//TODO add stuff to a MainControl constructor
	}
	
	public void loginAccount(String username, String password) {
		if(mainDA.login(username, password)) {
			account = new Account(mainDA.getPlayers(username));
		} else {
			System.out.println("Failed to login");
		}
		
	}
	public void logOut() {
		this.account = null;
	}
	
	public void createGame(Player gameStarter) {
		gameControl = new GameControl(mainDA, gameStarter);
	}
}