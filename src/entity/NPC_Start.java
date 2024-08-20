package entity;

import main.GamePanel;

public class NPC_Start extends Entity {

	public NPC_Start(GamePanel gp) {

		super(gp);

		direction = "down";
		speed = 0;
		getImage();
		setDialogue();
	}

	public void getImage() {

		down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {

		int i = 0;
		int index = 1;
		int maxIndex = 8;

		dialogues[i] = "Welcome to the Island of Moore! I am a former \nhero of this world.\n\n\n" + index + "/"
				+ maxIndex + "                              Press Enter to Continue";
		index++;
		i++;
		dialogues[i] = "You're probably wondering how you got here.\n\n\n\n" + index + "/" + maxIndex
				+ "                              Press Enter to Continue";
		index++;
		i++;
		dialogues[i] = "Every century, we call upon the great heroes to \nhelp us defeat the demon king.\n\n\n" + index
				+ "/" + maxIndex + "                              Press Enter to Continue";
		index++;
		i++;
		dialogues[i] = "If he hadn't been stopped, our world would \nbe on the verge of destruction.\n\n\n" + index
				+ "/" + maxIndex + "                              Press Enter to Continue";
		index++;
		i++;
		dialogues[i] = "I will be the one to accompany you on your \njourney.\n\n\n" + index + "/" + maxIndex
				+ "                              Press Enter to Continue";
		index++;
		i++;
		dialogues[i] = "You will be given a shield and a sword to defend \nyourself against hostile creatures.\n\n\n"
				+ index + "/" + maxIndex + "                              Press Enter to Continue";
		index++;
		i++;
		dialogues[i] = "There are poisonous slimes lurking around, so \nbe careful out there.Never allow yourself to be \ntouched by them!\n\n"
				+ index + "/" + maxIndex + "                              Press Enter to Continue";
		index++;
		i++;
		dialogues[i] = "Go forth, hero, but proceed with caution. Our \nworld's future depends on you!\n\n\n" + index
				+ "/" + maxIndex + "                              Press Enter to Continue";
		index++;
		i++;

	}

	public void setAction() {

	}

	public void speak() {

		super.speak();

	}

}
