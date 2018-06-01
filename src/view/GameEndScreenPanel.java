package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;

@SuppressWarnings("serial")
public class GameEndScreenPanel extends JPanel {
	
	private final int PANEL_WIDTH = 800;
	private final int PANEL_HEIGHT = 250;
	private final Font labelFont = new Font("Arial", Font.BOLD, 32);
	private Image image;
	private JLabel winOrLoseLabel;
	private boolean isWinner;
	private Player winner;
	
	public GameEndScreenPanel(boolean isWinner, Player winner) {
		this.winOrLoseLabel = new JLabel();
		this.winOrLoseLabel.setFont(labelFont);
		this.isWinner = isWinner;
		this.winner = winner;
		
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.GRAY);
		
		createEndingScreen();
	}
	
	private void createEndingScreen() {
		URL url = null;
		
		if(isWinner) {
			url = this.getClass().getResource("/images/Win_Image.png");
			winOrLoseLabel.setText("Gefeliciteerd " + winner.getUsername() + " met je overwinning!");
		} else {
			url = this.getClass().getResource("/images/Lose_Image.png");
			winOrLoseLabel.setText("Helaas! " + winner.getUsername() + " heeft gewonnen");
		}
		
		try {
			image = ImageIO.read(url);
			image = image.getScaledInstance((int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight(),
					Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
		add(winOrLoseLabel, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

}
