package sharedObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.logic.GameManager;
import exception.ImageNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * RenderableHolder is a static class that hold all shared images and
 * ImageView's objects. All other classes can directly call and get items from
 * this class since most of the fields are public static (except BLOCK_WIDTH and
 * BLOCK_HEIGHT because you can get it from GameManager.
 * 
 * @author Mos
 *
 */
public class RenderableHolder {
	/**
	 * Block's width. This is one of the only two fields that are not public fields.
	 */
	private static final int BLOCK_WIDTH = GameManager.getBlockWidth();
	/**
	 * Block's height. This is one of the only two fields that are not public
	 * fields.
	 */
	private static final int BLOCK_HEIGHT = GameManager.getBlockHeight();

	// Platform
	/**
	 * platform's image initialize using "green_01.png"
	 */
	public static Image greenOne;
	/**
	 * platform's image initialize using "green_02.png"
	 */
	public static Image greenTwo;
	/**
	 * platform's image initialize using "green_03.png"
	 */
	public static Image greenThree;
	/**
	 * platform's image initialize using "green_04.png"
	 */
	public static Image greenFour;
	/**
	 * platform's image initialize using "green_05.png"
	 */
	public static Image greenFive;
	/**
	 * platform's image initialize using "floatGreen_01.png"
	 */
	public static Image floatOne;
	/**
	 * platform's image initialize using "floatGreen_02.png"
	 */
	public static Image floatTwo;
	/**
	 * platform's image initialize using "floatGreen_03.png"
	 */
	public static Image floatThree;
	/**
	 * platform's image initialize using "floatGreen_04.png"
	 */
	public static Image floatFour;
	/**
	 * platform's image initialize using "lava.png"
	 */
	public static Image hurtPlatformOne;

	// Pane
	public static Image money_tag_Image;
	public static Image shop_Image;
	public static Image level_passed_Image;
	public static Image level_failed_Image;

	// Image
	public static Image green_box1;
	public static Image run_item;
	public static Image jump_item;
	public static Image lp_bonus_item;
	public static Image time_bonus_item;
	public static Image congrats_Image;

	// Background
	public static Image entrance_background_Image;
	public static Image normalLevelImage;
	public static Image subscene_background_Image;

	// Player
	public static Image player_sprite_Image;

	// Button
	public static Image pause_button_Image;
	public static Image buy_button_Image;
	public static Image mute_button_Image;
	public static Image unmute_button_Image;
	public static Image play_button_Image;
	public static Image continue_button_Image;
	public static Image skip_button_Image;
	public static Image home_button_Image;
	public static Image resume_button_Image;
	public static Image question_button_Image;
	public static Image quit_Image;

	// Logo
	/**
	 * Image for logo in Main Menu
	 */
	public static Image logo;

	// Player
	/**
	 * Image for initializing Play object in gameplay state.
	 */
	public static Image playerImage;

	// Sprite
	/**
	 * Image for Player's SpriteAnimation: Attack
	 */
	public static Image spritePlayerAttack;
	/**
	 * Image for Player's SpriteAnimation: Attack but facing left
	 */
	public static Image spritePlayerAttackBackward;
	/**
	 * Image for Player's SpriteAnimation: Run
	 */
	public static Image spritePlayerRun;
	/**
	 * Image for Player's SpriteAnimation: Run but facing left
	 */
	public static Image spritePlayerRunBackward;
	/**
	 * Image for Player's SpriteAnimation: Standing
	 */
	public static Image spritePlayerStanding;
	/**
	 * Image for Player's SpriteAnimation: Standing but facing left
	 */
	public static Image spritePlayerStandingBackward;
	/**
	 * Image for Player's SpriteAnimation: Jump
	 */
	public static Image spritePlayerJump;
	/**
	 * Image for Player's SpriteAnimation: Jump but facing left
	 */
	public static Image spritePlayerJumpBackward;

	// Item
	/**
	 * Image for item: Book (1)
	 */
	public static Image bookOne;
	/**
	 * Image for item: Book (2)
	 */
	public static Image bookTwo;
	/**
	 * Image for item: Coin
	 */
	public static Image coin;

	// Finish flag
	/**
	 * Image for item: Finish Flag
	 */
	public static Image finish;

	// Start flag
	/**
	 * Image for item: Start Flag
	 */
	public static Image start;

	// ---------------------------------------------------------
	static {
	}

	public static Image loadImage(String fileName) {
		try {
			URL image = ClassLoader.getSystemResource("image/" + fileName);
			if (image == null) {
				throw new ImageNotFoundException(fileName);
			}
			return new Image(ClassLoader.getSystemResource("image/" + fileName).toString());
		} catch (ImageNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return null;
	}

	public static Image loadImageButton(String fileName) {
		try {
			URL image = ClassLoader.getSystemResource("button/" + fileName);
			if (image == null) {
				throw new ImageNotFoundException(fileName);
			}
			return new Image(ClassLoader.getSystemResource("button/" + fileName).toString());
		} catch (ImageNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return null;
	}

	public static ImageView createImageViewForPlatform(int posX, int posY, String fileNumber) {
		ImageView block;
		switch (fileNumber) {
		case "1":
			block = new ImageView(RenderableHolder.greenOne);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "2":
			block = new ImageView(RenderableHolder.greenTwo);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "3":
			block = new ImageView(RenderableHolder.greenThree);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "4":
			block = new ImageView(RenderableHolder.greenFour);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "5":
			block = new ImageView(RenderableHolder.greenFive);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "f1":
			block = new ImageView(RenderableHolder.floatOne);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "f2":
			block = new ImageView(RenderableHolder.floatTwo);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "f3":
			block = new ImageView(RenderableHolder.floatThree);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "f4":
			block = new ImageView(RenderableHolder.floatFour);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "book1":
			block = new ImageView(RenderableHolder.bookOne);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "book2":
			block = new ImageView(RenderableHolder.bookTwo);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "coin":
			block = new ImageView(RenderableHolder.coin);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "start":
			block = new ImageView(RenderableHolder.start);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		case "finish":
			block = new ImageView(RenderableHolder.finish);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		default:
			block = new ImageView(RenderableHolder.greenOne);
			block.setFitWidth(BLOCK_WIDTH);
			block.setFitHeight(BLOCK_HEIGHT);
			break;
		}
		block.setTranslateX(posX);
		block.setTranslateY(posY);
		return block;
	}

	/**
	 * loadResource is a method that initialize all Image fields using loadImage.
	 * The reason why we do not simply use new Image(url) because if the path was incorrect, it will hard to find which part cause the error.
	 * You can think of this method as a object initializer.
	 */
	public static void loadResource() {

		// RenderableHolder.player_sprite_Image = loadImage("player_sprite_Image.png");
		RenderableHolder.green_box1 = loadImage("platform/green_01.png");
		RenderableHolder.jump_item = loadImage("jump.png");
		RenderableHolder.run_item = loadImage("run.png");
		RenderableHolder.time_bonus_item = loadImage("timeBonus.png");
		RenderableHolder.lp_bonus_item = loadImage("LpBonus.png");

		// Background
		RenderableHolder.entrance_background_Image = loadImage("mainSceneBackground_withoutLogo_fixed.png");
		RenderableHolder.normalLevelImage = loadImage("demo_level_background.png");
		RenderableHolder.subscene_background_Image = loadImage("SubScene_background.png");
		
		// Pane
		RenderableHolder.money_tag_Image = loadImage("moneyTag.png");
		RenderableHolder.shop_Image = loadImage("shop-pane.png");
		RenderableHolder.level_passed_Image = loadImage("passedLevel.png");
		RenderableHolder.level_failed_Image = loadImage("failedLevel.png");

		// Button
		RenderableHolder.pause_button_Image =  loadImageButton("pause.png");
		RenderableHolder.buy_button_Image =  loadImageButton("buy-button.png");
		RenderableHolder.unmute_button_Image = loadImageButton("unmute.png");
		RenderableHolder.mute_button_Image = loadImageButton("mute.png");
		RenderableHolder.play_button_Image = loadImageButton("start.png");
		RenderableHolder.skip_button_Image = loadImageButton("skip-button.png");
		RenderableHolder.continue_button_Image = loadImageButton("continue-button.png");
		RenderableHolder.home_button_Image = loadImageButton("home-button.png");
		RenderableHolder.resume_button_Image = loadImageButton("play-btn.png");
		RenderableHolder.question_button_Image = loadImageButton("help.png");
		RenderableHolder.quit_Image = loadImageButton("quit.png");
		RenderableHolder.congrats_Image = loadImage("congrats.png");
		
		
		// Platform
		RenderableHolder.greenOne = loadImage("platform/green_01.png");
		RenderableHolder.greenTwo = loadImage("platform/green_02.png");
		RenderableHolder.greenThree = loadImage("platform/green_03.png");
		RenderableHolder.greenFour = loadImage("platform/green_04.png");
		RenderableHolder.greenFive = loadImage("platform/green_05.png");
		RenderableHolder.floatOne = loadImage("platform/floatGreen_01.png");
		RenderableHolder.floatTwo = loadImage("platform/floatGreen_02.png");
		RenderableHolder.floatThree = loadImage("platform/floatGreen_03.png");
		RenderableHolder.floatFour = loadImage("platform/floatGreen_04.png");
		RenderableHolder.hurtPlatformOne = loadImage("platform/lava.png");

		// Logo
		RenderableHolder.logo = loadImage("Logo.png");

		// Player
		RenderableHolder.playerImage = loadImage("character_maleAdventurer_attack0.png");
		
		// Sprite
		RenderableHolder.spritePlayerAttack = loadImage("sprite/sprite_player_attack.png");
		RenderableHolder.spritePlayerAttackBackward = loadImage("sprite/sprite_player_attack_backward.png");
		RenderableHolder.spritePlayerRun = loadImage("sprite/sprite_player_run.png");
		RenderableHolder.spritePlayerRunBackward = loadImage("sprite/sprite_player_run_backward.png");
		RenderableHolder.spritePlayerStanding = loadImage("sprite/sprite_player_standing.png");
		RenderableHolder.spritePlayerStandingBackward = loadImage("sprite/sprite_player_standing_backward.png");
		RenderableHolder.spritePlayerJump = loadImage("sprite/sprite_player_jump.png");
		RenderableHolder.spritePlayerJumpBackward = loadImage("sprite/sprite_player_jump_backward.png");

		// Item
		RenderableHolder.bookOne = loadImage("platform/book_1.png");
		RenderableHolder.bookTwo = loadImage("platform/book_2.png");
		RenderableHolder.coin = loadImage("platform/coin.png");
		
		// Finish flag
		RenderableHolder.finish = loadImage("platform/finish.png");
		
		// Start flag
		RenderableHolder.start = loadImage("platform/start.png");
	}

}