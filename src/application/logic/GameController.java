package application.logic;

import java.util.ArrayList;

import entity.base.Direction;
import entity.character.Player;
import item.weapon.Weapon;

public class GameController {
	
	public static final int initialCoin = 100;
	
	private static Player player; 
	private static int coin;
	private static double time;
	private static int level;
	
	private static ArrayList<Weapon> inventory;
	
	public GameController(){
		time = 0;
		level = 1;
		player = new Player("VKJ");
		inventory = new ArrayList<Weapon>();
		
	}
	
	public static void movePlayer(Direction d) {
		
		switch(d) {
		case LEFT :
			player.setDirection(d);
			player.moveForward(d);
		case RIGHT :
			player.setDirection(d);
			player.moveForward(d);
		case UP :
			player.jump();
		}
	}
	
	public static int getCoin() {
		return coin;
	}

	public static void setCoin(int coin) {
		GameController.coin = coin;
	}

	public static double getTime() {
		return (int)(Math.round(time * 100))/100.0;
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
		return initialCoin;
	}
	public static Player getPlayer() {
		return player;
	}
	public static void setPlayer(Player player) {
		GameController.player = player;
	}

	
	
	
	
}
