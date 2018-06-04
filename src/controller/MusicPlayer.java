package controller;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import view.LoginRegisterPanel;

public class MusicPlayer {
	private Clip musicClip;
	private final String musicFile = "Catan-The-Score-Soundtrack.wav";

	public void playMusic() {
		musicClip = playSound(musicFile, false);
		System.out.println("play");
	}

	private Clip playSound(String soundFile, boolean shouldLoop) {
		Clip clip = null;
		try {
			URL sound = getClass().getResource("/Music/" + soundFile);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			if (shouldLoop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				System.out.println("loop");
			} else {
				clip.start();
				System.out.println("normaal afspelen");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return clip;
	}

	public void stopMusic() {
		if (musicClip != null) {
			System.out.println("stop");
			musicClip.stop();
		}else {
			System.out.println("foutje");
		}
		if(musicClip.isRunning() == true) {
			musicClip.stop();
			System.out.println("stop2");
		}
	}

}
