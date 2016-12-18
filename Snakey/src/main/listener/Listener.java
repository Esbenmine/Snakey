package main.listener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key enter = new Key();
	
	@Override
	public void keyPressed(KeyEvent e) {
		toogleKeys(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toogleKeys(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void toogleKeys(int keyCode, boolean down) {
		if (keyCode == KeyEvent.VK_UP) {
			up.toogle(down);
		} else if (keyCode == KeyEvent.VK_DOWN) {
			this.down.toogle(down);
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			right.toogle(down);
		} else if (keyCode == KeyEvent.VK_LEFT) {
			left.toogle(down);
		} else if (keyCode == KeyEvent.VK_ENTER) {
			enter.toogle(down);
		}
	}
	
	
	public class Key {
		private boolean isDown = false;
		
		public Key() {
			
		}
		
		public void toogle(boolean down) {
			isDown = down;
		}
		
		public boolean isDown() {
			return isDown;
		}
		
	}

}
