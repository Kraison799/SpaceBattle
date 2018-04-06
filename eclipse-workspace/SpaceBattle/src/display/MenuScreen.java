package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import state_machine.StateMachine;
import state_machine.SuperStateMachine;

public class MenuScreen extends SuperStateMachine implements KeyListener {
	private Font titleFont = new Font("Arial", Font.PLAIN, 64);
	private Font startFont = new Font("Arial", Font.PLAIN, 32);
	private String title = "Space Battle";
	private String start = "Press Enter";

	public MenuScreen(StateMachine stateMachine) {
		super(stateMachine);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(titleFont);
		int titleWidth = g.getFontMetrics().stringWidth(title);
		g.setColor(Color.yellow);
		g.drawString(title, (280*3/2)-(titleWidth/2)+1, 350+1);
		g.setColor(Color.blue);
		g.drawString(title, (280*3/2)-(titleWidth/2), 350);
		
		g.setFont(startFont);
		int startWidth = g.getFontMetrics().stringWidth(title);
		g.setColor(Color.white);
		g.drawString(start, (280*3/2)-(startWidth/2), 500);
	}

	@Override
	public void update(double delta) {
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ENTER) {
			this.getStateMachine().setState((byte) 1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
