package gui.element;

import application.utility.DrawStringUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

/**
 * HowToPlayerBox is a JavaFx StackPane with a specific String to be add to
 * itself in order to show player how to play the game. This class' object will
 * be add to howToPlay SubScene (GameSubScene class) as a root.
 * 
 * @author Jian
 *
 */
public class HowToPlayBox extends StackPane {
	/**
	 * A background rectangle for this class' object.
	 */
	private static Rectangle rec;
	/**
	 * An AnchorPane that will contain a String to be shown
	 */
	private static AnchorPane mainPane;
	/**
	 * A StackPane that will contain background and mainPane
	 */
	private static StackPane textPane;
	/**
	 * A VBox that will contain the header Label and mainPane Then this VBox will be
	 * added to this class' object
	 */
	private static VBox centerBox;
	/**
	 * An ImageView that will be added to mainPane
	 */
	private static ImageView img = new ImageView(RenderableHolder.spritePlayerJumpBackward);
	/**
	 * Width of this class' object
	 */
	private static final int WIDTH = 790;
	/**
	 * Height of this class' object
	 */
	private static final int HEIGHT = 540;

	/**
	 * Since this class is a class that is made specifically for one purpose, it
	 * will not take any parameter. This constructor starts with calling
	 * setUpBakground method and initialize centerBox. Then create the header Label
	 * called howToLabel, set this label's font using FontLoader, set text fill to
	 * be yellow then add this Label to the centerBox. Initialize mainPane as an
	 * AnchorPane and use DrawStringUtility's method to draw a how to play text onto
	 * the pane. Call setUpImage method and setUpPane method then add mainPane to
	 * the centerBox. Lastly this class' object add both background (bg) and
	 * centerBox and setPrefSize and Alignment
	 */
	public HowToPlayBox() {
		setUpBackground();
		centerBox = new VBox(15);
		Label howToLabel = new Label("HOW TO PLAY");
		FontLoader.setFont(howToLabel, FontType.TELEGRAMA, 38);
		howToLabel.setTextFill(Color.YELLOW);
		mainPane = new AnchorPane();
		DrawStringUtility.fillHowToPlay(mainPane);
		setUpImage();
		setUpPane();
		centerBox.getChildren().addAll(howToLabel, mainPane);
		this.getChildren().addAll(rec, centerBox);
		this.setPrefSize(WIDTH, HEIGHT);
		this.setAlignment(Pos.CENTER);

	}

	/**
	 * Initialize rec as a Rectangle with Color.rgb(0,34 75) and Opacity(0.9)
	 */
	private void setUpBackground() {
		rec = new Rectangle(WIDTH, HEIGHT);
		rec.setFill(Color.rgb(0, 34, 75));
		rec.setOpacity(0.9);
	}

	/**
	 * Add img (Image View) to mainPane. Initialize textPane as a StackPane. Add rec
	 * and mainPane to textPane set textPane's alignment to CENTER_LEFT. set
	 * centerBox's alignment to CENTER. set centerBox's max width to 400.
	 */
	private void setUpPane() {
		mainPane.getChildren().add(img);
		textPane = new StackPane();
		textPane.getChildren().addAll(rec, mainPane);
		textPane.setAlignment(Pos.CENTER_LEFT);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setMaxWidth(400);
	}

	/**
	 * Method for setting up image by set fitWidth and fitHeight to 100,140
	 * respectively and set layoutX, layoutY to 600,150 respectively.
	 */
	private void setUpImage() {
		img.setFitWidth(100);
		img.setFitHeight(140);
		img.setLayoutX(600);
		img.setLayoutY(150);
	}

	// ===================== instructions Text ===============================
	/**
	 * A String that will be shown in howToPlayer SubScene
	 */

	public static final String[] instructions = {
			"Keyboard Input",
			"  Press W : Jump                ",
		    "  Press A : Move Player Left    Press D : Move Player Right     ",
			"",
			"  Your Task is to help Ajarn to collect",
			"all the students' notebooks that are missing",
			"and run for your life as fast as possible!", "",
			"Remind you that .. ","You need to collect all the notebooks!",
			"                     As you know what to do   .. Ajarn Run !!"
	};
	
	/**
	 * instructions public static getter
	 * 
	 * @return instructions
	 */
	public static String[] getInstructions() {
		return instructions;
	}

}
