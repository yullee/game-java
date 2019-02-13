// Yul Lee Kim
// Reference from the class files 

package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

//import mvc_model.MusicPlayer.AudioTimer;
import view.EndScene;
//import model.ThemeColor;
//import view.EndScene;
import view.GameScene;
import view.HardGameScene;
import view.StartScene;

public class GameController {

	// Reference to window 
	private Stage primaryStage;
	// Reference to start scene
	private StartScene startScene;
	// Reference to game scene
	private GameScene gameScene;
	// Reference to game scene
	private HardGameScene hardGameScene;
	// Reference to end scene
	private EndScene endScene;
	
	private Media music;
	// Check if the music is playing or not 
	private boolean isPlaying = false;
	// Media player to play music clip
	private MediaPlayer musicPlayer;
	// Double of current position in music
	private double currentPosition;

	// Check if the game is actually being selected
	public static boolean isEasy = false;
	public static boolean isHard = false;
	
	public GameController(Stage primaryStage) {
		this.primaryStage = primaryStage;
		startScene = new StartScene(this);
		gameScene = new GameScene(this);
		hardGameScene = new HardGameScene(this);
		endScene = new EndScene(this); 
		loadStartScene(); // Call loadStartScene()
	}

	// Get reference to start scene
	public void setStartScene(StartScene startScene) {
		this.startScene = startScene;
		this.startScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
	}

	// Get reference to game scene
	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
	}
	
	// Get reference to hard game scene
	public void setHardGameScene(HardGameScene hardGameScene) {
		this.hardGameScene = hardGameScene;
	}

	// Get reference to end scene
	public void setEndScene(EndScene endScene) {
		this.endScene = endScene;
	}

	// Show start scene in window
	public void loadStartScene() {	
		startScene.resetComponents();
		primaryStage.setScene(startScene);	
		musicPlayer(); // Start the music
	}

	// Show game scene in window
	public void loadGameScene() {
		gameScene.reset();

		primaryStage.setScene(gameScene);
		
		musicPlayerStop();
		musicPlayer2();
		
		GameScene.home.setOnAction(e-> {
			loadStartScene();
			isEasy = false;
		});
		// Close button is on action
		GameScene.close.setOnAction(e ->{
			this.primaryStage.close();
		});					
		
	}
	
	// Show game scene in window
	public void loadHardGameScene() {
	
		hardGameScene.reset();
		primaryStage.setScene(hardGameScene);
		musicPlayerStop();
		musicPlayerSand();
		
		HardGameScene.home.setOnAction(e-> {
			loadStartScene();
			isHard = false;
		});
		// Close button is on action
		HardGameScene.close.setOnAction(e ->{
			this.primaryStage.close();
		});	
	}

	// Show end scene in window
	public void loadEndScene(boolean didWin) {
		
		endScene.resetComponents(didWin);
		primaryStage.setScene(endScene);
		musicPlayerStop();
		musicPlayer3();
	}
	public void musicPlayer() {
		// If the music is already playing, stop the music
		if(isPlaying == true) {
			musicPlayerStop();
		}
		System.out.println("Beginning music start!");
		try {
			// Get file from URL
			URL audioFile= getClass().getResource("/audio/marioTheme.mp3");
			music = new Media(audioFile.toString());
			// Add music to media player
			musicPlayer = new MediaPlayer(music);
			musicPlayerBeginning();
			musicPlayer.setCycleCount(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void musicPlayer2() {
		// If the music is already playing, stop the music
		if(isPlaying == true) {
			musicPlayerStop();
		}
		System.out.println("Second music start!");
		
		try {
			// Get file from URL
			URL audioFile= getClass().getResource("/audio/title.mp3");
			music = new Media(audioFile.toString());
			// add music to media player
			musicPlayer = new MediaPlayer(music);
			musicPlayer.setVolume(0.5);
			musicPlayerBeginning();
			musicPlayer.setCycleCount(10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void musicPlayerSand() {
		// If the music is already playing, stop the music
		if(isPlaying == true) {
			musicPlayerStop();
		}
		System.out.println("Sand music start!");
		
		try {
			// Get file from URL
			URL audioFile= getClass().getResource("/audio/sand_song.mp3");
			music = new Media(audioFile.toString());
			// add music to media player
			musicPlayer = new MediaPlayer(music);
			musicPlayer.setVolume(0.5);
			musicPlayerBeginning();
			musicPlayer.setCycleCount(10); // Set music cycle to 10
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void musicPlayer3() {
		// If the music is already playing, stop the music
		if(isPlaying == true) {
			musicPlayerStop();
		}
		System.out.println("Ending music start!");
		try {
			// Get file from URL
			URL audioFile= getClass().getResource("/audio/gameover.mp3");
			music = new Media(audioFile.toString());
			// add music to media player
			musicPlayer = new MediaPlayer(music);
			musicPlayerBeginning();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Reset to play music from the beginning
	public void musicPlayerBeginning() {
		musicPlayer.seek(Duration.ZERO);
		currentPosition = 0;
		musicPlayerPlay();
	}
	
	// Play music from current position
	public void musicPlayerPlay() {
		musicPlayer.seek(Duration.seconds(currentPosition));
		musicPlayer.play();
		isPlaying = true;
	}
	
	// Stop the music 
	public void musicPlayerStop(){
		musicPlayer.pause();
		isPlaying = false;
		musicPlayer.seek(Duration.ZERO);
		currentPosition = musicPlayer.getCurrentTime().toSeconds();
	}
}