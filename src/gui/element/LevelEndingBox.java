package gui.element;

import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sharedObject.RenderableHolder;

public class LevelEndingBox extends StackPane {

	private Image bg;
	private ImageButton imgButton;
	private Rectangle rec = new Rectangle(600, 800);

	public LevelEndingBox(LevelEndingType type) {
		initComponents(type);
		this.getChildren().addAll(rec, imgButton);
	}
	
	private void initComponents(LevelEndingType type) {
		switch (type) {
		case COMPLETED:
			imgButton = new ImageButton(ImageButtonType.CONTINUE_LV);
			bg = RenderableHolder.level_passed_Image;
			break;
		case FAILED:
			imgButton = new ImageButton(ImageButtonType.SKIP_LV);
			bg = RenderableHolder.level_failed_Image;
			break;
		default:
			break;
		}
		imgButton.setLayoutX(100);
		imgButton.setLayoutY(100);
		rec.setFill(new ImagePattern(bg));
	}
}
