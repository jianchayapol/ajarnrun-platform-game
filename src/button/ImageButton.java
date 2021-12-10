package button;

import view.ViewManager;
import application.logic.GameController;
import application.utility.ImageButtonType;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sharedObject.AudioLoader;
import sharedObject.RenderableHolder;

public class ImageButton extends ImageView {

	public ImageButton(ImageButtonType imageButtonType) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
	}

	private void initImageButton(ImageButtonType imageButtonType) {
		Image img = null;
		if (imageButtonType.equals(imageButtonType.SOUND)) {
			if(GameController.IsMute()) {
				img = RenderableHolder.mute_button_Image;
			}
			else {
				img = RenderableHolder.unmute_button_Image;
			}
		}
		this.setImage(img);
		this.setFitHeight(30);
		this.setFitWidth(30);
		
	}

	private void initEventHandler(ImageButtonType imageButtonType) {
		// TODO
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				if (imageButtonType.equals(imageButtonType)) {
					AudioLoader.Mouse_Click.play();
					GameController.setMute(!GameController.IsMute());
					initImageButton(imageButtonType);
					if(ViewManager.isVisible()) {
						ViewManager.setPlayingThemeSong(!ViewManager.isPlayingThemeSong());
						ViewManager.playThemeSong();
					}
					
				}
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				ImageButton.this.getParent().setCursor(Cursor.DEFAULT);
				setEffect(null);
			}
		});
		
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				ImageButton.this.getParent().setCursor(Cursor.HAND);
				setEffect(new DropShadow());
			}
		});
		
	}
	
}

