package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

public class LineClassD extends EnemyLine implements Drawable {
	private double angle;

	public LineClassD(int posX, int posY, int speed, int size) {
		super(posX, posY, speed, size);
		this.setPosY(-70);
	}

	@Override
	public void draw(Graphics2D g) {
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).draw(g);
		}
	}

	@Override
	public void update(double delta) {
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosY(this.getEnemies().get(c).getPosY()+this.getSpeed());
			this.getEnemies().get(c).update(delta);
		}
		this.arrangeLine();
		this.angle += 0.01;
		this.setPosY(this.getPosY()+1);
		for(int c = 0; c < this.getEnemies().size(); c++) {
			int dist = this.getEnemies().get(c).getPosX() - this.getPosX();
			int newPosX = (int) (dist * Math.cos(this.angle % 90));
			System.out.println(newPosX);
			int newPosY = (int) (dist * Math.sin(this.angle % 90));
			this.getEnemies().get(c).setPosX(this.getPosX() + newPosX);
			this.getEnemies().get(c).setPosY(this.getPosY() + newPosY);
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
		}
	}
}
