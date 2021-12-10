package view;

import button.MainButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewManager {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Image bgImg;
	private ImageView bg;
	private MainButton newGameButton;
	private MainButton leaderBoardButton;
	private MainButton exitButton;
	private MainButton info;
	private boolean isLeaderBoardPressed;
	private boolean isInfoPressed;
	private AjarnRunPartialSubScene aboutUs;
	private AjarnRunPartialSubScene leaderBoard;
	private AjarnRunLevelSubScene newGame;
	
	public ViewManager() {
		this.mainPane = new AnchorPane();
		setBackgroundImage("/mainSceneBackground_withoutLogo_fixed.png");
		
		// Create Logo
		createLogo();
		
		// Create all buttons
		createMainButton();
		createInfoButton();
		
		// Create SubScenes
		createAboutUsSubScene();
		createLeaderBoardSubScene();
		
		// Implement buttons' event listeners
		implementLeaderBoardEventListener();
		implementExitEventListener();
		implementAboutUsEventListener();
		
		// Create main scene and stage
		this.mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		this.mainStage = new Stage();
		this.mainStage.setScene(mainScene);
		this.mainStage.setResizable(false);
	}
	public Stage getStage() {
		return mainStage;
	}
	public AnchorPane getMainPane() {
		return mainPane;
	}
	
	private void createMainButton() {
		double initialHeight = 20;
		
		// New Game, Load Game, Exit
		newGameButton = new MainButton("New Game");
		leaderBoardButton = new MainButton("Leaderboard");
		exitButton = new MainButton("Exit");
		newGameButton.setLayoutY(HEIGHT-(3*initialHeight)-newGameButton.getPrefHeight()-leaderBoardButton.getPrefHeight()-exitButton.getPrefHeight());
		newGameButton.setLayoutX(20.00);
		leaderBoardButton.setLayoutY(newGameButton.getLayoutY()+initialHeight+newGameButton.getPrefHeight());
		leaderBoardButton.setLayoutX(20.00);
		exitButton.setLayoutY(leaderBoardButton.getLayoutY()+initialHeight+leaderBoardButton.getPrefHeight());
		exitButton.setLayoutX(20.00);
		
		// Set initial isPressed boolean values
		setIsLeaderBoardPressed(false);
		
		// Add to pane
		mainPane.getChildren().add(newGameButton);
		mainPane.getChildren().add(leaderBoardButton);
		mainPane.getChildren().add(exitButton);


	}
	
	private void setBackgroundImage(String url) {
		bgImg = new Image(url);
		bg = new ImageView(bgImg);
		bg.setFitHeight(HEIGHT);
		bg.setFitWidth(WIDTH);
		mainPane.getChildren().add(bg);
	}
	private void createLogo() {
		ImageView logo = new ImageView("/Logo.png");
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
		info.setLayoutX(WIDTH-initialHeight-info.getPrefWidth());
		info.setLayoutY(HEIGHT-initialHeight-info.getPrefHeight());
		mainPane.getChildren().add(info);
		setIsInfoPressed(false);
	}
	
	private void createAboutUsSubScene() {
		this.aboutUs = new AjarnRunPartialSubScene("/image/aboutUsDemo2.png", "infoButton", 350, 560);
		this.mainPane.getChildren().add(aboutUs);
	}
	private void createLeaderBoardSubScene() {
		leaderBoard = new AjarnRunPartialSubScene("/image/leaderboardDemo2.png", "leaderBoard", 350, 560);
		this.mainPane.getChildren().add(leaderBoard);
	}
	
	private void implementLeaderBoardEventListener() {
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
				mainStage.close();
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
	
	public static int getSceenHeight() {
		return HEIGHT;
	}
	public static int getScreenWidth() {
		return WIDTH;
	}
}
