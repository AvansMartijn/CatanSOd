package view;

import javax.swing.JDialog;

import model.Player;

@SuppressWarnings("serial")
public class YearOfPlentyDialog extends JDialog {

	private YearOfPlentyDialogPanel yearOfPlentyDialogPanel;

	public YearOfPlentyDialog() {

	yearOfPlentyDialogPanel = new YearOfPlentyDialogPanel();
		
		setUndecorated(true);		
		setContentPane(yearOfPlentyDialogPanel);
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);

	}

	public YearOfPlentyDialogPanel getYearOfPlentyDialogPanel() {
		return yearOfPlentyDialogPanel;
	}
}
