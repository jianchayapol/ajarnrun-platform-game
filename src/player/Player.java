package player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.base.Jumpable;
import logic.base.Moveable;

public class Player extends ImageView implements Moveable, Jumpable {
	
	private int velocityX;
	private int velocityY;
	
	public Player(Image image, int velocityX, int velocityY, int layoutX, int layoutY) {
		super(image);
		setVelocity(velocityX, velocityY);
		setFitWidth(96);
		setFitHeight(128);
		
		setLayoutX(layoutX);
		setLayoutY(layoutY-getFitHeight());
		
	}
	
	public void setVelocity(int velocityX, int velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
	
	public int getVelocityX() {
		return this.velocityX;
	}
	public int getVelocityY() {
		return this.velocityY;
	}
	
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	
}
