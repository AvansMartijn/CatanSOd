package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Dice;
import model.Gameboard;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import model.Tile;
import model.Village;
import view.BoardPanel;
import view.BuildingLocationButton;
import view.ChatPanel;
import view.DiceDotPanel;
import view.DicePanel;
import view.Frame;
import view.GameGUIPanel;
import view.LoginRegisterPanel;
import view.StreetLocationButton;
import view.TileButton;

public class GuiController {

	private Player player;
	private GameGUIPanel gameGUIPanel;
	private BoardPanel boardPanel;
	private DiceDotPanel dicePanel;
	private ChatPanel chatPanel;
	private Frame frame;
	private MainControl mainControl;
	private GameControl gameControl;
	private Gameboard gameBoard;
	private Timer timer;

	public GuiController(MainControl mainControl, GameControl gameControl) {
		this.mainControl = mainControl;
		this.gameControl = gameControl;

		frame = new Frame();

		setInlogPanel();
		// frame.setContentPane(gameGUIPanel);
		// frame.setPreferredSize(new
		// Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
//		frame.setUndecorated(true);

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
					player = gameControl.getPlayer();
					setIngameGuiPanel();
					frame.pack();
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
//		boardPanel = new BoardPanel(gameBoard);
		this.chatPanel = new ChatPanel(gameControl.getMessages());
		this.dicePanel = new DiceDotPanel();
		boardPanel = new BoardPanel(gameControl.getGameboard());
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
		gameGUIPanel = new GameGUIPanel(player, boardPanel, dicePanel, chatPanel);
		frame.setContentPane(gameGUIPanel);
		addTileListeners();
		addBuildLocListeners();
		addStreetLocListeners();
		addRollButtonListener();
		addPlayerColorToBuildingLocs();
		
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				refresh();
				chatPanel.setMessages(gameControl.getMessages());
			}

		}, 0, 5000);

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
					if(!gameControl.buildVillage(blb.getBuildingLocation())) {
						System.out.println("Je kan hier niet bouwen");
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
					System.out.println(slb.getStreetLocation().getBlStart());

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
			Village village = blb.getBuildingLocation().getVillage();
			if(village != null) {
				
					Color color = Color.BLACK;					
			        switch (village.getPlayer().getColor()) {
			            case ROOD:  color = Color.RED;
			                     break;
			            case WIT:  color = Color.WHITE;
			                     break;
			            case BLAUW:  color = Color.BLUE;
			                     break;
			            case ORANJE:  color = Color.ORANGE;
			                     break;	          
			        
			        }
			       blb.setBackground(color);
			    
			}
		}
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
