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

public class PauseGameLeaderBox extends StackPane{
	private static Rectangle rec;
	private static AnchorPane mainPane;
	private static StackPane textPane;
	private static VBox centerBox;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 350;
	
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
	
	private void setUpBackground() {
		rec = new Rectangle(WIDTH,HEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.9);
	}
	
	
}
