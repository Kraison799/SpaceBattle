package display;

import java.awt.Canvas;
import java.awt.Graphics2D;

import game_objects.BasicLine;
import game_objects.Player;
import state_machine.SuperStateMachine;

public class GameScreen  implements SuperStateMachine {
	private Player player;
	private BasicLine enemies;
	
	public GameScreen() {
		player = new Player(280*3/2-25, 360/16*9*3-55, 50, 50, "Spaceship_1");
		enemies = new BasicLine(280*3/2, -50, 1, 4);
	}

	@Override
	public void draw(Graphics2D g) {
		player.draw(g);
		enemies.draw(g);
	}

	@Override
	public void update(double delta) {
		for(int b = 0; b < player.getBullets().size(); b++) {
			for(int e = 0; e < enemies.getEnemies().size(); e++) {
				if(player.getBullets().get(b).isColliding(enemies.getEnemies().get(e))) {
					enemies.getEnemies().remove(e);
					player.getBullets().remove(b);
				}
			}
		}
		player.update(delta);
		enemies.update(delta);
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(player);
	}

}
