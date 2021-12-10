package application.logic;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import player.Player;
import sharedObject.RenderableHolder;
import level.Level;

public class GameController {
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> platforms = new ArrayList<Node>();
	private Player player;
	private String[] level;
	private int levelWidth;
	private static boolean isMute = false;
	
	public GameController() {
		setLevelWidth();
		setFirstLevelPlatform();
		player = new Player("/image/haracter_malAdventure_attack0.png", 5, 0, 50, 480);
		player.translateXProperty().addListener((obs, old, newValue) -> {
			int offSet = newValue.intValue();
			if (offSet > 400 && offSet < levelWidth-400) {
				
			}
		});
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
					platforms.add(RenderableHolder.createImageViewForPlatform(j*60, i*60, "1"));
					break;
				case '2':
					platforms.add(RenderableHolder.createImageViewForPlatform(j*60, i*60, "2"));
					break;
				case '3':
					platforms.add(RenderableHolder.createImageViewForPlatform(j*60, i*60, "3"));
					break;
				case '4':
					platforms.add(RenderableHolder.createImageViewForPlatform(j*60, i*60, "4"));
					break;
				case '5':
					platforms.add(RenderableHolder.createImageViewForPlatform(j*60, i*60, "5"));
					break;
				case '6':
				}
			}
		}
	}	

	public static boolean IsMute() {
		return isMute;
	}

	public static void setMute(boolean isMute) {
		GameController.isMute = isMute;
	}

	
	private ImageView createImageViewForPlatform(int posX, int posY, int width, int height,String url) { 
		ImageView block = new ImageView(new Image(url));
		block.setFitWidth(width);
		block.setFitHeight(height);
		
		return block;
	}
}

