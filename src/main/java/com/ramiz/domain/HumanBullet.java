package com.ramiz.domain;

import com.ramiz.domain.abstractClasses.Bullet;

public class HumanBullet extends Bullet {

	public HumanBullet(int x, int y, int width, int height) {
		super(x, y, width, height);
	}


	@Override
	public void move() {
		setBulletY((getBulletY() - 5) - ScoringAndUpgrades.bulletSpeed);
	}
	
	
	
	
}
