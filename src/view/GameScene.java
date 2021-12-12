package view;

import application.logic.GameManager;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameScene extends Scene {

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
	}
}
