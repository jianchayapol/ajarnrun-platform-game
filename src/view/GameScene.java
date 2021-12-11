package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sharedObject.AudioLoader;

public class GameScene {
	private Stage primaryStage;
	
	public GameScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		// Initialzing map
		
		//GameController.InitializeGame(...)
		
		HBox root = new HBox(4);
		root.setPrefHeight(600);
		root.setPrefWidth(800);
		
		root.setPadding(new Insets(4));
		root.getChildren().addAll();
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root);
		
		this.primaryStage = primaryStage;
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
}
