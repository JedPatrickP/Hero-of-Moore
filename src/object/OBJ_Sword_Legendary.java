package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Legendary extends Entity {

	public OBJ_Sword_Legendary(GamePanel gp) {
		super(gp);

		type = type_sword;
		name = "Legendary Sword";
		down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
		attackValue = 50;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\nA legendary sword that \ncan burst mobs!.";

	}

}
