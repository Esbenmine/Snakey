package main.objects;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.Game;
import main.listener.Listener;
import main.window.Window;

public class Snake extends Object {
	
	private ArrayList<Point> tail = new ArrayList<>();
	
	private Food food = new Food();
	
	private int velX = 1, velY = 1;
	private int dirX = 1, dirY = 0;
	private int lastDirX = dirX;
	private int lastDirY = dirY;
	
	private boolean lost = false;
	
	private boolean paused = false;
	
	private int movesPerSecond = 10;
		
	/**
	 * Counts amount of ticks since last move.
	 */
	private float counter;
	private float counterIncrement = 1/((float) Game.TICKS_PER_SECOND/movesPerSecond);
	
	private Listener listener;
	
	public Snake() {
		food.findLoc(Window.WIDTH / Game.TILE_SIZE, Window.HEIGHT / Game.TILE_SIZE);
		listener = Game.listener;
	}
	
	@Override
	public void tick() {
		if (paused) {
			if (listener.enter.isDown()) {
				reset();
			}
			return;
		}
		
		if (listener.up.isDown()) {
			if (!(lastDirX == 0 && lastDirY == 1)) {
				setDir(0, -1);
			}
		} else if (listener.down.isDown()) {
			if (!(lastDirX == 0 && lastDirY == -1)) {
				setDir(0, 1);
			}
		} else if (listener.right.isDown()) {
			if (!(lastDirX == -1 && lastDirY == 0)) {
				setDir(1, 0);
			}
		} else if (listener.left.isDown()) {
			if (!(lastDirX == 1 && lastDirY == 0)) {
				setDir(-1, 0);
			}
		}
		
		counter+=counterIncrement;
		if(counter >= 1) {
			counter -= 1;
			eatFood();
			move();
			lastDirX = dirX;
			lastDirY = dirY;
			checkDeath();
		}
		
	}
	
	private void eatFood() {
		if (x == food.x && y == food.y) {
			tail.add(new Point((int) x, (int) y));
			food.findLoc(Window.WIDTH / Game.TILE_SIZE, Window.HEIGHT / Game.TILE_SIZE);
		}
	}
	
	@Override
	public void render(GraphicsContext g) {
		food.render(g);
		g.setFill(Color.WHITE);
		
		//g.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
		for (Point p : tail) {
			g.setFill(Color.WHITE);
			g.fillRect(p.x + 1, p.y + 1, Game.TILE_SIZE - 2, Game.TILE_SIZE - 2);
		}
		g.setFill(Color.WHITE);
		g.fillRect((int) x + 1, (int) y + 1, Game.TILE_SIZE - 2, Game.TILE_SIZE - 2);
		
		if(lost) {
			String message = "You've lost!";
			g.setFill(Color.WHITE);
			Font font = Font.font("Verdana", FontWeight.BOLD, 52); 
			g.setFont(font);
			g.setFontSmoothingType(FontSmoothingType.GRAY);
			
			Text text = new Text(message);
			text.setFont(font);
			
			g.fillText(message, Window.WIDTH/2 - text.getLayoutBounds().getWidth()/2, Window.HEIGHT/2 - text.getLayoutBounds().getHeight()/2);
		}
	}

	private void move() {
		if (!tail.isEmpty()) {
			tail.add(new Point((int) x, (int) y));
			tail.remove(0);
		}
		
		x += velX*dirX*Game.TILE_SIZE;
		y += velY*dirY*Game.TILE_SIZE;		
	}
	
	/**
	 * Resets states of the snake.
	 */
	private void reset() {
		tail = new ArrayList<>();
		x = 0;
		y = 0;
		lost = false;
		paused = false;
		setDir(1, 0);
		food.findLoc(Window.WIDTH / Game.TILE_SIZE, Window.HEIGHT / Game.TILE_SIZE);
	}
	
	private void checkDeath() {
		if (x >= Window.WIDTH || x < 0 || y >= Window.HEIGHT || y < 0) {
			paused = true;
			lost = true;
			return;
		}
		for (Point p : tail) {
			if (x == p.x && y == p.y) {
				paused = true;
				lost = true;
			}
		}
	}
	
	private void setDir(int dirX, int dirY) {
		this.dirX = dirX;
		this.dirY = dirY;
	}
	
}
