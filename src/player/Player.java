package player;

import application.logic.GameManager;
import gui.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import player.base.Jumpable;
import player.base.Moveable;
import sharedObject.RenderableHolder;
/**
 * Player class is a class that can be used to initialize player in gameplay state (the player that you control).
 * This class implements Moveable and Jumpable which are Marker interfaces.
 * This class also has its own SpriteAnimation imported from SpriteAnimationClass and lots of boolean value to check which animation the player object should be showing.
 * @author Mos
 *
 */
public class Player extends Character implements Moveable, Jumpable {
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
	 * @param image
	 * @param velocityX
	 * @param velocityY
	 * @param translateX
	 * @param translateY
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
	
	public void update() {
		initializeBooleanValues();
		createSprite();
	}
	
	/* =============== sprite method =============== */

	private final Image SPRITE_RUN = RenderableHolder.spritePlayerRun;
	private final Image SPRITE_RUN_BW = RenderableHolder.spritePlayerRunBackward;
	private final Image SPRITE_ATTACK = RenderableHolder.spritePlayerAttack;
	private final Image SPRITE_ATTACK_BW = RenderableHolder.spritePlayerAttackBackward;
	private final Image SPRITE_STANDING = RenderableHolder.spritePlayerStanding;
	private final Image SPRITE_STANDING_BW = RenderableHolder.spritePlayerStandingBackward;
	private final Image SPRITE_JUMP = RenderableHolder.spritePlayerJump;
	private final Image SPRITE_JUMP_BW = RenderableHolder.spritePlayerJumpBackward;
		
	private static final int COLUMNS = 3;
	private static final int COUNT = 3;
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	
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
