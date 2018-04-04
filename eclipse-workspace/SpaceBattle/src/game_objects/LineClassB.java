package game_objects;

import java.awt.Graphics2D;
import java.util.Random;

import adt.List;

public class LineClassB extends EnemyLine implements Drawable {
	private boolean change;
	private int bossIndex;
	public LineClassB(int posX, int posY, int speed, int size) {
		super(posX, posY, speed, size);
		this.change = false;
		Random rand = new Random();
		int randBoss = rand.nextInt(this.getEnemies().size()-1);
		this.getEnemies().get(randBoss).setBoss();
		this.bossIndex = randBoss;
		this.setLineClass("LineClassB");
	}
	
	@ Override
	public void draw(Graphics2D g) {
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).draw(g);
		}
	}
	
	@ Override
	public void update(double delta) {
		for(int c = 0; c < this.getEnemies().size(); c++) {
			this.getEnemies().get(c).setPosY(this.getEnemies().get(c).getPosY()+this.getSpeed());
			this.getEnemies().get(c).update(delta);
		}
		if(this.getTimer().timerEvent(1000)) {
			// If the line has two or more enemies, two of them are chosen to shoot. If there is only one enemy, that enemy will shoot.
			if(this.getEnemies().size() > 1) {
				Random rand = new Random();
				int shooter_1 = rand.nextInt(this.getEnemies().size()-1);
				int shooter_2 = rand.nextInt(this.getEnemies().size()-1);
				this.getEnemies().get(shooter_1).shoot();
				this.getEnemies().get(shooter_2).shoot();
			} else if(this.getTimer().timerEvent(1000) && this.getEnemies().size() == 1) {
				Random rand = new Random();
				int shooter_1 = rand.nextInt(this.getEnemies().size()-1);
				this.getEnemies().get(shooter_1).shoot();
			}
			// AQUI ESTA EL ERROR
			if(change) {
				Random rand = new Random();
				int newPos = rand.nextInt(this.getEnemies().size()-1);
//				System.out.println(this.getEnemies().size());
//				System.out.println(newPos);
//				System.out.println(bossIndex);
//				while(newPos < 0 && newPos >= this.getEnemies().size()) {
//					newPos = rand.nextInt(this.getEnemies().size()-1);
//				}
//				List<Enemy> newList = new List<Enemy>();
//				for(int c = 0; c < this.getEnemies().size(); c++) {
//					if(!this.getEnemies().get(c).isBoss() && c != newPos) {
//						newList.add(this.getEnemies().get(c));
//					} else if(!this.getEnemies().get(c).isBoss() && c == newPos) {
//						newList.add(this.getEnemies().get(bossIndex));
//						bossIndex = newPos;
//						newList.add(this.getEnemies().get(c));
//					} else if(this.getEnemies().get(c).isBoss() && c == newPos) {
//						// The boss has the same position
//						newList = this.getEnemies();
//						break;
//					} else if(this.getEnemies().get(c).isBoss() && c != newPos) {}
//				}
//				this.setEnemies(newList);
				this.getEnemies().swap(bossIndex, newPos);
				bossIndex = newPos;
				change = false;
			} else {
				change = true;
			}
			// HASTA AQUI PUEDE ESTAR EL PROBLEMA
		}
	}
}
