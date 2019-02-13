// Yul Lee Kim

package monster;

import java.util.Random;

import entity.Player;
import javafx.scene.image.Image;

public class Mushroom extends Monster{
	
	private int mDamage = 20;
    private int mSpeed = 10;
    
	Random r = new Random();
	
	public static Image image = new Image("/images/mushroom.png");

	public Mushroom(double xOrigin, double yOrigin) {
		
		super(xOrigin, yOrigin, image);
		setXSpeed(1);
		setYSpeed(1);
	}

	@Override
	public void move() {
		
		int rand = r.nextInt(650 - 1) + 1;
		if(((rand) % 2) == 0)
        {
            setXOrigin(getXOrigin() + mSpeed);
            
        } else
        {
            setXOrigin(getXOrigin() - mSpeed);
        }
        
        if(((rand) % 2) == 0)
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

		return 0;
	}

	@Override
	public void interactWithPlayer(Player player) {
		
		player.loseHealth(mDamage);
		System.out.println("Mushroom damaged player's health to " + player.getHealth());
       
        if(player.getHealth() <= 0)
        {
            player.die();
        }
	}

}
