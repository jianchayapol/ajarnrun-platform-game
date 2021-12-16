package view;

import sharedObject.*;
import application.logic.GameManager;
import gui.button.ImageButton;
import gui.button.ImageButtonType;
import gui.button.MainButton;
import gui.element.HowToPlayBox;
import gui.element.PauseGameLeaderBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * ViewManager is the class that control the first state of the game that you know as Main Menu.
 * This class basically create a main scene and a main stage, then pass it to Main class.
 * This class is also the class that contain all buttons that you see in Main Menu, like New Game, Leaderboard, about us etc.
 * The way this class work is that it contain all buttons and implement event handlers to each of them.
 * Some buttons might initialize new object or scene, but there's one button that doesn't do that, that is mute button which call a method from AutioLoader class.
 * @author Mos
 *
 */
public class ViewManager {
	/**
	 * Screen's height
	 */
	private static final int HEIGHT = 600;
	/**
	 * Screen's width
	 */
	private static final int WIDTH = 800;
	/**
	 * mainPane is an AnchorPane that will be in mainScene. You can think of it as a background pane.
	 */
	private AnchorPane mainPane;
	/**
	 * mainScene is a scene that will be setted in mainStage.
	 */
	private static Scene mainScene;
	/**
	 * mainStage is a stage that will be initialized in this class. Its pointer will be shared with parimaryStage in Main class.
	 */
	private static Stage mainStage;
	/**
	 * Background (bg) is an ImageView that we will initiailze and use it as a background for this scene.
	 */
	private ImageView bg;
	/**
	 * NewGameButton is a button that we can click to go to next state of the game.
	 * The next state that we can go to from clicking newGameButton is EnterNameScene
	 */
	private MainButton newGameButton;
	/**
	 * leaderBoardButton is a button that when we click, a leaderboard subscene will show up, if we click at this button again, the subscene will move back.
	 */
	private MainButton leaderBoardButton;
	/**
	 * exitButton is a button which we click when we want to exit the game.
	 */
	private MainButton exitButton;
	/**
	 * info is a button that when we click, an about us sebscene will show up, if we click at this button again, the subscene will move back.
	 */
	private MainButton info;
	/**
	 * muteButton is a button which we click when we want to turn off the music, and when you want to turn on the music, simply click this button again.
	 */
	private ImageButton muteButton;
	/**
	 * howToButton is button that when we click, how to play subscene will show up and tell us how to play the game.
	 */
	private ImageButton howToButton;
	/**
	 * A boolean that check if the background music is playing or not.
	 */
	private static boolean isPlayingThemeSong;
	/**
	 * A boolean that check if the main view is visible or not.
	 * This field mainly use for the logic about stage's current scene.
	 */
	private static boolean isMainViewVisible;
	/**
	 * A boolean to check if leaderboard is being shown or not (since leaderboard will be shown when we click the button).
	 */
	private boolean isLeaderBoardPressed;
	/**
	 * A boolean to check if aboutus subscene is being shown or not (since aboutus subscene will be shown when we click the button).
	 */
	private boolean isInfoPressed;
	/**
	 * A bolean to check if how to play subscene is being shown or not (since how to play subscene will be shown when we click the button).
	 */
	private static boolean isShowHowToPlay;
	/**
	 * aboutUs is a GameSubScene object (extends JavaFx SubScene) that will be shown when when we click info button.
	 */
	private GameSubScene aboutUs;
	/**
	 * leaderBoard is a GameSubScene object (extends JavaFx SubScene) that will be shown when we click info button.
	 */
	private GameSubScene leaderBoard;
	/**
	 * howToPlay is a GameSubScene object (extends JavaFx SubScene) that will be shown when we click info button.
	 */
	private static GameSubScene howToPlay;
	/**
	 * gameScene is a GameScene object (extends JavaFx Scene) that we be initiallize when we enter the third state of the game.
	 */
	private static GameScene gameScene;
	/**
	 * enterNameScene is and EnterNameScene object (extends JavaFx Scene) that the player will see when they click newGameButton.
	 * This whole scene is the only scene in the second state of the game.
	 */
	private static EnterNameScene enterNameScene;
	/**
	 * A constructor of ViewManager class.
	 * When this class is initialize, it starts by initialize new AnchorPane.
	 * Then it sets initial boolean values, then, it create a logo and also all buttons and implement their event handler.
	 * Lastly, it initializes mainScene and Stage.
	 */
	public ViewManager() {
		this.mainPane = new AnchorPane();
		setBackgroundImage(RenderableHolder.entrance_background_Image);

		setIsVisible(true);
		setIsPlayingThemeSong(true);
		playThemeSong();

		// Create Logo
		createLogo();

		// Create all buttons
		createMainButton();
		createInfoButton();
		createSettingButton();

		// Create SubScenes
		createAboutUsSubScene();
		createLeaderBoardSubScene();
		createSubSceneHowToPlay();
		
		// Implement buttons' event listeners
		implementNewGameEventListener();
		implementLeaderboardEventListener();
		implementExitEventListener();
		implementAboutUsEventListener();

		// Create main scene and stage
		this.mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		this.mainStage = new Stage();
		this.mainStage.setScene(mainScene);
		this.mainStage.setResizable(false);
		
	}

