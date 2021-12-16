package gui.element;

import application.utility.DrawStringUtility;
import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
import sharedObject.RenderableHolder;

public class HowToPlayBox extends StackPane {
	private static Rectangle rec;
	private static AnchorPane mainPane;
	private static StackPane textPane;
	private static VBox centerBox;
	private static ImageView img = new ImageView(RenderableHolder.spritePlayerJumpBackward);
	private static final int WIDTH = 790;
	private static final int HEIGHT = 540;

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

	private void setUpBackground() {
		rec = new Rectangle(WIDTH, HEIGHT);
		rec.setFill(Color.rgb(0, 34, 75));
		rec.setOpacity(0.9);
	}

	private void setUpPane() {
		mainPane.getChildren().add(img);
		textPane = new StackPane();
		textPane.getChildren().addAll(rec, mainPane);
		textPane.setAlignment(Pos.CENTER_LEFT);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setMaxWidth(400);
	}

	private void setUpImage() {
		img.setFitWidth(100);
		img.setFitHeight(140);
		img.setLayoutX(600);
		img.setLayoutY(150);
	}

	// ===================== instructions Text ===============================

	public static final String[] instructions = { "Keyboard Input",
			"  Press W : Jump                Press ESC : Back to Main Menu",
			"  Press A : Move Player Left    Press D : Move Player Right     ", "",
			"  Your Task is to help Ajarn to collect", "all the students' notebooks that are missing",
			"and run for your life as fast as possible!", "", "Remind you that .. ",
			"You need to collect all the notebooks!", "                     As you know what to do   .. Ajarn Run !!" };

	public static String[] getInstructions() {
		return instructions;
	}

}
