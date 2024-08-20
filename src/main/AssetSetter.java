package main;

import entity.NPC_QuestSword;
import entity.NPC_QuickSand;
import entity.NPC_Start;
import monster.MON_BlueSlime;
import monster.MON_GreenSlime;
import monster.MON_RedSlime;
import object.OBJ_Chest;
import object.OBJ_ChestKey;
import object.OBJ_Door;
import object.OBJ_DoorKey;
import object.OBJ_LChest;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;

	}

	public void setObject() {

		int i = 0;

		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 11 * gp.tileSize;
		gp.obj[i].worldY = 37 * gp.tileSize;
		i++;

		gp.obj[i] = new OBJ_LChest(gp);
		gp.obj[i].worldX = 8 * gp.tileSize;
		gp.obj[i].worldY = 41 * gp.tileSize;
		i++;

		gp.obj[i] = new OBJ_Chest(gp);
		gp.obj[i].worldX = 22 * gp.tileSize;
		gp.obj[i].worldY = 42 * gp.tileSize;
		i++;
		
		gp.obj[i] = new OBJ_Chest(gp);
		gp.obj[i].worldX = 28 * gp.tileSize;
		gp.obj[i].worldY = 33 * gp.tileSize;
		i++;
		
		gp.obj[i] = new OBJ_Chest(gp);
		gp.obj[i].worldX = 31 * gp.tileSize;
		gp.obj[i].worldY = 32 * gp.tileSize;
		i++;
		
		gp.obj[i] = new OBJ_Chest(gp);
		gp.obj[i].worldX = 27 * gp.tileSize;
		gp.obj[i].worldY = 27 * gp.tileSize;
		i++;

		gp.obj[i] = new OBJ_ChestKey(gp);
		gp.obj[i].worldX = 23 * gp.tileSize;
		gp.obj[i].worldY = 42 * gp.tileSize;
		i++;
		
		gp.obj[i] = new OBJ_DoorKey(gp);
		gp.obj[i].worldX = 33 * gp.tileSize;
		gp.obj[i].worldY = 7 * gp.tileSize;
		i++;

	}

	public void setNPC() {

		int i = 0;

		gp.npc[i] = new NPC_Start(gp);
		gp.npc[i].worldX = gp.tileSize * 10;
		gp.npc[i].worldY = gp.tileSize * 8;
		i++;

		gp.npc[i] = new NPC_QuestSword(gp);
		gp.npc[i].worldX = gp.tileSize * 12;
		gp.npc[i].worldY = gp.tileSize * 34;
		i++;

		gp.npc[i] = new NPC_QuickSand(gp);
		gp.npc[i].worldX = gp.tileSize * 24;
		gp.npc[i].worldY = gp.tileSize * 27;
		i++;

	}

	public void setMonster() {

		int i = 0, x1 = 20, ia = 19;

		// Pond
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * x1;
		gp.monster[i].worldY = gp.tileSize * 7;
		i++;
		x1++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * x1;
		gp.monster[i].worldY = gp.tileSize * 7;
		i++;
		x1++;
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize * x1;
		gp.monster[i].worldY = gp.tileSize * 7;
		i++;
		x1++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * x1;
		gp.monster[i].worldY = gp.tileSize * 7;
		i++;
		x1++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * x1;
		gp.monster[i].worldY = gp.tileSize * 7;
		i++;
		x1++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * x1;
		gp.monster[i].worldY = gp.tileSize * 7;
		i++;
		x1++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * x1;
		gp.monster[i].worldY = gp.tileSize * 7;
		i++;

		// Treasure
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 8;
		gp.monster[i].worldY = gp.tileSize * 39;
		i++;
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 10;
		gp.monster[i].worldY = gp.tileSize * 40;
		i++;

		// QuickSand
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * ia;
		gp.monster[i].worldY = gp.tileSize * 41;
		i++;
		ia++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * ia;
		gp.monster[i].worldY = gp.tileSize * 41;
		i++;
		ia++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * ia;
		gp.monster[i].worldY = gp.tileSize * 41;
		i++;
		ia++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * ia;
		gp.monster[i].worldY = gp.tileSize * 41;
		i++;
		ia++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * ia;
		gp.monster[i].worldY = gp.tileSize * 41;
		i++;
		ia++;
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 29;
		gp.monster[i].worldY = gp.tileSize * 40;
		i++;
		ia++;
		
		gp.monster[i] = new MON_BlueSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 37;
		gp.monster[i].worldY = gp.tileSize * 9;
		i++;
		
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 37;
		gp.monster[i].worldY = gp.tileSize * 11;
		i++;

		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 35;
		gp.monster[i].worldY = gp.tileSize * 11;
		i++;
		
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 40;
		gp.monster[i].worldY = gp.tileSize * 8;
		i++;
		
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 35;
		gp.monster[i].worldY = gp.tileSize * 40;
		i++;
		
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 38;
		gp.monster[i].worldY = gp.tileSize * 40;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 30;
		gp.monster[i].worldY = gp.tileSize * 28;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 30;
		gp.monster[i].worldY = gp.tileSize * 33;
		i++;
		
		
	}

}
