package view;

import application.logic.GameManager;
import gui.element.GameHUD;
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
	private static StackPane pauseGameLeaderboard = new StackPane();
	private static AnchorPane pane;
	public GameScene(Pane parent, Stage primaryStage) {
		super(parent);
		initializeEventHandler();
		setUpStage(primaryStage);
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
		gameHud = new GameHUD();
		((AnchorPane)this.getRoot()).getChildren().add(gameHud);
		((AnchorPane)this.getRoot()).getChildren().add(pauseGameLeaderboard);
		setPauseLayout();
		pane = ((AnchorPane)this.getRoot());
		primaryStage.setTitle("Ajarn Ja Run!");
		primaryStage.setScene(this);
		setAudio();
	}
	
	private void runScene() {
		double mapTimeSecond = 120; // 2 minutes
		timeRemaining = mapTimeSecond;
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				GameManager.update();
				GameHUD.setProgress(GameHUD.getTimerProgressBar(), timeRemaining, mapTimeSecond);
				timeRemaining -= TIME_TICK;
				if(timeRemaining<=0) {
					GameHUD.setProgress(GameHUD.getTimerProgressBar(),0,mapTimeSecond);
					// level failed
					return;
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

	public static StackPane getPauseGameLeaderboard() {
		return pauseGameLeaderboard;
	}

	public static void setPauseGameLeaderboard(StackPane pauseGameLeaderboard) {
		GameScene.pauseGameLeaderboard = pauseGameLeaderboard;
	}

	public static boolean getIsPause() {
		return isPause;
	}

	public static void setIsPause(boolean isPause) {
		GameScene.isPause = isPause;
	}
	
	public static void updatePauseScreen() {
		if(GameScene.isPause) {
			pane.getChildren().add(pauseGameLeaderboard);
		}
		else {
			pane.getChildren().remove(pane.getChildren().size()-1);
		}
	}

}
