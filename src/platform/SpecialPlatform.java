package platform;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpecialPlatform extends ImageView {
	public SpecialPlatform(Image image, int fitWidth, int fitHeight) {
		super(image);
		setFitWidth(fitWidth);
		setFitHeight(fitHeight);
	}
}
