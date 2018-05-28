package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class MainMenuGUI extends JPanel {
	
	private final int PANEL_WIDTH = 520;
	private final int PANEL_HEIGHT = 800;
	private final int SCROLLPANE_WIDTH = 520;
	private final int SCROLLPANE_HEIGHT = 700;
	private final int SCROLLPANE_INCREMENT = 20;
	
	private Color backgroundColor = new Color(240, 226, 223);
	private Color innerColor = new Color(189, 133, 100);

	private RecentGamesPanel currentGames;
	private JPanel mainPanel;
	private String username;
	private JPanel topOptionsPanel;
	private JScrollPane scrollPane;
	private JPanel bottomOptionsPanel;
	private int pageNr;
	private Image image;

	public MainMenuGUI(String username, JPanel topOptionsPanel, JPanel bottomOptionsPanel, RecentGamesPanel currentGames) {
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		setBackground(backgroundColor);
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.topOptionsPanel = topOptionsPanel;
		this.bottomOptionsPanel = bottomOptionsPanel;
		this.currentGames = currentGames;
		this.username = username;
		pageNr = 0;

		mainPanel.add(new Title());
		mainPanel.add(this.topOptionsPanel);
		mainPanel.setBackground(innerColor);

		scrollPane = new JScrollPane(currentGames, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLLPANE_INCREMENT);
		scrollPane.setPreferredSize(new Dimension(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT));
		mainPanel.add(scrollPane);
		
		mainPanel.add(this.bottomOptionsPanel);
		
		GridBagLayout gridLayout = new GridBagLayout();
		setLayout(gridLayout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;

        gridLayout.setConstraints(mainPanel, constraints);
        add(mainPanel, constraints);
        
		URL url = this.getClass().getResource("/images/MainMenuBackground.jpg");

		try {
			image = ImageIO.read(url);
			image = image.getScaledInstance((int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight(),
					Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	public JPanel getCurrentGamesPanel() {
		return currentGames;
	}

	public class Title extends JPanel {
		
		private final int PANEL_WIDTH = 400;
		private final int PANEL_HEIGHT = 20;
		
		public Title() {
			setBackground(innerColor);
			this.add(new JLabel("Welkom terug, " + username + "!")); // Must be logged in user.
			this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		}
	}

	public int getPageNr() {
		return pageNr;
	}

	public void setPageNr(int pageNr) {
		this.pageNr = pageNr;
	}
}
