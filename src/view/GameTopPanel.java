package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameTopPanel extends JPanel {

	private final int HEIGHT = 40;

	// Instance variables
	private Color TopBarColor = new Color(189, 133, 95);
	private Color ButtonBackgroundColor = new Color(223, 190, 172);

	private JButton ChatButton; // TODO make it a chatLabel
	private JLabel GameLabel;
	private JButton GoToMainMenuButton;

	// Constructor
	public GameTopPanel(int myGameName) {
		create(myGameName);
	}

	// Create
	private void create(int myGameName) {
		setLayout(new BorderLayout());
		setBackground(TopBarColor);

		// Set size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		;
		double width = screenSize.getWidth();
		setPreferredSize(new Dimension((int) width, HEIGHT));

		// Get Image
		Image image = null;
		try {
			URL url = this.getClass().getResource("/images/chat.png");
			image = ImageIO.read(url);
			image = image.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}

		ChatButton = new JButton(new ImageIcon(image));
		ChatButton.setContentAreaFilled(false);
		ChatButton.setBorderPainted(false);
		GameLabel = new JLabel("Game " + myGameName);
		GameLabel.setHorizontalAlignment(JLabel.CENTER);
		GoToMainMenuButton = new JButton("Terug naar Spelmenu");
		GoToMainMenuButton.setBackground(ButtonBackgroundColor);

		add(ChatButton, BorderLayout.WEST);
		add(GameLabel, BorderLayout.CENTER);
		add(GoToMainMenuButton, BorderLayout.EAST);

	}
	
	public JButton getGoToMainMenuButton() {
		return GoToMainMenuButton;
	}
}
