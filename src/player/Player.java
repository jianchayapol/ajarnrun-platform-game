package player;

import java.util.ArrayList;

import base.Attackable;
import base.Damageable;
import item.Skill;
import item.weapon.Weapon;

public class Player implements Attackable, Damageable{

	private String character;
	private String url;
	private int maxLifePoint;
	private int currentLifePoint;
	private int defaultAtkPoint;
	private int currentAtkPoint;
	private int level;
	private int exp;
	private int coin;
	
	private int vx;
	private int vy;
	
	private Skill currentItem;
	private Weapon currentWeapon;
	private ArrayList<Weapon> inventory;

	Player(String character) {
		
		inventory = new ArrayList<Weapon>();
		switch (character) {
			
		case "VKJ":
			this.setUrl("VKJ-Character.png");
			this.setMaxLifePoint(120);
			this.setCurrentLifePoint(maxLifePoint);
			this.setDefaultAtkPoint(100);
			this.setCurrentAtkPoint(defaultAtkPoint);
			inventory.add(null);
			inventory.add(null);
			break;
			
		case "PVK":
			this.setUrl("PVK-Character.png");
			this.setMaxLifePoint(100);
			this.setCurrentLifePoint(maxLifePoint);
			this.setDefaultAtkPoint(120);
			this.setCurrentAtkPoint(defaultAtkPoint);
			inventory.add(null);
			break;
		
		case "NNN":
			this.setUrl("NNN-Character.png");
			this.setMaxLifePoint(100);
			this.setCurrentLifePoint(maxLifePoint);
			this.setDefaultAtkPoint(120);
			this.setCurrentAtkPoint(defaultAtkPoint);
			inventory.add(null);
			break;
			
		case "ATS":
			this.setUrl("ATS-Character.png");
			this.setMaxLifePoint(100);
			this.setCurrentLifePoint(maxLifePoint);
			this.setDefaultAtkPoint(120);
			this.setCurrentAtkPoint(defaultAtkPoint);
			inventory.add(null);
			break;
			
		default:
			this.setUrl("Student-Character.png");
			this.setMaxLifePoint(100);
			this.setCurrentLifePoint(maxLifePoint);
			this.setDefaultAtkPoint(120);
			this.setCurrentAtkPoint(defaultAtkPoint);
			inventory.add(null);
		}
		this.setCharacter(character);
		this.setLevel(1);
		this.setExp(0);
		this.setCoin(300);
		
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMaxLifePoint() {
		return maxLifePoint;
	}

	public void setMaxLifePoint(int maxLifePoint) {
		this.maxLifePoint = maxLifePoint;
	}

	public int getCurrentLifePoint() {
		return currentLifePoint;
	}

	public void setCurrentLifePoint(int currentLifePoint) {
		int val = currentLifePoint;
		if(val > getMaxLifePoint()) val = getMaxLifePoint();
		else if(val < 0) val = 0;
		this.currentLifePoint = val;
	}

	public int getDefaultAtkPoint() {
		return defaultAtkPoint;
	}

	public void setDefaultAtkPoint(int defaultAtkPoint) {
		this.defaultAtkPoint = defaultAtkPoint;
	}

	public int getCurrentAtkPoint() {
		return currentAtkPoint;
	}

	public void setCurrentAtkPoint(int currentAtkPoint) {
		int val = currentAtkPoint;
		if(val < getCurrentAtkPoint()) val = getDefaultAtkPoint();
		this.currentAtkPoint = val;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		int val = level;
		if(val < 0) val = 0;
		this.level = val;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		int val = exp;
		if(val<0) val = 0;
		this.exp = val;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		int val = coin;
		if(val<0) val = 0;
		this.coin = val;
		
	}

	public ArrayList<Weapon> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Weapon> inventory) {
		this.inventory = inventory;
	}

	@Override
	public int attack(Damageable character) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Skill getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(Skill currentItem) {
		this.currentItem = currentItem;
	}

	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	public void setCurrentWeapon(Weapon currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
	
	
	
}
