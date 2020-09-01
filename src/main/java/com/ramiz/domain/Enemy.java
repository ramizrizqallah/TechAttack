package com.ramiz.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy extends Rectangle {
	private int x = 10;
	private int y = -40;
	private  int width ;
	private  int height ;
	private int enemySpeed;
	
	public Enemy(int width, int height, int speed) {
		this.setEnemyWidth(width);
		this.setEnemyHeight(height);
		this.setEnemySpeed(speed);
	}
		

	public int getPosX() {
		return x;
	}

	public void setPosX(int x) {
		this.x = x;
	}

	public int getPosY() {
		return y;
	}

	public void setPosY(int y) {
		this.y = y;
	}

	public int getEnemyWidth() {
		return width;
	}

	public void setEnemyWidth(int width) {
		this.width = width;
	}

	public int getEnemyHeight() {
		return height;
	}

	public void setEnemyHeight(int height) {
		this.height = height;
	}


	public int getEnemySpeed() {
		return enemySpeed;
	}


	public void setEnemySpeed(int enemySpeed) {
		this.enemySpeed = enemySpeed;
	}
	
	
}
