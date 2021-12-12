package gui.button;

import view.EnterNameScene;
import view.GameScene;
import view.ViewManager;
import application.logic.GameController;
import application.utility.CSVUtility;
import javafx.application.Platform;
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
			if (GameController.IsMute()) {
				img = RenderableHolder.mute_button_Image;
			} else {
				img = RenderableHolder.unmute_button_Image;
			}
			height = 30;
			width = 30;
		} else if (imageButtonType.equals(ImageButtonType.PLAY)) {
			img = RenderableHolder.play_button_Image;
			height = 50;
			width = 139;
		} else if (imageButtonType.equals(ImageButtonType.NULL)) {
			height = 0;
			width = 0;
		} else if (imageButtonType.equals(ImageButtonType.CONTINUE_LV)) {
			img = RenderableHolder.continue_button_Image;
			height = 60;
			width = 190;
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
				AudioLoader.Mouse_Click.play();

				if (imageButtonType.equals(ImageButtonType.SOUND)) {
					setUpSound(imageButtonType);

				} else if (imageButtonType.equals(ImageButtonType.PLAY)) {
					setUpNameInput();
				} else if (imageButtonType.equals(ImageButtonType.CONTINUE_LV)) {

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

	public void setUpNameInput() {
		String name = EnterNameScene.getEnteredName();
		if (PlayerStat.checkEnteredName(name)) {
			String[] stat = { name.strip().toUpperCase(), "1", "0" };
			CSVUtility.appendToCSV(stat);
			ViewManager.setIsVisible(false);
			initImageButton(ImageButtonType.NULL);
			AudioLoader.Entrance_Theme_Song.stop();
			ViewManager.setIsPlayingThemeSong(false);
			EnterNameScene.getEnterPane().getChildren().remove(1);
			EnterNameScene.setLabel("loading..");
			EnterNameScene.startProgress();
			Thread thread = new Thread(() -> {
				try {
					Thread.sleep(1600);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							GameScene gameScene = new GameScene(EnterNameScene.getPrimaryStage());
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			thread.start();

		}
	}

	public void setUpSound(ImageButtonType imageButtonType) {
		AudioLoader.Mouse_Click.play();
		GameController.setMute(!GameController.IsMute());
		initImageButton(imageButtonType);
		if (ViewManager.isVisible()) {
			ViewManager.setIsPlayingThemeSong(!ViewManager.isPlayingThemeSong());
			ViewManager.playThemeSong();
		}
	}

}
