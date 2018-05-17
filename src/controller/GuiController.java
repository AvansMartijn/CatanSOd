package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Catan;
import model.City;
import model.Dice;
import model.Gameboard;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import model.Street;
import model.Tile;
import model.Village;
import view.BoardPanel;
import view.BuildingLocationButton;
import view.ChatPanel;
import view.DiceDotPanel;
import view.DicePanel;
import view.Frame;
import view.GameGUIPanel;
import view.GameSelect;
import view.GameSouthContainerPanel;
import view.LoginRegisterPanel;
import view.MainMenuGUI;
import view.PlayerActionPanel;
import view.PlayerStatsPanel;
import view.RecentGamePanel;
import view.RecentGamesPanel;
import view.StreetLocationButton;
import view.TileButton;

public class GuiController {

	private GameControl gameControl;
	private MainControl mainControl;

	private Frame frame;
	private PlayerActionPanel playerActionPanel;
	private GameSouthContainerPanel gameSouthContainerPanel;
	private PlayerStatsPanel[] playerStatsPanels;
	private MainMenuGUI mainMenuGui;
	private GameGUIPanel gameGUIPanel;
	private RecentGamesPanel currentGamesPanel;
	private BoardPanel boardPanel;
	private DiceDotPanel dicePanel;
	private ChatPanel chatPanel;

	private ArrayList<Catan> gameList;
	private Gameboard gameBoard;
	private Timer timer;
	private int pageNr;

	public GuiController(MainControl mainControl, GameControl gameControl) {
		this.mainControl = mainControl;
		this.gameControl = gameControl;
		frame = new Frame();

		setInlogPanel();

		frame.dispose();
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.pack();
		frame.setVisible(true);
	}

	public void addSystemMessageToChat(String s) {
		chatPanel.addSystemMessageToChat(s);
	}

