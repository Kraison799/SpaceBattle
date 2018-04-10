package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

public class LineClassB extends EnemyLine implements Drawable {
	private boolean change;
	private int bossIndex;
	
	public LineClassB(int posX, int posY, int speed, int size, int lvl) {
		super(posX, posY, speed, size, lvl);
		this.change = false;
		Random rand = new Random();
		int randBoss = rand.nextInt(this.getEnemies().size()-1);
		this.getEnemies().get(randBoss).setBoss();
		this.bossIndex = randBoss;
		this.setLineClass("LineClassB");
	}
	
	@ Override
	public void draw(Graphics2D g) {
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).draw(g);
		}
	}
	
	@ Override
	public void update(double delta) {
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosY(this.getEnemies().get(c).getPosY()+this.getSpeed());
			this.getEnemies().get(c).update(delta);
		}
		if(this.getTimer().timerEvent(1000)) {
			// If the line has two or more enemies, two of them are chosen to shoot. If there is only one enemy, that enemy will shoot.
			if(this.getEnemies().size() > 1) {
				Random rand = new Random();
				int shooter_1 = rand.nextInt(this.getEnemies().size()-1);
				int shooter_2 = rand.nextInt(this.getEnemies().size()-1);
				this.getEnemies().get(shooter_1).shoot();
				this.getEnemies().get(shooter_2).shoot();
			} else if(this.getTimer().timerEvent(1000) && this.getEnemies().size() == 1) {
				Random rand = new Random();
				int shooter_1 = rand.nextInt(this.getEnemies().size()-1);
				this.getEnemies().get(shooter_1).shoot();
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
