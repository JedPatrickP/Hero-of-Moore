package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Legendary;
import object.OBJ_Sword_Normal;
import object.OBJ_Sword_Rare;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public ArrayList<Entity> inventory = new ArrayList<>();

	public final int maxInventorySize = 20;

	public int hasDoorKey = 0;
	public int hasChestKey = 0;
	public int score = 0;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle(0, 0, 48, 48);
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;

		attackArea.width = 30;
		attackArea.height = 30;

		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();

		setItems();

	}

	public void setDefaultValues() {

		setSavedPosition();

		speed = 4;
		level = 1;
		maxLife = 100;
		life = maxLife;
		strength = 1;
		dexterity = 1;
		exp = 0;
		nextLevelExp = 2;

		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		attack = getAttack();
		defense = getDefense();

	}

	public void saveArray() {

		gp.save.add(speed);
		gp.save.add(level);
		gp.save.add(maxLife);
		gp.save.add(life);
		gp.save.add(strength);
		gp.save.add(exp);
		gp.save.add(nextLevelExp);

	}

	public void setItems() {

		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);

	}

	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}

	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}

	public void getPlayerImage() {

		up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);

	}

	public void getPlayerAttackImage() {

		if (currentWeapon.type == type_sword) {

			attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);

		}

		if (currentWeapon.type == type_axe) {

			attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);

		}

	}

	public void setSavedPosition() {

		worldX = gp.tileSize * 10;
		worldY = gp.tileSize * 9;
		direction = "up";

	}

	public void selectItem() {

		int itemIndex = gp.ui.getItemIndexOnSlot();

		if (itemIndex < inventory.size()) {

			Entity selectedItem = inventory.get(itemIndex);

			if (selectedItem.type == type_sword) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if (selectedItem.type == type_shield) {

				currentShield = selectedItem;
				defense = getDefense();

			}

			if (selectedItem.type == type_consumable) {

				selectedItem.use(this);
				inventory.remove(itemIndex);

			}
		}

	}

	public void restoreLifeAndMan() {
		life = maxLife;
		invincible = false;
	}

	public void update() {

		if (attacking == true) {

			attacking();

		} else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true || keyH.enterPressed == true) {

			if (keyH.upPressed == true) {
				direction = "up";

			} else if (keyH.downPressed == true) {
				direction = "down";

			} else if (keyH.leftPressed == true) {
				direction = "left";

			} else if (keyH.rightPressed == true) {
				direction = "right";

			}

			collisionOn = false;
			gp.cChecker.checkTile(this);

			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);

			gp.eHandler.checkEvent();

			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;

				}
			}

			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}

		} else {
			spriteNum = 1;
		}

		if (invincible == true) {

			invincibleCounter++;
			if (invincibleCounter > 60) {

				invincible = false;
				invincibleCounter = 0;
			}

		}

		if (life <= 0) {

			gp.gameState = gp.gameOverState;
			gp.scoreList.add(score);

			gp.stopMusic();
			gp.playSE(10);
		}

		if (life > maxLife) {
			life = maxLife;
		}

	}

	public void interactNPC(int i) {

		if (gp.keyH.enterPressed == true) {

			if (i != 999) {

				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();

			} else {
				gp.playSE(7);
				attacking = true;
			}

		}
	}

	public void contactMonster(int i) {

		if (i != 999) {

			if (invincible == false && gp.monster[i].dying == false) {
				gp.playSE(6);

				int damage = gp.monster[i].attack - gp.player.defense;
				if (damage < 0) {
					damage = 0;
				}

				gp.ui.addMessage("-" + damage + " health!");

				life -= damage;

				invincible = true;
			}
		}
	}

	public void attacking() {

		spriteCounter++;

		if (spriteCounter <= 5) {
			spriteNum = 1;
		}
		if (spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;

			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			switch (direction) {

			case "up":
				worldY -= attackArea.height;
				break;
			case "down":
				worldY += attackArea.height;
				break;
			case "left":
				worldX -= attackArea.width;
				break;
			case "right":
				worldX += attackArea.width;
				break;
			}

			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex, attack);

			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if (spriteCounter > 25) {

			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;

		}
	}

	public void damageMonster(int i, int attack) {

		if (i != 999) {

			if (gp.monster[i].invincible == false) {

				gp.playSE(5);

				int damage = attack;
				if (damage < 0) {
					damage = 0;
				}

				gp.monster[i].life -= damage;
				gp.ui.addMessage(damage + " damage!");

				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();

				if (gp.monster[i].life <= 0) {

					gp.monster[i].dying = true;
					gp.ui.addMessage("killed the " + gp.monster[i].name + "!");
					gp.ui.addMessage(gp.monster[i].exp + " exp gained!");
					exp += gp.monster[i].exp;
					score += 50;
					checkLevelUp();
				}

			}

		} else {

		}

	}

	public void checkLevelUp() {

		if (exp >= nextLevelExp) {

			level++;
			nextLevelExp += 2;
			maxLife += 2;

			attack = getAttack();
			defense = getDefense();

			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are now at level " + level + ".";

		}

	}

	public void RNG() {

		int i = new Random().nextInt(100) + 1;

		if (i >= 0 && i < 25) {

			dropItem(new OBJ_Potion_Red(gp));

		}

		if (i >= 25 && i < 75) {

			dropItem(new OBJ_Shield_Blue(gp));

		}

		if (i >= 75 && i < 100) {

			dropItem(new OBJ_Sword_Rare(gp));

		}

	}

	public void pickUpObject(int i) {

		if (i != 999) {

			String text;

			if (inventory.size() != maxInventorySize) {

				if (gp.obj[i].name == "Door") {

					if (hasDoorKey > 0) {
						gp.playSE(3);
						hasDoorKey--;
						gp.ui.addMessage("You unlocked a " + "Door" + "!");
						gp.obj[i] = null;

					}

				} else if (gp.obj[i].name == "Legendary Chest") {

					if (hasChestKey > 0) {
						gp.playSE(3);
						hasChestKey--;
						gp.ui.addMessage("You unlocked a " + "Chest" + "!");
						dropItem(new OBJ_Sword_Legendary(gp));
						gp.obj[i] = null;
					}

				} else if (gp.obj[i].name == "Chest") {

					if (hasChestKey > 0) {
						gp.playSE(3);
						hasChestKey--;
						gp.ui.addMessage("You unlocked a " + "Chest" + "!");
						gp.obj[i] = null;
						RNG();

					}

				} else if (gp.obj[i].name == "Door Key") {

					gp.playSE(1);
					hasDoorKey++;
					gp.ui.addMessage("You got a " + gp.obj[i].name + "!");
					gp.obj[i] = null;

				} else if (gp.obj[i].name == "Chest Key") {

					gp.playSE(1);
					hasChestKey++;
					gp.ui.addMessage("You got a " + gp.obj[i].name + "!");
					gp.obj[i] = null;

				}

				else {

					gp.playSE(1);
					inventory.add(gp.obj[i]);
					text = "Got a " + gp.obj[i].name + "!";
					gp.ui.addMessage(text);
					gp.obj[i] = null;

				}

			} else {

				text = "You cannot carry any more!";
				gp.ui.addMessage(text);
				gp.obj[i] = null;
			}

		}

	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		switch (direction) {

		case "up":

			if (attacking == false) {
				if (spriteNum == 1) {
					image = up1;
				}
				if (spriteNum == 2) {
					image = up2;
				}
			}
			if (attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if (spriteNum == 1) {
					image = attackUp1;
				}
				if (spriteNum == 2) {
					image = attackUp2;
				}
			}

			break;

		case "down":

			if (attacking == false) {
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = down2;
				}
			}
			if (attacking == true) {
				if (spriteNum == 1) {
					image = attackDown1;
				}
				if (spriteNum == 2) {
					image = attackDown2;
				}
			}

			break;

		case "left":

			if (attacking == false) {
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}
			}
			if (attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if (spriteNum == 1) {
					image = attackLeft1;
				}
				if (spriteNum == 2) {
					image = attackLeft2;
				}
			}
			break;
		case "right":

			if (attacking == false) {
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
			}
			if (attacking == true) {
				if (spriteNum == 1) {
					image = attackRight1;
				}
				if (spriteNum == 2) {
					image = attackRight2;
				}
			}
			break;

		}

		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}

		g2.drawImage(image, tempScreenX, tempScreenY, null);

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
