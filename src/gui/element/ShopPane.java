package gui.element;

import java.util.ArrayList;
import java.util.Collections;

import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

/**
 * ShopPane extends AnchorPane is class of shop user interface. ShopPane
 * contains components of shelf picture, and ShopItemBox.
 * 
 * @author jianchayapol
 *
 */
public class ShopPane extends AnchorPane {

	/**
	 * AnchorPane of shopStore containing the shelfBox.
	 */
	private static AnchorPane shopStore;
	/**
	 * This Rectangle rec is a dark background of this pane.
	 */
	private static Rectangle rec;
	/**
	 * ShelfBox is a VBox that contains all ShopItemBoxes.
	 */
	private static VBox shelfBox;
	/**
	 * controlBox is a VBox that contains shopStore and nextLvButton.
	 */
	private static VBox controlBox;

	/**
	 * shopPane is StackPane that contains ImageView of shop picture.
	 */
	private static StackPane shopPane;
	
	/**
	 * A Label that showing "Not Enough Money" message
	 */
	private static Label errorMessage;

	/**
	 * shelf is ArrayList<ShopItemBox> containing all shopItem in the shop
	 */
	private static ArrayList<ShopItemBox> shelf;
	private static final String[] items = { "run", "jump", "lp", "time" };

	/**
	 * ImageButton with ImageButtonType.CONTINUE_NEXT_LV
	 */
	private static ImageButton nextLvButton = new ImageButton(ImageButtonType.CONTINUE_NEXT_LV);

	/**
	 * ShopPane's height
	 */
	private static final int HEIGHT = 600;
	
	/**
	 * ShopPane's width
	 */
	private static final int WIDTH = 800;
	
	/**
	 * Constructor constructs and initialize by initializeShopPane() and initializeErrorMessage() and setupPane().
	 * add all the components rec, controlBox, errorMessage to the ShopPane.
	 */
	public ShopPane() {
		super();
		// initialize
		initializeShopPane();
		initializeErrorMessage();
		// setup
		setupPane();
		this.getChildren().addAll(rec, controlBox, errorMessage);
	}

	// =================== private static method ==============================

	/**
	 * method for initializing ShopPane,
	 * creating new StackPane and set it fit width and height to  400,500 respectively and add to shopPane.
	 * setting shopPane pref size to 400x520
	 * and calling setupShopItemBox() method.
	 *
	 */
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

	/**
	 * method for initializing new Label with empty text,
	 * set this label's font using FontLoader with FontType.TELEGRAMA, size=18, set text fill to
	 * be yellow setAlignment to Pos.BOTTOM_RIGHT,
	 * setLayoutX to 550 , setLayoutY to 535,
	 * 
	 */
	private void initializeErrorMessage() {
		errorMessage = new Label("");
		errorMessage.setTextFill(Color.YELLOW);
		FontLoader.setFont(errorMessage, FontType.TELEGRAMA, 18);
		errorMessage.setAlignment(Pos.BOTTOM_RIGHT);
		errorMessage.setLayoutX(550);
		errorMessage.setLayoutY(535);
	}

	/**
	 * method for setting up Pane
	 * Setting up shelfBoxMain to be new VBox with spacing=40.
	 * and add shelfBox to the ShelfBoxMain with Alignment=BOTTOM_CENTER
	 * Setting up shelfstore to be new AnchorPane 
	 * Add all the shopPane, shelfBoxMain to the shelfStore.
	 * Setting up shelfBoxMain's LayoutX=70 and LayoutY=117
	 * and calling setupRec() and setupControlBox() method to set up other component.
	 */
	private void setupPane() {
		VBox shelfBoxMain = new VBox(40);
		shelfBoxMain.setAlignment(Pos.BOTTOM_CENTER);
		shelfBoxMain.getChildren().add(shelfBox);

		shopStore = new AnchorPane();
		shopStore.getChildren().addAll(shopPane, shelfBoxMain);
		shelfBoxMain.setLayoutX(70);
		shelfBoxMain.setLayoutY(117);
		setupRec();
		setupControlBox();
	}

	/**
	 * Method for setting up Rectangle rec, a dark background of the ShopPane
	 * with the size of the ShopPane. SetFill to be black with opacity 0.6
	 */
	private void setupRec() {
		rec = new Rectangle(WIDTH, HEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.6);
	}

	/**
	 * Method for setting up controlBox as a new VBox with spacing=3 to store shopStore, nextLvButton.
	 * Set its LayoutX,LayoutY to be 200,3 respectively. Set alignment to CENTER
	 */
	private void setupControlBox() {
		controlBox = new VBox(3);
		controlBox.getChildren().addAll(shopStore, nextLvButton);
		controlBox.setLayoutX(200);
		controlBox.setLayoutY(3);
		controlBox.setAlignment(Pos.CENTER);
	}

	/**
	 *  Method for setting up ShopItemBox as a new VBox with spacing=37. set alignment to CENTER.
	 *  initialize ShopItemBox by item name by looping through items to get the all item's name, then add new ShopItem to the shelf.
	 *  Use method ShopPane.shuffleItems() to shuffle the order of shelf.
	 *  Looping through the shelf to add all the ShopItemBox to the shelfBox. 
	 */
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

	/**
	 * public static method for setting errorMessage 
	 * @param text a String of errorMessage
	 */
	public static void setErrorMessage(String text) {
		ShopPane.errorMessage.setText(text);
	}

	/**
	 * public static method for getting errorMessage 
	 * @return text a String of errorMessage
	 */
	public static String getErrorMessage() {
		return ShopPane.errorMessage.getText();
	}

	/**
	 * public static method to shuffle the ShopPane.shelf
	 * by calling Collections.shuffle(ArrayList<>).
	 */
	public static void shuffleItems() {
		Collections.shuffle(ShopPane.shelf);
	}

	/**
	 * public static method to get the shelf.
	 * @return shelf an ArrayList containing all ShopItemBoxes.
	 */
	public static ArrayList<ShopItemBox> getShelf() {
		return shelf;
	}

}