package gui.element;

import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

public class GameHUD extends HBox {

	private static HBox playerBox;
	private static MoneyBox moneyBox;
	private static ProgressBar timerProgBar;
	private static ImageButton pauseButton;
	private static ImageButton soundButton;
	private static HBox buttonBox;
	private static StackPane timerPane;
	private static StackPane progBarPane;
	private static ImageView image;
	private static Label levelLabel;
	private static Label nameLabel;
	private static ProgressBar progBar;
	private static VBox timerBox;
	private static StackPane imagePane;
	private static double timeValue;

	public GameHUD() {
		setupComponents();
		setupPlayerBox();
		this.setSpacing(50);
		this.getChildren().addAll(playerBox, timerBox, buttonBox);
		this.setPadding(new Insets(15));
		this.setMaxWidth(750);
	}

	public static void setupComponents() {
		imagePane = new StackPane();
		image = new ImageView(RenderableHolder.playerImage);
		image.setFitHeight(72);
		image.setFitWidth(48);
		imagePane.getChildren().addAll(image);
		imagePane.setAlignment(Pos.CENTER);

		progBar = new ProgressBar();
		progBar.setStyle("-fx-accent: red");
		progBar.setPrefSize(300, 28);
		setProgress(progBar, 80, 100);
		progBarPane = setTextProgBar(progBar, "HP");

		nameLabel = new Label("Mos");
		FontLoader.setFont(nameLabel, FontType.TELEGRAMA, 28);
		nameLabel.setTextFill(Color.BLACK);
		nameLabel.setAlignment(Pos.BASELINE_CENTER);

		timerProgBar = new ProgressBar(1.0d);
		timerProgBar.setPrefWidth(520);
		timerProgBar.setPrefHeight(30);
		timerPane = setTextProgBar(timerProgBar, "TIME");
		timerPane.setPrefWidth(500);
		timerBox = new VBox(10);

		levelLabel = new Label("Lv.1");
		FontLoader.setFont(levelLabel, FontType.TELEGRAMA, 22);
		levelLabel.setTextFill(Color.DARKRED);
		levelLabel.setAlignment(Pos.BASELINE_LEFT);
		timerBox.getChildren().addAll(levelLabel, timerPane);
		timerBox.setAlignment(Pos.BOTTOM_LEFT);
		timerBox.setMaxHeight(120);
		setupButtonBox();
	}

	private static void setupButtonBox() {
		buttonBox = new HBox(10);
		soundButton = new ImageButton(ImageButtonType.SOUND);
		pauseButton = new ImageButton(ImageButtonType.PAUSE);
		buttonBox.setPadding(new Insets(10));
		buttonBox.getChildren().addAll(pauseButton, soundButton);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
	}

	private static StackPane setTextProgBar(ProgressBar progBar, String text) {
		StackPane pane = new StackPane();
		Label label = new Label();
		FontLoader.setFont(label, FontType.TELEGRAMA, 10);
		label.setTextFill(Color.WHITE);
		label.setText(text);
		label.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(progBar, label);
		return pane;
	}

	private static void setupPlayerBox() {
		playerBox = new HBox(30);
		playerBox.setMaxSize(350, 120);

		moneyBox = new MoneyBox();
		HBox infoBox1 = new HBox(20);
		moneyBox.setPrefSize(100, 34);

		infoBox1.getChildren().addAll(nameLabel, moneyBox);

		VBox infoBox2 = new VBox(8);
		infoBox2.getChildren().addAll(infoBox1, progBarPane);

		playerBox.getChildren().addAll(imagePane, infoBox2);
	}

	public static void setProgress(ProgressBar progBar, double current, double max) {
		progBar.setProgress(current*1.0 / max);
		System.out.println(current*1.0 / max);
	}
	

//	public static void timerAnimate(double timeSecond) {
//		timeValue = timeSecond;
//		while (timeValue >= 0d) {
//			try {
//				Thread thread = new Thread(() -> {
//					try {
//						Platform.runLater(new Runnable() {
//							@Override
//							public void run() {
//								setProgress(timerProgBar, timeValue, timeSecond);
//							}
//						});
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				});
//				thread.start();
//				timeValue -= 0.001d;
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
