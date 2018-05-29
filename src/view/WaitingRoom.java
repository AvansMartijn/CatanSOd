package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.*;

@SuppressWarnings("serial")
public class WaitingRoom extends JPanel {

	private Image image;
	private Color bottomPanelBarColor = new Color(0, 0, 0, 50);
	private final int BOTTOMPANELBAR_HEIGHT = 80;
	private JButton exitButton;
	private JPanel bottomBarPanel;

	public WaitingRoom() {
		this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		this.setLayout(new BorderLayout());
		exitButton = new JButton("Terug");
		exitButton.setSize(20, 20);

		this.setImage();
		this.createBottomPanel();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	private void createBottomPanel() {
		bottomBarPanel = new JPanel();
		makeTauntbuttons();
		bottomBarPanel.setPreferredSize(new Dimension(this.getWidth(), BOTTOMPANELBAR_HEIGHT));
		bottomBarPanel.setBackground(bottomPanelBarColor);
		bottomBarPanel.add(exitButton);
		this.add(bottomBarPanel, BorderLayout.SOUTH);
	}

	private void setImage() {
		URL url = this.getClass().getResource("/images/WaitingRoomBackground.png");

		try {
			image = ImageIO.read(url);
			image = image.getScaledInstance((int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight(),
					Image.SCALE_DEFAULT);
		} catch (IOException e) {
		}
	}

	public void makeTauntbuttons() {

		Font Bold = new Font("Arial", Font.BOLD, 20);

		JButton[] tauntButtons;
		tauntButtons = new JButton[8];
		String text[] = { "Bruh", "Laugh", "Hallelujah", "Mlg", "NoGod", "Wrong", "Sparta", "Metalgear" };
		String info = "press if you want to have a funny sound";

		for (int count = 0; count < tauntButtons.length; count++) {
			tauntButtons[count] = new JButton(text[count]);
			tauntButtons[count].setForeground(Color.WHITE);
			tauntButtons[count].setBackground(Color.BLACK);
			tauntButtons[count].setFont(Bold);
			tauntButtons[count].setSize(20, 20);
			tauntButtons[count].setMargin(new Insets(20, 20, 20, 20));
			tauntButtons[count].setEnabled(true);
			tauntButtons[count].setToolTipText(info);
			tauntButtons[count].setVisible(true);
			tauntButtons[count].setBorderPainted(true);
			bottomBarPanel.add(tauntButtons[count]);
		}

		String[] soundPaths = new String[] { "BRUH - Original - Vine Version.wav",
				"/Music/Funny Laugh Sound- No No No No.wav" };

//		for (int i = 0; i < tauntButtons.length; i++) {
//			tauntButtons[i].addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					playSound("/Music/" + soundPaths[i]);
//				}
//			});
//		}

		tauntButtons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound("/Music/BRUH - Original - Vine Version.wav");
			}
		});

		tauntButtons[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playSound("/Music/Funny Laugh Sound- No No No No.wav");
			}
		});
		tauntButtons[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playSound("/Music/hallelujahshort.swf.wav");
			}
		});
		tauntButtons[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playSound("/Music/mlg-airhorn.wav");
			}
		});
		tauntButtons[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playSound("/Music/no-god-please-no-noooooooooo.wav");
			}
		});
		tauntButtons[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playSound("/Music/wrong.wav");
			}
		});
		tauntButtons[6].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playSound("/Music/thisissparta.wav");
			}
		});

		tauntButtons[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playSound("/Music/metalgearsolid.wav");
			}
		});
	}

	private void playSound(String fileName) { // TODO make music class which handles this
		Clip clip = null;
		try {
			URL sound = getClass().getResource("/Music/" + fileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
		}
	}

	public JButton getExitButton() {
		return exitButton;
	}
}
