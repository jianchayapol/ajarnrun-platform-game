package view;

import application.logic.GameManager;
import gui.element.GameHUD;
import gui.element.LevelEndingBox;
import gui.element.LevelEndingType;
import gui.element.PauseGameLeaderBox;
import gui.element.ShopPane;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sharedObject.AudioLoader;

public class GameScene extends Scene {

	private static GameHUD gameHud;
	private static Stage stage;
	private static final double TIME_TICK = 0.01666666666667;
	private static boolean isPlayingThemeSong;
	private static boolean isVisible = true;
	private static boolean isPause = false;
	private static Pane shop;
	private static Pane levelEnding;
	private static GameSubScene pauseGameLeaderboard;
	private static double timeMapSecond;
	private static double timeRemained;
	private static AnimationTimer timer;
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	
	public GameScene(Pane parent, Stage primaryStage) {
		super(parent);
		initializeEventHandler();
		setUpStage(primaryStage);
		setGameHud(GameManager.getUIRoot());
		createPauseGameLeaderboardSubScene();
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
		primaryStage.setTitle("Ajarn Ja Run!");
		primaryStage.setScene(this);
		setAudio();
	}
	
	private void setGameHud(Pane pane) {
		gameHud = new GameHUD();
		pane.getChildren().add(gameHud);
	}
	
	private void runScene() {
		
		setTimeMapSecond(120);
		timeRemained = getTimeMapSecond();
		timer = new AnimationTimer() {
			public void handle(long now) {
				GameManager.update();
				GameHUD.setProgress(GameHUD.getTimerProgressBar(), timeRemained, timeMapSecond);
				timeRemained -= TIME_TICK;
				if(timeRemained<=0) {
					GameHUD.setProgress(GameHUD.getTimerProgressBar(), 0, timeMapSecond);
					// level failed
				}
				
				if (GameManager.getIsLevelFinish()) {
					this.stop();
					GameManager.setUpNextLevel();
					GameScene.this.setGameHud(GameManager.getUIRoot());
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
		//createShopSubScene();
	}
	
	public static Stage getStage() {
		return stage;
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
	
	public static void setTimeMapSecond(double second) {
		timeMapSecond = second;
	}
	
	public static double getTimeMapSecond() {
		return timeMapSecond;
	}
	
	private void createPauseGameLeaderboardSubScene() {
		GameScene.pauseGameLeaderboard = new GameSubScene(new PauseGameLeaderBox(), "pauseGameLeaderboard", 800,350);
		GameManager.getAppRoot().getChildren().add(pauseGameLeaderboard);		
	}
	
	public static GameHUD getGameHud() {
		return gameHud;
	}

	public static void setGameHud(GameHUD gameHud) {
		GameScene.gameHud = gameHud;
	}

	public static AnimationTimer getTimer() {
		return timer;
	}

	public static void setTimer(AnimationTimer timer) {
		GameScene.timer = timer;
	}

	public static GameSubScene getShop() {
		return shop;
	}

	public static void setShop(GameSubScene shop) {
		GameScene.shop = shop;
	}

	public static GameSubScene getPauseGameLeaderboard() {
		return pauseGameLeaderboard;
	}

	public static void setPauseGameLeaderboard(GameSubScene pauseGameLeaderboard) {
		GameScene.pauseGameLeaderboard = pauseGameLeaderboard;
	}

	public GameSubScene getLevelEnding() {
		return levelEnding;
	}

	public static void setLevelEnding(GameSubScene levelEnding) {
		GameScene.levelEnding = levelEnding;
	}
}
