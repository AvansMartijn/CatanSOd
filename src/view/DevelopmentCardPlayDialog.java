package view;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class DevelopmentCardPlayDialog extends JDialog {

	private DevelopmentCardDialogPanel developmentCardDialogPanel;

	public DevelopmentCardPlayDialog(DevelopmentCardButton developmentCardButton) {

		developmentCardDialogPanel = new DevelopmentCardDialogPanel(developmentCardButton);
		
		setContentPane(developmentCardDialogPanel);
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(false);

	}

	public DevelopmentCardDialogPanel getDevelopmentCardDialogPanel() {
		return developmentCardDialogPanel;
	}
}
