package gui.element;

import gui.button.MainButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sharedObject.RenderableHolder;
import view.GameScene;
/**
 * CongratsBox is a JavaFx StackPane that will be shown when you finish the game.
 * The player will see there exp and level.
 * @author Mos
 *
 */
public class CongratsBox extends StackPane {
	/**
	 * A rectangle that will be used as a background
	 */
	private static Rectangle bgRec;
	/**
	 * mainBox
	 */
	private static VBox mainBox;
	/**
	 * statPane that contains player's stats
	 */
	private static VBox statPane;
	/**
	 * centerPane that will be at the center of the screen
	 */
	private static HBox centerBox;
	/**
	 * exitButton
	 */
	private static MainButton exitButton;
	/**
	 * Player's image to be shown in congrats box
	 */
	private static ImageView peopleImage = new ImageView(RenderableHolder.playerImage);
	/**
	 * Congrats image
	 */
	private static ImageView congratsImage = new ImageView(RenderableHolder.congrats_Image);
	/**
	 * Screen's height
	 */
	private static final int HEIGHT = 600;
	/**
	 * Screen's width
	 */
	private static final int WIDTH = 800;
	
	/**
	 * Constructors constructs by 
	 * initialize all the components by calling
	 * Rectangle bgRec, a dark background of the LevelEndingBox with the size of the
	 * LevelEndingBox. SetFill to be black with opacity 0.8
	 * Create mainPane as new AnchorPane to store bgRec, mainBox
	 * setAlignment of this congratsBox to Pos.CENTER
	 * setLayoutX and LayoutY of mainBox to 180,140 respectively
	 * Add mainPane to congratsBox
	 */
	public CongratsBox() {
		initComponents();
		bgRec = new Rectangle(WIDTH, HEIGHT);
		bgRec.setFill(Color.BLACK);
		bgRec.setOpacity(0.8);
		AnchorPane mainPane = new AnchorPane();
		mainPane.getChildren().addAll(bgRec, mainBox);
		this.setAlignment(Pos.CENTER);
		mainBox.setLayoutX(180);
		mainBox.setLayoutY(140);
		this.getChildren().addAll(mainPane);
	}

	/**
	 * initialize all the components
	 * creating mainBox as a new VBox with spacing = 40
	 * creating centerBox as a new VBox with spacing = 30
	 * creating statBox as a new VBox with spacing = 40
	 * set mainBox's alignment to CENTER
	 * initialize the Exit Button
	 * add new EndGameStatBox and exitButton to statPane
	 * set peopleImage's set fit width and height to 160,200 respectively
	 * Add all the peopleImage, statPane to centerBox
	 * Add all the congratsImage, centerBox to mainBox
	 * and setup the mouse event effect by calling setUpMouseEnter()
	 * 
	 */
	
	private void initComponents() {
		mainBox = new VBox(40);
		centerBox = new HBox(30);
		statPane = new VBox(40);
		mainBox.setAlignment(Pos.CENTER);

		initializeExitButton();

		statPane.getChildren().addAll(new EndGameStatBox(), exitButton);
		peopleImage.setFitWidth(160);
		peopleImage.setFitHeight(200);
		centerBox.getChildren().addAll(peopleImage, statPane);
		mainBox.getChildren().addAll(congratsImage, centerBox);
		setUpMouseEnter();

	}

	/**
	 * initialize mainButton as an "Exit" button 
	 * an setOnAction to close the application by calling method GameScene.getStage().close()
	 */
	private void initializeExitButton() {
		exitButton = new MainButton("Exit");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameScene.getStage().close();
			}
		});
	}

	// ====================== private static method(s) ============================

	/**
	 * method for setting EventHandler MouseEvent to set the DropShadow effect by
	 * using setEffect(new DropShadow()) while mouse entering and set null while
	 * mouse exit.
	 */
	private void setUpMouseEnter() {

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				CongratsBox.congratsImage.setEffect(new DropShadow());
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				CongratsBox.congratsImage.setEffect(null);
			}
		});
	}

}