package state_machine;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;

import display.GameScreen;

public class StateMachine {
	private Canvas canvas;
	private ArrayList<SuperStateMachine> states = new ArrayList<SuperStateMachine>();
	private byte selectState;
	
	public StateMachine(Canvas canvas) {
		this.canvas = canvas;
		
		SuperStateMachine game = new GameScreen();
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
