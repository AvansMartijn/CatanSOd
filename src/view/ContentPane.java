package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ContentPane extends JPanel {

	public ContentPane() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel welcome = new JLabel("Welkom bij Kolonisten van Catan");
		welcome.setFont(new Font("Arial", Font.PLAIN, 30));
		welcome.setPreferredSize(new Dimension(600,300));
		add(welcome);

		RegisterLoginPanel relogPanel = new RegisterLoginPanel();

		add(relogPanel);

	}
}
