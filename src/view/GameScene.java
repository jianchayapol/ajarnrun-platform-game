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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.level.Level;
import sharedObject.AudioLoader;
/**
 * GameScene class is a JavaFx Scene that is created for the gameplay state (after you press start button in EnterNameScene).
 * This class run itself when the constructor is call and also call GameManager's static method to update the game realtime.
 * As the game continue, the player will still be in this scene event when we change the game level, this class' object will not be created more than onece.
 * @author Mos
 *
 */
public class GameScene extends Scene {
	/**
	 * gameHud is a GameHUD's object that contains all the Game HUD, such as, HP, time, pause button etc.
	 */
	private static GameHUD gameHud;
	/**
	 * JavaFx stage
	 */
	private static Stage stage;
	/**
	 * A short period of time in one frame
	 */
	private static final double TIME_TICK = 0.01666666666667;
	/**
	 * A boolean to check if the background music is playing or not
	 */
	private static boolean isPlayingThemeSong;
	/**
	 * A boolean to check if this scene (GameScene) is visible to the player or not
	 */
	private static boolean isVisible = true;
	/**
	 * A boolean to check if the game is paused or not
	 */
	private static boolean isPause = false;
	/**
	 * A GameSubScene that will be shown when player pause the game.
	 */
	private static GameSubScene pauseGameLeaderboard;
	/**
	 * Time that player has within a level
	 */
	private static double timeMapSecond;
	/**
	 * The remaining time the player has
	 */
	private static double timeRemained;
	/**
	 * An AnimationTimer that is used to run a loop for gameplay
	 */
	private static AnimationTimer timer;
	/**
	 * Screen's height
	 */
	private static final int HEIGHT = 600;
	/**
	 * Screen's width
	 */
	private static final int WIDTH = 800;
	/**
	 * As the game is a on way game, either way the game ends, the player cannot return to main menu. So, this constructor can take a JavaFx Stage as a parameter and set itself as a scene.
	 * It starts with call super constructor an pass parent pane. Then it initializeEventHandler for this scene using initializeEventHandler method.
	 * The constructor then call setUpStage method to set itself as a scene and also set stage's title.
	 * Then it initailize pauseGameLeaderboard using createPauseGameLeaderboardSubScene method.
	 * Lastly, the constructor run itself with runScene method.
	 * @param parent This class' object parent node
	 * @param primaryStage ViewManager's primaryStage
	 */
	public GameScene(Pane parent, Stage primaryStage) {
		super(parent);
		initializeEventHandler();
		setUpStage(primaryStage);
		setGameHud(GameManager.getUIRoot());
		createPauseGameLeaderboardSubScene();
		runScene();
		stage = primaryStage;
	}
	/**
	 * Implement Scene(itself)'s event handlers to set value of GameManager's keys HashMap, so the game will know which KeyCode is being pressed at a time.
	 */
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
	/**
	 * Set stage's title and set stage's scene using this class' object
	 * @param primaryStage The same primaryStage that has been passed since the ViewManager class
	 */
	private void setUpStage(Stage primaryStage) {
		primaryStage.setTitle("Ajarn Ja Run!");
		primaryStage.setScene(this);
		setAudio();
	}
	/** 
	 * Initialize gameHud and add it to pane
	 * @param pane The parent node that will add gameHude inside
	 */
	private static void setGameHud(Pane pane) {
		gameHud = new GameHUD();
		pane.getChildren().add(gameHud);
	}
	
	/**
	 * Method that implements with the AnimationTimer to run scene of the game level by level.
	 * Setting up time of the map and time remained to 120 seconds.
	 * creating new AnimationTimer, initialize timeUpCounter and levelCompleteCounter to be 0.
	 * In each time of ticker, checking the status of 4 booleans:
	 * Reduce timeRemained by TIME_TICK (0.01666666666667)
	 * and call method to update GameManager.update(), MoneyBox.updateMoneyText() and updateHUD()
	 * Condition for being isTimeUp is timeRemained is 0 and game is not finish, calling method GameManager.isLevelFinish()
	 * Condition for being isWasted is player dead, calling method GameManager.isDead()
	 * Condition for being isMissingBook is not collect all the book, calling method GameManager.isMissingBook()
	 * Condition for being isWinGame is win all the level GameManager.getLevelCount() == Level.ALL_LEVEL.length - 1
	 * 
	 * if any boolean match the statement that Level Failed (isTimeUp or isMissingBook or isWasted)
	 * will directly show LevelEnding textbox for 150ms and continue to levelFailed levelEndingBox.
	 * if Level Completed but not the last level, will directly goes to shopPane 
	 * We use method, GameManager.getAppRoot().getChildren().add() to add new Pane
	 * and use method, removeAppRootLatestItem() to remove the latest pane added.
	 */
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

