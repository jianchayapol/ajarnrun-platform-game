package gui.element;

import application.utility.DrawStringUtility;
import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import sharedObject.FontLoader;
import sharedObject.FontType;
/**
 * PauseGameLeaderBox is a JavaFx StackPane that will be shown when player click pause button while playing in any level of the game.
 * 
 * @author Jian
 *
 */
public class PauseGameLeaderBox extends StackPane{
	/**
	 * A background image
	 */
	private static Rectangle rec;
	/**
	 * mainPane 
	 */
	private static AnchorPane mainPane;
	/**
	 * textPane that contains texts
	 */
	private static StackPane textPane;
	/**
	 * centerBox is the mainPane
	 */
	private static VBox centerBox;
	/**
	 * Screen's width
	 */
	private static final int WIDTH = 800;
	/**
	 * Screen's height
	 */
	private static final int HEIGHT = 350;
	/**
	 * Set up background using setUpBackground() method.
	 * Create a Label with text "Pause"
	 * Change Label font and font size.
	 * Change Label color to white.
	 * Initialize mainPane as an AnchorPane
	 * Draw leaderboard text using DrawStringUtility.fillLeaderBoard method
	 * Initialize textPane as a StackPane
	 * Add background and mainPane to textPane.
	 * Set textPane's alignment to center left.
	 * Set centerBox's padding to 5
	 * Add Label and mainPane to centerBox
	 * Set centerBox's alignment to center.
	 * set centerBox's maxWidth to 400
	 * Add all item to this class' object
	 * Set alignment to center
	 * Set pref size to WIDTH and HEIGHT
	 */
	public PauseGameLeaderBox() {
		setUpBackground();
		centerBox = new VBox(15);
		Label pauseLabel = new Label("Pause");
		FontLoader.setFont(pauseLabel, FontType.TELEGRAMA, 40);
		pauseLabel.setTextFill(Color.WHITE);
		
		mainPane = new AnchorPane();
		DrawStringUtility.fillLeaderBoard(mainPane);
		textPane = new StackPane();
		textPane.getChildren().addAll(rec,mainPane);
		textPane.setAlignment(Pos.CENTER_LEFT);
		centerBox.setPadding(new Insets(5));
		centerBox.getChildren().addAll(pauseLabel,mainPane);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setMaxWidth(400);
		this.getChildren().addAll(rec,centerBox);
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(WIDTH,HEIGHT);
	}
	/**
	 * Initialize rec as a Rectangle object with WIDTH width, HEIGHT height, black color and opacity 0.9
	 */
	private void setUpBackground() {
		rec = new Rectangle(WIDTH,HEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.9);
	}
	
	
}
