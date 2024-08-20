package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ChestKey extends Entity {
	
	public OBJ_ChestKey(GamePanel gp) {
		super(gp);
		
		name = "Chest Key";
		down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
		collision = true;
		description = "["+name+"]\nIt is used to unlock \na chest.";
	}

}