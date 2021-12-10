package sharedObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exception.ImageNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RenderableHolder {
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static final RenderableHolder instance = new RenderableHolder();
	private static final int BLOCK_WIDTH = 60;
	private static final int BLOCK_HEIGHT = 60;
	
	
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
	
	// Images
	public static Image x;

	// Background
	public static Image entrance_background_Image;

	// Player
	public static Image player_sprite_Image;

	// Button
	public static Image mute_button_Image;
	public static Image unmute_button_Image;
	
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
		
		// Button
		RenderableHolder.unmute_button_Image = new Image("/button/unmute.png");
		RenderableHolder.mute_button_Image = new Image("/button/mute.png");
		/*
		 * 
		 * 
		 * 
		 */
		
		// Platform
		RenderableHolder.greenOne = new Image("/image/platform/green_01.png");
		RenderableHolder.greenTwo = new Image("/image/platform/green_02.png");
		RenderableHolder.greenThree = new Image("/image/platform/green_03.png");
		RenderableHolder.greenFour = new Image("/image/platform/green_04.png");
		RenderableHolder.greenFive = new Image("/image/platform/green_05.png");
		RenderableHolder.floatOne = new Image("/image/platform/floatGreen_01.png");
		RenderableHolder.floatTwo = new Image("/image/platform/floatGreen_02.png");
		RenderableHolder.floatThree = new Image("/image/platform/floatGreen_03.png");
		RenderableHolder.floatFour = new Image("/image/platform/floatGreen_04.png");

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