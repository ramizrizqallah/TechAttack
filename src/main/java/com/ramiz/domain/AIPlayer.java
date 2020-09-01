package com.ramiz.domain;

import javax.swing.JPanel;

import com.ramiz.domain.abstractClasses.Player;

public class AIPlayer extends Player {
	private int AIPlayerDirection;

	public AIPlayer(int x, int y, int direction) {
		super(x, y);
		setAIPlayerDirection(direction);
	}

	
	public int getAIPlayerDirection() {
		return AIPlayerDirection;
	}

	public void setAIPlayerDirection(int aIPlayerDirection) {
		AIPlayerDirection = aIPlayerDirection;
	}


	@Override
	public void move() {
		setPlayerX(getPlayerX() + 1 * getAIPlayerDirection());
	}
	
	

	
}
