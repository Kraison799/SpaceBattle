package game_objects;

import java.awt.Rectangle;

public abstract class MoveableObject extends GameObject {
	private int speed;
	private boolean collide;
	private Rectangle rect;
	
	public MoveableObject(int posX, int posY, int width, int height, String spriteName, int speed) {
		super(posX, posY, width, height, spriteName);
		this.speed = speed;
		this.collide = false;
		this.setRect();
	}

	public int getSpeed() {
		return speed;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect() {
		this.rect = new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
	}

	public boolean isCollide(MoveableObject other) {
		collide = other.getRect().getBounds().intersects(this.getRect().getBounds());
		return collide;
	}
}
