package com.ramiz.domain;

public class ScoringAndUpgrades {
	private int level = 1;
	private int score = 0;
	private int hiddenScore = 0;
	public static int bulletSpeed = 0;
	private int hiddenBulletScore = 0;
	private int playerSpeed = 0;
	private double enemySpeed = 5;
	private double money = 2000;
	
	private boolean multiBullet = false;
	private boolean bulletFaster = false;
	private boolean laserAim = false;
	private boolean AIPlayer = false;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	public int getPlayerSpeed() {
		return playerSpeed;
	}

	public void setPlayerSpeed(int playerSpeed) {
		this.playerSpeed = playerSpeed;
	}

	public int getHiddenScore() {
		return hiddenScore;
	}

	public void setHiddenScore(int hiddenScore) {
		this.hiddenScore = hiddenScore;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getEnemySpeed() {
		return enemySpeed;
	}

	public void setEnemySpeed(double d) {
		this.enemySpeed = d;
	}

	public boolean isMultiBullet() {
		return multiBullet;
	}

	public void setMultiBullet(boolean multiBullet) {
		this.multiBullet = multiBullet;
	}

	public int getHiddenBulletScore() {
		return hiddenBulletScore;
	}

	public void setHiddenBulletScore(int hiddenBulletScore) {
		this.hiddenBulletScore = hiddenBulletScore;
	}

	public boolean isBulletFaster() {
		return bulletFaster;
	}

	public void setBulletFaster(boolean bulletFaster) {
		this.bulletFaster = bulletFaster;
	}

	public boolean isLaserAim() {
		return laserAim;
	}

	public void setLaserAim(boolean laserAim) {
		this.laserAim = laserAim;
	}

	public boolean isAIPlayer() {
		return AIPlayer;
	}

	public void setAIPlayer(boolean aIPlayer) {
		AIPlayer = aIPlayer;
	}

}
