package platform;

import javafx.scene.image.Image;
import platform.base.Damagable;

public class HurtPlatform extends SpecialPlatform implements Damagable {
	public HurtPlatform(Image image, int fitWidth, int fitHeight, int translateX, int translateY) {
		super(image, fitWidth, fitHeight);
		setTranslateX(translateX);
		setTranslateY(translateY);
	}
}
