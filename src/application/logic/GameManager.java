package application.logic;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import level.Level;
import player.Player;
import sharedObject.RenderableHolder;
import view.ViewManager;

public class GameManager {
	private static HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private static ArrayList<Node> platforms = new ArrayList<Node>();
	private static Player player;
	private static boolean canJump;
	private static int levelWidth;
	private static boolean isMute;
	
	private static AnchorPane appRoot = new AnchorPane();
	private static AnchorPane gameRoot = new AnchorPane();
	private static AnchorPane uiRoot = new AnchorPane();
	
	private static int time;
	
	static {
		setLevelWidth();
		setFirstLevelPlatform();
		initializePlayer();
		setGameRootLayoutX();
		addGameRoot();
		addUIRoot();
		setIsMute(false);
		setTime(0);
	}
	
	/* ============================== PRIVATE STATIC METHOD ============================== */
	/* ==================== USE IN CONSTRUCTOR ==================== */
	
	private static void setLevelWidth() {
		levelWidth = Level.LEVEL1[0].length();
	}
	
	private static void setFirstLevelPlatform() {
		Pane background = new Pane();
		background.setPrefSize(800, 600);
		background.setBackground(new Background(new BackgroundImage(RenderableHolder.normalLevelImage,
				BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT,
				null
			)));
		for (int i = 0; i < Level.LEVEL1.length; i++) {
			String line = Level.LEVEL1[i];
			for (int j = 0; j < line.length(); j++) {
				ImageView platform;
				switch (line.charAt(j)) {
				case '0':
					break;
				case '1':
					platform = RenderableHolder.createImageViewForPlatform(j * 60, i * 60, "1");
					platforms.add(platform);
					break;
				case '2':
					platform = RenderableHolder.createImageViewForPlatform(j * 60, i * 60, "2");
					platforms.add(platform);
					break;
				case '3':
					platform = RenderableHolder.createImageViewForPlatform(j * 60, i * 60, "3");
					platforms.add(platform);
					break;
				case '4':
					platform = RenderableHolder.createImageViewForPlatform(j * 60, i * 60, "4");
					platforms.add(platform);
					break;
				case '5':
					platform = RenderableHolder.createImageViewForPlatform(j * 60, i * 60, "5");
					platforms.add(platform);
					break;
				case 'A':
					platform = RenderableHolder.createImageViewForPlatform(j * 60, i * 60, "f1");
					platforms.add(platform);
					break;
				case 'B':
					platform = RenderableHolder.createImageViewForPlatform(j * 60, i * 60, "f2");
					platforms.add(platform);
					break;
				case 'C':
					platform = RenderableHolder.createImageViewForPlatform(j * 60, i * 60, "f3");
					platforms.add(platform);
					break;
				case 'D':
					platform = RenderableHolder.createImageViewForPlatform(j * 60, i * 60, "f4");
					platforms.add(platform);
					break;
				default:
					break;
				}
			}
		}
		appRoot.getChildren().add(background);
	}
	
	private static void initializePlayer() {
		player = new Player(RenderableHolder.playerImage, 5, 5, 100, 100);
	}
	
	private static void setGameRootLayoutX() {
		player.translateXProperty().addListener((observer, oldValue, newValue) -> {
			int offSet = newValue.intValue();
			if (offSet > (ViewManager.getScreenWidth()/2) && offSet < levelWidth-(ViewManager.getScreenWidth()/2)) {
				gameRoot.setLayoutX(-(offSet-(ViewManager.getScreenWidth()/2)));
			}
		});
	}
	
	private static void addGameRoot() {
		appRoot.getChildren().add(gameRoot);
	}
	
	private static void addUIRoot() {
		appRoot.getChildren().add(uiRoot);
	}
	
	private static void setIsMute(boolean isMute) {
		GameManager.isMute = isMute;
	}
	
	private static void setTime(int time) {
		GameManager.time = time;
	}
	
	/* ==================== USE IN update() METHOD ==================== */
	
	private static void movePlayerX(int moveX) {
		
	}
	
	private static void movePlayerY(int moveY) {
		
	}
	
	private static void jumpPlayer() {
		
	}
	
	private static boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}
	
	/* ============================== PUBLIC STATIC METHOD ============================== */
	
	public static void update() {
		if (isPressed(KeyCode.W) && player.getTranslateY() >= 5) {
			jumpPlayer();
		}
		if (isPressed(KeyCode.A) && player.getTranslateX() >= 5) {
			movePlayerX(-5);
		}
		if (isPressed(KeyCode.D) && player.getTranslateX() <= levelWidth - 45) {
			movePlayerX(5);
		}
		if (player.getVelocityY() < 10) {
			player.setVelocityY(player.getVelocityY() + 1);
		}
		
		movePlayerY(player.getVelocityY());
	}
	
	public static void setKeysValue(KeyCode keyCode, boolean value) {
		keys.put(keyCode, value);
	}


	/* ============================== GETTER/SETTER ============================== */
	
	public static AnchorPane getAppRoot() {
		return appRoot;
	}

	public static void setAppRoot(AnchorPane appRoot) {
		GameManager.appRoot = appRoot;
	}
}
