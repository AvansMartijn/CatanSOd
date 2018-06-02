package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.DevelopmentCardType;
import model.Player;

@SuppressWarnings("serial")
public class DevelopmentCardsPanel extends JPanel {
	
	private final int PANEL_WIDTH = 650;
	private final int PANEL_HEIGHT = 240;
	private final int SCROLLPANE_WIDTH = 650;
	private final int SCROLLPANE_HEIGHT = 240;
	private final int SCROLLPANE_INCREMENT = 20;
	private final int CARD_WIDTH = 150;
	private final int SPACE_BETWEEN_CARDS = 5;
	
	private Color myBackgroundColor = new Color(189, 133, 100);
	private ArrayList<DevelopmentCardButton> developmentCardButtons;
	private JPanel panel = new JPanel();
	private Player selfPlayer;

	public DevelopmentCardsPanel(Player selfPlayer) {
		this.selfPlayer = selfPlayer;
		developmentCardButtons = new ArrayList<DevelopmentCardButton>();
		createComponents();
		createDevelopmentCards();
	}

	public void createComponents() {
		setLayout(new FlowLayout());
		setBackground(myBackgroundColor);
		
		panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		panel.setBackground(myBackgroundColor);

		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(SCROLLPANE_INCREMENT);
		scrollPane.setPreferredSize(new Dimension(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT));
		add(scrollPane);
	}
	
	private void createDevelopmentCards() {
		for (int i = 0; i < selfPlayer.getHand().getDevelopmentCards().size(); i++) {
			DevelopmentCardType developmentCardType = selfPlayer.getHand().getDevelopmentCards().get(i).getDevelopmentCardType();
			addDevelopmentCardButton(developmentCardType);
		}
	}

	// Add development card
	public void addDevelopmentCardButton(DevelopmentCardType developmentCardType) {
		DevelopmentCardButton developmentCardButton = new DevelopmentCardButton(developmentCardType);
		developmentCardButtons.add(developmentCardButton);
		setPanelSize();
		panel.add(developmentCardButton);
	}
	
	// TODO function if card played, set not visible
	
	private void setPanelSize() {
		int size = (developmentCardButtons.size() * (CARD_WIDTH + SPACE_BETWEEN_CARDS));
		panel.setPreferredSize(new Dimension(size, PANEL_HEIGHT));
	}

	public ArrayList<DevelopmentCardButton> getDevelopmentCards() {
		return developmentCardButtons;
	}
}
