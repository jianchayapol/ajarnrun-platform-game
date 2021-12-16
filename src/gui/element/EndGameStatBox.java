package gui.element;

import application.logic.GameManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

/**
 * This EngGameStatBox is extends from the VBox containing player's stats which
 * are player's name, player's money, player's exp when play completed all the
 * level.
 * 
 * @author jianchayapol
 *
 */
public class EndGameStatBox extends VBox {

	/*
	 * HBox that contains name stat
	 */
	private HBox nameBox;
	/*
	 * HBox that contains money stat
	 */
	private HBox moneyBox;
	/*
	 * HBox that contains exp stat
	 */
	private HBox expBox;

	/**
	 * Constructor construct the EndGameStatBox by initializing the Box and setting
	 * up the box.
	 */
	public EndGameStatBox() {
		initializeBox();
		setUpStatBox();
	}

	/**
	 * This method is used to setup all the pane by adding nameBox, moneyBox, expBox
	 * to the EndGameStatBox Set padding to 10, Set spacing to 15, and set aliment
	 * to CENTER.
	 */
	private void setUpStatBox() {
		this.getChildren().addAll(nameBox, moneyBox, expBox);
		this.setPadding(new Insets(10));
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
	}

	/**
	 * This method inititialize each box by the type by calling createBox(String
	 * type) method.
	 */
	private void initializeBox() {
		nameBox = createBox("name");
		moneyBox = createBox("coin");
		expBox = createBox("exp");
	}

	/**
	 * This method is for creating new box by their type by each cases. Setting the
	 * Box's image by using the RenderableHolder to get public static field setting
	 * the progressLabel's test with each type of data from GameManager by calling
	 * the public static method: GameManager.getPlayerName(),
	 * GameManager.getPlayerCoin(), GameManager.getPlayerEXP(). Then setup its
	 * properties by calling setUpLabel() and setUpImage() methods. Add all the
	 * components to the box and return it.
	 * 
	 * @param type
	 * @return
	 */
	private HBox createBox(String type) {
		HBox box = new HBox(30);
		String progress = "";
		ImageView image = new ImageView();
		Label progressLabel = null;

		switch (type) {
		case "name":
			image = new ImageView(RenderableHolder.bookOne);
			progressLabel = new Label(GameManager.getPlayerName());
			break;
		case "coin":
			image = new ImageView(RenderableHolder.coin);
			progressLabel = new Label(String.valueOf(GameManager.getPlayerCoin()));
			break;
		case "exp":
			type += " ";
			image = new ImageView(RenderableHolder.finish);
			int exp = GameManager.getPlayerEXP();
			progressLabel = new Label(String.valueOf(exp));

			break;
		default:
			break;
		}

		Label typeLabel = new Label(type + ": ");

		setUpImage(image);
		setUpLabel(typeLabel);
		setUpLabel(progressLabel);

		box.getChildren().addAll(image, typeLabel, progressLabel);
		progressLabel.setAlignment(Pos.CENTER_LEFT);
		box.setAlignment(Pos.CENTER_LEFT);

		return box;
	}

	/**
	 * Set the label's font by using static method setFont() from FontLoader to set
	 * font to FontType.TELEGRAMA and fontSize 22. Set textFill to White.
	 * 
	 * @param label
	 */
	private void setUpLabel(Label label) {
		FontLoader.setFont(label, FontType.TELEGRAMA, 22);
		label.setTextFill(Color.WHITE);
	}

	/**
	 * This method is to set the image's fit width and height to 30.
	 * 
	 * @param image
	 */
	private void setUpImage(ImageView image) {
		image.setFitHeight(30);
		image.setFitWidth(30);
	}

}
