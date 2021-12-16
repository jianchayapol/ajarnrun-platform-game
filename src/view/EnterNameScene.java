package view;

import application.logic.GameManager;
import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

public class EnterNameScene extends Scene {
	private static Stage primaryStage;
	private static ImageButton playButton = new ImageButton(ImageButtonType.PLAY);
	private static StackPane enterPane;
	private static ProgressBar progBar;
	private static Rectangle whiteRectangle;
	private static double progressValue;
	private static TextField textField;
	private static Label headerText;
	private static Label errorMessage;
	private static ImageButton homeButton;

	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;

	public EnterNameScene(Stage primaryStage) {
		super(new StackPane());
		((StackPane) this.getRoot()).setPrefSize(WIDTH, HEIGHT);
		// Background
		setBackgroundImage(RenderableHolder.entrance_background_Image);
	
		// initialize
		initializeTextField();
		initializeProgBar();
		initializeRectangle();
		initializeEnterNameLabel();
		initializeErrorMessage();
		implementBackHandler();
		initializeImageButton();
		
		// set up
		setupPane();

		// Stage
		EnterNameScene.primaryStage = primaryStage;
		EnterNameScene.setScene(this);
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static Scene getScene() {
		return primaryStage.getScene();
	}
	
	public static void setScene(Scene scene) {
		EnterNameScene.primaryStage.setScene(scene);
	}
	
	public static void setPrimaryStage(Stage stage) {
		EnterNameScene.primaryStage = stage;
	}

	// =================== private static method ==============================

	// initialize Items

	private static void initializeTextField() {
		textField = new TextField();
		textField.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		textField.setPrefWidth(290);
		textField.setMaxWidth(290);
		FontLoader.setFont(textField, FontType.PSLCD, 30);
		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.ENTER)) {
					playButton.playButtonPress();
				}
			}
		});
	}

	private static void initializeProgBar() {
		progBar = new ProgressBar(0);
		progBar.setMaxWidth(290);
		progBar.setMaxHeight(50);
	}

	private void initializeRectangle() {
		whiteRectangle = new Rectangle(450, 320);
		whiteRectangle.setFill(Color.WHITESMOKE);
		whiteRectangle.setOpacity(0.2);
		whiteRectangle.setLayoutX(30);
		whiteRectangle.setLayoutY(30);
	}

	private void initializeEnterNameLabel() {
		headerText = new Label("Enter your name!");
		headerText.setTextFill(Color.WHITESMOKE);
		FontLoader.setFont(headerText, FontType.TELEGRAMA, 32);
		headerText.setAlignment(Pos.CENTER);
	}

	private void initializeErrorMessage() {
		errorMessage = new Label("");
		errorMessage.setAlignment(Pos.BOTTOM_LEFT);
		errorMessage.setTextFill(Color.YELLOW);
		FontLoader.setFont(errorMessage, FontType.TELEGRAMA, 18);
		errorMessage.setLayoutY(400);
		errorMessage.setAlignment(Pos.BOTTOM_CENTER);
	}

	private void initializeImageButton() {
		homeButton = new ImageButton(ImageButtonType.HOME);
	}
	
	private void implementBackHandler() {
		((StackPane) this.getRoot()).setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.ESCAPE)) {
					EnterNameScene.setScene(ViewManager.getMainScene());
				}
			}
		});
	}

	private void setupPane() {
		StackPane pane = new StackPane();
		enterPane = new StackPane();
		enterPane.getChildren().addAll(progBar, textField);
		VBox vbox = new VBox(30);
		vbox.getChildren().addAll(headerText, enterPane, playButton, errorMessage);
		pane.getChildren().addAll(whiteRectangle, vbox);
		vbox.setAlignment(Pos.CENTER);
		playButton.setLayoutY(400);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(25));
		
		HBox buttonBox = new HBox(20);
		buttonBox.getChildren().addAll(homeButton);
		buttonBox.setPadding(new Insets(25));
		buttonBox.setAlignment(Pos.TOP_RIGHT);
		
		VBox mainBox = new VBox(45);
		mainBox.getChildren().addAll(buttonBox,pane);
		mainBox.setPadding(new Insets(10));
		
		pane.setAlignment(Pos.CENTER);
		
		((StackPane) this.getRoot()).getChildren().addAll(mainBox);
		((StackPane) this.getRoot()).setAlignment(Pos.CENTER);
	}

	// setup
	private void setBackgroundImage(Image bgImg) {
		ImageView bg = new ImageView(bgImg);
		bg.setFitHeight(HEIGHT);
		bg.setFitWidth(WIDTH);
		bg.setBlendMode(BlendMode.DARKEN);

		Rectangle rec = new Rectangle(WIDTH, HEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.8);

		((StackPane) this.getRoot()).getChildren().addAll(bg, rec);
	}

	// =================== public static method : USED IN NameInputUtility class
	// ==============================

	public static String getEnteredName() {
		return textField.getText();
	}

	public static void setErrorMessage(String text) {
		EnterNameScene.errorMessage.setText(text);
	}

	public static String getErrorMessage() {
		return EnterNameScene.errorMessage.getText();
	}

	public static ImageButton getImageButton() {
		return EnterNameScene.playButton;
	}
	
	// ProgressBar

	public static void setProgBar(double d) {
		progBar.setProgress(d);
	}

	public static void startProgress() {
		Thread thread = new Thread(() -> {
			simulateProgressIncrement(0d);
		});
		thread.start();
	}

	public static void simulateProgressIncrement(Double start) {
		progressValue = start;
		while (progressValue < 1d) {
			try {
				Thread.sleep(10);
				Thread thread = new Thread(() -> {
					try {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								setProgBar(progressValue);
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				thread.start();
				progressValue += 0.01d;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Label
	public static void setLabel(String text) {
		EnterNameScene.headerText.setText(text);
	}

	// Pane & Scene
	public static void setupLoading() {
		GameManager.setPlayerName(getEnteredName());
		getEnterPane().getChildren().remove(1);
		setErrorMessage(null);
		setLabel("loading..");
		startProgress();
	}

	public static StackPane getEnterPane() {
		return EnterNameScene.enterPane;
	}

	public static void startGameScene() {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(1600);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						GameScene gameScene = new GameScene(GameManager.getAppRoot(), EnterNameScene.getPrimaryStage());
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}

}
