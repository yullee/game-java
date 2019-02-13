// Yul Lee Kim

package entity;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.Sprite;

public abstract class Entity extends Pane{
	private int xSpeed;
	private int ySpeed;

	// Test
	protected int mXOrigin;
	protected int mYOrigin;
	protected int mWidth;
	protected int mHeight;
	protected boolean mExists = true;
	public Image mImage;
	private Node view;
    private Point2D velocity = new Point2D(0, 0);
	 protected ImageView imageView;
	    int count = 2;
	    int columns = 12;
	    int offsetX = 0;
	    int offsetY = 0;
	    int width = 30;
	    int height = 35;
	    int score = 0;
	    double x;
	    double y;
		
	    Rectangle removeRect = null;
	    public Sprite animation;
	    
	    public Entity(Node view) {
	        this.view = view;
	    }
	    
	    public Entity(ImageView imageView, double x, double y) {
	        this.imageView = imageView;
			setLayoutX(x);
		      setLayoutY(y);
		      
		      getChildren().addAll(imageView);
	        
	    }
	    
	    public void moveX(int x) {
	        boolean right = x>0?true:false;
	        for(int i = 0; i < Math.abs(x); i++) {
	            if (right) {
	            		this.setTranslateX(this.getTranslateX() + 1);
	            } else {
	            		this.setTranslateX(this.getTranslateX() - 1);
	            }
//	            isBonuseEat();
	        }
	    }
	    public void moveY(int y) {
	        boolean down = y > 0 ? true : false;
	        for (int i = 0; i < Math.abs(y); i++) {
	            if (down) {
	            		this.setTranslateY(this.getTranslateY() + 1);
	            } else {
	            		this.setTranslateY(this.getTranslateY() - 1);
	            }
//	            isBonuseEat();
	        }
	    }
	public enum Type
	{
		TYPE_MONSTER,
		TYPE_ITEM,
		TYPE_PLAYER;
	}

	
	public Entity(double xOrigin, double yOrigin) {
		this.xSpeed = this.getXSpeed();
		this.ySpeed = this.getYSpeed();
		
	}
	

	public Entity(double xOrigin, double yOrigin , Image image) {
		
//		super(image);
		
		this.mImage = image;
		this.xSpeed = this.getXSpeed();
		this.ySpeed = this.getYSpeed();

	}

	
	public Image getmImage() {
		return mImage;
	}
	
	

    private boolean alive = true;

    public void update() {
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public Node getView() {
        return view;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

   
//    public double getRotate() {
//        return view.getRotate();
//    }

//    public void rotateRight() {
//        view.setRotate(view.getRotate() + 5);
//        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
//    }
//
//    public void rotateLeft() {
//        view.setRotate(view.getRotate() - 5);
//        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
//    }

    public boolean isColliding(Entity other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }
 
	// Setter functions
    public void setXOrigin(int xOrigin)
    {
//        super.setX(xOrigin);
        mXOrigin = xOrigin;
    }
    
    public void setYOrigin(int yOrigin)
    {
//    	super.setY(yOrigin);
        mYOrigin = yOrigin;
    }
    
    // Getter functions
    public int getXOrigin() 
    {
        return mXOrigin;
    }
    
    public int getYOrigin() 
    {
        return mYOrigin;
    }
    
	public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public boolean getExists()
	{
		return mExists;
	}

	public void setNotExist()
	{
		mExists = false;
	}

	// All child of Entity are required to implement move() function
	public abstract void move(); // This is a pure virtual function
	public abstract int getEntityType();	
}