package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import constructor.Level;
import game_objects.Player;
import state_machine.StateMachine;
import state_machine.SuperStateMachine;

public class GameScreen extends SuperStateMachine implements KeyListener {
	private Player player;
	private Level level;
	private int levelCounter;
	private int score;
	private BufferedImage bg;
	
	public GameScreen(StateMachine stateMachine) {
		super(stateMachine);
		player = new Player(280*3/2-25, 360/16*9*3-55, 50, 50, "Spaceship_1");
		level = new Level(1);
		levelCounter = 1;
		score = 0;
		
		try {
			URL url = this.getClass().getResource("/images/Background.png");
			bg = ImageIO.read(url);
		} catch(IOException e) {e.printStackTrace();}
	}
	
	public void reset() {
		player = new Player(280*3/2-25, 360/16*9*3-55, 50, 50, "Spaceship_1");
		level = new Level(1);
		levelCounter = 1;
	}
	
	public void gameOver(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 350*3+10, 200*3+10);
		BufferedImage go = null;
		try {
			URL url = this.getClass().getResource("/images/GameOver.png");
			go = ImageIO.read(url);
		} catch(IOException e) {e.printStackTrace();}
		g.drawImage(go, (350*3+10)/2-go.getWidth(), 100, go.getWidth()*2, go.getHeight()*2, null);
	}
	
	public void showInfo() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(!this.player.isAlive()) {
			this.gameOver(g);
			return;
		}
		g.drawImage(bg, 0, 0, 280*3+10, 200*3+10, null);
		g.setColor(Color.darkGray);
		g.fillRect(280*3+10, 0, 350*3-280*3, 200*3+10);
		player.draw(g);
		level.draw(g);
	}

	@Override
	public void update(double delta) {
		if(!player.isAlive()) {
			return;
		}
		if(level.getLineCounter() == 4) {
			++levelCounter;
			level = new Level(levelCounter);
		}
		// Loop to destroy enemies
		for(int e = 0; e < level.getCurrent().getEnemies().size(); e++) {
			int b = 0;
			while(b < player.getBullets().size()) {
				if(player.getBullets().get(b).isColliding(level.getCurrent().getEnemies().get(e))) {
					player.getBullets().remove(b);
					if(level.getCurrent().getEnemies().get(e).destroy()) {
						if(level.getCurrent().getEnemies().get(e).isBoss()) {
							this.score += 500;
						} else {
							this.score += 100;
						}
						level.getCurrent().getEnemies().remove(e);
						if(level.getCurrent().getEnemies().size() == 0) {
							level.nextLine();
							level.getCurrent().update(delta);
						}
						--e;
					}
				} else {
				b++;
				}
			}
		}
		level.getCurrent().arrangeLine();
		// Loop to destroy the player
		for(int e = 0; e < level.getCurrent().getEnemies().size(); e++) {
			for(int eb = 0; eb < level.getCurrent().getEnemies().get(e).getBullets().size(); eb++) {
				if(level.getCurrent().getEnemies().get(e).getBullets().get(eb).isColliding(player) && eb < level.getCurrent().getEnemies().get(e).getBullets().size()) {
					player.loseLife();
					level.getCurrent().getEnemies().get(e).getBullets().remove(eb);
				}
			}
		}
		// Loop to destroy the player's bullets out of the screen
		for(int pb = 0; pb < player.getBullets().size(); pb++) {
			if(player.getBullets().get(pb).getPosY() < -50) {
				player.getBullets().remove(pb);
				--pb;
			}
		}
		// Loop to destroy the enemy's bullets for each enemy
		for(int e = 0; e < level.getCurrent().getEnemies().size(); e++) {
			for(int eb = 0; eb < level.getCurrent().getEnemies().get(e).getBullets().size(); eb++) {
				if(level.getCurrent().getEnemies().get(e).getBullets().get(eb).getPosY() > 600) {
					level.getCurrent().getEnemies().get(e).getBullets().remove(eb);
					--eb;
				}
			}
		}
		// Loop to get collisions between enemies and the player
		for(int e = 0; e < level.getCurrent().getEnemies().size(); e++) {
			if(level.getCurrent().getEnemies().get(e).isColliding(player)) {
				if(level.getCurrent().getEnemies().get(e).isBoss()) {
					this.score += 500;
				} else {
					this.score += 100;
				}
				level.getCurrent().getEnemies().remove(e);
				if(level.getCurrent().getEnemies().size() == 0) {
					level.nextLine();
					level.update(delta);
				}
				player.loseLife();
			}
		}
		// Update for the objects in the screen
		player.update(delta);
		level.update(delta);
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(player);
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) {
			this.reset();
			this.getStateMachine().setState((byte) 0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
