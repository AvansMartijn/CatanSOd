package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resources;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Catan;
import model.City;
import model.Gameboard;
import model.Player;
import model.PlayerColor;
import model.Resource;
import model.ResourceType;
import model.Street;
import model.Tile;
import model.Village;
import view.BoardPanel;
import view.BottomOptionsPanel;
import view.BuildPanel;
import view.BuildingLocationButton;
import view.BuyPanel;
import view.ChatPanel;
import view.DiceDotPanel;
import view.Frame;
import view.GameGUIPanel;
import view.GameSelect;
import view.GameSouthContainerPanel;
import view.GameTopPanel;
import view.LoginRegisterPanel;
import view.MainMenuGUI;
import view.NewGamePanel;
import view.RecentGamesTopPanel;
import view.PlayerActionPanel;
import view.PlayerOptionMenuPanel;
import view.PlayerStatsPanel;
import view.RecentGamePanel;
import view.RecentGamesPanel;
import view.ReturnToBuildPanel;
import view.StreetLocationButton;
import view.TileButton;
import view.TradeBankPanel;
import view.TradeOptionsPanel;
import view.TradePlayerPanel;
import view.TradeRespondDialog;
import view.CurrentTradeRequestPanel;
import view.WaitingRoom;

public class GuiController {

	private GameControl gameControl;
	private MainControl mainControl;
	private Frame frame;
	private GameSouthContainerPanel gameSouthContainerPanel;
	private PlayerStatsPanel[] playerStatsPanels;
	private RecentGamesTopPanel topOptionsPanel;
	private BottomOptionsPanel bottomOptionsPanel;
	private MainMenuGUI mainMenuGui;
	private GameGUIPanel gameGUIPanel;
	private RecentGamesPanel currentGamesPanel;
	private BoardPanel boardPanel;
	private DiceDotPanel diceDotPanel;
	private ChatPanel chatPanel;
	private PlayerActionPanel playerActionPanel;
	private PlayerOptionMenuPanel playerOptionMenuPanel;
	private BuyPanel buyPanel;
	private BuildPanel buildPanel;
	private ReturnToBuildPanel returnToBuildPanel;
	private TradeRespondDialog tradeRespondDialog;
	private TradeOptionsPanel tradeOptionsPanel;
	private TradePlayerPanel tradePlayerPanel;
	private TradeBankPanel tradeBankPanel;
	private CurrentTradeRequestPanel tradeRequestListPanel;

	private ArrayList<Catan> gameList;
	// private Gameboard gameBoard;

	// TODO uncomment these when PlayerActionPanelExpended is merged (these classes
	// are added in that branch)
	// private BuyDialog buyDialog;
	// private TradeDialog tradeDialog;
	// private BuildDialog buildDialog;

	private int pageNr;

	public GuiController(MainControl mainControl, GameControl gameControl) {
		this.mainControl = mainControl;
		this.gameControl = gameControl;
		frame = new Frame();

		setInlogPanel();


//		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice graphicsDevice= graphicsEnvironment.getDefaultScreenDevice(); 
//		
//		boolean canChangeDisplay = graphicsDevice.isDisplayChangeSupported();
//		if (canChangeDisplay) {
//			DisplayMode displayMode = graphicsDevice.getDisplayMode();
//			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//			int width = (int) screenSize.getWidth();
//			int height = (int) screenSize.getHeight();
//			int bitDepth = 16;
//			displayMode = new DisplayMode(width, height, bitDepth, displayMode.getRefreshRate());
//			try {
//				graphicsDevice.setDisplayMode(displayMode);
//			} catch(Throwable e) {
//				graphicsDevice.setFullScreenWindow(null);
//			}
//			
//		}


		frame.dispose();
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.pack();
		frame.setVisible(true);
	}

