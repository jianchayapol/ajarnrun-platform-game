package application.logic;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import logic.level.Level;
import player.Player;
import sharedObject.RenderableHolder;
import view.ViewManager;

public class GameManager {
	public static HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private static ArrayList<Node> platforms = new ArrayList<Node>();
	
	private static Player player;
	private static boolean canJump;
	private static int levelWidth;
	private static boolean isMute;
	private static int levelCount;
	
	private static AnchorPane appRoot = new AnchorPane();
	private static AnchorPane gameRoot = new AnchorPane();
	private static AnchorPane uiRoot = new AnchorPane();
	
	private static final int BLOCK_WIDTH = 60;
	private static final int BLOCK_HEIGHT = 60;
	
	
	// Time
	private static int time;
	
	// Level Finish Checker
	private static boolean isLevelFinish;
	private static int finishPositionX;
	private static int finishPositionY;
	
	// Player's stat
	private static int playerCurrentHP;
	private static int playerMaxHP;
	private static int playerCoin;
	private static int playerEXP;
	private static boolean isDead;
	
	// Enemy
	private static HashMap<Node, Boolean> enemy = new HashMap<Node, Boolean>();
	
	// Item
	private static HashMap<Node, Integer> item = new HashMap<Node, Integer>();
	
	// Node Counter
	private static int nodeCount;
	
	static {
		RenderableHolder.loadResource();
		initializeLevelCount();
		initializeNodeCount();
		setLevelWidth();
		setLevelPlatform();
		initializePlayer();
		initializePlayerMaxHP();
		initializePlayerCurrentHP();
		initializePlayerCoin();
		initializePlayerEXP();
		addPlayerToGameRoot();
		setGameRootLayoutX();
		addGameRoot();
		addUIRoot();
		setCanJump(true);
		setIsMute(false);
		setTime(120);
		initializeKeysValue();
		setIsLevelFinish(false);
	}
	
	/* ============================== PRIVATE STATIC METHOD ============================== */
	/* ==================== USE IN CONSTRUCTOR ==================== */
	
	private static void initializeLevelCount() {
		levelCount = 0;
	}
	
	private static void initializeNodeCount() {
		nodeCount = 0;
	}
	
	private static void setLevelWidth() {
		levelWidth = 0;
		levelWidth = Level.ALL_LEVEL[levelCount][0].length() * BLOCK_WIDTH;
	}
	
