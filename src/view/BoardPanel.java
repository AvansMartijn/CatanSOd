package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.BuildingLocation;
import model.Gameboard;
import model.ResourceType;
import model.StreetLocation;
import model.Tile;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private Gameboard gameBoard;
	private final int panelWidth = 800;
	private final int panelHeight = 750;

	private final int streetWidth = 50;
	private final int streetHeight = 50;
	private final int tileWidth = 150;
	private final int tileHeight = 150;
	private final int tileImgWidth = 134;
	private final int tileImgHeight = 152;
	private final int buildingLocWidthHeight = 30;

	private final int amountOfTiles = 19;
	private final int amountOfBuildLoc = 54;
	// private final int totalTiles = tilesRow1 + tilesRow2 + tilesRow3 + tilesRow4
	// + tilesRow5;
	HashMap<Point, Point> cordMap;
	private int[] tilesPerRow = new int[] { 3, 4, 5, 4, 3 };
	private int[] buildingLocPerRow = new int[] { 2, 4, 6, 6, 6, 6, 6, 6, 6, 4, 2 };
	// private JLabel[] myTileLabels = new JLabel[totalTiles];

	private ArrayList<TileButton> tileButtonArrayList = new ArrayList<TileButton>();
	private ArrayList<BuildingLocationButton> buildingLocButtonArray = new ArrayList<BuildingLocationButton>();
	private ArrayList<StreetLocationButton> streetLocButtonArray = new ArrayList<StreetLocationButton>();

	private ArrayList<Tile> tileArr = new ArrayList<Tile>();
	private ArrayList<BuildingLocation> buildingLocArray = new ArrayList<BuildingLocation>();
	private ArrayList<StreetLocation> streetLocArray = new ArrayList<StreetLocation>();
	
	
	
	// TODO panel 10 breder maken
	

	public BoardPanel(Gameboard gameboard) {
		this.gameBoard = gameboard;
		tileArr = gameBoard.getTileArr();
		buildingLocArray = gameBoard.getBuildingLocArr();
		streetLocArray = gameBoard.getStreetLocArr();
		setLayout(null);
		// setLayout(new GridBagLayout());
		setBackground(Color.BLUE); // TODO make it some cool image
		setPreferredSize(new Dimension(panelWidth, panelHeight));
		createTiles();
	}
	
	private void addListeners() {
		for(TileButton b : tileButtonArrayList) {
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("X: " +  b.getX() + " Y: " + b.getY());
				}
				
			});
		}
	}

	// Create tiles and draw them
	private void createTiles() {
		int position = 0;
//		int[] values_x = new int[] {80, 145, 210, 275, 340, 405, 470, 535, 600, 665, 730};
//		int[] values_y = new int[] { 85, 50};
		cordMap = new HashMap<Point, Point>();
		fillCordMap();
		
		int[] tilevalues_x = new int[] { 150, 85, 20, 85, 150 };
		int[] tilevalues_y = new int[] { 50, 162, 274, 386, 498 };

		int[] idTileArr = new int[] { 6, 11, 16, 3, 8, 13, 18, 1, 5, 10, 15, 19, 2, 7, 12, 17, 4, 9, 14 };
		for (int i = 0; i < tilesPerRow.length; i++) {

			for (int j = 0; j < tilesPerRow[i]; j++) {
				Tile tile = tileArr.get(idTileArr[position] - 1);
				TileButton tileButton = new TileButton(getResourceIcon(tile.getRsType().toString()),
						tile.getChipNumber(), tile);

				tileButton.setLocation(tilevalues_x[i] + ((tileWidth + 130 * j) - 100), tilevalues_y[i]);
				tileButton.setSize(tileWidth, tileHeight);

				tileButtonArrayList.add(tileButton);
				add(tileButton, -1);

				position++;
			}

		}
		addListeners();
		
		for(BuildingLocation bl: buildingLocArray) {
			BuildingLocationButton blb = new BuildingLocationButton(bl);
			
			Point locPoint = cordMap.get(new Point(bl.getXLoc(), bl.getYLoc()));
			
			blb.setLocation((int) locPoint.getX() - buildingLocWidthHeight / 2, (int) locPoint.getY() - buildingLocWidthHeight / 2);
			blb.setSize(buildingLocWidthHeight, buildingLocWidthHeight);
			
			buildingLocButtonArray.add(blb);
			add(blb, 0);
		}
		
		for(StreetLocation sl: streetLocArray) {
			StreetLocationButton slb = new StreetLocationButton(sl);
			Point startlocPoint = cordMap.get(new Point(sl.getBlStart().getXLoc(), sl.getBlStart().getYLoc()));
//			Point endlocPoint = cordMap.get(new Point(sl.getBlEnd().getYLoc(), sl.getBlEnd().getYLoc()));
			
			slb.setLocation((int)startlocPoint.getX(), (int)startlocPoint.getY());
			slb.setSize(streetWidth, streetHeight);
			
			streetLocButtonArray.add(slb);
			add(slb, 0);
		}

	}

	private void fillCordMap() {
		cordMap.put(new Point(1,3), new Point(80, 386));
		cordMap.put(new Point(1,4), new Point(80, 310));
		cordMap.put(new Point(2,2), new Point(145, 498));
		cordMap.put(new Point(2,3), new Point(145, 424));
		cordMap.put(new Point(2,5), new Point(145, 274));
		cordMap.put(new Point(2,6), new Point(145, 198));
		cordMap.put(new Point(3,1), new Point(210, 610));
		cordMap.put(new Point(3,2), new Point(210, 535));
		cordMap.put(new Point(3,4), new Point(210, 386));
		cordMap.put(new Point(3,5), new Point(210, 310));
		cordMap.put(new Point(3,7), new Point(210, 162));
		cordMap.put(new Point(3,8), new Point(210, 86));
		cordMap.put(new Point(4,1), new Point(275, 645));
		cordMap.put(new Point(4,3), new Point(275, 498));
		cordMap.put(new Point(4,4), new Point(275, 424));
		cordMap.put(new Point(4,6), new Point(275, 274));
		cordMap.put(new Point(4,7), new Point(275, 198));
		cordMap.put(new Point(4,9), new Point(275, 50));
		cordMap.put(new Point(5,2), new Point(340, 610));
		cordMap.put(new Point(5,3), new Point(340, 535));
		cordMap.put(new Point(5,5), new Point(340, 386));
		cordMap.put(new Point(5,6), new Point(340, 310));
		cordMap.put(new Point(5,8), new Point(340, 162));
		cordMap.put(new Point(5,9), new Point(340, 86));
		cordMap.put(new Point(6,2), new Point(405, 645));
		cordMap.put(new Point(6,4), new Point(405, 498));
		cordMap.put(new Point(6,5), new Point(405, 424));
		cordMap.put(new Point(6,7), new Point(405, 274));
		cordMap.put(new Point(6,8), new Point(405, 198));
		cordMap.put(new Point(6,10), new Point(405, 50));
		cordMap.put(new Point(7,3), new Point(470, 610));
		cordMap.put(new Point(7,4), new Point(470, 535));
		cordMap.put(new Point(7,6), new Point(470, 386));
		cordMap.put(new Point(7,7), new Point(470, 310));
		cordMap.put(new Point(7,9), new Point(470, 162));
		cordMap.put(new Point(7,10), new Point(470, 86));
		cordMap.put(new Point(8,3), new Point(535, 645));
		cordMap.put(new Point(8,5), new Point(535, 498));
		cordMap.put(new Point(8,6), new Point(535, 424));
		cordMap.put(new Point(8,8), new Point(535, 274));
		cordMap.put(new Point(8,9), new Point(535, 198));
		cordMap.put(new Point(8,11), new Point(535, 50));
		cordMap.put(new Point(9,4), new Point(600, 610));
		cordMap.put(new Point(9,5), new Point(600, 535));
		cordMap.put(new Point(9,7), new Point(600, 386));
		cordMap.put(new Point(9,8), new Point(600, 310));
		cordMap.put(new Point(9,10), new Point(600, 162));
		cordMap.put(new Point(9,11), new Point(600, 86));
		cordMap.put(new Point(10,6), new Point(665, 498));
		cordMap.put(new Point(10,7), new Point(665, 424));
		cordMap.put(new Point(10,9), new Point(665, 274));
		cordMap.put(new Point(10,10), new Point(665, 198));
		cordMap.put(new Point(11,8), new Point(730, 386));
		cordMap.put(new Point(11,9), new Point(730, 310));
	}

	public void enableTiles() {
		for (TileButton h : tileButtonArrayList) {
			h.setEnabled(true);
		}
	}

	public void disableTiles() {
		for (TileButton h : tileButtonArrayList) {
			h.setEnabled(false);
		}
	}

	public void enableBuildingLocs() {
		for (BuildingLocationButton b : buildingLocButtonArray) {
			b.setEnabled(true);
		}
	}

	public void disableBuildingLocs() {
		for (BuildingLocationButton b : buildingLocButtonArray) {
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
