package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Rare extends Entity {

	public OBJ_Sword_Rare(GamePanel gp) {
		super(gp);

		type = type_sword;
		name = "Rare Sword";
		down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
		attackValue = 10;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\nA rare sword.";

	}

}
