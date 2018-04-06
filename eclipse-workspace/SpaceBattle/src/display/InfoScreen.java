package display;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class InfoScreen extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 200, HEIGHT = 600;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage bg;
	
	public InfoScreen() {
		this.setSize(WIDTH, HEIGHT);
		this.setFocusable(true);
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {e.printStackTrace();}
	}
	
	public void draw(BufferStrategy bs) {
		do {
			do {
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				g.drawImage(bg, 0, 0, WIDTH+10, HEIGHT+10, this);
				
				g.dispose();
			} while(bs.contentsRestored());
			bs.show();
		} while(bs.contentsLost());
	}
	
	public void update(double delta) {
		
	}

	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		int frames = 0;
		
		this.createBufferStrategy(3);
		BufferStrategy bs = this.getBufferStrategy();
		
		while(running) {
			long now = System.nanoTime();
			long updates = now - lastTime;
			lastTime = now;
			double delta = updates / (double) OPTIMAL_TIME;
			
			frames++;
			
			if(System.currentTimeMillis()-timer > 1000) {
				timer += 1000;
				frames = 0;
			}
			
			draw(bs);
			update(delta);
			
			try {
				Thread.sleep((lastTime-System.nanoTime()+OPTIMAL_TIME)/1000000);
			} catch(Exception e) {}
		}
	}
}