	public void addSystemMessageToChat(Color c, String s) {
		chatPanel.addSystemMessageToChat(c, s);
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
					loginregisterPanel.setMessagelabel("Ongeldige gegevens ingevoerd");
//					frame.pack(); // TODO discuss with martijn
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
						loginregisterPanel.setMessagelabel("Gebruikersnaam bestaat al");
						frame.pack();
					} else {
						loginregisterPanel.setMessagelabel("Account succesvol aangemaakt");
					}
				} else {
					loginregisterPanel.setMessagelabel("Ongeldige invoer: Speciale tekens zijn niet toegestaan");
				}
			}
		});

		loginregisterPanel.getExitButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "Ja", "Annuleren" };

				int result = JOptionPane.showOptionDialog(null, "Weet je zeker dat je het spel wilt afsluiten?",
						"Waarschuwing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (result == JOptionPane.YES_OPTION) {

					System.exit(0);
				}
			}
		});
		frame.setContentPane(loginregisterPanel);
		frame.pack();
	}

	public void setMainMenu(ArrayList<Catan> gameList, String username) {

		topOptionsPanel = new RecentGamesTopPanel();

		NewGamePanel newGamePanel = new NewGamePanel(mainControl.getAllAccounts(), mainControl.getAcccountUsername());
		topOptionsPanel.getCreateGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setTitle("Nieuw Spel");
				dialog.setContentPane(newGamePanel);
				dialog.pack();
				dialog.setLocationRelativeTo(null);
				dialog.toFront();
				dialog.requestFocus();
				dialog.setVisible(true);
			}
		});

		newGamePanel.getCreateGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String boardChoice = (String)newGamePanel.getBoardChoice();
				if(boardChoice == "Random") {
					mainControl.createNewGame(newGamePanel.getInvitedPlayers(), true);
				}else {
					mainControl.createNewGame(newGamePanel.getInvitedPlayers(), false);
				}

			}
		});

		currentGamesPanel = new RecentGamesPanel(gameList, pageNr);
		ArrayList<RecentGamePanel> gamePanels = currentGamesPanel.getGamePanels();
		for (RecentGamePanel p : gamePanels) {
			p.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mainControl.joinGame(p.getGame());
				}

			});
		}
		bottomOptionsPanel = new BottomOptionsPanel();

		bottomOptionsPanel.getLogoutButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "Ja", "Nee" };

				int result = JOptionPane.showOptionDialog(null, "Weet je zeker dat je wilt uitloggen?", "Waarschuwing",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (result == JOptionPane.YES_OPTION) {
					mainControl.stopIngameTimer();
					mainControl.logOut();
					setInlogPanel();
					// TODO Auto-generated method stub
				}
			}
		});

		bottomOptionsPanel.getExitButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "Ja", "Nee" };

				int result = JOptionPane.showOptionDialog(null, "Weet je zeker dat je het spel wilt afsluiten?",
						"Waarschuwing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (result == JOptionPane.YES_OPTION) {
					mainControl.logOut();
					System.exit(0);
				}
			}
		});

		this.mainMenuGui = new MainMenuGUI(username, topOptionsPanel, bottomOptionsPanel, currentGamesPanel);

		frame.setContentPane(mainMenuGui);
		frame.pack();
	}

	public void setWaitingRoom(ArrayList<Player> players) {
		// WaitingRoom waitingRoom = new WaitingRoom(players);
		// frame.setContentPane(waitingRoom);
		frame.pack();

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
		this.chatPanel = new ChatPanel(gameControl.getCatanGame().getMessages());
		this.diceDotPanel = new DiceDotPanel(gameControl.getCatanGame().getDice());
		if (gameControl.hasRolledDice()) {
			diceDotPanel.getButton().setVisible(false);
		}

		GameTopPanel gameTopPanel = new GameTopPanel(gameControl.getCatanGame().getIdGame());
		gameTopPanel.getGoToMainMenuButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "Ja", "Nee" };

				int result = JOptionPane.showOptionDialog(null, "Weet je zeker dat je het spel wilt verlaten?",
						"Waarschuwing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);
				if (result == JOptionPane.YES_OPTION) {
					gameControl.unloadCatan();
					mainControl.stopIngameTimer();
					mainControl.loadProfile();
				}
			}

		});
		this.playerOptionMenuPanel = new PlayerOptionMenuPanel();
		this.buyPanel = new BuyPanel();
		this.buildPanel = new BuildPanel();
		this.returnToBuildPanel = new ReturnToBuildPanel();
		this.tradeOptionsPanel = new TradeOptionsPanel();
		this.tradePlayerPanel = new TradePlayerPanel(gameControl.getCatanGame().getSelfPlayer());
		this.tradeBankPanel = new TradeBankPanel(gameControl.getCatanGame().getSelfPlayer());
		this.tradeRequestListPanel = new CurrentTradeRequestPanel();
		this.playerActionPanel = new PlayerActionPanel(playerOptionMenuPanel, buildPanel, buyPanel, tradePlayerPanel,
				tradeBankPanel, returnToBuildPanel, tradeOptionsPanel, tradeRequestListPanel);

		// this.tradePanel = new TradePanel();
		// this.returnToBuildPanel = new ReturnToBuildPanel();
		// this.playerActionPanel = new PlayerActionPanel(playerOptionMenuPanel,
		// buildPanel, buyPanel, tradePanel,
		// returnToBuildPanel);

		this.boardPanel = new BoardPanel(gameControl.getCatanGame().getGameboard());
		for (int i = 0; i < 4; i++) {
			Player player = gameControl.getCatanGame().getPlayers().get(i);
			PlayerStatsPanel playerstatspanel = new PlayerStatsPanel(player);
			playerStatsPanels[i] = (playerstatspanel);
		}
		this.gameSouthContainerPanel = new GameSouthContainerPanel(playerStatsPanels,
				gameControl.getCatanGame().getSelfPlayer());

		JTextField chatPanelTextField = chatPanel.getTextField();
		chatPanelTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = chatPanelTextField.getText();
				if (message != null) {
					if (gameControl.addMessage(message)) {
						chatPanelTextField.setText("");
					} else {
						addSystemMessageToChat(Color.RED, "Je mag maar 1 bericht per seconde versturen!");
					}
				}
			}
		});

		// System.out.println("addplayercolortoStreet");
		addPlayerColorToStreetLocs();
		gameGUIPanel = new GameGUIPanel(gameTopPanel, boardPanel, diceDotPanel, chatPanel, playerActionPanel,
				gameSouthContainerPanel, gameControl.getCatanGame().getSelfPlayer());

		addListeners();

		frame.setContentPane(gameGUIPanel);
		frame.pack();

	}

	private void addTileListeners() {
		for (TileButton b : boardPanel.getTileButtonArrayList()) {
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					gameControl.changeRobber(b.getTile().getIdTile());
					boardPanel.disableTileButtons();
					boardPanel.repaint();
					gameControl.addMessage("Heeft de struikrover verzet naar " + b.getTile().getIdTile());
				}
			});
		}
	}

	private void addBuildLocListeners() {

		for (BuildingLocationButton blb : boardPanel.getBuildingLocationButtonArrayList()) {
			blb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if(blb.getState()) {
						if (!gameControl.buildCity(blb.getBuildingLocation())) {
							addSystemMessageToChat(Color.RED, "Je kan hier geen stad bouwen");
							
						} else {
							gameControl.addMessage("Heeft een stad gebouwd op X: " + blb.getBuildingLocation().getXLoc() + " Y: " + blb.getBuildingLocation().getYLoc());
							boardPanel.disableBuildingLocButtons();
							playerActionPanel.setBuildPanel();
							addPlayerColorToBuildingLocs();
						}
					}else {
						if (!gameControl.buildVillage(blb.getBuildingLocation())) {
							addSystemMessageToChat(Color.RED, "Je kan hier geen nederzetting bouwen");
							
						} else {
							gameControl.addMessage("Heeft een dorp gebouwd op X: " + blb.getBuildingLocation().getXLoc() + " Y: " + blb.getBuildingLocation().getYLoc());
							boardPanel.disableBuildingLocButtons();
							playerActionPanel.setBuildPanel();
							addPlayerColorToBuildingLocs();
						}
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
						addSystemMessageToChat(Color.RED, "Je kan hier geen straat bouwen");
					} else {
						gameControl.addMessage("Heeft een straat gebouwd tussen X: " + slb.getStreetLocation().getBlStart().getXLoc() + " Y: " + slb.getStreetLocation().getBlStart().getYLoc()
								 + " en X: " + slb.getStreetLocation().getBlEnd().getXLoc() + " Y: " + slb.getStreetLocation().getBlEnd().getYLoc());
						boardPanel.disableStreetLocButtons();
						playerActionPanel.setBuildPanel();
						addPlayerColorToStreetLocs();
					}
				}
			});
		}
	}

	private void addRollButtonListener() {

		diceDotPanel.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Catan catanGame = gameControl.getCatanGame();
				// Only if it is the players' turn can the player roll the dice.
				// Only if the player has not rolled the dice yet, can the player roll dice.
				// This is separate for testing purposes, so just make it if(true) to disable
				// the restriction.
				// boolean shouldRoll = catanGame.getSelfPlayer().getFollownr() ==
				// catanGame.getTurn() && !catanGame.hasRolledDice();
				// if(shouldRoll) {
				// int[] die = gameControl.rollDice();
				// gameControl.getCatanGame().getDice().setDie(die);
				// gameControl.editDiceLastThrown(die);
				// gameControl.getCatanGame().setRolledDice(false);
				gameControl.rollDice();
				diceDotPanel.getButton().setVisible(false);
				// gameControl.getCatanGame().setRolledDice(true);
				refreshDice();
				// When the player rolls the dice, he starts his turn
				// }
			}
		});
	}

	private void addPlayerActionBuyButtonListener() { // TODO IF STATEMENT IS BROKEN?, FOR TESTING PURPOSES CODE IS
														// ABOVE IT

		playerActionPanel.getPlayerOptionMenuPanel().getBuyButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setBuyPanel();
				if (gameControl.getCatanGame().isSelfPlayerTurn()) {

				}
			}
		});
	}

	private void addPlayerActionBuyQuitButtonListener() {
		playerActionPanel.getBuyPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setPlayerOptionMenuPanel();

			}
		});
	}

	private void addTradeButtonsListeners() {

		playerActionPanel.getPlayerOptionMenuPanel().getPlayerTradeButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();

			}
		});

		playerActionPanel.getTradeOptionsPanel().getBankButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int[] resources = gameControl.getHarbourLocations();
				playerActionPanel.getTradeBankPanel().updateRatio(resources);
				playerActionPanel.setTradeBankPanel();
			}
		});

		playerActionPanel.getTradeOptionsPanel().getPlayerButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradePlayerPanel();

			}
		});

		playerActionPanel.getTradeOptionsPanel().getBackButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setPlayerOptionMenuPanel();

			}
		});

		playerActionPanel.getTradeOptionsPanel().getRequestsButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeRequestListPanel();
				if (gameControl.getCatanGame().isSelfPlayerTurn()) {
				}
			}
		});

		playerActionPanel.getPlayerTradePanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();
				if (gameControl.getCatanGame().isSelfPlayerTurn()) {
				}
			}
		});

		playerActionPanel.getTradeBankPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();
				if (gameControl.getCatanGame().isSelfPlayerTurn()) {
				}
			}
		});

		playerActionPanel.getTradeBankPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();
				if (gameControl.getCatanGame().isSelfPlayerTurn()) {
				}
			}
		});
		

		playerActionPanel.getTradeRequestListPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();
				if (gameControl.getCatanGame().isSelfPlayerTurn()) {
				}
			}
		});
	}

	private void addPlayerActionTradeSendRequestButtonListener() {
		playerActionPanel.getPlayerTradePanel().getSendRequestButton().addActionListener(new ActionListener() { // TODO
																												// maybe
			// in
			// GameControl
			@Override
			public void actionPerformed(ActionEvent e) {
				// give
				int stoneGive = playerActionPanel.getPlayerTradePanel().getStoneGive();
				int woolGive = playerActionPanel.getPlayerTradePanel().getWoolGive();
				int ironGive = playerActionPanel.getPlayerTradePanel().getIronGive();
				int wheatGive = playerActionPanel.getPlayerTradePanel().getWheatGive();
				int woodGive = playerActionPanel.getPlayerTradePanel().getWoodGive();

				// receive
				int stoneReceive = playerActionPanel.getPlayerTradePanel().getWoodReceive();
				int woolReceive = playerActionPanel.getPlayerTradePanel().getWheatReceive();
				int ironReceive = playerActionPanel.getPlayerTradePanel().getStoneReceive();
				int wheatReceive = playerActionPanel.getPlayerTradePanel().getIronReceive();
				int woodReceive = playerActionPanel.getPlayerTradePanel().getWoolReceive();

				gameControl.createTradeRequest(stoneGive, woolGive, ironGive, wheatGive, woodGive, stoneReceive,
						woolReceive, ironReceive, wheatReceive, woodReceive);

			}
		});
	}

	private void addTradeRespondDialogActionListeners() {

	}

	// actionlisteners for build menu
	private void addPlayerActionBuildButtonsListener() {

		playerActionPanel.getPlayerOptionMenuPanel().getBuildButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameControl.canBuy(Village.cost)) {
					playerActionPanel.getBuildPanel().getVillageButton().setEnabled(true);
				} else {
					playerActionPanel.getBuildPanel().getVillageButton().setEnabled(false);
				}
				if (gameControl.canBuy(Street.cost)) {
					playerActionPanel.getBuildPanel().getStreetButton().setEnabled(true);
				} else {
					playerActionPanel.getBuildPanel().getStreetButton().setEnabled(false);
				}
				if (gameControl.canBuy(City.cost)) {
					playerActionPanel.getBuildPanel().getCityButton().setEnabled(true);
				} else {
					playerActionPanel.getBuildPanel().getCityButton().setEnabled(false);
				}
				playerActionPanel.setBuildPanel();
			}
		});

		playerActionPanel.getBuildPanel().getStreetButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boardPanel.enableStreetLocButtons();
				playerActionPanel.setReturnToBuildPanel();
			}
		});

		playerActionPanel.getBuildPanel().getVillageButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				villageBoolean = true;
				
				boardPanel.enableBuildingLocButtons(false);
				playerActionPanel.setReturnToBuildPanel();
			}
		});

		playerActionPanel.getBuildPanel().getCityButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

