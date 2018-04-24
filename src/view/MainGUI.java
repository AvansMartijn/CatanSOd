package view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {

	public MainGUI() {
		
		setTitle("Catan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBarGUI menuBar = new MenuBarGUI();	
		setJMenuBar(menuBar);
		
		ContentPane contentPane = new ContentPane();
		add(contentPane);
		
		pack();
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setVisible(true);

	}
	
	
	
}
