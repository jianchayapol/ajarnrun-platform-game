package gui.draw;

import entity.character.Player;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class DrawUtil {

/////////////////////////////////////// Player Animation //////////////////////////////////////

	private static final Image IMAGES = new Image("player-among.png");
	private static final int COLUMNS = 12;
	private static final int COUNT = 12;
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	private static final int WIDTH = 85;
	private static final int HEIGHT = 140;

	public static void createFirstSprite() {
		Player.setImageView(new ImageView(IMAGES));
	}

	public static void createSprite() {
		Player.setImageView(new ImageView(IMAGES));

		Player.getImageView().setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		Player.getImageView().relocate((double) (100), (double) 100);
		//imageView.relocate(arg0, arg1);
		if (true) {
			Player.setAnimation(new SpriteAnimation(Player.getImageView(), Duration.millis(500), COUNT, COLUMNS, OFFSET_X, OFFSET_Y, WIDTH,
					HEIGHT));
			Player.getAnimation().setCycleCount(Animation.INDEFINITE);
			Player.getAnimation().play();
		}
	}

}
