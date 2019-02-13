// Yul Lee Kim

package item;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// It is still an abstract class because the abstract functions have not been implemented.
public abstract class Item extends Enemy
{
	// Abstract function
	public abstract void interactWithPlayer(Player player);

	// Use an initialization list because no default constructor for the base class
	// Call the parameterized constructor of Entity
//	protected Item(ImageView imageView, double x, double y)
//	{
//		super(imageView, x, y);
//	}
	
	protected Item(double xOrigin, double yOrigin, Image image)
	{
		super(xOrigin, yOrigin, image);
	}

	// Override the isItem function from Entity
	@Override
	public int getEntityType()
	{
		return 1;
	}

	// Override the move function, but nothing happens as the item is immobile
	@Override
	public void move()
	{
	}
	
}