	private static void setLevelPlatform() {
		Rectangle background = new Rectangle(ViewManager.getScreenWidth(), ViewManager.getScreenHeight());
		background.setFill(new ImagePattern(RenderableHolder.normalLevelImage));
		for (int i = 0; i < Level.ALL_LEVEL[levelCount].length; i++) {
			String line = Level.ALL_LEVEL[levelCount][i];
			for (int j = 0; j < line.length(); j++) {
				ImageView platform;
				switch (line.charAt(j)) {
				case '0':
					break;
				case '1':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "1");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case '2':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "2");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case '3':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "3");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case '4':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "4");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case '5':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "5");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case 'A':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "f1");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case 'B':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "f2");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case 'C':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "f3");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case 'D':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "f4");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case 'E':
					platform = RenderableHolder.createImageViewForPlatform(j*BLOCK_WIDTH, i*BLOCK_HEIGHT, "E");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				case 'e':
					platform = RenderableHolder.createImageViewForPlatform(j*BLOCK_WIDTH, i*BLOCK_HEIGHT, "e");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					nodeCount++;
					break;
				
				// FINISH BLOCK
				case 'X':
					finishPositionX = j*BLOCK_WIDTH;
					finishPositionY = i*BLOCK_HEIGHT;
				default:
					break;
				}
			}
		}
		appRoot.getChildren().add(background);
	}
	
	private static void initializePlayer() {
		player = new Player(RenderableHolder.spritePlayerStanding, 0, 0, 200, 1);
	}
	
	private static void initializePlayerMaxHP() {
		playerMaxHP = 100;
	}
	
	private static void initializePlayerCurrentHP() {
		playerCurrentHP = playerMaxHP;
	}
	
	private static void initializePlayerCoin() {
		playerCoin = 0;
	}
	
	private static void initializePlayerEXP() {
		playerEXP = 0;
	}
	
	private static void addPlayerToGameRoot() {
		gameRoot.getChildren().add(player);
	}
	
	private static void setGameRootLayoutX() {
		player.translateXProperty().addListener((observer, oldValue, newValue) -> {
			int offSet = newValue.intValue();
			if (offSet > 400 && offSet < levelWidth-400) {
				gameRoot.setLayoutX(-(offSet-400));
			}
		});
	}
	
	private static void addGameRoot() {
		appRoot.getChildren().add(gameRoot);
	}
	
	private static void addUIRoot() {
		appRoot.getChildren().add(uiRoot);
	}
	
	private static void setTime(int time) {
		GameManager.time = time;
	}
	
	private static void setCanJump(boolean canJump) {
		GameManager.canJump = canJump;
	}
	
	private static void initializeKeysValue() {
		keys.put(KeyCode.W, false);
		keys.put(KeyCode.A, false);
		keys.put(KeyCode.D, false);
	}
	
	private static void levelCountInclement() {
		levelCount++;
	}
	
	/* ==================== USED TO SET UP NEW LEVEL ==================== */
	
	private static void clearUIRoot() {
		uiRoot.getChildren().clear();
	}
	
	private static void clearGameRoot() {
		gameRoot.getChildren().clear();
	}
	
	private static void clearAppRoot() {
		appRoot.getChildren().clear();
	}
	
	private static void clearPlatforms() {
		platforms.clear();
	}
	
	private static void resetFinishPosition() {
		finishPositionX = 0;
		finishPositionY = 0;
	}
	
	/* ==================== USED IN update() METHOD ==================== */
	
	/* TEST TEST */
	
	public static void testMoveX(int value) {
		boolean movingRight = value > 0;
		// all checker
		int playerTopY = (int) player.getTranslateY();
		int playerBottomY = playerTopY + player.getHeight();
		int playerLeftX = (int) player.getTranslateX();
		int playerRightX = playerLeftX + player.getWidth();
		
		int rowFeet = (int) (playerBottomY/60);
		if (playerBottomY % BLOCK_WIDTH == 0) {
			rowFeet -= 1;
		}
		int rowHead;
		if ((rowFeet + 1)*BLOCK_WIDTH - playerBottomY > 20) {
			rowHead = rowFeet - 2;
 		} else {
 			rowHead = rowFeet - 1;
 		}
		
		int columnLeft = (int) (playerLeftX/60);
		if (playerRightX % BLOCK_HEIGHT == 0) {
			columnLeft -= 1;
		}
		int columnRight;
		if (playerLeftX - columnLeft > 25) {
			columnRight = columnLeft + 2;
		} else {
			columnRight = columnLeft + 1;
		}
		for (int i = 0; i < Math.abs(value); i++) {
			if (movingRight) {
				if (Level.ALL_LEVEL[levelCount][rowHead].charAt(columnRight+1) != '0' || Level.ALL_LEVEL[levelCount][rowFeet].charAt(columnRight+1) != '0') {
					if (playerRightX >= (columnRight+1)*BLOCK_WIDTH) {
//						player.setTranslateX(player.getTranslateX() - 1);
					} else {
						player.setTranslateX(player.getTranslateX() + 1);
					} 
					
				} else {
					player.setTranslateX(player.getTranslateX() + 1);
				}
			} else if (!movingRight) {
				if (Level.ALL_LEVEL[levelCount][rowHead].charAt(columnLeft-1) != '0' || Level.ALL_LEVEL[levelCount][rowFeet].charAt(columnLeft-1) != '0') {
					if (playerLeftX <= (columnLeft*BLOCK_WIDTH)) {
//						player.setTranslateX(player.getTranslateX() + 1);
					} else {
						player.setTranslateX(player.getTranslateX() - 1);
					}
				} else {
					player.setTranslateX(player.getTranslateX() - 1);
				}
			}
		}
	}
	
	private static void testMoveY(int value) {
		boolean movingDown = value > 0;
		// all checker
		int playerTopY = (int) player.getTranslateY();
		int playerBottomY = playerTopY + player.getHeight();
		int playerLeftX = (int) player.getTranslateX();
		int playerRightX = playerLeftX + player.getWidth();
		
		int rowFeet = (int) (playerBottomY/60);
		if (playerBottomY % BLOCK_WIDTH == 0) {
			rowFeet -= 1;
		}
		int rowHead;
		if ((rowFeet + 1)*BLOCK_WIDTH - playerBottomY > 20) {
			rowHead = rowFeet - 2;
 		} else {
 			rowHead = rowFeet - 1;
 		}
		
		int columnLeft = (int) (playerLeftX/60);
		if (playerRightX % BLOCK_HEIGHT == 0) {
			columnLeft -= 1;
		}
		int columnRight;
		if (playerLeftX - columnLeft > 15) {
			columnRight = columnLeft + 1;
		} else {
			columnRight = columnLeft + 1;
		}
		
		for (int i = 0; i < Math.abs(value); i++) {
			
		}
	}
	
	/* TEST TEST */
	
	private static void movePlayerX(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (player.getTranslateX() + player.getWidth() - 20 == platform.getTranslateX()) {
							return;
						}
					} else {
						if (player.getTranslateX() == platform.getTranslateX() + BLOCK_WIDTH - 15) {
							return;
						}
					}
				}
			}
			player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
		}
	}

	private static void movePlayerY(int value) {
		boolean movingDown = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingDown) {
						if (player.getTranslateY() + player.getHeight() == platform.getTranslateY()) {
							setCanJump(true);
							return;
						}
					} else {
						if (player.getTranslateY() == platform.getTranslateY() + BLOCK_HEIGHT - 15) {
							return;
						}
					}
				}
			}
			player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
		}
	}

	private static void jumpPlayer(int jumpHeight) {
		if (canJump) {
			player.setVelocityY(player.getVelocityY() - jumpHeight);
			setCanJump(false);
		}
	}
	
	private static boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}
	
	private static void setPlayerCurrentHP(int HP) {
		playerCurrentHP = HP;
	}
	
	/* ============================== PUBLIC STATIC METHOD ============================== */
	
	public static void update() {
		if (isPressed(KeyCode.W) && player.getTranslateY() >= 5) {
			jumpPlayer(30);
		}
		if (isPressed(KeyCode.A) && player.getTranslateX() >= 5) {
//			jumpPlayer(3);
//			movePlayerX(-5);
			testMoveX(-5);
		}
		if (isPressed(KeyCode.D) && player.getTranslateX() <= levelWidth - 5 - player.getWidth()) {
//			jumpPlayer(3);
//			movePlayerX(5);
			testMoveX(5);
		}
		if (player.getVelocityY() < 10) {
			player.setVelocityY(player.getVelocityY() + 1);
		}
		movePlayerY(player.getVelocityY());
		player.update();
		
		if (player.getTranslateY() + player.getHeight() >= finishPositionY && player.getTranslateX() + player.getWidth() == finishPositionX) {
			setIsLevelFinish(true);
		}
		
//		System.out.println("translateX = " + player.getTranslateX());
//		System.out.println("translateX (cast int) = " + (int) player.getTranslateX()); // it works
	}
	
	public static void setUpNextLevel() {
		setIsLevelFinish(false);
		levelCountInclement();
		resetFinishPosition();
		setLevelWidth();
		clearUIRoot();
		clearGameRoot();
		clearAppRoot();
		clearPlatforms();
		setLevelPlatform();
		initializePlayer();
		setPlayerCurrentHP(playerMaxHP);
		addPlayerToGameRoot();
		setGameRootLayoutX();
		addGameRoot();
		addUIRoot();
		setCanJump(true);
		setIsMute(false);
		setTime(10);
		initializeKeysValue();
	}
	
	/* ============================== GETTER/SETTER ============================== */
	
	public static AnchorPane getAppRoot() {
		return appRoot;
	}

	public static void setAppRoot(AnchorPane appRoot) {
		GameManager.appRoot = appRoot;
	}
	
	public static void setIsMute(boolean isMute) {
		GameManager.isMute = isMute;
	}
	
	public static boolean getIsMute() {
		return isMute;
	}
	
	public static int getBlockWidth() {
		return BLOCK_WIDTH;
	}
	
	public static int getBlockHeight() {
		return BLOCK_HEIGHT;
	}
	
	public static void setKeysValue(KeyCode keyCode, boolean value) {
		keys.put(keyCode, value);
	}
	
	public static boolean getKeysValue(KeyCode key) {
		return keys.getOrDefault(key, false);
	}
	
	public static void setIsLevelFinish(boolean isFinish) {
		isLevelFinish = isFinish;
	}
	
	public static boolean getIsLevelFinish() {
		return isLevelFinish;
	}
	
	public static int getLevelCount() {
		return levelCount;
	}
	
	public static AnchorPane getUIRoot() {
		return uiRoot;
	}
	
	public static int getTime() {
		return time;
	}
	
	/* ============================== SET PLAYER'S STATS ============================== */
	
	public static void setPlayerMaxHP(int maxHP) {
		playerMaxHP = maxHP;
	}
	
	// setPlayerCurrentHP is not a public method
	
	public static void setPlayerCoin(int coin) {
		playerCoin = coin;
	}
	
	public static void setPlayerEXP(int EXP) {
		playerEXP = EXP;
	}
	
	/* ============================== SET PLAYER'S STATS ============================== */
	
	public static int getPlayerMaxHP() {
		return playerMaxHP;
	}
	
	public static int getPlayerCurrentHP() {
		return playerCurrentHP;
	}
	
	public static int getPlayerCoin() {
		return playerCoin;
	}
	
	public static int getPlayerEXP() {
		return playerEXP;
	}
	
	public static boolean isDead() {
		return isDead;
	}
}
