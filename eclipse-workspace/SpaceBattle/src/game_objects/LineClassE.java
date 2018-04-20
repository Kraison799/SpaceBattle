package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

import adt.DoubleLinkedList;

public class LineClassE extends EnemyLine implements Drawable {
	private double angle;

	public LineClassE(int posX, int posY, int speed, int size, int lvl) {
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
		this.setPosY(-70);
		Random rand = new Random();
		int newBoss = rand.nextInt(size-1);
		this.getEnemies().get(newBoss).setBoss();
		this.setHaveBoss(true);
		this.setLineClass("LineClassE");
	}

	@Override
	public void draw(Graphics2D g) {
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).draw(g);
		}
	}

	@Override
	public void update(double delta) {
		this.setHaveBoss(false);
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosY(this.getEnemies().get(c).getPosY()+this.getSpeed());
			this.getEnemies().get(c).update(delta);
			if(this.getEnemies().get(c).isBoss()) {
				this.setHaveBoss(true);
			}
		}
		this.arrangeLine();
		this.angle += 0.01;
		this.setPosY(this.getPosY()+1);
		for(int c = 0; c < this.getEnemies().size(); c++) {
			int dist = this.getEnemies().get(c).getPosX() - this.getPosX();
			int newPosX = (int) (dist * Math.cos(this.angle % 90));
			int newPosY = (int) (dist * Math.sin(this.angle % 90));
			this.getEnemies().get(c).setPosX(this.getPosX() + newPosX);
			this.getEnemies().get(c).setPosY(this.getPosY() + newPosY);
			this.getEnemies().get(c).update(delta);
		}
		if(this.getTimer().timerEvent(1000)) {
			if(this.getEnemies().size() > 1) {
				Random rand = new Random();;
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
