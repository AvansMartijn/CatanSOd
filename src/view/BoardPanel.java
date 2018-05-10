package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	private final int PANEL_WIDTH = 700;
	private final int PANEL_HEIGHT = 700;

	private final int TILE_WIDTH = 130;
	private final int TILE_HEIGHT = 150;

	private final int AANTAL_TILES_RIJ1 = 3;
	private final int AANTAL_TILES_RIJ2 = 4;
	private final int AANTAL_TILES_RIJ3 = 5;
	private final int AANTAL_TILES_RIJ4 = 4;
	private final int AANTAL_TILES_RIJ5 = 3;

	private final int AANTAL_TILES_TOTAAL = AANTAL_TILES_RIJ1 + AANTAL_TILES_RIJ2 + AANTAL_TILES_RIJ3
			+ AANTAL_TILES_RIJ4 + AANTAL_TILES_RIJ5;

	// Instance variables
	private int[] lengtesRijen = new int[] { AANTAL_TILES_RIJ1, AANTAL_TILES_RIJ2, AANTAL_TILES_RIJ3, AANTAL_TILES_RIJ4,
			AANTAL_TILES_RIJ5 };
	private JLabel[] myTileLabels = new JLabel[AANTAL_TILES_TOTAAL];

	// Constructor
	public BoardPanel() {
		setLayout(null);
		setBackground(Color.BLUE); // TODO make it some cool image
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
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
				Image image = null;
				try {
					URL url = this.getClass().getResource("/images/Wood_Tile.png"); // TODO change this to getTileType(tileNumber);
					image = ImageIO.read(url);
					image = image.getScaledInstance(TILE_WIDTH, TILE_HEIGHT, Image.SCALE_DEFAULT);
				} catch (IOException e) {
				}
				myTileLabels[tileNumber] = new JLabel(new ImageIcon(image));
				add(myTileLabels[tileNumber]);

				myTileLabels[tileNumber].setLocation(values_x[i] + (TILE_WIDTH * j), values_y[i]);
				myTileLabels[tileNumber].setSize(TILE_WIDTH, TILE_HEIGHT);
				tileNumber++;
			}
		}
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
