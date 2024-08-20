package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	public boolean showDebugText = false;
	public final boolean debug = true;
	GamePanel gp;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		if (gp.gameState == gp.controlState) {
			controlState(code);
		} else if (gp.gameState == gp.titleState) {
			titleState(code);
		} else if (gp.gameState == gp.playState) {
			playState(code);
		} else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		} else if (gp.gameState == gp.dialogueState) {
			dialogueState(code);
		} else if (gp.gameState == gp.characterState) {
			characterState(code);
		} else if (gp.gameState == gp.gameOverState) {
			gameOverState(code);
		} else if (gp.gameState == gp.highScoreState) {
			highScoreState(code);
		} else if (gp.gameState == gp.optionState) {
			optionState(code);
		} else if (gp.gameState == gp.finishState) {
			
			finishState(code);
			
		}

	}

	public void finishState(int code) {
		
		if (code == KeyEvent.VK_ENTER) {

			gp.stopMusic();
			gp.gameState = gp.titleState;
			gp.restart();

		}
		
	}
	
	public void optionState(int code) {

		if (code == KeyEvent.VK_ESCAPE) {

			gp.gameState = gp.optionState;
			gp.ui.subState = 0;

		}
		if (code == KeyEvent.VK_ENTER) {
			gp.playSE(9);
			enterPressed = true;

		}

		if (code == KeyEvent.VK_W) {

			if (gp.ui.subState == 2) {

				if (gp.ui.commandNum == 0) {

				}

			} else {
				if (gp.ui.commandNum == 0) {

				} else {
					gp.ui.commandNum--;
					gp.playSE(9);
				}
			}

		}

		if (code == KeyEvent.VK_S) {

			if (gp.ui.subState == 1) {

				if (gp.ui.commandNum == 0) {

				}

			} else if (gp.ui.subState == 2) {

				if (gp.ui.commandNum == 0) {

				}

			} else if (gp.ui.subState == 3) {

				if (gp.ui.commandNum != 0) {

					gp.ui.commandNum--;

				}

				if (gp.ui.commandNum != 1) {
					gp.ui.commandNum++;
				}

			} else {

				if (gp.ui.commandNum == 4) {

				} else {
					gp.ui.commandNum++;
					gp.playSE(9);
				}

			}

		}

		if (code == KeyEvent.VK_A) {

			if (gp.ui.subState == 0) {

				if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(9);
				}

				if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(9);
				}

			}

		}
		if (code == KeyEvent.VK_D) {

			if (gp.ui.subState == 0) {

				if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(9);
				}
				if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(9);
				}

			}
		}

	}

	public void controlState(int code) {

		if (code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.titleState;
		}

	}

	public void highScoreState(int code) {

		if (code == KeyEvent.VK_ENTER) {

			gp.gameState = gp.titleState;
			gp.playSE(9);
			gp.ui.commandNum = 0;

		}

	}

	public void loadGameState(int code) {

		if (code == KeyEvent.VK_W && gp.ui.noSave == false && gp.ui.commandNum != 0) {

			gp.ui.commandNum--;
			gp.playSE(9);
		}

		if (code == KeyEvent.VK_S && gp.ui.noSave == false && gp.ui.commandNum != 1) {

			gp.ui.commandNum++;
			gp.playSE(9);
		}

		if (code == KeyEvent.VK_ENTER) {
			
			if(gp.ui.commandNum == 1) {
				
				gp.gameState = gp.titleState;
				
			} else if(gp.ui.commandNum == 0){
				
				gp.gameState = gp.playState;
				
			}
			

		}

	}

	public void titleState(int code) {

		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
			gp.playSE(9);
		}

		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if (gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(9);
		}

		if (code == KeyEvent.VK_ENTER) {

			gp.playSE(9);

			if (gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.playSE(9);
				gp.playMusic(0);
			}
			
			if (gp.ui.commandNum == 1) {
				gp.playSE(9);
				gp.gameState = gp.highScoreState;
			}
			if (gp.ui.commandNum == 2) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Close?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				gp.playSE(9);
				if (result == JOptionPane.YES_OPTION) {
					gp.playSE(9);
					System.exit(0);
				}

			}

		}

	}

	public void playState(int code) {

		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		if (code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if (code == KeyEvent.VK_H) {

			if (showDebugText == false) {
				showDebugText = true;
			} else if (showDebugText == true) {
				showDebugText = false;
			}
		}

		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionState;
		}

	}

	public void pauseState(int code) {

		if (code == KeyEvent.VK_P) {

			gp.gameState = gp.playState;

		}

	}

	public void dialogueState(int code) {

		if (code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
		}

	}

	public void gameOverState(int code) {

		showDebugText = false;

		if (code == KeyEvent.VK_ENTER) {

			gp.gameState = gp.titleState;
			gp.restart();
		}
	}

	public void characterState(int code) {

		if (code == KeyEvent.VK_C) {

			gp.gameState = gp.playState;

		}

		if (code == KeyEvent.VK_W) {
			if (gp.ui.slotRow != 0) {

				gp.ui.slotRow--;
				gp.playSE(9);
			}

		}
		if (code == KeyEvent.VK_A) {
			if (gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_S) {
			if (gp.ui.slotRow != 3) {
				gp.ui.slotRow++;
				gp.playSE(9);
			}

		}
		if (code == KeyEvent.VK_D) {
			if (gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
				gp.playSE(9);
			}
		}

		if (code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
	}

}
