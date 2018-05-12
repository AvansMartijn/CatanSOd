package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.BuildingLocation;
import model.Gameboard;
import model.ResourceType;
import model.Tile;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private Gameboard gameBoard;
	private final int panelWidth = 800;
	private final int panelHeight = 750;

	private final int tileWidth = 150;
	private final int tileHeight = 150;
	private final int tileImgWidth = 134;
	private final int tileImgHeight = 152;
	private final int buildingLocWidthHeight = 30;

	private final int tilesRow1 = 3;
	private final int tilesRow2 = 4;
	private final int tilesRow3 = 5;
	private final int tilesRow4 = 4;
	private final int tilesRow5 = 3;
	private final int totalTiles = tilesRow1 + tilesRow2 + tilesRow3 + tilesRow4 + tilesRow5;

	private int[] lengtesRijen = new int[] { tilesRow1, tilesRow2, tilesRow3, tilesRow4, tilesRow5 };
	// private JLabel[] myTileLabels = new JLabel[totalTiles];
	private TileButton[] hexArray = new TileButton[totalTiles];
	private BuildingLocationButton[] buildingLocArray = new BuildingLocationButton[totalTiles];

	private ArrayList<Tile> tileArr = new ArrayList<Tile>();
	private ArrayList<BuildingLocation> buildingLocationArray = new ArrayList<BuildingLocation>();

	public BoardPanel(Gameboard gameboard) {
		this.gameBoard = gameboard;
		tileArr = gameBoard.getTileArr();
		buildingLocationArray = gameBoard.getBuildingLocArr();
		setLayout(null);
		// setLayout(new GridBagLayout());
		setBackground(Color.BLUE); // TODO make it some cool image
		setPreferredSize(new Dimension(panelWidth, panelHeight));
		createTiles();
	}

	// Create tiles and draw them
	private void createTiles() {
		int position = 0;
		int[] values_x = new int[] { 150, 85, 20, 85, 150 };
		int[] values_y = new int[] { 50, 162, 274, 386, 498 };

		int[] idArr = new int[] { 6, 11, 16, 3, 8, 13, 18, 1, 5, 10, 15, 19, 2, 7, 12, 17, 4, 9, 14 };
		for (int i = 0; i < lengtesRijen.length; i++) {

			for (int j = 0; j < lengtesRijen[i]; j++) {

				Tile tile = tileArr.get(idArr[position] - 1);
				TileButton tileButton = new TileButton(getResourceIcon(tile.getRsType().toString()), tile.getChipNumber(), tile);

				tileButton.setLocation(values_x[i] + ((tileWidth + 130 * j) - 100), values_y[i]);
				tileButton.setSize(tileWidth, tileHeight);
				add(tileButton, -1);
				position++;
			}
		}
		// loop through tiles
		// for each tile create and set a tilebutton using pixel locations in predefined
		// array
		// for each tile loop through all its building locations
		// check if building location already has a buildinglocbutton, if it doesn't
		// create and add it.
		// for each tile loop through all streetlocations
		// do shit

		// TODO: REWRITE using intarray with paintlocation values of tiles
		// TODO: ADD intarray with buildinglocations to draw

//		int tileNumber = 0;
//		// Voor het aantal rijen van Tiles
//		for (int i = 0; i < lengtesRijen.length; i++) {
//			// Voor het aantal tiles in een rij
//			for (int j = 0; j < lengtesRijen[i]; j++) {
//				Image image = null;
//				// TileButton hexButton = null;
//				try {
//					URL url = this.getClass().getResource("/images/Iron_Tile.png"); // TODO change this to
//																					// getTileType(tileNumber);
//					image = ImageIO.read(url);
//					image = image.getScaledInstance(tileImgWidth, tileImgHeight, Image.SCALE_DEFAULT);
//				} catch (IOException e) {
//				}
//				// myTileLabels[tileNumber] = new JLabel(new ImageIcon(image));
//				hexArray[tileNumber] = new TileButton(new ImageIcon(image), j);
//				hexArray[tileNumber].addActionListener(new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						// TODO Auto-generated method stub
//						System.out.println("tile");
//					}
//
//				});
//
//				buildingLocArray[tileNumber] = new BuildingLocationButton();
//				buildingLocArray[tileNumber].addActionListener(new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						// TODO Auto-generated method stub
//						System.out.println("buildingLoc");
//					}
//
//				});
//
//				// add(myTileLabels[tileNumber]);
//				add(hexArray[tileNumber], -1);
//				add(buildingLocArray[tileNumber], 0);
//
//				// myTileLabels[tileNumber].setLocation(values_x[i] + (tileWidth * j),
//				// values_y[i]);
//				hexArray[tileNumber].setLocation(values_x[i] + ((tileWidth + 130 * j) - 100), values_y[i]);
//				buildingLocArray[tileNumber].setLocation(values_x[i] + ((tileWidth + 130 * j) - 105), values_y[i] + 20);
//
//				// myTileLabels[tileNumber].setSize(tileWidth, tileHeight);
//				hexArray[tileNumber].setSize(tileWidth, tileHeight);
//				buildingLocArray[tileNumber].setSize(buildingLocWidthHeight, buildingLocWidthHeight);
//				tileNumber++;
//
//			}
//		}
		// int count = 0;
		// for(TileButton h: hexArray) {
		// System.out.println(count);
		// h.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// System.out.println("test");
		//// System.out.println("Clicked tile:" + h.getX() + " " + h.getY());
		//
		// }
		// });
		// count++;
		//// h.setEnabled(false);
		// }

		// int counter = 0;
		// while(counter < hexArray.length) {
		// System.out.println("test1");
		// hexArray[counter].addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// // TODO Auto-generated method stub
		// System.out.println("test");
		// }
		//
		// });
		// counter++;
		// }

	}

	public void enableTiles() {
		for (TileButton h : hexArray) {
			h.setEnabled(true);
		}
	}

	public void disableTiles() {
		for (TileButton h : hexArray) {
			h.setEnabled(false);
		}
	}

	public void enableBuildingLocs() {
		for (BuildingLocationButton b : buildingLocArray) {
			b.setEnabled(true);
		}
	}

	public void disableBuildingLocs() {
		for (BuildingLocationButton b : buildingLocArray) {
			b.setEnabled(false);
		}
	}

	private ImageIcon createImageIcon(URL url) {
		Image image = null;
		try {

			image = ImageIO.read(url);
			image = image.getScaledInstance(tileImgWidth, tileImgHeight, Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
		return new ImageIcon(image);
	}

	// Get tileType
	private ImageIcon getResourceIcon(String rsType) {
		URL url = null;
		switch (rsType) {
		case "WOL":
			url = this.getClass().getResource("/images/Sheep_Tile.png"); 
			return createImageIcon(url);
		case "GRAAN":
			url = this.getClass().getResource("/images/Wheat_Tile.png");
			return createImageIcon(url);
		case "BAKSTEEN":
			url = this.getClass().getResource("/images/Stone_Tile.png");
			return createImageIcon(url);
		case "HOUT":
			url = this.getClass().getResource("/images/Wood_Tile.png");
			return createImageIcon(url);
		case "ERTS":
			url = this.getClass().getResource("/images/Iron_Tile.png");
			return createImageIcon(url);
		case "WOESTIJN":
			url = this.getClass().getResource("/images/Desert_Tile.png");
			return createImageIcon(url);
		default:
			return null;
		}
	}

	// private static final long serialVersionUID = 1L;
	// private static final int ROWS = 5;
	// private static final int COLUMNS = 5;
	// private HexagonButton[][] hexButton = new HexagonButton[ROWS][COLUMNS];
	//
	// public HexagonPattern() {
	// setLayout(null);
	// initGUI();
	// }
	//
	// public void initGUI() {
	// int offsetX = -10;
	// int offsetY = 0;
	//
	// for(int row = 0; row < ROWS; row++) {
	// for(int col = 0; col < COLUMNS; col++){
	// hexButton[row][col] = new HexagonButton(row, col);
	// hexButton[row][col].addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	// HexagonButton clickedButton = (HexagonButton) e.getSource();
	// System.out.println("Button clicked: [" + clickedButton.getRow() + "][" +
	// clickedButton.getCol() + "]");
	// }
	// });
	// add(hexButton[row][col]);
	// hexButton[row][col].setBounds(offsetY, offsetX, 105, 95);
	// offsetX += 87;
	// }
	// if(row%2 == 0) {
	// offsetX = -52;
	// } else {
	// offsetX = -10;
	// }
	// offsetY += 76;
	// }
	// }
}
