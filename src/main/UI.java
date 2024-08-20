package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import object.OBJ_Heart;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, maruMonica;
	BufferedImage keyImage;
	public boolean messageOn = false;

	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	public int score1, score2, score3, score4, score5;
	int subState = 0;
	public boolean noSave;

	public UI(GamePanel gp) {
		this.gp = gp;

		OBJ_Heart heart = new OBJ_Heart(gp);
		keyImage = heart.image;

		arial_40 = new Font("Calibri", Font.BOLD, 40);

		try {
			InputStream is = getClass().getResourceAsStream("/font/MaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);

		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void drawCharacterScreen() {

		final int frameX = gp.tileSize * 2;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));

		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;

		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Exp Lvl", textX, textY);
		textY += lineHeight;
		g2.drawString("Door Key", textX, textY);
		textY += lineHeight;
		g2.drawString("Chest Key", textX, textY);
		textY += lineHeight + 20;

		textY += lineHeight + 20;

		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;

		int tailX = (frameX + frameWidth) - 30;

		textY = frameY + gp.tileSize;
		String value;

		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);

		textY += lineHeight;

		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.hasDoorKey);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.hasChestKey);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		textY += lineHeight + 20;
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 15, null);
		textY += lineHeight;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY, null);

	}

	public void drawHighScore() {

		Collections.sort(gp.scoreList, Collections.reverseOrder());

		g2.setColor(new Color(0, 0, 0, 255));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
		text = "High Score";
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize * 3;
		g2.drawString(text, x, y);

		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y - 4);

		int index = 1;

		// 1 - 5
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
		text = index + ". " + gp.scoreList.get(0);
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);
		index++;

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
		text = index + ". " + gp.scoreList.get(1);
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);
		index++;

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
		text = index + ". " + gp.scoreList.get(2);
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);
		index++;

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
		text = index + ". " + gp.scoreList.get(3);
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);
		index++;

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
		text = index + ". " + gp.scoreList.get(4);
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
		text = "Back";
		x = getXforCenteredText(text);
		y = gp.tileSize * 2;
		g2.drawString(text, x, y * 5);
		g2.drawString(">", x - 40, y * 5);

	}

	public void addMessage(String text) {

//		message = text;
//		messageOn = true;

		message.add(text);
		messageCounter.add(0);

	}

	public void getScore() {

	}

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;

	}

	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;

	}

	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);

	}

	public void drawHealth() {

		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
		g2.drawString("= " + gp.player.life, 80, 63);

	}

	public void drawMessage() {

		int messageX = gp.tileSize / 2;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));

		for (int i = 0; i < message.size(); i++) {

			if (message.get(i) != null) {

				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);

				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;

				if (messageCounter.get(i) > 180) {

					message.remove(i);
					messageCounter.remove(i);
				}
			}

		}

	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;

		g2.setFont(maruMonica);
		g2.setColor(Color.white);

		if (gp.gameState == gp.controlState) {
			drawInstructions();
		}

		if (gp.gameState == gp.titleState) {

			drawTitleScreen();
		}

		if (gp.gameState == gp.playState) {
			drawHealth();
			drawMessage();

		}
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
			gp.gameState = gp.pauseState;
		}
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}

		if (gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory();
		}

		if (gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}

		if (gp.gameState == gp.highScoreState) {
			drawHighScore();
		}

		if (gp.gameState == gp.optionState) {
			drawOptionScreen();
		}
		if(gp.gameState == gp.finishState) {
			
			drawGameOverWin();
			
		}

	}

	public void drawOptionScreen() {

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));

		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 10;

		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		switch (subState) {
		case 0:
			options_top(frameX, frameY);
			break;
		case 1:
			options_fullScreenNotification(frameX, frameY);
			break;
		case 2:
			options_control(frameX, frameY);
			break;

		}

		gp.keyH.enterPressed = false;

	}

	public void options_top(int frameX, int frameY) {

		int textX;
		int textY;

		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		textX = frameX + gp.tileSize;
		textY += gp.tileSize * 2;
		g2.drawString("Full Screen", textX, textY);

		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				if (gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				} else if (gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
		}

		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);

		if (commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}

		textY += gp.tileSize;
		g2.drawString("SE", textX, textY);

		if (commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
		}

		textY += gp.tileSize;
		g2.drawString("Control", textX, textY);

		if (commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}

		textY += gp.tileSize * 2;
		g2.drawString("Back", textX, textY);

		if (commandNum == 4) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}

		textX = frameX + (int) (gp.tileSize * 4.5);
		textY = frameY + gp.tileSize * 2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);

		if (gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}

		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);

		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);

		gp.config.saveConfig();

	}

	public void options_fullScreenNotification(int frameX, int frameY) {

		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;

		currentDialogue = "The change will take \neffect after restarting \nthe game.";

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}

		textY = frameY + gp.tileSize * 9;
		g2.drawString("Back", textX, textY);

		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}

	}

	public void options_control(int frameX, int frameY) {

		int textX;
		int textY;

		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Confirm/Attack", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Character Screen", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Pause", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Show Debug", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Options", textX, textY);
		textY += gp.tileSize;

		textX = frameX + gp.tileSize * 6;
		textY = frameY + gp.tileSize * 2;
		g2.drawString("WASD", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY);
		textY += gp.tileSize;
		g2.drawString("C", textX, textY);
		textY += gp.tileSize;
		g2.drawString("P", textX, textY);
		textY += gp.tileSize;
		g2.drawString("H", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ESC", textX, textY);
		textY += gp.tileSize;

		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize * 9;
		g2.drawString("Back", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}

	}

	public void drawGameOverWin() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
		text = "You've finished the game!";
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.drawString(text, x, y);

		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y - 4);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
		text = "Score: " + gp.player.score;
		x = getXforCenteredText(text);
		y = gp.tileSize * 6;
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
		text = "Quit";
		x = getXforCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}

		slotCol = 0;
		slotRow = 0;

	}
	
	public void drawGameOverScreen() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		text = "Game Over";
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.drawString(text, x, y);

		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y - 4);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
		text = "Score: " + gp.player.score;
		x = getXforCenteredText(text);
		y = gp.tileSize * 6;
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
		text = "Quit";
		x = getXforCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}

		slotCol = 0;
		slotRow = 0;

	}

	public void drawInventory() {

		int frameX = gp.tileSize * 12;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 6;
		int frameHeight = gp.tileSize * 5;

		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;

		for (int i = 0; i < gp.player.inventory.size(); i++) {

			if (gp.player.inventory.get(i) == gp.player.currentWeapon
					|| gp.player.inventory.get(i) == gp.player.currentShield) {

				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);

			}

			g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

			slotX += slotSize;

			if (i == 4 || i == 9 || i == 14) {

				slotX = slotXstart;
				slotY += slotSize;

			}

		}

		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;

		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight + 50;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize * 3;

		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(28F));

		int itemIndex = getItemIndexOnSlot();

		if (itemIndex < gp.player.inventory.size()) {

			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

			for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}

		}

	}

	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}

	public void drawTitleScreen() {

		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "Hero of Moore";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 3;

		g2.setColor(Color.gray);
		g2.drawString(text, x + 5, y + 5);

		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
		y += gp.tileSize * 2;

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));

		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize * 3;
		g2.drawString(text, x, y);

		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "HIGH SCORE";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}

	}

	public void drawInstructions() {

		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
		String text = "Controls:";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 2;

		g2.setColor(Color.gray);
		g2.drawString(text, x + 3, y + 3);

		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));

		text = "W -> Forward/Up";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);

		text = "S -> Backward/Down";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);

		text = "A -> Left";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);

		text = "D -> Right";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);

		text = "Enter -> Interact/Attack";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);

		text = "C -> Character Status/Inventory";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);

		text = "H -> Debug";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);

		text = "Press Enter to continue";
		x = getXforCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);

	}

	public void drawDialogueScreen() {

		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 5;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
		x += gp.tileSize;
		y += gp.tileSize + 5;

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}

	}

	public void drawSubWindow(int x, int y, int width, int height) {

		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

	}

}
