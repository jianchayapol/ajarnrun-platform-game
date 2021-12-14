package player;

import javafx.scene.image.Image;
import player.base.Jumpable;
import player.base.Moveable;

public class Enemy extends Character implements Moveable, Jumpable {
	

	private final int WIDTH = 50;
	private final int HEIGHT = 50;
	
	public Enemy(Image image, int velocityX, int velocityY, int layoutX, int layoutY) {
		super(image, velocityX, velocityY, layoutX, layoutY);
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
