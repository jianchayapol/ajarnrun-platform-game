package view;

import application.logic.GameManager;
import gui.element.GameHUD;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameScene extends Scene {

	private static GameHUD gameHud;
	
	public GameScene(Pane parent, Stage primaryStage) {
		super(parent);
		initializeEventHandler();
		setUpStage(primaryStage);
		runScene();
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
		primaryStage.setTitle("Ajarn Ja Run!");
		primaryStage.setScene(this);
	}
	
	private void runScene() {
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				GameManager.update();
			}
		};
		timer.start();
		//GameHUD.timerAnimate(1);
	}
}
