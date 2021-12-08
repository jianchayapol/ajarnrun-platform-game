package view;

import button.MainButton;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainViewManager {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Image bgImg;
	private ImageView bg;
	private Image characterImg;
	private ImageView character;
	private MainButton newGameButton;
	private MainButton loadGameButton;
	private MainButton exitButton;
	private MainButton info;
	
	public MainViewManager() {
		this.mainPane = new AnchorPane();
		setBackgroundImage("/town.png");
		addCharacter("/aj-vishnu1.png");
		createMainButton();
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
		newGameButton.setLayoutY(initialHeight);
		newGameButton.setLayoutX(20.00);
		loadGameButton = new MainButton("Load Game");
		loadGameButton.setLayoutY(initialHeight + loadGameButton.getPrefHeight() + initialHeight);
		loadGameButton.setLayoutX(20.00);
		exitButton = new MainButton("Exit");
		exitButton.setLayoutY(initialHeight + loadGameButton.getPrefHeight() + initialHeight + exitButton.getPrefHeight() + initialHeight);
		exitButton.setLayoutX(20.00);
		
		// About Us
		info = new MainButton("About Us");
		info.setLayoutX(WIDTH-initialHeight-info.getPrefWidth());
		info.setLayoutY(HEIGHT-initialHeight-info.getPrefHeight());
		
		// Add to pane
		mainPane.getChildren().add(newGameButton);
		mainPane.getChildren().add(loadGameButton);
		mainPane.getChildren().add(exitButton);
		mainPane.getChildren().add(info);
	}
	
	private void setBackgroundImage(String url) {
		bgImg = new Image(url);
		bg = new ImageView(bgImg);
		bg.setFitHeight(HEIGHT);
		bg.setFitWidth(WIDTH);
		mainPane.getChildren().add(bg);
	}
	
	private void addCharacter(String url) {
		characterImg = new Image(url);
		character = new ImageView(characterImg);
		character.setFitHeight(100);
		character.setFitWidth(80);
		character.setLayoutY(400);
		character.setLayoutX(100);
		mainPane.getChildren().add(character);
	}
}
