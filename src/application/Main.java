package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import application.logic.GameController;
import entity.base.Direction;
import entity.character.Player;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		StackPane root = new StackPane();
		Canvas canvas = new Canvas(720, 480);
		Scene scene = new Scene(root, 720,480);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().addAll(canvas);
		addEventListener(scene, gc);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Ajarn ja run !!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void addEventListener(Scene s, GraphicsContext gc) {
		s.setOnKeyPressed((event) -> {
			KeyCode k = event.getCode();
			switch (k) {
			case LEFT:
				GameController.movePlayer(Direction.LEFT);
				break;
			case RIGHT:
				GameController.movePlayer(Direction.RIGHT);
				break;
			case UP:
				GameController.movePlayer(Direction.UP);
				break;
			default:
				System.out.println("Invalid Key");
				break;
			}

		});
	}

}
