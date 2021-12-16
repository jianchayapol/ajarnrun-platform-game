package gui.button;

import view.EnterNameScene;
import view.GameScene;
import view.ViewManager;
import application.logic.*;
import application.utility.CSVUtility;
import application.utility.NameInputUtility;
import gui.element.MoneyBox;
import gui.element.ShopPane;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.shop.ShopItem;
import logic.shop.ShopManager;
import sharedObject.AudioLoader;
import sharedObject.RenderableHolder;

/**
 * This ImageButton class extend ImageView. The overall concept of this
 * ImageButton is a ImageView that acts like a button. There are various type of
 * ImageButton ( You can see in ImageButtonType enum).
 * 
 * @author jianchayapol
 *
 */
public class ImageButton extends ImageView {

	/**
	 * represents the ImageButton's height
	 */
	private int height;
	/**
	 * represents the ImageButton's width
	 */
	private int width;
	/**
	 * an Image of the ImageButton
	 */
	private static Image image;
	/**
	 * String that represents the button's own characteristic (used in
	 * ImageButtonTyoe.BUY in process of buying item only)
	 */
	private String altText;

	/**
	 * This Constructors is the General Constructor for most of the ImageButton
	 * except ImageItemType.BUY. This will initialize the button's fields and
	 * initialize the Event Handler.
	 * 
	 * @param imageButtonType
	 */
	public ImageButton(ImageButtonType imageButtonType) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
	}

	/**
	 * This Constructor is specified to used with ImageButtonType.BUY in process of
	 * buying shopItem only. This will set the altText with the altText given in
	 * parameter, initialize the button's fields and the Event Handler.
	 * 
	 * @param imageButtonType
	 * @param altText
	 */
	public ImageButton(ImageButtonType imageButtonType, String altText) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
		this.altText = altText;
	}

	/**
	 * This Method is to Initialize the ImageButton's field by different type of the
	 * button. (1) SOUND: If the game is mute, will initialize the image as
	 * mute_button_Image. If not, image=unmute_button_Image. set image size to
	 * 35x35. (2)PLAY: set image as play_button_Image with size 139x50. (3),(4)
	 * CONTINUE_TOSHOP set image as continue_button_Image with size of 139x50. (5)
	 * EXIT_GAME set image as quit_Image and set size to 139x50. (6) BUY set image
	 * as buy_button_Image and set size to 72x40. (7) EXIT_GAME set image as
	 * quit_Image and set size to 139x50. (8) PAUSE: If the game is pause, will
	 * initialize the image as pause_button_Image. If not, image=resume_button_Image
	 * and set size to 24x24. (9) HOME set image as home_button_Image and set size
	 * to 45x45. and (10) HELP set image as question_button_Image set opacity to 0.6
	 * and set size to 30x28.
	 * 
	 * @param imageButtonType
	 */
	private void initImageButton(ImageButtonType imageButtonType) {

		switch (imageButtonType) {
		case SOUND:
			if (GameManager.isMute()) {
				image = RenderableHolder.mute_button_Image;
			} else {
				image = RenderableHolder.unmute_button_Image;
			}
			setSize(35, 35);
			break;
		case PLAY:
			image = RenderableHolder.play_button_Image;
			setSize(139, 50);
			break;
		case CONTINUE_TOSHOP:
			image = RenderableHolder.continue_button_Image;
			setSize(139, 50);
			break;

		case CONTINUE_NEXT_LV:
			image = RenderableHolder.continue_button_Image;
			setSize(139, 50);
			break;
		case EXIT_GAME:
			image = RenderableHolder.quit_Image;
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
				image = RenderableHolder.resume_button_Image;
			}
			setSize(24, 24);
			break;
		case HOME:
			image = RenderableHolder.home_button_Image;
			setSize(45, 45);
			break;
		case HELP:
			image = RenderableHolder.question_button_Image;
			setSize(30, 28);
			this.setOpacity(0.6);
		default:
			setSize(0, 0);
			break;
		}
		this.setImage(image);
		this.setFitHeight(height);
		this.setFitWidth(width);
	}

	/**
	 * This method is to initialize the event handler by its ImageButtonType (1)
	 * SOUND: Setup sound mute and unmute hadling by calling the method setUpSound()
	 * (2) PLAY: Setup input textfield and loading progress bar by calling
	 * setUpNameInput() (3) CONTINUE_TOSHOP: setup the shopePane by calling
	 * setUpContinueToShop() method (4) CONTINUE_NEXT_LV: initiate the next level
	 * game by calling setUpContinueNextLv() (5) EXIT_GAME: exit the game by calling
	 * setGameExit() (6) BUY: setup the process buying item by calling
	 * setUpBuyItem(), this will deal with the shopManager class. (7) PAUSE: Setup
	 * the pause screen in GameScene by calling setUpPauseScreen() (8) HOME: back to
	 * main menu (viewManager) by calling setBackHome() (9) HELP: launching the
	 * HowToPlay SubScene by calling setUpHowToPlay()
	 */
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
				case CONTINUE_TOSHOP:
					setUpContinueToShop();
					break;
				case CONTINUE_NEXT_LV:
					setUpContinueNextLv();
					break;
				case EXIT_GAME:
					setGameExit();
					break;
				case BUY:
					setUpBuyItem();
					break;
				case PAUSE:
					setUpPauseScreen();
					break;
				case HOME:
					setBackToHome();
					break;
				case HELP:
					setUpHowToPlay();
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

	/**
	 * this method is to setup the name input by getting the entered name by calling
	 * static method EnterNameScene.getEnteredName() check the entered name format
	 * by calling NameInputUtility.checkEnteredName(name). If the entered name is in
	 * correct format, add to CSV file using CSVUtility.appendToCSV(). Dispose the
	 * ImageButton. Stop the viewManager scene by calling
	 * ViewManager.stopViewManager(). Then start loading new game scene by calling
	 * two method, EnterNameScene.setupLoading() and EnterNameScene.startGameScene()
	 */
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

	/**
	 * this method is to set up the sound preference (mute and unmute) Negating the
	 * boolean of the GameManager.isMute and initialize the new button with the
	 * correct status. Check if the ViewManager and GameScene is visible or not. If
	 * yes, setIsPlayingThemeSong to true and play Theme Song by calling
	 * methods,setIsPlayingThemeSong() and playThemeSong()
	 */
	private void setUpSound() {
		GameManager.setMute(!GameManager.isMute());
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

	/**
	 * This methods is to setup the shopPane. By removing first, the latest item in
	 * GameManager.AppRoot which are the EndlingLevelTextBox, and add new shopPane.
	 * Setup the MoneyBox by calling MoneyBox.updateMoneyText() method.
	 */
	private void setUpContinueToShop() {
		GameManager.getAppRoot().getChildren().remove(GameManager.getAppRoot().getChildren().size() - 1);
		GameManager.getAppRoot().getChildren().add(new ShopPane());
		MoneyBox.updateMoneyText();
	}

	/**
	 * This methods is to setup the next game level by initialize the next level by
	 * Calling the GameScene.initializeNextLevel() method.
	 */
	private void setUpContinueNextLv() {
		if (!GameManager.isPressedNextLv()) {
			GameManager.setPressedNextLv(true);
			GameScene.initializeNextLevel();
		}
	}

	/**
	 * This method is to setup the buy item process by the ImageButton's altText
	 * There are 4 types of BUY button altText. Getting the item's price using
	 * Getters ShopItem.getXXXXPrice() Then call the static method
	 * ShopManager.buyShopItem(itemPrice) to buy the item, this will return status
	 * of buying item as a boolean. If buying status is true, will automatically
	 * upgrade player's stat by the item type that player buy. case "run" will
	 * upgrade the player run speed by calling GameManager.makeRunFast(). case
	 * "jump" will call makeJumpHigh(). case "time" will increase the map time by
	 * calling GameManager.upgradeMapTime() and case "lp" will upgrade player HP by
	 * calling GameManager.upgradePlayerMaxHP()
	 */
	private void setUpBuyItem() {
		switch (altText) {
		case "run":
			if (ShopManager.buyShopItem(ShopItem.getRunPrice())) {
				GameManager.makeRunFast();
			}
			break;
		case "jump":
			if (ShopManager.buyShopItem(ShopItem.getJumpPrice())) {
				GameManager.makeJumpHigh();
			}
			break;
		case "time":
			if (ShopManager.buyShopItem(ShopItem.getTimePrice())) {
				GameManager.upgradeMapTime();
			}
			break;
		case "lp":
			if (ShopManager.buyShopItem(ShopItem.getLpPrice())) {
				GameManager.upgradePlayerMaxHP();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * This method deals with the GameSubScene by calling the method
	 * GameScene.getPauseGameLeaderboard().moveSubScene(alternativeWord) where
	 * alternativeWord will be "pauseGamePressed" and "pauseGameUnpressed" Will
	 * check for the condition to move the subscene in and out correctly. Also make
	 * changes of timer start and stop when pressing this button by calling
	 * GameScene.getTimer().start() and GameScene.getTimer().stop() methods.
	 */
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

	/**
	 * This method deals with the GameSubScene by calling the method
	 * ViewManager.getHowToPlay().moveSubScene(alternativeWord) where
	 * alternativeWord will be "howToPlayPressed" and "howToPlayUnpressed" Will
	 * check for the condition to move the subscene in and out correctly. Negative
	 * isShowHowToPlay field in viewManager by calling
	 * ViewManager.setShowHowToPlay(!ViewManager.isShowHowToPlay())
	 */
	private void setUpHowToPlay() {
		if (ViewManager.isShowHowToPlay()) {
			ViewManager.getHowToPlay().moveSubScene("howToPlayPressed");
		} else {
			ViewManager.getHowToPlay().moveSubScene("howToPlayUnpressed");
		}
		ViewManager.setShowHowToPlay(!ViewManager.isShowHowToPlay());
	}

	/**
	 * Method for setting back the scene to the viewManager.mainScene (main menu) by
	 * calling method ViewManager.getMainScene().
	 */
	private void setBackToHome() {
		EnterNameScene.setScene(ViewManager.getMainScene());
	}

	/**
	 * This method is Setting the Stage close (exit the game) by calling static
	 * method, GameScene.getStage().close()
	 */
	private void setGameExit() {
		GameScene.getStage().close();
	}

	// =================== GETTER / SETTER ======================

	/**
	 * This method is the setter that set the size of this ImageButton
	 * 
	 * @param w
	 * @param h
	 */
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}

	/**
	 * This method is the getter to get the altText of the ImageButtonType.BUY
	 * Button
	 * 
	 * @return String of altText
	 */
	public String getAltText() {
		return this.altText;
	}

	/**
	 * This method is the setter to set the altText of the ImageButtonType.BUY
	 * Button
	 * 
	 * @param altText
	 */
	public void setAltText(String altText) {
		this.altText = altText;
	}

	/**
	 * This public static method is for pressing the playButton by calling the
	 * setUpNameInput() method.
	 */
	public void playButtonPress() {
		setUpNameInput();
	}

}
