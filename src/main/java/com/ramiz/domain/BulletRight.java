package com.ramiz.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.ramiz.domain.abstractClasses.Bullet;
import com.ramiz.runners.GamePlay;

public class BulletRight extends Bullet  {
	public BulletRight(int x, int y, int width, int height) {
		super(x,y,width,height);
		
	}

	@Override
	public void move() {
		setBulletY(getBulletY() - 5 - ScoringAndUpgrades.bulletSpeed);
		setBulletX(getBulletX() + 1 + ScoringAndUpgrades.bulletSpeed/5);
	}
	
	
	
	
}
