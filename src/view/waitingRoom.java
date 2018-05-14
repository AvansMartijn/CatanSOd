package view;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;

import java.awt.Image;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.io.IOException;

import java.net.URL;

import javax.imageio.ImageIO;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.*;

@SuppressWarnings("serial")
public class waitingRoom extends JPanel {
	private Font Bold = new Font("Arial", Font.BOLD, 30);
	private Color lightBlue = new Color(0, 0, 182, 155);
	private Color lightBlue2 = new Color(0, 0, 190, 160);
	private JPanel buttons = new JPanel();
	private int joint = 0;
	private JLabel playersInRoom = new JLabel();

	public waitingRoom() {
		this.setLayout(new BorderLayout());
		buttons.setBackground(lightBlue2);
		makebuttons();
		this.add(buttons, BorderLayout.NORTH);
		backgroundImage();
		this.setBackground(lightBlue);
		this.setVisible(true);
	}

	public void backgroundImage() {
		Image image = null;
		try {
			URL url = new URL(
					"https://www.spellenrijk.nl/resize/999-kol01b-2_12557510737781.png/0/1100/True/de-kolonisten-van-catan-basisspel-3.png");
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel background = new JLabel(new ImageIcon(image));
		this.add(background, BorderLayout.CENTER);
	}

	public void makeJLabels() {

		JLabel waiting = new JLabel();
		// aantal gejoinde mensen opschrijven
		playersInRoom.setText("Players joined: " + getJoint());
		playersInRoom.setFont(Bold);
		playersInRoom.setForeground(Color.white);
		// text voor het wachten
		waiting.setText("wait a few minutes, the match will start soon");
		waiting.setForeground(Color.white);
		waiting.setFont(Bold);
		playersInRoom.setHorizontalAlignment(JLabel.CENTER);
		buttons.add(playersInRoom);
		// buttons.add(waiting, BorderLayout.NORTH);
	}

	@SuppressWarnings("unused")
	public void makebuttons() {

		Font Bold = new Font("Arial", Font.BOLD, 20);

		JButton button[];
		JButton Bruh, Laugh, hallelujah, mlg, noGodPleaseNo, wrong, thisissparta, metalgearsolid;
		String text[] = { "bruh", "laugh", "hallelujah", "mlg", "noGod", "wrong", "parta", "metalgear" };
		String info = "press if you want to have a funny sound";
		button = new JButton[8];
		for (int count = 0; count < button.length; count++) {
			if (count == 4) {
				makeJLabels();
			}
			button[count] = new JButton(text[count]);
			button[count].setForeground(Color.WHITE);
			button[count].setBackground(Color.black);
			button[count].setFont(Bold);
			button[count].setSize(20, 20);
			button[count].setMargin(new Insets(20, 20, 20, 20));
			button[count].setEnabled(true);
			button[count].setToolTipText(info);
			button[count].setVisible(true);
			button[count].setBorderPainted(true);
			buttons.add(button[count]);
		}
		button[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\Music\\BRUH - Original - Vine Version.wav");

			}

		});
		button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\Music\\Funny Laugh Sound- No No No No.wav");

			}
		});
		button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\Music\\hallelujahshort.swf.wav");

			}
		});
		button[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\Music\\mlg-airhorn.wav");

			}
		});
		button[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\Music\\no-god-please-no-noooooooooo.wav");

			}
		});
		button[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\Music\\wrong.wav");

			}
		});
		button[6].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\Music\\thisissparta.wav");

			}
		});

		button[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\Music\\metalgearsolid.wav");

			}
		});
	}

	/*
	 * public static void play(String file) { File Clap = new File(file); try { Clip
	 * clip = AudioSystem.getClip();
	 * clip.open(AudioSystem.getAudioInputStream(Clap)); clip.start();
	 * Thread.sleep(clip.getMicrosecondLength() / 1000); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
	public static void play2(String filename) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	public int getJoint() {
		return joint;
	}

	public void setJoint(int joint) {
		this.joint = this.joint + joint;
	}

}
