package view;



import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ContentPane extends JPanel{

	public ContentPane() {
		
		
		RegisterLoginPanel relogPanel = new RegisterLoginPanel();
		
		GameContentPane gameContent = new GameContentPane();
		
		add(gameContent);
		
	}
}
