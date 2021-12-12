package player;

import javafx.scene.image.Image;
import player.base.Jumpable;
import player.base.Moveable;

public class Player extends Character implements Moveable, Jumpable {
	private final int WIDTH = 96;
	private final int HEIGHT = 128;
	
	public Player(Image image, int velocityX, int velocityY, int translateX, int translateY){
		super(image, velocityX, velocityY, translateX, translateY);
		setFitWidth(WIDTH);
		setFitHeight(HEIGHT);
	}
	
	/* ==================== GETTER/SETTER ==================== */
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
}
