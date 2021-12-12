package player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Character extends ImageView {
	
	private int velocityX;
	private int velocityY;
	
	public Character(Image image,int velocityX, int velocityY, int layoutX, int layoutY) {
		super(image);
		setVelocity(velocityX, velocityY);
		setLayout(layoutX, layoutY);
	}
	
	public void setLayout(int layoutX, int layoutY) {
		setLayoutX(layoutX);
		setLayoutY(layoutY);
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
