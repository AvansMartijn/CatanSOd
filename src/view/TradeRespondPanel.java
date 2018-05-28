package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

import model.Player;
import model.ResourceType;
import model.TradeRequest;

@SuppressWarnings("serial")
public class TradeRespondPanel extends JPanel {

	private final int PANEL_WIDTH = 275;
	private final int PANEL_HEIGHT = 300;

	private final int BUTTON_WIDTH = 160;
	private final int BUTTON_HEIGHT = 35;

	private final int TEXTFIELD_INPUT_WIDTH = 35;
	private final int TEXTFIELD_INPUT_HEIGHT = 20;
	private final int TEXTFIELD_OFFSET_X1 = 215;
	private final int TEXTFIELD_OFFSET_X2 = 20;
	private final int TEXTFIELD_OFFSET_Y = 30;

	private String playerName;
	private Boolean counterOffer, tradeAccept;
	// requests for sending the trade offer
	// private int[] resourceOffer;
	// private int[] resourceRequest;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel, subTitleLabel1, subTitleLabel2, subTitleLabel3, declinedLabel;
	private JLabel woodLabel, wheatLabel, brickLabel, ironLabel, woolLabel;

	private JFormattedTextField woodGive, wheatGive, brickGive, ironGive, woolGive;
	private JFormattedTextField woodReceive, wheatReceive, brickReceive, ironReceive, woolReceive;

	private JButton sendRequestButton;
	
	private TradeRequest tR;
	
	public TradeRespondPanel(TradeRequest tR) {

		this.tR = tR;
		
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		titleLabel = new JLabel("Bod van speler " + tR.getIdPlayer());
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(0, 5, 280, 40);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);

		subTitleLabel = new JLabel("wat wil je doen?");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setBounds(0, 30, 280, 40);
		subTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(subTitleLabel);

		createResourceInput();

		sendRequestButton = new JButton("Tegenbod Accepteren");
		sendRequestButton.setBounds(55, 255, BUTTON_WIDTH, BUTTON_HEIGHT);
		sendRequestButton.setHorizontalAlignment(JLabel.CENTER);
		sendRequestButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		sendRequestButton.setBackground(textBackgroundColor);
		sendRequestButton.setForeground(TextColor);
		if(tR.getAccepted() == 1) {
			add(sendRequestButton);
		}else {
			declinedLabel = new JLabel("Speler heeft verzoek afgewezen");
			declinedLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
			declinedLabel.setForeground(TextColor);
			declinedLabel.setBounds(55, 255, BUTTON_WIDTH, BUTTON_HEIGHT);
			declinedLabel.setHorizontalAlignment(JLabel.CENTER);
			add(declinedLabel);
		}
		

	}

	private void createResourceInput() {

		

		subTitleLabel1 = new JLabel("grondstoffen");
		subTitleLabel1.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel1.setForeground(TextColor);
		subTitleLabel1.setBounds(40, 40, 200, 60);
		subTitleLabel1.setHorizontalAlignment(JLabel.CENTER);
		add(subTitleLabel1);

		subTitleLabel2 = new JLabel("geven");
		subTitleLabel2.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel2.setForeground(TextColor);
		subTitleLabel2.setBounds(20, 50, 200, 60);
		add(subTitleLabel2);

		brickGive = new JFormattedTextField();
		brickGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		brickGive.setText(tR.getG_brick() + "");
		brickGive.setEditable(false);
		add(brickGive);

		woolGive = new JFormattedTextField();
		woolGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		woolGive.setText(tR.getG_wool() + "");
		woolGive.setEditable(false);
		add(woolGive);

		ironGive = new JFormattedTextField();
		ironGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		ironGive.setText(tR.getG_iron() + "");
		ironGive.setEditable(false);
		add(ironGive);

		wheatGive = new JFormattedTextField();
		wheatGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		wheatGive.setText(tR.getG_wheat() + "");
		wheatGive.setEditable(false);
		add(wheatGive);

		woodGive = new JFormattedTextField();
		woodGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		woodGive.setText(tR.getG_wood() + "");
		woodGive.setEditable(false);
		add(woodGive);

		brickLabel = new JLabel("Baksteen");
		brickLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y, 200, 60);
		brickLabel.setHorizontalAlignment(JLabel.CENTER);
		add(brickLabel);

		woolLabel = new JLabel("Wol");
		woolLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y * 2, 200, 60);
		woolLabel.setHorizontalAlignment(JLabel.CENTER);
		add(woolLabel);

		ironLabel = new JLabel("Erts");
		ironLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y * 3, 200, 60);
		ironLabel.setHorizontalAlignment(JLabel.CENTER);
		add(ironLabel);

		wheatLabel = new JLabel("Graan");
		wheatLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y * 4, 200, 60);
		wheatLabel.setHorizontalAlignment(JLabel.CENTER);
		add(wheatLabel);

		woodLabel = new JLabel("Hout");
		woodLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y * 5, 200, 60);
		woodLabel.setHorizontalAlignment(JLabel.CENTER);
		add(woodLabel);

		subTitleLabel3 = new JLabel("ontvangen");
		subTitleLabel3.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel3.setForeground(TextColor);
		subTitleLabel3.setBounds(200, 50, 250, 60);
		add(subTitleLabel3);

		brickReceive = new JFormattedTextField();
		brickReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		brickReceive.setText(tR.getW_brick() + "");
		brickReceive.setEditable(false);
		add(brickReceive);

		woolReceive = new JFormattedTextField();
		woolReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		woolReceive.setText(tR.getW_wool() + "");
		woolReceive.setEditable(false);
		add(woolReceive);

		ironReceive = new JFormattedTextField();
		ironReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		ironReceive.setText(tR.getW_iron() + "");
		ironReceive.setEditable(false);
		add(ironReceive);

		wheatReceive = new JFormattedTextField();
		wheatReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		wheatReceive.setText(tR.getW_wheat() + "");
		wheatReceive.setEditable(false);
		add(wheatReceive);

		woodReceive = new JFormattedTextField();
		woodReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		woodReceive.setText(tR.getW_wood() + "");
		woodReceive.setEditable(false);
		add(woodReceive);
	}

	public JButton getSendRequestButton() {
		return sendRequestButton;
	}

	public int getWoodGive() {
		int parsed = Integer.parseInt(woodGive.getText());
		return parsed;
	}

	public int getWheatGive() {
		int parsed = Integer.parseInt(wheatGive.getText());
		return parsed;                
	}  

	public int getBrickGive() {
		int parsed = Integer.parseInt(brickGive.getText());
		return parsed;                
	}

	public int getIronGive() {
		int parsed = Integer.parseInt(ironGive.getText());
		return parsed;                
	} 

	public int getWoolGive() {
		int parsed = Integer.parseInt(woolGive.getText());
		return parsed;                
	}

	public int getWoodReceive() {
		int parsed = Integer.parseInt(woodReceive.getText());
		return parsed;                
	}

	public int getWheatReceive() {
		int parsed = Integer.parseInt(wheatReceive.getText());
		return parsed;                
	}

	public int getBrickReceive() {
		int parsed = Integer.parseInt(brickReceive.getText());
		return parsed;                
	}

	public int getIronReceive() {
		int parsed = Integer.parseInt(ironReceive.getText());
		return parsed;                
	}

	public int getWoolReceive() {
		int parsed = Integer.parseInt(woolReceive.getText());
		return parsed;                
	}
	
	
}