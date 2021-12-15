package gui.element;

import gui.button.MainButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sharedObject.RenderableHolder;
import view.GameScene;

public class CongratsBox extends StackPane {

	private static Rectangle bgRec;
	private static VBox mainBox;
	private static VBox statPane;
	private static HBox centerBox;
	private static MainButton leaderboardButton;
	private static MainButton exitButton;
	private Rectangle rec = new Rectangle(280, 380);
	private static ImageView peopleImage = new ImageView(RenderableHolder.playerImage);
	private static ImageView congratsImage = new ImageView(RenderableHolder.congrats_Image);
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	
	public CongratsBox() {
		initComponents();
		bgRec = new Rectangle(WIDTH, HEIGHT);
		bgRec.setFill(Color.BLACK);
		bgRec.setOpacity(0.8);
		AnchorPane mainPane = new AnchorPane();
		mainPane.getChildren().addAll(bgRec,mainBox);
		this.setAlignment(Pos.CENTER);
		mainBox.setLayoutX(180);
		mainBox.setLayoutY(140);
		this.getChildren().addAll(mainPane);
	}

	private void initComponents() {
		mainBox = new VBox(40);
		centerBox = new HBox(30);
		statPane = new VBox(40);
		mainBox.setAlignment(Pos.CENTER);
		
		initializeLeaderboardButton();
		initializeExitButton();
		
		statPane.getChildren().addAll(new EndGameStatBox(),exitButton);
		peopleImage.setFitWidth(160);
		peopleImage.setFitHeight(200);
		centerBox.getChildren().addAll(peopleImage,statPane);
		mainBox.getChildren().addAll(congratsImage,centerBox);
		setUpMouseEnter();

	}
	
	private void initializeLeaderboardButton() {
		leaderboardButton = new MainButton("Leaderboard");
		leaderboardButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
			}
		});
	}
	
	private void initializeExitButton() {
		exitButton = new MainButton("Exit");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameScene.getStage().close();
			}
		});
	}

	// ====================== private static method(s) ============================

	
	private void setUpMouseEnter() {

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				CongratsBox.congratsImage.setEffect(new DropShadow());
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				CongratsBox.congratsImage.setEffect(null);
			}
		});
	}


}