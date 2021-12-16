package platform;

import javafx.scene.image.Image;
import platform.base.Damagable;
/**
 * HurtPlatform is a class to a special block that can hurt player from a normal block.
 * This class also implement Damagable as a Marker interface.
 * @author Mos
 *
 */
public class HurtPlatform extends SpecialPlatform implements Damagable {
	/**
	 * The constructor pass first three parameter into super constructor, and the last two parameters will be used to setTranslate.
	 * @param image Block's image
	 * @param fitWidth
	 * @param fitHeight
	 * @param translateX
	 * @param translateY
	 */
	public HurtPlatform(Image image, int fitWidth, int fitHeight, int translateX, int translateY) {
		super(image, fitWidth, fitHeight);
		setTranslateX(translateX);
		setTranslateY(translateY);
	}
}
