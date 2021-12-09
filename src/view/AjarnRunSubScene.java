package view;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class AjarnRunSubScene extends SubScene {
	
	private static final String BACKGROUND_IMAGE = "/mainSceneBackground_withoutLogo_fixed.png";
	
	public AjarnRunSubScene(Parent mainPane, double width, double height) {
		super(mainPane, width, height);
		prefWidth(width);
		prefHeight(height);
		BackgroundImage img = new BackgroundImage(new Image(BACKGROUND_IMAGE, 800, 600, false, true),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				null
			);
		/* AnchorPane root2 = (AnchorPane) this.getRoot(); */
	}
	public AjarnRunSubScene(String imageURL, String altText, int width, int height) {
		super(new AnchorPane(), width, height);
		prefWidth(width);
		prefHeight(height);
		BackgroundImage img = new BackgroundImage(new Image(imageURL, width, height, false, true),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				null
			);
		AnchorPane subSceneRoot = (AnchorPane) this.getRoot();
		subSceneRoot.setBackground(new Background(img));
		
		switch (altText) {
		case "infoButton":
			setLayoutX(-width-100);
			setLayoutY(20);
			break;
		default:
			break;
		}
	}
	
	public void moveSubScene(String altText) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.5));
		transition.setNode(this);
		switch (altText) {
		case "infoButtonUnpressed":
			transition.setToX(this.getWidth()+120);
			transition.setToY(0);
			break;
		case "infoButtonPressed":
			transition.setToX(-this.getWidth()-120);
			transition.setToY(0);
		default:
			break;
		}
		transition.play();
	}
}
