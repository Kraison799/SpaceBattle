package game_objects;

import adt.List;
import state_machine.Timer;

public abstract class EnemyLine implements Drawable {
	private List<Enemy> enemies;
	private int posX, posY, speed;
	private Timer timer;
	private String lineClass;
	private boolean haveBoss;
	
	public EnemyLine(int posX, int posY, int speed, int size, int lvl) {
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.lineClass = "EnemyLine";
		this.haveBoss = false;
		
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

	public boolean isHaveBoss() {
		return haveBoss;
	}

	public void setHaveBoss(boolean haveBoss) {
		this.haveBoss = haveBoss;
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
