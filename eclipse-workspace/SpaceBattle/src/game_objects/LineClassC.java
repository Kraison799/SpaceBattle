package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

import adt.CircleList;

/**
 * In this enemy line, when the boss die, another enemy takes its place as boss.
 * @author Victor Castrillo
 *
 */
public class LineClassC extends EnemyLine implements Drawable {
	public LineClassC(int posX, int posY, int speed, int size, int lvl) {
		super(posX, posY, speed, size, lvl);
		int counter = 0;
		this.setEnemies(new CircleList<Enemy>());
		while(counter < size) {
			Random rand = new Random();
			int imageIndex = rand.nextInt(3);
			String sprite = "";
			if (imageIndex == 0) {
				sprite = "Invader_1";
			} else if(imageIndex == 1) {
				sprite = "Invader_2";
			} else if(imageIndex == 2) {
				sprite = "Invader_3";
			} else if(imageIndex == 3) {
				sprite = "Invader_4";
			}
			Enemy enemy = new Enemy(posX, posY, 30, 50, lvl, speed, sprite);
			this.getEnemies().add(enemy);
			counter++;
		}
		this.getEnemies().get(size/2).setBoss();
		this.setHaveBoss(true);
		this.setLineClass("LineClassC");
	}
	
	@ Override
	public void draw(Graphics2D g) {
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).draw(g);
		}
	}
	
	@ Override
	public void update(double delta) {
		this.setHaveBoss(false);
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosY(this.getEnemies().get(c).getPosY()+this.getSpeed());
			this.getEnemies().get(c).update(delta);
			if(this.getEnemies().get(c).isBoss()) {
				this.setHaveBoss(true);;
			}
		}
		Random rand = new Random();
		if(!this.isHaveBoss() && this.getEnemies().size() != 1) {
			int newBoss = rand.nextInt(this.getEnemies().size()-1);
			this.getEnemies().get(newBoss).setBoss();
		} else if(!this.isHaveBoss() && this.getEnemies().size() == 1) {
			this.getEnemies().get(0).setBoss();
		}
		this.arrangeLine();
		if(this.getTimer().timerEvent(1000)) {
			if(this.getEnemies().size() > 1) {
				int shooter_1 = rand.nextInt(this.getEnemies().size()-1);
				int shooter_2 = rand.nextInt(this.getEnemies().size()-1);
				this.getEnemies().get(shooter_1).shoot();
				this.getEnemies().get(shooter_2).shoot();
			} else if(this.getEnemies().size() == 1) {
				this.getEnemies().get(0).shoot();
			}
		}
	}
}
