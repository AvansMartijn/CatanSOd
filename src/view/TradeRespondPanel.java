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
	private final int TEXTFIELD_OFFSET_X1 = 20;
	private final int TEXTFIELD_OFFSET_X2 = 215;
	private final int TEXTFIELD_OFFSET_Y = 30;

	// requests for sending the trade offer
	// private int[] resourceOffer;
	// private int[] resourceRequest;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel, subTitleLabel1, subTitleLabel2, subTitleLabel3;
	private JLabel woodLabel, wheatLabel, brickLabel, ironLabel, woolLabel;

	private JFormattedTextField woodGive, wheatGive, brickGive, ironGive, woolGive;
	private JFormattedTextField woodReceive, wheatReceive, brickReceive, ironReceive, woolReceive;

	private JButton sendRequestButton;
	
	private TradeRequest tR;
	
	private Player selfPlayer;

	public TradeRespondPanel(String playerName, TradeRequest tR) {

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
		add(sendRequestButton);

	}

	private void createResourceInput() {

		int[] resourceTypes = new int[5];
		resourceTypes = getResourceAmount();
		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter[] formatters = new NumberFormatter[5];

		for (int i = 0; i < resourceTypes.length; i++) {

			formatters[i] = new NumberFormatter(format);
			formatters[i].setValueClass(Integer.class);
			formatters[i].setMinimum(0);
			formatters[i].setMaximum(resourceTypes[i]);
			formatters[i].setAllowsInvalid(true);
			formatters[i].setCommitsOnValidEdit(true);
			formatters[i].setOverwriteMode(true);

		}

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

		brickReceive = new JFormattedTextField(formatters[0]);
		brickReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(brickReceive);

		woolReceive = new JFormattedTextField(formatters[1]);
		woolReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woolReceive);

		ironReceive = new JFormattedTextField(formatters[2]);
		ironReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(ironReceive);

		wheatReceive = new JFormattedTextField(formatters[3]);
		wheatReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(wheatReceive);

		woodReceive = new JFormattedTextField(formatters[4]);
		woodReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woodReceive);
	}

	public JButton getSendRequestButton() {
		return sendRequestButton;
	}

	public int getWoodGive() {
		return Integer.parseInt(woodGive.getText());
	}

	public int getWheatGive() {
		return Integer.parseInt(wheatGive.getText());
	}

	public int getBrickGive() {
		return Integer.parseInt(brickGive.getText());
	}

	public int getIronGive() {
		return Integer.parseInt(ironGive.getText());
	}

	public int getWoolGive() {
		return Integer.parseInt(woolGive.getText());
	}

	public int getWoodReceive() {
		return Integer.parseInt(woodReceive.getText());
	}

	public int getWheatReceive() {
		return Integer.parseInt(wheatReceive.getText());
	}

	public int getBrickReceive() {
		return Integer.parseInt(brickReceive.getText());
	}

	public int getIronReceive() {
		return Integer.parseInt(ironReceive.getText());
	}

	public int getWoolReceive() {
		return Integer.parseInt(woolReceive.getText());
	}
	
	private int[] getResourceAmount() {
		int[] resources = new int[] { 0, 0, 0, 0, 0 };

		for (int i = 0; i < selfPlayer.getHand().getResources().size(); i++) {
			ResourceType resourceType = selfPlayer.getHand().getResources().get(i).getRsType();

			switch (resourceType) {
			case BAKSTEEN:
				resources[0]++;
				break;
			case WOL:
				resources[1]++;
				break;
			case ERTS:
				resources[2]++;
				break;
			case GRAAN:
				resources[3]++;
				break;
			case HOUT:
				resources[4]++;
				break;
			default:
				// TODO maybe?
			}
		}
		return resources;
	}
	
}