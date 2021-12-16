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

/**
 * This public class, EnterNameScene class extends from Scene. It represents as
 * the second scene of this game where player can input their name to start the
 * game.
 * 
 * @author jianchayapol
 *
 */
public class EnterNameScene extends Scene {
	/**
	 * primaryStage
	 */
	private static Stage primaryStage;

	/**
	 * The ImageButton with a ImageButtonType.PLAY which implements the
	 * mousePressEventHandler to Start the game. (For more detailed, see in ..
	 * ImageButton & EnterNameScene)
	 */
	private static ImageButton playButton = new ImageButton(ImageButtonType.PLAY);

	/**
	 * The StackPane that contains progBar and textField
	 */
	private static StackPane enterPane;

	/*
	 * This ProgressBar represents as a Loading ProgressBar setting up to represent
	 * the loading map animation (progress bar Simulate Progress Increment).
	 */
	private static ProgressBar progBar;

	/*
	 * The rectangle for using as the black transparent background.
	 */
	private static Rectangle whiteRectangle;

	/*
	 * This value represents the loading progress value
	 */
	private static double progressValue;

	/*
	 * A Textfield for players to input their name
	 */
	private static TextField textField;

	/*
	 * This is Header Label Text
	 */
	private static Label headerText;

	/*
	 * errorMessage showing when player input the wrong format name
	 */
	private static Label errorMessage;

	/*
	 * ImageButton with the ImageButtonType.HOME for setting the scene Back to the
	 * main menu (ViewManager)
	 */
	private static ImageButton homeButton;

	/*
	 * Screen's height setup to be 600
	 */
	private static final int HEIGHT = 600;

	/**
	 * Screen's Width setup to be 800
	 */
	private static final int WIDTH = 800;

	/**
	 * This Constructor construct the EnterNameScene with the given Stage
	 * primaryStage from vieManager. This set the root PrefSize to the screen's
	 * width and height. Set Background to the entrance_background_Image by
	 * accessing public static field RenderableHolder.entrance_background_Image.
	 * Then, Initialize TextField, progBar, darkRectangle, enterNameLabel,
	 * errorMessage. Implements BackHandler and ImageButton. Setup the pane. Set the
	 * prtmsryStage as a primaryStage field and Set this as Scene.
	 * 
	 * @param primaryStage
	 */
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

	/**
	 * Method for Getting primaryStage
	 * 
	 * @return primaryStage
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Method for Getting scene
	 * 
	 * @return scene of primaryStage
	 */

	public static Scene getScene() {
		return primaryStage.getScene();
	}

	/**
	 * Method for Setting Scene of primaryStage
	 */
	public static void setScene(Scene scene) {
		EnterNameScene.primaryStage.setScene(scene);
	}

	/**
	 * Method for Setting stage as a EnterNameScene.primaryStage
	 */
	public static void setPrimaryStage(Stage stage) {
		EnterNameScene.primaryStage = stage;
	}

	// =================== private static method ==============================

	// initialize Items

	/**
	 * Method for Initializing textField, Set it's Background Color to become white,
	 * Set PrefWidth and MaxWidth to 290. setFont to FontType.PSLCD by using static
	 * method setFont() from FontLoader class, Set fontSize to 30, and implements
	 * the EventHandler by setting KeyCode.ENTER as a submit button by using the
	 * playButtonPress() method in ImageButton class.
	 */
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

	/**
	 * Method to Initialize ProgressBar progBar by setting the default value of
	 * progBar to 0, and Set maxWidth and maxHeight to 290, 50 respectively.
	 */
	private static void initializeProgBar() {
		progBar = new ProgressBar(0);
		progBar.setMaxWidth(290);
		progBar.setMaxHeight(50);
	}

	/**
	 * This method is used to Initialize the Rectangle whiteRectangle with width=450
	 * and height=320, set color to WHITESMOKE with 0.2 opacity, and set both
	 * LayoutX and LayoutY to 30.
	 */
	private void initializeRectangle() {
		whiteRectangle = new Rectangle(450, 320);
		whiteRectangle.setFill(Color.WHITESMOKE);
		whiteRectangle.setOpacity(0.2);
		whiteRectangle.setLayoutX(30);
		whiteRectangle.setLayoutY(30);
	}

	/**
	 * Method for Initializing the header Label EnterNameLabel, set text to "Enter
	 * your name!", setTextFill to WHITESMOKE, and set font to FontType.TELEGRAMA
	 * with fontSize=32 by using static method setFont() in FontLoader, and
	 * setAliment Position to center.
	 */
	private void initializeEnterNameLabel() {
		headerText = new Label("Enter your name!");
		headerText.setTextFill(Color.WHITESMOKE);
		FontLoader.setFont(headerText, FontType.TELEGRAMA, 32);
		headerText.setAlignment(Pos.CENTER);
	}

