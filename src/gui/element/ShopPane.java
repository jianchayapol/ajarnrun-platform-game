package gui.element;

import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

public class ShopPane extends StackPane {

	private static VBox shelfBox;
	private static StackPane shopPane;
	private static Label errorMessage;
	private ArrayList<ShopItemBox> shelf;

	private static final String[] items = { "run", "jump", "lp", "time" };
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;

	public ShopPane() {
		this.setPrefSize(WIDTH, HEIGHT);

		// Background
		setBackgroundImage(RenderableHolder.entrance_background_Image);

		// initialize
		initializeShopPane();
		initializeErrorMessage();

		// set up
		setupPane();
	}

	// =================== private static method ==============================

	// initialize

	private void initializeShopPane() {
		shopPane = new StackPane();
		ImageView shop = new ImageView(RenderableHolder.shop_Image);
		shop.setFitHeight(500);
		shop.setFitWidth(400);
		shopPane.getChildren().add(shop);
		shopPane.setPrefSize(400, 520);
		shopPane.setAlignment(Pos.CENTER);
		setupShopItemBox();
	}

	private void initializeErrorMessage() {
		errorMessage = new Label("");
		errorMessage.setTextFill(Color.YELLOW);
		FontLoader.setFont(errorMessage, FontType.TELEGRAMA, 18);
	}

	private void setupPane() {
		StackPane pane = new StackPane();
		pane.getChildren().addAll(shopPane);
		pane.setAlignment(Pos.CENTER);
		VBox shelfBoxMain = new VBox(42);
		shelfBoxMain.setPadding(new Insets(33));
		shelfBoxMain.setAlignment(Pos.BOTTOM_CENTER);
		shelfBoxMain.getChildren().addAll(shelfBox, errorMessage);
		errorMessage.setAlignment(Pos.BOTTOM_RIGHT);
		this.getChildren().addAll(pane, shelfBoxMain);
	}

	// setup
	private void setBackgroundImage(Image bgImg) {
		ImageView bg = new ImageView(bgImg);
		bg.setFitHeight(HEIGHT);
		bg.setFitWidth(WIDTH);
		bg.setBlendMode(BlendMode.DARKEN);

		Rectangle rec = new Rectangle(WIDTH, HEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.7);

		this.getChildren().addAll(bg, rec);
	}

	private void setupShopItemBox() {
		shelfBox = new VBox(52);
		shelfBox.setPadding(new Insets(5));
		shelfBox.setAlignment(Pos.CENTER);

		// initialize ShopItemBox by item name
		shelf = new ArrayList<ShopItemBox>();
		for (String name : items) {
			shelf.add(new ShopItemBox(name));
		}
		Collections.shuffle(shelf);
		for (int i = 0; i < shelf.size(); i++) {
			shelfBox.getChildren().add(shelf.get(i));
		}
	}

	// =================== public static method ==============================

	public static void setErrorMessage(String text) {
		ShopPane.errorMessage.setText(text);
	}

	public static String getErrorMessage() {
		return ShopPane.errorMessage.getText();
	}

}
