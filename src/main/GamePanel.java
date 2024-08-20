package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	final int originalTileSize = 16;
	final int scale = 3;

	final public int tileSize = originalTileSize * scale;
	final public int maxScreenCol = 20;
	final public int maxScreenRow = 12;
	final public int screenWidth = tileSize * maxScreenCol;
	final public int screenHeight = tileSize * maxScreenRow;
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;

	public boolean fullScreenOn = false;
	boolean alreadyChecked = false;

	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	int FPS = 60;

	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	public HighScore HS = new HighScore(this);

	Sound music = new Sound();
	Sound se = new Sound();

	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	Thread gameThread;

	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[30];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[50];
	ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Integer> scoreList = new ArrayList<>();
	public ArrayList<Integer> save = new ArrayList<>();

	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionState = 5;
	public final int gameOverState = 6;
	public final int highScoreState = 7;
	public final int controlState = 8;
	public final int finishState = 9;

	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;

	public boolean loadGame;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

	}

	public void setupGame() {

		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();

		gameState = controlState;

		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) tempScreen.getGraphics();

		if (fullScreenOn == true) {
			setFullScreen();

		}

	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();

	}

	public void restart() {

		player.hasChestKey = 0;
		player.hasDoorKey = 0;
		player.setDefaultValues();
		player.restoreLifeAndMan();
		player.setItems();
		player.score = 0;
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		ui.message.clear();
		ui.messageCounter.clear();
		stopMusic();

	}

	@Override
	public void run() {

		Collections.sort(scoreList, Collections.reverseOrder());

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;

		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {

				try {

					if (HS.nullChecker() == true && !alreadyChecked) {
						HS.dummyScore();
						alreadyChecked = true;
					}

					if (HS.nullChecker() == false && !alreadyChecked) {
						HS.loadScore();
						alreadyChecked = true;
					}
				} catch (Exception e) {
				}

				try {
					HS.saveScore();
				} catch (Exception e) {
				}

				update();
				drawToTempScreen();
				drawToScreen();
				delta--;
				drawCount++;

			}

		}

	}

	public void drawToScreen() {

		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();

	}

	public void setFullScreen() {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);

		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();

	}

	public void update() {

		if (gameState == playState) {

			player.update();

			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					if (monster[i].alive == true) {
						monster[i].update();
					}

					if (monster[i].alive == false) {
						monster[i].checkDrop();

						monster[i] = null;
					}

				}

			}

		}

		if (gameState == titleState) {

		}

		if (gameState == pauseState) {

		}

	}

	public void drawToTempScreen() {

		long drawStart = 0;
		drawStart = System.nanoTime();

		if (gameState == titleState) {

			ui.draw(g2);

		} else {

			tileM.draw(g2);

			entityList.add(player);

			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					entityList.add(monster[i]);
				}
			}

			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {

					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;

				}

			});

			for (int i = 0; i < entityList.size(); i++) {

				entityList.get(i).draw(g2);

			}

			entityList.clear();

			ui.draw(g2);

		}

		if (keyH.showDebugText == true) {

			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;

			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			g2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int lineHeight = 20;

			g2.drawString("WorldX: " + player.worldX, x, y);
			y += lineHeight;
			g2.drawString("WorldYX: " + player.worldY, x, y);
			y += lineHeight;
			g2.drawString("Col: " + (player.worldX + player.solidArea.x) / tileSize, x, y);
			y += lineHeight;
			g2.drawString("Row: " + (player.worldY + player.solidArea.y) / tileSize, x, y);
			y += lineHeight;
			g2.drawString("Draw Time: " + passed, x, y);
			y += lineHeight;

		}

	}

	public void playMusic(int i) {

		music.setFile(i);
		music.play();
		music.loop();

	}

	public void stopMusic() {

		music.stop();

	}

	public void playSE(int i) {

		se.setFile(i);
		se.play();

	}

}
