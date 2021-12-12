package view;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import level.Level;
import player.Player;

public class GameViewManager {
	private static HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private static ArrayList<Node> platforms = new ArrayList<Node>();
	private static Player player = new Player("/image/character_maleAdventurer_attack0.png", 5, 0, 50, 480);
	private static boolean canJump;
	private static int levelWidth;
	private static boolean isMute = false;
	
	private static AnchorPane appRoot = new AnchorPane();
	private static AnchorPane gameRoot = new AnchorPane();
	private static AnchorPane uiRoot = new AnchorPane();
	
	private static int time = 0;
	
	static {
		
	}
	
	public static void setLevelWidth() {
		levelWidth = Level.LEVEL1[0].length();
	}
}
