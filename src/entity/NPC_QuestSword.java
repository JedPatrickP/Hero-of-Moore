package entity;

import main.GamePanel;

public class NPC_QuestSword extends Entity {

	public NPC_QuestSword(GamePanel gp) {

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

		dialogues[i] = "Obtain the key to this door in order to get the \nmost powerful sword that will aid you in \ndefeating the demon king.\n\n" + index + "/"
				+ maxIndex + "                              Press Enter to Continue";
		index++;
		i++;
		dialogues[i] = "The key, if I'm not mistaken, will be found near \nthe pond. However, there are numerous \nmonsters guarding that location.\n\n" + index + "/"
				+ maxIndex + "                              Press Enter to Continue";
		

	}

	public void setAction() {

	}

	public void speak() {

		super.speak();

	}

}
