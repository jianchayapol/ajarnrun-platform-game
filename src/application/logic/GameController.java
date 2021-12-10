package application.logic;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import player.Player;

public class GameController {
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> platforms = new ArrayList<Node>();
	private Player player;
	
	
	public GameController() {
		
	}
	
	public static void platformStart() {
		initializePlatform();
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				update();
			}
		};
		timer.start();
	}
	private static void update() {
	}
	private static void initializePlatform() {
	}
}
