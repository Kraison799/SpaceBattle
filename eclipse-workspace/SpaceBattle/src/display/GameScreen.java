package display;

import java.awt.Canvas;
import java.awt.Graphics2D;

import game_objects.EnemyLine;
import game_objects.Player;
import state_machine.SuperStateMachine;

public class GameScreen  implements SuperStateMachine {
	private Player player;
	private EnemyLine enemies;
	
	public GameScreen() {
		player = new Player(360*3/2-25, 360/16*9*3-55, 50, 50, "Spaceship_1");
		enemies = new EnemyLine(360*3/2, -50, 1, 7);
	}

	@Override
	public void draw(Graphics2D g) {
		player.draw(g);
		enemies.draw(g);
	}

	@Override
	public void update(double delta) {
		player.update(delta);
		enemies.update(delta);
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(player);
	}

}
