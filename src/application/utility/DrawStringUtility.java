package application.utility;

import java.util.ArrayList;

import gui.element.HowToPlayBox;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import logic.leaderboard.LeaderBoard;
import sharedObject.FontLoader;
import sharedObject.FontType;

/**
 * This public class, DrawStringUtility is an utility that provides useful
 * static methods to Draw String on GUI pane and Scene.
 * 
 * @author jianchayapol
 *
 */
public class DrawStringUtility {

	/**
	 * This method is for filling String text of leaderboard data from the csv file
	 * to the LeaderBoard SubScene. The concept of filling the text is to by using
	 * Label and Line as the components and setting up their LayoutX and LayoutY
	 * Setting up the components' format. Initialize int posX, posY with the
	 * starting point and use as a layout counter in both x-axis and y-axis. In the
	 * row and column part, use method sortUpdatedData() from LeaderBoard first,
	 * then get the data in form of ArrayList by using readCSV() from
	 * CSVUtility. Set limit number of data to not exceed 9. Looping through all the
	 * data and fill the row and adding LayoutX and LayooutY for line spacing. Set
	 * Text color to white and Use method setFont() from FontLoader to set TELEGRAMA
	 * Font with Font size = 18Add all the components to the root of the SubScene
	 * 
	 * @param subscene
	 */
	public static void fillLeaderBoard(SubScene subscene) {
		Label header = new Label("Rank" + "  " + "Lv." + "  " + "Player" + "  " + "Exp");
		header.setTextFill(Color.DARKRED);
		FontLoader.setFont(header, FontType.TELEGRAMA, 22);

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
			setRankingColor(line, i);
			line.setLayoutX(posX);
			line.setLayoutY(posY);

			subSceneRoot.getChildren().add(line);
		}

	}

	/**
	 * This is the method used to fill String text of leaderboard data from the csv
	 * file to the pane. The concept of filling the text is the smae as the previous
	 * method, by using Label as the components and setting up their LayoutX and
	 * LayoutY Setting up the components' format. Initialize int posX, posY with the
	 * starting point and use as a layout counter in both x-axis and y-axis. In the
	 * row and column part, use method sortUpdatedData() from LeaderBoard first,
	 * then get the data in form of ArrayList by using readCSV() from
	 * CSVUtility. Set limit number of data to not exceed 5. Looping through all the
	 * data and fill the row and adding LayoutX and LayooutY for line spacing. Set
	 * Text color to white and Use method setFont() from FontLoader to set TELEGRAMA
	 * Font with Font size = 18 Add all the components to the pane
	 * 
	 * @param pane
	 */
	public static void fillLeaderBoard(AnchorPane pane) {
		Label header = new Label("Rank" + "  " + "Lv." + "  " + "Player" + "    " + "Exp");
		header.setTextFill(Color.WHITE);
		FontLoader.setFont(header, FontType.TELEGRAMA, 18);

		int posX = 45;
		int posY = 10;

		header.setLayoutX(posX);
		header.setLayoutY(posY);

		pane.getChildren().addAll(header);

		// Read Data from CSV
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
			FontLoader.setFont(line, FontType.TELEGRAMA, 18);
			pane.getChildren().add(line);
		}

	}

	/**
	 * This is the method used to fill String text of HowToPlay to the pane. The
	 * concept of filling the text is the smae as the previous method, by using
	 * Label as the components and setting up their LayoutX and LayoutY Setting up
	 * the components' format. Initialize int posX, posY with the starting point and
	 * use as a layout counter in both x-axis and y-axis. In the row and column
	 * part, use method getInstructions() from HowToPlayBox class first to get the
	 * text to fill in format of String[]. Looping through all the data and fill the
	 * row and adding LayoutX and LayooutY for line spacing. Set Text color to white
	 * and Use method setFont() from FontLoader to set TELEGRAMA Font with Font size
	 * = 18 Add all the components to the pane
	 * 
	 * @param pane
	 */
	public static void fillHowToPlay(AnchorPane pane) {

		int posX = 25;
		int posY = 10;

		String[] instructions = HowToPlayBox.getInstructions();

		for (int i = 0; i < instructions.length; i++) {
			Label line = new Label(instructions[i]);
			line.setLayoutX(posX);
			line.setLayoutY(posY);
			line.setTextFill(Color.WHITE);
			posY += 40;
			FontLoader.setFont(line, FontType.TELEGRAMA, 18);
			pane.getChildren().add(line);
		}

	}

	/**
	 * This private static method is use to coloring the Label component. Using
	 * integer i as a ranking level. Color the top 3 Ranking Label to be Gold,
	 * Silver, and Copper respectively, otherwise Label is colored to be black. Use
	 * method setFont() from FontLoader to set TELEGRAMA Font with Font size = 21
	 * 
	 * @param line
	 * @param i
	 */
	private static void setRankingColor(Label line, int i) {
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

	/**
	 * This private static method for filling the String s to fit the length of n.
	 * For example, fillString("AB",5) will automatically return "AB " ,and also
	 * fillString("ABCDEF",3) will return "ABC "
	 * 
	 * @param s
	 * @param n
	 * @return String output
	 */
	private static String fillString(String s, int n) {
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
