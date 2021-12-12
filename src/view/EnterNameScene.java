package view;

import java.io.FileInputStream;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class EnterNameScene {
	private static Stage primaryStage;
	private ImageButton playButton;
	private static StackPane mainPane;
	private static ProgressBar progBar;
	private static double value;
	private static StackPane enterPane;
	private static TextField textField;
	private static Label text1;
	private static Label errorMessage;
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	
	public EnterNameScene(Stage primaryStage) {
		mainPane = new StackPane();
		setBackgroundImage(RenderableHolder.entrance_background_Image);
		setUpForm();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		EnterNameScene.primaryStage = primaryStage;
	}
	
	private void setBackgroundImage(Image bgImg) {
		ImageView bg = new ImageView(bgImg);
		bg.setFitHeight(HEIGHT);
		bg.setFitWidth(WIDTH);
		bg.setBlendMode(BlendMode.DARKEN);
		
		Rectangle rec = new Rectangle(WIDTH,HEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.8);
		
		mainPane.getChildren().addAll(bg,rec);
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	private void setUpForm() {
		
		this.mainPane.setPrefSize(WIDTH, HEIGHT);
		
		playButton = new ImageButton(ImageButtonType.PLAY);
		
		StackPane pane = new StackPane();
		
		textField = new TextField();
		textField.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		textField.setPrefWidth(290);
		textField.setMaxWidth(290);
		try {
			textField.setFont(Font.loadFont(new FileInputStream("res/font/Courier.ttf"), 28));
		} catch (Exception e) {
			textField.setFont(Font.font("Verdana", 28));
		}
		
		enterPane = new StackPane();
		progBar = new ProgressBar(0);
		progBar.setMaxWidth(290);
		progBar.setMaxHeight(50);
		enterPane.getChildren().addAll(progBar,textField);
		
		
		VBox vbox = new VBox(40);
		
		Rectangle rec = new Rectangle(450,320);
		rec.setFill(Color.WHITESMOKE);
		rec.setOpacity(0.2);
		rec.setLayoutX(30);
		rec.setLayoutY(30);
		
		text1 = new Label("Enter your name!");
		text1.setTextFill(Color.WHITESMOKE);
		try {
			text1.setFont(Font.loadFont(new FileInputStream("res/font/YanoneKaffeesatz-SemiBold.ttf"), 32));
		} catch (Exception e) {
			text1.setFont(Font.font("Verdana", 32));
		}
		text1.setAlignment(Pos.CENTER);
		
		
		errorMessage = new Label("");
		errorMessage.setAlignment(Pos.BOTTOM_LEFT);
		errorMessage.setTextFill(Color.YELLOW);
		
		try {
			errorMessage.setFont(Font.loadFont(new FileInputStream("res/font/Courier.ttf"), 18));
		} catch (Exception e) {
			errorMessage.setFont(Font.font("Verdana", 18));
		}
		
		vbox.getChildren().addAll(text1,enterPane, playButton,errorMessage);
		errorMessage.setLayoutY(400);
		errorMessage.setAlignment(Pos.BOTTOM_CENTER);
		
		pane.getChildren().addAll(rec,vbox);
		
		vbox.setAlignment(Pos.CENTER);
		playButton.setLayoutY(400);
		pane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(pane);
		mainPane.setAlignment(Pos.CENTER);
		
	}
	
	public static String getEnteredName() {
		return textField.getText();
	}

	public static void setProgBar(double d) {
		progBar.setProgress(d);
	}
	
	public static void startProgress() {
		Thread thread = new Thread(() -> { simulateProgressIncrement(0d); });
		thread.start();
	}
	
	public static void simulateProgressIncrement(Double start) {
		value = start;
		while (value < 1d) {
			try {
				Thread.sleep(10);
				
				Thread thread = new Thread(() -> {
					try {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								setProgBar(value);
							}
						});
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				});
				thread.start();

				value += 0.01d;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void setLabel(String text) {
		EnterNameScene.text1.setText(text);
	}

	public static StackPane getEnterPane() {
		// TODO Auto-generated method stub
		return EnterNameScene.enterPane;
	}

	public static void setErrorMessage(String text) {
		EnterNameScene.errorMessage.setText(text);
	}
	
	public static String getErrorMessage() {
		return EnterNameScene.errorMessage.getText();
	}
	
	public static void setupLoading() {
		getEnterPane().getChildren().remove(1);
		setErrorMessage(null);
		setLabel("loading..");
		startProgress();
	}
	
	public static void startGameScene() {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(1600);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						GameScene gameScene = new GameScene(EnterNameScene.getPrimaryStage());
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}
	
}
