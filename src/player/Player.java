package player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {
	
	private int velocityX;
	private int velocityY;
	private boolean canJump;
	
	public Player(String URL, int velocityX, int velocityY, int layoutX, int layoutY) {
		super(new Image(URL));
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
	
	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}
	public boolean getCanJump() {
		return this.canJump;
	}
}
