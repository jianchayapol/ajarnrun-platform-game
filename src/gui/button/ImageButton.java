package gui.button;

import view.EnterNameScene;
import view.GameScene;
import view.ViewManager;
import application.logic.GameManager;
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

	private int height;
	private int width;
	private Image img;

	public ImageButton(ImageButtonType imageButtonType) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
	}

	private void initImageButton(ImageButtonType imageButtonType) {

		switch (imageButtonType) {
		case SOUND:
			setSize(30, 30);
			if (GameManager.getIsMute()) {
				img = RenderableHolder.mute_button_Image;
			} else {
				img = RenderableHolder.unmute_button_Image;
			}
			break;
		case PLAY:
			img = RenderableHolder.play_button_Image;
			setSize(139, 50);
			break;
		case NULL:
			break;
		case CONTINUE_LV:
			setSize(139, 50);
			break;
		case SKIP_LV:
			setSize(139, 50);
			break;
		default:
			setSize(0, 0);
			break;
		}
		this.setImage(img);
		this.setFitHeight(height);
		this.setFitWidth(width);

	}

	// Init Event Handler
	private void initEventHandler(ImageButtonType imageButtonType) {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!GameManager.getIsMute()) AudioLoader.Mouse_Click.play();
				switch (imageButtonType) {
				case SOUND:
					setUpSound();
					break;
				case PLAY:
					setUpNameInput();
					break;
				case CONTINUE_LV:
					break;
				case SKIP_LV:
					break;
				default:
					break;
				}
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
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

	// Setup
	public void setUpNameInput() {
		String name = EnterNameScene.getEnteredName();
		if (PlayerStat.checkEnteredName(name)) {
			String[] stat = { name.strip(), "2", "0" };
			CSVUtility.appendToCSV(stat);
			stopViewManager();
			EnterNameScene.setupLoading();
			EnterNameScene.startGameScene();
		}
	}
	public void setUpSound() {
		GameManager.setIsMute(!GameManager.getIsMute());
		initImageButton(ImageButtonType.SOUND);
		if (ViewManager.isVisible()) {
			ViewManager.setIsPlayingThemeSong(!ViewManager.isPlayingThemeSong());
			ViewManager.playThemeSong();
		}
	}
	public void stopViewManager() {
		ViewManager.setIsVisible(false);
		initImageButton(ImageButtonType.NULL);
		AudioLoader.Entrance_Theme_Song.stop();
		ViewManager.setIsPlayingThemeSong(false);
	}

	// Getter & Setter	
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
}
