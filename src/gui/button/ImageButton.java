package gui.button;

import view.EnterNameScene;
import view.GameScene;
import view.ViewManager;
import application.logic.*;
import application.utility.CSVUtility;
import exception.BuyItemFailedException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.leaderboard.PlayerStat;
import logic.shop.ShopManager;
import sharedObject.AudioLoader;
import sharedObject.RenderableHolder;

public class ImageButton extends ImageView {

	private int height;
	private int width;
	private Image img;
	private String altText;
	
	public ImageButton(ImageButtonType imageButtonType) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
	}
	
	public ImageButton(ImageButtonType imageButtonType, String altText) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
		this.altText = altText;
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
		case CONTINUE_LV:
			setSize(139, 50);
			break;
		case SKIP_LV:
			setSize(139, 50);
			break;
		case BUY:
			setSize(80, 20);
			break;
		case PAUSE:
			img = RenderableHolder.pause_button_Image;
			setSize(30, 30);
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
	private void setUpNameInput() {
		String name = EnterNameScene.getEnteredName();
		if (PlayerStat.checkEnteredName(name)) {
			// Add to CSV file
			String[] stat = { name.strip(), "1", "0" };
			CSVUtility.appendToCSV(stat);
			// Preparing to the next Scene
//			initImageButton(ImageButtonType.NULL);
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
	}
	
	private void setUpBuyItem() {
		switch(altText) {
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

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}
	
}
