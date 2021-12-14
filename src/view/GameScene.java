package view;

import application.logic.GameManager;
import gui.element.GameHUD;
import gui.element.LevelEndingBox;
import gui.element.LevelEndingType;
import gui.element.PauseGameLeaderBox;
import gui.element.ShopPane;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import sharedObject.AudioLoader;

public class GameScene extends Scene {

	private static GameHUD gameHud;
	private static Stage stage;
	private static double timeRemaining;
	private static final double TIME_TICK = 0.01666666666667;
	private static boolean isPlayingThemeSong;
	private static boolean isVisible = true;
	private static boolean isPause = false;
	private static AnchorPane pane;
	private GameSubScene shop;
	private GameSubScene pauseGameLeaderboard;
	private GameSubScene levelEnding;
	
	// test field
	private static double timeMapSecond;
	public GameScene(Pane parent, Stage primaryStage) {
		super(parent);
		initializeEventHandler();
		setUpStage(primaryStage);
		setGameHud(GameManager.getUIRoot());
		setPauseGameLeaderboard(GameManager.getUIRoot());
		runScene();
		stage = primaryStage;
	}
	
	private void initializeEventHandler() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				GameManager.setKeysValue(event.getCode(), true);
			}
		});
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				GameManager.setKeysValue(event.getCode(), false);
			}
		});
	}
	
	private void setUpStage(Stage primaryStage) {
		setPauseLayout();
		pane = ((AnchorPane)this.getRoot());
		primaryStage.setTitle("Ajarn Ja Run!");
		primaryStage.setScene(this);
		setAudio();
	}
	
	private void setGameHud(Pane pane) {
		gameHud = new GameHUD();
		pane.getChildren().add(gameHud);
	}
	
	private void setPauseGameLeaderboard(Pane pane) {
		pane.getChildren().add(pauseGameLeaderboard);
	}
	
	private void runScene() {
		setTimeMapSecond(120);
		timeRemaining = getTimeMapSecond();
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				GameManager.update();
				GameHUD.setProgress(GameHUD.getTimerProgressBar(), timeRemaining, timeMapSecond);
				timeRemaining -= TIME_TICK;
				if(timeRemaining<=0) {
					GameHUD.setProgress(GameHUD.getTimerProgressBar(), 0, timeMapSecond);
					// level failed
				}
				
				if (GameManager.getIsLevelFinish()) {
					this.stop();
					GameManager.setUpNextLevel();
					GameScene.this.setGameHud(GameManager.getUIRoot());
					GameScene.this.setPauseGameLeaderboard(GameManager.getUIRoot());
					GameScene.setTimeMapSecond(120);
					this.start();
				}
//				if(success) {
//					// level complete
//					return;
//				}
			}
		};
		timer.start();
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	private void setPauseLayout() {
		pauseGameLeaderboard.setLayoutX(50);
		pauseGameLeaderboard.setLayoutY(50);
	}
	
	private void setAudio() {
		GameScene.isPlayingThemeSong = !GameManager.getIsMute();
		playThemeSong();
	}

	public static boolean isPlayingThemeSong() {
		return isPlayingThemeSong;
	}

	public static void setIsPlayingThemeSong(boolean isPlayingThemeSong) {
		GameScene.isPlayingThemeSong = isPlayingThemeSong;
	}

	public static boolean isVisible() {
		return GameScene.isVisible;
	}

	public static void playThemeSong() {
		AudioClip themeSong = AudioLoader.Game_Theme_Song;
		if (isPlayingThemeSong && !GameManager.getIsMute()) {
			themeSong.setVolume(0.45);
			themeSong.setCycleCount(AudioClip.INDEFINITE);
			themeSong.play();
		} else {
			themeSong.stop();
		}
	}

	public static boolean getIsPause() {
		return isPause;
	}

	public static void setIsPause(boolean isPause) {
		GameScene.isPause = isPause;
	}
	
//	public static void updatePauseScreen() {
//		if(GameScene.isPause) {
//			pane.getChildren().add(pauseGameLeaderboard);
//		}
//		else {
//			pane.getChildren().remove(pane.getChildren().size()-1);
//		}
//	}
	
	public static void setTimeMapSecond(double second) {
		timeMapSecond = second;
	}
	
	public static double getTimeMapSecond() {
		return timeMapSecond;
	}
	
	private void createShopSubScene() {
		this.shop = new GameSubScene(new ShopPane(), "shop", 350, 560);
		GameManager.getAppRoot().getChildren().add(shop);
	}
	
	private void createPauseGameLeaderboardSubScene() {
		this.pauseGameLeaderboard = new GameSubScene(new PauseGameLeaderBox(), "pauseGameLeaderboard", 300, 300);
		GameManager.getAppRoot().getChildren().add(pauseGameLeaderboard);		
	}
	
	private void createLevelEndingSubScene(LevelEndingType type) {
		this.levelEnding = new GameSubScene(new LevelEndingBox(type), "levelEnding", 300, 300);
		GameManager.getAppRoot().getChildren().add(levelEnding);
	}
}
