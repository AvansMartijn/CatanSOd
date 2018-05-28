package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TradeRequest;

@SuppressWarnings("serial")
public class TradeRespondPanels extends JPanel {
	
	private final int PANEL_WIDTH = 825;
	private final int PANEL_HEIGHT = 340;
	
	private final int BUTTON_WIDTH = 160;
	private final int BUTTON_HEIGHT = 35;
	
	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);
	
	private TradeRespondPanel tradeRespondDialogPanel1;
	private TradeRespondPanel tradeRespondDialogPanel2;
	private TradeRespondPanel tradeRespondDialogPanel3;
	
	private JButton cancelButton;

	public TradeRespondPanels(TradeRequest tR1,TradeRequest tR2,TradeRequest tR3) {

		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setBackground(backgroundColor);
		
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		
		tradeRespondDialogPanel1 = new TradeRespondPanel(tR1);
		tradeRespondDialogPanel2 = new TradeRespondPanel(tR2);
		tradeRespondDialogPanel3 = new TradeRespondPanel(tR3);

		add(tradeRespondDialogPanel1);
		add(tradeRespondDialogPanel2);
		add(tradeRespondDialogPanel3);
		
		cancelButton = new JButton("Handel afbreken");
		cancelButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		cancelButton.setBackground(textBackgroundColor);
		cancelButton.setForeground(TextColor);
		add(cancelButton);
		
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public TradeRespondPanel getTradeRespondDialogPanel1() {
		return tradeRespondDialogPanel1;
	}

	public TradeRespondPanel getTradeRespondDialogPanel2() {
		return tradeRespondDialogPanel2;
	}

	public TradeRespondPanel getTradeRespondDialogPanel3() {
		return tradeRespondDialogPanel3;
	}
	
	
	
}