package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MonopolyDialogPanel extends JPanel {

	private final int PANEL_WIDTH = 260;
	private final int PANEL_HEIGHT = 380;

	private final Dimension buttonDimension = new Dimension(220, 50);
	private final Border buttonBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel;
	
	private String [] resourceNames;

	public MonopolyDialogPanel() {

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(new FlowLayout());

		titleLabel = new JLabel("MONOPOLY");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		titleLabel.setForeground(TextColor);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);

		subTitleLabel = new JLabel("welke grondstof wil je nemen?");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		subTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(subTitleLabel);

		resourceNames = new String[] {"BAKSTEEN","WOL", "ERTS","GRAAN", "HOUT",};
		
		for (int i = 0; i < resourceNames.length; i++) {
			JButton resourceButton = new JButton(resourceNames[i]);
			resourceButton.setFont(new Font("SansSerif", Font.BOLD, 20));
			resourceButton.setBackground(textBackgroundColor);
			resourceButton.setForeground(TextColor);
			resourceButton.setHorizontalAlignment(JLabel.CENTER);
			resourceButton.setBorder(buttonBorder);
			resourceButton.setPreferredSize(buttonDimension);
			add(resourceButton);
		}
	}

	public String[] getResourceNames() {
		return resourceNames;
	}
}