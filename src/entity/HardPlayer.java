// Yul Lee Kim

package entity;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import view.Sprite;

public class HardPlayer extends Entity{
	private Node view;
	private Point2D velocity = new Point2D(0, 0);
	
	// Initialize variables
	private int mSpeed = 20;
	private int mHealth = 300;

	static Image image = new Image("images/super_mario.gif");
	static ImageView imageView = new ImageView(image);

	public HardPlayer(int x, int y) {
		super(imageView, x, y);
		animation = new Sprite(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
	}

	@Override
	public void move() {}

	@Override
	public int getEntityType() {
		return 2; // TYPE_PLAYER
	}
	
	public void update() {
		view.setTranslateX(view.getTranslateX() + velocity.getX());
		view.setTranslateY(view.getTranslateX() + velocity.getY());
	}

	public void up()
    {
        setYOrigin(getYOrigin() - mSpeed);
    }
    
    public void down()
    {
        setYOrigin(getYOrigin() + mSpeed);
    }
    
    public void left()
    {
        setXOrigin(getXOrigin() - mSpeed);
    }
    
    public void right()
    {
        setXOrigin(getXOrigin() + mSpeed);
    		
    }
    
    public void setSpeed(int speed)
    {
        mSpeed = speed;
    }
 
    public void die()
    {
        setNotExist();
    }
    
    public int getSpeed()
    {
        return mSpeed;
    }
    
    public int getHealth()
    {
        return mHealth;
    }
    public void setHealth(int health)
    {
        mHealth = health;
    }
    
    // Increase a health point
    public void gainHealth(int potion)
    {
        mHealth += potion;
    }
    
    public void loseHealth(int damage)
    {
        mHealth -= damage;
    }
}
