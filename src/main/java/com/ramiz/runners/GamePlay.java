package com.ramiz.runners;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.ramiz.domain.AIBullet;
import com.ramiz.domain.AIPlayer;
import com.ramiz.domain.BulletLeft;
import com.ramiz.domain.BulletRight;
import com.ramiz.domain.EnemyBullet;
import com.ramiz.domain.HealthStatus;
import com.ramiz.domain.HumanBullet;
import com.ramiz.domain.HumanPlayer;
import com.ramiz.domain.ScoringAndUpgrades;
import com.ramiz.domain.SoundPlayer;
import com.ramiz.domain.abstractClasses.Bullet;
import com.ramiz.domain.abstractClasses.Enemy;
import com.ramiz.domain.abstractClasses.Player;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	private int bulletY;
	private int bulletX;
	private final int WIDTH = 1200, HEIGHT = 700, BORDER = WIDTH - 150;
	boolean play, gameRunning, generated, spawned, livesAdded, endGame, eBulletShot, eBulletReadyToShoot = false;
	boolean readyToShoot, shot, aibulletShot, aibulletReadyToShoot, enemiesCanShoot = false;
	boolean shotThree, shootThree, threeBulletsActivated, laserActivated, AIPlayerActivated;
	int[] enemies = new int[10];
	int[][] dimensions = new int[10][2];
	Timer timer;
	java.util.Timer movement = new java.util.Timer();
	HumanBullet hBullet;
	EnemyBullet eBullet;
	BulletRight bulletR;
	BulletLeft bulletL;
	AIBullet AIBullet;
	Player player = new HumanPlayer(WIDTH / 2, HEIGHT - 70);
	AIPlayer AIPlayer = new AIPlayer(WIDTH / 2, HEIGHT - 70, 1);
	Enemy enemy = new Enemy(40, 40, 0.1);
	Rectangle enemyTrace, bulletTrace;
	HealthStatus health = new HealthStatus(3);
	ScoringAndUpgrades score = new ScoringAndUpgrades();
	SoundPlayer sound = new SoundPlayer();
	private int countdownOfHealth = health.getHowManyLivesTillEnding() - 1;
	static Image heart = null;
	static Image humanTank = null;
	static Image AITank = null;
	static Image normalTank = null;
	static Image bulletTank = null;
	static Image background = null;
	static Image startbg = null;

	public GamePlay() {
		File heartFile = new File("src//main//resources//heart.png");
		File humanTankFile = new File("src//main//resources//humanTank.png");
		File AITankFile = new File("src//main//resources//AITank.png");
		File normalTankFile = new File("src//main//resources//normalTank.png");
		File bulletTankFile = new File("src//main//resources//bulletTank.png");
		File backgroundFile = new File("src//main//resources//back.png");
		File startbgFile = new File("src//main//resources//startbg.jpg");
		try {
			humanTank = ImageIO.read(humanTankFile);
			AITank = ImageIO.read(AITankFile);
			normalTank = ImageIO.read(normalTankFile);
			bulletTank = ImageIO.read(bulletTankFile);
			heart = ImageIO.read(heartFile);
			background = ImageIO.read(backgroundFile);
			startbg = ImageIO.read(startbgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		movement.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (gameRunning) {
					if (spawned) {
						moveEnemies();
					}
				}
			}

		}, 30, 5);
		movement.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (gameRunning) {
					score.setMoney(score.getMoney() + 0.5);
				}
			}

		}, 1000, 1000);

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(8, this);
		timer.start();

	}

	public void paint(Graphics g) {
		if (endGame == true) {
			
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			Font end = new Font("Helvetica", Font.BOLD, 30);
			g.setFont(end);
			g.setColor(Color.BLACK);
			g.drawString("Thanks For Playing Tech Attack", WIDTH / 2 - 180, HEIGHT / 2 - 40);
			end = new Font("Helvetica", Font.BOLD, 20);
			g.setFont(end);
			g.setColor(Color.DARK_GRAY);
			g.drawString("-By Ramiz Rizqallah", (WIDTH / 2) - 70, HEIGHT / 2 - 10);
			g.setColor(Color.RED);
			end = new Font("Helvetica", Font.BOLD, 50);
			g.setFont(end);
			g.drawString("Your Score Is: "+score.getScore(), (WIDTH / 2) -165, HEIGHT / 2 + 100);

		}

		if (!endGame && !gameRunning) {
			//g.setColor(Color.black);
			//g.fillRect(0, 0, WIDTH, HEIGHT);
			Font begin = new Font("Helvetica", Font.BOLD, 30);
			g.drawImage(startbg, 0, 0, 1200, 800, null);
			g.setFont(begin);
			g.setColor(Color.WHITE);
			g.drawString("Welcome To Tech Attack", WIDTH / 2 - 150, HEIGHT / 4 - 40);
			begin = new Font("Helvetica", Font.BOLD, 20);
			g.setFont(begin);
			g.drawString("-By Ramiz Rizqallah", (WIDTH / 2) - 70, HEIGHT / 4 - 10);
			g.setColor(Color.red);
			g.drawString("PRESS ENTER TO START!", (WIDTH / 2) - 100, HEIGHT / 4 + 60);
		}
		// background
		if (gameRunning && !endGame) {

			g.drawImage(background, 0, 0, 1200, 800, null);
			g.setColor(Color.BLACK);
			g.fillRect(BORDER, 0, 150, HEIGHT);
			g.setColor(Color.WHITE);
			g.fillRect(WIDTH - 150, 0, 3, HEIGHT);
			Font status = new Font("Helvetica", Font.BOLD, 24);
			g.setFont(status);
			g.drawString("LEVEL ", BORDER + 40, 100);
			g.drawString("SCORE ", BORDER + 40, 180);
			g.drawString("MONEY ", BORDER + 40, 260);

			g.setColor(Color.WHITE);
			g.fillRect(BORDER, 300, 152, 3);
			g.fillRect(BORDER, 370, 152, 3);
			g.fillRect(BORDER, 440, 152, 3);
			g.fillRect(BORDER, 510, 152, 3);
		}

		// label for counter
		if (gameRunning && !endGame) {
			if (score.getScore() == 0)
				g.drawString("00", BORDER + 70, 210);
			else
				g.drawString(Integer.toString(score.getScore()), BORDER + 60, 210);

			g.drawString(Integer.toString(score.getLevel()), BORDER + 70, 130);
			g.drawString("$".concat(Double.toString(score.getMoney())), BORDER + 60, 290);

			if (score.isBulletFaster() == true) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.WHITE);
				g.drawString("1", BORDER + 5, 330);
				g.drawString("Speed Bullet", BORDER + 22, 330);
				g.drawString("$100.00", BORDER + 40, 350);
			} else {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.darkGray);
				g.drawString("1", BORDER + 5, 330);
				g.drawString("Speed Bullet", BORDER + 22, 330);
				g.drawString("$100.00", BORDER + 40, 350);
			}

			if (score.isMultiBullet() == true && !threeBulletsActivated) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.WHITE);
				g.drawString("2", BORDER + 5, 400);
				g.drawString("Multi Bullet", BORDER + 22, 400);
				g.drawString("$350.00", BORDER + 40, 420);
			} else if (score.isMultiBullet() == false && threeBulletsActivated == false) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.darkGray);
				g.drawString("2", BORDER + 5, 400);
				g.drawString("Multi Bullet", BORDER + 22, 400);
				g.drawString("$350.00", BORDER + 40, 420);

			} else if (threeBulletsActivated) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.red);
				g.drawString("2", BORDER + 5, 400);
				g.drawString("Multi Bullet", BORDER + 22, 400);
				g.drawString("$350.00", BORDER + 40, 420);

			}

			if (score.isLaserAim() == true && !laserActivated) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.WHITE);
				g.drawString("3", BORDER + 5, 470);
				g.drawString("Laser Aim", BORDER + 22, 470);
				g.drawString("$700.00", BORDER + 40, 490);
			} else if (score.isLaserAim() == false && laserActivated == false) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.darkGray);
				g.drawString("3", BORDER + 5, 470);
				g.drawString("Laser Aim", BORDER + 22, 470);
				g.drawString("$700.00", BORDER + 40, 490);

			} else if (laserActivated == true) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.red);
				g.drawString("3", BORDER + 5, 470);
				g.drawString("Laser Aim", BORDER + 22, 470);
				g.drawString("$700.00", BORDER + 40, 490);

			}
			if (score.isAIPlayer() == true && !AIPlayerActivated) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.WHITE);
				g.drawString("4", BORDER + 5, 540);
				g.drawString("AI Player", BORDER + 22, 540);
				g.drawString("$1500.00", BORDER + 40, 560);
			} else if (score.isAIPlayer() == false && AIPlayerActivated == false) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.darkGray);
				g.drawString("4", BORDER + 5, 540);
				g.drawString("AI Player", BORDER + 22, 540);
				g.drawString("$1500.00", BORDER + 40, 560);

			} else if (AIPlayerActivated == true) {
				Font status = new Font("Helvetica", Font.BOLD, 20);
				g.setFont(status);
				g.setColor(Color.red);
				g.drawString("4", BORDER + 5, 540);
				g.drawString("AI Player", BORDER + 22, 540);
				g.drawString("$1500.00", BORDER + 40, 560);

			}
			// bullet
			if (gameRunning && !endGame) {
				if (shot) {
					g.setColor(Color.white);
					g.fillOval(((Bullet) hBullet).getBulletX(), ((Bullet) hBullet).getBulletY(),
							((Bullet) hBullet).getBulletWidth(), ((Bullet) hBullet).getBulletHeight());
					if (shotThree) {
						g.fillOval(((Bullet) bulletR).getBulletX(), ((Bullet) bulletR).getBulletY(),
								((Bullet) bulletR).getBulletWidth(), ((Bullet) bulletR).getBulletHeight());
						g.fillOval(((Bullet) bulletL).getBulletX(), ((Bullet) bulletL).getBulletY(),
								((Bullet) bulletL).getBulletWidth(), ((Bullet) bulletL).getBulletHeight());

					}
				}
				if (aibulletShot) {
					g.setColor(Color.white);
					g.fillOval(AIBullet.getBulletX(), AIBullet.getBulletY(), AIBullet.getBulletWidth(),
							AIBullet.getBulletHeight());

				}
				if (eBulletShot) {
					for (int i = 0; i < bulletXs.size(); i++) {
						g.setColor(Color.white);
						g.fillOval(bulletXs.get(i), bulletYs.get(i), 5, 7);
					} 
					

				}

				// player
				/*
				 * g.setColor(Color.GREEN); g.fillRect(player.getPlayerX(), player.getPlayerY(),
				 * 40, 20); g.fillRect(player.getPlayerX() + 17, player.getPlayerY() - 14, 7,
				 * 15);
				 */
				g.drawImage(humanTank, player.getPlayerX(), player.getPlayerY(), 40, 40, null);
				if (laserActivated) {
					g.setColor(Color.pink);
					g.fillRect(player.getPlayerX() + 20, 0, 1, player.getPlayerY());
				}

				// AI Player
				if (AIPlayerActivated) {

					// g.fillRect(((Player) AIPlayer).getPlayerX(), ((Player)
					// AIPlayer).getPlayerY(), 40, 20);
					// g.fillRect(((Player) AIPlayer).getPlayerX() + 17, ((Player)
					// AIPlayer).getPlayerY() - 14, 7, 15);
					g.drawImage(AITank, ((Player) AIPlayer).getPlayerX(), ((Player) AIPlayer).getPlayerY(), 40, 40,
							null);
					g.setColor(Color.cyan);
					Font ai = new Font("Helvetica", Font.ITALIC, 10);
					g.setFont(ai);
					g.drawString("AI Player", ((Player) AIPlayer).getPlayerX(), ((Player) AIPlayer).getPlayerY() - 10);
				}

				// lives
				if (gameRunning == true) {
					for (int i = 0; i < health.healthLeft.length; i++) {
						if (health.healthLeft[i] == 1) {
							g.setColor(Color.blue);
							g.drawImage(heart, health.getXOfHeart(), health.getYOfHeart(), 20, 20, null);
							health.setXOfHeart(health.getXOfHeart() + 30);
						}
					}
					health.setXOfHeart(1085);
					livesAdded = true;
				}
				// enemies
				if (generated) {
					for (int i = 0; i < enemies.length; i++) {

						if (enemies[i] == 1) {
							g.setColor(Color.RED);
							g.drawImage(normalTank, enemy.getPosX(), (int) enemy.getPosY(), enemy.getEnemyWidth(),
									enemy.getEnemyHeight(), null);
							enemy.setPosX(enemy.getPosX() + 105);

							// tracing

							dimensions[i][0] = enemy.getPosX();
							dimensions[i][1] = (int) enemy.getPosY();
						} else if (enemies[i] == 4) {
							g.setColor(Color.MAGENTA);
							g.drawImage(bulletTank, enemy.getPosX(), (int) enemy.getPosY(), enemy.getEnemyWidth(),
									enemy.getEnemyHeight(), null);
							enemy.setPosX(enemy.getPosX() + 105);
							dimensions[i][0] = enemy.getPosX();
							dimensions[i][1] = (int) enemy.getPosY();

						} else {
							enemy.setPosX(enemy.getPosX() + 105);
						}
					}
					spawned = true;
					generated = true;
					enemy.setPosX(0);
				}

			}
			// end of Game announcement!

			g.dispose();
		}
	}

	public void shoot() {
		if (shot)
			hBullet.move();
		if (gameRunning && aibulletShot) {
			AIBullet.move();

		}
	}

	public void enemyShoot() {
		if (eBulletShot) {
			for (int i = 0; i < bulletYs.size(); i++) {
				bulletYs.set(i, bulletYs.get(i) + 3);
			}
			System.out.println(Arrays.toString(bulletYs.toArray()));
		}

	}

	public void shootThreeBullets() {
		if (shotThree) {
			bulletR.move();
			bulletL.move();
		}
	}

	public void actionPerformed(ActionEvent e) {
		timer.start();

		if (score.getMoney() >= 100) {
			score.setBulletFaster(true);
		} else
			score.setBulletFaster(false);

		if (score.getMoney() >= 1500 && !AIPlayerActivated) {
			score.setAIPlayer(true);
		} else
			score.setAIPlayer(false);

		if (score.getMoney() >= 350 && !threeBulletsActivated) {
			score.setMultiBullet(true);
		} else
			score.setMultiBullet(false);

		if (score.getMoney() >= 700 && !laserActivated) {
			score.setLaserAim(true);
		} else
			score.setLaserAim(false);
		if (score.getHiddenScore() >= 150) {
			score.setLevel(score.getLevel() + 1);
			sound.sound("src\\main\\resources\\sounds\\levelup.wav");
			score.setEnemySpeed(score.getEnemySpeed() + 0.05);
			score.setHiddenScore(0);
		}
		if (gameRunning && !livesAdded) {
			generateHealth();
		}

		if (gameRunning && shot) {
			isBulletReady();
		}
		if (gameRunning && aibulletShot) {
			isAIBulletReady();
		}

		isEnemyGenerated();
		if (generated == false) {
			generateEnemies();
		}
		moveAIPlayer();
		shoot();
		enemyShoot();
		
		for(Integer a : bulletYs) {
		if(eBulletShot && a > 700) {
			a = null;
			eBulletReadyToShoot = true;
		} else {
			eBulletReadyToShoot = false;
			eBulletShot = false;
		}
		}
		shootThreeBullets();
		enemyShooting();
		repaint();

		if (gameRunning && AIPlayerActivated) {
			if (((Player) AIPlayer).getPlayerX() <= 0)
				AIPlayer.setAIPlayerDirection(1);
			else if (((Player) AIPlayer).getPlayerX() >= BORDER - 40)
				AIPlayer.setAIPlayerDirection(-1);
		}

		if (gameRunning && AIPlayerActivated) {
			if (AIBullet == null)
				aibulletReadyToShoot = true;
			for (int i = 0; i < dimensions.length; i++) {
				if (dimensions[i][0] != 0) {
					if (((Player) AIPlayer).getPlayerX() == dimensions[i][0] - 105) {
						if (aibulletReadyToShoot) {
							bulletX = ((Player) AIPlayer).getPlayerX();
							bulletY = ((Player) AIPlayer).getPlayerY();
							AIBullet = new AIBullet(bulletX + 20, bulletY, 5, 7);
							aibulletShot = true;
							aibulletReadyToShoot = false;
						}
					}
				}
			}
		}

		A: if (gameRunning == true && shot) {
			for (int i = 0; i < dimensions.length; i++) {
				if (enemies[i] == 1 || enemies[i] == 4) {
					int enemyX = dimensions[i][0];
					int enemyY = dimensions[i][1];
					// System.out.println(enemyX + " " + enemyY);
					enemyTrace = new Rectangle(enemyX - 105, enemyY, 40, 40);
					bulletTrace = new Rectangle(hBullet.getBulletX(), hBullet.getBulletY(), 5, 7);

					if (enemyTrace.intersects(bulletTrace)) {
						score.setScore(score.getScore() + 10);
						score.setMoney(score.getMoney() + 5);
						score.setHiddenScore(score.getHiddenScore() + 10);
						changeValueOfEnemy(i);
						dimensions[i][0] = 0;
						dimensions[i][1] = 0;
						sound.sound("src\\main\\resources\\sounds\\enemy_Death.wav");
						shot = false;
						hBullet = null;
						readyToShoot = true;
						// System.out.println("Touched");
						break A;
					}
				}
			}
		}
		AI: if (gameRunning == true && aibulletShot) {
			for (int i = 0; i < dimensions.length; i++) {
				if (enemies[i] == 1 || enemies[i] == 4) {
					int enemyX = dimensions[i][0];
					int enemyY = dimensions[i][1];
					// System.out.println(enemyX + " " + enemyY);
					enemyTrace = new Rectangle(enemyX - 105, enemyY, 40, 40);
					bulletTrace = new Rectangle(AIBullet.getBulletX(), AIBullet.getBulletY(), 5, 7);

					if (enemyTrace.intersects(bulletTrace)) {
						score.setScore(score.getScore() + 10);
						score.setMoney(score.getMoney() + 5);
						score.setHiddenScore(score.getHiddenScore() + 10);
						changeValueOfEnemy(i);
						dimensions[i][0] = 0;
						dimensions[i][1] = 0;
						sound.sound("src\\main\\resources\\sounds\\enemy_Death.wav");
						aibulletShot = false;
						AIBullet = null;
						aibulletReadyToShoot = true;
						// System.out.println("Touched");
						break AI;
					}
				}
			}
		}
		B: if (gameRunning == true && shotThree) {
			for (int i = 0; i < dimensions.length; i++) {
				if (enemies[i] == 1 || enemies[i] == 4) {
					int enemyX = dimensions[i][0];
					int enemyY = dimensions[i][1];
					// System.out.println(enemyX + " " + enemyY);
					enemyTrace = new Rectangle(enemyX - 105, enemyY, 40, 40);
					Rectangle bulletRTrace = new Rectangle(((Bullet) bulletR).getBulletX(),
							((Bullet) bulletR).getBulletY(), 5, 7);

					if (enemyTrace.intersects(bulletRTrace)) {
						score.setScore(score.getScore() + 10);
						score.setMoney(score.getMoney() + 5);
						score.setHiddenScore(score.getHiddenScore() + 10);
						changeValueOfEnemy(i);
						dimensions[i][0] = 0;
						dimensions[i][1] = 0;
						sound.sound("src\\main\\resources\\sounds\\enemy_Death.wav");
						shot = false;
						hBullet = null;
						readyToShoot = true;
						// System.out.println("Touched");
						break B;
					}
				}
			}
		}
		C: if (gameRunning == true && shotThree) {
			for (int i = 0; i < dimensions.length; i++) {
				if (enemies[i] == 1 || enemies[i] == 4) {
					int enemyX = dimensions[i][0];
					int enemyY = dimensions[i][1];
					// System.out.println(enemyX + " " + enemyY);
					enemyTrace = new Rectangle(enemyX - 105, enemyY, 40, 40);
					Rectangle bulletLTrace = new Rectangle(((Bullet) bulletL).getBulletX(),
							((Bullet) bulletL).getBulletY(), 5, 7);

					if (enemyTrace.intersects(bulletLTrace)) {
						score.setScore(score.getScore() + 10);
						score.setMoney(score.getMoney() + 5);
						score.setHiddenScore(score.getHiddenScore() + 10);
						changeValueOfEnemy(i);
						dimensions[i][0] = 0;
						dimensions[i][1] = 0;
						sound.sound("src\\main\\resources\\sounds\\enemy_Death.wav");
						shot = false;
						hBullet = null;
						readyToShoot = true;
						// System.out.println("Touched");
						break C;
					}
				}
			}
		}

		if (enemy.getPosY() >= HEIGHT - 70) {
			if (countdownOfHealth == -1) {
				endGame = true;
				gameRunning = false;
			} else {
				checkForHealth();
				for (int i = 0; i < enemies.length; i++) {
					enemies[i] = 0;
					dimensions[i][0] = 0;
					dimensions[i][1] = 0;
					shot = false;
					hBullet = null;
					readyToShoot = true;
				}
			}
		}

	}

	ArrayList<Integer> bulletXs = new ArrayList<Integer>();
	ArrayList<Integer> bulletYs = new ArrayList<Integer>();
	private void enemyShooting() {
		if (gameRunning && enemiesCanShoot && generated && !eBulletShot) {
			for (int i = 0; i < enemies.length; i++) {
				if (enemies[i] == 4) {
					if (eBulletReadyToShoot && !eBulletShot) {
						int ebulletX = dimensions[i][0] - 90;
						int ebulletY = dimensions[i][1] + 40;
						eBullet = new EnemyBullet(ebulletX, ebulletY, 5, 7);
						bulletXs.add(ebulletX);
						bulletYs.add(ebulletY);
					}
				}
			}
			eBulletShot = true;
			eBulletReadyToShoot = false;
		}
	}

	private void isEnemyGenerated() {
		a: for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] == 1 || enemies[i] == 4) {
				generated = true;
				break a;
			} else
				generated = false;
		}
		b: for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] == 4) {
				enemiesCanShoot = true;
				eBulletReadyToShoot = true;
				break b;
			} else {
				enemiesCanShoot = false;
				eBulletReadyToShoot = false;
			}
		}

	}

	private void generateEnemies() {
		enemy.setPosY(-40);
		for (int i = 0; i < enemies.length; i++) {
			enemies[i] = (int) (Math.round(Math.random()));
		}
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] == 0)
				enemies[i] = (int) (4 * (Math.round(Math.random())));
		}
		generated = true;
		System.out.println(Arrays.toString(enemies));
	}

	private void checkForHealth() {
		health.healthLeft[countdownOfHealth] = 0;
		countdownOfHealth--;
		sound.sound("src\\main\\resources\\sounds\\player_Death.wav");
	}

	private void generateHealth() {
		for (int i = 0; i < health.healthLeft.length; i++) {
			health.healthLeft[i] = 1;
		}
		livesAdded = true;

	}

	private void isBulletReady() {
		if (hBullet.getBulletY() <= 0) {
			readyToShoot = true;
			hBullet = null;
			shot = false;
		}
		if (eBullet != null && eBullet.getBulletY() >= 600) {
			eBulletReadyToShoot = true;
			eBullet = null;
			eBulletShot = false;

		}
	}

	private void isAIBulletReady() {

		if (AIBullet.getBulletY() <= 0) {
			aibulletReadyToShoot = true;
			AIBullet = null;
			aibulletShot = false;

		}
	}

	private void changeValueOfEnemy(int i) {
		enemies[i] = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (gameRunning) {
				player.setPlayerX(player.getPlayerX() + 35 + score.getPlayerSpeed());
				if (player.getPlayerX() >= BORDER - 40)
					player.setPlayerX(BORDER - 40);

			}

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (gameRunning) {
				player.setPlayerX(player.getPlayerX() - 35 - score.getPlayerSpeed());
				if (player.getPlayerX() <= 0)
					player.setPlayerX(0);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (hBullet == null)
				readyToShoot = true;
			if (readyToShoot) {
				bulletX = player.getPlayerX() + 17;
				bulletY = player.getPlayerY() - 10;
				hBullet = new HumanBullet(bulletX, bulletY, 5, 7);
				if (shootThree) {
					bulletX = player.getPlayerX() + 17;
					bulletY = player.getPlayerY() - 10;
					bulletR = new BulletRight(bulletX, bulletY, 5, 7);
					bulletL = new BulletLeft(bulletX, bulletY, 5, 7);
					shotThree = true;
					threeBulletsActivated = true;
				}
				sound.sound("src\\main\\resources\\sounds\\fire.wav");
				shot = true;
				readyToShoot = false;

			}
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!gameRunning)
				gameRunning = true;
		} else if (e.getKeyCode() == KeyEvent.VK_1) {
			if (gameRunning && score.isBulletFaster()) {
				score.setMoney(score.getMoney() - 100);
				sound.sound("src\\main\\resources\\sounds\\cash.wav");
				score.setBulletSpeed(score.getBulletSpeed() + 5);
				score.setBulletFaster(false);

			}

		} else if (e.getKeyCode() == KeyEvent.VK_2) {
			if (gameRunning && score.isMultiBullet()) {
				score.setMoney(score.getMoney() - 350);
				sound.sound("src\\main\\resources\\sounds\\cash.wav");
				shootThree = true;
				threeBulletsActivated = true;

			}
		} else if (e.getKeyCode() == KeyEvent.VK_3) {
			if (gameRunning && score.isLaserAim()) {
				score.setMoney(score.getMoney() - 700);
				sound.sound("src\\main\\resources\\sounds\\cash.wav");
				laserActivated = true;

			}
		} else if (e.getKeyCode() == KeyEvent.VK_4) {
			if (gameRunning && score.isAIPlayer()) {
				score.setMoney(score.getMoney() - 1500);
				sound.sound("src\\main\\resources\\sounds\\cash.wav");
				AIPlayerActivated = true;

			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	private void moveAIPlayer() {
		if (gameRunning && AIPlayerActivated) {
			AIPlayer.move();
		}

	}

	private void moveEnemies() {
		if (gameRunning && generated) {
			enemy.setPosY(enemy.getPosY() + 0.05 + score.getEnemySpeed());
		}
	}
}
