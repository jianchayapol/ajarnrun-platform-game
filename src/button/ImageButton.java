package button;

import view.EnterNameScene;
import view.GameScene;
import view.ViewManager;
import application.logic.GameController;
import application.utility.CSVUtility;
import application.utility.ImageButtonType;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import player.PlayerStat;
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
		int height = 0;
		int width = 0;
		
		if (imageButtonType.equals(imageButtonType.SOUND)) {
			if(GameController.IsMute()) {
				img = RenderableHolder.mute_button_Image;
			}
			else {
				img = RenderableHolder.unmute_button_Image;
			}
			height = 30;
			width = 30;
		}
		else if(imageButtonType.equals(ImageButtonType.PLAY)) {
			img = RenderableHolder.play_button_Image;
			height = 50;
			width = 139;
		}
		this.setImage(img);
		this.setFitHeight(height);
		this.setFitWidth(width);
		
	}

	private void initEventHandler(ImageButtonType imageButtonType) {
		// TODO
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				if (imageButtonType.equals(ImageButtonType.SOUND)) {
					AudioLoader.Mouse_Click.play();
					GameController.setMute(!GameController.IsMute());
					initImageButton(imageButtonType);
					if(ViewManager.isVisible()) {
						ViewManager.setPlayingThemeSong(!ViewManager.isPlayingThemeSong());
						ViewManager.playThemeSong();
					}
					
				}
				else if(imageButtonType.equals(ImageButtonType.PLAY)) {
					 String name = EnterNameScene.getEnteredName();
					 if(PlayerStat.checkEnteredName(name)) {
						 String[] stat = {name.strip(),"1","0"};
						 CSVUtility.appendToCSV(stat);
						 ViewManager.setVisible(false);
						 GameScene gameScene = new GameScene(EnterNameScene.getPrimaryStage());
						 AudioLoader.Entrance_Theme_Song.stop();
						 ViewManager.setPlayingThemeSong(false);
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

