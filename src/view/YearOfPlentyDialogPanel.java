package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class YearOfPlentyDialogPanel extends JPanel {

	private final int PANEL_WIDTH = 340;
	private final int PANEL_HEIGHT = 350;

	private final int BUTTON_WIDTH = 160;
	private final int BUTTON_HEIGHT = 35;

	private final int BUTTON_OFFSET_X = 80;
	private final int RADIOBUTTON2_OFFSET_Y = 35;

	private final int RADIOBUTTON1_OFFSET = 60;
	private final int RADIOBUTTON2_OFFSET = 240;

	private final int LABEL_OFFSET = 60;

	private final int RADIOBUTTON_SIZE = 20;

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel1, subTitleLabel2, subTitleLabel3;

	private JButton sendRequestButton;

	private ButtonGroup getResourceButtons1, getResourceButtons2;

	private String[] resourceNames1, resourceNames2;

	public YearOfPlentyDialogPanel() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(null);

		titleLabel = new JLabel("Uitvinding");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
		titleLabel.setForeground(TextColor);
		titleLabel.setBounds(30, 10, 280, 40);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);

		subTitleLabel1 = new JLabel("Welke grondstoffen wil je ontvangen?");
		subTitleLabel1.setFont(new Font("SansSerif", Font.BOLD, 15));
		subTitleLabel1.setForeground(TextColor);
		subTitleLabel1.setBounds(30, 40, 280, 40);
		subTitleLabel1.setHorizontalAlignment(JLabel.CENTER);
		add(subTitleLabel1);

		createResourceInput();

		sendRequestButton = new JButton("Nemen");
		sendRequestButton.setBounds(BUTTON_OFFSET_X, 280, BUTTON_WIDTH, BUTTON_HEIGHT);
		sendRequestButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		sendRequestButton.setBackground(textBackgroundColor);
		sendRequestButton.setForeground(TextColor);
		sendRequestButton.setHorizontalAlignment(JButton.CENTER);
		add(sendRequestButton);

	}

	private void createResourceInput() {

		subTitleLabel2 = new JLabel("Eerste grondstof");
		subTitleLabel2.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel2.setForeground(TextColor);
		subTitleLabel2.setBounds(30, 50, 200, 60);
		add(subTitleLabel2);

		getResourceButtons1 = new ButtonGroup();

		resourceNames1 = new String[] { "BAKSTEEN", "WOL", "ERTS", "GRAAN", "HOUT" };

		for (int i = 0; i < resourceNames1.length; i++) {
			JRadioButton resourceRadioButton = new JRadioButton();
			resourceRadioButton.setBounds(RADIOBUTTON1_OFFSET, 110 + (RADIOBUTTON2_OFFSET_Y * i), RADIOBUTTON_SIZE,
					RADIOBUTTON_SIZE);
			resourceRadioButton.setBackground(backgroundColor);
			resourceRadioButton.setActionCommand(resourceNames1[i]);
			getResourceButtons1.add(resourceRadioButton);
			add(resourceRadioButton);

			JLabel resourceLabel = new JLabel(resourceNames1[i]);
			resourceLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
			resourceLabel.setForeground(TextColor);
			resourceLabel.setBounds(LABEL_OFFSET, 90 + (RADIOBUTTON2_OFFSET_Y * i), 200, 60);
			resourceLabel.setHorizontalAlignment(JLabel.CENTER);
			add(resourceLabel);

			if (i == 0) {
				resourceRadioButton.setSelected(true);
			}
		}

		subTitleLabel3 = new JLabel("Tweede grondstof");
		subTitleLabel3.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel3.setForeground(TextColor);
		subTitleLabel3.setBounds(200, 50, 250, 60);
		add(subTitleLabel3);

		getResourceButtons2 = new ButtonGroup();

		resourceNames2 = new String[] { "brick", "wool", "iron", "wheat", "wood" };

		for (int i = 0; i < resourceNames2.length; i++) {
			JRadioButton resourceRadioButton = new JRadioButton();
			resourceRadioButton.setBounds(RADIOBUTTON2_OFFSET, 110 + (RADIOBUTTON2_OFFSET_Y * i), RADIOBUTTON_SIZE,
					RADIOBUTTON_SIZE);
			resourceRadioButton.setBackground(backgroundColor);
			resourceRadioButton.setActionCommand(resourceNames2[i]);
			getResourceButtons2.add(resourceRadioButton);
			add(resourceRadioButton);
			if (i == 0) {
				resourceRadioButton.setSelected(true);
			}
		}
	}
	
	public void setCountedBankResources() {
		
		
		
	}

	public ButtonGroup getGetResourceButtons1() {
		return getResourceButtons1;
	}

	public void setGetResourceButtons1(ButtonGroup getResourceButtons1) {
		this.getResourceButtons1 = getResourceButtons1;
	}

	public ButtonGroup getGetResourceButtons2() {
		return getResourceButtons2;
	}

	public void setGetResourceButtons2(ButtonGroup getResourceButtons2) {
		this.getResourceButtons2 = getResourceButtons2;
	}

	public JButton getSendRequestButton() {
		return sendRequestButton;
	}

}