	private void createSettingButton() {
		muteButton = new ImageButton(ImageButtonType.SOUND);
		howToButton = new ImageButton(ImageButtonType.HELP);
		HBox hb = new HBox(10);
		howToButton.setSize(20, 20);
		hb.setPadding(new Insets(15));
		hb.getChildren().addAll(howToButton,muteButton);
		hb.setAlignment(Pos.CENTER);
		this.mainPane.getChildren().add(hb);
	}

	public static void playThemeSong() {
		AudioClip themeSong = AudioLoader.Entrance_Theme_Song;
		if (isPlayingThemeSong && !GameManager.isMute()) {
			themeSong.setVolume(0.5);
			themeSong.setCycleCount(AudioClip.INDEFINITE);
			themeSong.play();
		} else {
			themeSong.stop();
		}
	}
	
	public static GameScene getGameScene() {
		return gameScene;
	}
	
	private void createMainButton() {
		double initialHeight = 20;

		// New Game, Load Game, Exit
		newGameButton = new MainButton("New Game");
		leaderBoardButton = new MainButton("Leaderboard");
		exitButton = new MainButton("Exit");
		newGameButton.setLayoutY(HEIGHT - (3 * initialHeight) - newGameButton.getPrefHeight()
				- leaderBoardButton.getPrefHeight() - exitButton.getPrefHeight());
		newGameButton.setLayoutX(20.00);
		leaderBoardButton.setLayoutY(newGameButton.getLayoutY() + initialHeight + newGameButton.getPrefHeight());
		leaderBoardButton.setLayoutX(20.00);
		exitButton.setLayoutY(leaderBoardButton.getLayoutY() + initialHeight + leaderBoardButton.getPrefHeight());
		exitButton.setLayoutX(20.00);

		// Set initial isPressed boolean values
		setIsLeaderBoardPressed(false);

		// Add to pane
		mainPane.getChildren().add(newGameButton);
		mainPane.getChildren().add(leaderBoardButton);
		mainPane.getChildren().add(exitButton);

	}

	private void setBackgroundImage(Image bgImg) {
		this.bg = new ImageView(bgImg);
		this.bg.setFitHeight(HEIGHT);
		this.bg.setFitWidth(WIDTH);
		mainPane.getChildren().add(this.bg);
	}

