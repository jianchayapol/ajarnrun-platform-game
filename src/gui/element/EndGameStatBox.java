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

public class EndGameStatBox extends VBox {

	private HBox nameBox;
	private HBox moneyBox;
	private HBox expBox;

	public EndGameStatBox() {
		initializeBox();
		setUpStatBox();
	}
	
	private void setUpStatBox() {
		this.getChildren().addAll(nameBox, moneyBox, expBox);
		this.setPadding(new Insets(10));
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
	}
	
	private void initializeBox() {
		nameBox = createBox("name");
		moneyBox = createBox("coin");
		expBox = createBox("exp");
	}
	
	private HBox createBox(String type) {
		HBox box = new HBox(30);
		String progress = "";
		ImageView image = new ImageView();
		Label progressLabel = null;

		switch (type) {
		case "name":
			image = new ImageView(RenderableHolder.enemyOneLeft);
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

	private void setUpLabel(Label label) {
		FontLoader.setFont(label, FontType.TELEGRAMA, 22);
		label.setTextFill(Color.WHITE);
	}

	private void setUpImage(ImageView image) {
		image.setFitHeight(30);
		image.setFitWidth(30);
	}
	
}
