package view;

import application.logic.GameController;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.Player;
import sharedObject.AudioLoader;

public class GameScene {
	private Stage primaryStage;


	public GameScene(Stage primaryStage) {
		this.primaryStage = primaryStage;

		GameController gameController = new GameController();
		
		Pane root = GameController.getAppRoot();
		Scene scene = new Scene(root);
		
		scene.setOnKeyPressed(event-> GameController.getKeys().put(event.getCode(), true));
		scene. setOnKeyReleased(event -> GameController.getKeys().put(event.getCode(), false));
			
		this.primaryStage.setTitle("Ajarn Ja Run!");
		this.primaryStage = primaryStage;
		this.primaryStage.setScene(scene);
		this.primaryStage.show();

		GameController.platformStart();
	
	}

	
}
