package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

import adt.List;
import state_machine.Timer;

public abstract class EnemyLine implements Drawable {
	private List<Enemy> enemies;
	private int posX, posY, speed, size;
	private Timer timer;
	
	public EnemyLine(int posX, int posY, int speed, int size) {
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.size = size;
		
		int counter = 0;
		this.enemies = new List<Enemy>();
		while(counter < this.size) {
			Enemy enemy = new Enemy(posX, posY, 30, 50, 2, speed, "Invader_1");
			this.getEnemies().add(enemy);
			counter++;
		}
		
		this.timer = new Timer();
	}
	
	public List<Enemy> getEnemies() {
		return enemies;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getSpeed() {
		return speed;
	}

	public Timer getTimer() {
		return timer;
	}

	@ Override
	public void draw(Graphics2D g) {
		for(int c = 0; c < size; c++) {
			enemies.get(c).draw(g);
		}
	}
	
	@ Override
	public void update(double delta) {
		for(int c = 0; c < size; c++) {
			enemies.get(c).update(delta);
		}
		if(timer.timerEvent(1000)) {
			Random rand = new Random();;
			int shooter_1 = rand.nextInt(size-1);
			int shooter_2 = rand.nextInt(size-1);
			enemies.get(shooter_1).shoot();
			enemies.get(shooter_2).shoot();
		}
	}
}
