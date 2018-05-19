package view;

import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DevelopmentCardButton extends JButton {
	
	private final int CARD_WIDTH = 150;
	private final int CARD_HEIGHT = 220;

	public DevelopmentCardButton() { // TODO switch case for type developmentcard image so as parameter a string type maybe
		setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
		
		URL iconURL = this.getClass().getResource("/images/Ontwikkelingskaart.jpg");
		ImageIcon myOpenIcon = new ImageIcon(iconURL);
		JLabel myOpenLabel = new JLabel(myOpenIcon);
		
		setBackground(Color.WHITE);
		add(myOpenLabel);
	}
}
