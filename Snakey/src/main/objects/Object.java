package main.objects;
import java.awt.Graphics2D;

public abstract class Object {
	
	protected float x = 0, y = 0;
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
}
