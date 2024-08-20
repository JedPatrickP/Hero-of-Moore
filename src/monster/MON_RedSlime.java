package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_ChestKey;
import object.OBJ_Potion_Big;
import object.OBJ_Potion_Red;
import object.OBJ_Sword_Rare;

public class MON_RedSlime extends Entity {

	GamePanel gp;

	public MON_RedSlime(GamePanel gp) {
		super(gp);

		this.gp = gp;

		type = type_monster;
		name = "Red Slime";
		speed = 1;
		maxLife = 50;
		life = maxLife;
		attack = 50;
		defense = 0;
		exp = 2;

		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();

	}

	public void getImage() {

		up1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);

	}

	public void setAction() {

		actionLockCounter++;

		if (actionLockCounter == 120) {

			Random random = new Random();
			int i = random.nextInt(100) + 1;

			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}

			actionLockCounter = 0;

		}

	}

	public void damageReaction() {

		actionLockCounter = 0;

		switch (gp.player.direction) {

		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;

		}

	}

	public void checkDrop() {

		int i = new Random().nextInt(100) + 1;

		dropItem(new OBJ_ChestKey(gp));
		gp.player.score += 150;
		gp.player.exp += 5;

		if (i >= 0 && i < 50) {

			dropItem(new OBJ_Potion_Red(gp));
			gp.player.score += 15;
			gp.player.exp += 2;

		}

		if (i >= 50 && i < 75) {

			dropItem(new OBJ_Sword_Rare(gp));
			gp.player.score += 15;
			gp.player.exp += 2;

		}
		
		if (i >= 75 && i < 100) {

			dropItem(new OBJ_Potion_Big(gp));
			gp.player.score += 20;
			gp.player.exp += 5;
		}

	}

}