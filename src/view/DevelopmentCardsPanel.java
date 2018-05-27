package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.DevelopmentCardType;
import model.Player;

@SuppressWarnings("serial")
public class DevelopmentCardsPanel extends JPanel {
	
	private final int PANEL_WIDTH = 650;
	private final int PANEL_HEIGHT = 240;
	private final int CARD_WIDTH = 150;
	private final int CARD_HEIGHT = 220;
	private final int SPACE_BETWEEN_CARDS = 5;
	
	private Color myBackgroundColor = new Color(189, 133, 100);
	private ArrayList<JButton> developmentCards;
	private JPanel panel = new JPanel();
	private Player selfPlayer;

	public DevelopmentCardsPanel(Player selfPlayer) {
		this.selfPlayer = selfPlayer;
		create();
		
		// TODO own function?
		for (int i = 0; i < selfPlayer.getHand().getDevelopmentCards().size(); i++) {
			DevelopmentCardType developmentCardType = selfPlayer.getHand().getDevelopmentCards().get(i).getDevelopmentCardType();
			addDevelopmentCard(developmentCardType);
		}
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

	// Add development card
	public void addDevelopmentCard(DevelopmentCardType developmentCardType) {
		DevelopmentCardButton developmentCardButton = new DevelopmentCardButton(developmentCardType);
		developmentCards.add(developmentCardButton);
		setPanelSize();
		panel.add(developmentCardButton);
	}
	
	public void removeDevelopmentCard() {
		// TODO
	}
	
	private void setPanelSize() {
		int size = (developmentCards.size() * (CARD_WIDTH + SPACE_BETWEEN_CARDS));
		panel.setPreferredSize(new Dimension(size, PANEL_HEIGHT));
	}

	public ArrayList<JButton> getDevelopmentCards() {
		return developmentCards;
	}
}
