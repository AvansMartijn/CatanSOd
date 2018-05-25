package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.ResourceType;

@SuppressWarnings("serial")
public class TradeBankPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 160;
	private final int BUTTON_HEIGHT = 35;

	private final int BUTTON_OFFSET_X = 20;
	private final int RADIOBUTTON2_OFFSET_Y = 35;

	private final int RADIOBUTTON1_OFFSET = 60;
	private final int RADIOBUTTON2_OFFSET = 240;

	private final int LABEL_OFFSET = 90;

	private final int RADIOBUTTON_SIZE = 20;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel1, subTitleLabel2;

	private JLabel woodHarbour, wheatHarbour, brickHarbour, ironHarbour, woolHarbour;

	private JButton sendRequestButton, returnButton;

	private ButtonGroup giveButtons, receiveButtons;

	private JRadioButton woodGive, wheatGive, brickGive, ironGive, woolGive;

	private JRadioButton woodReceive, wheatReceive, brickReceive, ironReceive, woolReceive;

	public TradeBankPanel() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		titleLabel = new JLabel("HANDELEN");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(80, 10, 280, 40);
		add(titleLabel);

		createResourceInput();
		
		addResourceLabels();

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

		subTitleLabel1 = new JLabel("geven");
		subTitleLabel1.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel1.setForeground(TextColor);
		subTitleLabel1.setBounds(50, 50, 200, 60);
		add(subTitleLabel1);

		giveButtons = new ButtonGroup();

		brickGive = new JRadioButton();
		brickGive.setBounds(RADIOBUTTON1_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		brickGive.setBackground(backgroundColor);
		brickGive.setActionCommand("brick");
		giveButtons.add(brickGive);
		add(brickGive);

		woolGive = new JRadioButton();
		woolGive.setBounds(RADIOBUTTON1_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y * 2, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		woolGive.setBackground(backgroundColor);
		woolGive.setActionCommand("wool");
		giveButtons.add(woolGive);
		add(woolGive);

		ironGive = new JRadioButton();
		ironGive.setBounds(RADIOBUTTON1_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y * 3, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		ironGive.setBackground(backgroundColor);
		ironGive.setActionCommand("iron");
		giveButtons.add(ironGive);
		add(ironGive);

		wheatGive = new JRadioButton();
		wheatGive.setBounds(RADIOBUTTON1_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y * 4, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		wheatGive.setBackground(backgroundColor);
		wheatGive.setActionCommand("wheat");
		giveButtons.add(wheatGive);
		add(wheatGive);

		woodGive = new JRadioButton();
		woodGive.setBounds(RADIOBUTTON1_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y * 5, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		woodGive.setBackground(backgroundColor);
		woodGive.setActionCommand("wood");
		giveButtons.add(woodGive);
		add(woodGive);

		subTitleLabel2 = new JLabel("ontvangen");
		subTitleLabel2.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel2.setForeground(TextColor);
		subTitleLabel2.setBounds(230, 50, 250, 60);
		add(subTitleLabel2);

		receiveButtons = new ButtonGroup();

		brickReceive = new JRadioButton();
		brickReceive.setBounds(RADIOBUTTON2_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		brickReceive.setBackground(backgroundColor);
		brickReceive.setActionCommand("brick");
		receiveButtons.add(brickReceive);
		add(brickReceive);

		woolReceive = new JRadioButton();
		woolReceive.setBounds(RADIOBUTTON2_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y * 2, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		woolReceive.setBackground(backgroundColor);
		woolReceive.setActionCommand("wool");
		receiveButtons.add(woolReceive);
		add(woolReceive);

		ironReceive = new JRadioButton();
		ironReceive.setBounds(RADIOBUTTON2_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y * 3, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		ironReceive.setBackground(backgroundColor);
		ironReceive.setActionCommand("iron");
		receiveButtons.add(ironReceive);
		add(ironReceive);

		wheatReceive = new JRadioButton();
		wheatReceive.setBounds(RADIOBUTTON2_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y * 4, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		wheatReceive.setBackground(backgroundColor);
		wheatReceive.setActionCommand("wheat");
		receiveButtons.add(wheatReceive);
		add(wheatReceive);

		woodReceive = new JRadioButton();
		woodReceive.setBounds(RADIOBUTTON2_OFFSET, 70 + RADIOBUTTON2_OFFSET_Y * 5, RADIOBUTTON_SIZE, RADIOBUTTON_SIZE);
		woodReceive.setBackground(backgroundColor);
		woodReceive.setActionCommand("wood");
		receiveButtons.add(woodReceive);
		add(woodReceive);

	}

	private void addResourceLabels() {
		
		brickHarbour = new JLabel();
		brickHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		brickHarbour.setForeground(TextColor);
		brickHarbour.setBounds(LABEL_OFFSET, 50 + RADIOBUTTON2_OFFSET_Y, 200, 60);
		add(brickHarbour);

		woolHarbour = new JLabel();
		woolHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		woolHarbour.setForeground(TextColor);
		woolHarbour.setBounds(LABEL_OFFSET, 50 + RADIOBUTTON2_OFFSET_Y * 2, 200, 60);
		add(woolHarbour);

		ironHarbour = new JLabel();
		ironHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		ironHarbour.setForeground(TextColor);
		ironHarbour.setBounds(LABEL_OFFSET, 50 + RADIOBUTTON2_OFFSET_Y * 3, 200, 60);
		add(ironHarbour);

		wheatHarbour = new JLabel();
		wheatHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		wheatHarbour.setForeground(TextColor);
		wheatHarbour.setBounds(LABEL_OFFSET, 50 + RADIOBUTTON2_OFFSET_Y * 4, 200, 60);
		add(wheatHarbour);

		woodHarbour = new JLabel();
		woodHarbour.setFont(new Font("SansSerif", Font.BOLD, 12));
		woodHarbour.setForeground(TextColor);
		woodHarbour.setBounds(LABEL_OFFSET, 50 + RADIOBUTTON2_OFFSET_Y * 5, 200, 60);
		add(woodHarbour);
	}

	public void updateRatio(int[] resources) {

		brickHarbour.setText("Baksteen " + resources[0] + ":" + "1");
		woolHarbour.setText("Wol      " + resources[1] + ":" + "1");
		ironHarbour.setText("Erts     " + resources[2] + ":" + "1");
		wheatHarbour.setText("Graan    " + resources[3] + ":" + "1");
		woodHarbour.setText("Hout     " + resources[4] + ":" + "1");

	}

	public JButton getSendRequestButton() {
		return sendRequestButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}

	public ButtonGroup getGiveButtonGroup() {
		return giveButtons;
	}

	public ButtonGroup getReceiveButtonGroup() {
		return receiveButtons;
	}

	public ResourceType getSelectedResourceType(ButtonGroup buttonGroup) {

		ResourceType giveResource = null;

		switch (buttonGroup.getSelection().getActionCommand()) {

		case "brick":
			giveResource = ResourceType.BAKSTEEN;
			break;
		case "iron":
			giveResource = ResourceType.ERTS;
			break;
		case "wool":
			giveResource = ResourceType.WOL;
			break;
		case "wheat":
			giveResource = ResourceType.GRAAN;
			break;
		case "wood":
			giveResource = ResourceType.HOUT;
			break;
		default:
			break;
		}
		return giveResource;
	}
}