package gui.element;

import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sharedObject.RenderableHolder;

public class LevelPassBox extends StackPane {

	private Image bg;
	private ImageButton imgButton;
	private Rectangle rec = new Rectangle(600, 800);
	private int x = 100, y = 100;

	public LevelPassBox(LevelPassType type) {

		switch (type) {
		case COMPLETED:
			imgButton = new ImageButton(ImageButtonType.CONTINUE_LV);
			bg = RenderableHolder.level_passed_Image;
			rec.setFill(new ImagePattern(bg));
			imgButton.setLayoutX(x);
			imgButton.setLayoutY(y);
			this.getChildren().addAll(rec, imgButton);
			break;
		case FAILED:
			imgButton = new ImageButton(ImageButtonType.SKIP_LV);
			bg = RenderableHolder.level_failed_Image;
			rec.setFill(new ImagePattern(bg));
			imgButton.setLayoutX(x);
			imgButton.setLayoutY(y);
			this.getChildren().addAll(rec, imgButton);
			break;
		default:
			break;
		}
	}
}
