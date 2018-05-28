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
	private PlayerActionPanel playerActionPanel;
	private ResourcesPanel resourcesPanel;
	private BoardPanel boardPanel;
	private DiceDotPanel diceDotPanel;
	private ChatPanel chatPanel;
	private GameSouthContainerPanel gameSouthContainerPanel;
	private GridBagConstraints gridBagConstraints;

	// Constructor
	public GameGUIPanel(GameTopPanel gameTopPanel, BoardPanel boardPanel, DiceDotPanel diceDotPanel, ChatPanel chatPanel,
			PlayerActionPanel playerActionPanel, GameSouthContainerPanel gameSouthContainerPanel, Player selfPlayer) {

		setBackground(myBackGroundColor);
		setLayout(new GridBagLayout());
		this.gameTopPanel = gameTopPanel;
		this.playerActionPanel = playerActionPanel;
		resourcesPanel = new ResourcesPanel(selfPlayer);
		this.boardPanel = boardPanel;
		this.diceDotPanel = diceDotPanel;
		this.chatPanel = chatPanel;
		this.gameSouthContainerPanel = gameSouthContainerPanel;
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

		// Add PlayerOptionMenuPanel // TODO only if its this players turn
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(200, 0, 0, 100);
		add(playerActionPanel, gridBagConstraints);

		// Add DicePanel
		gridBagConstraints.insets = new Insets(0, 0, 500, 150);
		add(diceDotPanel, gridBagConstraints);

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

	public JPanel getPlayerActionPanel() {
		return playerActionPanel;
	}

	public ResourcesPanel getResourcesPanel() {
		return resourcesPanel;
	}

	public GameSouthContainerPanel getGameSouthContainerPanel() {
		return gameSouthContainerPanel;
	}

	public GameTopPanel getGameTopPanel() {
		return gameTopPanel;
	}
	
}
