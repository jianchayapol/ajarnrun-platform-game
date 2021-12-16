package view;

import application.logic.GameManager;
import gui.element.CongratsBox;
import gui.element.GameHUD;
import gui.element.LevelEndingBox;
import gui.element.LevelEndingType;
import gui.element.MoneyBox;
import gui.element.PauseGameLeaderBox;
import gui.element.EndingLevelTextBox;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.level.Level;
import sharedObject.AudioLoader;

public class GameScene extends Scene {

	private static GameHUD gameHud;
	private static Stage stage;
	private static final double TIME_TICK = 0.01666666666667;
	private static boolean isPlayingThemeSong;
	private static boolean isVisible = true;
	private static boolean isPause = false;

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

	private static void setGameHud(Pane pane) {
		gameHud = new GameHUD();
		pane.getChildren().add(gameHud);
	}
	
	private void runScene() {

		setTimeMapSecond(120);
		timeRemained = getTimeMapSecond();

		timer = new AnimationTimer() {

			int timeUpCounter = 0;
			int levelCompleteCounter = 0;

			public void handle(long now) {
				GameManager.update();
				updateHUD();
				timeRemained -= TIME_TICK;

				boolean isTimeUp = timeRemained <= 0 && !GameManager.getIsLevelFinish();
				boolean isWasted = GameManager.isDead();
				boolean isMissingBook = GameManager.isMissingBook();//
				boolean isWinGame = GameManager.getLevelCount() == Level.ALL_LEVEL.length - 1;

				// ------------------------ Level Failed ------------------------------
				if (isTimeUp || isMissingBook || isWasted) {
					String altText = "";
					if (isTimeUp)
						altText = "timeUp";
					else if (isMissingBook)
						altText = "notebook";
					else if (isWasted)
						altText = "wasted";

					GameHUD.setProgress(GameHUD.getTimerProgressBar(), 0, timeMapSecond);
					timeUpCounter++;
					if (timeUpCounter == 1) {
						GameManager.getAppRoot().getChildren().add(new EndingLevelTextBox(altText));
					}
					if (timeUpCounter >= 150) {
						this.stop();
						removeAppRootLatestItem();
						GameManager.getAppRoot().getChildren().add(new LevelEndingBox(LevelEndingType.FAILED));
					}
				}
				// ------------------------ Level Completed ------------------------------
				if (GameManager.getIsLevelFinish() && !isMissingBook) {
					
					GameManager.updateLeaderboard();
					MoneyBox.updateMoneyText();
					levelCompleteCounter++;
					if (levelCompleteCounter == 1) {
						GameManager.getAppRoot().getChildren().add(new EndingLevelTextBox("complete"));
					}
					if (levelCompleteCounter >= 150) {
						this.stop();
						removeAppRootLatestItem();
						MoneyBox.updateMoneyText();
						// ------------------------ Complete Last Level -----------------------
						if (isWinGame) {
							GameManager.getAppRoot().getChildren().add(new CongratsBox());
						}
						// ------------------------ Level Complete ------------------------------
						else {
							GameManager.getAppRoot().getChildren().add(new LevelEndingBox(LevelEndingType.COMPLETED));
						}
					}
				}
			}
		};
		timer.start();

	}

	private static void removeAppRootLatestItem() {
		GameManager.getAppRoot().getChildren().remove(GameManager.getAppRoot().getChildren().size() - 1);
	}

	public static void initializeNextLevel() {
		timer.stop();
		GameManager.setUpNextLevel();
		GameHUD.setLevelLabel();
		GameScene.setGameHud(GameManager.getUIRoot());
		GameScene.setTimeMapSecond(90);
		timeRemained = getTimeMapSecond();

		timer.start();
	}

	private static void updateHUD() {
		GameHUD.setMoneyLabel();
		GameHUD.setProgress(GameHUD.getTimerProgressBar(), timeRemained, timeMapSecond);
		GameHUD.setProgress(GameHUD.getHpProgressBar(), GameManager.getPlayerCurrentHP(), GameManager.getPlayerMaxHP());
	}

	public static Stage getStage() {
		return stage;
	}

	private void setAudio() {
		GameScene.isPlayingThemeSong = !GameManager.isMute();
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
		if (isPlayingThemeSong && !GameManager.isMute()) {
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

	public static void createPauseGameLeaderboardSubScene() {
		GameScene.pauseGameLeaderboard = new GameSubScene(new PauseGameLeaderBox(), "pauseGameLeaderboard", 800, 350);
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

	public static GameSubScene getPauseGameLeaderboard() {
		return pauseGameLeaderboard;
	}

	public static void setPauseGameLeaderboard(GameSubScene pauseGameLeaderboard) {
		GameScene.pauseGameLeaderboard = pauseGameLeaderboard;
	}

}
