// Yul Lee Kim

package view;

import controller.GameController;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class StartScene extends Scene {
	
	// scene height
	private static int sceneHeight = 600;
	// scene width
	private static int sceneWidth = 800;
	
	// reference to controller
	private GameController controller;
	// main layout pane
	private static VBox mainPane = new VBox();
	// label for start screen
	private Label startLabel;
	// HBox to hold components to choose number of lights
	private HBox numberChoice;
	private HBox numberChoice2;
	// label to direct user to choose number
	private Label chooseNumberLabel;
	// combo box to choose number of lights to appear on next screen

	// button to start game
	public static Button oceanGameButton;
	public static Button sandGameButton;
//	private Button scoreButton;
	private TextField name;
	private ImageView characterBg;
	public StartScene(GameController controller) {
		super(mainPane, sceneWidth, sceneHeight);
	
		this.controller = controller;
		mainPane.setSpacing(40);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setStyle("-fx-background-color: white; -fx-font-family: Helvetica" );
		createComponents();
		
	}
	
	// set up components in pane
	public void createComponents(){
		// Set up a title label
		startLabel = new Label("Super Mario Odyssey"); 
		startLabel.setFont(Font.font("Helvetica", 60)); 
		startLabel.setWrapText(true);
		startLabel.setTextAlignment(TextAlignment.CENTER);

		// Set up the player's info 
		setUpChoiceMechanism();
		Label info = new Label();
		info.setText("\nInstruction: Use W, A, S or D key to move the player.\nOcean: Easy Level\nSand: Hard Level\n");
		info.setFont(Font.font("Helvetica", 20)); 
		info.setWrapText(true);
		info.setTextAlignment(TextAlignment.CENTER);
		
		numberChoice2 = new HBox(); // Set up HBox
		// Set up button to transition to next part of game
		oceanGameButton = new Button("Ocean Stage");
		sandGameButton = new Button("Sand Stage");
//		scoreButton = new Button("View Scores");
		numberChoice2.getChildren().addAll(info);
		// The start button is disabled until the user puts an input 
		oceanGameButton.disableProperty().bind(Bindings.isEmpty(name.textProperty()));
		sandGameButton.disableProperty().bind(Bindings.isEmpty(name.textProperty()));
		
		oceanGameButton.setOnAction(e->{
			GameController.isEasy = true;
			System.out.println("After the button is clicked: " + GameController.isEasy);
			FadeTransition ft = new FadeTransition(Duration.seconds(1.5), oceanGameButton);
            ft.setToValue(0.5);
            // Call load game scene function 
            
			controller.loadGameScene();
			
		});

		sandGameButton.setOnAction(e->{
			GameController.isHard = true;
			FadeTransition ft = new FadeTransition(Duration.seconds(1.5), sandGameButton);
            ft.setToValue(0.5);
         // Call load hard game scene function 
            
			controller.loadHardGameScene();
			
		});

		numberChoice2.setSpacing(10);
		numberChoice2.setAlignment(Pos.CENTER);
		numberChoice2.getChildren().addAll(oceanGameButton, sandGameButton);
		characterBg = new ImageView("/images/mario_bg.png"); // Set a character background
		characterBg.setFitWidth(550);
		characterBg.setFitHeight(250);
		// add components to main pane
		mainPane.getChildren().addAll(startLabel, numberChoice, numberChoice2, characterBg);
		
	}
	
	// set up part of GUI to choose number of lights
	public void setUpChoiceMechanism(){
		numberChoice = new HBox(); // Set up HBox
		chooseNumberLabel = new Label("Enter a player: "); // Set up a player name label
		name = new TextField();
		// format HBox for these components
		
		numberChoice.setSpacing(10);
		numberChoice.setAlignment(Pos.CENTER);
		numberChoice.getChildren().addAll(chooseNumberLabel, name);
	}
	
	// reset components whenever scene is loaded
	public void resetComponents(){
		name.setText("");
	}	
}
