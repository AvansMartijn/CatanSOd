package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends JPanel {

	private Font Bold = new Font("Arial", Font.BOLD, 14);
	private JButton button[];
	private JButton thrown, build, buy, deal;
	private String text[] = { "throw", "build", "buy", "exchange" };
	private String info[] = { "press if you want to throw the dices", "press if you want to build",
			"press if you want to use a developmentcard", "press if you want to change materials" };

	public Button() {
		throwb();

	}

	public void throwb() {
		button = new JButton[3];
		for (int count = 0; count < button.length; count++) {
			button[count] = new JButton(text[count]);
			this.add(button[count]);
			button[count].setForeground(Color.WHITE);
			button[count].setBackground(Color.orange);
			button[count].setFont(Bold);
			button[count].setMargin(new Insets(20, 20, 20, 20));
			button[count].setEnabled(true);
			button[count].setToolTipText(info[count]);
		}

	}
	
	public void paintComponent(Graphics g) {
		
	}
}