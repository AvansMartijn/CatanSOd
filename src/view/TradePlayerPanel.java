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

@SuppressWarnings("serial")
public class TradePlayerPanel extends JPanel {

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

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel1, subTitleLabel2, subTitleLabel3;

	private JLabel woodLabel, wheatLabel, brickLabel, ironLabel, woolLabel;

	private JButton sendRequestButton, returnButton;

	private JFormattedTextField woodGive, wheatGive, brickGive, ironGive, woolGive;

	private JFormattedTextField woodReceive, wheatReceive, brickReceive, ironReceive, woolReceive;

	private Player selfPlayer;

	public TradePlayerPanel(Player selfPlayer) {
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
				
			}
		}
		return resources;
	}

	public void createResourceInput() {

		// brick, wool, iron, wheat, wood
		NumberFormatter[] formatters = new NumberFormatter[5];
		NumberFormat format = NumberFormat.getInstance();

		int[] resourceTypes = new int[5];
		resourceTypes = getResourceAmount();

		for (int i = 0; i < resourceTypes.length; i++) {
			formatters[i] = new NumberFormatter(format);
			formatters[i].setValueClass(Integer.class);
			formatters[i].setMinimum(0);
			formatters[i].setMaximum(19);
			formatters[i].setAllowsInvalid(true);
			formatters[i].setCommitsOnValidEdit(true);
			formatters[i].setOverwriteMode(true);
		}

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

		brickGive = new JFormattedTextField(formatters[0]);
		brickGive.setText("0");
		brickGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(brickGive);

		woolGive = new JFormattedTextField(formatters[1]);
		woolGive.setText("0");
		woolGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woolGive);

		ironGive = new JFormattedTextField(formatters[2]);
		ironGive.setText("0");
		ironGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(ironGive);

		wheatGive = new JFormattedTextField(formatters[3]);
		wheatGive.setText("0");
		wheatGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(wheatGive);

		woodGive = new JFormattedTextField(formatters[4]);
		woodGive.setText("0");
		woodGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woodGive);

		brickLabel = new JLabel("Baksteen");
		brickLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y, 200, 60);
		brickLabel.setHorizontalAlignment(JLabel.CENTER);
		add(brickLabel);

		woolLabel = new JLabel("Wol");
		woolLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 2, 200, 60);
		woolLabel.setHorizontalAlignment(JLabel.CENTER);
		add(woolLabel);

		ironLabel = new JLabel("Erts");
		ironLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 3, 200, 60);
		ironLabel.setHorizontalAlignment(JLabel.CENTER);
		add(ironLabel);

		wheatLabel = new JLabel("Graan");
		wheatLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 4, 200, 60);
		wheatLabel.setHorizontalAlignment(JLabel.CENTER);
		add(wheatLabel);

		woodLabel = new JLabel("Hout");
		woodLabel.setBounds(70, 50 + TEXTFIELD_OFFSET_Y * 5, 200, 60);
		woodLabel.setHorizontalAlignment(JLabel.CENTER);
		add(woodLabel);

		subTitleLabel3 = new JLabel("ontvangen");
		subTitleLabel3.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel3.setForeground(TextColor);
		subTitleLabel3.setBounds(240, 50, 250, 60);
		add(subTitleLabel3);

		NumberFormatter formatter = new NumberFormatter(format);

		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(19);
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(true);
		formatter.setOverwriteMode(true);

		brickReceive = new JFormattedTextField(formatter);
		brickReceive.setText("0");
		brickReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(brickReceive);

		woolReceive = new JFormattedTextField(formatter);
		woolReceive.setText("0");
		woolReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woolReceive);

		ironReceive = new JFormattedTextField(formatter);
		ironReceive.setText("0");
		ironReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(ironReceive);

		wheatReceive = new JFormattedTextField(formatter);
		wheatReceive.setText("0");
		wheatReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(wheatReceive);

		woodReceive = new JFormattedTextField(formatter);
		woodReceive.setText("0");
		woodReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		add(woodReceive);

	}

	public JButton getSendRequestButton() {
		return sendRequestButton;
	}

	public JButton getReturnButton() {
		return returnButton;
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