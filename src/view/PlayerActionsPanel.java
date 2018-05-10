package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerActionsPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;
	
	// Instance variables
	private Color myBackgroundColor = new Color(189, 133, 100);
	private Color myTextBackgroundColor = new Color(223, 190, 172);
	
	private JButton myBuyButton;
	private JButton myTradeButton;
	private JButton myBuildButton;
	private JButton myEndTurnButton;
	
	// Constructor
	public PlayerActionsPanel() { // TODO add ActionListeners + USE AN ARRAY FOR THE BUTTONS (code repeats a lot)
		setBackground(myBackgroundColor);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.weightx = 1;
        c.weighty = 0.25;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
		
		myBuyButton = new JButton("Koop");
		myBuyButton.setBackground(myTextBackgroundColor);
		myBuyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		myBuyButton.setMargin(new Insets(4,0,4,0));
		myBuyButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		myTradeButton = new JButton("Handel");
		myTradeButton.setBackground(myTextBackgroundColor);
		myTradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		myTradeButton.setMargin(new Insets(4,0,4,0));
		myTradeButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		myBuildButton = new JButton("Bouw");
		myBuildButton.setBackground(myTextBackgroundColor);
		myBuildButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		myBuildButton.setMargin(new Insets(4,0,4,0));
		myBuildButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		myEndTurnButton = new JButton("Eindig Beurt");
		myEndTurnButton.setBackground(Color.BLACK);
		myEndTurnButton.setForeground(Color.WHITE);
		myEndTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		myEndTurnButton.setMargin(new Insets(4,0,4,0));
		myEndTurnButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		add(myBuyButton, c);
		add(myTradeButton, c);
		add(myBuildButton, c);
		c.insets = new Insets(50, 10, 10, 10);
		add(myEndTurnButton, c);
	}
}
