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

import model.Player;

@SuppressWarnings("serial")
public class RobberDialogPanel extends JPanel {

	private final int PANEL_WIDTH = 260;
	private final int PANEL_HEIGHT = 260;

	private final Dimension buttonDimension = new Dimension(220, 50);
	private final Border buttonBorder = BorderFactory.createEmptyBorder(10, 50, 10, 50);

	private Color backgroundColor = new Color(189, 133, 100);
	private Color textBackgroundColor = new Color(223, 190, 172);
	private Color TextColor = new Color(50, 50, 50);

	private JLabel titleLabel, subTitleLabel;

	private JButton player1Button, player2Button, player3Button;

	private Player player1, player2, player3;

	public RobberDialogPanel(Player player1, Player player2, Player player3) {

		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;

		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(backgroundColor);

		setLayout(new FlowLayout());

		titleLabel = new JLabel("STRUIKROVER");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		titleLabel.setForeground(TextColor);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);

		subTitleLabel = new JLabel("Van wie wil je grondstoffen stelen?");
		subTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		subTitleLabel.setForeground(TextColor);
		subTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		subTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(subTitleLabel);

		player1Button = new JButton(player1.getUsername());
		player1Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		player1Button.setBackground(textBackgroundColor);
		player1Button.setForeground(TextColor);
		player1Button.setHorizontalAlignment(JLabel.CENTER);
		player1Button.setBorder(buttonBorder);
		player1Button.setSize(buttonDimension);
		add(player1Button);

		player2Button = new JButton(player2.getUsername());
		player2Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		player2Button.setBackground(textBackgroundColor);
		player2Button.setForeground(TextColor);
		player2Button.setHorizontalAlignment(JLabel.CENTER);
		player2Button.setBorder(buttonBorder);
		player2Button.setSize(buttonDimension);
		add(player2Button);

		player3Button = new JButton(player3.getUsername());
		player3Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		player3Button.setBackground(textBackgroundColor);
		player3Button.setForeground(TextColor);
		player3Button.setHorizontalAlignment(JLabel.CENTER);
		player3Button.setBorder(buttonBorder);
		player3Button.setSize(buttonDimension);
		add(player3Button);

	}

	public JButton getPlayer1Button() {
		return player1Button;
	}

	public JButton getPlayer2Button() {
		return player2Button;
	}

	public JButton getPlayer3Button() {
		return player3Button;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getPlayer3() {
		return player3;
	}

	public void setPlayer3(Player player3) {
		this.player3 = player3;
	}

}