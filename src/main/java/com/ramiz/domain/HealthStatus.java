package com.ramiz.domain;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HealthStatus extends Rectangle{
	private int howManyLivesTillEnding ;
	private int x = 1050 + 35;
	private final int y = 30;
	public int[] healthLeft;
	public Image heart = new ImageIcon("sr\\main\\resources\\heart.png").getImage();

	

	public HealthStatus(int incomingNum) {
		this.howManyLivesTillEnding = incomingNum;
		this.healthLeft = new int[incomingNum];
	}

	public int getHowManyLivesTillEnding() {
		return howManyLivesTillEnding;
	}

	public void setHowManyLivesTillEnding(int howManyLivesTillEnding) {
		this.howManyLivesTillEnding = howManyLivesTillEnding;
	}


	public int getXOfHeart() {
		return x;
	}

	public void setXOfHeart(int x) {
		this.x = x;
	}

	public int getYOfHeart() {
		return y;
	}

	
	
}
