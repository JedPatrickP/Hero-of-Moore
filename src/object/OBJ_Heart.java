package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

	public OBJ_Heart(GamePanel gp) {

		super(gp);
		this.gp = gp;

		value = 3;
		
		name = "Heart";
		image = setup("/objects/heart", gp.tileSize, gp.tileSize);

		type = type_pickupOnly; 
		down1 = setup("/objects/heart", gp.tileSize/2, gp.tileSize/2);

	}

	public void use(Entity entity) {

		gp.playSE(1);
		gp.ui.addMessage("+3 Health!");

		gp.player.life += 3;

	}

}