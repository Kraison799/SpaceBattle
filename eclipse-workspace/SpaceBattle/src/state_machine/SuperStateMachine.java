package state_machine;

import java.awt.Canvas;
import java.awt.Graphics2D;

public interface SuperStateMachine {
	public void draw(Graphics2D g);
	public void update(double delta);
	public void init(Canvas canvas);
}
