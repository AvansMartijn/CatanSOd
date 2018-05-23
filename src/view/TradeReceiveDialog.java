package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;

@SuppressWarnings("serial")
public class TradeReceiveDialog extends JDialog {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 150;
	private final int BUTTON_HEIGHT = 35;

	private final int TEXTFIELD_INPUT_WIDTH = 35;
	private final int TEXTFIELD_INPUT_HEIGHT = 20;
	private final int TEXTFIELD_OFFSET_X1 = 60;
	private final int TEXTFIELD_OFFSET_X2 = 255;
	private final int TEXTFIELD_OFFSET_Y = 35;

	// requests for sending the trade offer
	// private int[] resourceOffer;
	// private int[] resourceRequest;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel, subTitleLabel1, subTitleLabel2, subTitleLabel3;
	private JLabel woodLabel, wheatLabel, stoneLabel, ironLabel, woolLabel;

	private JButton sendRequestButton, returnButton;

	private JFormattedTextField woodGive, wheatGive, stoneGive, ironGive, woolGive;

	private JFormattedTextField woodReceive, wheatReceive, stoneReceive, ironReceive, woolReceive;

	public TradeReceiveDialog() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		titleLabel = new JLabel("HANDELSVERZOEK");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(25, 10, 320, 40);
		add(titleLabel);

		subTitleLabel = new JLabel("Speler * heeft een handelsverzoek verstuurd");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setBounds(40, 35, 280, 40);
		add(subTitleLabel);

		createResourceInput();

		sendRequestButton = new JButton("Tegenbod versturen");
		sendRequestButton.setBounds(10, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
		sendRequestButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		sendRequestButton.setBackground(textBackgroundColor);
		sendRequestButton.setForeground(TextColor);
		add(sendRequestButton);

		returnButton = new JButton("Tegenbod afwijzen");
		returnButton.setBounds(170, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
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
		formatter.setMaximum(100);
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(true);
		formatter.setOverwriteMode(true);

		subTitleLabel1 = new JLabel("grondstoffen");
		subTitleLabel1.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel1.setForeground(TextColor);
		subTitleLabel1.setBounds(130, 40, 200, 60);
		add(subTitleLabel1);

		subTitleLabel2 = new JLabel("geven");
		subTitleLabel2.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel2.setForeground(TextColor);
		subTitleLabel2.setBounds(60, 50, 200, 60);
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
		woodLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y, 200, 60);
		add(woodLabel);

		wheatLabel = new JLabel("Graan");
		wheatLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y * 2, 200, 60);
		add(wheatLabel);

		stoneLabel = new JLabel("Steen");
		stoneLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y * 3, 200, 60);
		add(stoneLabel);

		ironLabel = new JLabel("Ijzer");
		ironLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y * 4, 200, 60);
		add(ironLabel);

		woolLabel = new JLabel("Wol");
		woolLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y * 5, 200, 60);
		add(woolLabel);

		subTitleLabel3 = new JLabel("ontvangen");
		subTitleLabel3.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel3.setForeground(TextColor);
		subTitleLabel3.setBounds(240, 50, 250, 60);
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