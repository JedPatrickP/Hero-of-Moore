package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity {

	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp);

		type = type_shield;
		name = "Normal Shield";
		down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
		defenseValue = 5;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "["+name+"]\nA solidly constructed \nshield.";
		
		
	}

}