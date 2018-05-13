package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Gameboard;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import view.BoardPanel;
import view.ChatPanel;
import view.DicePanel;
import view.Frame;
import view.GameGUIPanel;
import view.LoginRegisterPanel;

public class GuiController {

	private Player player;
	private GameGUIPanel gameGUIPanel;
	private BoardPanel boardPanel;
	private DicePanel dicePanel;
	private ChatPanel chatPanel;
	private Frame frame;
	private MainControl mainControl;
	private GameControl gameControl;
	private Gameboard gameBoard;

	public GuiController(MainControl mainControl, GameControl gameControl, Gameboard gameBoard) {
		this.mainControl = mainControl;
		this.gameControl = gameControl;
		player = gameControl.getPlayer();
		frame = new Frame();
		this.gameBoard = gameBoard;
		this.dicePanel = new DicePanel();
		this.chatPanel = new ChatPanel();
		boardPanel = new BoardPanel(gameBoard);
		gameGUIPanel = new GameGUIPanel(new Player(724, "BerendBrokkepap", PlayerColor.ROOD, 3, PlayStatus.UITGEDAAGDE), boardPanel, dicePanel, chatPanel);
		frame.setContentPane(gameGUIPanel);

//		frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.pack();
		frame.setVisible(true);
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
				
				if(!mainControl.loginAccount(username, password)) {
					usernameTextField.setText("");
					passwordTextField.setText("");
					loginregisterPanel.setMessagelabel("Invalid Credentials");
					frame.pack();
				} else {
					setBoardPanel();
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
				
				if(hasValidInput(username) && hasValidInput(password)) {
					if(!mainControl.createAccount(username, password)) {
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
	
//	public void setGamePanel() {
//		gameGUIPanel = new GameGUIPanel(player, gameBoard);
//		frame.setContentPane(gameGUIPanel);
//	}
	
	public void setBoardPanel() {
		boardPanel = new BoardPanel(gameControl.getGameboard());
		frame.setContentPane(boardPanel);
	}
	
}
