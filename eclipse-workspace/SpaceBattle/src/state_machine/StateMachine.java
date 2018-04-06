package state_machine;

import java.awt.Canvas;
import java.awt.Graphics2D;

import adt.List;
import display.GameScreen;
import display.MenuScreen;

public class StateMachine {
	private Canvas canvas;
	private List<SuperStateMachine> states = new List<SuperStateMachine>();
	private byte selectState;
	
	public StateMachine(Canvas canvas) {
		this.canvas = canvas;
		
		SuperStateMachine menu = new MenuScreen(this);
		SuperStateMachine game = new GameScreen(this);
		states.add(menu);
		states.add(game);
	}

	public void draw(Graphics2D g) {
		states.get(selectState).draw(g);
	}

	public void update(double delta) {
		states.get(selectState).update(delta);
	}
	
	public void setState(byte i) {
		for(int r = 0; r < canvas.getKeyListeners().length; r++)
			canvas.removeKeyListener(canvas.getKeyListeners()[r]);
		selectState = i;
		states.get(selectState).init(canvas);
	}
	
	public byte getStates() {
		return selectState;
	}
}
