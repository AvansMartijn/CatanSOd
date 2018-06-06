package controller;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	
	private final String musicFile = "Catan-The-Score-Soundtrack.wav";
	private Clip musicClip;
	
	public void playMusic() {
		musicClip = playSound(musicFile, true);
	}
	
	public void playTaunt(String soundFile) {
		playSound(soundFile, false);
	}

	private Clip playSound(String soundFile, boolean shouldLoop) {
		Clip clip = null;
		try {
			URL sound = getClass().getResource("/Music/" + soundFile);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			if(shouldLoop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.start();
			}
		} catch(Exception e) {
		}
		return clip;
	}
	
	public void stopMusic() {
		if(musicClip != null) {
			musicClip.stop();
		}
	}
}
