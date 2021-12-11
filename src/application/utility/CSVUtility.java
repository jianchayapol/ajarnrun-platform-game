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
		BufferedReader reader = null;
		BufferedWriter writer = null;
		boolean check = false;
		ArrayList<String> lines = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader("res/csv" + filename));
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				String[] l = line.split(",");
				if(text[0].equals(l[0])) {
					if(Integer.parseInt(l[1])>Integer.parseInt(text[1]) ) {
						lines.add(line);
						check = true;
					}
					else if(Integer.parseInt(l[1])==Integer.parseInt(text[1]) && Integer.parseInt(l[2])>Integer.parseInt(text[2])) {
						lines.add(line);
						check = true;
					}
				}else {
					lines.add(line);
				}
			}
			
			String textToWrite = "";
			
			writer = new BufferedWriter(new FileWriter(new File("res/csv" + filename), false));
			for (String s : lines) {
				textToWrite += (s.split(",")[0] + "," +s.split(",")[1]+ "," + s.split(",")[2] + "\n");
			}
			
			if(!check) {
				for (int i = 0; i < text.length; i++) {
					textToWrite += escapeSpecialCharacters(text[i]);
					if (i != text.length - 1) {
						textToWrite += ",";
					}
				}
				textToWrite += "\n";
			}
			
			writer.write(textToWrite);
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
				textToWrite += (name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase() + "," + level + "," + exp + "\n");
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
