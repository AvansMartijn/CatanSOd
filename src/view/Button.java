package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Button extends JPanel {
	private Font Bold = new Font("Arial", Font.BOLD, 30);

	private Color lightBlue = new Color(0, 0, 182, 155);
	private JButton button[];
	JButton thrown, build, buy, deal;

	public Button() {

	}

	public void buttons() {

		String text[] = { "throw", "build", "buy", "exchange" };
		String info[] = { "press if you want to throw the dices", "press if you want to build",
				"press if you want to use a developmentcard", "press if you want to change materials" };
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

	public JButton[] getButton() {
		return button;
	}

	public void setButton(JButton[] button) {
		this.button = button;
	}

}