//				cityBoolean = true;
				boardPanel.enableBuildingLocButtons(true);

				playerActionPanel.setReturnToBuildPanel();
			}
		});

		playerActionPanel.getBuildPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setPlayerOptionMenuPanel();
			}
		});
	}

	private void addBuildBackButtonListener() {

		playerActionPanel.getReturnToBuildPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// recognize which building type is selected

				// give error message in chat of wrong type is selected

				// of building built, remove resources from hand.

				Object[] options = { "Ja", "Nee" };

				int result = JOptionPane.showOptionDialog(null, "Weet je zeker dat je wilt stoppen met bouwen?",
						"Waarschuwing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);
				if (result == JOptionPane.YES_OPTION) {
					boardPanel.disableStreetLocButtons();
					boardPanel.disableBuildingLocButtons();
					playerActionPanel.setBuildPanel();

				}
				// if (gameControl.getCatanGame().isSelfPlayerTurn()) {
				// }
			}
		});
	}

	private void addPlayerActionEndTurnButtonListener() {

		playerActionPanel.getPlayerOptionMenuPanel().getEndTurnButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				playerActionPanel.setVisible(false); // TODO DONT FORGET TO SET THIS VISIBLE WHEN SELFPLAYER TURN IS
														// BACK -- JIM
				Catan catanGame = gameControl.getCatanGame();
				catanGame.endTurn();
				if (catanGame.isSelfPlayerTurn()) {
				}
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

	// public void refresh() {
	// refreshRobber();
	// refreshDice();
	// addPlayerColorToBuildingLocs();
	// addPlayerColorToStreetLocs();
	// }

	public void refreshBoard() {
		addPlayerColorToBuildingLocs();
		addPlayerColorToStreetLocs();
	}

	public void refreshChat() {
		System.out.println("refreshchat");
		chatPanel.setMessages(gameControl.getCatanGame().getMessages());
		chatPanel.repaint();
	}

	public void refreshRobber() {
		boardPanel.repaint();
	}

	public void refreshDice() {
		diceDotPanel.repaint();
	}

	public void refreshPlayers() {
		gameSouthContainerPanel.repaint();
	}

	private void addListeners() {

		// board listeners
		addTileListeners();
		addBuildLocListeners();
		addStreetLocListeners();
		addRollButtonListener();
		addPlayerColorToBuildingLocs();

		// buy listeners
		addPlayerActionBuyButtonListener();
		addPlayerActionBuyQuitButtonListener();

		// Trade listeners
		addTradeButtonsListeners();
		addTradeRespondDialogActionListeners();
		addPlayerActionTradeSendRequestButtonListener();

		// build listeners
		addPlayerActionBuildButtonsListener();
		addBuildBackButtonListener();

		// end turn listener
		addPlayerActionEndTurnButtonListener();

	}

	// public void setGameBoard(Gameboard gameBoard) {
	// this.gameBoard = gameBoard;
	// }

}
