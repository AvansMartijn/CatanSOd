package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class MyDevelopmentCardsPanel extends JPanel {

	private final int PANEL_WIDTH = 650;
	private final int PANEL_HEIGHT = 240;
	private final int SCROLLPANE_HEIGHT = 20;
	
	// Instance variables
	private Color myBackgroundColor = new Color(189, 133, 100);
	private ArrayList<JLabel> developmentCards;
	private JScrollPane myScrollPane;
	private JPanel myPanel;
	//private Player player;
	
	// Constructor
	public MyDevelopmentCardsPanel() { // TODO Add player to constructor parameter
		//this.player = player;
		developmentCards = new ArrayList<JLabel>();
		myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.X_AXIS));
		myScrollPane = new JScrollPane(myPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		myScrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, SCROLLPANE_HEIGHT));
		
		setBackground(myBackgroundColor);
		setLayout(new BorderLayout());
		add(myPanel, BorderLayout.CENTER);
		
		// TEST
		for(int i = 0; i < 4; i++) {
			addDevelopmentCard();
		}
		// END TEST
		add(myScrollPane, BorderLayout.SOUTH);
	}
	
	private void addDevelopmentCard() {
		// Get Image
		Image image = null;
		try {
			URL url = this.getClass().getResource("/images/Ontwikkelingskaart.jpg");
			image = ImageIO.read(url);
			image = image.getScaledInstance(150, 220, Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
		JLabel myLabel = new JLabel(new ImageIcon(image));
		developmentCards.add(myLabel);
		myPanel.add(myLabel);
	}
}
