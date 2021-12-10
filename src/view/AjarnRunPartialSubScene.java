package view;

import java.io.FileInputStream;
import java.util.ArrayList;

import application.utility.CSVUtility;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import player.LeaderBoard;

public class AjarnRunPartialSubScene extends SubScene {

	private static final String BACKGROUND_IMAGE = "/mainSceneBackground_withoutLogo_fixed.png";
	private final String FONT_PATH = "res/font/YanoneKaffeesatz-SemiBold.ttf";
	private final String FONT_PATH2 = "res/font/Courier.ttf";

	public AjarnRunPartialSubScene(Parent mainPane, double width, double height) {
		super(mainPane, width, height);
		prefWidth(width);
		prefHeight(height);
		BackgroundImage img = new BackgroundImage(new Image(BACKGROUND_IMAGE, 800, 600, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		/* AnchorPane root2 = (AnchorPane) this.getRoot(); */
	}

	public AjarnRunPartialSubScene(String imageURL, String altText, int width, int height) {
		super(new AnchorPane(), width, height);
		prefWidth(width);
		prefHeight(height);
		BackgroundImage img = new BackgroundImage(new Image(imageURL, width, height, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane subSceneRoot = (AnchorPane) this.getRoot();
		subSceneRoot.setBackground(new Background(img));

		switch (altText) {
		case "infoButton":
			setLayoutX(-width - 100);
			setLayoutY(20);
			setEffect(new DropShadow());
			break;
		case "leaderBoard":
			setLayoutX(900);
			setLayoutY(20);
			setEffect(new DropShadow());
			fillLeaderBoard();

			break;
		case "newGame":
			setLayoutX(0);
			setLayoutY(0);
		default:
			break;
		}
	}

	public void moveSubScene(String altText) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.5));
		transition.setNode(this);
		switch (altText) {
		case "infoButtonUnpressed":
			transition.setToX(this.getWidth() + 120);
			transition.setToY(0);
			break;
		case "infoButtonPressed":
			transition.setToX(-this.getWidth() - 120);
			transition.setToY(0);
			break;
		case "leaderBoardUnpressed":
			transition.setToX(-this.getWidth() - 120);
			transition.setToY(0);
			break;
		case "leaderBoardPressed":
			transition.setToX(this.getWidth() + 120);
			transition.setToY(0);
			break;
		default:
			break;
		}
		transition.play();
	}

	public void fillLeaderBoard() {
		Label header = new Label("Rank" + "         " + "Lv." + "        " + "Player" + "             " + "Exp");
		header.setTextFill(Color.DARKRED);
		try {
			header.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 32));
		} catch (Exception e) {
			header.setFont(Font.font("Verdana", 32));
		}

		int posX = 15;
		int posY = 120;
		header.setLayoutX(posX);
		header.setLayoutY(posY);

		AnchorPane subSceneRoot = (AnchorPane) this.getRoot();
		subSceneRoot.getChildren().add(header);

		// Read Data from CS
		LeaderBoard.sortUpdatedData();
		ArrayList<String> data = CSVUtility.readCSV();
		int n = data.size();
		if (n > 10)
			n = 10;
		posX += 3;
		posY += 15;
		for (int i = 1; i <= n; i++) {
			posY += 40;
			String[] stat = data.get(i - 1).split(",");
			Label line = new Label(fillString("[" + String.valueOf(i) + "]", 6) + fillString(stat[1], 4)
					+ fillString(stat[0], 8) + fillString(stat[2], 4));
			line.setTextFill(Color.DARKRED);
			try {
				line.setFont(Font.loadFont(new FileInputStream(FONT_PATH2), 24));
			} catch (Exception e) {
				line.setFont(Font.font("Verdana", 24));
			}

			line.setLayoutX(posX);
			line.setLayoutY(posY);

			subSceneRoot.getChildren().add(line);
		}

	}

	public static String fillString(String s, int n) {
		int n2 = n;
		if (n > s.length()) {
			n2 = s.length();
		}
		String output = s.substring(0, n2);
		while (output.length() < n) {
			output += " ";
		}
		return output;
	}

}