				boolean isTimeUp = timeRemained <= 0 && !GameManager.isLevelFinish();
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
				if (GameManager.isLevelFinish() && !isMissingBook) {
					
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
	/**
	 * Remove GameManager's appRoot last item.
	 */
	private static void removeAppRootLatestItem() {
		GameManager.getAppRoot().getChildren().remove(GameManager.getAppRoot().getChildren().size() - 1);
	}
	/**
	 * Stop the AnimationTimer and call GameManager.setUpNextLevel method to initialize new level.
	 * Change level label using GameHud.setLevelLabel method
	 * setGameHud using GameScene.setGameHud(GameManager.getUIRoot())
	 * reset timeMapSecond to 90 seconds
	 * and then, restart the AnimationTimer
	 */
	public static void initializeNextLevel() {
		timer.stop();
		GameManager.setUpNextLevel();
		GameHUD.setLevelLabel();
		GameScene.setGameHud(GameManager.getUIRoot());
		GameScene.setTimeMapSecond(90);
		timeRemained = getTimeMapSecond();

		timer.start();
	}
	/**
	 * Update money lebel using GameHUD.setMoneyLabel()
	 * set time bar using GameHUD.setProgress(GameHUD.getTimerProgressBar(), timeRemained, timeMapSecond)
	 * set HP bar using GameHUD.setProgress(GameHUD.getHpProgressBar(), GameManager.getPlayerCurrentHP(), GameManager.getPlayerMaxHP())
	 */
	private static void updateHUD() {
		GameHUD.setMoneyLabel();
		GameHUD.setProgress(GameHUD.getTimerProgressBar(), timeRemained, timeMapSecond);
		GameHUD.setProgress(GameHUD.getHpProgressBar(), GameManager.getPlayerCurrentHP(), GameManager.getPlayerMaxHP());
	}
	/**
	 * stage public static getter
	 * @return stage
	 */
	public static Stage getStage() {
		return stage;
	}
	/**
	 * set isPlayingThemeSong to be opposite with GameManager's isMute value and then call playThemeSong method
	 */
	private void setAudio() {
		GameScene.isPlayingThemeSong = !GameManager.isMute();
		playThemeSong();
	}
	/**
	 * isPlayingThemeSong public static getter
	 * @return isPlayingThemeSong
	 */
	public static boolean isPlayingThemeSong() {
		return isPlayingThemeSong;
	}
	/**
	 * isPlayingThemeSong public static setter
	 * @param isPlayingThemeSong Used to set the value to isPlayingThemeSong
	 */
	public static void setIsPlayingThemeSong(boolean isPlayingThemeSong) {
		GameScene.isPlayingThemeSong = isPlayingThemeSong;
	}
	/**
	 * isVisible public static getter
	 * @return isVisible
	 */
	public static boolean isVisible() {
		return GameScene.isVisible;
	}
	/**
	 * Check if the level is muted or not, if not, player the theme song.
	 */
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
	/**
	 * isPause public static getter
	 * @return isPause
	 */
	public static boolean getIsPause() {
		return isPause;
	}
	/**
	 * isPause public static setter
	 * @param isPause Used to set the value of isPause
	 */
	public static void setIsPause(boolean isPause) {
		GameScene.isPause = isPause;
	}
	/**
	 * timeMapSecond public static setter
	 * @param second Used to assign the value to timeMapSecond
	 */
	public static void setTimeMapSecond(double second) {
		timeMapSecond = second;
	}
	/**
	 * timeMapSecond public static getter
	 * @return timeMapSecond
	 */
	public static double getTimeMapSecond() {
		return timeMapSecond;
	}
	/**
	 * Initialize pauseGameLeaderboard as a GameSubScene using the second constructor, with 800 as the width and 350 as the height.
	 * Then add it to GameManager's appRoot
	 */
	public static void createPauseGameLeaderboardSubScene() {
		GameScene.pauseGameLeaderboard = new GameSubScene(new PauseGameLeaderBox(), "pauseGameLeaderboard", 800, 350);
		GameManager.getAppRoot().getChildren().add(pauseGameLeaderboard);
	}
	/**
	 * gameHud public static getter
	 * @return gameHud
	 */
	public static GameHUD getGameHud() {
		return gameHud;
	}
	/**
	 * gameHud public static setter
	 * @param gameHud Used to share the same address (object) as the GameScene's gameHud
	 */
	public static void setGameHud(GameHUD gameHud) {
		GameScene.gameHud = gameHud;
	}
	/**
	 * timer (AnimationTimer) public static getter
	 * @return timer
	 */
	public static AnimationTimer getTimer() {
		return timer;
	}
	/**
	 * timer public static setter
	 * @param timer Used to share the same address (object) as the GameScene's timer
	 */
	public static void setTimer(AnimationTimer timer) {
		GameScene.timer = timer;
	}
	/**
	 * pauseGameLeaederboard public static getter
	 * @return pauseGameLeaderboard
	 */
	public static GameSubScene getPauseGameLeaderboard() {
		return pauseGameLeaderboard;
	}
	/**
	 * pauseGameLeaderboard public static setter
	 * @param pauseGameLeaderboard Used to share the same address (object) as the GameScene's pauseGameLeaderboard
	 */
	public static void setPauseGameLeaderboard(GameSubScene pauseGameLeaderboard) {
		GameScene.pauseGameLeaderboard = pauseGameLeaderboard;
	}

}
