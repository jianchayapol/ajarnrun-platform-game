package application.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import application.utility.CSVUtility;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import gui.element.GameHUD;
import gui.element.MoneyBox;
import logic.level.Level;
import platform.Coin;
import platform.FinishFlag;
import platform.HurtPlatform;
import platform.base.Collectable;
import platform.base.Damagable;
import platform.Book;
import player.Player;
import sharedObject.RenderableHolder;
import view.GameScene;
import view.ViewManager;

/**
 * GameManager is the class that control gameplay state. It start with
 * initialize share objects, then the level, player, game root, and so on. The
 * scene that controls gameplay state can call a public static method called
 * update which check all the thing that happen within one frame.
 * 
 * @author Mos
 *
 */
public class GameManager {
	/**
	 * A HashMap<KeyCode, Boolean> that check which key player is pressing.
	 */
	private static HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	/**
	 * An ArrayList<Node> that contains all the blocks that player is playing on.
	 */
	private static ArrayList<Node> platforms = new ArrayList<Node>();
	/**
	 * Player object named player is the player themself. This object can be moved
	 * as you press a specific key
	 */
	private static Player player;
	/**
	 * A boolean to check that should player jump at that frame.
	 */
	private static boolean canJump;
	/**
	 * A levelWidth is the actual level width with the pixel unit.
	 */
	private static int levelWidth;
	/**
	 * A boolean to check if the song is muted or not at that frame.
	 */
	private static boolean isMute;
	/**
	 * A boolean to check has player click continue button yet
	 */
	private static boolean isPressedNextLv;
	/**
	 * An integer to count a level. It is also used as an index.
	 */
	private static int levelCount;
	/**
	 * An anchor pane (appRoot) is the main pane is game scene.
	 */
	private static AnchorPane appRoot = new AnchorPane();
	/**
	 * An anchor pane (gameRoot) is the pane that contains all platform or Node just
	 * like platforms.
	 */
	private static AnchorPane gameRoot = new AnchorPane();
	/**
	 * An anchor pane (uiRoot) is the pane that contains game HUD and other buttons.
	 */
	private static AnchorPane uiRoot = new AnchorPane();

	/**
	 * An integer that is used to define block's width
	 */
	private static final int BLOCK_WIDTH = 120;
	/**
	 * An integer that is used to define block's height
	 */
	private static final int BLOCK_HEIGHT = 120;
	/**
	 * A boolean to check if player is dead.
	 */
	private static boolean isDead;
	/**
	 * An integer to set the positionX player must be in order to finish the level.
	 * This field can be changed as the level change.
	 */
	private static int finishPositionX;
	/**
	 * An integer to set the positionY player must be in order to finish the level.
	 * This field can be changed as the level change.
	 */
	private static int finishPositionY;
	/**
	 * A boolean to check if the level player is playing is finish.
	 */
	private static boolean isLevelFinish;
	/**
	 * The player's name.
	 */
	private static String playerName;
	/**
	 * The player's current HP.
	 */
	private static int playerCurrentHP;
	/**
	 * The player's max HP. This field is used to set the player's initial current
	 * HP when this game change a level.
	 */
	private static int playerMaxHP;
	/**
	 * The player's coin. This field tell the player how much money he/she/they has.
	 */
	private static int playerCoin;
	/**
	 * The player's EXP. This field show how good you are at this game, also, this
	 * field is used when the game is sorting its leaderboard.
	 */
	private static int playerEXP;
	/**
	 * The Random object. This field is used when we need to random something in the
	 * game's logic.
	 */
	private static Random random = new Random();
	/**
	 * A jump bonus. It is craeted as 0, then, increased as the player buy an
	 * upgrade.
	 */
	private static int jumpBonus;
	/**
	 * A speed bonus. It is created as 0, then, increased as the player buy an
	 * upgrade.
	 */
	private static int speedBonus;
	/**
	 * An ArrayList that contains all the coins in the map.
	 */
	private static ArrayList<Node> coins = new ArrayList<Node>();
	/**
	 * An ArrayList that contains all the books in the map. This field is decreased
	 * as the player collect a coin.
	 */
	private static ArrayList<Node> books = new ArrayList<Node>();
	/**
	 * An integer that show how much book the current level has. This field is
	 * decreased as the player collect a book.
	 */
	private static int bookCount;
	/**
	 * A boolean to check that has player collect all the books that were in the
	 * map.
	 */
	private static boolean isMissingBook = false;
	/**
	 * A static constructor for GameManager class.
	 * It starts with call loadResource() method from RenderableHolder to initialize all its fields.
	 * The reason why this class will always be called before RenderableHolder class is RenderableHolder need to get block's width and hiehgt from this class.
	 * Then, it initialize almost all the fields dependently on usability's order.
	 */
	static {
		RenderableHolder.loadResource();
		initializeLevelCount();
		setLevelWidth();
		initializeBookCount();
		setLevelPlatform();
		initializePlayer();
		setJumpBonus(0);
		setSpeedBonus(0);
		initializePlayerMaxHP();
		initializePlayerCurrentHP();
		initializePlayerCoin();
		initializePlayerEXP();
		addPlayerToGameRoot();
		setGameRootLayoutX();
		addGameRoot();
		addUIRoot();
		setCanJump(false);
		setMute(false);
		initializeKeysValue();
		setLevelFinish(false);
	}

