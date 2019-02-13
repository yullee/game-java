// Yul Lee Kim

package view;

import view.Sprite;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import monster.Chulgu;
import monster.Monster;
import monster.Mushroom;
import monster.Poison;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URL;
//import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import controller.GameController;
import entity.Enemy;
import entity.Entity;
import entity.Player;

import frame.Frame;
import item.SpeedItem;
import item.YellowFlower;
import view.Sprite;

public class GameScene extends Scene {

	private HashMap<KeyCode, Boolean> keys = new HashMap<>();
//    public Sprite hardAnimation;

	
	private Player player;
	// Reference to controller
	private GameController controller;
	private Media music;
	private boolean isPlaying = false;
	// Media player to play music clip
	private MediaPlayer musicPlayer;
	
	// Double of current position in music
	private double currentPosition;

	// Button to pause game
	private Button pauseButton;
	// Main layout VBox
	private static VBox mainPane = new VBox();
	
	// List of enemies 
	private List<Enemy> poisonList;
	private List<Enemy> mushroomList;
	private List<Enemy> chulguList;
	private List<Enemy> speedItemList;
	private List<Enemy> yellowFlowerList;
	private HBox hb;
	private Pane pane;
	private Pane menuPane;
	
	private static int mGWWidth = 2400;
	private static int mGWHeight = 2400;
	private StringProperty healthScore;
	private Text checkStatus;
	
	// Buttons to set menu 
	public static Button close;
	public static Button home;
	public static Button resumeButton;
	Random r = new Random(); // Generate a random value

	static Frame mFrame  = new Frame(mGWWidth, mGWHeight);

	int fHeight = mFrame.getFrameHeight();
    int fWidth = mFrame.getFrameWidth();
    
	public GameScene(GameController controller) {
		super(mainPane, mFrame.getFrameWidth(), mFrame.getFrameHeight());

		// Initialize arrayLists
		poisonList = new ArrayList<>(); 
		mushroomList = new ArrayList<>();
		chulguList = new ArrayList<>();
		speedItemList = new ArrayList<>();
		yellowFlowerList = new ArrayList<>();
		this.controller = controller;
		mainPane.setStyle("-fx-background-color: white; -fx-font-family: Helvetica" );
		menuPane = new Pane();
		menuPane.setPrefSize(800, 50);
		
		pane = new Pane();
		pane.setPrefSize(800, 750);

		mainPane.getChildren().addAll(pane, menuPane );
	    mainPane.setOnKeyPressed(event->keys.put(event.getCode(),true));
	    mainPane.setOnKeyReleased(event-> {
	    		keys.put(event.getCode(), false);
	    });
	    
	
	    createPlayer(); // Create a player
	    createEnemies(); // Create enemies 
	    createAnimation(); // Create an animation 
	    doAnimation(); // Do main animation 
		createComponents();	  
	    
		// Biding a text property to display the player's health 
		healthScore = new SimpleStringProperty(Integer.toString(player.getHealth())); 
		checkStatus.textProperty().bind(healthScore);		
	}
	
