// Yul Lee Kim 

package view;

import java.util.ArrayList;
import java.util.List;

import controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class EndScene extends Scene {
	
	// Scene height
	private static int sceneHeight = 600;
	// Scene width
	private static int sceneWidth = 600;

	// Reference to controller
	private GameController controller;
	// Main layout pane
	private static VBox mainPane = new VBox();
	// Label to display ending message
	private Label endLabel;
	
	// Button to restart game
	private Button restartButton;
	// Pane for padding
	private Pane paddingPane;
	
	public EndScene(GameController controller) {
		super(mainPane,sceneWidth,sceneHeight);
		this.controller = controller;
		mainPane.setSpacing(40);
		mainPane.setAlignment(Pos.CENTER);
		createComponents(); // Create components
	}
	
	// set up components in pane
	public void createComponents() {
		// set up pane for animation to happen in
		// set up label to display ending text
		endLabel = new Label();
		endLabel.setFont(Font.font("Helvetica", 50)); //$NON-NLS-1$
		endLabel.setWrapText(true);
		endLabel.setTextAlignment(TextAlignment.CENTER);

		// Set up button to restart game
		restartButton = new Button("Restart"); //$NON-NLS-1$
		restartButton.setOnAction(e->{
			// Return to the start scene 
			controller.loadStartScene();
		});

		paddingPane = new Pane();
		paddingPane.setMinSize(100, 150);
		// add to pane
		mainPane.getChildren().addAll(endLabel, restartButton, paddingPane);
	}
	
	// reset components whenever scene is loaded
	public void resetComponents(boolean didWin){
		// if won, say you won
		if (didWin) {
			endLabel.setText("You won!"); //$NON-NLS-1$
		}
		// else say you didn't solve
		else {
			endLabel.setText("Too bad. You lost..."); 
			GameController.isEasy = false;
			GameController.isHard = false;
		}
	}	
	
}
