package com.ramiz.domain.abstractClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy extends Rectangle {
	private int x = 10;
	private double y = -40;
	private  int width ;
	private  int height ;
	private double enemySpeed;
	
	public Enemy(int width, int height, double d) {
		this.setEnemyWidth(width);
		this.setEnemyHeight(height);
		this.setEnemySpeed(d);
	}
		

	public int getPosX() {
		return x;
	}

	public void setPosX(int x) {
		this.x = x;
	}

	public double getPosY() {
		return y;
	}

	public void setPosY(double d) {
		this.y = d;
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


	public double getEnemySpeed() {
		return enemySpeed;
	}


	public void setEnemySpeed(double d) {
		this.enemySpeed = d;
	}
	
}
