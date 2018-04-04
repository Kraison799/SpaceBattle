package display;

import java.awt.Canvas;
import java.awt.Graphics2D;

import game_objects.BasicLine;
import game_objects.EnemyLine;
import game_objects.LineClassA;
import game_objects.LineClassB;
import game_objects.LineClassC;
import game_objects.LineClassE;
import game_objects.Player;
import state_machine.SuperStateMachine;

public class GameScreen  implements SuperStateMachine {
	private Player player;
	private LineClassB enemies;
	
	public GameScreen() {
		player = new Player(280*3/2-25, 360/16*9*3-55, 50, 50, "Spaceship_1");
		enemies = new LineClassB(280*3/2, -60, 1, 7);
	}

	@Override
	public void draw(Graphics2D g) {
		player.draw(g);
		enemies.draw(g);
	}

	@Override
	public void update(double delta) {
		if(this.enemies.getEnemies().get(0) == null) {
			return;
		}
		// Loop to destroy enemies
		for(int e = 0; e < enemies.getEnemies().size(); e++) {
			int b = 0;
			while(b < player.getBullets().size()) {
				if(player.getBullets().get(b).isColliding(enemies.getEnemies().get(e))) {
					player.getBullets().remove(b);
					if(enemies.getEnemies().get(e).destroy()) {
						enemies.getEnemies().remove(e);
						--e;
					}
				} else {
				b++;
				}
			}
		}
		enemies.arrangeLine();
		// Loop to destroy the player
		for(int e = 0; e < enemies.getEnemies().size(); e++) {
			for(int eb = 0; eb < enemies.getEnemies().get(e).getBullets().size(); eb++) {
				if(enemies.getEnemies().get(e).getBullets().get(eb).isColliding(player) && eb < enemies.getEnemies().get(e).getBullets().size()) {
					player.loseLife();
					enemies.getEnemies().get(e).getBullets().remove(eb);
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
		for(int e = 0; e < enemies.getEnemies().size(); e++) {
			for(int eb = 0; eb < enemies.getEnemies().get(e).getBullets().size(); eb++) {
				if(enemies.getEnemies().get(e).getBullets().get(eb).getPosY() > 600) {
					enemies.getEnemies().get(e).getBullets().remove(eb);
					--eb;
				}
			}
		}
		// Loop to get collisions between enemies and the player
		for(int e = 0; e < enemies.getEnemies().size(); e++) {
			if(enemies.getEnemies().get(e).isColliding(player)) {
				enemies.getEnemies().remove(e);
				player.loseLife();
			}
		}
		// Update for the objects in the screen
		player.update(delta);
		enemies.update(delta);
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(player);
	}

}
