package view;                                                                                                                                                                         
                                                                                                                                                                                        
import java.awt.Color;                                                                                                                                                                  
import java.awt.Dimension;                                                                                                                                                              
import java.awt.Font;                                                                                                                                                                   
                                                                                                                                                                                        
import javax.swing.JButton;                                                                                                                                                                                                                                                                                                                         
                                                                                                                                                              
import javax.swing.JLabel;                                                                                                                                                              
import javax.swing.JPanel;                                                                                                                                                              
                                                                                                                                                                                       
@SuppressWarnings("serial")
public class BuyPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_SIZE = 150;
	
	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);
	
	private JLabel titleLabel;
	private JLabel subTitleLabel;
	
	private JButton yesButton;
	private JButton returnButton;

	

	public BuyPanel() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		titleLabel = new JLabel("ONTWIKKELINGSKAART KOPEN");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(10, 10, 320, 40);
		add(titleLabel);
		
		subTitleLabel = new JLabel("Weet je zeker dat je een ontwikkelingskaart wilt kopen?");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setBounds(10, 60, 320, 40);
		add(subTitleLabel);
		
		yesButton = new JButton("JA");
		yesButton.setBounds(10, 140, BUTTON_SIZE, BUTTON_SIZE);
		yesButton.setFont(new Font("SansSerif", Font.BOLD, 35));
		yesButton.setBackground(textBackgroundColor);
		yesButton.setForeground(TextColor);
		add(yesButton);

		returnButton = new JButton("NEE");
		returnButton.setBounds(180, 140, BUTTON_SIZE, BUTTON_SIZE);
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 35));
		returnButton.setBackground(textBackgroundColor);
		returnButton.setForeground(TextColor);
		add(returnButton);
		
		
	}

	public JButton getYesButton() {
		return yesButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}
}                                                      