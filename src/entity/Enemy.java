// Yul Lee Kim

package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Enemy extends ImageView{
	private int xSpeed;
	private int ySpeed;

	// Test
	protected int mXOrigin;
	protected int mYOrigin;
	protected int mWidth;
	protected int mHeight;
	protected boolean mExists = true;
	public Image mImage;

	public enum Type
	{
		TYPE_MONSTER,
		TYPE_ITEM,
		TYPE_PLAYER;
	}

	
	public Enemy(double xOrigin, double yOrigin) {
		this.xSpeed = this.getXSpeed();
		this.ySpeed = this.getYSpeed();
		
		super.setX(xOrigin);
		super.setY(yOrigin);
	}
	

	public Enemy(double x, double y , Image image) {
		
		super(image);
		
		this.mImage = image;
		this.xSpeed = this.getXSpeed();
		this.ySpeed = this.getYSpeed();
		
		super.setX(x);
		super.setY(y);
	}

	public Image getmImage() {
		return mImage;
	}
	
	public int getWidth() 
    {
        return (int) mImage.getWidth();
    }
    
    public int getHeight() 
    {
        return (int) mImage.getHeight();
    }
    
	public boolean contains(Enemy other) 
    {
        // Check the four corners of 'other'
        int leftX = other.mXOrigin;
        int topY = other.mYOrigin;
        int rightX = leftX + other.getWidth();
        int bottomY = topY + other.getHeight();
        
        System.out.println("leftX is: " + leftX);
        if (contains(leftX, topY) || contains(leftX, bottomY) ||
            contains(rightX, topY) || contains(rightX, bottomY)) {
            // There is an overlap
            return true;
        }
        // No overlap
        return false;
    }
	
	public boolean contains(double x, double y) 
    {
        int xOrigin = this.getXOrigin();
        int yOrigin = this.getYOrigin();
        int width = this.getWidth();
        int height = this.getHeight();
        
        return ((x >= xOrigin && x <= (xOrigin + width)) &&
                (y >= yOrigin && y <= (yOrigin + height)));
    }
	
	// Setter functions
    public void setXOrigin(int xOrigin)
    {
        mXOrigin = xOrigin;
    }
    
    public void setYOrigin(int yOrigin)
    {
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