	/* ============================== PRIVATE STATIC METHOD ============================== */

	/* ==================== USE IN CONSTRUCTOR ==================== */

	/**
	 * A method that set levelCount to 0. This is field is used only in the
	 * constructor.
	 */
	private static void initializeLevelCount() {
		levelCount = 0;
	}

	/**
	 * A method that set levelWidth to the real level's width widh pixel unit. The
	 * level width can be as long as the creator want, the number that is assigned
	 * to levelWidth is not fixed.
	 */
	private static void setLevelWidth() {
		levelWidth = 0;
		levelWidth = Level.ALL_LEVEL[levelCount][0].length() * BLOCK_WIDTH;
	}

	/**
	 * A method to set bookCount to zero before counting the actual number of books
	 * that the current level has. You can think of this method as a reset metho.
	 */
	private static void initializeBookCount() {
		bookCount = 0;
	}

	/**
	 * A method that set a level's background and read through all the character in
	 * a map in order to initialize all platform that is not a blank space with its
	 * position. If a platform is a coin block, it will be added to coins ArrayList.
	 * If a platform is a book block, it will be added to books ArrayList. If a
	 * platform is created from a char 'X', the finishPositions will be set to this
	 * platform's position. Basically, all platforms will be created using
	 * RenderableHolder class. But, there are some special platform that need to be
	 * initiailized from other class, like Book, Coin, FinishFlag, HurtPlatform. All
	 * blocks are added to gameRoot and platforms.
	 */
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
					break;
				case '2':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "2");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					break;
				case '3':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "3");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					break;
				case '4':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "4");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					break;
				case '5':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "5");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					break;
				case 'A':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "f1");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					break;
				case 'B':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "f2");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					break;
				case 'C':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "f3");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					break;
				case 'D':
					platform = RenderableHolder.createImageViewForPlatform(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, "f4");
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					break;

				// Item
				case 'M':
					platform = new Coin(RenderableHolder.coin, BLOCK_WIDTH, BLOCK_HEIGHT, j * BLOCK_WIDTH,
							i * BLOCK_HEIGHT);
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					// For collecting item logic
					coins.add(platform);
					break;
				case 'K':
					platform = new Book(RenderableHolder.bookOne, BLOCK_WIDTH, BLOCK_HEIGHT, j * BLOCK_WIDTH,
							i * BLOCK_HEIGHT);
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					// For collecting item logic
					books.add(platform);
					bookCount++;
					break;
				case 'k':
					platform = new Book(RenderableHolder.bookTwo, BLOCK_WIDTH, BLOCK_HEIGHT, j * BLOCK_WIDTH,
							i * BLOCK_HEIGHT);
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					books.add(platform);
					bookCount++;
					break;
				case 'H':
					platform = new HurtPlatform(RenderableHolder.hurtPlatformOne, BLOCK_WIDTH, BLOCK_HEIGHT,
							j * BLOCK_WIDTH, i * BLOCK_HEIGHT);
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					break;
				case 'X':
					platform = new FinishFlag(RenderableHolder.finish, BLOCK_WIDTH, BLOCK_HEIGHT, j * BLOCK_WIDTH,
							i * BLOCK_HEIGHT);
					gameRoot.getChildren().add(platform);
					platforms.add(platform);
					finishPositionX = j * BLOCK_WIDTH;
					finishPositionY = i * BLOCK_HEIGHT;
					break;
				default:
					break;
				}
			}
		}
		appRoot.getChildren().add(background);
	}

	/**
	 * A method that initialize player with Player class.
	 */
	private static void initializePlayer() {
		player = new Player(RenderableHolder.spritePlayerStanding, 5, 0, 200, 1);
	}

	/**
	 * A method that is used to set integer jumpBonus.
	 * 
	 * @param bonus A bonus given to player's jump power
	 */
	private static void setJumpBonus(int bonus) {
		jumpBonus = bonus;
	}

	/**
	 * A method that is used to set integer speedBonus.
	 * 
	 * @param bonus A bonus that given to player's move speed
	 */
	private static void setSpeedBonus(int bonus) {
		speedBonus = bonus;
	}

	/**
	 * A method that is used to initialize player's max HP. This method is used only
	 * in the constructor.
	 */
	private static void initializePlayerMaxHP() {
		playerMaxHP = 250;
	}

	/**
	 * A method that is used to initialize player's HP at the start of level 1. This
	 * method is used only in the constructor.
	 */
	private static void initializePlayerCurrentHP() {
		playerCurrentHP = playerMaxHP;
	}

	/**
	 * A method that is used to initialize player's coin. Player's coin must be 0
	 * everytimes the game is started.
	 */
	private static void initializePlayerCoin() {
		playerCoin = 0;
	}

	/**
	 * A method that is used to initialize player's EXP. Player's EXP must be 0
	 * everytimes the game is started.
	 */
	private static void initializePlayerEXP() {
		playerEXP = 0;
	}

	/**
	 * A method that is used to add player to gameRoot.
	 */
	private static void addPlayerToGameRoot() {
		gameRoot.getChildren().add(player);
	}

	/**
	 * A method that is used to add listener to player. This is the method that make
	 * gameRoot move as the player move. As we fix screen's width to 800, we use the
	 * number 40 instead of getting it using ViewManager.getScreenWidth/2
	 */
	private static void setGameRootLayoutX() {
		player.translateXProperty().addListener((observer, oldValue, newValue) -> {
			int offSet = newValue.intValue();
			if (offSet > 400 && offSet < levelWidth - 400) {
				gameRoot.setLayoutX(-(offSet - 400));
			}
		});
	}

	/**
	 * A method that is used to add gameRoot to appRoot. gameRoot is a pane that
	 * contains all platforms that this class created earlier. You can think of
	 * appRoot as the main pane or the biggest pane that contains everything.
	 */
	private static void addGameRoot() {
		appRoot.getChildren().add(gameRoot);
	}

	/**
	 * A method that is used to add uiRoot to appRoot. uiRoot is a pane that
	 * contains game HUD and buttons that doensn't move like the gameRoot does.
	 */
	private static void addUIRoot() {
		appRoot.getChildren().add(uiRoot);
	}

	/**
	 * A method that is used to set boolean canJump.
	 * 
	 * @param canJump A boolean used to set value for canJump
	 */
	private static void setCanJump(boolean canJump) {
		GameManager.canJump = canJump;
	}

	/**
	 * A method that is used to initialize all KeyCodes' value in keys HashMap.
	 * Actually, keys HashMap might have all the KeyCodes the player press, but this
	 * class will only focus on three main KeyCodes. If we change the value of these
	 * KeyCodes to true, things will not change much as boolean canJump is false at
	 * this point, but we should keep these values false at the stating point.
	 */
	private static void initializeKeysValue() {
		keys.put(KeyCode.W, false);
		keys.put(KeyCode.A, false);
		keys.put(KeyCode.D, false);
	}

	/**
	 * A method that is used to increase levelCount field by one. This method will
	 * not be called in the constructor, but it will be called in the method that is
	 * used to change level.
	 */
	private static void levelCountInclement() {
		levelCount++;
	}

	/* ==================== USED TO SET UP NEW LEVEL ==================== */

	/**
	 * A method that is used to clear everything out of the uiRoot. This method will
	 * be called in order to set up the next level.
	 */
	private static void clearUIRoot() {
		uiRoot.getChildren().clear();
	}

	/**
	 * A method that is used to clear everyting out of the gameRoot. This method
	 * will be called in order to set up the next leve.
	 */
	private static void clearGameRoot() {
		gameRoot.getChildren().clear();
	}

	/**
	 * A method that is used to clear everything out of the appRoot. As you know,
	 * appRoot only contains background, gameRoot, and uiRoot respectively.
	 */
	private static void clearAppRoot() {
		appRoot.getChildren().clear();
	}

	/**
	 * A method that is used to clear everything out of the platforms. platforms'
	 * items are the same as gameRoot.getChildren()'s items.
	 */
	private static void clearPlatforms() {
		platforms.clear();
	}

	/**
	 * A method that is used to set the finishPositionX and finishPositionY to zero
	 * and zero respectively. Actually, the finishPositions do not need to be
	 * resetted, but with this method, this class will be more readable.
	 */
	private static void resetFinishPosition() {
		finishPositionX = 0;
		finishPositionY = 0;
	}

	/* ==================== USED IN update() METHOD ==================== */

	/**
	 * A method that check if player can move horizontally in that direction or not,
	 * if the player can move, the player will move as much as the value given to
	 * this method. This method takes a number of times player might moves in one
	 * frame. This method let player moves throught a coin or a book but not other
	 * blocks, somehow, player can walk through all the blocks.
	 * 
	 * @param value The number of move this player makes in one frame.
	 */
	private static void movePlayerX(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (player.getTranslateX() + player.getWidth() + 1 == platform.getTranslateX()) {
							if (platform instanceof Collectable) {
							} else if (platform instanceof Damagable) {
								setPlayerCurrentHP(getPlayerCurrentHP() - 5 - random.nextInt(5));
								return;
							} else {
								return;
							}
						}
					} else {
						if (player.getTranslateX() == platform.getTranslateX() + BLOCK_WIDTH + 1) {
							if (platform instanceof Collectable) {
							} else if (platform instanceof Damagable) {
								setPlayerCurrentHP(getPlayerCurrentHP() - 5 - random.nextInt(5));
								return;
							} else {
								return;
							}
						}
					}
				}
			}
			player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
		}
	}

	/**
	 * A method that check if player can move vertically in that direction or not,
	 * if the player can move, then the player will move as much as the value given
	 * to this method This method taks a number of times player might moves in one
	 * frame. This method let player moves through a coin or a book but not other
	 * blocks.
	 * 
	 * @param value The number of move this player makes in one frame.
	 */
	private static void movePlayerY(int value) {
		boolean movingDown = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingDown) {
						if (player.getTranslateY() + player.getHeight() == platform.getTranslateY()) {
							if (platform instanceof Collectable) {
							} else if (platform instanceof Damagable) {
								setPlayerCurrentHP(getPlayerCurrentHP() - 1);
								setCanJump(true);
								return;
							} else {
								setCanJump(true);
								return;
							}
						}
					} else {
						if (player.getTranslateY() == platform.getTranslateY() + BLOCK_HEIGHT) {
							if (platform instanceof Collectable) {

							} else if (platform instanceof Damagable) {
								setPlayerCurrentHP(getPlayerCurrentHP() - 1);
								return;
							} else {
								return;
							}
						}
					}
				}
			}
			player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
		}
	}

	/**
	 * A Method that set player's vertical velocity to the value given to this
	 * method. The reason why this method is named jumpPlayer is because, in every
	 * frame, update() method will call method movePlayerY and use player vertical
	 * velocity as a value given to that method. This method can only change
	 * player's vertical velocity if the player can jump at that time, otherwise,
	 * this method will not do anything.
	 * 
	 * @param jumpHeight The number of vertical velocity this method will add to
	 *                   player's field. The value should be between 30 to 40.
	 */
	private static void jumpPlayer(int jumpHeight) {
		if (canJump) {
			player.setVelocityY(player.getVelocityY() - jumpHeight);
			setCanJump(false);
		}
	}

	/**
	 * A method that check if the specific KeyCode given to this method is being
	 * pressed or not.
	 * 
	 * @param key The specific KeyCode that need to be checked.
	 * @return A boolean value that tells if the KeyCode is being pressed or not.
	 */
	private static boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	/**
	 * A method to set player's current HP to a specific value given to this method.
	 * It is mostly used in movePlayerY() and movePlayerX() methods in order to
	 * decrease player's HP.
	 * 
	 * @param HP A number of HP that will be set to player's current HP.
	 */
	private static void setPlayerCurrentHP(int HP) {
		playerCurrentHP = HP;
	}

	/**
	 * A method that is used to check all coins in coins ArrayList if any of them
	 * are collected. If a coin is collected, all platforms, gameRoot, and coins
	 * will remove that coin using ArrayList's remove(object) method The logic used
	 * to check if the player collect a coin is about checking both coin's and
	 * player's position.
	 */
	private static void checkCoinCollect() {
		for (Node coin : coins) {
			boolean boundXLeftMin = coin.getTranslateX() <= player.getTranslateX() + player.getWidth();
			boolean boundXLeftMax = player.getTranslateX() + player.getWidth() <= coin.getTranslateX() + BLOCK_WIDTH;
			boolean boundXRightMin = coin.getTranslateX() <= player.getTranslateX();
			boolean boundXRightMax = player.getTranslateX() <= coin.getTranslateX() + BLOCK_WIDTH;

			// e, f, g, h check translateY
			boolean boundYTopMin = coin.getTranslateY() <= player.getTranslateY() + player.getHeight();
			boolean boundYTopMax = player.getTranslateY() + player.getHeight() <= coin.getTranslateY() + BLOCK_HEIGHT;
			boolean boundYBottomMin = coin.getTranslateY() <= player.getTranslateY();
			boolean boundYBottomMax = player.getTranslateY() <= coin.getTranslateY() + BLOCK_HEIGHT;

			if (((boundXLeftMin && boundXLeftMax) || (boundXRightMin && boundXRightMax))
					&& ((boundYTopMin && boundYTopMax) || (boundYBottomMin && boundYBottomMax))) {
				GameManager.setPlayerCoin(GameManager.getPlayerCoin() + 5 + random.nextInt(5));
				gameRoot.getChildren().remove(coin);
				platforms.remove(coin);
				coins.remove(coin);
				break;
			}
		}
	}

	/**
	 * This method works similaly to checkCoinCollect() method, but in this method,
	 * we use books ArrayList instead of coins ArrayList.
	 */
	private static void checkBookCollect() {
		for (Node book : books) {
			boolean boundXLeftMin = book.getTranslateX() <= player.getTranslateX() + player.getWidth();
			boolean boundXLeftMax = player.getTranslateX() + player.getWidth() <= book.getTranslateX() + BLOCK_WIDTH;
			boolean boundXRightMin = book.getTranslateX() <= player.getTranslateX();
			boolean d = player.getTranslateX() <= book.getTranslateX() + BLOCK_WIDTH;

			// e, f, g, h check translateY
			boolean boundYTopMin = book.getTranslateY() <= player.getTranslateY() + player.getHeight();
			boolean boundYTopMax = player.getTranslateY() + player.getHeight() <= book.getTranslateY() + BLOCK_HEIGHT;
			boolean boundYBottomMin = book.getTranslateY() <= player.getTranslateY();
			boolean boundYBottomMax = player.getTranslateY() <= book.getTranslateY() + BLOCK_HEIGHT;

			if (((boundXLeftMin && boundXLeftMax) || (boundXRightMin && d))
					&& ((boundYTopMin && boundYTopMax) || (boundYBottomMin && boundYBottomMax))) {
				gameRoot.getChildren().remove(book);
				platforms.remove(book);
				books.remove(book);
				bookCount--;
				break;
			}
		}
	}

	/*
	 * ============================== PUBLIC STATIC METHOD
	 * ==============================
	 */
	/**
	 * This method will be called everytimes GameScene's animation timer is running.
	 * Basically, this method will check everything happends within a frame, like,
	 * moving methods, player's update method, item collecting methods, and win or
	 * lose condition checking algorithms.
	 */
	public static void update() {
		if (isPressed(KeyCode.W) && player.getTranslateY() >= 5) {
			jumpPlayer(32 + jumpBonus);
		}
		if (isPressed(KeyCode.A) && player.getTranslateX() >= 5) {
			movePlayerX(-5 - speedBonus);
		}
		if (isPressed(KeyCode.D) && player.getTranslateX() <= levelWidth - 5 - player.getWidth()) {
			movePlayerX(5 + speedBonus);
		}
		if (player.getVelocityY() < 10) {
			player.setVelocityY(player.getVelocityY() + 1);
		}
		movePlayerY(player.getVelocityY());
		player.update();
		checkCoinCollect();
		checkBookCollect();

		// Check level finish
		if (!isLevelFinish) {
			boolean boundXLeftMin = finishPositionX <= player.getTranslateX() + player.getWidth();
			boolean boundXLeftMax = player.getTranslateX() + player.getWidth() <= finishPositionX + BLOCK_WIDTH;
			boolean boundXRightMin = finishPositionX <= player.getTranslateX();
			boolean boundXRightMax = player.getTranslateX() <= finishPositionX + BLOCK_WIDTH;

			// e, f, g, h check translateY
			boolean boundYTopMin = finishPositionY <= player.getTranslateY() + player.getHeight();
			boolean boundYTopMax = player.getTranslateY() + player.getHeight() <= finishPositionY + BLOCK_HEIGHT;
			boolean boundYBottomMin = finishPositionY <= player.getTranslateY();
			boolean boundYBottomMax = player.getTranslateY() <= finishPositionY + BLOCK_HEIGHT;

			if (((boundXLeftMin && boundXLeftMax) || (boundXRightMin && boundXRightMax))
					&& ((boundYTopMin && boundYTopMax) || (boundYBottomMin && boundYBottomMax))) {
				setLevelFinish(true);
				setPressedNextLv(false);
			}
		}
		if (isLevelFinish && bookCount > 0) {
			setMissingBook(true);
		}

		if (player.getTranslateY() > 600) {
			setDead(true);
		}

		if (getPlayerCurrentHP() <= 0) {
			setDead(true);
		}

		if (getPlayerCurrentHP() <= 0) {
			setDead(true);
		}
	}

	/**
	 * This method is called when the player has finished a level.
	 * This method will reset everything that need to be resetted, which, some of the methods are the same in the constructor.
	 * All you need to focus is that after calling this method, the next level is ready to be played.
	 */

	public static void setUpNextLevel() {
		setLevelFinish(false);
		setPressedNextLv(true);
		levelCountInclement();
		resetFinishPosition();
		setLevelWidth();
		initializeBookCount();
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
		setMute(false);
		initializeKeysValue();
		GameScene.createPauseGameLeaderboardSubScene();
	}

	/*
	 * ============================== GETTER/SETTER ==============================
	 */
	/**
	 * appRoot public static getter.
	 * 
	 * @return
	 */

	public static AnchorPane getAppRoot() {
		return appRoot;
	}

	/**
	 * appRoot public static setter.
	 * 
	 * @param appRoot Used to set appRoot to the given parameter.
	 */

	public static void setAppRoot(AnchorPane appRoot) {
		GameManager.appRoot = appRoot;
	}

	/**
	 * isMute public static setter.
	 * 
	 * @param isMute Used to set isMute to the given parameter
	 */

	public static void setMute(boolean isMute) {
		GameManager.isMute = isMute;
	}

	/**
	 * isMute public static getter.
	 * 
	 * @return
	 */

	public static boolean isMute() {
		return isMute;
	}

	/**
	 * BLOCK_WIDTH public static getter.
	 * 
	 * @return
	 */
	public static int getBlockWidth() {
		return BLOCK_WIDTH;
	}

	/**
	 * BLOCK_HEIGHT public static getter.
	 * @return
	 */
	public static int getBlockHeight() {
		return BLOCK_HEIGHT;
	}

	/**
	 * KeyCode and Value from keys HashMap public static setter.
	 * 
	 * @param keyCode The KeyCode that need to be checked.
	 * @param value   True if the given KeyCode is being pressed.
	 */

	public static void setKeysValue(KeyCode keyCode, boolean value) {
		keys.put(keyCode, value);
	}

	/**
	 * KeyCode's value from keys HashMap public static getter
	 * 
	 * @param key The Specific KeyCode that need to be checked.
	 * @return The value of given parameter's value.
	 */

	public static boolean getKeysValue(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	/**
	 * isLevelFinish public static setter
	 * 
	 * @param isFinish Used to set the isLevelFinish's value to the given parameter.
	 */

	public static void setLevelFinish(boolean isFinish) {
		isLevelFinish = isFinish;
	}

	/**
	 * isLevelFinish public static getter.
	 * 
	 * @return isLevelFinish.
	 */

	public static boolean isLevelFinish() {
		return isLevelFinish;
	}

	/**
	 * isPressedNextLv public static setter.
	 * 
	 * @param bool Used to set the isPressedNextLv's value to the given parameter.
	 */

	public static void setPressedNextLv(boolean bool) {
		isPressedNextLv = bool;
	}

	/**
	 * isPressedNextLv public static getter
	 * 
	 * @return isPressedNextLv
	 */

	public static boolean isPressedNextLv() {
		return isPressedNextLv;
	}

	/**
	 * levelCount public static getter.
	 * 
	 * @return levelCount
	 */

	public static int getLevelCount() {
		return levelCount;
	}

	/**
	 * uiRoot public static getter.
	 * 
	 * @return uiRoot
	 */

	public static AnchorPane getUIRoot() {
		return uiRoot;
	}

	/*
	 * ============================== SET PLAYER'S STATS
	 * ==============================
	 */
	/**
	 * Player's max HP public static setter.
	 * 
	 * @param maxHP Used to set player's max HP to the given parameter.
	 */

	public static void setPlayerMaxHP(int maxHP) {
		playerMaxHP = maxHP;
	}

	/**
	 * Player's coin public static setter.
	 * 
	 * @param coin Used to set player's coin to the given parameter.
	 */

	// setPlayerCurrentHP is not a public method

	public static void setPlayerCoin(int coin) {
		playerCoin = coin;
	}

	/**
	 * Player's EXP public static setter.
	 * 
	 * @param EXP Used to set player's EXP to the given parameter.
	 */

	public static void setPlayerEXP(int EXP) {
		playerEXP = EXP;
	}

	/**
	 * Player's name public static setter.
	 * 
	 * @param name Used to set player's name to the given parameter.
	 */
	public static void setPlayerName(String name) {
		playerName = name;
	}

	/**
	 * isDead public static getter.
	 * 
	 * @param isDead Used to set isDead's value to the given parameter.
	 */

	public static void setDead(boolean isDead) {
		GameManager.isDead = isDead;
	}

	/**
	 * isMissingBook public static getter.
	 * 
	 * @param isMissing Used to set isMissingBook's value to the given parameter.
	 */

	public static void setMissingBook(boolean isMissing) {
		isMissingBook = isMissing;
	}

	/*
	 * ============================== GET PLAYER'S STATS
	 * ==============================
	 */
	/**
	 * A method that is used to increase jumpBonus by 1, if jumpBonus is more than
	 * or equal to 5, it will be set to 5
	 */

	public static void makeJumpHigh() {
		jumpBonus++;
		if (jumpBonus >= 5) {
			jumpBonus = 5;
		}
	}

	/**
	 * A method that is used to increase speedBonus by 1, if speedBonus is more than
	 * or equal to 5, it will be set to 5
	 */

	public static void makeRunFast() {
		speedBonus++;
		if (speedBonus >= 5) {
			speedBonus = 5;
		}
	}

	/**
	 * A method that is used to increase player's max HP by a number between 30 to
	 * 50.
	 */
	public static void upgradePlayerMaxHP() {
		int newHP = GameManager.getPlayerMaxHP() + 30 + random.nextInt(20);
		GameManager.setPlayerMaxHP(newHP);
	}

	/**
	 * A method that is used to increase timeMap by a number between 0 to 20.
	 */
	public static void upgradeMapTime() {
		double time = GameScene.getTimeMapSecond() + random.nextDouble() * 20;
		GameScene.setTimeMapSecond(time);
	}

	/**
	 * A method that is used to update leaderboard as the game is running.
	 */
	public static void updateLeaderboard() {
		String name = GameManager.getPlayerName();
		String level = String.valueOf(levelCount + 1);
		GameManager.setPlayerEXP(GameManager.getPlayerCoin() * 100);
		String calculatedExp = String.valueOf(GameManager.getPlayerEXP());
		String[] data = { name, level, calculatedExp };
		CSVUtility.appendToCSV(data);
	}

	/**
	 * Player's max HP public static getter.
	 * 
	 * @return player's max HP
	 */
	public static int getPlayerMaxHP() {
		return playerMaxHP;
	}

	/**
	 * Player's current HP public static getter.
	 * 
	 * @return
	 */

	public static int getPlayerCurrentHP() {
		return playerCurrentHP;
	}

	/**
	 * Player's coins public static getter.
	 * 
	 * @return Player's current coin (spending coins will change this value too)
	 */
	public static int getPlayerCoin() {
		return playerCoin;
	}

	/**
	 * Player's EXP public static getter.
	 * 
	 * @return Player's current EXP (playing the game might increase this value)
	 */

	public static int getPlayerEXP() {
		return playerEXP;
	}

	/**
	 * isDead public static getter
	 * 
	 * @return isDead.
	 */

	public static boolean isDead() {
		return isDead;
	}

	/**
	 * isMissingBook public static getter.
	 * 
	 * @return isMissingBook
	 */

	public static boolean isMissingBook() {
		return isMissingBook;
	}

	/**
	 * Player's name public static getter.
	 * 
	 * @return Player's name.
	 */

	public static String getPlayerName() {
		return playerName;
	}
}
