package gui.element;

import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

public class ShopPane extends AnchorPane {
	
	private static AnchorPane shopStore;
	private static Rectangle rec;
	private static VBox shelfBox;
	private static StackPane shopPane;
	private static Label errorMessage;
	private static ArrayList<ShopItemBox> shelf;
	private static final String[] items = { "run", "jump", "lp", "time" };
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	public ShopPane() {
		
		super();
	
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
		VBox shelfBoxMain = new VBox(40);
		shelfBoxMain.setAlignment(Pos.BOTTOM_CENTER);
		shelfBoxMain.getChildren().addAll(shelfBox, errorMessage);
		errorMessage.setAlignment(Pos.BOTTOM_RIGHT);
		shopStore = new AnchorPane();
		shopStore.getChildren().addAll(shopPane, shelfBoxMain);
		shelfBoxMain.setLayoutX(70);
		shelfBoxMain.setLayoutY(117);
		
		rec = new Rectangle(WIDTH, HEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.6);
		
		
		shopStore.setLayoutX(200);
		shopStore.setLayoutY(20);
		this.getChildren().addAll(rec,shopStore);
	}


	private void setupShopItemBox() {
		shelfBox = new VBox(37);
		shelfBox.setAlignment(Pos.CENTER);

		// initialize ShopItemBox by item name
		shelf = new ArrayList<ShopItemBox>();
		for (String name : items) {
			shelf.add(new ShopItemBox(name));
		}
		ShopPane.shuffleItems();
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
	
	public static void shuffleItems() {
		Collections.shuffle(ShopPane.shelf);
	}

}