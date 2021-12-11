package gui.element;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerStatBox extends HBox {
	private ImageView image;
	private Label moneyLabel;
	private Label levelLabel;
	private Label nameLabel;
	private ProgressBar progBar;

	public PlayerStatBox() {
		// imageView = RenderableHolder.???
		setHpProgress();
		HBox infoBox1 = new HBox();
		infoBox1.getChildren().addAll(levelLabel, progBar);

		HBox infoBox2 = new HBox();
		infoBox2.getChildren().addAll(nameLabel, moneyLabel);

		VBox playerBox = new VBox(20);
		playerBox.getChildren().addAll(infoBox1, infoBox2);

		this.getChildren().addAll(image, playerBox);
	}

	public void setHpProgress() {
		progBar.setProgress(80.0 / 100.0);
	}
}
