package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class HighScore {

	GamePanel gp;

	boolean check;

	public HighScore(GamePanel gp) {
		this.gp = gp;
	}

	public void saveScore() throws Exception {

		BufferedWriter bw = new BufferedWriter(new FileWriter("highscore.txt"));

		bw.write(String.valueOf(gp.scoreList.get(0)));
		bw.newLine();

		bw.write(String.valueOf(gp.scoreList.get(1)));
		bw.newLine();

		bw.write(String.valueOf(gp.scoreList.get(2)));
		bw.newLine();

		bw.write(String.valueOf(gp.scoreList.get(3)));
		bw.newLine();

		bw.write(String.valueOf(gp.scoreList.get(4)));

		bw.close();

	}

	public void dummyScore() {

		if (gp.scoreList.size() == 0) {
			int i = 0;
			while (gp.scoreList.size() != 5) {

				gp.scoreList.add(i, 0);
				i++;
			}
		}

	}

	public boolean nullChecker() throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("highscore.txt"));

		if (br.readLine() == null) {
			return check = true;
		} else {
			return check = false;
		}

	}

	public void loadScore() {

		try {

			BufferedReader br = new BufferedReader(new FileReader("highscore.txt"));

			String s = br.readLine();
			gp.scoreList.add(0, Integer.parseInt(s));

			String s1 = br.readLine();
			gp.scoreList.add(1, Integer.parseInt(s1));

			String s2 = br.readLine();
			gp.scoreList.add(2, Integer.parseInt(s2));

			String s3 = br.readLine();
			gp.scoreList.add(3, Integer.parseInt(s3));

			String s4 = br.readLine();
			gp.scoreList.add(4, Integer.parseInt(s4));

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
