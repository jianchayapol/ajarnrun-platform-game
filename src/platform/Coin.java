package platform;

import javafx.scene.image.Image;
import platform.base.Collectable;

/**
 * Coin class is a class that tells the player that player can walk through this
 * class' object and player's coin will increase. This class also implements
 * Collectable as a Marker interface
 * 
 * @author Mos
 *
 */
public class Coin extends SpecialPlatform implements Collectable {
	/**
	 * The constructor pass first three parameter into super constructor, and the
	 * last two parameters will be used to setTranslate.
	 * 
	 * @param image      Block's image
	 * @param fitWidth
	 * @param fitHeight
	 * @param translateX
	 * @param translateY
	 */
	public Coin(Image image, int fitWidth, int fitHeight, int translateX, int translateY) {
		super(image, fitWidth, fitHeight);
		setTranslateX(translateX);
		setTranslateY(translateY);
	}
}