package logic.leaderboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import application.utility.CSVUtility;

/**
 * public class LeaderBoard is the utility class that contains useful static
 * method used in game's leaderboard management.
 * 
 * @author jianchayapol
 */
public class LeaderBoard {

	/**
	 * An ArrayList<String> for storing player's statistic data getting from the
	 * readCSV() from the CSVUtility class
	 */
	private static ArrayList<String> data;

	/**
	 * A SortedSet<PlayerStat> which is a container for Storing the Sorted
	 * PlayerStat in the correct order.
	 */
	private static SortedSet<PlayerStat> stats;

	/**
	 * A public static method that used to update the CSV file by Sorting all the
	 * data in the file Call static method readCSV() from the CSVUtility class to
	 * get all data in format of ArrayList of String Looping through the data file,
	 * Split each element with delimiter comma (",") to get name, level and, exp
	 * fields. Instantiate new PlayerStat objects by using those fields and Add to
	 * the container SortedSet of PlayerStat stats to store each player's PlayerStat
	 * in the correct order, then call public static method updateSortCSV(stats)
	 * from CSVUtilityto update the csv file. [ Note: We use the PlayerStat's Custom
	 * Comparater for PlayerStat objects sorting]
	 * 
	 */
	public static void sortUpdatedData() {
		stats = new TreeSet<PlayerStat>();
		data = CSVUtility.readCSV();
		for (String line : getData()) {
			String[] s = line.split(",");
			if (s.length == 3) {
				String name = s[0];
				int level = Integer.parseInt(s[1]);
				int exp = Integer.parseInt(s[2]);
				stats.add(new PlayerStat(name, level, exp));
			}
		}
		CSVUtility.updateSortCSV(stats);
	}

	/**
	 * Method to set the Data
	 * @param data from CSVUtility.readCSV
	 */
	public static void setData(ArrayList<String> data) {
		LeaderBoard.data = data;
	}

	/**
	 * Method to get the data
	 * @return data from CSVUtility.readCSV
	 */
	public static ArrayList<String> getData() {
		return data;
	}

}
