package game_objects;

import java.util.Random;

import adt.List;
import state_machine.Timer;

public abstract class EnemyLine {
	private List<Enemy> enemies;
	private int posX, posY, speed, size;
	private Timer timer;
	private String lineClass;
	
	public EnemyLine(int posX, int posY, int speed, int size) {
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.size = size;
		this.lineClass = "EnemyLine";
		
		int counter = 0;
		this.enemies = new List<Enemy>();
		while(counter < this.size) {
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
			Enemy enemy = new Enemy(posX, posY, 30, 50, 1, speed, sprite);
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
	
	public String getLineClass() {
		return lineClass;
	}

	public void setLineClass(String lineClass) {
		this.lineClass = lineClass;
	}

	public void arrangeLine() {
		int dist = 0;
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosX(dist);
			dist += 35 + this.getEnemies().get(c).getWidth();
		}
		dist = (840 - this.getEnemies().get(this.getEnemies().size()-1).getPosX() - this.getEnemies().get(this.getEnemies().size()-1).getWidth())/2;
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosX(dist + this.getEnemies().get(c).getPosX());
		}
	}
}
