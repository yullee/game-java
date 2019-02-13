// Yul Lee Kim

package frame;
import entity.Entity;

// Frame is the visible portion of the game
public class Frame
{
	private int mXOrigin;
	private int mYOrigin;
	private int mFrameWidth;
	private int mFrameHeight;
	private int mWindowWidth;
	private int mWindowHeight;
	
	public Frame(int windowWidth)
	{
		this(windowWidth, 0);
	}
	
	public Frame()
	{
		this(0, 0);
	}

	public Frame(int windowWidth, int windowHeight)
	{
		this.mWindowWidth = windowWidth;
		this.mWindowHeight = windowHeight;
		setFrameDimension();
	}

	// Checks if the entity is within frame
	public boolean isInFrame(Entity entity)
	{
		int yOrigin = entity.getYOrigin();
		int xOrigin = entity.getXOrigin();

		return (xOrigin >= mXOrigin) && (xOrigin <= (mXOrigin + mFrameWidth)) && (yOrigin >= mYOrigin) && (yOrigin <= (mYOrigin + mFrameHeight));
	}


	public int getXOrigin()
	{
		return mXOrigin;
	}

	public int getYOrigin()
	{
		return mYOrigin;
	}

	public int getFrameHeight()
	{
		return mFrameHeight;
	}


	public int getFrameWidth()
	{
		return mFrameWidth;
	}

	public void setXOrigin(int xOrigin)
	{
		mXOrigin = xOrigin;
	}

	public void setYOrigin(int yOrigin)
	{
		mYOrigin = yOrigin;
	}

	private void setFrameDimension()
	{
		mFrameWidth = mWindowWidth / 3;
		mFrameHeight = mWindowHeight / 3;
		mXOrigin = mFrameWidth;
		mYOrigin = mFrameHeight;
	}
}