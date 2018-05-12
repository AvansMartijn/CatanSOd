import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private final int panelWidth = 800;
	private final int panelHeight = 800;

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

	private int[] lengtesRijen = new int[] { tilesRow1, tilesRow2, tilesRow3, tilesRow4,
			tilesRow5 };
//	private JLabel[] myTileLabels = new JLabel[totalTiles];
	private TileButton[] hexArray = new TileButton[totalTiles];
	private BuildingLocationButton[] buildingLocArray = new BuildingLocationButton[totalTiles];

	public BoardPanel() {
		setLayout(null);
//		setLayout(new GridBagLayout());
		setBackground(Color.BLUE); // TODO make it some cool image
		setPreferredSize(new Dimension(panelWidth, panelHeight));
		createTiles();
	}

	// Create tiles and draw them
	private void createTiles() {
		int tileNumber = 0;

		int[] values_x = new int[] { 150, 85, 20, 85, 150 };
		int[] values_y = new int[] { 50, 162, 274, 386, 498 };

		
		//TODO: REWRITE using intarray with paintlocation values of tiles
		//TODO: ADD intarray with buildinglocations to draw
		
		// Voor het aantal rijen van Tiles
		for (int i = 0; i < lengtesRijen.length; i++) {
			// Voor het aantal tiles in een rij
			for (int j = 0; j < lengtesRijen[i]; j++) {
				Image image = null;
//				TileButton hexButton = null;
				try {
					URL url = this.getClass().getResource("/images/Iron_Tile.png"); // TODO change this to getTileType(tileNumber);
					image = ImageIO.read(url);
					image = image.getScaledInstance(tileImgWidth, tileImgHeight, Image.SCALE_DEFAULT);
				} catch (IOException e) {
				}
//				myTileLabels[tileNumber] = new JLabel(new ImageIcon(image));
				hexArray[tileNumber] = new TileButton(new ImageIcon(image), j);
				buildingLocArray[tileNumber] = new BuildingLocationButton();
				
//				add(myTileLabels[tileNumber]);
				add(hexArray[tileNumber], -1);
				add(buildingLocArray[tileNumber], 0);

//				myTileLabels[tileNumber].setLocation(values_x[i] + (tileWidth * j), values_y[i]);
				hexArray[tileNumber].setLocation(values_x[i] + ((tileWidth+130 * j) -100), values_y[i]);
				buildingLocArray[tileNumber].setLocation(values_x[i] + ((tileWidth+130 * j) -105), values_y[i] + 20);
				
//				myTileLabels[tileNumber].setSize(tileWidth, tileHeight);
				hexArray[tileNumber].setSize(tileWidth, tileHeight);
				buildingLocArray[tileNumber].setSize(buildingLocWidthHeight, buildingLocWidthHeight);
				tileNumber++;
				
			}
		}
		
		for(TileButton h: hexArray) {
			h.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Clicked tile:" + h.getX() + " " + h.getY());
					
				}
			});
//			h.setEnabled(false);
		}
		
	}
	
	public void enableTiles() {
		for(TileButton h: hexArray) {
			h.setEnabled(true);
		}
	}
	
	public void disableTiles() {
		for(TileButton h: hexArray) {
			h.setEnabled(false);
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
//	private static final long serialVersionUID = 1L;
//	private static final int ROWS = 5;
//	private static final int COLUMNS = 5;
//	private HexagonButton[][] hexButton = new HexagonButton[ROWS][COLUMNS];
//
//	public HexagonPattern() {
//		setLayout(null);
//		initGUI();
//	}
//
//	public void initGUI() {
//        int offsetX = -10;
//        int offsetY = 0;
//
//        for(int row = 0; row < ROWS; row++) {
//            for(int col = 0; col < COLUMNS; col++){
//                hexButton[row][col] = new HexagonButton(row, col);
//                hexButton[row][col].addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        HexagonButton clickedButton = (HexagonButton) e.getSource();
//                        System.out.println("Button clicked: [" + clickedButton.getRow() + "][" + clickedButton.getCol() + "]");
//                    }
//                });
//                add(hexButton[row][col]);
//                hexButton[row][col].setBounds(offsetY, offsetX, 105, 95);
//                offsetX += 87;
//            }
//            if(row%2 == 0) {
//                offsetX = -52;
//            } else {
//                offsetX = -10;
//            }
//            offsetY += 76;
//        }
//    }
}
