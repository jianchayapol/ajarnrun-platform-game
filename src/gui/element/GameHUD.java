package gui.element;

import application.logic.GameManager;
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

/**
 * This GameHUD class extends HBox is a gameHUD presents in the GameScene
 * UIRoot. This HUD show the details about player's stats during the game.
 * 
 * @author jianchayapol
 *
 */
public class GameHUD extends HBox {

	/**
	 * This HBox contains nameLabel,moneyBox, progBar, image.
	 */
	private static HBox playerBox;
	/**
	 * This moneyBox is a box that represents player's total coins.
	 */
	private static MoneyBox moneyBox;
	/**
	 * The ProgressBar representing time left (countdown)
	 */
	private static ProgressBar timerProgBar;
	/**
	 * ImageButton used to pause the game
	 */
	private static ImageButton pauseButton;
	/**
	 * ImageButton used to mute and unmute the game
	 */
	private static ImageButton soundButton;
	/**
	 * This HBox contains buttons.
	 */
	private static HBox buttonBox;
	/**
	 * This StackPane contains timerProgBar and Label "TIME".
	 */
	private static StackPane timerPane;
	/**
	 * This StackPane contains progBar and Label "HP".
	 */
	private static StackPane progBarPane;
	/**
	 * ImageView of player image
	 */
	private static ImageView image;
	/**
	 * Label that represents player's current level
	 */
	private static Label levelLabel;
	/**
	 * Label that represents player's name
	 */
	private static Label nameLabel;
	/**
	 * | The ProgressBar representing player's current HP
	 */
	private static ProgressBar progBar;
	/**
	 * This VBox contains levelLabel, timerPane
	 */
	private static VBox timerBox;

	/**
	 * Constructor construct by setting up all the components and HUD Preference by
	 * calling method setupComponents() and setupHudPref().
	 */
	public GameHUD() {
		setupComponents();
		setupHudPref();
	}

	// =============== public static methods =========================

	/**
	 * Methods for setting the ProgressBar (progBar and timerProg) If the current
	 * progress is greater than its max, set to max. If the current progress is less
	 * than zero, set to zero. If the current progress is less than 10%, set the
	 * style to red. call method setProgress(0d) to set the progress
	 * 
	 * @param progBar
	 * @param current
	 * @param max
	 */
	public static void setProgress(ProgressBar progBar, double current, double max) {
		if (current > max) {
			current = max;
		}
		if (current < 0) {
			current = 0;
		}
		double progress = current * 1.0 / max;
		progBar.setProgress(progress);
		if (progress <= 0.1d) {
			progBar.setStyle("-fx-accent: red");
		}
	}

	/**
	 * public static method for getting timer ProgressBar
	 * 
	 * @return
	 */
	public static ProgressBar getTimerProgressBar() {
		return timerProgBar;
	}

	/**
	 * public static method for getting HP ProgressBar
	 * 
	 * @return
	 */
	public static ProgressBar getHpProgressBar() {
		return progBar;
	}

	/**
	 * public static method for setting level Label
	 * 
	 * @return
	 */
	public static void setLevelLabel() {
		int level = GameManager.getLevelCount() + 1;
		levelLabel.setText("Lv." + String.valueOf(level));
	}

	/**
	 * public static method for setting name Label
	 * 
	 * @return
	 */
	public static void setNameLabel(String name) {
		nameLabel.setText(name);
	}
	
	/**
	 * public static method for setting money Label
	 */
	public static void setMoneyLabel() {
		moneyBox.updateMoneyText();
	}
	
	// =============== private static methods =========================

	/**
	 * Method for setting up components, by calling initImagePane(),
	 * initHpProgBar(), initNameLabel(), initLevelLabel(), initTimerBox(),
	 * initPlayerBox() methos.s
	 */
	private static void setupComponents() {
		initImagePane();
		initHpProgBar();
		initNameLabel();
		initLevelLabel();
		initTimerBox();
		initButtonBox();
		initPlayerBox();
	}

	/**
	 * method for initialize imagePane by using RenderableHolder to access the
	 * public static field of image, set fit width and height to 48,72 respectively.
	 */
	private static void initImagePane() {
		image = new ImageView(RenderableHolder.playerImage);
		image.setFitHeight(72);
		image.setFitWidth(48);
	}

	/**
	 * method for initialize HP progBar, inititialize with the full progress (100%
	 * progress), set the style to green, setPrefSize to 300x28. Add the Label "HP"
	 * in the middle of progBar by calling setTextProgBar() method.
	 */
	private static void initHpProgBar() {
		progBar = new ProgressBar(1.0d);
		progBar.setStyle("-fx-accent: green");
		progBar.setPrefSize(300, 28);
		progBarPane = setTextProgBar(progBar, "HP");
	}

