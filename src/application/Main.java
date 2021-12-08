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
import view.MainViewManager;
import application.logic.GameController;
import gui.draw.GameScreen;
import entity.base.Direction;
import entity.character.Player;

public class Main extends Application {
	private MainViewManager mainViewManager;
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainViewManager = new MainViewManager();
		primaryStage = mainViewManager.getStage();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
