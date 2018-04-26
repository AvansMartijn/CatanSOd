package controller;

import model.MainMenu;

public class MainControl {
	
	private MainMenu mainMenu;
	private GameControl gameControl;
	
	
	public MainControl() {
		mainMenu = new MainMenu();
		new GameBoardControl();
		//TODO add stuff to a MainControl constructor
	}
}