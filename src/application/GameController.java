package application;

import java.util.ArrayList;

import item.weapon.Weapon;

public class GameController {
	
	public static final int initCoin = 100;
	
	private static int coin;
	private static int time;
	private static int level;
	private static ArrayList<Weapon> inventory;
	
	public GameController(){
		time = 0;
		level = 0;
		inventory = new ArrayList<Weapon>();
	}
	public static int getCoin() {
		return coin;
	}

	public static void setCoin(int coin) {
		GameController.coin = coin;
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		GameController.time = time;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		GameController.level = level;
	}

	public static ArrayList<Weapon> getInventory() {
		return inventory;
	}

	public static void setInventory(ArrayList<Weapon> inventory) {
		GameController.inventory = inventory;
	}

	public static int getInitcoin() {
		return initCoin;
	}

	
	
	
}
