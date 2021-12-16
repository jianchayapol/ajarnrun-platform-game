package player;

import application.logic.GameManager;
import gui.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import player.base.Jumpable;
import player.base.Movable;
import sharedObject.RenderableHolder;
/**
 * Player class is a class that can be used to initialize player in gameplay state (the player that you control).
 * This class implements Moveable and Jumpable which are Marker interfaces.
 * This class also has its own SpriteAnimation imported from SpriteAnimationClass and lots of boolean value to check which animation the player object should be showing.
 * @author Mos
 *
 */
public class Player extends Character implements Movable, Jumpable {
	/**
	 * player's width
	 */
	private final int WIDTH = 75;
	/**
	 * player's height
	 */
	private final int HEIGHT = 100;
	/**
	 * player's jump power
	 */
	private int jumpHeight = 21;
	/**
	 * boolean that check if player is moving to the right.
	 */
	private boolean isMovingRight;
	/**
	 * boolean that check if player is facing to the right.
	 */
	private boolean isFacingRight;
	/**
	 * boolean that check if player is moving to the left (isMovingRight isn't always equal to !isMovingLeft)
	 */
	private boolean isMovingLeft;
	/**
	 * boolean that check if player is facing to the left.
	 */
	private boolean isFacingLeft;
	/**
	 * boolean that check if player isn't moving horizontally.
	 */
	private boolean isStandingX;
	/**
	 * boolean that check if player isn't moving vertically
	 */
	private boolean isStandingY;
	/**
	 * an Animation object that will be use to create SpriteAnimation object.
	 */
	private Animation animation;
	/**
	 * Player's constructor simply pass first three parameter to super constructor.
	 * The last two parameter is use to setFitWidth and fitHeight (since a Player's object is also an ImageView's object)
	 * @param image Used to set player's image
	 * @param velocityX Used to set player's horizontal velocity
	 * @param velocityY Used to set player's vertical velocity
	 * @param translateX Used to set blocks positionX
	 * @param translateY Used to set blocks positionY
	 */
	public Player(Image image, int velocityX, int velocityY, int translateX, int translateY){
		super(image, velocityX, velocityY, translateX, translateY);
		setFitWidth(WIDTH);
		setFitHeight(HEIGHT);
		initializeBooleanValues();
	}
	/**
	 * This method is use to calculate the result of boolean values that will be passed to the createSprite method.
	 */
	private void initializeBooleanValues() {
		if ((GameManager.getKeysValue(KeyCode.A) && GameManager.getKeysValue(KeyCode.D)) || (!GameManager.getKeysValue(KeyCode.A) && !GameManager.getKeysValue(KeyCode.D))) {
			this.isStandingX = true;
			this.isMovingRight = false;
			this.isMovingLeft = false;
		} else 	if (GameManager.getKeysValue(KeyCode.D)) {
			this.isFacingRight = true;
			this.isMovingRight = true;
			this.isFacingLeft = false;
			this.isStandingX = false;
		} else if (GameManager.getKeysValue(KeyCode.A)) {
			this.isFacingLeft = true;
			this.isMovingLeft = true;
			this.isFacingRight = false;
			this.isStandingX = false;
		} else {
			
		}
		if (this.getVelocityY() == 10 || this.getTranslateY() >= 480 - this.HEIGHT - 15) {
			this.isStandingY = true;
		} else {
			this.isStandingY = false;
		}
	}
	
	/* ==================== GETTER/SETTER ==================== */
	/**
	 * Player's height public getter.
	 * @return Player's height
	 */
	public int getHeight() {
		return HEIGHT;
	}
	/**
	 * Player's width public getter.
	 * @return Player's width
	 */
	public int getWidth() {
		return WIDTH;
	}	
	/**
	 * Player's jumpHeight public getter.
 	 * @return player's jumpHeight
	 */
	public int getJumpHeight() {
		return jumpHeight;
	}
	
	/* ============================== ANIMATION SPRITE ============================== */
	
	/* =============== update method =============== */
	/**
	 * A method that will be called every frame as the game is running.
	 * This method will call initilizeBooleanValues method and craeteSprite method.
	 */
	public void update() {
		initializeBooleanValues();
		createSprite();
	}
	
	/* =============== sprite method =============== */
	/**
	 * Image of a player running facing right
	 */
	private final Image SPRITE_RUN = RenderableHolder.spritePlayerRun;
	/**
	 * Image of a player running facing left
	 */
	private final Image SPRITE_RUN_BW = RenderableHolder.spritePlayerRunBackward;
	/**
	 * Image of a player attacking facing right
	 */
	private final Image SPRITE_ATTACK = RenderableHolder.spritePlayerAttack;
	/**
	 * Image of a player attacking facing left
	 */
	private final Image SPRITE_ATTACK_BW = RenderableHolder.spritePlayerAttackBackward;
	/**
	 * Image of a player standing facing right
	 */
	private final Image SPRITE_STANDING = RenderableHolder.spritePlayerStanding;
	/**
	 * Image of a player standing facing left
	 */
	private final Image SPRITE_STANDING_BW = RenderableHolder.spritePlayerStandingBackward;
	/**
	 * Image of a player jumping facing right
	 */
	private final Image SPRITE_JUMP = RenderableHolder.spritePlayerJump;
	/**
	 * Image of a player jumping facing left
	 */
	private final Image SPRITE_JUMP_BW = RenderableHolder.spritePlayerJumpBackward;
	/**
	 * COLUMNS for SpriteAnimation's object
	 */
	private static final int COLUMNS = 3;
	/**
	 * COUNT for SpriteAnimation's object
	 */
	private static final int COUNT = 3;
	/**
	 * OFFSET_X for SpriteAnimation's object
	 */
	private static final int OFFSET_X = 0;
	/**
	 * OFFSET_Y for SpriteAnimation's object
	 */
	private static final int OFFSET_Y = 0;
	
	/**
	 * This method will check player's gesture and create a SpriteAnimation depending on that gesture.
	 * This method also use final fields in this class, such as, Image objects and some integers.
	 * Then call method run from SpriteAnimation's object.
	 */
	void createSprite() {
		if (!isStandingY) {
			if (isFacingLeft) {
				this.setImage(SPRITE_JUMP_BW);
			} else if (isFacingRight) {
				this.setImage(SPRITE_JUMP);
			}
		} else {
			if (isStandingX) {
				if (isFacingLeft) {
					this.setImage(SPRITE_STANDING_BW);
				} else if (isFacingRight) {
					this.setImage(SPRITE_STANDING);
				}
			} else if (isMovingRight) {
				this.setImage(SPRITE_RUN);
			} else if (isMovingLeft) {
				this.setImage(SPRITE_RUN_BW);
			}
		}
		
		setFitWidth(WIDTH);
		setFitHeight(HEIGHT);
		this.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		
		if (isMovingRight || isMovingLeft) {
			animation = new SpriteAnimation(
		             this,
		             Duration.millis(500),
		             COUNT, COLUMNS,
		             OFFSET_X, OFFSET_Y,
		             WIDTH, HEIGHT
				);
				animation.setCycleCount(Animation.INDEFINITE);
				animation.play();
		}
	}
}
