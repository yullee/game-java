// Yul Lee Kim

package monster;

import entity.Player;
import javafx.scene.image.Image;

public class Poison extends Monster{
	
	public static Image image = new Image("/images/poison.png");

	public Poison(double x, double y) {
		
		super(x, y, image);
		setXSpeed(2);
		setYSpeed(2);
	}

	@Override
	public void move() {
		
	}

	@Override
	public int getEntityType() {
		return 0; // TYPE_MONSTER
	}

	@Override
	public void interactWithPlayer(Player player) {

	}
}
