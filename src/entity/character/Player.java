package entity.character;

import entity.base.Attackable;
import entity.base.Damagable;
import entity.base.Direction;
import entity.base.Entity;
import entity.base.Jumpable;
import entity.base.Runnable;
import gui.draw.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player extends Entity implements Attackable, Damagable, Jumpable, Runnable {

	ImageView imageView;
	Animation animation;
	
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

		case "NNN":
			this.setImageUrl("NNN-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		case "ATS":
			this.setImageUrl("ATS-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		default:
			createFirstSprite();
			createSprite();
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

/////////////////////////////////////// Player Animation //////////////////////////////////////

	private static final Image IMAGES = new Image("player-among.png");
	private static final int COLUMNS = 12;
	private static final int COUNT = 12;
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	private static final int WIDTH = 85;
	private static final int HEIGHT = 140;

	void createFirstSprite() {
		imageView = new ImageView(IMAGES);
	}

	void createSprite() {
		imageView = new ImageView(IMAGES);
		
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		imageView.relocate((double) (100), (double) 100);
		//imageView.relocate(arg0, arg1);
		if (true) {
			animation = new SpriteAnimation(imageView, Duration.millis(500), COUNT, COLUMNS, OFFSET_X, OFFSET_Y, WIDTH,
					HEIGHT);
			animation.setCycleCount(Animation.INDEFINITE);
			animation.play();
		}
	}

	public ImageView getImageView() {
		// TODO Auto-generated method stub
		return this.imageView;
	}

}
