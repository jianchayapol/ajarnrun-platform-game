package application.logic;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import player.Player;
import level.Level;

public class GameController {
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> platforms = new ArrayList<Node>();
	private Player player;
	private String[] level;
	private int levelWidth;
	
	
	public GameController() {
		setLevelWidth();
		setFirstLevelPlatform();
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
	
	private void setLevelWidth() {
		this.levelWidth = Level.LEVEL1[0].length() * 60;
	}
	
	private void setFirstLevelPlatform() {
		for (int i = 0; i < Level.LEVEL1.length; i++) {
			String line = Level.LEVEL1[i];
			for (int j = 0; j < line.length(); j ++) {
				switch (line.charAt(j)) {
				case '0':
					break;
				case '1':
					platforms.add(createImageViewForPlatform(j*60, i*60, 60, 60, "/image/platform/green_05.png"));
					break;
				}
			}
		}
	}
	
	private ImageView createImageViewForPlatform(int posX, int posY, int width, int height,String url) { 
		ImageView block = new ImageView(new Image(url));
		block.setFitWidth(width);
		block.setFitHeight(height);
		
		return block;
	}
}

