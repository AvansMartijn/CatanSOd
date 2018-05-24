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

@SuppressWarnings("serial")
public class TradeRespondDialogPanel extends JPanel {

	private final int PANEL_WIDTH = 275;
	private final int PANEL_HEIGHT = 300;

	private final int BUTTON_WIDTH = 160;
	private final int BUTTON_HEIGHT = 35;

	private final int TEXTFIELD_INPUT_WIDTH = 35;
	private final int TEXTFIELD_INPUT_HEIGHT = 20;
	private final int TEXTFIELD_OFFSET_X1 = 20;
	private final int TEXTFIELD_OFFSET_X2 = 215;
	private final int TEXTFIELD_OFFSET_Y = 30;

	// requests for sending the trade offer
	// private int[] resourceOffer;
	// private int[] resourceRequest;

	private String playerName;
	private Boolean counterOffer, tradeAccept;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel, subTitleLabel1, subTitleLabel2, subTitleLabel3;
	private JLabel woodLabel, wheatLabel, stoneLabel, ironLabel, woolLabel;

	private JFormattedTextField woodGive, wheatGive, stoneGive, ironGive, woolGive;
	private JFormattedTextField woodReceive, wheatReceive, stoneReceive, ironReceive, woolReceive;

	private JButton sendRequestButton;

	public TradeRespondDialogPanel(String newPlayerName, boolean counterOffer) {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		this.playerName = newPlayerName;

		setLayout(null);

		String title = setMessage();

		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(0, 5, 280, 40);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);

		if (counterOffer || tradeAccept) {

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
			add(sendRequestButton);

		} else {
		}
	}

	private void createResourceInput() {

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(100);
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(true);
		formatter.setOverwriteMode(true);

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

		woodGive = new JFormattedTextField(formatter);
		woodGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH, TEXTFIELD_INPUT_HEIGHT);
		add(woodGive);

		wheatGive = new JFormattedTextField(formatter);
		wheatGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(wheatGive);

		stoneGive = new JFormattedTextField(formatter);
		stoneGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(stoneGive);

		ironGive = new JFormattedTextField(formatter);
		ironGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(ironGive);

		woolGive = new JFormattedTextField(formatter);
		woolGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woolGive);

		woodLabel = new JLabel("Hout");
		woodLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y, 200, 60);
		woodLabel.setHorizontalAlignment(JLabel.CENTER);
		add(woodLabel);

		wheatLabel = new JLabel("Graan");
		wheatLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y * 2, 200, 60);
		wheatLabel.setHorizontalAlignment(JLabel.CENTER);
		add(wheatLabel);

		stoneLabel = new JLabel("Steen");
		stoneLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y * 3, 200, 60);
		stoneLabel.setHorizontalAlignment(JLabel.CENTER);
		add(stoneLabel);

		ironLabel = new JLabel("Ijzer");
		ironLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y * 4, 200, 60);
		ironLabel.setHorizontalAlignment(JLabel.CENTER);
		add(ironLabel);

		woolLabel = new JLabel("Wol");
		woolLabel.setBounds(35, 50 + TEXTFIELD_OFFSET_Y * 5, 200, 60);
		woolLabel.setHorizontalAlignment(JLabel.CENTER);
		add(woolLabel);

		subTitleLabel3 = new JLabel("ontvangen");
		subTitleLabel3.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel3.setForeground(TextColor);
		subTitleLabel3.setBounds(200, 50, 250, 60);
		add(subTitleLabel3);

		woodReceive = new JFormattedTextField(formatter);
		woodReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woodReceive);

		wheatReceive = new JFormattedTextField(formatter);
		wheatReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(wheatReceive);

		stoneReceive = new JFormattedTextField(formatter);
		stoneReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(stoneReceive);

		ironReceive = new JFormattedTextField(formatter);
		ironReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(ironReceive);

		woolReceive = new JFormattedTextField(formatter);
		woolReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woolReceive);
	}

	public JButton getSendRequestButton() {
		return sendRequestButton;
	}

	public String getWoodGive() {
		return woodGive.getText();
	}

	public String getWheatGive() {
		return wheatGive.getText();
	}

	public String getStoneGive() {
		return stoneGive.getText();
	}

	public String getIronGive() {
		return ironGive.getText();
	}

	public String getWoolGive() {
		return woolGive.getText();
	}

	public String getWoodReceive() {
		return woodReceive.getText();
	}

	public String getWheatReceive() {
		return wheatReceive.getText();
	}

	public String getStoneReceive() {
		return stoneReceive.getText();
	}

	public String getIronReceive() {
		return ironReceive.getText();
	}

	public String getWoolReceive() {
		return woolReceive.getText();
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	private String setMessage() {
		String message = "";

		if (counterOffer) {
			message = playerName + " heeft een tegenbod gedaan";
		} else if (tradeAccept) {
			message = playerName + " heeft je handelsverzoek geaccepteerd";
		} else {
			message = playerName + " heeft je handelsverzoek afgewezen";
		}
		return message;
	}

	public Boolean getCounterOffer() {
		return counterOffer;
	}

	public void setCounterOffer(Boolean counterOffer) {
		this.counterOffer = counterOffer;
	}

}