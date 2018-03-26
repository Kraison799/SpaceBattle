package game_objects;

import java.awt.Graphics2D;

import adt.List;

public class Enemy extends MoveableObject implements Drawable {
	private List<Bullet> bullets;
	private int resistance;
	private boolean boss, destroy;
	private String bulletSprite;

	public Enemy(int posX, int posY, int width, int height, int resistance, int speed, String spriteName) {
		super(posX, posY, width, height, speed, spriteName);
		this.resistance = resistance;
		this.bullets = new List<Bullet>();
		this.bulletSprite = "Laser";
		this.boss = false;
	}
	
	public void shoot() {
		if(!boss) {
			Bullet bullet = new Bullet(this.getPosX()+7, this.getPosY()+this.getHeight(), 15, 30, 4, bulletSprite);
			bullets.add(bullet);
		} else if(boss) {
			Bullet bullet = new Bullet(this.getPosX()+36, this.getPosY()+this.getHeight(), 30, 50, 4, bulletSprite);
			bullets.add(bullet);
		}
	}
	
	public void getDamage() {
		if(resistance == 1) {
			resistance--;
			destroy = true;
		}
		resistance--;
		destroy = false;
	}
	
	public void setBoss() {
		this.boss = true;
		this.bulletSprite = "MegaLaser";
		this.resistance *= 3;
		this.setImage("Boss_1");
		this.setWidth(100);
		this.setHeight(75);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(this.getSprite(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), null);
		for(int r = 0; r < bullets.size(); r++) {
			bullets.get(r).draw(g);
		}
	}

	@Override
	public void update(double delta) {
		for(int r = 0; r < bullets.size(); r++) {
			bullets.get(r).update(delta);
		}
		this.setRect();
	}
}
