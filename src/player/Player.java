package player;

import javafx.scene.image.Image;
import logic.base.Jumpable;
import logic.base.Moveable;

public class Player extends Character implements Moveable, Jumpable {
	private final int WIDTH = 96;
	private final int HEIGHT = 128;
	
	public Player(Image image, int velocityX, int velocityY, int layoutX, int layoutY){
		super(image, velocityX, velocityY, layoutX, layoutY);
		setFitWidth(WIDTH);
		setFitHeight(HEIGHT);
	}
	
}
