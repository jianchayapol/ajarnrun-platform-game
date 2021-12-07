package entity.character;

import entity.base.Attackable;
import entity.base.Damagable;
import entity.base.Direction;
import entity.base.Entity;
import entity.base.Jumpable;
import entity.base.Runnable;
import gui.draw.*;
import application.utility.InputUtility;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Player extends Entity implements Attackable, Damagable, Jumpable, Runnable {

	private static ImageView imageView;
	private static Animation animation;
	
	public Player(String name) {

		super();

		switch (name) {

		case "VKJ":
			this.setImageUrl("VKJ-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		case "PVK":
			this.setImageUrl("PVK-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		default:
			DrawUtil.createFirstSprite();
			DrawUtil.createSprite();
			this.imageView = new ImageView("player-among.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;
		}
		
		this.setCurrentPos(100, 200);
		this.setName(name);
		this.setDirection(Direction.RIGHT);
	}

	public void update() {
		
		if (InputUtility.getKeyPressed(KeyCode.LEFT)) {
			System.out.println(">> ");;
		}
		
	}
	
	@Override
	public int attack(Damagable character) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
	}

	@Override
	public void moveForward(Direction direction) {
		// TODO Auto-generated method stub
		int x = (int) this.getCurrentPos().getX();
		int y = (int) this.getCurrentPos().getY();
		// รอแก้
		switch (direction) {
		case RIGHT:
			if (x + 1 < 720)
				this.setCurrentPos(x + 1, y);
		case LEFT:
			if (x - 1 > 0)
				this.setCurrentPos(x - 1, y);
		default:
			break;
		}
	}
	
	public static ImageView getImageView() {
		return imageView;
	}

	public static void setImageView(ImageView imageView) {
		Player.imageView = imageView;
	}

	public static Animation getAnimation() {
		return animation;
	}

	public static void setAnimation(Animation animation) {
		Player.animation = animation;
	}

}
