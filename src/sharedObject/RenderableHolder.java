package sharedObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exception.ImageNotFoundException;
import javafx.scene.image.Image;

public class RenderableHolder {
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static final RenderableHolder instance = new RenderableHolder();

	// Images
	public static Image x;

	// Background
	public static Image entrance_background_Image;

	// Player
	public static Image player_sprite_Image;

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

	public static void loadResource() {
		RenderableHolder.player_sprite_Image = loadImage("player_sprite_Image.png");
		RenderableHolder.entrance_background_Image = loadImage("player_sprite_Image.png");
		
		/*
		 * 
		 * 
		 * 
		 */
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