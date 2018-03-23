package game_objects;

import java.awt.Graphics2D;

import adt.List;

public class Enemy extends MoveableObject implements Drawable {
	private List<Bullet> bullets;

	public Enemy(int posX, int posY, int width, int height, int speed, String spriteName) {
		super(posX, posY, width, height, speed, spriteName);
		this.bullets = new List<Bullet>();
	}
	
	public void shoot() {
		Bullet bullet = new Bullet(this.getPosX()+7, this.getPosY()+this.getHeight(), 15, 30, 4, "Laser");
		bullets.add(bullet);
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
