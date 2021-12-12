package view;

import sharedObject.*;
import application.logic.GameController;
import gui.button.ImageButton;
import gui.button.ImageButtonType;
import gui.button.MainButton;
import gui.element.LevelPassBox;
import gui.element.LevelPassType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewManager {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private ImageView bg;
	private MainButton newGameButton;
	private MainButton leaderBoardButton;
	private MainButton exitButton;
	private MainButton info;
	private ImageButton muteButton;
	
	private static boolean isPlayingThemeSong;
	private static boolean isVisible;

	private boolean isLeaderBoardPressed;
	private boolean isInfoPressed;

	private MainViewSubScene aboutUs;
	private MainViewSubScene leaderBoard;

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
		createMuteButton();

		// Create SubScenes
		createAboutUsSubScene();
		createLeaderBoardSubScene();

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

	private void createMuteButton() {
		muteButton = new ImageButton(ImageButtonType.SOUND);
		muteButton.setLayoutX(20);
		muteButton.setLayoutY(20);
		this.mainPane.getChildren().add(muteButton);
	}

	public static void playThemeSong() {
		AudioClip themeSong = AudioLoader.Entrance_Theme_Song;
		if (isPlayingThemeSong && !GameController.IsMute()) {
			themeSong.setVolume(0.5);
			themeSong.setCycleCount(AudioClip.INDEFINITE);
			themeSong.play();
		} else {
			themeSong.stop();
		}
	}

	private void createMainButton() {
		double initialHeight = 20;

		// New Game, Load Game, Exit
		newGameButton = new MainButton("New Game");
		leaderBoardButton = new MainButton("Leaderboard");
		exitButton = new MainButton("Exit");
		newGameButton.setLayoutY(HEIGHT - (3 * initialHeight) - newGameButton.getPrefHeight() - leaderBoardButton.getPrefHeight() - exitButton.getPrefHeight());
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
		this.aboutUs = new MainViewSubScene("/image/aboutUsDemo2.png", "infoButton", 350, 560);
		this.mainPane.getChildren().add(aboutUs);
	}

	private void createLeaderBoardSubScene() {
		leaderBoard = new MainViewSubScene("/image/leaderboardDemo2.png", "leaderBoard", 350, 560);
		this.mainPane.getChildren().add(leaderBoard);
	}

	private void implementNewGameEventListener() {
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				EnterNameScene enterNameScene = new EnterNameScene(mainStage);
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
				ViewManager.this.getStage().close();
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
	
	public Stage getStage() {
		return mainStage;
	}

	public AnchorPane getMainPane() {
		return mainPane;
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
		return isVisible;
	}

	public static void setIsVisible(boolean isVisible) {
		ViewManager.isVisible = isVisible;
	}

	public static boolean isPlayingThemeSong() {
		return isPlayingThemeSong;
	}

	public static void setIsPlayingThemeSong(boolean isPlayingThemeSong) {
		ViewManager.isPlayingThemeSong = isPlayingThemeSong;
	}

}
