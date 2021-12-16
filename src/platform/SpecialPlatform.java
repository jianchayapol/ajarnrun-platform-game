package platform;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * SpecialPlatform is a class that is almost the same as JavaFx ImageView but width different name and Marker Interfaces.
 * SpecialPlatform is actually a parent class for Book, Coin, FinishFlag and HurtPlatform classes.
 * @author Mos
 *
 */
public class SpecialPlatform extends ImageView {
	/**
	 * A constructor that set Image from given parameter, also, setFitWidth and fitHeight from two given integers.
	 * @param image Platform's image
	 * @param fitWidth Used to set block's fitWidth
	 * @param fitHeight Used to set block's fitHeight
	 */
	public SpecialPlatform(Image image, int fitWidth, int fitHeight) {
		super(image);
		setFitWidth(fitWidth);
		setFitHeight(fitHeight);
	}
}
