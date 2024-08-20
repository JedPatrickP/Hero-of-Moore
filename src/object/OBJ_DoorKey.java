package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_DoorKey extends Entity {
	
	public OBJ_DoorKey(GamePanel gp) {
		super(gp);
		
		name = "Door Key";
		down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
		collision = true;
		description = "["+name+"]\nIt is used to unlock \na door.";
	}

}