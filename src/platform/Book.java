
package platform;

import javafx.scene.image.Image;
import platform.base.Collectable;
/**
 * Book class is a class that tells the player that player can walk through this class' object and you need to collect all of the books to win a level.
 * This class also implements Collectable as a Marker interface
 * @author Mos
 *
 */
public class Book extends SpecialPlatform implements Collectable {
	/**
	 * The constructor pass first three parameter into super constructor, and the last two parameters will be used to setTranslate.
	 * @param image Block's image
	 * @param fitWidth Used to set block's fitWidth
	 * @param fitHeight Used to set block's fitHeight
	 * @param translateX Used to set blocks positionX
	 * @param translateY Used to set blocks positionY
	 */
	public Book(Image image, int fitWidth, int fitHeight, int translateX, int translateY) {
		super(image, fitWidth, fitHeight);
		setTranslateX(translateX);
		setTranslateY(translateY);
	}
}

