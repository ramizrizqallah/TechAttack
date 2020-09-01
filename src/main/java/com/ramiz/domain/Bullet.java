package com.ramiz.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.ramiz.runners.GamePlay;

public class Bullet extends Rectangle  {
	private int width;
	private int height;
	private int x , y;
	
	public Bullet(int x, int y, int width, int height) {
		this.setBulletY(y);
		this.setBulletX(x);
		this.width = width;
		this.height = height;
		
	}

	public int getBulletX() {
		return x;
	}
	public int getBulletWidth() {
		return width;
	}
	public int getBulletHeight() {
		return height;
	}

	public void setBulletX(int x) {
		this.x = x;
	}

	public int getBulletY() {
		return y;
	}

	public void setBulletY(int y) {
		this.y = y;
	}
	
	
	
	
}
