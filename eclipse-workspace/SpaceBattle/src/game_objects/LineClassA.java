package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

public class LineClassA extends EnemyLine {
	public LineClassA(int posX, int posY, int speed, int size) {
		super(posX, posY, speed, size);
		this.getEnemies().get(size/2).setBoss();
	}

	public void arrangeLine() {
		if(this.getSize()%2 == 1) {
			for(int c = 0; c < this.getSize(); c++) {
				if(!(c == this.getSize()/2)) {
					int pos = c-(this.getSize()/2);
					this.getEnemies().get(c).setPosX(this.getPosX()+80*pos);
				} else if(c == this.getSize()/2) {
					this.getEnemies().get(c).setPosX(this.getPosX()-37);
				}
			}
		}
	}
	
	@ Override
	public void draw(Graphics2D g) {
		for(int c = 0; c < this.getSize(); c++) {
			this.getEnemies().get(c).draw(g);
		}
	}
	
	@ Override
	public void update(double delta) {
		for(int c = 0; c < this.getSize(); c++) {
			this.getEnemies().get(c).setPosY(this.getEnemies().get(c).getPosY()+this.getSpeed());
			this.getEnemies().get(c).update(delta);
		}
		this.arrangeLine();
		if(this.getTimer().timerEvent(1000)) {
			Random rand = new Random();;
			System.out.println(this.getSize());
			int shooter_1 = rand.nextInt(this.getSize()-1);
			int shooter_2 = rand.nextInt(this.getSize()-1);
			this.getEnemies().get(shooter_1).shoot();
			this.getEnemies().get(shooter_2).shoot();
		}
	}
}
