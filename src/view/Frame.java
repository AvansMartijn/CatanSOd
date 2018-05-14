package view;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	private GameGUIPanel gameGUIPanel = new GameGUIPanel();
	waitingRoom room = new waitingRoom();
	// Constructor
	public Frame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setContentPane(gameGUIPanel);

		setTitle("Kolonisten van Catan - SOd");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {

		new Frame();
	}
}
