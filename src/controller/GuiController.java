package controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import model.Street;
import model.Tile;
import model.Village;
import view.BoardPanel;
import view.BuildingLocationButton;
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
import view.PlayerActionPanel;
import view.PlayerOptionMenuPanel;
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
	private DiceDotPanel diceDotPanel;
	private ChatPanel chatPanel;
	private PlayerOptionMenuPanel playerOptionMenuPanel;

	private ArrayList<Catan> gameList;
//	private Gameboard gameBoard;
	private Timer timer;
	
	//TODO uncomment these when PlayerActionPanelExpended is merged (these classes are added in that branch)
//	private BuyDialog buyDialog;
//	private TradeDialog tradeDialog;
//	private BuildDialog buildDialog;
	
	private int pageNr;

	public GuiController(MainControl mainControl, GameControl gameControl) {
		this.mainControl = mainControl;
		this.gameControl = gameControl;
		timer = new Timer();
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
		JButton createGameButton = new JButton("Game aanmaken");
		NewGamePanel newGamePanel = new NewGamePanel(mainControl.getAllAccounts());
		createGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setTitle("Nieuw Spel");
				dialog.setContentPane(newGamePanel);
				dialog.pack();
				dialog.setVisible(true);
			}
		});
		newGamePanel.getCreateGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainControl.createNewGame(newGamePanel.getInvitedPlayers());
				
			}
		});
		optionsPanel.add(createGameButton);

		optionsPanel.add(new JButton("Uitnodigingen bekijken"));

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

		this.mainMenuGui = new MainMenuGUI(username, optionsPanel, currentGamesPanel);

		frame.setContentPane(mainMenuGui);
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
 		GameTopPanel gameTopPanel= new GameTopPanel(gameControl.getCatanGame().getIdGame());
 		gameTopPanel.getGoToMainMenuButton().addActionListener(new ActionListener() {

 			@Override
 			public void actionPerformed(ActionEvent e) {

 				Object[] options = {"Ja",
                 "Nee"};
				
 				int result = JOptionPane.showOptionDialog(null, "Weet je zeker dat je het spel wilt verlaten?", "Waarschuwing", JOptionPane.YES_NO_OPTION,
 						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);					
 				if (result == JOptionPane.YES_OPTION) {
 					gameControl.unloadCatan();
 					timer.cancel();
 					mainControl.loadProfile();
 				}

 			}

 		});
		this.playerActionPanel = new PlayerActionPanel();
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
						addSystemMessageToChat("Je mag maar 1 bericht per seconde versturen!");
					}

				}
			}
		});
// <<<<<<< FixSquad2
// 		gameGUIPanel = new GameGUIPanel(boardPanel, diceDotPanel, chatPanel, playerActionPanel, gameSouthContainerPanel,
// 				gameControl.getCatanGame().getSelfPlayer());
// =======
// >>>>>>> development
		addTileListeners();
		addBuildLocListeners();
		addStreetLocListeners();
		addRollButtonListener();
		addPlayerColorToBuildingLocs();
		System.out.println("addplayercolortoStreet");
		addPlayerColorToStreetLocs();
		gameGUIPanel = new GameGUIPanel(gameTopPanel, boardPanel, diceDotPanel, chatPanel, playerOptionMenuPanel, gameSouthContainerPanel,
				gameControl.getCatanGame().getSelfPlayer());
		
		addPlayerActionBuyButtonListener();
		addPlayerActionTradeButtonListener();
		addPlayerActionBuildButtonListener();
		addPlayerActionEndTurnButtonListener();

		frame.setContentPane(gameGUIPanel);
		frame.pack();

	}

	private void addTileListeners() {
		for (TileButton b : boardPanel.getTileButtonArrayList()) {
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
 					gameControl.changeRobber(b.getTile().getIdTile());
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
					if (!gameControl.buildVillage(blb.getBuildingLocation())) {
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

		diceDotPanel.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				Catan catanGame = gameControl.getCatanGame();
				//Only if it is the players' turn can the player roll the dice.
				//Only if the player has not rolled the dice yet, can the player roll dice.
				//This is separate for testing purposes, so just make it if(true) to disable the restriction. 
//				boolean shouldRoll = catanGame.getSelfPlayer().getFollownr() == catanGame.getTurn() && !catanGame.hasRolledDice();
//				if(shouldRoll) {
//					int[] die = gameControl.rollDice();
//					gameControl.getCatanGame().getDice().setDie(die);
//					gameControl.editDiceLastThrown(die);
					gameControl.rollDice();
					gameControl.getCatanGame().setRolledDice(true);
					refreshDice();
					//When the player rolls the dice, he starts his turn
//				}
			}
		});
	}
	
	private void addPlayerActionBuyButtonListener() {
		
		playerOptionMenuPanel.getBuyButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(gameControl.getCatanGame().isSelfPlayerTurn()) {
					//TODO uncomment this when PlayerActionPanelExpended is merged (this class is added in that branch)
					
					
					
				}			
			}
		});		
	}
	
	private void addPlayerActionTradeButtonListener() {
		
		playerOptionMenuPanel.getTradeButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(gameControl.getCatanGame().isSelfPlayerTurn()) {
					//TODO uncomment this when PlayerActionPanelExpended is merged (this class is added in that branch)
					
					
				}
			}
		});		
	}

	private void addPlayerActionBuildButtonListener() {
		
		playerOptionMenuPanel.getBuildButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(gameControl.getCatanGame().isSelfPlayerTurn()) {
					//TODO uncomment this when PlayerActionPanelExpended is merged (this class is added in that branch)
					
					
				}
			}
		});		
	}
	
	private void addPlayerActionEndTurnButtonListener() {
		
		playerOptionMenuPanel.getEndTurnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Catan catanGame = gameControl.getCatanGame();
				if(catanGame.isSelfPlayerTurn()) {
					catanGame.endTurn();					
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

//	public void refresh() {
//		refreshRobber();
//		refreshDice();
//		addPlayerColorToBuildingLocs();
//		addPlayerColorToStreetLocs();
//	}
	
	public void refreshBoard() {
		addPlayerColorToBuildingLocs();
		addPlayerColorToStreetLocs();
	}
	
	public void refreshChat() {
		chatPanel.setMessages(gameControl.getCatanGame().getMessages());
		chatPanel.repaint();
	}

	public void refreshRobber() {
		boardPanel.repaint();
	}

	public void refreshDice() {
		diceDotPanel.repaint();
	}

//	public void setGameBoard(Gameboard gameBoard) {
//		this.gameBoard = gameBoard;
//	}
}
