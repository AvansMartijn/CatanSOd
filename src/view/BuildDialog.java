package view;


import java.awt.Component;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class BuildDialog extends JDialog {

	private BuildPanel buildPanel;

	public BuildDialog(Component location) {

		setUndecorated(true);

		buildPanel = new BuildPanel();

		setContentPane(buildPanel);

		pack();
		setLocationRelativeTo(location);

		setVisible(true);
		toFront();
		requestFocus();

	}
}
