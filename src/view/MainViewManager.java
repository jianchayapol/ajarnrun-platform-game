package view;

import button.MainButton;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
	private Canvas canvas;
	private Image bgImg;
	private ImageView bg;
	private Image characterImg;
	private ImageView character;
	private MainButton newGameButton;
	private MainButton loadGameButton;
	private MainButton exitButton;
	
	public MainViewManager() {
		this.mainPane = new AnchorPane();
		createMainButton();
		setBackgroundImage("src/res/townBG.png");
		addCharacter("src/res/aj-vishnu1.png");
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
		newGameButton = new MainButton("New Game");
		loadGameButton = new MainButton("Load Game");
		exitButton = new MainButton("Exit");
		mainPane.getChildren().add(newGameButton);
		mainPane.getChildren().add(loadGameButton);
		mainPane.getChildren().add(exitButton);
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
