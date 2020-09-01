package com.ramiz.runners;

import java.awt.Font;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import org.w3c.dom.css.Counter;

import javax.swing.*;

public class Display {
	
	static Image frameIcon = new ImageIcon("src\\main\\resources\\AITank.png").getImage();
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GamePlay gameplay = new GamePlay();
		frame.setSize(1200, 700);
		frame.setTitle("Tech Attack - By Ramiz Rizqallah");
	 	frame.setIconImage(frameIcon);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(gameplay);
		frame.setVisible(true);

	}
	
		

}
