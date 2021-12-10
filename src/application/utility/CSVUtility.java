package application.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;

import player.PlayerStat;

public class CSVUtility {

	public static String filename = "/leader-board.csv";

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

	public static void appendToCSV(String[] text) {
		BufferedWriter writer = null;
		try {

			writer = new BufferedWriter(new FileWriter(new File("res/csv" + filename), true));

			String textToWrite = "";
			for (int i = 0; i < text.length; i++) {
				textToWrite += text[i];
				if (i != text.length - 1) {
					textToWrite += ",";
				}
			}

			writer.write(textToWrite + "\n");
			System.out.println("Successfully Added");
			writer.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
	}

	public static void updateSortCSV(SortedSet<PlayerStat> stats) {
		BufferedWriter writer = null;
		try {

			writer = new BufferedWriter(new FileWriter(new File("res/csv" + filename), false));

			String textToWrite = "";

			for (PlayerStat p : stats) {
				String name = p.getName();
				String level = String.valueOf(p.getLevel());
				String exp = String.valueOf(p.getExp());
				textToWrite += (name + "," + level + "," + exp + "\n");
			}
			writer.write(textToWrite);
			System.out.println("Successfully Updated!");
			writer.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
	}

	public static String escapeSpecialCharacters(String data) {
		String escapedData = data.replaceAll("\\R", " ");
		if (data.contains(",") || data.contains("\"") || data.contains("'")) {
			data = data.replace("\"", "\"\"");
			escapedData = "\"" + data + "\"";
		}
		return escapedData;
	}

}
