package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

import adt.DoubleLinkedList;

/**
 * This enemy line is similar to the class A, but the boss in this line will randomly change its position. 
 * @author Victor Castrillo
 *
 */
public class LineClassB extends EnemyLine implements Drawable {
	private boolean change;
	private int bossIndex;
	
	public LineClassB(int posX, int posY, int speed, int size, int lvl) {
		super(posX, posY, speed, size, lvl);
		int counter = 0;
		this.setEnemies(new DoubleLinkedList<Enemy>());
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
		this.change = false;
		Random rand = new Random();
		int randBoss = rand.nextInt(this.getEnemies().size()-1);
		this.getEnemies().get(randBoss).setBoss();
		this.setHaveBoss(true);
		this.bossIndex = randBoss;
		this.setLineClass("LineClassB");
	}
	
	@ Override
	public void draw(Graphics2D g) {
		try {
			for(int c = 0; c < this.getEnemies().size(); c++) {
				this.getEnemies().get(c).draw(g);
			}
		} catch(Exception e) {
			this.draw(g);
		}
	}
	
	@ Override
	public void update(double delta) {
		this.setHaveBoss(false);
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosY(this.getEnemies().get(c).getPosY()+this.getSpeed());
			this.getEnemies().get(c).update(delta);
			if(this.getEnemies().get(c).isBoss()) {
				this.setHaveBoss(true);
			}
		}
		if(this.getTimer().timerEvent(1000)) {
			// If the line has two or more enemies, two of them are chosen to shoot. If there is only one enemy, that enemy will shoot.
			if(this.getEnemies().size() > 1) {
				Random rand = new Random();
				int shooter_1 = rand.nextInt(this.getEnemies().size()-1);
				int shooter_2 = rand.nextInt(this.getEnemies().size()-1);
				this.getEnemies().get(shooter_1).shoot();
				this.getEnemies().get(shooter_2).shoot();
			} else if(this.getEnemies().size() == 1) {
				this.getEnemies().get(0).shoot();
			}
			if(change && this.getEnemies().size() > 1) {
				Random rand = new Random();
				int newPos = rand.nextInt(this.getEnemies().size()-1);
				this.getEnemies().swap(bossIndex, newPos);
				bossIndex = newPos;
				change = false;
			} else {
				change = true;
			}
		}
	}
}
