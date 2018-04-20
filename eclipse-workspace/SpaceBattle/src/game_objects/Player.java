package game_objects;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import adt.LinkedList;
import state_machine.Timer;

public class Player extends MoveableObject implements KeyListener, Drawable {
	private boolean right, left, shoot, alive;
	private int cooldown, lifes;
	private Timer timer;
	
	private LinkedList<Bullet> bullets = new LinkedList<Bullet>();

	public Player(int posX, int posY, int width, int height, String spriteName) {
		super(posX, posY, width, height, 5, spriteName);
		this.right = false;
		this.left = false;
		this.shoot = false;
		this.alive = true;
		this.cooldown = 500;
		this.lifes = 5;
		
		this.bullets = new LinkedList<Bullet>();
		
		this.timer = new Timer();
	}
	
	public void shoot() {
		if(timer.timerEvent(cooldown)) {
			Bullet bullet = new Bullet(this.getPosX()+22, this.getPosY()-30, 10, 30, -6, "MissileSprite");
			bullets.add(bullet);
		}
	}
	
	public LinkedList<Bullet> getBullets() {
		return bullets;
	}
	
	public int getLifes() {
		return lifes;
	}
	
	public void loseLife() {
		if(lifes >= 1) {
			--lifes;
		} else if(lifes == 0) {
			this.alive = false;
		}
	}

	public boolean isAlive() {
		return alive;
	}

	@ Override
	public void draw(Graphics2D g) {
		g.drawImage(this.getSprite(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), null);
		for(int r = 0; r < bullets.size(); r++) {
			bullets.get(r).draw(g);
		}
	}
	
	@ Override
	public void update(double delta) {
		for(int r = 0; r < bullets.size(); r++) {
			bullets.get(r).update(delta);
		}
		
		if(right && !left && this.getPosX() < 280*3-this.getWidth()+10) {
			this.setPosX(this.getPosX()+this.getSpeed());
		} else if(!right && left && this.getPosX() > 0) {
			this.setPosX(this.getPosX()-this.getSpeed());
		}
		if(shoot) {
			this.shoot();
		}
		this.setRect();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			right = true;
		} else if(key == KeyEvent.VK_LEFT) {
			left = true;
		} else if (key == KeyEvent.VK_SPACE) {
			shoot = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			right = false;
		} else if(key == KeyEvent.VK_LEFT) {
			left = false;
		} else if(key == KeyEvent.VK_SPACE) {
			shoot = false;
		} 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
