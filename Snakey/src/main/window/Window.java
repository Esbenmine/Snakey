package main.window;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Game;
import main.listener.Listener;

public class Window extends Application {
	
	private String title; 
	
	private Game game;
	
	public static final int WIDTH = 400, HEIGHT = 400;
	
	private double scale = 1.5d;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(title);
		
		Group root = new Group();
		
		game = new Game(WIDTH, HEIGHT, scale);
		
		root.getChildren().add(game);
		
		Scene scene = new Scene(root, WIDTH * scale, HEIGHT * scale);
		Listener ls = new Listener();
		primaryStage.sizeToScene();
		
		scene.setOnKeyPressed(ls);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		
		primaryStage.centerOnScreen();
		
		primaryStage.show();
		game.start();
	}
	
	@Override
	public void stop() throws Exception {
		game.stop();
		super.stop();
	}
	
}
