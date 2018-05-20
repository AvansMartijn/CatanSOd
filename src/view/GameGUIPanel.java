package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import model.Player;

@SuppressWarnings("serial")
public class GameGUIPanel extends JPanel {

	// Instance variables
	private Color myBackGroundColor = new Color(240, 226, 223);
	private GameTopPanel gameTopPanel;
	private PlayerActionPanel playerActionsPanel;
	private MyResourcesPanel resourcesPanel;
	private BoardPanel boardPanel;
	private DicePanel dicePanel;
	private ChatPanel chatPanel;
	private GameSouthContainerPanel gameSouthContainerPanel;
	private BuyDialog buyDialog;
	private BuildDialog buildDialog;
	private TradeDialog tradeDialog;

	
	private GridBagConstraints gridBagConstraints;
	// private Player player;

	// Constructor
	public GameGUIPanel(GameTopPanel gameTopPanel, BoardPanel boardPanel, DicePanel dicePanel, ChatPanel chatPanel,
			PlayerActionPanel playerActionPanel, GameSouthContainerPanel gameSouthContainerPanel, Player selfPlayer) { // TODO
																														// array
																														// of
																														// players
																														// as
																														// you
																														// need
																														// 4
																														// playerStatsPanels?
		// this.player = player;
		setBackground(myBackGroundColor);
		setLayout(new GridBagLayout());
		this.gameTopPanel = gameTopPanel; // TODO set gamelabel text in constructor
		this.playerActionsPanel = playerActionPanel;
		resourcesPanel = new MyResourcesPanel(selfPlayer); // TODO selfplayer
		this.boardPanel = boardPanel;
		this.dicePanel = dicePanel;
		this.chatPanel = chatPanel;
		this.gameSouthContainerPanel = gameSouthContainerPanel; // TODO create array of players in constructor
		createLayout();
	}

	// Create layout
	private void createLayout() {
		// Add Top Panel
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.gridwidth = 5;
		add(gameTopPanel, gridBagConstraints);

		// Add ChatPanel
		gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		gridBagConstraints.gridy++;
		add(chatPanel, gridBagConstraints);

		// Add Board Panels
		gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
		gridBagConstraints.gridx++;
		add(boardPanel, gridBagConstraints);

		// Add PlayerActionsPanel // TODO only if its this players turn
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(200, 0, 0, 100);
		add(playerActionsPanel, gridBagConstraints);

		// Add DicePanel
		gridBagConstraints.insets = new Insets(0, 0, 500, 150);
		add(dicePanel, gridBagConstraints);

		// Add resourcesPanel
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
		gridBagConstraints.gridy++;
		add(resourcesPanel, gridBagConstraints);

		// Add GameSouthContainerPanel
		gridBagConstraints.gridy++;
		gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
		add(gameSouthContainerPanel, gridBagConstraints);
	}

	public void setGameboardPanel() {
		//TODO: what goes here?
	}

	public JPanel getPlayerActionPanel() {
		return playerActionsPanel;
	}

	public void showBuyDialog(BuyDialog newBuyDialog) {
		buyDialog = newBuyDialog;
		add(buyDialog);
	}

	public void showBuildDialog(BuildDialog newBuildDialog) {
		buildDialog = newBuildDialog;
		add(buildDialog);
	}

	public void showTradeDialog(TradeDialog newTradeDialog) {
		tradeDialog = newTradeDialog;
		add(tradeDialog);
	}

	public void removeBuyDialog() {

	}

	public void removeBuildDialog() {

	}

	public void removeTradeDialog() {

	}
	
}
