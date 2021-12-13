package sharedObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.logic.GameManager;
import exception.ImageNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RenderableHolder {
	
	private static final int BLOCK_WIDTH = GameManager.getBlockWidth();
	private static final int BLOCK_HEIGHT = GameManager.getBlockHeight();

	// Private Image
	private static Image greenOne;
	private static Image greenTwo;
	private static Image greenThree;
	private static Image greenFour;
	private static Image greenFive;
	private static Image floatOne;
	private static Image floatTwo;
	private static Image floatThree;
	private static Image floatFour;

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

	// Background
	public static Image entrance_background_Image;
	public static Image normalLevelImage;

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

	// Logo
	public static Image logo;

	// Player
	public static Image playerImage;
	
	// Sprite
	public static Image spritePlayerAttack;
	public static Image spritePlayerAttackBackward;
	public static Image spritePlayerRun;
	public static Image spritePlayerRunBackward;
	public static Image spritePlayerStanding;
	public static Image spritePlayerStandingBackward;
	public static Image spritePlayerJump;
	public static Image spritePlayerJumpBackward;

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
			break;
		case "2":
			block = new ImageView(RenderableHolder.greenTwo);
			break;
		case "3":
			block = new ImageView(RenderableHolder.greenThree);
			break;
		case "4":
			block = new ImageView(RenderableHolder.greenFour);
			break;
		case "5":
			block = new ImageView(RenderableHolder.greenFive);
			break;
		case "f1":
			block = new ImageView(RenderableHolder.floatOne);
			break;
		case "f2":
			block = new ImageView(RenderableHolder.floatTwo);
			break;
		case "f3":
			block = new ImageView(RenderableHolder.floatThree);
			break;
		case "f4":
			block = new ImageView(RenderableHolder.floatFour);
			break;
		default:
			block = new ImageView(RenderableHolder.greenOne);
		}
		block.setFitWidth(BLOCK_WIDTH);
		block.setFitHeight(BLOCK_HEIGHT);
		block.setTranslateX(posX);
		block.setTranslateY(posY);
		return block;
	}

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
	}

}