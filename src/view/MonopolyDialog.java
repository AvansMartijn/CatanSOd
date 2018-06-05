package view;

import javax.swing.JDialog;


@SuppressWarnings("serial")
public class MonopolyDialog extends JDialog {

	private MonopolyDialogPanel monopolyDialogPanel;

	public MonopolyDialog() {

		monopolyDialogPanel = new MonopolyDialogPanel();
		
		setUndecorated(true);
		
		setContentPane(monopolyDialogPanel);
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	public MonopolyDialogPanel getMonopolyDialogPanel() {
		return monopolyDialogPanel;
	}
}
