package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.DevelopmentCardType;

@SuppressWarnings("serial")
public class DevelopmentCardDialogPanel extends JPanel {

	private final int PANEL_WIDTH = 100;
	private final int PANEL_HEIGHT = 200;

	private DevelopmentCardType developmentCardType;

	private JTextArea developmentCardDescription;
	private JButton playButton;

	public DevelopmentCardDialogPanel(DevelopmentCardButton developmentCardButton) {
		this.developmentCardType = developmentCardButton.getDevelopmentCard().getDevelopmentCardType();
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

		playButton = new JButton("Speel");
		developmentCardDescription = new JTextArea();
		developmentCardDescription.setText(getText(developmentCardType));
		developmentCardDescription.setLineWrap(true);
		developmentCardDescription.setWrapStyleWord(true);
		developmentCardDescription.setEditable(false);

		add(developmentCardDescription, BorderLayout.CENTER);
		add(playButton, BorderLayout.SOUTH);
	}

	private String getText(DevelopmentCardType developmentCardType) {
		switch (developmentCardType) {
		case KNIGHT:
			return "Bij het spelen van deze kaart moet je de struikrover verzetten en van één van de getroffen spelers een grondstoffenkaart trekken.";
		case VICTORY_POINT:
			return "Bij het spelen van deze kaart word de hoeveelheid van je overwinningspunten met 1 verhoogd.";
		case ROAD_BUILDING:
			return "Bij het spelen van deze kaart mag je direct twee straten bouwen.";
		case MONOPOLY:
			return "Bij het spelen van deze kaart kies je een grondstoffensoort. Alle spelers geven je hiervan alle kaarten die ze bezitten.";
		case YEAR_OF_PLENTY:
			return "Bij het spelen van deze kaart neem je direct twee grondstoffenkaarten naar keuze van de bank.";
		default:
			System.out.println("foutje");
		}
		return null;
	}

	public JButton getPlayButton() {
		return playButton;
	}
}
