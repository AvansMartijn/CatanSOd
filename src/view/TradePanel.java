package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

@SuppressWarnings("serial")
public class TradePanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 160;
	private final int BUTTON_HEIGHT = 35;

	private final int BUTTON_OFFSET_X = 20;

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

	private JLabel titleLabel;
	private JLabel subTitleLabel1;
	private JLabel subTitleLabel2;
	private JLabel subTitleLabel3;

	private JLabel woodLabel;
	private JLabel wheatLabel;
	private JLabel stoneLabel;
	private JLabel ironLabel;
	private JLabel woolLabel;

	private JButton sendRequestButton;
	private JButton returnButton;

	private JFormattedTextField woodGive;
	private JFormattedTextField wheatGive;
	private JFormattedTextField stoneGive;
	private JFormattedTextField ironGive;
	private JFormattedTextField woolGive;

	private JFormattedTextField woodRecieve;
	private JFormattedTextField wheatRecieve;
	private JFormattedTextField stoneRecieve;
	private JFormattedTextField ironRecieve;
	private JFormattedTextField woolRecieve;

	public TradePanel() {

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
		formatter.setMaximum(19);
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(true);
		formatter.setOverwriteMode(true);

		subTitleLabel1 = new JLabel("grondstoffen:");
		subTitleLabel1.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel1.setForeground(TextColor);
		subTitleLabel1.setBounds(130, 30, 200, 60);
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

		woodRecieve = new JFormattedTextField(formatter);
		woodRecieve.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woodRecieve);

		wheatRecieve = new JFormattedTextField(formatter);
		wheatRecieve.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(wheatRecieve);

		stoneRecieve = new JFormattedTextField(formatter);
		stoneRecieve.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(stoneRecieve);

		ironRecieve = new JFormattedTextField(formatter);
		ironRecieve.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(ironRecieve);

		woolRecieve = new JFormattedTextField(formatter);
		woolRecieve.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woolRecieve);

	}

	public JButton getSendRequestButton() {
		return sendRequestButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}

	public JTextField getWoodGive() {
		return woodGive;
	}

	public JTextField getWheatGive() {
		return wheatGive;
	}

	public JTextField getStoneGive() {
		return stoneGive;
	}

	public JTextField getIronGive() {
		return ironGive;
	}

	public JTextField getWoolGive() {
		return woolGive;
	}

	public JTextField getWoodRecieve() {
		return woodRecieve;
	}

	public JTextField getWheatRecieve() {
		return wheatRecieve;
	}

	public JTextField getStoneRecieve() {
		return stoneRecieve;
	}

	public JTextField getIronRecieve() {
		return ironRecieve;
	}

	public JTextField getWoolRecieve() {
		return woolRecieve;
	}
}