package view;

import application.utility.*;
import javafx.animation.TranslateTransition;
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
/**
 * GameSubScene class is a JavaFx SubScene class that will be called to initialize SubScenes in Main Menu, such as about us, leaderboard, How to play.
 * And also create a SubScene when player pause the game during the gameplay state.
 * There are 2 constructor in this class. The first constructor takes a String as the first parameter, while the second constructor takes a Pane as the first parameter.
 * @author Mos
 *
 */
public class GameSubScene extends SubScene {
	
	
	// ================= Constructor: MainView SubScene ===============================
	/**
	 * This constructor takes a String as the first parameter in order to initiailize an image in order to set SubScene's root background.
	 * There are two switch cases in this constructor to choose wheater about us SubScene or leaderboard SubScene will be created.
	 * @param imageURL A url that is used to initialize an Image object
	 * @param altText A String to choose which SubScene to create.
	 * @param width Width of SubScene's root
	 * @param height Height of SubScene's root
	 */
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
		default:
			break;
		}
	}

	// ================= Constructor: GameView SubScene ===============================
	/**
	 * This constructor takse a Pane as the first parameter but doesn't use that pane as a SubScene's root, instead, this constructor initialize new AnchorPane and add the Pane parameter to this AnchorPane
	 * There are two switch cases in this constructor to choose wheater pause game leaderboard SubScene or How to play SubScene will be created.
	 * @param pane
	 * @param altText A String to choose which SubScene to create.
	 * @param width Width of SubScene's root
	 * @param height Height of SubScene's root
	 */
	public GameSubScene(Pane pane, String altText, int width, int height) {
		super(new AnchorPane(), width, height);
		prefWidth(width);
		prefHeight(height);
		AnchorPane subSceneRoot = (AnchorPane) this.getRoot();
		subSceneRoot.getChildren().add(pane);

		switch (altText) {
		case "pauseGameLeaderboard":
			setLayoutX(0);
			setLayoutY(-height-20);
			setOpacity(0.65);
			setEffect(new DropShadow());
			break;
		case "howToPlay":
			setLayoutX(0);
			setLayoutY(-height-100);
			setOpacity(0.65);
			setEffect(new DropShadow());
			break;
		default:
			break;
		}
	}
	/**
	 * This method create a JavaFx TranslateTransition object and set GameSubScene(this) as its Node.
	 * Then move the Node using TranslateTransition method using altText to tells the TranslateTransition how to move it.
	 * @param altText
	 */
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
		case "leaderBoardUnpressed":
			transition.setToX(-width - 120);
			transition.setToY(0);
			break;
		case "leaderBoardPressed":
			transition.setToX(width + 120);
			transition.setToY(0);
			break;
		
		// ================= GameView SubScene =============================

		case "pauseGameUnpressed":
			transition.setToX(0);
			transition.setToY(height+135);
			break;
		case "pauseGamePressed":
			transition.setToX(0);
			transition.setToY(-height-135);
			break;
		case "howToPlayUnpressed":
			transition.setToX(0);
			transition.setToY(height+148);
			break;
		case "howToPlayPressed":
			transition.setToX(0);
			transition.setToY(-height-148);
			break;

		default:
			break;
		}
		transition.play();
	}
	
}
