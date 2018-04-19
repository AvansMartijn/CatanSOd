package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuBarGUI extends JMenuBar {

	public MenuBarGUI() {

		JMenu file = new JMenu("File");
		JMenu testMode = new JMenu("Test mode");

		JMenuItem exit = new JMenuItem("exit");
		JMenuItem test1 = new JMenuItem("test 1");
		JMenuItem test2 = new JMenuItem("test 2");
		JMenuItem test3 = new JMenuItem("test 3");

		add(file);
		add(testMode);

		file.add(exit);
		testMode.add(test1);
		testMode.add(test2);
		testMode.add(test3);

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		test1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO add testmode
			}
		});

		test2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO add testmode
			}
		});

		test3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO add testmode
			}
		});
	}
}
