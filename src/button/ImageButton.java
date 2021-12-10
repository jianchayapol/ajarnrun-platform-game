package button;

import application.Main;
import application.logic.GameController;
import application.utility.ImageButtonType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends ImageView {

	public ImageButton(ImageButtonType imageButtonType) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
	}

	private void initImageButton(ImageButtonType imageButtonType) {
		Image img = null;
		if (imageButtonType.equals(imageButtonType.SOUND)) {
			GameController.setMute(!GameController.IsMute());
			if(GameController.IsMute()) {
				img = null;
			}
			else {
				img = null;
			}
		}
		this.setImage(img);
		this.setFitHeight(26);
		this.setFitWidth(26);
	}

	private void initEventHandler(ImageButtonType imageButtonType) {
		// TODO
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (imageButtonType.equals(imageButtonType.SOUND)) {
					initImageButton(imageButtonType.SOUND);
				}
			}
		});
	}
	
}

