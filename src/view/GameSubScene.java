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
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameSubScene extends SubScene {

	private static final String MAIN_VIEW_BACKGROUND_IMAGE = "/mainSceneBackground_withoutLogo_fixed.png";

	// ================= Constructor: MainView SubScene ===============================

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

	// ================= Constructor: GameView SubScene ===============================
	
	public GameSubScene(Pane pane, String altText, int width, int height) {
		super(new AnchorPane(), width, height);
		prefWidth(width);
		prefHeight(height);
		AnchorPane subSceneRoot = (AnchorPane) this.getRoot();
		subSceneRoot.getChildren().add(pane);

		switch (altText) {
		
		case "levelEnding":
			setLayoutX(0);
			setLayoutY(0);
			setEffect(new DropShadow());
			break;
		case "shop":
			setLayoutX(0);
			setLayoutY(0);
			setEffect(new DropShadow());
			DrawStringUtility.fillLeaderBoard(this);
			break;
		case "pauseGameLeaderboard":
			setLayoutX(0);
			setLayoutY(-height-20);
			setOpacity(0.65);
			setEffect(new DropShadow());
			break;
		default:
			break;
		}
	}
	
	public void moveSubScene(String altText) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.5));
		transition.setNode(this);
		double width = this.getWidth();
		double height = this.getHeight();
				
		switch (altText) {
		
		// ================= mainView SubScene =============================
		
		case "infoButtonUnpressed":
			transition.setToX(width + 120);
			transition.setToY(0);
			break;
		case "infoButtonPressed":
			transition.setToX(-width - 120);
			transition.setToY(0);
			break;
		case "leaderboardUnpressed":
			transition.setToX(-width - 120);
			transition.setToY(0);
			break;
		case "leaderboardPressed":
			transition.setToX(width + 120);
			transition.setToY(0);
			break;
		
		// ================= GameView SubScene =============================
		
		case "levelEnding":
			transition.setToX(width + 120);
			transition.setToY(0);
			break;	
		case "shop":
			transition.setToX(-width - 120);
			transition.setToY(0);
			break;
		case "pauseGameUnpressed":
			transition.setToX(0);
			transition.setToY(height+135);
			break;
		case "pauseGamePressed":
			transition.setToX(0);
			transition.setToY(-height-135);
			break;

		default:
			break;
		}
		transition.play();
	}

}
