package gui.element;

import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

public class EndingLevelTextBox extends StackPane {

	private static Rectangle bgRec;
	private static Label textLabel;
	private int textSize;
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	
	public EndingLevelTextBox(String text) {
		
		setUpBackground();
		String textToWrite = "";
		
		switch(text) {
		case "timeUp":
			textToWrite = "Time's Up!";
			textSize= 90;
			break;
		case "complete":
			textToWrite = "Level Completed!";
			textSize= 60;
			break;
		case "notebook":
			textToWrite = "You haven't collect \n all the notebooks !";
			textSize= 50;
			break;
		case "wasted":
			textToWrite = "WASTED!";
			textSize= 90;
			break;
		}
		setUpTextLabel(textToWrite);
		this.getChildren().addAll(bgRec,textLabel);
	}
	
	private void setUpBackground() {
		bgRec = new Rectangle(WIDTH, HEIGHT);
		bgRec.setFill(Color.BLACK);
		bgRec.setOpacity(0.3);
	}

	private void setUpTextLabel(String textToWrite) {
		textLabel = new Label(textToWrite);
		textLabel.setTextFill(Color.WHITESMOKE);
		FontLoader.setFont(textLabel, FontType.TELEGRAMA, textSize);
		textLabel.setAlignment(Pos.CENTER);
	}


}