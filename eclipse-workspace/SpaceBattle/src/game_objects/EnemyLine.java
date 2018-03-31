package game_objects;

import adt.List;
import state_machine.Timer;

public abstract class EnemyLine {
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

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
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
	
	public void arrangeLine() {
		int dist = 0;
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosX(dist);
			dist += 35 + this.getEnemies().get(c).getWidth();
			System.out.println("Enemy width: "+this.getEnemies().get(c).getWidth());
			System.out.println("Dist: "+dist);
		}
		dist = (840 - this.getEnemies().get(this.getEnemies().size()-1).getPosX() - this.getEnemies().get(this.getEnemies().size()-1).getWidth())/2;
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosX(dist + this.getEnemies().get(c).getPosX());
		}
	}
}
