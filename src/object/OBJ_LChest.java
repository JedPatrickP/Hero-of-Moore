package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_LChest extends Entity {

	public OBJ_LChest(GamePanel gp) {

		super(gp);
		
		name = "Legendary Chest";
		down1 = setup("/objects/chest", gp.tileSize, gp.tileSize);
		collision = true;

	}

}