package gui.element;

import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.shop.ShopItem;
import sharedObject.FontLoader;
import sharedObject.FontType;

public class ShopItemBox extends StackPane{
	
	private ShopItem item;
	private ImageView itemImage;
	private Label nameTag;
	private Label priceTag;
	private HBox mainBox;
	private ImageButton buyButton;
	private static final int IMAGE_SIZE = 40;
	private static final int WIDTH = 265;
	private static final int HEIGHT = 60;
	
	public ShopItemBox(String name) {
		item = new ShopItem(name);
		setupItemBox();
		this.setPrefSize(WIDTH, HEIGHT);
		this.getChildren().add(mainBox);
	}
	
	private void setupItemBox() {
		// SetUp
		setUpBuyButton();
		setUpItemImage();
		setUpNameTag();
		setUpPriceTag();
		
		VBox infoBox = new VBox(4);
		infoBox.getChildren().addAll(nameTag,priceTag);
		
		mainBox = new HBox(10);
		mainBox.getChildren().addAll(itemImage,infoBox,buyButton);
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setPrefSize(WIDTH, HEIGHT);
		mainBox.setMaxWidth(265);
		setUpMouseEnter();
	}

	private void setUpMouseEnter() {
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				ShopItemBox.this.mainBox.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				ShopItemBox.this.mainBox.setBackground(null);
			}
		});
	}
	private void setUpBuyButton() {
		buyButton = new ImageButton(ImageButtonType.BUY, item.getItemName());
		buyButton.setLayoutX(0);
	}
	private void setUpItemImage() {
		itemImage = new ImageView(item.getImage());
		itemImage.setFitWidth(IMAGE_SIZE);
		itemImage.setFitHeight(IMAGE_SIZE);
	}
	private void setUpPriceTag() {
		priceTag = new Label(item.getPriceText());
		FontLoader.setFont(priceTag, FontType.TELEGRAMA, 16);
	}
	private void setUpNameTag() {
		nameTag = new Label(item.getDescription());
		nameTag.setTextFill(Color.SADDLEBROWN);
		FontLoader.setFont(nameTag, FontType.TELEGRAMA, 15);	
	}
}