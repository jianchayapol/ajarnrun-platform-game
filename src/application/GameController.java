package application;

import java.awt.Point;
import java.util.ArrayList;

import entity.base.Direction;
import entity.character.PlayerCharacter;
import item.weapon.Weapon;

public class GameController {
	
	public static final int initCoin = 100;
	
	private static PlayerCharacter player;
	private static int coin;
	private static int time;
	private static int level;
	private static ArrayList<Weapon> inventory;
	
	public GameController(){
		time = 0;
		level = 0;
		player = new PlayerCharacter("ATS");
		inventory = new ArrayList<Weapon>();
		
	}
	
	public static void movePlayer(Direction d) {
		
		switch(d) {
		case LEFT :
			player.setDirection(d);
			player.moveForward();
		case RIGHT :
			player.setDirection(d);
			player.moveForward();
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
	public PlayerCharacter getPlayer() {
		return player;
	}
	public void setPlayer(PlayerCharacter player) {
		this.player = player;
	}

	
	
	
	
}
