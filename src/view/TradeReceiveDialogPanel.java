
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
public class TradeReceiveDialogPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_HEIGHT = 35;

	private final int TEXTFIELD_INPUT_WIDTH = 35;
	private final int TEXTFIELD_INPUT_HEIGHT = 20;
	private final int TEXTFIELD_OFFSET_X1 = 60;
	private final int TEXTFIELD_OFFSET_X2 = 255;
	private final int TEXTFIELD_OFFSET_Y = 35;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel, subTitleLabel1, subTitleLabel2, subTitleLabel3;
	private JLabel woodLabel, wheatLabel, brickLabel, ironLabel, woolLabel;

	private JButton counterOfferButton, returnButton;

	private JFormattedTextField woodGive, wheatGive, brickGive, ironGive, woolGive;

	private JFormattedTextField woodReceive, wheatReceive, brickReceive, ironReceive, woolReceive;

	private Player player;
	
	public TradeReceiveDialogPanel(Player player, TradeRequest tR) {
		
		this.player = player;
		
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

		createResourceInput(tR);

		counterOfferButton = new JButton("Accepteren/Tegenaabod");
		counterOfferButton.setBounds(10, 300, 200, BUTTON_HEIGHT);
		counterOfferButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		counterOfferButton.setBackground(textBackgroundColor);
		counterOfferButton.setForeground(TextColor);
		add(counterOfferButton);

		returnButton = new JButton("Afwijzen");
		returnButton.setBounds(230, 300, 100, BUTTON_HEIGHT);
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		returnButton.setBackground(textBackgroundColor);
		returnButton.setForeground(TextColor);
		add(returnButton);
	}

	private void createResourceInput(TradeRequest tr) {

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
		subTitleLabel1.setBounds(130, 40, 200, 60);
		add(subTitleLabel1);

		subTitleLabel2 = new JLabel("geven");
		subTitleLabel2.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel2.setForeground(TextColor);
		subTitleLabel2.setBounds(60, 50, 200, 60);
		add(subTitleLabel2);

		brickGive = new JFormattedTextField();
		brickGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		brickGive.setText("" + tr.getW_brick());
		brickGive.setEditable(false);
		add(brickGive);

		woolGive = new JFormattedTextField();
		woolGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		woolGive.setText("" + tr.getW_wool());
		woolGive.setEditable(false);
		add(woolGive);

		ironGive = new JFormattedTextField();
		ironGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		ironGive.setText("" + tr.getW_iron());
		ironGive.setEditable(false);
		add(ironGive);

		wheatGive = new JFormattedTextField();
		wheatGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		wheatGive.setText("" + tr.getW_wheat());
		wheatGive.setEditable(false);
		add(wheatGive);

		woodGive = new JFormattedTextField();
		woodGive.setBounds(TEXTFIELD_OFFSET_X1, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		woodGive.setText("" + tr.getW_wood());
		woodGive.setEditable(false);
		add(woodGive);

		brickLabel = new JLabel("Baksteen");
		brickLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y, 200, 60);
		add(brickLabel);

		woolLabel = new JLabel("Wol");
		woolLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y * 2, 200, 60);
		add(woolLabel);

		ironLabel = new JLabel("Erts");
		ironLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y * 3, 200, 60);
		add(ironLabel);

		wheatLabel = new JLabel("Graan");
		wheatLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y * 4, 200, 60);
		add(wheatLabel);

		woodLabel = new JLabel("Hout");
		woodLabel.setBounds(150, 50 + TEXTFIELD_OFFSET_Y * 5, 200, 60);
		add(woodLabel);

		subTitleLabel3 = new JLabel("ontvangen");
		subTitleLabel3.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel3.setForeground(TextColor);
		subTitleLabel3.setBounds(240, 50, 250, 60);
		add(subTitleLabel3);

		brickReceive = new JFormattedTextField(formatters[0]);
		brickReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		brickReceive.setText("" + tr.getG_brick());
		add(brickReceive);

		woolReceive = new JFormattedTextField(formatters[0]);
		woolReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 2, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		woolReceive.setText("" + tr.getG_wool());
		add(woolReceive);

		ironReceive = new JFormattedTextField(formatters[0]);
		ironReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 3, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		ironReceive.setText("" + tr.getG_iron());
		add(ironReceive);

		wheatReceive = new JFormattedTextField(formatters[0]);
		wheatReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 4, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		wheatReceive.setText("" + tr.getG_wheat());
		add(wheatReceive);

		woodReceive = new JFormattedTextField(formatters[0]);
		woodReceive.setBounds(TEXTFIELD_OFFSET_X2, 70 + TEXTFIELD_OFFSET_Y * 5, TEXTFIELD_INPUT_WIDTH,
				TEXTFIELD_INPUT_HEIGHT);
		woodReceive.setText("" + tr.getG_wood());
		add(woodReceive);

	}

	public JButton getCounterOfferButton() {
		return counterOfferButton;
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
	
	private int[] getResourceAmount() {
		int[] resources = new int[] { 0, 0, 0, 0, 0 };

		for (int i = 0; i < player.getHand().getResources().size(); i++) {
			ResourceType resourceType = player.getHand().getResources().get(i).getRsType();

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