	/**
	 * method for initialize nameLabel by setting text to player's name. Set the
	 * label's font by using static method setFont() from FontLoader to set font to
	 * FontType.TELEGRAMA and fontSize 20. Set textFill to Black. Set its
	 * setAlignment to BASELINE_CENTER.
	 */
	private static void initNameLabel() {
		nameLabel = new Label(GameManager.getPlayerName());
		FontLoader.setFont(nameLabel, FontType.TELEGRAMA, 20);
		nameLabel.setTextFill(Color.BLACK);
		nameLabel.setAlignment(Pos.BASELINE_CENTER);
	}

	/**
	 * method for initialize timerBox by setting up timer progressBar to full 100%
	 * progress Set its PrefWidth and maxWidth to 400. Set PrefHeight to 30.
	 * progress), set the style to green, setPrefSize to 300x28. Add the Label
	 * "TIME" in the middle of progBar by calling setTextProgBar() method to get the
	 * timePane. Set timerPane's PrefWidth to 430. Add the levelLabel, timerPane to
	 * new VBox with spacing 10, set its maxHeight to 120, and set alignment to
	 * BOTTOM_LEFT.
	 */
	private static void initTimerBox() {
		// Setup ProgressBar
		timerProgBar = new ProgressBar(1.0d);
		timerProgBar.setPrefWidth(400);
		timerProgBar.setMaxWidth(400);
		timerProgBar.setPrefHeight(30);
		timerPane = setTextProgBar(timerProgBar, "TIME");
		timerPane.setPrefWidth(430);
		// Add to VBox
		timerBox = new VBox(10);
		timerBox.setMaxHeight(120);
		timerBox.getChildren().addAll(levelLabel, timerPane);
		timerBox.setAlignment(Pos.BOTTOM_LEFT);
	}

	/**
	 * method for initialize levelLabel and set its text by calling setLevelLabel()
	 * Set the label's font by using static method setFont() from FontLoader to set
	 * font to FontType.TELEGRAMA and fontSize 22. Set textFill to Darkred set
	 * alignment to BASELINE_LEFT.
	 * 
	 */
	private static void initLevelLabel() {
		levelLabel = new Label();
		FontLoader.setFont(levelLabel, FontType.TELEGRAMA, 22);
		levelLabel.setTextFill(Color.DARKRED);
		levelLabel.setAlignment(Pos.BASELINE_LEFT);
		setLevelLabel();
	}

	/**
	 * method for initialize buttonBox to be new HBox with 20 spacing. Initialize
	 * and Add ImageButtonType.SOUND and ImageButtonType.PAUSE ImageButton. Set
	 * Padding insets to 10 and set its alignment to CENTER_RIGHT.
	 */
	private static void initButtonBox() {
		buttonBox = new HBox(20);
		soundButton = new ImageButton(ImageButtonType.SOUND);
		pauseButton = new ImageButton(ImageButtonType.PAUSE);
		buttonBox.setPadding(new Insets(10));
		buttonBox.getChildren().addAll(pauseButton, soundButton);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
	}

	/**
	 * method for Setting text on the ProgressBar. Create new StackPane to store the
	 * progressBar and the new Label. Set the alignment to CENTER. set the label
	 * text by calling setLevelLabel() Set the label's font by using static method
	 * setFont() from FontLoader to set font to FontType.TELEGRAMA and fontSize 10.
	 * 
	 * @param progBar
	 * @param text
	 * @return pane containing the ProgressBar and the Label
	 */
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

	/**
	 * method for initialize the playerBox, creating new HBox with 20 spacing,
	 * setMaxSize to 480x120, Set PrefWidth to 430. Creating new StackPane storing
	 * moneyBox, set alignment to center left. Creating new HBox with spacing 20,
	 * add nameLabel and the StackPane. Set MoneyBox PrefSize to 100,34 Creating new
	 * VBox with spacing 8, add the HBox and progBarPane. Then add image and VBox to
	 * the playerBox.
	 */
	private static void initPlayerBox() {
		playerBox = new HBox(20);
		playerBox.setMaxSize(480, 120);
		playerBox.setPrefWidth(430);
		StackPane moneyPane = new StackPane();
		moneyBox = new MoneyBox();
		HBox infoBox1 = new HBox(20);
		moneyBox.setPrefSize(100, 34);
		moneyPane.getChildren().addAll(moneyBox);
		moneyPane.setAlignment(Pos.CENTER_LEFT);
		infoBox1.getChildren().addAll(nameLabel, moneyPane);

		VBox infoBox2 = new VBox(8);
		infoBox2.getChildren().addAll(infoBox1, progBarPane);
		playerBox.getChildren().addAll(image, infoBox2);
	}

	/**
	 * Method for setting up GameHUD Preference, setting spacing to 30, add
	 * playerBox, timerBox, buttonBox, set padding to 15 and set maxWidth to 800.
	 */
	private void setupHudPref() {
		setSpacing(30);
		getChildren().addAll(playerBox, timerBox, buttonBox);
		setPadding(new Insets(15));
		setMaxWidth(800);
	}
}
