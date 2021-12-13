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

	public GameHUD() {
		setupComponents();
		setupHudPref();
	}
	
	// =================== public static method(s) : USED in GameScene =========================
	
	public static void setProgress(ProgressBar progBar, double current, double max) {
		double progress = current*1.0/max;
		progBar.setProgress(progress);
		if(progress<=0.1d) progBar.setStyle("-fx-accent: red");
	}
	
	public static ProgressBar getTimerProgressBar() {
		return timerProgBar;
	}
	
	// =================== private static method(s) : USED within this GameHUD class =========================
	
	private static void setupComponents() {
		// initialize components
		initImagePane();
		initHpProgBar();
		initNameLabel();
		initLevelLabel();
		initTimerBox();
		initButtonBox();
		initPlayerBox();
	}

	private static void initImagePane() {
		image = new ImageView(RenderableHolder.playerImage);
		image.setFitHeight(72);
		image.setFitWidth(48);
	}
	
	private static void initHpProgBar() {
		progBar = new ProgressBar();
		progBar.setStyle("-fx-accent: green");
		progBar.setPrefSize(300, 28);
		setProgress(progBar, 80, 100);
		progBarPane = setTextProgBar(progBar, "HP");
	}
	
	private static void initNameLabel() {
		nameLabel = new Label("Mos");
		FontLoader.setFont(nameLabel, FontType.TELEGRAMA, 28);
		nameLabel.setTextFill(Color.BLACK);
		nameLabel.setAlignment(Pos.BASELINE_CENTER);
	}
	
	private static void initTimerBox() {
		// Setup ProgressBar 
		timerProgBar = new ProgressBar(1.0d);
		timerProgBar.setPrefWidth(520);
		timerProgBar.setPrefHeight(30);
		timerPane = setTextProgBar(timerProgBar, "TIME");
		timerPane.setPrefWidth(500);
		// Add to VBox
		timerBox = new VBox(10);
		timerBox.getChildren().addAll(levelLabel, timerPane);
		timerBox.setAlignment(Pos.BOTTOM_LEFT);
		timerBox.setMaxHeight(120);
	}
	
	private static void initLevelLabel() {
		levelLabel = new Label("Lv.1");
		FontLoader.setFont(levelLabel, FontType.TELEGRAMA, 22);
		levelLabel.setTextFill(Color.DARKRED);
		levelLabel.setAlignment(Pos.BASELINE_LEFT);
	}

	private static void initButtonBox() {
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

	private static void initPlayerBox() {
		playerBox = new HBox(30);
		playerBox.setMaxSize(350, 120);

		moneyBox = new MoneyBox();
		HBox infoBox1 = new HBox(20);
		moneyBox.setPrefSize(100, 34);

		infoBox1.getChildren().addAll(nameLabel, moneyBox);

		VBox infoBox2 = new VBox(8);
		infoBox2.getChildren().addAll(infoBox1, progBarPane);

		playerBox.getChildren().addAll(image, infoBox2);
	}
	
	private void setupHudPref() {
		setSpacing(50);
		getChildren().addAll(playerBox, timerBox, buttonBox);
		setPadding(new Insets(15));
		setMaxWidth(750);
	}

}
