package platform;

import javafx.scene.image.Image;
import platform.base.Collectable;

public class Book extends SpecialPlatform implements Collectable {
	public Book(Image image, int fitWidth, int fitHeight, int translateX, int translateY) {
		super(image, fitWidth, fitHeight);
		setTranslateX(translateX);
		setTranslateY(translateY);
	}
}
