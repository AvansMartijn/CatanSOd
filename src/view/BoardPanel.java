package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Gameboard;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	Gameboard gameboard;
	private final int panelWidth = 700;
	private final int panelHeight = 700;

	private final int tileWidth = 130;
	private final int tileHeight = 150;

	private final int tilesRow1 = 3;
	private final int tilesRow2 = 4;
	private final int tilesRow3 = 5;
	private final int tilesRow4 = 4;
	private final int tilesRow5 = 3;
	private final int totalTiles = tilesRow1 + tilesRow2 + tilesRow3 + tilesRow4 + tilesRow5;

	private int[] lengtesRijen = new int[] { tilesRow1, tilesRow2, tilesRow3, tilesRow4,
			tilesRow5 };
	private JLabel[] myTileLabels = new JLabel[totalTiles];

	public BoardPanel(Gameboard gameboard) {
		this.gameboard = gameboard;
		setLayout(null);
		setBackground(Color.BLUE); // TODO make it some cool image
		setPreferredSize(new Dimension(panelWidth, panelHeight));
		createTiles();
	}

	// Create tiles and draw them
	private void createTiles() {
		int tileNumber = 0;

		int[] values_x = new int[] { 150, 85, 20, 85, 150 };
		int[] values_y = new int[] { 50, 162, 274, 386, 498 };

		// Voor het aantal rijen van Tiles
		for (int i = 0; i < lengtesRijen.length; i++) {
			// Voor het aantal tiles in een rij
			for (int j = 0; j < lengtesRijen[i]; j++) {
//				Image image = null;
//				try {
//					URL url = this.getClass().getResource("/images/Wood_Tile.png"); // TODO change this to getTileType(tileNumber);
//					image = ImageIO.read(url);
//					image = image.getScaledInstance(tileWidth, tileHeight, Image.SCALE_DEFAULT);
//				} catch (IOException e) {
//				}
//				myTileLabels[tileNumber] = new JLabel(new ImageIcon(image));
//				add(myTileLabels[tileNumber]);
//
//				myTileLabels[tileNumber].setLocation(values_x[i] + (tileWidth * j), values_y[i]);
//				myTileLabels[tileNumber].setSize(tileWidth, tileHeight);
//				tileNumber++;
			}
		}
		add(new TilePanel(1, 1));
	}

	/*
	// Get tileType
	private URL getTileType(int tileNumber) {
		GameBoardControl gameBoardControl;
		String code = gameBoardControl.tiles.get(tileNumber).getRsType();

		switch (code) {
		case "WOOL":
			return this.getClass().getResource("/images/Sheep_Tile.png");
			break;
		case "WHEAT":
			return this.getClass().getResource("/images/Wheat_Tile.png");
			break;
		case "STONE":
			return this.getClass().getResource("/images/Stone_Tile.png");
			break;
		case "WOOD":
			return this.getClass().getResource("/images/Wood_Tile.png");
			break;
		case "IRON":
			return this.getClass().getResource("/images/Iron_Tile.png");
			break;
		case "DESERT":
			return this.getClass().getResource("/images/Desert_Tile.png");
			break;
			default:
				System.out.println("No tileType found");
		}
	}
	*/
}
