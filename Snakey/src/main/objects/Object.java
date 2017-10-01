package main.objects;
import javafx.scene.canvas.GraphicsContext;

public abstract class Object {
	
	protected float x = 0, y = 0;
	
	public abstract void tick();
	
	public abstract void render(GraphicsContext g);
	
}
