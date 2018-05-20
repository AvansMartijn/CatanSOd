package view;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class BuildDialog extends JDialog {

	private BuildPanel buildPanel;

	public BuildDialog(PlayerActionPanel playerActionPanel) {

		setUndecorated(true);

		buildPanel = new BuildPanel();

		setContentPane(buildPanel);

		pack();
		setLocationRelativeTo(playerActionPanel);

		toFront();
		requestFocus();
		setVisible(true);
	}
}
