package com.ramiz.domain;

import javax.swing.JPanel;

public class Player extends JPanel {
	private int playerX ;
	private int playerY ;
	
	public Player(int x, int y) {
		this.setPlayerX(x);
		this.setPlayerY(y);
		
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}
	

	
}
