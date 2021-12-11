package view;

import java.io.FileInputStream;

import application.utility.ImageButtonType;
import button.ImageButton;
import exception.WrongFormatPlayerNameException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
	private StackPane mainPane;
	private static TextField textField;
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	
	public EnterNameScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		mainPane = new StackPane();
		setBackgroundImage(RenderableHolder.entrance_background_Image);
		setUpForm(mainPane);
		Scene scene = new Scene(mainPane);
		
		this.primaryStage = primaryStage;
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
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
	
	private void setUpForm(StackPane root) {
		
		root.setPrefHeight(HEIGHT);
		root.setPrefWidth(WIDTH);
		mainPane = root;
		
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
		
		VBox vbox = new VBox(40);
		
		Rectangle rec = new Rectangle(450,320);
		rec.setFill(Color.WHITESMOKE);
		rec.setOpacity(0.2);
		rec.setLayoutX(30);
		rec.setLayoutY(30);
		
		Label text1 = new Label("Enter your name!");
		text1.setTextFill(Color.WHITESMOKE);
		try {
			text1.setFont(Font.loadFont(new FileInputStream("res/font/YanoneKaffeesatz-SemiBold.ttf"), 32));
		} catch (Exception e) {
			text1.setFont(Font.font("Verdana", 32));
		}
		text1.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(text1,textField, playButton);
		
		pane.getChildren().addAll(rec,vbox);
		
		vbox.setAlignment(Pos.CENTER);
		playButton.setLayoutY(400);
		pane.setAlignment(Pos.CENTER);
		root.getChildren().add(pane);
		root.setAlignment(Pos.CENTER);
		
	}
	
	public static String getEnteredName() {
		return textField.getText();
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}


}
