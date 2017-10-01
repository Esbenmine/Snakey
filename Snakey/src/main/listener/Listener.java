package main.listener;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Listener implements EventHandler<KeyEvent> {
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key enter = new Key();
	
	public void toggleKeys(KeyCode keyCode, boolean down) {
		switch (keyCode) {
		case UP:
			up.toggle(down);
			break;
		case DOWN:
			this.down.toggle(down);
			break;
		case RIGHT:
			right.toggle(down);
			break;
		case LEFT:
			left.toggle(down);
			break;
		case ENTER:
			enter.toggle(down);
			break;
		default:
			break;
		}
	}
	
	
	public class Key {
		private boolean isDown = false;
		
		public Key() {
			
		}
		
		public void toggle(boolean down) {
			isDown = down;
		}
		
		public boolean isDown() {
			return isDown;
		}
		
	}


	@Override
	public void handle(KeyEvent event) {
		
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			toggleKeys(event.getCode(), true);
		} else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
			toggleKeys(event.getCode(), false);
		}
	}

}
