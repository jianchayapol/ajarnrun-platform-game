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

/**
 * ShopItemBox extends StackPane is a box containing item in ShopPane
 * 
 * @author jianchayapol
 *
 */
public class ShopItemBox extends StackPane {

	/**
	 * item (ShopItem class)
	 */
	private static ShopItem item;
	/**
	 * ImageView of item
	 */
	private ImageView itemImage;
	/**
	 * Label of item's name
	 */
	private Label nameTag;
	/**
	 * Label of item's price
	 */
	private Label priceTag;
	/**
	 * HBox containing all components
	 */
	private HBox mainBox;
	/**
	 * ImageButton with ImageButtonType.BUY used to buy the selected item in
	 * ShopPane.
	 */
	private ImageButton buyButton;
	/**
	 * size of item's image
	 */
	private static final int IMAGE_SIZE = 40;
	/**
	 * box's width
	 */
	private static final int WIDTH = 265;
	/**
	 * box's height
	 */
	private static final int HEIGHT = 60;

	/**
	 * Constructor constructs ShopItemBox by the name given from parameter. Calling
	 * setupItemBox() method to setup all the components. set the ShopItemBox's
	 * PrefSize to WIDTH,HEIGHT. add the mainBox to ShopItemBox
	 * 
	 * @param name
	 */
	public ShopItemBox(String name) {
		item = new ShopItem(name);
		setupItemBox();
		this.setPrefSize(WIDTH, HEIGHT);
		this.getChildren().add(mainBox);
	}

	/**
	 * Setting up itemBox by calling setUpBuyButton(), setUpItemImage(),
	 * setUpNameTag(), setUpPriceTag() method. Creating new VBox with spacing = 4 to
	 * store nameTag, priceTag. Initialize mainBox with new HBox with spacing 10 to
	 * store itemImage, infoBox, buyButton. Setup mainBox's PrefSize to fit the
	 * Box's Size and set its maxWidth to 265. Set up the mouse enter event by
	 * calling method setUpMouseEnter().
	 */
	private void setupItemBox() {
		// SetUp
		setUpBuyButton();
		setUpItemImage();
		setUpNameTag();
		setUpPriceTag();

		VBox infoBox = new VBox(4);
		infoBox.getChildren().addAll(nameTag, priceTag);

		mainBox = new HBox(10);
		mainBox.getChildren().addAll(itemImage, infoBox, buyButton);
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setPrefSize(WIDTH, HEIGHT);
		mainBox.setMaxWidth(265);
		setUpMouseEnter();
	}

	/**
	 * method for setting EventHandler MouseEvent to set the Box's background to
	 * Color.ANTIQUEWHITE while mouse entering and set null while mouse exit.
	 */

	private void setUpMouseEnter() {

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				ShopItemBox.this.mainBox.setBackground(
						new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				ShopItemBox.this.mainBox.setBackground(null);
			}
		});
	}

	/**
	 * method for setting buy button, initialize buyButton with ImageButtonType.BUY
	 * and item's name. and setLayoutX to 0.
	 */
	private void setUpBuyButton() {
		buyButton = new ImageButton(ImageButtonType.BUY, item.getItemName());
		buyButton.setLayoutX(0);
	}

	/**
	 * method for setting itemImage, initialize new ImageView with item's image and
	 * set fit width and height to IMAGE_SIZE.
	 */
	private void setUpItemImage() {
		itemImage = new ImageView(item.getImage());
		itemImage.setFitWidth(IMAGE_SIZE);
		itemImage.setFitHeight(IMAGE_SIZE);
	}

	/**
	 * method for setting priceTag, initialize new Label with text of item's price
	 * by calling getPriceText() method. Set the label's font by using static method
	 * setFont() from FontLoader to set font to FontType.TELEGRAMA and fontSize 16
	 */
	private void setUpPriceTag() {
		priceTag = new Label(item.getPriceText(item.getItemName()));
		FontLoader.setFont(priceTag, FontType.TELEGRAMA, 16);
	}

	/**
	 * method for setting priceTag, initialize new Label with text of item's
	 * description. Set the label's font by using static method setFont() from
	 * FontLoader to set font to FontType.TELEGRAMA and fontSize 15. Set textFill to
	 * SADDLEBROWN.
	 */
	private void setUpNameTag() {
		nameTag = new Label(item.getDescription());
		nameTag.setTextFill(Color.SADDLEBROWN);
		FontLoader.setFont(nameTag, FontType.TELEGRAMA, 15);
	}

}