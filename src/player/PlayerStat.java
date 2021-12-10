package player;

import exception.EmptyPlayerNameException;

public class PlayerStat implements Comparable<PlayerStat> {

	private String name;
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
		if (this.getExp() != other.getExp()) {
			comp = (this.getExp() < other.getExp()) ? 1 : -1;
		} else if (this.getLevel() != other.getLevel()) {
			comp = (this.getLevel() < other.getLevel()) ? 1 : -1;
		}
		else {
			comp = this.getName().compareTo(other.getName());
		}
		return comp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name){
		
		try{
			if (name.isBlank()) {
				throw new EmptyPlayerNameException();
			}
			else this.name = name;
		}
		catch(EmptyPlayerNameException e) {
			System.out.println(e.getMessage());
		}
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
