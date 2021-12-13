package gui.element;

import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.shop.ShopItem;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

public class ShopItemBox extends HBox{
	
	private ShopItem item;
	
	private ImageView itemImage;
	private Label nameTag;
	private Label priceTag;
	private ImageButton buyButton;
	private static final int WIDTH = 160;
	private static final int HEIGHT = 25;
	
	public ShopItemBox(String name) {
		item = new ShopItem(name);
		setupItemBox();
		this.setPrefSize(WIDTH,HEIGHT);
		this.setSpacing(20);
		this.setPadding(new Insets(10,10,10,10));
	}
	
	private void setupItemBox() {
		// Buy Button
		buyButton = new ImageButton(ImageButtonType.BUY, item.getItemName());
		// Item Image
		itemImage = new ImageView(item.getImage());
		itemImage.setFitWidth(WIDTH/4);
		// name tag
		nameTag = new Label(item.getDescription());
		nameTag.setTextFill(Color.SADDLEBROWN);
		FontLoader.setFont(nameTag, FontType.TELEGRAMA, 30);	
		// price tag
		priceTag = new Label(item.getPriceText());
		FontLoader.setFont(priceTag, FontType.TELEGRAMA, 20);
		
		VBox infoBox = new VBox(10);
		infoBox.getChildren().addAll(nameTag,priceTag);
		
		this.getChildren().addAll(itemImage,infoBox,buyButton);
		this.setAlignment(Pos.CENTER);
	}
	
	
}
