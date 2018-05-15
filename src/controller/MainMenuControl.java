package controller;

import java.util.ArrayList;

import dbaccess.MainDA;
import model.Catan;
import view.MainMenuGUI;

public class MainMenuControl {
	private MainDA mainDA;

	public MainMenuControl() {
		mainDA = new MainDA();
		ArrayList<Catan> gameList = mainDA.GetAllGamesOfUser("nikita"); // Must be logged in user.
		new MainMenuGUI(gameList);
	}
}