	public void setInlogPanel() {
		LoginRegisterPanel loginregisterPanel = new LoginRegisterPanel();
		loginregisterPanel.getInlogButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField usernameTextField = loginregisterPanel.getUsernameText();
				JTextField passwordTextField = loginregisterPanel.getPasswordText();
				String username = usernameTextField.getText();
				String password = passwordTextField.getText();

				if (!mainControl.loginAccount(username, password)) {
					usernameTextField.setText("");
					passwordTextField.setText("");
					loginregisterPanel.setMessagelabel("Invalid Credentials");
					frame.pack();
				} else {
					mainControl.loadProfile();
				}
			}
		});

		loginregisterPanel.getRegisterButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField usernameTextField = loginregisterPanel.getUsernameText();
				JTextField passwordTextField = loginregisterPanel.getPasswordText();
				String username = usernameTextField.getText();
				String password = passwordTextField.getText();

				if (hasValidInput(username) && hasValidInput(password)) {
					if (!mainControl.createAccount(username, password)) {
						usernameTextField.setText("");
						passwordTextField.setText("");
						loginregisterPanel.setMessagelabel("Username already exists");
						frame.pack();
					} else {
						loginregisterPanel.setMessagelabel("Successfully created account");
					}
				} else {
					loginregisterPanel.setMessagelabel("Ongeldige invoer: Speciale tekens zijn niet toegestaan");
				}

			}

		});
		frame.setContentPane(loginregisterPanel);
		frame.pack();
	}

	public void setMainMenu(ArrayList<Catan> gameList, String username) {
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
		optionsPanel.add(new JButton("Game aanmaken"));
		optionsPanel.add(new JButton("Uitnodigingen bekijken"));

		JPanel nextPreviousPanel = new JPanel();
		nextPreviousPanel.setLayout(new BoxLayout(nextPreviousPanel, BoxLayout.X_AXIS));
		JButton previousButton = new JButton("Vorige");
		previousButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (pageNr > 0) {
					pageNr--;
					retrieveGames(pageNr);
				}
				;

			}
		});
		JButton nextButton = new JButton("Volgende");
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pageNr++;
				retrieveGames(pageNr);
			}
		});
		nextPreviousPanel.add(previousButton);
		nextPreviousPanel.add(nextButton);

		currentGamesPanel = new RecentGamesPanel(gameList, pageNr);
		ArrayList<RecentGamePanel> gamePanels = currentGamesPanel.getGamePanels();
		for (RecentGamePanel p : gamePanels) {
			p.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					joinGame(p.getGame());

				}

			});
		}

		this.mainMenuGui = new MainMenuGUI(username, optionsPanel, nextPreviousPanel, currentGamesPanel);

		frame.setContentPane(mainMenuGui);
		frame.pack();
	}

	private void joinGame(Catan game) {
		gameControl.setCatan(game);
		setGameBoard(gameControl.getGameboard());
		setIngameGuiPanel();
	}

	public void retrieveGames(int pageId) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainMenuGui.remove(currentGamesPanel);
		currentGamesPanel = new RecentGamesPanel(gameList, pageId);
		mainMenuGui.add(currentGamesPanel, c);
		mainMenuGui.getCurrentGamesPanel().invalidate();
		mainMenuGui.getCurrentGamesPanel().validate();
	}

	public void setGameSelect() {

		GameSelect gameSelect = new GameSelect();

		gameSelect.getCreateNewGameButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (gameSelect.getStandardGameButton().isSelected()) {
					// create standard game
				} else if (gameSelect.getRandomGameButton().isSelected()) {
					// create random game
				} else {
					gameSelect.getWarningLabel().setText("Geen speelbord geselecteerd");
				}
			}
		});
	}

	private boolean hasValidInput(String str) {
		if (str == null || str.trim().isEmpty()) {
			return false;
		}
		Pattern p = Pattern.compile("[^a-z A-Z0-9]");
		Matcher m = p.matcher(str);

		boolean b = m.find();
		if (!b) {
			return true;
		} else {
			return false;
		}
	}

	public void setIngameGuiPanel() {
		playerStatsPanels = new PlayerStatsPanel[4];
		this.chatPanel = new ChatPanel(gameControl.getMessages());
		this.dicePanel = new DiceDotPanel();
		this.playerActionPanel = new PlayerActionPanel();
		this.boardPanel = new BoardPanel(gameControl.getGameboard());
		for (int i = 0; i < 4; i++) {
			Player player = gameControl.getCatanGame().getPlayers().get(i);
			PlayerStatsPanel playerstatspanel = new PlayerStatsPanel(player);
			playerStatsPanels[i] = (playerstatspanel);
		}
		this.gameSouthContainerPanel = new GameSouthContainerPanel(playerStatsPanels,
				gameControl.getCatanGame().getSelfPlayer());
		dicePanel.setLastThrown(gameControl.getDiceLastThrown());

		JTextField chatPanelTextField = chatPanel.getTextField();
		chatPanelTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = chatPanelTextField.getText();
				if (message != null) {
					if (gameControl.addMessage(message)) {
						chatPanelTextField.setText("");
					} else {
						addSystemMessageToChat("Je mag maar 1 bericht per seconde versturen!");
					}

				}
			}
		});
		gameGUIPanel = new GameGUIPanel(boardPanel, dicePanel, chatPanel, playerActionPanel, gameSouthContainerPanel);
		addTileListeners();
		addBuildLocListeners();
		addStreetLocListeners();
		addRollButtonListener();
		addPlayerColorToBuildingLocs();
		addPlayerColorToStreetLocs();

		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				refresh();
				chatPanel.setMessages(gameControl.getMessages());
			}

		}, 0, 5000);

		frame.setContentPane(gameGUIPanel);
		frame.pack();

	}

	private void addTileListeners() {
		for (TileButton b : boardPanel.getTileButtonArrayList()) {
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					for (int i = 0; i < gameBoard.getTileArr().size(); i++) {
						if (gameBoard.getTileArr().get(i).hasRobber()) {
							gameBoard.getTileArr().get(i).setRobber(false);
						}
					}

					b.getTile().setRobber(true);
					gameControl.changeRobberInDB(b.getTile().getIdTile());
					boardPanel.repaint();
				}

			});
		}
	}

	private void addBuildLocListeners() {

		for (BuildingLocationButton blb : boardPanel.getBuildingLocationButtonArrayList()) {
			blb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!gameControl.buildCity(blb.getBuildingLocation())) {
						System.out.println("Je kan hier geen neerzetting bouwen");
					}

				}
			});
		}
	}

	private void addStreetLocListeners() {
		for (StreetLocationButton slb : boardPanel.getStreetLocationButtonArrayList()) {
			slb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!gameControl.buildStreet(slb.getStreetLocation())) {
						System.out.println("je kan hier geen straat bouwen");
					}

				}
			});
		}
	}

	private void addRollButtonListener() {

		dicePanel.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int[] die = gameControl.rollDice();
				dicePanel.setLastThrown(die);
				gameControl.editDiceLastThrown(die);
				dicePanel.repaint();

			}

		});
	}

	public void addPlayerColorToBuildingLocs() {
		for (BuildingLocationButton blb : boardPanel.getBuildingLocationButtonArrayList()) {			
			Color color = Color.BLACK;

			Village village = blb.getBuildingLocation().getVillage();	
			if (village != null) {
				color = convertPlayerColorToAWT(village.getPlayer().getColor());
			}
			blb.setBackground(color);

			City city = blb.getBuildingLocation().getCity();
			if (city != null) {
				color = convertPlayerColorToAWT(city.getPlayer().getColor());
			}
			blb.setBackground(color);		
			

		}
	}
	
	public void addPlayerColorToStreetLocs() {
		for (StreetLocationButton slb : boardPanel.getStreetLocationButtonArrayList()) {			
			Color color = Color.BLACK;

			Street street = slb.getStreetLocation().getStreet();	
			if (street != null) {
				color = convertPlayerColorToAWT(street.getPlayer().getColor());
			}
			slb.setBackground(color);		

		}
	}

	public Color convertPlayerColorToAWT(PlayerColor playerColor) {
		Color color = Color.BLACK;
		switch (playerColor) {
		case ROOD:
			color = Color.RED;
			break;
		case WIT:
			color = Color.WHITE;
			break;
		case BLAUW:
			color = Color.BLUE;
			break;
		case ORANJE:
			color = Color.ORANGE;
			break;

		}
		return color;
	}

	public void refresh() {
		refreshRobber();
		refreshDice();
		addPlayerColorToBuildingLocs();
	}

	public void refreshRobber() {
		for (Tile t : gameBoard.getTileArr()) {
			if (t.getIdTile() == gameControl.getRobberIdTile()) {
				t.setRobber(true);

			} else {
				t.setRobber(false);
			}
		}
		boardPanel.repaint();
	}

	public void refreshDice() {
		dicePanel.setLastThrown(gameControl.getDiceLastThrown());
		gameControl.setDiceLastThrown(gameControl.getDiceLastThrown());
		dicePanel.repaint();
	}

	public void setGameBoard(Gameboard gameBoard) {
		this.gameBoard = gameBoard;
	}

}
