package view;

import application.utility.*;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class GameSubScene extends SubScene {

	private static final String BACKGROUND_IMAGE = "/mainSceneBackground_withoutLogo_fixed.png";

	public GameSubScene(String imageURL, String altText, int width, int height) {
		super(new AnchorPane(), width, height);
		prefWidth(width);
		prefHeight(height);
		BackgroundImage img = new BackgroundImage(new Image(imageURL, width, height, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane subSceneRoot = (AnchorPane) this.getRoot();
		subSceneRoot.setBackground(new Background(img));

		switch (altText) {
		case "infoButton":
			setLayoutX(-width - 100);
			setLayoutY(20);
			setEffect(new DropShadow());
			break;
		case "leaderBoard":
			setLayoutX(900);
			setLayoutY(20);
			setEffect(new DropShadow());
			DrawStringUtility.fillLeaderBoard(this);

			break;
		case "newGame":
			setLayoutX(width / 2);
			setLayoutY(height / 2);
			setEffect(new DropShadow());
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
			transition.setToX(this.getWidth() + 120);
			transition.setToY(0);
			break;
		case "infoButtonPressed":
			transition.setToX(-this.getWidth() - 120);
			transition.setToY(0);
			break;
		case "leaderBoardUnpressed":
			transition.setToX(-this.getWidth() - 120);
			transition.setToY(0);
			break;
		case "leaderBoardPressed":
			transition.setToX(this.getWidth() + 120);
			transition.setToY(0);
			break;
		default:
			break;
		}
		transition.play();
	}

	

}
