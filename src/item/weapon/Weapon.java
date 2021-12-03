package item.weapon;

import entity.character.PlayerCharacter;
import entity.enemy.Enemy;
import exception.EquipItemFailedException;
import item.base.Equipable;

public abstract class Weapon implements Equipable {

	protected String name;
	protected String imageUrl;
	protected int cost;
	protected int attack;

	public abstract int hit(Enemy target);

	@Override
	public boolean equip(PlayerCharacter player) throws EquipItemFailedException {
		// TODO Auto-generated method stub
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
