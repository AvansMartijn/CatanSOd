package view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	private GameGUIPanel gameGUIPanel;
	
	// Constructor
	public Frame() {
		gameGUIPanel = new GameGUIPanel();
		setContentPane(gameGUIPanel);
		setTitle("Kolonisten van Catan - SOd");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Why setResizable(false)?
		setResizable(false);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
