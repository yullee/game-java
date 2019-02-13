// Yul Lee Kim

package item;
import entity.Player;
import javafx.scene.image.Image;

// SpeedItem is now defined as a child of Item
public class YellowFlower extends Item
{
	public static Image image = new Image("/images/yellowFlower.png");

	// Use an initialization list because no default constructor for the base class
	// Call the parameterized constructor of Entity
	public YellowFlower(double xOrigin, double yOrigin)
	{
		super(xOrigin, yOrigin, image);
		setXSpeed(1);
		setYSpeed(1);
	}

	// Override the interactWithPlayer function from Entity
	@Override
	public void interactWithPlayer(Player player)
	{
		int pSpeed = player.getSpeed();
		player.setSpeed(pSpeed + 5);
		System.out.print("Increased players speed to ");
		System.out.print(player.getSpeed());
		System.out.print("\n");
		setNotExist();
	}
}