	private void createLogo() {
		ImageView logo = new ImageView(RenderableHolder.logo);
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				logo.setEffect(new DropShadow());
			}
		});
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				logo.setEffect(null);
			}
		});
		mainPane.getChildren().add(logo);
	}

	private void createInfoButton() {
		double initialHeight = 20;
		// About Us
		info = new MainButton("About Us");
		info.setLayoutX(WIDTH - initialHeight - info.getPrefWidth());
		info.setLayoutY(HEIGHT - initialHeight - info.getPrefHeight());
		mainPane.getChildren().add(info);
		setIsInfoPressed(false);
	}

	private void createAboutUsSubScene() {
		this.aboutUs = new GameSubScene("/image/aboutUsDemo2.png", "infoButton", 350, 560);
		this.mainPane.getChildren().add(aboutUs);
	}

	private void createLeaderBoardSubScene() {
		leaderBoard = new GameSubScene("/image/leaderboardDemo2.png", "leaderBoard", 350, 560);
		this.mainPane.getChildren().add(leaderBoard);
	}

	private void implementNewGameEventListener() {
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				enterNameScene = new EnterNameScene(mainStage);
				mainStage.setScene(enterNameScene);
			}
		});
		newGameButton.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.N && e.isControlDown()) {
				enterNameScene = new EnterNameScene(mainStage);
				mainStage.setScene(enterNameScene);
			}
		});
	}

	private void implementLeaderboardEventListener() {
		leaderBoardButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (ViewManager.this.getIsLeaderBoardPressed()) {
					leaderBoard.moveSubScene("leaderBoardPressed");
					ViewManager.this.setIsLeaderBoardPressed(false);
				} else {
					leaderBoard.moveSubScene("leaderBoardUnpressed");
					ViewManager.this.setIsLeaderBoardPressed(true);
					if (ViewManager.this.getIsInfoPressed()) {
						aboutUs.moveSubScene("infoButtonPressed");
						ViewManager.this.setIsInfoPressed(false);
					}
				}
			}
		});
	}

	private void implementExitEventListener() {
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ViewManager.mainStage.close();
			}
		});
	}

	private void implementAboutUsEventListener() {
		info.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (ViewManager.this.getIsInfoPressed()) {
					aboutUs.moveSubScene("infoButtonPressed");
					ViewManager.this.setIsInfoPressed(false);
				} else {
					aboutUs.moveSubScene("infoButtonUnpressed");
					ViewManager.this.setIsInfoPressed(true);
					if (ViewManager.this.getIsLeaderBoardPressed()) {
						leaderBoard.moveSubScene("leaderBoardPressed");
						ViewManager.this.setIsLeaderBoardPressed(false);
					}
				}
			}
		});
	}

	private void createSubSceneHowToPlay() {
		ViewManager.howToPlay = new GameSubScene(new HowToPlayBox(),"howToPlay",790,540);
		this.mainPane.getChildren().add(howToPlay);
	}
	
	public static Stage getStage() {
		return mainStage;
	}

	public AnchorPane getMainPane() {
		return mainPane;
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public void setIsLeaderBoardPressed(boolean isPressed) {
		this.isLeaderBoardPressed = isPressed;
	}

	public boolean getIsLeaderBoardPressed() {
		return this.isLeaderBoardPressed;
	}

	public void setIsInfoPressed(boolean isPressed) {
		this.isInfoPressed = isPressed;
	}

	public boolean getIsInfoPressed() {
		return isInfoPressed;
	}

	public MainButton getNewGameButton() {
		return newGameButton;
	}

	public MainButton getLoadGameButton() {
		return leaderBoardButton;
	}

	public MainButton getExitButton() {
		return exitButton;
	}

	public MainButton getInfo() {
		return info;
	}

	public static int getScreenHeight() {
		return HEIGHT;
	}

	public static int getScreenWidth() {
		return WIDTH;
	}

	public static boolean isVisible() {
		return isMainViewVisible;
	}

	public static void setIsVisible(boolean isVisible) {
		ViewManager.isMainViewVisible = isVisible;
	}

	public static boolean isPlayingThemeSong() {
		return isPlayingThemeSong;
	}

	public static void setIsPlayingThemeSong(boolean isPlayingThemeSong) {
		ViewManager.isPlayingThemeSong = isPlayingThemeSong;
	}

	public static boolean isShowHowToPlay() {
		return isShowHowToPlay;
	}

	public static void setShowHowToPlay(boolean isShowHowToPlay) {
		ViewManager.isShowHowToPlay = isShowHowToPlay;
	}

	public static void stopViewManager() {
		ViewManager.setIsVisible(false);
		AudioLoader.Entrance_Theme_Song.stop();
		ViewManager.setIsPlayingThemeSong(false);
	}

	public static GameSubScene getHowToPlay() {
		return howToPlay;
	}

	public static void setHowToPlay(GameSubScene howToPlay) {
		ViewManager.howToPlay = howToPlay;
	}
}
