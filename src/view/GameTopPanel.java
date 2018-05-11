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
	private Color myTopBarColor = new Color(189, 133, 95);
	private Color myButtonBackgroundColor = new Color(223, 190, 172);

	private JButton myChatButton;
	private JLabel myGameLabel;
	private JButton myGoToMainMenuButton;

	// Constructor
	public GameTopPanel(String myGameName) {
		create(myGameName);
	}
	
	// Create
	private void create(String myGameName) {
		setLayout(new BorderLayout());
		setBackground(myTopBarColor);
		
		// Set size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();;
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
		
		myChatButton = new JButton(new ImageIcon(image));
		myChatButton.setContentAreaFilled(false);
		myChatButton.setBorderPainted(false);
		myGameLabel = new JLabel("Game " + myGameName);
		myGameLabel.setHorizontalAlignment(JLabel.CENTER);
		myGoToMainMenuButton = new JButton("Terug naar Spelmenu");
		myGoToMainMenuButton.setBackground(myButtonBackgroundColor);
		
		add(myChatButton, BorderLayout.WEST);
		add(myGameLabel, BorderLayout.CENTER);
		add(myGoToMainMenuButton, BorderLayout.EAST);
	}
}
