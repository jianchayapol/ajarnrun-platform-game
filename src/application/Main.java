package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import application.logic.GameController;
import gui.draw.GameScreen;
import entity.base.Direction;
import entity.character.Player;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		StackPane root = new StackPane();
		Canvas canvas = new Canvas(720, 480);

		ImageView bg = new ImageView(new Image("/town.png"));
		bg.setFitHeight(480); bg.setFitWidth(720);
		
		ImageView img = new ImageView(new Image("/aj-vishnu1.png"));
		img.setFitWidth(80); img.setFitHeight(100);
		root.setAlignment(Pos.CENTER_LEFT);
		
		GameScreen gameScreen = new GameScreen(720, 480);
		root.getChildren().add(gameScreen);
		gameScreen.requestFocus();
		
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.paintComponent();
			}
		};
		
		animation.start();
		root.getChildren().addAll(bg,img);
		
		Scene scene = new Scene(root, 720, 480);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Ajarn ja run !!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
