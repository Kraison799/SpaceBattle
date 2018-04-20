package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

import adt.CircleList;

public class LineClassD extends EnemyLine implements Drawable {
	public LineClassD(int posX, int posY, int speed, int size, int lvl) {
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
			Enemy enemy = new Enemy(posX, posY, 30, 50, 1+rand.nextInt(3), speed, sprite);
			this.getEnemies().add(enemy);
			counter++;
		}
		this.setLineClass("LineClassD");
	}
	
	public void orderLine() {
		boolean changed = false;
		int index = 0;
		while(index < this.getEnemies().size()-1) {
			if(this.getEnemies().get(index).getResistance() < this.getEnemies().get(index+1).getResistance()) {
				this.getEnemies().swap(index, index+1);
				changed = true;
			}
			++index;
		}
		if(changed) {
			this.orderLine();
		}
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
		Random rand = new Random();
		if(this.getEnemies().size() > 1) {
//			this.orderLine();
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
