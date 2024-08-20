package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Big extends Entity {

	int value = 50;

	public OBJ_Potion_Big(GamePanel gp) {
		super(gp);

		type = type_consumable;
		name = "Big Potion";
		down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		description = "[Big Potion]\nHeals your life by " + value + ".";

	}

	public void use(Entity entity) {

		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drink the " + name + " !\n" + "Your life has been recovered by " + value + ".";
		entity.life += value;
		gp.player.life += value;
		gp.playSE(2);
	}

}
