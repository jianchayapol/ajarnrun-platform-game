package gui.element;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerStatBox {
	private ImageView image;
	private Label moneyLabel;
	private Label expLabel;
	private Label timeLabel;
	private VBox root;
	private ProgressBar progBar;
	
	public PlayerStatBox() {
		// imageView = RenderableHolder.???
		setHpProgress();
		timeLabel.setText("360");
		expLabel.setText("0");
		
		root = new VBox(30);
		
		HBox InfoBox = new HBox(20);
		InfoBox.getChildren().addAll(moneyLabel,timeLabel);
		
		root.getChildren().addAll(image,progBar,InfoBox);
	}

	public void setHpProgress() {
		progBar.setProgress(80.0/100.0);
	}
	
	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	public Label getMoneyLabel() {
		return moneyLabel;
	}

	public Label getExpLabel() {
		return expLabel;
	}

	public ProgressBar getProgBar() {
		return progBar;
	}

	public void setProgBar(ProgressBar progBar) {
		this.progBar = progBar;
	}

}
