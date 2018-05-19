package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Player;

@SuppressWarnings("serial")
public class MyDevelopmentCardsPanel extends JPanel {
	
	private final int PANEL_WIDTH = 650;
	private final int PANEL_HEIGHT = 240;
	private final int CARD_WIDTH = 150;
	private final int CARD_HEIGHT = 220;
	private final int SPACE_BETWEEN_CARDS = 5;
	
	private Color myBackgroundColor = new Color(189, 133, 100);
	private ArrayList<JButton> developmentCards;
	private JPanel panel = new JPanel();

	public MyDevelopmentCardsPanel(Player selfPlayer) {
		create();
		// TEST
		for (int i = 0; i < 10; i++) {
			addDevelopmentCard();
		}
		// END TEST
	}

	public void create() {
		developmentCards = new ArrayList<JButton>();
		setLayout(new FlowLayout());
		setBackground(myBackgroundColor);
		
		panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		panel.setBackground(myBackgroundColor);

		JScrollPane js = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		js.getHorizontalScrollBar().setUnitIncrement(20);
		js.setPreferredSize(new Dimension(650, 240));
		add(js);
	}

	// Add development cards
	public void addDevelopmentCard() {
		DevelopmentCardButton developmentCardButton = new DevelopmentCardButton();
		developmentCards.add(developmentCardButton);
		setPanelSize();
		panel.add(developmentCardButton);
	}
	
	public void removeDevelopmentCard() {
		
	}
	
	private void setPanelSize() {
		int size = (developmentCards.size() * (CARD_WIDTH + SPACE_BETWEEN_CARDS));
		panel.setPreferredSize(new Dimension(size, PANEL_HEIGHT));
	}
}
