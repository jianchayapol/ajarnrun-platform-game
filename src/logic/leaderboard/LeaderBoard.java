package logic.leaderboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import application.utility.CSVUtility;

public class LeaderBoard {

	private static ArrayList<String> data;
	private static SortedSet<PlayerStat> stats;  

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
//			String name = s[0];
//			int level = Integer.parseInt(s[1]);
//			int exp = Integer.parseInt(s[2]);
//			stats.add(new PlayerStat(name, level, exp));
		}
		CSVUtility.updateSortCSV(stats);
	}
	
	public static void setData(ArrayList<String> data) {
		LeaderBoard.data = data;
	}

	public static ArrayList<String> getData() {
		return data;
	}

}
