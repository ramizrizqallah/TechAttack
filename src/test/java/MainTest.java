import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.Timer;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MainTest {

	public static void main(String[] args)   {
		int[][] test = new int[20][2];
		InputStream audio;

		try {
			audio = new FileInputStream(new File("src\\main\\resources\\sounds\\fire.wav"));
			System.out.println("Played");
			AudioStream sound = new AudioStream(audio);
			AudioPlayer.player.start(sound);
		} catch (Exception e) {
		}
	}
}
