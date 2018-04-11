package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

public class LineClassC extends EnemyLine implements Drawable {
	public LineClassC(int posX, int posY, int speed, int size, int lvl) {
		super(posX, posY, speed, size, lvl);
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
			} else if(this.getTimer().timerEvent(1000) && this.getEnemies().size() == 1) {
				int shooter_1 = rand.nextInt(this.getEnemies().size()-1);
				this.getEnemies().get(shooter_1).shoot();
			}
		}
	}
}
