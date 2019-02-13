// Yul Lee Kim

// Use WASD key to control the player 

package view;

import controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private GameController controller;

	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create controller object which also sets up scenes
		controller = new GameController(primaryStage);
		
		// Set window title
		primaryStage.setTitle("SUPER MARIO");
		
		// Show window
		primaryStage.show();
	}
}
