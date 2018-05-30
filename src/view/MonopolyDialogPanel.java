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
	private final int PANEL_HEIGHT = 340;

	private final Dimension buttonDimension = new Dimension(220, 50);
	private final Border buttonBorder = BorderFactory.createEmptyBorder(10, 50, 10, 50);

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel;

	private JButton brickButton, woolButton, ironButton, wheatButton, woodButton;

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

		brickButton = new JButton("HOUT");
		brickButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		brickButton.setBackground(textBackgroundColor);
		brickButton.setForeground(TextColor);
		brickButton.setHorizontalAlignment(JLabel.CENTER);
		brickButton.setBorder(buttonBorder);
		brickButton.setSize(buttonDimension);
		add(brickButton);

		woolButton = new JButton("WOL");
		woolButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		woolButton.setBackground(textBackgroundColor);
		woolButton.setForeground(TextColor);
		woolButton.setHorizontalAlignment(JLabel.CENTER);
		woolButton.setBorder(buttonBorder);
		woolButton.setSize(buttonDimension);
		add(woolButton);

		ironButton = new JButton("ERTS");
		ironButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		ironButton.setBackground(textBackgroundColor);
		ironButton.setForeground(TextColor);
		ironButton.setHorizontalAlignment(JLabel.CENTER);
		ironButton.setBorder(buttonBorder);
		ironButton.setSize(buttonDimension);
		add(ironButton);

		wheatButton = new JButton("GRAAN");
		wheatButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		wheatButton.setBackground(textBackgroundColor);
		wheatButton.setForeground(TextColor);
		wheatButton.setHorizontalAlignment(JLabel.CENTER);
		wheatButton.setBorder(buttonBorder);
		wheatButton.setSize(buttonDimension);
		add(wheatButton);

		woodButton = new JButton("HOUT");
		woodButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		woodButton.setBackground(textBackgroundColor);
		woodButton.setForeground(TextColor);
		woodButton.setHorizontalAlignment(JLabel.CENTER);
		woodButton.setBorder(buttonBorder);
		woodButton.setSize(buttonDimension);
		add(woodButton);

	}

}