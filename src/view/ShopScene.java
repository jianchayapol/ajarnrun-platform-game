package view;

import application.logic.GameManager;
import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

public class ShopScene extends Scene {
	private static Stage primaryStage;
	private static StackPane shopPane;
	private static VBox buttonPane;
	private static Label errorMessage;

	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;

	public ShopScene(Stage primaryStage) {
		super(new StackPane());
		((StackPane)this.getRoot()).setPrefSize(WIDTH, HEIGHT);
		
		// Background
		setBackgroundImage(RenderableHolder.entrance_background_Image);

		// initialize
		initializeShopPane();
		initializeErrorMessage();

		// set up
		setupPane();

		// Stage
		primaryStage.setScene(this);
		this.primaryStage = primaryStage;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	// =================== private static method ==============================

	// initialize 

	private void initializeShopPane() {
		shopPane = new StackPane();
		ImageView shop = new ImageView(RenderableHolder.shop_Image);
		shop.setFitHeight(400);
		shop.setFitWidth(300);
		shopPane.getChildren().add(shop);
		shopPane.setPrefSize(360,480);
		shopPane.setAlignment(Pos.CENTER);
	}
	
	private void initializeErrorMessage() {
		errorMessage = new Label("");
		errorMessage.setAlignment(Pos.BOTTOM_LEFT);
		errorMessage.setTextFill(Color.YELLOW);
		FontLoader.setFont(errorMessage, FontType.YANONE, 18);
		errorMessage.setLayoutY(400);
		errorMessage.setAlignment(Pos.BOTTOM_CENTER);
	}

	private void setupPane() {
		StackPane pane = new StackPane();
		pane.getChildren().addAll(shopPane);
		pane.setAlignment(Pos.CENTER);
		((StackPane)this.getRoot()).getChildren().addAll(pane);
		((StackPane)this.getRoot()).setAlignment(Pos.CENTER);
	}
	
	// setup
	private void setBackgroundImage(Image bgImg) {
		ImageView bg = new ImageView(bgImg);
		bg.setFitHeight(HEIGHT);
		bg.setFitWidth(WIDTH);
		bg.setBlendMode(BlendMode.DARKEN);

		Rectangle rec = new Rectangle(WIDTH, HEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.7);

		((StackPane)this.getRoot()).getChildren().addAll(bg, rec);
	}

	// =================== public static method ==============================


	public static void setErrorMessage(String text) {
		ShopScene.errorMessage.setText(text);
	}

	public static String getErrorMessage() {
		return ShopScene.errorMessage.getText();
	}

	public static void startGameScene() {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(400);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						//GameScene gameScene = new GameScene(GameManager.getAppRoot(), primaryStage);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}

}
