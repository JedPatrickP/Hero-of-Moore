package entity;

import main.GamePanel;

public class NPC_QuickSand extends Entity {

	public NPC_QuickSand(GamePanel gp) {

		super(gp);

		direction = "up";
		speed = 0;
		getImage();
		setDialogue();
	}

	public void getImage() {

		down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
		up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {

		int i = 0;
		int index = 1;
		int maxIndex = 2;

		dialogues[i] = "Please be cautious and always check your \nsurroundings because there is rumor of a \ndeadly quicksand somewhere in this area.\n\n"
				+ index + "/" + maxIndex + "                              Press Enter to Continue";
		index++;
		i++;
		dialogues[i] = "The demon king has the appearance of a blue \nslime, but it is far more powerful than any slime. \n\n\n"
				+ index + "/" + maxIndex + "                              Press Enter to Continue";
		index++;
		i++;

	}

	public void setAction() {

	}

	public void speak() {

		super.speak();

	}

}
