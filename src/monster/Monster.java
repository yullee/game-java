// Yul Lee Kim

package monster;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import javafx.scene.image.Image;

// It is still an abstract class because the abstract functions
// have not been implemented.
public abstract class Monster extends Enemy
{

	protected Monster(double xOrigin, double yOrigin, Image image) {
		super(xOrigin, yOrigin, image);
	}

	// Abstract function
	public void interactWithPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	// Override the isMonster function from Entity
	@Override
	public int getEntityType()
	{
		// return Type.TYPE_MONSTER;
		return 0;
	}

}
