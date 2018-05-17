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
public class PlayerActionPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;
	
	// Instance variables
	private Color myBackgroundColor = new Color(189, 133, 100);
	private Color myTextBackgroundColor = new Color(223, 190, 172);
	
	private JButton BuyButton;
	private JButton TradeButton;
	private JButton BuildButton;
	private JButton EndTurnButton;
	
	// Constructor
	public PlayerActionPanel() { // TODO add ActionListeners + USE AN ARRAY FOR THE BUTTONS (code repeats a lot)
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
		
		BuyButton = new JButton("Koop");
		BuyButton.setBackground(myTextBackgroundColor);
		BuyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		BuyButton.setMargin(new Insets(4,0,4,0));
		BuyButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		TradeButton = new JButton("Handel");
		TradeButton.setBackground(myTextBackgroundColor);
		TradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		TradeButton.setMargin(new Insets(4,0,4,0));
		TradeButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		BuildButton = new JButton("Bouw");
		BuildButton.setBackground(myTextBackgroundColor);
		BuildButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		BuildButton.setMargin(new Insets(4,0,4,0));
		BuildButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		EndTurnButton = new JButton("Eindig Beurt");
		EndTurnButton.setBackground(Color.BLACK);
		EndTurnButton.setForeground(Color.WHITE);
		EndTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		EndTurnButton.setMargin(new Insets(4,0,4,0));
		EndTurnButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		add(BuyButton, c);
		add(TradeButton, c);
		add(BuildButton, c);
		c.insets = new Insets(50, 10, 10, 10);
		add(EndTurnButton, c);
	}

	public JButton getBuyButton() {
		return BuyButton;
	}

	public JButton getTradeButton() {
		return TradeButton;
	}

	public JButton getBuildButton() {
		return BuildButton;
	}

	public JButton getEndTurnButton() {
		return EndTurnButton;
	}
	
	
	
}
