package application.utility;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import logic.leaderboard.LeaderBoard;
import sharedObject.FontLoader;
import sharedObject.FontType;

public class DrawStringUtility {
	
	public static void fillLeaderBoard(SubScene subscene) {
		Label header = new Label("Rank" + "  " + "Lv." + "  " + "Player" + "  " + "Exp");
		header.setTextFill(Color.DARKRED);
		FontLoader.setFont(header, FontType.TELEGRAMA,22);

		int posX = 10;
		int posY = 120;

		Line lineShape = new Line(posX, posY, posX + 320, posY);
		lineShape.setStrokeWidth(4);
		lineShape.setFill(Color.DARKRED);

		posY += 20;

		header.setLayoutX(posX);
		header.setLayoutY(posY);

		AnchorPane subSceneRoot = (AnchorPane) subscene.getRoot();
		subSceneRoot.getChildren().addAll(lineShape, header);

		// Read Data from CSV
		LeaderBoard.setData(CSVUtility.readCSV());
		LeaderBoard.sortUpdatedData();
		ArrayList<String> data = CSVUtility.readCSV();

		int n = data.size();
		if (n > 9)
			n = 9;
		posX += 3;
		posY += 15;
		for (int i = 1; i <= n; i++) {
			posY += 40;
			String[] stat = data.get(i - 1).split(",");
			Label line = new Label(fillString("[" + String.valueOf(i) + "]", 6) + fillString(stat[1], 5)
					+ fillString(stat[0], 9) + fillString(stat[2], 4));
			setUpText(line, i);
			line.setLayoutX(posX);
			line.setLayoutY(posY);

			subSceneRoot.getChildren().add(line);
		}

	}
	
	public static void fillLeaderBoard(AnchorPane pane) {
		Label header = new Label("Rank" + "  " + "Lv." + "  " + "Player" + "    " + "Exp");
		header.setTextFill(Color.WHITE);
		FontLoader.setFont(header, FontType.TELEGRAMA,18);

		int posX = 45;
		int posY = 10;

		header.setLayoutX(posX);
		header.setLayoutY(posY);

		pane.getChildren().addAll( header);

		// Read Data from CSV
		LeaderBoard.setData(CSVUtility.readCSV());
		LeaderBoard.sortUpdatedData();
		ArrayList<String> data = CSVUtility.readCSV();

		int n = data.size();
		if (n > 5)
			n = 5;
		posX += 3;
		posY += 10;
		for (int i = 1; i <= n; i++) {
			posY += 40;
			String[] stat = data.get(i - 1).split(",");
			Label line = new Label(fillString("[" + String.valueOf(i) + "]", 6) + fillString(stat[1], 5)
					+ fillString(stat[0], 10) + fillString(stat[2], 4));
			line.setLayoutX(posX);
			line.setLayoutY(posY);
			line.setTextFill(Color.WHITE);
			FontLoader.setFont(line, FontType.TELEGRAMA,18);
			pane.getChildren().add(line);
		}

	}

	private static void setUpText(Label line, int i) {
		switch (i) {
		case 1:
			line.setTextFill(Color.rgb(164, 129, 17));
			break;
		case 2:
			line.setTextFill(Color.rgb(113, 112, 110));
			break;
		case 3:
			line.setTextFill(Color.rgb(93, 45, 36));
			break;
		default:
			line.setTextFill(Color.BLACK);
			break;
		}
		FontLoader.setFont(line, FontType.TELEGRAMA, 21);
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
