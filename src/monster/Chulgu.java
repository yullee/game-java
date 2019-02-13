// Yul Lee Kim

package monster;

import java.util.Random;

import entity.Player;
import javafx.scene.image.Image;

public class Chulgu extends Monster{
	
	private int mDamage = 25;
	private int mSpeed = 10;
	public static Image image = new Image("/images/chulgu.png");

	public Chulgu(double x, double y) {
		super(x, y, image);
		setXSpeed(1);
		setYSpeed(1);
	}

	@Override
	public void move() {
		Random r = new Random();
		int mtRand = r.nextInt(10 - 1) + 1;
		
		if (((mtRand) % 2) == 0)
		{
			setXOrigin(getXOrigin() + mSpeed);
		}
		else
		{
			setXOrigin(getXOrigin() - mSpeed);
		}

		if (((mtRand) % 2) == 0)
		{
			setYOrigin(getYOrigin() + mSpeed);
		}
		else
		{
			setYOrigin(getYOrigin() - mSpeed);
		}

	}

	@Override
	public int getEntityType() {
		return 0; // TYPE_MONSTER
	}

	@Override
	public void interactWithPlayer(Player player) {
		
	}

}
