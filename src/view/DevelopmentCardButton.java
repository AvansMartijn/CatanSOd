package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.DevelopmentCard;
import model.DevelopmentCardType;

@SuppressWarnings("serial")
public class DevelopmentCardButton extends JButton {
	
	private final int CARD_WIDTH = 150;
	private final int CARD_HEIGHT = 220;
	
	private DevelopmentCard developmentCard;
	private DevelopmentCardPlayDialog developmentCardPlayDialog;

	public DevelopmentCardButton(DevelopmentCard developmentCard) {
		this.developmentCard = developmentCard;
		this.developmentCardPlayDialog = new DevelopmentCardPlayDialog(this);
		setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
		
		URL iconURL = this.getClass().getResource(getImagePath(developmentCard.getDevelopmentCardType()));
		ImageIcon developmentCardImage = new ImageIcon(iconURL);
		Image img = developmentCardImage.getImage();  
	    Image resizedImage = img.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, java.awt.Image.SCALE_DEFAULT);
	    developmentCardImage.setImage(resizedImage);
		JLabel myOpenLabel = new JLabel(developmentCardImage);
		
		setBackground(Color.WHITE);
		add(myOpenLabel);
	}
	
	public DevelopmentCardPlayDialog getDevelopmentCardPlayDialog() {
		return developmentCardPlayDialog;
	}

	public void setDevelopmentCardPlayDialog(DevelopmentCardPlayDialog developmentCardPlayDialog) {
		this.developmentCardPlayDialog = developmentCardPlayDialog;
	}

	private String getImagePath(DevelopmentCardType developmentCardType) {
		switch (developmentCardType) {
		case KNIGHT:
			return "/images/Knight.jpg";
		case VICTORY_POINT:
			return "/images/VictoryPoint.jpg";
		case ROAD_BUILDING:
			return "/images/StreetBuilding.jpg";
		case MONOPOLY:
			return "/images/Monopoly.jpg";
		case YEAR_OF_PLENTY:
			return "/images/Year_Of_Plenty.jpg";
		default:
			System.out.println("foutje");
		}
		return null;
	}

	public DevelopmentCard getDevelopmentCard() {
		return developmentCard;
	}
	
	
}
