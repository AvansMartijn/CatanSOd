package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TopOptionsPanel extends JPanel {

	private JButton createGameButton;
	private JButton inviteButton;
	
	
	public TopOptionsPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		createGameButton = new JButton("Game aanmaken");
		add(createGameButton);
		
		inviteButton = new JButton("Uitnodigingen bekijken");
		add(inviteButton);
			
	}

	public JButton getCreateGameButton() {
		return createGameButton;
	}

	public JButton getInviteButton() {
		return inviteButton;
	}
}
