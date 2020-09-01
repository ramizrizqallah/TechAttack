package com.ramiz.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.*;

public class SoundPlayer {

	public static void sound(String filepath) {

		InputStream audio;

		try {
			audio = new FileInputStream(new File(filepath));
			AudioStream sound = new AudioStream(audio);
			AudioPlayer.player.start(sound);
		} catch (Exception e) {
		}

	}
}
