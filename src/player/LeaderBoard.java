package player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import player.PlayerStat;
import application.utility.CSVUtility;

public class LeaderBoard {

	private static ArrayList<String> data = CSVUtility.readCSV();
	private static SortedSet<PlayerStat> stats = new TreeSet();   
	
	public static void sortUpdatedData() {
		for (String line : data) {
			String[] s = line.split(",");
			String name = s[0];
			int level = Integer.parseInt(s[1]);
			int exp = Integer.parseInt(s[2]);
			stats.add(new PlayerStat(name, level, exp));
		}
		CSVUtility.updateSortCSV(stats);
	}
	
	
}
