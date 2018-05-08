package view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	private GameGUIPanel gameGUIPanel = new GameGUIPanel();
	
	// Constructor
	public Frame() {
		setContentPane(gameGUIPanel);
		setTitle("Kolonisten van Catan - SOd");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
