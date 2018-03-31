package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

import adt.List;

public class LineClassB extends EnemyLine implements Drawable {
	private boolean change;
	private int bossIndex;
	
	public LineClassB(int posX, int posY, int speed, int size) {
		super(posX, posY, speed, size);
		this.change = false;
		Random rand = new Random();
		int randBoss = rand.nextInt(this.getEnemies().size()-1);
		this.getEnemies().get(randBoss).setBoss();
		this.bossIndex = randBoss;
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
			if(change) {
				Random rand = new Random();
				int newPos = rand.nextInt(this.getEnemies().size()-1);
				List<Enemy> newList = new List<Enemy>();
				newList.add(this.getEnemies().get(bossIndex));
				for(int c = 0; c < this.getEnemies().size(); c++) {
					if(!this.getEnemies().get(c).isBoss() && c != newPos) {
						newList.add(this.getEnemies().get(c));
					} else if(!this.getEnemies().get(c).isBoss() && c == newPos) {
						newList.add(this.getEnemies().get(bossIndex));
						newList.add(this.getEnemies().get(c));
					} else if(this.getEnemies().get(c).isBoss() && c == newPos) {
						newList = this.getEnemies();
						return;
					}
				}
				this.setEnemies(newList);
				change = false;
			} else {
				change = true;
			}
		}
		this.arrangeLine();
	}
}
