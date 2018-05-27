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
	private final int TEXTFIELD_OFFSET = 140;
	private final int LABEL_OFFSET = 180;
	private final int TEXTFIELD_OFFSET_Y = 35;

	private final int RADIOBUTTON_SIZE = 20;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel1, subTitleLabel2, subTitleLabel3;

	private JLabel woodLabel, wheatLabel, brickLabel, ironLabel, woolLabel;

	private JLabel woodHarbour, wheatHarbour, brickHarbour, ironHarbour, woolHarbour;

	private JButton sendRequestButton, returnButton;

	private ButtonGroup give;

	private JRadioButton woodGive, wheatGive, brickGive, ironGive, woolGive;

	private JFormattedTextField woodReceive, wheatReceive, brickReceive, ironReceive, woolReceive;

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
		formatter.setMaximum(selfPlayer.getHand().getResources().size());
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

		brickGive = new JRadioButton();
		brickGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		brickGive.setBackground(backgroundColor);
		give.add(brickGive);
		add(brickGive);

		woolGive = new JRadioButton();
		woolGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 2, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		woolGive.setBackground(backgroundColor);
		give.add(woolGive);
		add(woolGive);

		ironGive = new JRadioButton();
		ironGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 3, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		ironGive.setBackground(backgroundColor);
		give.add(ironGive);
		add(ironGive);

		wheatGive = new JRadioButton();
		wheatGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 4, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		wheatGive.setBackground(backgroundColor);
		give.add(wheatGive);
		add(wheatGive);

		woodGive = new JRadioButton();
		woodGive.setBounds(RADIOBUTTON_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 5, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		woodGive.setBackground(backgroundColor);
		give.add(woodGive);
		add(woodGive);

		brickLabel = new JLabel("Baksteen");
		brickLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y, 200, 60);
		add(brickLabel);

		woolLabel = new JLabel("Wol");
		woolLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 2, 200, 60);
		add(woolLabel);

		ironLabel = new JLabel("Erts");
		ironLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 3, 200, 60);
		add(ironLabel);

		wheatLabel = new JLabel("Graan");
		wheatLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 4, 200, 60);
		add(wheatLabel);

		woodLabel = new JLabel("Hout");
		woodLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 5, 200, 60);
		add(woodLabel);

		subTitleLabel3 = new JLabel("ontvangen");
		subTitleLabel3.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel3.setForeground(TextColor);
		subTitleLabel3.setBounds(130, 50, 250, 60);
		add(subTitleLabel3);

		brickReceive = new JFormattedTextField();
		brickReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(brickReceive);

		woolReceive = new JFormattedTextField();
		woolReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woolReceive);

		ironReceive = new JFormattedTextField();
		ironReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(ironReceive);

		wheatReceive = new JFormattedTextField();
		wheatReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(wheatReceive);

		woodReceive = new JFormattedTextField();
		woodReceive.setBounds(TEXTFIELD_OFFSET, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woodReceive);

		brickHarbour = new JLabel();
		brickHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		brickHarbour.setForeground(TextColor);
		brickHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y, 200, 60);
		add(brickHarbour);

		woolHarbour = new JLabel();
		woolHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		woolHarbour.setForeground(TextColor);
		woolHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y * 2, 200, 60);
		add(woolHarbour);

		ironHarbour = new JLabel();
		ironHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		ironHarbour.setForeground(TextColor);
		ironHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y * 3, 200, 60);
		add(ironHarbour);

		wheatHarbour = new JLabel();
		wheatHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		wheatHarbour.setForeground(TextColor);
		wheatHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y * 4, 200, 60);
		add(wheatHarbour);

		woodHarbour = new JLabel();
		woodHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		woodHarbour.setForeground(TextColor);
		woodHarbour.setBounds(LABEL_OFFSET, 50 + TEXTFIELD_OFFSET_Y * 5, 200, 60);
		add(woodHarbour);

	}

	public void updateRatio(int[] resources) {

		brickHarbour.setText("Handelsratio: " + resources[0] + "/1");
		woolHarbour.setText("Handelsratio: " + resources[1] + "/1");
		ironHarbour.setText("Handelsratio: " + resources[2] + "/1");
		wheatHarbour.setText("Handelsratio: " + resources[3] + "/1");
		woodHarbour.setText("Handelsratio: " + resources[4] + "/1");

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
		return brickGive.getText();
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
		return brickReceive.getText();
	}

	public String getIronReceive() {
		return ironReceive.getText();
	}

	public String getWoolReceive() {
		return woolReceive.getText();
	}

}