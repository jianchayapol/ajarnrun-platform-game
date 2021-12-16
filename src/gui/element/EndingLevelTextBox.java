package gui.element;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sharedObject.FontLoader;
import sharedObject.FontType;

/**
 * EndingLevelTextBox is a JavaFx StackPane that will be shown when a level is ended whether you die, your time is up, you fail a mission, or you complete a level.
 * This class is just a StackPane that has a transparent background with a text shown at the center of the screen.
 * @author Jian
 *
 */
public class EndingLevelTextBox extends StackPane {
	/**
	 * bgRec is a backgroundRectangle, this is the background for this class, and it'll fit up the screen width and height.
	 */
	private static Rectangle bgRec;
	/**
	 * textLabel is a JavaFx Label object.
	 * This is the text that is shown when a level is ended.
	 */
	private static Label textLabel;
	/**
	 * textSize is an integer used to set textLabel's size
	 */
	private int textSize;
	/**
	 * Screen's height
	 */
	private static final int HEIGHT = 600;
	/**
	 * Screen's width
	 */
	private static final int WIDTH = 800;
	/**
	 * The constructor call setUpBackground method and create an empty String called "textToWrite".
	 * Then the constructor use switch case with 4 cases to set textToWrite to a specific text.
	 * Lastly, the constructor call method setUpTextLabel and add background and textLabel to the object itself.
	 * @param text An alternative text to be used in switch case's cases to set textToWrite String.
	 */
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
	/**
	 * Initialize bgRec as a Rectangle object which is fitted to the screen.
	 * Then set the background's opacity to 0.3
	 */
	private void setUpBackground() {
		bgRec = new Rectangle(WIDTH, HEIGHT);
		bgRec.setFill(Color.BLACK);
		bgRec.setOpacity(0.3);
	}
	/**
	 * Initialize textLabel with String textToWrite (which is the given parameter).
	 * Then set some styles and setAlignment to Center.
	 * @param textToWrite A string used to setLabel for textLabel
	 */
	private void setUpTextLabel(String textToWrite) {
		textLabel = new Label(textToWrite);
		textLabel.setTextFill(Color.WHITESMOKE);
		FontLoader.setFont(textLabel, FontType.TELEGRAMA, textSize);
		textLabel.setAlignment(Pos.CENTER);
	}


}