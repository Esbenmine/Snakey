package main.objects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Game;

public class Food extends Object {

	@Override
	public void tick() {
		
	}

	
	@Override
	public void render(GraphicsContext g) {
		g.setFill(Color.CYAN);
		g.fillRect((int) x + 1, (int) y + 1, Game.TILE_SIZE - 2, Game.TILE_SIZE - 2);
	}

	/**
	 * Find random location from rows and columns.
	 * @param rows Rows in the grid
	 * @param columns Columns in the grid
	 */
	public void findLoc(int rows, int columns) {
		x = (int) (Math.random()*rows) * Game.TILE_SIZE;
		y = (int) (Math.random()*columns) * Game.TILE_SIZE;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void seX(float x) {
		this.x = x;
	}

	
}
