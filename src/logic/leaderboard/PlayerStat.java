package logic.leaderboard;

import exception.WrongFormatPlayerNameException;
import view.EnterNameScene;

public class PlayerStat implements Comparable<PlayerStat> {

	public String name;
	private int level;
	private int exp;

	public PlayerStat(String name, int level, int exp) {
		this.setName(name);
		this.setLevel(level);
		this.setExp(exp);
	}

	@Override
	public int compareTo(PlayerStat other) {
		// TODO Auto-generated method stub
		int comp = 0;
		if (this.getLevel() != other.getLevel()) {
			comp = (this.getLevel() < other.getLevel()) ? 1 : -1;
		} else if (this.getExp() != other.getExp()) {
			comp = (this.getExp() < other.getExp()) ? 1 : -1;
		} else {
			comp = this.getName().compareTo(other.getName());
		}
		return comp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (checkEnteredName(name)) {
			this.name = name;
		}
	}

	public static boolean checkEnteredName(String name) {
		try {
			if (name.isBlank()) {
				EnterNameScene.setErrorMessage("PlayerName cannot be blank!");
				throw new WrongFormatPlayerNameException("PlayerName cannot be blank!");
			} else if (name.length() > 8) {
				EnterNameScene.setErrorMessage("PlayerName Cannot Exceed 8 Characters");
				throw new WrongFormatPlayerNameException("PlayerName Cannot Exceed 8 Characters");
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
}
