package view;

import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class AjarnRunLevelSubScene extends SubScene {

	public AjarnRunLevelSubScene(Parent arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
	}
	
	public AjarnRunLevelSubScene(String imageURL, String altText) {
		super(new AnchorPane(), ViewManager.getScreenWidth(), ViewManager.getScreenHeight());
		prefWidth(ViewManager.getScreenWidth());
		prefHeight(ViewManager.getScreenHeight());
		BackgroundImage img = new BackgroundImage(new Image(imageURL, ViewManager.getScreenWidth(), ViewManager.getScreenHeight(), false, true),
				BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT,
				null
			);
		AnchorPane subSceneRoot = (AnchorPane) this.getRoot();
		subSceneRoot.setBackground(new Background(img));
	}

}
