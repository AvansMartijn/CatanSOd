package controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Catan;
import model.City;
import model.Player;
import model.PlayerColor;
import model.ResourceType;
import model.Street;
import model.Tile;
import model.TradeRequest;
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
import view.InvitePanel;
import view.LoginRegisterPanel;
import view.MainMenuGUI;
import view.ManageInvitesFrame;
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
import view.TradeReceiveDialog;
import view.TradeRespondDialog;
import view.CurrentTradeRequestPanel;
import view.DevelopmentCardButton;
import view.DevelopmentCardDialogPanel;
import view.DevelopmentCardsPanel;

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
	private InvitePanel invitePanel;
	private ReturnToBuildPanel returnToBuildPanel;
	private TradeOptionsPanel tradeOptionsPanel;
	private TradePlayerPanel tradePlayerPanel;
	private TradeBankPanel tradeBankPanel;
	private CurrentTradeRequestPanel tradeRequestListPanel;
	private DevelopmentCardsPanel developmentCardsPanel;

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
		gameControl.setGuiController(this);
		frame = new Frame();

		setInlogPanel();

		// GraphicsEnvironment graphicsEnvironment =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();
		// GraphicsDevice graphicsDevice= graphicsEnvironment.getDefaultScreenDevice();
		//
		// boolean canChangeDisplay = graphicsDevice.isDisplayChangeSupported();
		// if (canChangeDisplay) {
		// DisplayMode displayMode = graphicsDevice.getDisplayMode();
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// int width = (int) screenSize.getWidth();
		// int height = (int) screenSize.getHeight();
		// int bitDepth = 16;
		// displayMode = new DisplayMode(width, height, bitDepth,
		// displayMode.getRefreshRate());
		// try {
		// graphicsDevice.setDisplayMode(displayMode);
		// } catch(Throwable e) {
		// graphicsDevice.setFullScreenWindow(null);
		// }
		//
		// }

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
		topOptionsPanel.getInviteButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainControl.loadInvites();

			}
		});

		newGamePanel.getCreateGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String boardChoice = (String) newGamePanel.getBoardChoice();
				if (boardChoice == "Random") {
					mainControl.createNewGame(newGamePanel.getInvitedPlayers(), true);
				} else {
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

	public void setInvitePanel(ArrayList<Catan> invitedList, ArrayList<Catan> ableToInviteList) {
		InvitePanel invitePanel = new InvitePanel(invitedList, ableToInviteList);
		invitePanel.getAcceptButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				mainControl.acceptInvite(invitePanel.getInvitedList().get(invitePanel.getInvitedListSelectedIndex()));
				mainControl.loadInvites();
			}
		});
		invitePanel.getDeclineButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainControl.declineInvite(invitePanel.getInvitedList().get(invitePanel.getInvitedListSelectedIndex()));
				mainControl.loadInvites();
			}
		});
		invitePanel.getRefreshButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainControl.loadInvites();
			}
		});
		invitePanel.getInviteButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Catan catan = invitePanel.getInvitedList().get(invitePanel.getInvitedListSelectedIndex());
				ManageInvitesFrame frame = new ManageInvitesFrame(mainControl.getAllAccounts(), catan);
				frame.panel.getSaveInvitesButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (frame.panel.getInvitedPlayers().size() < 4) {
							JOptionPane.showConfirmDialog(null, "Te weinig spelers",
									"Je moet minimaal 4 spelers hebben.", JOptionPane.OK_OPTION);
						} else {
							// Compare Lists and update in DB.
							ArrayList<Player> playersToRemove = new ArrayList<>();
							ArrayList<Player> playersToAdd = new ArrayList<>();
							for (Player p : catan.getPlayers()) {
								if (!frame.panel.getInvitedPlayers().stream()
										.anyMatch(t -> t.getUsername().equals(p.getUsername()))) {
									// Remove P
									playersToRemove.add(p);
								}
							}
							for (Player s : frame.panel.getInvitedPlayers()) {
								if (!catan.getPlayers().stream()
										.anyMatch(t -> t.getUsername().equals(s.getUsername()))) {
									// Add S
									playersToAdd.add(s);
								}
							}
							if (playersToRemove.size() == playersToAdd.size()) {
								mainControl.switchInvites(playersToAdd, playersToRemove, catan.getIdGame());
								JOptionPane.showConfirmDialog(null, "Gelukt!", "De nieuwe mensen zijn uitgenodigd!",
										JOptionPane.OK_OPTION);
							}
							frame.dispose();// Invited. Frame can close now.
							mainControl.loadInvites();
						}

					}
				});
			}
		});
		this.invitePanel = invitePanel;
		frame.setContentPane(this.invitePanel);
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
		developmentCardsPanel = new DevelopmentCardsPanel(gameControl.getCatanGame().getSelfPlayer());
		this.chatPanel = new ChatPanel(gameControl.getCatanGame().getMessages());
		this.diceDotPanel = new DiceDotPanel(gameControl.getCatanGame().getDice());

		GameTopPanel gameTopPanel = new GameTopPanel(gameControl.getCatanGame().getIdGame());

		gameTopPanel.getGoToMainMenuButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "Spel verlaten", "Afsluiten", "Annuleren" };

				int result = JOptionPane.showOptionDialog(null, "Weet je zeker dat je het spel wilt verlaten?",
						"Waarschuwing", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
						options[0]);
				if (result == JOptionPane.YES_OPTION) {
					gameControl.unloadCatan();
					mainControl.stopIngameTimer();
					mainControl.loadProfile();
				}
				if (result == JOptionPane.NO_OPTION) {
					System.exit(0);
				}

			}

		});
		this.playerOptionMenuPanel = new PlayerOptionMenuPanel();
		this.buyPanel = new BuyPanel();
		this.buildPanel = new BuildPanel();
		this.returnToBuildPanel = new ReturnToBuildPanel();
		this.tradeOptionsPanel = new TradeOptionsPanel();
		this.tradePlayerPanel = new TradePlayerPanel(gameControl.getCatanGame().getSelfPlayer());
		this.tradeBankPanel = new TradeBankPanel();
		this.tradeRequestListPanel = new CurrentTradeRequestPanel();
		this.playerActionPanel = new PlayerActionPanel(playerOptionMenuPanel, buildPanel, buyPanel, tradePlayerPanel,
				tradeBankPanel, returnToBuildPanel, tradeOptionsPanel, tradeRequestListPanel);
		if (gameControl.getCatanGame().getTurn() == gameControl.getCatanGame().getSelfPlayer().getIdPlayer()) {
			enablePlayerActionPanel();
		}

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
		this.gameSouthContainerPanel = new GameSouthContainerPanel(playerStatsPanels, developmentCardsPanel);

		JTextField chatPanelTextField = chatPanel.getTextField();
		chatPanelTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = chatPanelTextField.getText();
				if (message != null) {
					if (gameControl.addPlayerMessage(message)) {
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
					if(!b.getTile().hasRobber()) {
					gameControl.changeRobber(b.getTile().getIdTile());
					boardPanel.disableTileButtons();
					boardPanel.repaint();
					gameControl.addLogMessage(gameControl.getCatanGame().getSelfPlayer().getUsername()
							+ " Heeft de struikrover verzet naar " + b.getTile().getIdTile());
					enablePlayerActionPanel();
					gameControl.stealCardCauseRobber();
					} else {
						addSystemMessageToChat(Color.RED, "Je moet de robber naar een ander vak verplaatsen!");
					}
				}
			});
		}
	}
