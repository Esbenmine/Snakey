package main;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.listener.Listener;
import main.objects.Snake;

public class Game extends Canvas {

	private boolean running;
		
	private Snake snake;
	
	public static Listener listener;
	
	private double scale;
		
	public static final int TILE_SIZE = 20;
	
	private GraphicsContext g;
	
	public static final int TICKS_PER_SECOND = 20;
	
	private AnimationTimer gameLoop = new AnimationTimer() {
		
		private static final double NS_PER_TICK = 1000000000d/TICKS_PER_SECOND;
		
		private long lastTime = System.nanoTime();
		
		private double delta = 0d;
		
		private long lastTimer = System.currentTimeMillis();
		
		private int fpsCount = 0;
		
		@Override
		public void start() {
			init();
			super.start();
		}
		
		@Override
		public void handle(long now) {
			delta += (now-lastTime) / NS_PER_TICK;
			lastTime = now;
			
			while (delta >= 1) {
				tick();
				render();
				fpsCount++;
				delta -= 1;
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("FPS: " + fpsCount);
				
				fpsCount = 0;
			}
			
		}
		
		
		
	};
	
	public Game(int width, int height, double scale) {
		super(width * scale, height * scale);
		this.scale = scale;		
	}
	
	
	public void init() {
		g = getGraphicsContext2D();
		g.scale(scale, scale);
		
		listener = new Listener();		
		
		setOnKeyPressed(listener);
		setOnKeyReleased(listener);
		
		requestFocus();
		
		snake = new Snake();
		
	}
	
	public void tick() {
		snake.tick();
	}
	
	public void render() {
		
		g.clearRect(0, 0, getWidth(), getHeight());
		
		drawBackground(g);
		
		snake.render(g);		
		
		
	}
	
	private void drawBackground(GraphicsContext g) {
		g.setFill(Color.rgb(51, 51, 51));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	/**
	 * Start the game thread.
	 */
	public void start() {
		if (running) return;
		
		gameLoop.start();
		
		running = true;
	}
	
	/**
	 * Stop the game thread.
	 */
	public void stop() {
		if (!running) return;
		running = false;
		gameLoop.stop();
	}
	
}
