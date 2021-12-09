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
	private MainButton newGameButton;
	private MainButton loadGameButton;
	private MainButton exitButton;
	private MainButton info;
	
	public MainViewManager() {
		this.mainPane = new AnchorPane();
		setBackgroundImage("/mainSceneBackground_demo.png");
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
		loadGameButton = new MainButton("Load Game");
		exitButton = new MainButton("Exit");
		newGameButton.setLayoutY(HEIGHT-(3*initialHeight)-newGameButton.getPrefHeight()-loadGameButton.getPrefHeight()-exitButton.getPrefHeight());
		newGameButton.setLayoutX(20.00);
		loadGameButton.setLayoutY(newGameButton.getLayoutY()+initialHeight+newGameButton.getPrefHeight());
		loadGameButton.setLayoutX(20.00);
		exitButton.setLayoutY(loadGameButton.getLayoutY()+initialHeight+loadGameButton.getPrefHeight());
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
	public MainButton getNewGameButton() {
		return newGameButton;
	}
	public MainButton getLoadGameButton() {
		return loadGameButton;
	}
	public MainButton getExitButton() {
		return exitButton;
	}
	public MainButton getInfo() {
		return info;
	}
}
