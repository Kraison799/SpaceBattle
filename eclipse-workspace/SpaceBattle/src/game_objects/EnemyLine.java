package game_objects;

import java.awt.Graphics2D;

import adt.List;

public class EnemyLine {
	private List<Enemy> enemies;
	private int posX, posY, speed;
	private int size;
	
	public EnemyLine(int posX, int posY, int speed, int size) {
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.size = size;
		
		this.enemies = new List<Enemy>();
		
		for(int i = 0; i < size; i++) {
			Enemy enemy = new Enemy(0, 0, 30, 50, speed, "Invader_1");
			enemies.add(enemy);
		}
		arrangeLine();
	}
	
	public void arrangeLine() {
		if(size%2 == 1) {
			enemies.get(0).setPosX(posX-enemies.get(0).getWidth()/2);
			for(int i = 1; i < size; i+=2) {
				enemies.get(i).setPosX(posX-enemies.get(i).getWidth()/2-enemies.get(i).getWidth()*((i+1)/2)-50*((i+1)/2));
				enemies.get(i+1).setPosX(posX+enemies.get(i).getWidth()/2+enemies.get(i).getWidth()*((i)/2)+50*((i+1)/2));
			}
			return;
		}
		for(int i = 0; i < size; i+=2) {
			enemies.get(i).setPosX(posX-enemies.get(i).getWidth()/2-25-enemies.get(i).getWidth()*((i+1)/2)-50*((i+1)/2));
			enemies.get(i+1).setPosX(posX+enemies.get(i).getWidth()/2+25+enemies.get(i).getWidth()*((i+1)/2)+50*((i+1)/2));
		}
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < size; i++) {
			this.enemies.get(i).draw(g);
		}
	}
	
	public void update(double delta) {
		if(posY >= 360/16*9-50) {
			enemies = null;
		}
		for(int i = 0; i < size; i++) {
			enemies.get(i).setPosY(enemies.get(i).getPosY()+speed);
			enemies.get(i).update(delta);
		}
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

	public List<Enemy> getEnemies() {
		return enemies;
	}
}
