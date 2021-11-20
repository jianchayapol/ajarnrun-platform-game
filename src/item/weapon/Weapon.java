package item.weapon;

import enemy.Enemy;

public abstract class Weapon {
	String name;
	String url;
	int cost;
	int attack;
	
	public abstract int attack(Enemy target);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getAttack() {
		return attack;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return attack;
	}
	
	
}
