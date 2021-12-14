package gui.button;

import view.EnterNameScene;
import view.GameScene;
import view.ViewManager;
import application.logic.*;
import application.utility.CSVUtility;
import application.utility.NameInputUtility;
import gui.element.PauseGameLeaderBox;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import logic.shop.ShopManager;
import sharedObject.AudioLoader;
import sharedObject.RenderableHolder;

public class ImageButton extends ImageView {

	private int height;
	private int width;
	private static Image image;
	private static String altText;

	// constructor1 : normal
	public ImageButton(ImageButtonType imageButtonType) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
	}

	// constructor2 : BuyButton (ShopScene)
	public ImageButton(ImageButtonType imageButtonType, String altText) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
		ImageButton.altText = altText;
	}

	// Init Image Button
	private void initImageButton(ImageButtonType imageButtonType) {

		switch (imageButtonType) {
		case SOUND:
			setSize(30, 30);
			if (GameManager.getIsMute()) {
				image = RenderableHolder.mute_button_Image;
			} else {
				image = RenderableHolder.unmute_button_Image;
			}
			break;
		case PLAY:
			image = RenderableHolder.play_button_Image;
			setSize(139, 50);
			break;
		case CONTINUE_LV:
			image = RenderableHolder.continue_button_Image;
			setSize(139, 50);
			break;
		case SKIP_LV:
			image = RenderableHolder.skip_button_Image;
			setSize(139, 50);
			break;
		case BUY:
			image = RenderableHolder.buy_button_Image;
			setSize(72, 40);
			break;
		case PAUSE:
			if (!GameScene.getIsPause()) {
				image = RenderableHolder.pause_button_Image;
			} else {
				image = RenderableHolder.unmute_button_Image;
			}
			setSize(30, 30);
			break;
		default:
			setSize(0, 0);
			break;
		}
		this.setImage(image);
		this.setFitHeight(height);
		this.setFitWidth(width);
	}

	// Init Event Handler
	private void initEventHandler(ImageButtonType imageButtonType) {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AudioLoader.Mouse_Click.play();
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
				case BUY:
					setUpBuyItem();
					break;
				case PAUSE:
					setUpPauseScreen();
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

	// ====================== SETUP =========================

	public void setUpNameInput() {
		String name = EnterNameScene.getEnteredName();
		if (NameInputUtility.checkEnteredName(name)) {
			// Add to CSV file
			String[] stat = { name.strip(), "1", "0" };
			CSVUtility.appendToCSV(stat);
			// Preparing to the next Scene
			setImage(null);
			ViewManager.stopViewManager();
			// loading.. GameScene
			EnterNameScene.setupLoading();
			EnterNameScene.startGameScene();
		}
	}
	
	private void setUpSound() {
		GameManager.setIsMute(!GameManager.getIsMute());
		initImageButton(ImageButtonType.SOUND);
		if (ViewManager.isVisible()) {
			ViewManager.setIsPlayingThemeSong(!ViewManager.isPlayingThemeSong());
			ViewManager.playThemeSong();
		}
		if (GameScene.isVisible()) {
			GameScene.setIsPlayingThemeSong(!GameScene.isPlayingThemeSong());
			GameScene.playThemeSong();
		}
	}

	private void setUpBuyItem() {
		switch (altText) {
		case "run":
			ShopManager.buyShopItem(10);
			break;
		case "jump":
			ShopManager.buyShopItem(10);
			break;
		case "time":
			ShopManager.buyShopItem(10);
			break;
		case "lp":
			ShopManager.buyShopItem(10);
			break;
		default:
			break;
		}
	}

	private void setUpPauseScreen() {
		if (GameScene.getIsPause()) {
			GameScene.getPauseGameLeaderboard().moveSubScene("pauseGamePressed");
			GameScene.getTimer().start();
		} else {
			GameScene.getPauseGameLeaderboard().moveSubScene("pauseGameUnpressed");
			GameScene.getTimer().stop();
		}
		GameScene.setIsPause(!GameScene.getIsPause());
		initImageButton(ImageButtonType.PAUSE);
	}

	// =========================== GETTERS - SETTERS
	// =================================

	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}

	public static String getAltText() {
		return altText;
	}

	public static void setAltText(String altText) {
		ImageButton.altText = altText;
	}
	
	public void playButtonPress() {
		setUpNameInput();
	}

}
