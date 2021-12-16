package application.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;

import logic.leaderboard.PlayerStat;

/**
 * This CSVUtility public class is an utility that provides useful static
 * methods for all CSV File management which includes Reading, Writing, Adding,
 * and Sorting File leader-board.csv
 * 
 * @author jianchayapol
 *
 */

public class CSVUtility {

	/**
	 * An String of filename [.csv] This file collects all player's statistics data.
	 */
	private static String filename = "/leader-board.csv";

	/**
	 * An public static method for reading all data in this the csv file by reading
<<<<<<< HEAD
	 * through each line of the file and add to the ArrayList of String
||||||| 626e6b5
	 * through each line of the file and add to the ArrayList<String>
=======
	 * through each line of the file and add to the ArrayList
>>>>>>> 7b3919d7b2b41a11359d0f6515506feffd1473dd
	 * 
<<<<<<< HEAD
	 * @return an ArrayList<String> containing all text data in format of {
	 *         "Name,1,0", "Name2,1,0", ... }
||||||| 626e6b5
	 * @return an ArrayList<String> containing all text data in format of {
	 * "Name,1,0", "Name2,1,0", ... }
=======
	 * @return an ArrayList containing all text data in format of {
	 * "Name,1,0", "Name2,1,0", ... }
>>>>>>> 7b3919d7b2b41a11359d0f6515506feffd1473dd
	 */
	public static ArrayList<String> readCSV() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("res/csv" + filename));
			ArrayList<String> lines = new ArrayList<>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			return lines;

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * An public static method for appending the player's progress to the CSV file
	 * by reading through each line of the file and check conditions of adding to
	 * filter some line that does not match the condition out. condition: If the
	 * name of toAddText is not exists in the file, mark it as new HighScore. If
	 * name exists, but the new one is greater, we will mark the new one as a
	 * HighScore ,and filter the exists one out. While reading the file, if the name
	 * doesn't match with the toAddText name, will be automatically appended to the
<<<<<<< HEAD
	 * temporary ArrayList of String tmpLines. Finally, If the toAddText is a new
||||||| 626e6b5
	 * temporary ArrayList<String> tmpLines. Finally, If the toAddText is a new
=======
	 * temporary ArrayList tmpLines. Finally, If the toAddText is a new
>>>>>>> 7b3919d7b2b41a11359d0f6515506feffd1473dd
	 * HighScore, add it to the file
	 * 
	 * @param toAddText A Sting[] of length 3 that contains player's name, level,
	 *                  and, exp respectively
	 */
	public static void appendToCSV(String[] toAddText) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		boolean isHighScore = false;
		ArrayList<String> tmpLines = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader("res/csv" + filename));
			String line = null;

			boolean isExist = false;
			while ((line = reader.readLine()) != null) {

				String[] textSplitted = line.split(",");
				String name = toAddText[0];
				String otherName = textSplitted[0];
				int level = Integer.parseInt(toAddText[1]);
				int otherLevel = Integer.parseInt(textSplitted[1]);
				int exp = Integer.parseInt(toAddText[2]);
				int otherExp = Integer.parseInt(textSplitted[2]);

				if (name.equals(otherName)) {
					isExist = true;
					if (level >= otherLevel) {
						isHighScore = true;
					} else if ((level == otherLevel) && (otherExp == exp)) {
						isHighScore = true;
					} else {
						tmpLines.add(line);
					}
				} else {
					tmpLines.add(line);
				}

				if (!isExist) {
					isHighScore = true;
				}
			}

			// Writing to CSV File

			String textToWrite = "";
			writer = new BufferedWriter(new FileWriter(new File("res/csv" + filename), false));
			for (String s : tmpLines) {
				textToWrite += (s.split(",")[0] + "," + s.split(",")[1] + "," + s.split(",")[2] + "\n");
			}
			if (isHighScore) {
				textToWrite += getCSVFormat(toAddText);
			}

			writer.write(textToWrite);
			writer.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
	}

	/**
	 * A public static method to update the csv file to become the correct sorted
	 * order. By Getting the SortedSet stats, and overwrite the file
	 * with all the data obtained from each element of stats. [ Note: use the GETTER
	 * to obtained all the PlayerStat object's field.] then write the text to the
	 * .csv file in the correct format of "Name,1,0"
	 * 
	 * @param stats
	 */
	public static void updateSortCSV(SortedSet<PlayerStat> stats) {
		BufferedWriter writer = null;
		try {

			writer = new BufferedWriter(new FileWriter(new File("res/csv" + filename), false));
			// obtain all the PlayerStat's fields to write to csv file.
			String textToWrite = "";
			for (PlayerStat p : stats) {
				String name = p.getName();
				String level = String.valueOf(p.getLevel());
				String exp = String.valueOf(p.getExp());
				textToWrite += (name + "," + level + "," + exp + "\n");
			}
			writer.write(textToWrite);
			writer.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
	}

	/**
	 * private static method called to remove all the CSV file case sensitive like
	 * special characters, commas.
	 * 
	 * @param data
	 * @return escapedData the String output that escaped all sensitive cases.
	 */
	private static String escapeSpecialCharacters(String data) {
		String escapedData = data.replaceAll("\\R", " ");
		if (data.contains(",") || data.contains("\"") || data.contains("'")) {
			data = data.replace("\"", "\"\"");
			escapedData = "\"" + data + "\"";
		}
		return escapedData;
	}

	/**
	 * An private static method to get the correct CSV Format of String "Name,1,0"
	 * from the Array of String By looping through the String[] text and joining
	 * each element that are formatted correctly together with comma (",").
	 * 
	 * @return textToWrite the correct format of string to write to A
	 */
	private static String getCSVFormat(String[] text) {
		String textToWrite = "";
		for (int i = 0; i < text.length; i++) {
			textToWrite += escapeSpecialCharacters(text[i]);
			if (i != text.length - 1) {
				textToWrite += ",";
			}
		}
		textToWrite += "\n";
		return textToWrite;
	}

}
