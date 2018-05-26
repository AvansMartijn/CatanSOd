package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.BuildingLocation;
import model.Gameboard;
import model.StreetLocation;
import model.Tile;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private Gameboard gameBoard;
	private final int panelWidth = 810;
	private final int panelHeight = 700;
	private Color backGroundColor = new Color(240, 226, 223);

	private final int streetWidth = 20;
	private final int streetHeight = 20;
	private final int tileWidth = 150;
	private final int tileHeight = 150;
	private final int tileImgWidth = 134;
	private final int tileImgHeight = 152;
	private final int buildingLocWidthHeight = 30;

	HashMap<Point, Point> cordMap;

	private ArrayList<TileButton> tileButtonArrayList = new ArrayList<TileButton>();
	private ArrayList<BuildingLocationButton> buildingLocButtonArray = new ArrayList<BuildingLocationButton>();
	private ArrayList<StreetLocationButton> streetLocButtonArray = new ArrayList<StreetLocationButton>();

	private ArrayList<Tile> tileArr = new ArrayList<Tile>();
	private ArrayList<BuildingLocation> buildingLocArray = new ArrayList<BuildingLocation>();
	private ArrayList<StreetLocation> streetLocArray = new ArrayList<StreetLocation>();

	public BoardPanel(Gameboard gameboard) {
		this.gameBoard = gameboard;
		tileArr = gameBoard.getTileArr();
		buildingLocArray = gameBoard.getBuildingLocArr();
		streetLocArray = gameBoard.getStreetLocArr();
		setLayout(null);
		
		// TEST
		setBackground(backGroundColor);
		URL url = this.getClass().getResource("/images/ocean.png");
		
		Image image = null;
		try {
			image = ImageIO.read(url);
			image = image.getScaledInstance(panelWidth, panelHeight, Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
		JLabel background = new JLabel();
		ImageIcon icon = new ImageIcon(url);
		background.setIcon(icon);
		background.setBounds(0, 0, panelWidth, panelHeight);
		add(background, -1);
		// END TEST

		setPreferredSize(new Dimension(panelWidth, panelHeight));
		createTiles();
	}
	


	

	public ArrayList<TileButton> getTileButtonArrayList() {
		return tileButtonArrayList;
	}
	
	public ArrayList<BuildingLocationButton> getBuildingLocationButtonArrayList(){
		return buildingLocButtonArray;
	}
	
	public ArrayList<StreetLocationButton> getStreetLocationButtonArrayList(){
		return streetLocButtonArray;
	}




	// Create tiles and draw them
	private void createTiles() {

		cordMap = new HashMap<Point, Point>();
		fillCordMap();

		for (Tile t : tileArr) {
			TileButton tileButton = new TileButton(getResourceIcon(t.getRsType().toString()), t.getChipNumber(), t);

			Point locPoint = cordMap.get(new Point(t.getX(), t.getY()));

			tileButton.setLocation((int) locPoint.getX(), (int) locPoint.getY());
			tileButton.setSize(tileWidth, tileHeight);

			tileButton.setEnabled(false);
			tileButtonArrayList.add(tileButton);
			add(tileButton, 0);

		}


		// disableTileButtons();

		for (BuildingLocation bl : buildingLocArray) {
			BuildingLocationButton blb = new BuildingLocationButton(bl);

			Point locPoint = cordMap.get(new Point(bl.getXLoc(), bl.getYLoc()));

			blb.setLocation((int) locPoint.getX() - buildingLocWidthHeight / 2,
					(int) locPoint.getY() - buildingLocWidthHeight / 2);
			blb.setSize(buildingLocWidthHeight, buildingLocWidthHeight);
			
			blb.setEnabled(false);
			buildingLocButtonArray.add(blb);
			add(blb, 0);
		}

//		addBuildLocListeners();
//		disableBuildingLocButtons();

		for (StreetLocation sl : streetLocArray) {
			StreetLocationButton slb;
			Point startlocPoint = cordMap.get(new Point(sl.getBlStart().getXLoc(), sl.getBlStart().getYLoc()));
			Point endlocPoint = cordMap.get(new Point(sl.getBlEnd().getXLoc(), sl.getBlEnd().getYLoc()));
			int startXLoc = (int) startlocPoint.getX();
			int startYLoc = (int) startlocPoint.getY();
			int endXLoc = (int) endlocPoint.getX();
			int endYLoc = (int) endlocPoint.getY();
			Point streetLocationPoint = null;
			int rotation = 0;
			if (startXLoc < endXLoc) {

				// xstart + ((endx - startx)/2)
				if (startYLoc < endYLoc) {
					// ystart + ((eindy - starty)/2)
					streetLocationPoint = new Point((startXLoc + ((endXLoc - startXLoc) / 2)),
							(startYLoc + ((endYLoc - startYLoc) / 2)));
					rotation = 35;
				} else if (startYLoc > endYLoc) {
					// yeind + ((ystart - yeind)/2)
					streetLocationPoint = new Point((startXLoc + ((endXLoc - startXLoc) / 2)),
							(endYLoc + ((startYLoc - endYLoc) / 2)));
					rotation = 85;
				}

			} else if (startXLoc > endXLoc) {
				if (startYLoc < endYLoc) {
					streetLocationPoint = new Point((endXLoc + ((startXLoc - endXLoc) / 2)),
							(endYLoc + ((startYLoc - endYLoc) / 2)));
					rotation = 85;
				} else if (startYLoc > endYLoc) {
					streetLocationPoint = new Point((startXLoc + ((endXLoc - startXLoc) / 2)),
							(startYLoc + ((endYLoc - startYLoc) / 2)));
					rotation = 35;
				}
			} else {
				if (startYLoc < endYLoc) {
					streetLocationPoint = new Point((startXLoc), (startYLoc + ((endYLoc - startYLoc) / 2)));
				} else if (startYLoc > endYLoc) {
					streetLocationPoint = new Point((endXLoc), (endYLoc + ((startYLoc - endYLoc) / 2)));
				}
			}
			slb = new StreetLocationButton(sl, rotation);
			slb.setLocation((int) (streetLocationPoint.getX() - (streetWidth / 2)),
					(int) (streetLocationPoint.getY() - (streetHeight / 2)));
			slb.setSize(streetWidth, streetHeight);
			slb.setEnabled(false);
			streetLocButtonArray.add(slb);
			add(slb, 0);
		}

//		addStreetLocListeners();
//		disableStreetLocButtons();

	}

	private void fillCordMap() {
		cordMap.put(new Point(1, 3), new Point(80, 386));
		cordMap.put(new Point(1, 4), new Point(80, 310));
		cordMap.put(new Point(2, 2), new Point(145, 498));
		cordMap.put(new Point(2, 3), new Point(145, 424));
		cordMap.put(new Point(2, 4), new Point(70, 274));
		cordMap.put(new Point(2, 5), new Point(145, 274));
		cordMap.put(new Point(2, 6), new Point(145, 198));
		cordMap.put(new Point(3, 1), new Point(210, 610));
		cordMap.put(new Point(3, 2), new Point(210, 535));
		cordMap.put(new Point(3, 3), new Point(135, 386));
		cordMap.put(new Point(3, 4), new Point(210, 386));
		cordMap.put(new Point(3, 5), new Point(210, 310));
		cordMap.put(new Point(3, 6), new Point(135, 162));
		cordMap.put(new Point(3, 7), new Point(210, 162));
		cordMap.put(new Point(3, 8), new Point(210, 86));
		cordMap.put(new Point(4, 1), new Point(275, 645));
		cordMap.put(new Point(4, 2), new Point(200, 498));
		cordMap.put(new Point(4, 3), new Point(275, 498));
		cordMap.put(new Point(4, 4), new Point(275, 424));
		cordMap.put(new Point(4, 5), new Point(200, 274));
		cordMap.put(new Point(4, 6), new Point(275, 274));
		cordMap.put(new Point(4, 8), new Point(200, 50));
		cordMap.put(new Point(4, 7), new Point(275, 198));
		cordMap.put(new Point(4, 9), new Point(275, 50));
		cordMap.put(new Point(5, 2), new Point(340, 610));
		cordMap.put(new Point(5, 3), new Point(340, 535));
		cordMap.put(new Point(5, 4), new Point(265, 386));
		cordMap.put(new Point(5, 5), new Point(340, 386));
		cordMap.put(new Point(5, 6), new Point(340, 310));
		cordMap.put(new Point(5, 7), new Point(265, 162));
		cordMap.put(new Point(5, 8), new Point(340, 162));
		cordMap.put(new Point(5, 9), new Point(340, 86));
		cordMap.put(new Point(6, 2), new Point(405, 645));
		cordMap.put(new Point(6, 3), new Point(330, 498));
		cordMap.put(new Point(6, 4), new Point(405, 498));
		cordMap.put(new Point(6, 5), new Point(405, 424));
		cordMap.put(new Point(6, 6), new Point(330, 274));
		cordMap.put(new Point(6, 7), new Point(405, 274));
		cordMap.put(new Point(6, 8), new Point(405, 198));
		cordMap.put(new Point(6, 9), new Point(330, 50));
		cordMap.put(new Point(6, 10), new Point(405, 50));
		cordMap.put(new Point(7, 3), new Point(470, 610));
		cordMap.put(new Point(7, 4), new Point(470, 535));
		cordMap.put(new Point(7, 5), new Point(395, 386));
		cordMap.put(new Point(7, 6), new Point(470, 386));
		cordMap.put(new Point(7, 7), new Point(470, 310));
		cordMap.put(new Point(7, 8), new Point(395, 162));
		cordMap.put(new Point(7, 9), new Point(470, 162));
		cordMap.put(new Point(7, 10), new Point(470, 86));
		cordMap.put(new Point(8, 3), new Point(535, 645));
		cordMap.put(new Point(8, 4), new Point(460, 498));
		cordMap.put(new Point(8, 5), new Point(535, 498));
		cordMap.put(new Point(8, 6), new Point(535, 424));
		cordMap.put(new Point(8, 7), new Point(460, 274));
		cordMap.put(new Point(8, 8), new Point(535, 274));
		cordMap.put(new Point(8, 9), new Point(535, 198));
		cordMap.put(new Point(8, 10), new Point(460, 50));
		cordMap.put(new Point(8, 11), new Point(535, 50));
		cordMap.put(new Point(9, 4), new Point(600, 610));
		cordMap.put(new Point(9, 5), new Point(600, 535));
		cordMap.put(new Point(9, 6), new Point(525, 386));
		cordMap.put(new Point(9, 7), new Point(600, 386));
		cordMap.put(new Point(9, 8), new Point(600, 310));
		cordMap.put(new Point(9, 9), new Point(525, 162));
		cordMap.put(new Point(9, 10), new Point(600, 162));
		cordMap.put(new Point(9, 11), new Point(600, 86));
		cordMap.put(new Point(10, 6), new Point(665, 498));
		cordMap.put(new Point(10, 7), new Point(665, 424));
		cordMap.put(new Point(10, 8), new Point(590, 274));
		cordMap.put(new Point(10, 9), new Point(665, 274));
		cordMap.put(new Point(10, 10), new Point(665, 198));
		cordMap.put(new Point(11, 8), new Point(730, 386));
		cordMap.put(new Point(11, 9), new Point(730, 310));
	}

	public void enableTileButtons() {
		for (TileButton h : tileButtonArrayList) {
			h.setEnabled(true);
		}
	}

	public void disableTileButtons() {
		for (TileButton h : tileButtonArrayList) {
			h.setEnabled(false);
		}
	}

	public void enableBuildingLocButtons(boolean state) {
		for (BuildingLocationButton b : buildingLocButtonArray) {
			b.setState(state);
			b.setEnabled(true);
		}
	}

	public void disableBuildingLocButtons() {
		for (BuildingLocationButton b : buildingLocButtonArray) {

			b.setEnabled(false);
		}
	}

	public void enableStreetLocButtons() {
		for (StreetLocationButton slb : streetLocButtonArray) {
			slb.setEnabled(true);
		}
	}

	public void disableStreetLocButtons() {
		for (StreetLocationButton slb : streetLocButtonArray) {
			slb.setEnabled(false);
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
			url = this.getClass().getResource("/images/Wool_Tile.png");
			return createImageIcon(url);
		case "GRAAN":
			url = this.getClass().getResource("/images/Wheat_Tile.png");
			return createImageIcon(url);
		case "BAKSTEEN":
			url = this.getClass().getResource("/images/Brick_Tile.png");
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

}
