package main.window;
import java.awt.Dimension;

import javax.swing.JFrame;

import main.Game;

public class Window extends JFrame {

	private static final long serialVersionUID = -8255319694373975038L;
	
	private int height, width;
	
	public Window(String title, int width, int height, Game game) {
		super(title);
		
		this.height = height;
		this.width = width;
		
		game.setPreferredSize(new Dimension(width, height));
		game.setMaximumSize(new Dimension(width, height));
		game.setMinimumSize(new Dimension(width, height));
		add(game);
		
		setResizable(false);
		
		pack();
		
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		setVisible(true);
	}
	
}
