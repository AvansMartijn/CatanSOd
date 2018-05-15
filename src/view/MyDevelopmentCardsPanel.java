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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MyDevelopmentCardsPanel extends JPanel {
	
	private final int PANEL_WIDTH = 650;
	private final int PANEL_HEIGHT = 240;
	private final int CARD_WIDTH = 150;
	private final int CARD_HEIGHT = 220;
	private final int SPACE_BETWEEN_CARDS = 5;
	
	private Color myBackgroundColor = new Color(189, 133, 100);
	private ArrayList<JLabel> developmentCards;
	private JPanel panel = new JPanel();

	public MyDevelopmentCardsPanel() {
		create();
		// TEST
		for (int i = 0; i < 10; i++) {
			addDevelopmentCard(panel);
		}
		// END TEST
	}

	private void create() {
		developmentCards = new ArrayList<JLabel>();
		setLayout(new FlowLayout());
		setBackground(new Color(240, 226, 223)); // TODO variable
		
		panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		panel.setBackground(myBackgroundColor);

		JScrollPane js = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		js.setPreferredSize(new Dimension(650, 240));
		add(js);
	}

	// Add development cards
	private void addDevelopmentCard(JPanel jp) {
		// Get Image
		Image image = null;
		try {
			URL url = this.getClass().getResource("/images/Ontwikkelingskaart.jpg");
			image = ImageIO.read(url);
			image = image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
		JLabel myLabel = new JLabel(new ImageIcon(image));
		developmentCards.add(myLabel);
		setPanelSize();
		jp.add(myLabel);
	}
	
	private void setPanelSize() {
		int size = (developmentCards.size() * (CARD_WIDTH + SPACE_BETWEEN_CARDS));
		panel.setPreferredSize(new Dimension(size, PANEL_HEIGHT));
	}
}
