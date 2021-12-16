package player;

import javafx.scene.image.Image;
import player.base.Jumpable;
import player.base.Moveable;

public class Enemy extends Character implements Moveable {
	

	private final int WIDTH = 50;
	private final int HEIGHT = 50;
	private int attackDamage;
	
	public Enemy(Image image, int velocityX, int velocityY, int translateX, int translateY, int attackDamage) {
		super(image, velocityX, velocityY, translateX, translateY);
		setAttackDamage(attackDamage);
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
	
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
}
