package player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Character class is an abstract class that extends ImageView.
 * This class was created as an abstract class for player class and some other classes that were abandoned and deleted.
 * This class is mostly just an ImageView class with velocity that will make a character moves.
 * @author Mos
 *
 */
public abstract class Character extends ImageView {
	/**
	 * A value of velocity that character use to move horizontally.
	 */
	private int velocityX;
	/**
	 * A value of velocity that character use to move vertically.
	 */
	private int velocityY;
	/**
	 * The constructor that work similarly like the ImageView class, but with setVelocity method.
	 * Since Character class is extended from ImageView, the Character object itself is also an ImageView.
	 * @param image An image that will be passed to super class' constructor
	 * @param velocityX Horizontal velocity
	 * @param velocityY Vertical velocity
	 * @param layoutX Horizontal distance use to place itself in a root node.
	 * @param layoutY Vertical distance use to place itself in a root node.
	 */
	public Character(Image image,int velocityX, int velocityY, int layoutX, int layoutY) {
		super(image);
		setVelocity(velocityX, velocityY);
		setTranslate(layoutX, layoutY);
	}
	/**
	 * A method that set object's translateX and translateY from 2 given parameters.
	 * @param layoutX An integer that is used to set object's translateX.
	 * @param layoutY An integer that is used to set object's translateY.
	 */
	public void setTranslate(int layoutX, int layoutY) {
		setTranslateX(layoutX);
		setTranslateY(layoutY);
	}
	/**
	 * A method that set object's velocity.
	 * @param velocityX An integer that is used to set horizontal velocity.
	 * @param velocityY An integer that is used to set vertical velocity
	 */
	public void setVelocity(int velocityX, int velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
	/**
	 * velocityX public getter.
	 * @return velocityX
	 */
	public int getVelocityX() {
		return this.velocityX;
	}
	/**
	 * velocityY public getter.
	 * @return velocityY
	 */
	public int getVelocityY() {
		return this.velocityY;
	}
	/**
	 * velocityX public setter
	 * @param velocityX An ineger that is used to set velocityX
	 */
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
	/**
	 * velocityY public setter.
	 * @param velocityY An ineger that is used to set velocityY
	 */
	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

}