	// Add a sound effect - Bad enemy
	public void musicPlayer() {
//		System.out.println("Enemy sound!");
		try {
			// Get a file from the URL
			URL audioFile= getClass().getResource("mario_touch.wav");
			music = new Media(audioFile.toString());
			// Add music to media player
			musicPlayer = new MediaPlayer(music);
			musicPlayer.setVolume(1.0);
			musicPlayerBeginning();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Add a sound effect - Good enemy
		public void musicPlayer2() {
//			System.out.println("Enemy sound!");
			try {
				// Get a file from the URL
				URL audioFile= getClass().getResource("mario_jump.wav");
				music = new Media(audioFile.toString());
				// Add music to media player
				musicPlayer = new MediaPlayer(music);
				musicPlayer.setVolume(1.0);
				musicPlayerBeginning();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	// Reset to play music from the beginning
	public void musicPlayerBeginning() {
		if(isPlaying == true) {
			musicPlayerStop();
		}
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
		
	// Stop music 
	public void musicPlayerStop() {
		musicPlayer.pause();
		isPlaying = false;
		musicPlayer.seek(Duration.ZERO);
		currentPosition = musicPlayer.getCurrentTime().toSeconds();
	}

	// Create a player to the window
	public void createPlayer() {
		player = new Player((fWidth / 2) , (fHeight / 2) );
		System.out.println("Hard player is created.");
		pane.getChildren().addAll(player);
	}
	
	// Initialize five enemies/entities
	public void createEnemies() {
		// Since it is a hard version, double the number of enemies
		for(int i = 0; i < 5; i++) {
			Poison p = new Poison(generateRand(), generateRand());
			poisonList.add(p);
			pane.getChildren().add(p);
			
			Mushroom m = new Mushroom(generateRand(), generateRand());
			mushroomList.add(m);
			pane.getChildren().add(m);
			
			SpeedItem s = new SpeedItem(generateRand(), generateRand());
			speedItemList.add(s);
			pane.getChildren().add(s);
			
			Chulgu c = new Chulgu(generateRand(), generateRand());
			chulguList.add(c);
			pane.getChildren().add(c);
			
			YellowFlower y = new YellowFlower(generateRand(), generateRand());
			yellowFlowerList.add(y);
			pane.getChildren().add(y);
		}
	}
	
	// Generate a random integer
	public int generateRand() {
		return r.nextInt(650 - 1) + 1;
	}
	
	// Handle the player and enemies 
	public void createAnimation() {
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
	            	if(GameController.isEasy) {
	                updatePlayer();
	                updateEnemies();
	            }
	       }
		};
		timer.start();	
	}

	// Update the player's position
	public void updatePlayer() {
		// Player to animate 
		mainPane.getScene().setOnKeyPressed(e-> {
//			if(player.getXOrigin() < 0 || player.getYOrigin() < 0 || player.getXOrigin() > pane.getPrefWidth() || player.getYOrigin() > pane.getPrefHeight()) {
			if(e.getCode() == KeyCode.W) {
			//	    	 if (isPressed(KeyCode.UP)) {
				player.animation.play();		
				player.animation.setOffsetY(35);
				player.animation.setOffsetX(143);
				player.moveY(-4);
			} else if(e.getCode() == KeyCode.S) {
				player.animation.play();
				player.animation.setOffsetY(0);	
				player.animation.setOffsetX(146);
				player.moveY(4);
			} else if(e.getCode() == KeyCode.D) {
				player.animation.play();
				player.animation.setOffsetX(200);
				player.animation.setOffsetY(0);
				player.moveX(4);
			} else if(e.getCode() == KeyCode.A) {
				player.animation.play();
				player.animation.setOffsetY(0);
				player.animation.setOffsetX(87);
				player.moveX(-4);
			} else {
				player.animation.stop(); // Else stop the animation
			}
		});
	}
    
	// Continuously generate enemies
	private void updateEnemies() {
		// If the scene is actually started 
		if(GameController.isEasy) { 
			// Less enemies are generated 
			if (Math.random() < 0.05) {
				addChulgu(new Chulgu(Math.random() * pane.getPrefWidth(), Math.random() * pane.getPrefHeight() )); // Randomly displaying chulgu
				addMushroom(new Mushroom( Math.random() * pane.getPrefWidth(), Math.random() * pane.getPrefHeight() ));
				addPoison(new Poison( Math.random() * pane.getPrefWidth(), Math.random() * pane.getPrefHeight() ));
			}
			
			// More items are generated  
			if (Math.random() < 0.03) {
				addSpeedItem(new SpeedItem( Math.random() * pane.getPrefWidth(), Math.random() * pane.getPrefHeight()));
				addFlowerItem(new YellowFlower( Math.random() * pane.getPrefWidth(), Math.random() * pane.getPrefHeight() ));
			}
		}
	}
	 
	// Not implemented yet
	public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

	// Green turtle
	 private void addChulgu(Enemy enemy) {
		 chulguList.add(enemy);
		 pane.getChildren().addAll(enemy);
	}
	 
	 // Red mushroom
	 private void addSpeedItem(Enemy enemy) {
		speedItemList.add(enemy);
		 pane.getChildren().addAll(enemy);
	}
	 
	private void addFlowerItem(Enemy enemy) {
		yellowFlowerList.add(enemy);
		pane.getChildren().addAll(enemy);
	}
	 
	private void addMushroom(Enemy enemy) {
		 mushroomList.add(enemy);
		 pane.getChildren().addAll(enemy);
	}
	
	private void addPoison(Enemy enemy) {
		 poisonList.add(enemy);
		 pane.getChildren().addAll(enemy);
	}
	
	// Set up components in pane
	public void createComponents(){		
		Font f = Font.loadFont(getClass().getResource("/fonts/Super Plumber Brothers.ttf").toExternalForm(),14);
		
		pane.setStyle("-fx-background-image: url(\"images/background.png\");");
		
		menuPane.setStyle("-fx-background-image: url(\"images/menu_bg.jpeg\");");

		hb = new HBox();
		hb.setPrefWidth(800);
		hb.setPrefHeight(50);
		hb.setAlignment(Pos.CENTER); // Align center 
		hb.setStyle("-fx-background: #FFF");
		hb.setPadding(new Insets(10, 50, 50, 50)); // Place a padding
	    hb.setSpacing(10);
	    
		createButtons();
		
		VBox h2 = new VBox();
		Label score = new Label("Player");
		score.getStyleClass().add("score");
		checkStatus = new Text(""); // Set a new text
		checkStatus.setFill(Color.WHITE); // Set a text color to white 
			
		h2.setPadding(new Insets(0, 0, 0, 20));
	
		score.setStyle("-fx-font-size: 30 ; -fx-text-fill: white ; -fx-font-family: \"Super Plumber Brothers\" ");
		checkStatus.setStyle("-fx-font-size: 20 ; -fx-text-fill: white ; -fx-font-family: \"Super Plumber Brothers\" ");
		score.setFont(f);

		h2.getChildren().addAll(score, checkStatus);
		pane.getChildren().addAll(h2);
		menuPane.getChildren().addAll(hb);
		
	}
	
	public void createButtons() {
		home = new Button("Return to Menu"); // Start the game
		close = new Button("Close"); // Close the stage

		// Set up pause and resume buttons
		pauseButton = new Button("Pause"); 
		resumeButton = new Button("Resume");	
		
		// Pause the game
		pauseButton.setOnAction(e-> {
			GameController.isEasy = false;
		});
				
		// Resume the game
		resumeButton.setOnAction(e-> {
			GameController.isEasy = true;  
		});
		
		hb.getChildren().add(home); 
		hb.getChildren().add(pauseButton);
		hb.getChildren().add(resumeButton);
		hb.getChildren().add(close);
	}
	
	public void reset() {
	 	player.setHealth(300);
	 	healthScore = new SimpleStringProperty(Integer.toString(player.getHealth())); 
	 	checkStatus.textProperty().bind(healthScore);
	}
	
	public void badCollision(Entity players, ImageView b) {
	    if (players.getBoundsInParent().intersects(b.getBoundsInLocal())) {
	        b.setY(-100); // Will be changed to actually delete an enemy
	        
	        // Not implemented yet
	        // interactWithPlayer(player);
	        player.setHealth(player.getHealth() - 30);
	        musicPlayer();
	        if(player.getHealth() == 0) { // If the player's health is 0, the game ends 
	        		controller.loadEndScene(false);
	        		GameController.isEasy = false;
	        		reset(); // Reset all components
	        }
//	        System.out.println("Bad enemy detected!");
	        healthScore = new SimpleStringProperty(Integer.toString(player.getHealth())); 
	        checkStatus.textProperty().bind(healthScore);
	        
	    }
	}
	
	public void goodCollision(Entity players, ImageView b) {
	    if (players.getBoundsInParent().intersects(b.getBoundsInLocal())) {
	        b.setY(-100); // Will be changed to actually delete an enemy
	        
	        // Not implemented yet
	        // interactWithPlayer(player);
	        player.setHealth(player.getHealth() + 30);
	        musicPlayer2();
	        if(player.getHealth() == 0) { // If the player's health is 0, the game ends 
	        		controller.loadEndScene(false);
	        		GameController.isEasy = false;
	        		reset(); // Reset all components
	        }
//	        System.out.println("Good enemy detected!");
	        healthScore = new SimpleStringProperty(Integer.toString(player.getHealth())); 
	        checkStatus.textProperty().bind(healthScore);
	        
	    }
	}
	
	public void doAnimation() {
		
		AnimationTimer animation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				
				if(GameController.isEasy) {
				// Main game loop animation -- to move characters
				for(Enemy c: poisonList) {
					moveCharacter(c);
					badCollision(player, c); // detect a collision
				}
				
				for(Enemy c: mushroomList) {
					moveCharacters(c);
					badCollision(player, c); // detect a collision
				}
				
				for(Enemy c: chulguList) {
					moveCharacters(c);
					badCollision(player, c); // detect a collision	
				}
				
				for(Enemy c: speedItemList) {
					moveCharacters(c);
					goodCollision(player, c); // detect a collision
				}
				
				for(Enemy c: yellowFlowerList) {
					moveCharacters(c);
					goodCollision(player, c); // detect a collision	
				}
				}
			}
			
			private void moveCharacters(Enemy c) {
				// Need to change - the enemies to disappear if out of the boundary
				if(c.getX() < 0 || c.getY() < 0 || c.getX() > pane.getPrefWidth() || c.getY() > pane.getPrefHeight() -50) {
					c.setY(-100);
				}
				c.setX(c.getX() - c.getXSpeed());
				c.setY(c.getY() - c.getYSpeed());
			}
			
			// Move entities
			private void moveCharacter(Enemy c) {
				if(c.getX() < 0 || c.getY() < 0 || c.getX() > pane.getPrefWidth() || c.getY() > pane.getPrefHeight() - 50) {
					c.setY(-100);
				}
				//simple version for now, just move based on x.
				//can add in collision detection later.
				c.setX(c.getX() + c.getXSpeed());
				c.setY(c.getY() + c.getYSpeed());
			}
			
		};
	
		animation.start();
	}
}