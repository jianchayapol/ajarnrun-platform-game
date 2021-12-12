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
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static final RenderableHolder instance = new RenderableHolder();
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
	public static Image level_passed_Image;
	public static Image level_failed_Image;
	
	// Background
	public static Image entrance_background_Image;
	public static Image normalLevelImage;

	// Player
	public static Image player_sprite_Image;

	// Button
	public static Image mute_button_Image;
	public static Image unmute_button_Image;
	public static Image play_button_Image;
	public static Image continue_button_Image;
	public static Image skip_button_Image;
	
	// Logo
	public static Image logo;
	
	// Player
	public static Image playerImage;
	// ---------------------------------------------------------
	static {
		loadResource();
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
		block.setLayoutX(posX);
		block.setLayoutY(posY);
		return block;
	}

	public static void loadResource() {
		
		//RenderableHolder.player_sprite_Image = loadImage("player_sprite_Image.png");
		
		// Background
		RenderableHolder.entrance_background_Image = loadImage("mainSceneBackground_withoutLogo_fixed.png");
		RenderableHolder.normalLevelImage = loadImage("demo_level_background.png");
		
		// Pane
		RenderableHolder.level_passed_Image = loadImage("passedLevel.png");
		RenderableHolder.level_failed_Image = loadImage("failedLevel.png");
		
		// Button
		RenderableHolder.unmute_button_Image = loadImageButton("unmute.png");
		RenderableHolder.mute_button_Image = loadImageButton("mute.png");
		RenderableHolder.play_button_Image = loadImageButton("start.png");
		RenderableHolder.skip_button_Image= loadImageButton("skip-button.png");
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
	}
	
	public RenderableHolder() {
		
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ()) {
				return 1;
			}
			return -1;
		};
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		/*
		 * to do
		 */
	}
	
	public static RenderableHolder getInstance() {
		return instance;
	}

	public ArrayList<IRenderable> getEntities() {
		return entities;
	}

}