	/**
	 * Method for Initializing the Label ErrorMessage, setTextFill to YELLOW, and
	 * set font to FontType.TELEGRAMA with fontSize=18 by using static method
	 * setFont() in FontLoader, setAliment Position to bottom left, and setLayoutY
	 * to 400.
	 */
	private void initializeErrorMessage() {
		errorMessage = new Label("");
		errorMessage.setAlignment(Pos.BOTTOM_LEFT);
		errorMessage.setTextFill(Color.YELLOW);
		FontLoader.setFont(errorMessage, FontType.TELEGRAMA, 18);
		errorMessage.setLayoutY(400);
		errorMessage.setAlignment(Pos.BOTTOM_CENTER);
	}

	/**
	 * Method for Initializing homeButton to be ImageButton with
	 * ImageButtonType.HOME.
	 */
	private void initializeImageButton() {
		homeButton = new ImageButton(ImageButtonType.HOME);
	}

	/**
	 * Method for Implementation the KeyEvent to be able to get back to the main
	 * menu (viewManager) by using esc button (KeyCode.ESCAPE).
	 */
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

	/**
	 * Method for Setting up the pane, initialize pane and enterPane as StackPane.
	 * In enterPane, this pane will contain two components which are progBar and
	 * textField. (At first, progBar is hiding behind the textField until the key
	 * trigger from setUpoading() method) Initialize VBox with spacing=30 to store
	 * headerText, enterPane, playButton, errorMessage, and set aliment to center.
	 * add whiteRectangle, vbox to the StackPane pane and set its padding=25 and
	 * aliment to center. Set LayoutY of playButton to 400. Create new HBox
	 * buttonBox with spacing=20, padding=25, aliment=top right, and add homeButton
	 * to it. Create new VBox mainBox that contains buttonBox and pane with
	 * spacing=45, padding=10. Add mainBox to the root of the scene and set aliment
	 * to center.
	 * 
	 */
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
		mainBox.getChildren().addAll(buttonBox, pane);
		mainBox.setPadding(new Insets(10));

		pane.setAlignment(Pos.CENTER);

		((StackPane) this.getRoot()).getChildren().addAll(mainBox);
		((StackPane) this.getRoot()).setAlignment(Pos.CENTER);
	}

	/**
	 * This method is to set the Background of the scene. The Background are made up
	 * of two components which are ImageView and the Dark rectangle. Set the
	 * ImageView from the given bgImg parameter, setBlendMode(BlendMode.DARKEN). Set
	 * the rectangle's color to black with 0.8 opacity. Set the two component's
	 * Width and Height to fit the screen size
	 * 
	 * @param background image of Entrance_Background
	 */
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

	/**
	 * | static method used to get the name entered to the textField
	 * 
	 * @return String of name entered to the textField
	 */
	public static String getEnteredName() {
		return textField.getText();
	}

	/**
	 * Method for Setting Text of errorMessage
	 * 
	 * @param String of error message text 
	 */
	public static void setErrorMessage(String text) {
		EnterNameScene.errorMessage.setText(text);
	}

	/**
	 * Method for Getting errorMessage
	 * 
	 * @return String of errorMessage
	 */
	public static String getErrorMessage() {
		return EnterNameScene.errorMessage.getText();
	}

	/**
	 * Method for Getting playButton
	 * 
	 * @return ImageButton of playButton
	 */
	public static ImageButton getImageButton() {
		return EnterNameScene.playButton;
	}

	/**
	 * Method for setting the progress of the progressBar this method will be used
	 * in method simulateProgressIncrement(d) in the process of loading animation.
	 * 
	 * @param progBar progress
	 */
	public static void setProgBar(double d) {
		progBar.setProgress(d);
	}

	/**
	 * This method Implements thread to start the simulateProgressIncrement(0d).
	 */
	public static void startProgress() {
		Thread thread = new Thread(() -> {
			simulateProgressIncrement(0d);
		});
		thread.start();
	}

	/**
	 * Method for doing the loading progress animation, Increasing the progBar +=
	 * 0.01d (until the progress reach 1.0d) every 10 ms by calling
	 * setProgBar(progressValue) method.
	 * 
	 * @param double of start progress 
	 */
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

	/**
	 * Method for Setting text of header Label
	 * 
	 * @param header text 
	 */
	public static void setLabel(String text) {
		EnterNameScene.headerText.setText(text);
	}

	/**
	 * Method for Setting up Loading (this method will be called whenever player
	 * trigger the playButton or pressing Enter. This method will Set
	 * GameManager.setPlayerName to the entered text and remove the textField
	 * automatically. Dispose any errorMessage, setLabel to "loading..", then start
	 * the process of Loading by calling startProgress() method.
	 */
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

	/**
	 * Implements thread in this method to sleep 1600 ms before staring the new
	 * GameScene
	 */
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
