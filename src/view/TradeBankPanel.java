package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.NumberFormatter;

import model.Player;

@SuppressWarnings("serial")
public class TradeBankPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 160;
	private final int BUTTON_HEIGHT = 35;

	private final int BUTTON_OFFSET_X = 20;

	private final int TEXTFIELD_INPUT_WIDTH = 35;
	private final int TEXTFIELD_INPUT_HEIGHT = 20;
	private final int RADIOBUTTON_OFFSET = 25;
	private final int TEXTFIELD_OFFSET = 120;
	private final int LABEL_OFFSET = 180;
	private final int TEXTFIELD_OFFSET_Y = 35;

	private final int RADIOBUTTON_SIZE = 20;

	// requests for sending the trade offer
	// private int[] resourceOffer;
	// private int[] resourceRequest;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel1, subTitleLabel2, subTitleLabel3;

	private JLabel woodLabel, wheatLabel, stoneLabel, ironLabel, woolLabel;

	private JLabel woodHarbour, wheatHarbour, stoneHarbour, ironHarbour, woolHarbour;

	private JButton sendRequestButton, returnButton;

	private ButtonGroup give;

	private JRadioButton woodGive, wheatGive, stoneGive, ironGive, woolGive;

	private JFormattedTextField woodReceive, wheatReceive, stoneReceive, ironReceive, woolReceive;

	private Player selfPlayer;

	public TradeBankPanel(Player selfPlayer) {
		this.selfPlayer = selfPlayer;

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		titleLabel = new JLabel("HANDELEN");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(80, 10, 280, 40);
		add(titleLabel);

		createResourceInput();

		sendRequestButton = new JButton("Verzoek versturen");
		sendRequestButton.setBounds(BUTTON_OFFSET_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
		sendRequestButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		sendRequestButton.setBackground(textBackgroundColor);
		sendRequestButton.setForeground(TextColor);
		add(sendRequestButton);

		returnButton = new JButton("terug");
		returnButton.setBounds(220, 300, 100, BUTTON_HEIGHT);
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		returnButton.setBackground(textBackgroundColor);
		returnButton.setForeground(TextColor);
		add(returnButton);

	}

	private void createResourceInput() {

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(selfPlayer.getHand().getResources().size()); // TODO update function and do this for each
																			// individual resource, we need more
																			// formatters for each textfield
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(true);
		formatter.setOverwriteMode(true);

		subTitleLabel1 = new JLabel("grondstoffen:");
		subTitleLabel1.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel1.setForeground(TextColor);
		subTitleLabel1.setBounds(50, 30, 200, 60);
		add(subTitleLabel1);

		subTitleLabel2 = new JLabel("geven");
		subTitleLabel2.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel2.setForeground(TextColor);
		subTitleLabel2.setBounds(20, 50, 200, 60);
		add(subTitleLabel2);

		give = new ButtonGroup();

		woodGive = new JRadioButton();
		woodGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		woodGive.setBackground(backgroundColor);
		give.add(woodGive);
		add(woodGive);

		wheatGive = new JRadioButton();
		wheatGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 2, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		wheatGive.setBackground(backgroundColor);
		give.add(wheatGive);
		add(wheatGive);

		stoneGive = new JRadioButton();
		stoneGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 3, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		stoneGive.setBackground(backgroundColor);
		give.add(stoneGive);
		add(stoneGive);

		ironGive = new JRadioButton();
		ironGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 4, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		ironGive.setBackground(backgroundColor);
		give.add(ironGive);
		add(ironGive);

		woolGive = new JRadioButton();
		woolGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 5, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		woolGive.setBackground(backgroundColor);
		give.add(woolGive);
		add(woolGive);

		woodLabel = new JLabel("Hout");
		woodLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y, 200, 60);
		add(woodLabel);

		wheatLabel = new JLabel("Graan");
		wheatLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 2, 200, 60);
		add(wheatLabel);

		stoneLabel = new JLabel("Steen");
		stoneLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 3, 200, 60);
		add(stoneLabel);

		ironLabel = new JLabel("Ijzer");
		ironLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 4, 200, 60);
		add(ironLabel);

		woolLabel = new JLabel("Wol");
		woolLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 5, 200, 60);
		add(woolLabel);

		subTitleLabel3 = new JLabel("ontvangen");
		subTitleLabel3.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel3.setForeground(TextColor);
		subTitleLabel3.setBounds(100, 50, 250, 60);
		add(subTitleLabel3);

		woodReceive = new JFormattedTextField();
		woodReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woodReceive);

		wheatReceive = new JFormattedTextField();
		wheatReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(wheatReceive);

		stoneReceive = new JFormattedTextField();
		stoneReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(stoneReceive);

		ironReceive = new JFormattedTextField();
		ironReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(ironReceive);

		woolReceive = new JFormattedTextField();
		woolReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woolReceive);

		woodHarbour = new JLabel("Je hebt hier");
		woodHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		woodHarbour.setForeground(TextColor);
		woodHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y, 200, 60);
		add(woodHarbour);

		wheatHarbour = new JLabel("Je hebt hier");
		wheatHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		wheatHarbour.setForeground(TextColor);
		wheatHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y * 2, 200, 60);
		add(wheatHarbour);

		stoneHarbour = new JLabel("Je hebt hier");
		stoneHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		stoneHarbour.setForeground(TextColor);
		stoneHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y * 3, 200, 60);
		add(stoneHarbour);

		ironHarbour = new JLabel("Je hebt hier");
		ironHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		ironHarbour.setForeground(TextColor);
		ironHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y * 4, 200, 60);
		add(ironHarbour);

		woolHarbour = new JLabel("Je hebt hier");
		woolHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		woolHarbour.setForeground(TextColor);
		woolHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y * 5, 200, 60);
		add(woolHarbour);

	}

	public JButton getSendRequestButton() {
		return sendRequestButton;
	}

	public JButton getReturnButton() {
		return returnButton;
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
	
	
	
}