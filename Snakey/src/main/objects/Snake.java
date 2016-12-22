package main.objects;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import main.Game;
import main.listener.Listener;

public class Snake extends Object {
	
	private ArrayList<Point> tail = new ArrayList<>();
	
	private Food food = new Food();
	
	private int velX = 1, velY = 1;
	private int dirX = 1, dirY = 0;
	
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
		food.findLoc(Game.WIDTH / Game.TILE_SIZE, Game.HEIGHT / Game.TILE_SIZE);
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
			setDir(0, -1);
		} else if (listener.down.isDown()) {
			setDir(0, 1);
		} else if (listener.right.isDown()) {
			setDir(1, 0);
		} else if (listener.left.isDown()) {
			setDir(-1, 0);
		}
		
		counter+=counterIncrement;
		if(counter >= 1) {
			counter -= 1;
			eatFood();
			move();
			checkDeath();
		}
		
	}
	
	private void eatFood() {
		if (x == food.x && y == food.y) {
			tail.add(new Point((int) x, (int) y));
			food.findLoc(Game.WIDTH / Game.TILE_SIZE, Game.HEIGHT / Game.TILE_SIZE);
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		food.render(g);
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
		for (Point p : tail) {
			g.setColor(Color.WHITE);
			g.fillRect(p.x + 1, p.y + 1, Game.TILE_SIZE - 2, Game.TILE_SIZE - 2);
		}
		g.setColor(Color.WHITE);
		g.fillRect((int) x + 1, (int) y + 1, Game.TILE_SIZE - 2, Game.TILE_SIZE - 2);
		
		if(lost) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", Font.BOLD, 52));
			FontMetrics fm = g.getFontMetrics();
			String message = "You've lost!";
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			g.drawString(message, Game.WIDTH/2 - fm.stringWidth(message)/2, Game.HEIGHT/2 - fm.getHeight()/2);
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
		food.findLoc(Game.WIDTH / Game.TILE_SIZE, Game.HEIGHT / Game.TILE_SIZE);
	}
	
	private void checkDeath() {
		if (x >= Game.WIDTH || x < 0 || y >= Game.HEIGHT || y < 0) {
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
