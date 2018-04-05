package constructor;

import java.awt.Graphics2D;
import java.util.Random;

import game_objects.BasicLine;
import game_objects.Drawable;
import game_objects.EnemyLine;
import game_objects.LineClassA;
import game_objects.LineClassB;
import game_objects.LineClassC;
import game_objects.LineClassD;
import game_objects.LineClassE;

public class Level implements Drawable {
	private EnemyLine current;
	private EnemyLine next;
	private int lineCounter;
	
	public Level() {
		current = new BasicLine(280*3/2, -60, 1, 7);
		next = this.createNewLine();
		lineCounter = 0;
	}
	
	public EnemyLine createNewLine() {
		Random rand = new Random();
		int index = rand.nextInt(5);
		int size = 5 + rand.nextInt(3);
		++lineCounter;
		if(lineCounter >= 3) {
			lineCounter = 2;
		}
		if(index == 0) {
			return new BasicLine(280*3/2, -60, lineCounter, size);
		} else if(index == 1) {
			return new LineClassA(280*3/2, -60, lineCounter, size);
		} else if(index == 2) {
			return new LineClassB(280*3/2, -60, lineCounter, size);
		} else if(index == 3) {
			return new LineClassC(280*3/2, -60, lineCounter, size);
		} else if(index == 4) {
			return new LineClassD(280*3/2, -60, lineCounter, size);
		} else if(index == 5) {
			return new LineClassE(280*3/2, -60, lineCounter, size);
		}
		return null;
	}

	public EnemyLine getCurrent() {
		return current;
	}
	
	public void nextLine() {
		current = next;
		next = this.createNewLine();
	}

	@Override
	public void draw(Graphics2D g) {
		this.current.draw(g);
	}

	@Override
	public void update(double delta) {
		for(int c = 0; c < this.current.getEnemies().size(); c++) {
			if(current.getEnemies().get(c).getPosY() > 600) {
				this.nextLine();
				return;
			}
		}
		if(current.getEnemies().size() == 0) {
			this.nextLine();
		}
		this.current.update(delta);
	}
}