package logic.leaderboard;

import application.utility.NameInputUtility;
import exception.WrongFormatPlayerNameException;
import view.EnterNameScene;

/**
 * An public class of PlayerStat that implements Comparable to custom its
 * comparator
 * 
 * @author jianchayapol
 *
 */
public class PlayerStat implements Comparable<PlayerStat> {

	/**
	 * A String of player's name. This field represent the input name from the
	 * player in EnterNameScene
	 */
	public String name;

	/*
	 * An integer of player's level. This field represents the player's level
	 * completing.
	 */
	private int level;

	/**
	 * An integer of player's exp which are automatically calculated during
	 * completing the game.
	 */
	private int exp;

	/**
	 * Construct the PlayerStat with the given name, level, and, exp.
	 * 
	 * @param name
	 * @param level
	 * @param exp
	 */
	public PlayerStat(String name, int level, int exp) {
		this.setName(name);
		this.setLevel(level);
		this.setExp(exp);
	}

	/**
	 * Override the the method compareTo() to use the custom comparator Comparing
	 * the PlayerStat objects by their level. If they are at the same level, compare
	 * them by their exp, and their name respectively.
	 */
	@Override
	public int compareTo(PlayerStat other) {
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

	/**
	 * Get the name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/*
	 * Set the correct format of name By using checkEnteredName(String name) from
	 * NameInputUtility class to check is the name is in the correct format or not,
	 * then Set the name.
	 */
	public void setName(String name) {
		if (NameInputUtility.checkEnteredName(name)) {
			this.name = name;
		}
	}

	/**
	 * A public method for getting the field level
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * A public method for setting the field level
	 * 
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * A public method for getting the field exp
	 * 
	 * @return exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * A public method for setting the field exp
	 * 
	 * @param exp
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
}
