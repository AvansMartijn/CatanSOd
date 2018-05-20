package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.*;

@SuppressWarnings("serial")
public class WaitingRoom extends JPanel {
	private Font Bold = new Font("Arial", Font.BOLD, 30);
	private Color lightBlue2 = new Color(230, 253, 255);
	private Color lightblue3 = new Color(173, 216, 250);
	private JPanel buttons = new JPanel();
	private JPanel playersPanel;
	// private String subject[] = { "Name: " + getName() + " / IdGame: " + " /
	// Status: " };
	private DefaultListModel dlm = new DefaultListModel();
	private JButton deletePlayerButton;
	private JList<String> playerList = new JList<String>(dlm);
	// private JPanel videos = new JPanel();
	// private ArrayList<Player> players;
	private JLabel playersInRoom = new JLabel();

	public WaitingRoom() {

		// this.players = players;
		this.setLayout(new BorderLayout());
		playersPanel = new JPanel();
		buttons.setBackground(lightblue3);
		makebuttons();
		this.add(buttons, BorderLayout.NORTH);
		backgroundImage();
		// this.add(videos, BorderLayout.SOUTH);
		// videos.setBackground(lightblue3);
		// trailer();
		this.setBackground(lightblue3);
		makePlayerLabels();
		playersPanel.setBackground(lightblue3);
		this.add(playersPanel, BorderLayout.SOUTH);
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
		// text voor het wachten
		waiting.setText("wait a few minutes, the match will start soon");
		waiting.setForeground(Color.white);
		waiting.setFont(Bold);
		playersInRoom.setHorizontalAlignment(JLabel.CENTER);
		buttons.add(playersInRoom);
		// buttons.add(waiting, BorderLayout.NORTH);
	}

	public void update(String ding) {
		String playerString = new String();
		ding = playerString;
/*		for (Player p : players) {
			playerString = playerString + p.getUsername() + ": " + p.getPlayerStatus() + " ";
		}*/

		dlm.addElement(playerString);
		playerList.updateUI();

	}

	public void makePlayerLabels() {

		// aantal gejoinde mensen opschrijven

		playerList.setPreferredSize(new Dimension(400, 400));
		JScrollPane s = new JScrollPane(playerList);
		s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		JScrollPane scrollableList = new JScrollPane(playerList);
		playerList.setBackground(Color.WHITE);
		playerList.setForeground(Color.DARK_GRAY);
		playerList.setBackground(lightblue3);
		JButton iets = new JButton("update?");
		iets.setPreferredSize(new Dimension(400, 50));
		iets.setBackground(lightBlue2);
		iets.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update("hi");
				
			}
		});
		playersPanel.add(scrollableList);
		playersPanel.add(iets);
	}

	@SuppressWarnings("unused")
	public void makebuttons() {

		Font Bold = new Font("Arial", Font.BOLD, 20);

		JButton button[];
		JButton Bruh, Laugh, hallelujah, mlg, noGodPleaseNo, wrong, thisissparta, metalgearsolid;
		String text[] = { "Bruh", "Laugh", "Hallelujah", "Mlg", "NoGod", "Wrong", "Sparta", "Metalgear" };
		String info = "press if you want to have a funny sound";
		button = new JButton[8];
		for (int count = 0; count < button.length; count++) {
			if (count == 4) {
				makeJLabels();
			}
			button[count] = new JButton(text[count]);
			button[count].setForeground(Color.BLACK);
			button[count].setBackground(lightBlue2);
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
				play2("\\java codering voor programmeren\\CatanSOd\\src\\Music\\BRUH - Original - Vine Version.wav");

			}

		});
		button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\src\\Music\\Funny Laugh Sound- No No No No.wav");

			}
		});
		button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\src\\Music\\hallelujahshort.swf.wav");

			}
		});
		button[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\src\\Music\\mlg-airhorn.wav");

			}
		});
		button[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\src\\Music\\no-god-please-no-noooooooooo.wav");

			}
		});
		button[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\src\\Music\\wrong.wav");

			}
		});
		button[6].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\src\\Music\\thisissparta.wav");

			}
		});

		button[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play2("\\java codering voor programmeren\\CatanSOd\\src\\Music\\metalgearsolid.wav");

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

	public void playerList() {

	}

	// public void trailer() {
	// Font Bold = new Font("Arial", Font.BOLD, 40);
	//
	// JButton video = new JButton("Trailer");
	// video.setForeground(Color.BLACK);
	// video.setBackground(lightBlue2);
	// video.setFont(Bold);
	// video.setSize(50, 20);
	// video.setMargin(new Insets(20, 20, 20, 20));
	// video.setEnabled(true);
	// video.setToolTipText("bekijk de trailer");
	// video.setVisible(true);
	// video.setBorderPainted(true);
	// video.addActionListener(new ActionListener() {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// try {
	// Desktop.getDesktop()
	// .open(new File("D:\\java codering voor programmeren\\CatanSOd\\Music\\Catan
	// Trailer.mpg"));
	// } catch (IOException e1) {
	// // TODO Auto-generated catch block
	// System.out.println("sorry you don't have the required programs");
	// }
	//
	// }
	// });
	// videos.add(video, BorderLayout.CENTER);
	// }

}