//	
//	public void repaintAndValidate() {
//		gameGUIPanel.repaint();
//		gameGUIPanel.revalidate();
//	}

	private void addBuildLocListeners() {

		for (BuildingLocationButton blb : boardPanel.getBuildingLocationButtonArrayList()) {
			blb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (gameControl.getCatanGame().isFirstRound()) {
						if (!gameControl.buildInitialVillage(blb.getBuildingLocation())) {
							addSystemMessageToChat(Color.RED, "Je kan hier geen nederzetting bouwen");

						} else {
							gameControl.addLogMessage(gameControl.getCatanGame().getSelfPlayer().getUsername()
									+ " Heeft een dorp gebouwd op X: " + blb.getBuildingLocation().getXLoc() + " Y: "
									+ blb.getBuildingLocation().getYLoc());

							boardPanel.disableBuildingLocButtons();
							addPlayerColorToBuildingLocs();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							boardPanel.enableStreetLocButtons();

							// refreshPlayerResources();
							// refreshPlayers();
						}
					} else {
						if (blb.getState()) {
							if (!gameControl.buildCity(blb.getBuildingLocation())) {
								addSystemMessageToChat(Color.RED, "Je kan hier geen stad bouwen");

							} else {
								gameControl.addLogMessage(gameControl.getCatanGame().getSelfPlayer().getUsername()
										+ " Heeft een stad gebouwd op X: " + blb.getBuildingLocation().getXLoc()
										+ " Y: " + blb.getBuildingLocation().getYLoc());
								boardPanel.disableBuildingLocButtons();
								playerActionPanel.setBuildPanel();
								addPlayerColorToBuildingLocs();
								refreshPlayerResources();
//								refreshPlayers();
							}
						} else {
							if (!gameControl.buildVillage(blb.getBuildingLocation())) {
								addSystemMessageToChat(Color.RED, "Je kan hier geen nederzetting bouwen");

							} else {
								gameControl.addLogMessage(gameControl.getCatanGame().getSelfPlayer().getUsername()
										+ " Heeft een dorp gebouwd op X: " + blb.getBuildingLocation().getXLoc()
										+ " Y: " + blb.getBuildingLocation().getYLoc());
								boardPanel.disableBuildingLocButtons();
								playerActionPanel.setBuildPanel();
								addPlayerColorToBuildingLocs();

								refreshPlayerResources();
//								refreshPlayers();
							}
						}
					}

				}
			});
		}
	}

	public void refreshPlayerResources() {
		gameGUIPanel.getResourcesPanel().updateResourcesAmount();
		updatePlayerStats();
	}

	private void addStreetLocListeners() {
		for (StreetLocationButton slb : boardPanel.getStreetLocationButtonArrayList()) {
			slb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (gameControl.getCatanGame().isFirstRound()) {
						System.out.println("buildstreet first round");
						if (!gameControl.buildInitialStreet(slb.getStreetLocation())) {
							addSystemMessageToChat(Color.RED, "Je kan hier geen straat bouwen");
						} else {
							gameControl.addLogMessage(gameControl.getCatanGame().getSelfPlayer().getUsername()
									+ " Heeft een straat gebouwd tussen X: "
									+ slb.getStreetLocation().getBlStart().getXLoc() + " Y: "
									+ slb.getStreetLocation().getBlStart().getYLoc() + " en X: "
									+ slb.getStreetLocation().getBlEnd().getXLoc() + " Y: "
									+ slb.getStreetLocation().getBlEnd().getYLoc());
							boardPanel.disableStreetLocButtons();
							// playerActionPanel.setBuildPanel();
							addPlayerColorToStreetLocs();
							gameControl.endFirstRoundTurn();
							// gameGUIPanel.getResourcesPanel().updateResourcesAmount();
							// updatePlayerStats();
						}
					} else {
						System.out.println("buildstreet normal");
						if (!gameControl.buildStreet(slb.getStreetLocation())) {
							addSystemMessageToChat(Color.RED, "Je kan hier geen straat bouwen");
						} else {
							gameControl.addLogMessage(gameControl.getCatanGame().getSelfPlayer().getUsername()
									+ " Heeft een straat gebouwd tussen X: "
									+ slb.getStreetLocation().getBlStart().getXLoc() + " Y: "
									+ slb.getStreetLocation().getBlStart().getYLoc() + " en X: "
									+ slb.getStreetLocation().getBlEnd().getXLoc() + " Y: "
									+ slb.getStreetLocation().getBlEnd().getYLoc());
							boardPanel.disableStreetLocButtons();
							playerActionPanel.setBuildPanel();
							addPlayerColorToStreetLocs();
							gameGUIPanel.getResourcesPanel().updateResourcesAmount();
							updatePlayerStats();
						}
					}
				}
			});
		}
	}

	private void addRollButtonListener() {

		diceDotPanel.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				gameControl.rollDice();
				diceDotPanel.getButton().setVisible(false);
				refreshDice();
			}
		});
	}

	public void enableDice() {
		diceDotPanel.getButton().setVisible(true);
		System.out.println("enabled button");
	}

	private void addDevelopmentCardsPanelButtonListeners() {
		ArrayList<DevelopmentCardButton> developmentCards = developmentCardsPanel.getDevelopmentCards();
		for (DevelopmentCardButton b : developmentCards) {
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DevelopmentCardDialogPanel developmentCardDialogPanel = new DevelopmentCardDialogPanel(b);
					JDialog dialog = new JDialog();
					dialog.setTitle("Ontwikkelingskaart");
					dialog.setContentPane(developmentCardDialogPanel); // TODO add actionlisteners for playbutton
					dialog.pack();
					dialog.setLocationRelativeTo(null);
					dialog.toFront();
					dialog.requestFocus();
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
			});
		}
	}

	private void addPlayerActionBuyButtonListener() {
		playerActionPanel.getPlayerOptionMenuPanel().getBuyButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setBuyPanel();
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

	public void showTradeReceiveDialog(TradeRequest tr) {

		TradeReceiveDialog tradeReceive = new TradeReceiveDialog(gameControl.getCatanGame().getSelfPlayer(), tr);

		tradeReceive.pack();
		tradeReceive.setLocationRelativeTo(null);
		tradeReceive.setVisible(true);

		tradeReceive.getTradeReceiveDialogPanel().getCounterOfferButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gameGUIPanel.getGameTopPanel().getGoToMainMenuButton().setEnabled(false);

				int brickGive = tradeReceive.getTradeReceiveDialogPanel().getBrickGive();
				int woolGive = tradeReceive.getTradeReceiveDialogPanel().getWoolGive();
				int ironGive = tradeReceive.getTradeReceiveDialogPanel().getIronGive();
				int wheatGive = tradeReceive.getTradeReceiveDialogPanel().getWheatGive();
				int woodGive = tradeReceive.getTradeReceiveDialogPanel().getWoodGive();

				int brickReceive = tradeReceive.getTradeReceiveDialogPanel().getBrickReceive();
				int woolReceive = tradeReceive.getTradeReceiveDialogPanel().getWoolReceive();
				int ironReceive = tradeReceive.getTradeReceiveDialogPanel().getIronReceive();
				int wheatReceive = tradeReceive.getTradeReceiveDialogPanel().getWheatReceive();
				int woodReceive = tradeReceive.getTradeReceiveDialogPanel().getWoodReceive();

				HashMap<ResourceType, Integer> currentHand = gameControl.getCatanGame().getSelfPlayer().getHand()
						.getAmountOfResources();

				if (currentHand.get(ResourceType.BAKSTEEN).intValue() < brickGive
						|| currentHand.get(ResourceType.WOL).intValue() < woolReceive
						|| currentHand.get(ResourceType.ERTS).intValue() < ironReceive
						|| currentHand.get(ResourceType.GRAAN).intValue() < wheatReceive
						|| currentHand.get(ResourceType.HOUT).intValue() < woodReceive) {

					tradeReceive.setAlwaysOnTop(false);

					Object[] options = { "Oke" };

					int result = JOptionPane.showOptionDialog(null, "Je hebt niet genoeg grondstoffen", "Waarschuwing",
							JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);

					if (result == JOptionPane.CANCEL_OPTION) {
						tradeReceive.setAlwaysOnTop(true);
					}

				} else {

					TradeRequest newTradeRequest = new TradeRequest(
							gameControl.getCatanGame().getSelfPlayer().getIdPlayer(), brickGive, woolGive, ironGive,
							wheatGive, woodGive, brickReceive, woolReceive, ironReceive, wheatReceive, woodReceive, 1);

					gameControl.acceptTradeRequest(newTradeRequest);
					gameControl.addLogMessage(
							gameControl.getCatanGame().getSelfPlayer().getUsername() + " heeft het handelsaanbod van "
									+ gameControl.getCatanGame().getPlayerByID(tr.getIdPlayer()).getUsername()
									+ " geaccepteerd");
					// gameControl.setShouldRefreshEnabled(tr.getIdPlayer());
					tradeReceive.dispose();
					gameGUIPanel.getGameTopPanel().getGoToMainMenuButton().setEnabled(true);
				}
			}
		});

		tradeReceive.getTradeReceiveDialogPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				TradeRequest declineRequest = new TradeRequest(gameControl.getCatanGame().getSelfPlayer().getIdPlayer(),
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
				gameControl.declineTradeRequest(declineRequest);
				gameControl.addLogMessage(gameControl.getCatanGame().getSelfPlayer().getUsername()
						+ " heeft het handelsaanbod van"
						+ gameControl.getCatanGame().getPlayerByID(tr.getIdPlayer()).getUsername() + " afgewezen");
				// gameControl.setShouldRefreshEnabled(tr.getIdPlayer());
				tradeReceive.dispose();
			}
		});
	}

	private void addTradeButtonsListeners() {

		playerActionPanel.getPlayerOptionMenuPanel().getTradeButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();
			}
		});

		playerActionPanel.getTradeOptionsPanel().getBankButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int[] resources = gameControl.getResourceRatios();
				playerActionPanel.getTradeBankPanel().updateRatio(resources);
				playerActionPanel.setTradeBankPanel();
			}
		});

		playerActionPanel.getTradeOptionsPanel().getPlayerButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradePlayerPanel();
				System.out.println("trade1");

			}
		});

		playerActionPanel.getTradeOptionsPanel().getBackButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setPlayerOptionMenuPanel();
				System.out.println("trade2");
			}
		});

		playerActionPanel.getTradeOptionsPanel().getRequestsButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("trade3");
				playerActionPanel.setTradeRequestListPanel();
			}
		});

		playerActionPanel.getPlayerTradePanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();
			}
		});

		playerActionPanel.getTradeBankPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();
			}
		});

		playerActionPanel.getTradeBankPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();
			}
		});

		playerActionPanel.getTradeBankPanel().getSendRequestButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int[] resourceRatios = gameControl.getResourceRatios();
				ResourceType resourceTypeToGive = playerActionPanel.getTradeBankPanel()
						.getSelectedResourceType(playerActionPanel.getTradeBankPanel().getGiveButtonGroup());
				ResourceType resourceTypeToReceive = playerActionPanel.getTradeBankPanel()
						.getSelectedResourceType(playerActionPanel.getTradeBankPanel().getReceiveButtonGroup());

				gameControl.getBankTradeRequest(resourceRatios, resourceTypeToGive, resourceTypeToReceive);

				gameGUIPanel.getResourcesPanel().updateResourcesAmount();

				updatePlayerStats();

			}
		});

		playerActionPanel.getTradeRequestListPanel().getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playerActionPanel.setTradeOptionsPanel();
			}

		});
	}

	private void addPlayerActionSendTradeRequestButtonListener() {
		playerActionPanel.getPlayerTradePanel().getSendRequestButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int stoneGive = playerActionPanel.getPlayerTradePanel().getBrickGive();
				int woolGive = playerActionPanel.getPlayerTradePanel().getWoolGive();
				int ironGive = playerActionPanel.getPlayerTradePanel().getIronGive();
				int wheatGive = playerActionPanel.getPlayerTradePanel().getWheatGive();
				int woodGive = playerActionPanel.getPlayerTradePanel().getWoodGive();

				int stoneReceive = playerActionPanel.getPlayerTradePanel().getBrickReceive();
				int woolReceive = playerActionPanel.getPlayerTradePanel().getWoolReceive();
				int ironReceive = playerActionPanel.getPlayerTradePanel().getIronReceive();
				int wheatReceive = playerActionPanel.getPlayerTradePanel().getWheatReceive();
				int woodReceive = playerActionPanel.getPlayerTradePanel().getWoodReceive();

				System.out.println(stoneGive + woolGive + ironGive + wheatGive + woodGive + stoneReceive + woolReceive
						+ ironReceive + wheatReceive + woodReceive);
				gameControl.createPlayerTradeRequest(stoneGive, woolGive, ironGive, wheatGive, woodGive, stoneReceive,
						woolReceive, ironReceive, wheatReceive, woodReceive);

				gameControl.countTradeOffers();
				playerActionPanel.setPlayerOptionMenuPanel();
			}
		});
	}

	public void fillTradeRequest() {
		ArrayList<TradeRequest> tradeRequestArr = gameControl.getCatanGame().getTradeRequestArr();
		TradeRespondDialog tradeRespond = new TradeRespondDialog(tradeRequestArr.get(0), tradeRequestArr.get(1),
				tradeRequestArr.get(2));

		gameGUIPanel.getGameTopPanel().getGoToMainMenuButton().setEnabled(false);
		disablePanelButtons();

		tradeRespond.pack();
		tradeRespond.setLocationRelativeTo(null);
		addTradeRespondDialogActionListeners(tradeRespond);
		tradeRespond.setVisible(true);
	}

	private void addTradeRespondDialogActionListeners(TradeRespondDialog tradeRespond) {

		tradeRespond.getTradeRespondPanels().getTradeRespondDialogPanel1().getSendRequestButton()
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						gameControl.commenceTrade(0);
						refreshPlayerResources();
						tradeRespond.dispose();

						gameGUIPanel.getGameTopPanel().getGoToMainMenuButton().setEnabled(true);
						enablePanelButtons();
					}
				});

		tradeRespond.getTradeRespondPanels().getTradeRespondDialogPanel2().getSendRequestButton()
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						gameControl.commenceTrade(1);
						refreshPlayerResources();

						gameGUIPanel.getGameTopPanel().getGoToMainMenuButton().setEnabled(true);
						enablePanelButtons();

						tradeRespond.dispose();
					}
				});

		tradeRespond.getTradeRespondPanels().getTradeRespondDialogPanel3().getSendRequestButton()
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						gameControl.commenceTrade(2);
						refreshPlayerResources();

						gameGUIPanel.getGameTopPanel().getGoToMainMenuButton().setEnabled(true);
						enablePanelButtons();

						tradeRespond.dispose();
					}
				});

		tradeRespond.getTradeRespondPanels().getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gameControl.deleteTradeRequest();

				gameGUIPanel.getGameTopPanel().getGoToMainMenuButton().setEnabled(true);
				enablePanelButtons();

				tradeRespond.dispose();
			}
		});
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
				// villageBoolean = true;

				boardPanel.enableBuildingLocButtons(false);
				playerActionPanel.setReturnToBuildPanel();
			}
		});

		playerActionPanel.getBuildPanel().getCityButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// cityBoolean = true;
				boardPanel.enableBuildingLocButtons(true);

				playerActionPanel.setReturnToBuildPanel();
				System.out.println("test");
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

				Object[] options = { "Ja", "Nee" };

				int result = JOptionPane.showOptionDialog(null, "Weet je zeker dat je wilt stoppen met bouwen?",
						"Waarschuwing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);
				if (result == JOptionPane.YES_OPTION) {
					boardPanel.disableStreetLocButtons();
					boardPanel.disableBuildingLocButtons();
					playerActionPanel.setBuildPanel();
				}
				if (gameControl.getCatanGame().isSelfPlayerTurn()) {
				}

			}
		});
	}

	private void addPlayerActionEndTurnButtonListener() {

		playerActionPanel.getPlayerOptionMenuPanel().getEndTurnButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				playerActionPanel.setVisible(false);
				gameControl.endTurn();
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

	public void enablePlayerActionPanel() {
		playerActionPanel.setVisible(true);
	}
	public void disablePlayerActionPanel() {
		playerActionPanel.setVisible(false);
	}

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

//	public void refreshPlayers() {
//		
//	}

	private void addListeners() {

		// board listeners
		addTileListeners();
		addBuildLocListeners();
		addStreetLocListeners();
		addRollButtonListener();
		addPlayerColorToBuildingLocs();

		// developmentCardPanelButton listeners
		addDevelopmentCardsPanelButtonListeners();

		// buy listeners
		addPlayerActionBuyButtonListener();
		addPlayerActionBuyQuitButtonListener();

		// Trade listeners
		addTradeButtonsListeners();

		addPlayerActionSendTradeRequestButtonListener();

		// build listeners
		addPlayerActionBuildButtonsListener();
		addBuildBackButtonListener();

		// end turn listener
		addPlayerActionEndTurnButtonListener();

	}

	// public void setGameBoard(Gameboard gameBoard) {
	// this.gameBoard = gameBoard;
	// }

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public void createStealDialog(ArrayList<Player> playersAtRobberTile) {

	}

	private void updatePlayerStats() {

		for (int i = 0; i < playerStatsPanels.length; i++) {
			playerStatsPanels[i].updateStats();
		}
	}

	private void enablePanelButtons() {
		playerOptionMenuPanel.getBuildButton().setEnabled(true);
		playerOptionMenuPanel.getBuyButton().setEnabled(true);
		playerOptionMenuPanel.getTradeButton().setEnabled(true);
		playerOptionMenuPanel.getEndTurnButton().setEnabled(true);
	}

	private void disablePanelButtons() {
		playerOptionMenuPanel.getBuildButton().setEnabled(false);
		playerOptionMenuPanel.getBuyButton().setEnabled(false);
		playerOptionMenuPanel.getTradeButton().setEnabled(false);
		playerOptionMenuPanel.getEndTurnButton().setEnabled(false);
	}

}
