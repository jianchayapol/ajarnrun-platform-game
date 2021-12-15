package platform;

import javafx.scene.image.Image;
import platform.base.Collectable;

public class FinishFlag extends SpecialPlatform implements Collectable {
	public FinishFlag(Image image, int fitWidth, int fitHeight, int translateX, int translateY) {
		super(image, fitWidth, fitHeight);
		setTranslateX(translateX);
		setTranslateY(translateY);
	}
}
