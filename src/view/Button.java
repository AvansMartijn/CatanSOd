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
	private JButton gooien, bouwen, kopen, ruilen;
	private String text[] = { "gooien", "bouwen", "kopen", "ruilen" };
	private String info[] = { "klik als je wit dobbellen", "klik als je wilt bouwen",
			"klik als je een developmentcard wilt kopen", "klik als u grondstoffen wilt ruilen" };

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