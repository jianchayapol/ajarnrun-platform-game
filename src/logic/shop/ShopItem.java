package logic.shop;

import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

/**
 * This ShopItem class of shopping items in ShopeItemBox which builded in
 * ShopPane.
 * 
 * @author jianchayapol
 *
 */
public class ShopItem {

	/**
	 * A String that represents the item's name, used as the text Label and also the
	 * altText in the ImageButton class (ImageButtonType.BUY)
	 */
	private String itemName;

	/**
	 * A String represent the item's description. The descriptions are shown as a
	 * text Label in shopItemBox which builded in shopPane after completing each
	 * level of game.
	 */
	private String Description;

	/**
	 * This Image type field is represent the Image of the item. Used as the
	 * ImageView in shopItemBox which builded in shopPane after completing each
	 * level of game.
	 */
	private Image image;

	/**
	 * An private static final integer set up to $50 showing the price of the "run"
	 * item. Used as the the text Label "$ price" in shopItemBox which builded in
	 * shopPane after completing each level of game. Used in ShopManager to
	 * calculate and setup the buy item event.
	 */
	private static final int RUN_PRICE = 50;

	/**
	 * An private static final integer set up to $65 showing the price of the "jump"
	 * item. Used as the the text Label "$ price" in shopItemBox which builded in
	 * shopPane after completing each level of game. Used in ShopManager to
	 * calculate and setup the buy item event.
	 */
	private static final int JUMP_PRICE = 65;

	/**
	 * An private static final integer set up to $35 showing the price of the "time"
	 * item. Used as the the text Label "$ price" in shopItemBox which builded in
	 * shopPane after completing each level of game. Used in ShopManager to
	 * calculate and setup the buy item event.
	 */
	private static final int TIME_PRICE = 35;

	/**
	 * An private static final integer set up to $30 showing the price of the "lp"
	 * item. Used as the the text Label "$ price" in shopItemBox which builded in
	 * shopPane after completing each level of game. Used in ShopManager to
	 * calculate and setup the buy item event.
	 */
	private static final int LP_PRICE = 30;

	/**
	 * This constructor constructs new ShopItem by the String name By the name,
	 * setup all the field by the item's image, name and description
	 * Use RenderableHolder to access the public static Image field.
	 * @param name
	 */
	public ShopItem(String name) {
		switch (name) {
		case "run":
			image = RenderableHolder.run_item;
			Description = "Booster Speed";
			break;
		case "time":
			image = RenderableHolder.time_bonus_item;
			Description = "Time Bonus   ";
			break;
		case "jump":
			image = RenderableHolder.jump_item;
			Description = "Booster Jump ";
			break;
		case "lp":
			image = RenderableHolder.lp_bonus_item;
			Description = "Health Bonus ";
			break;

		default:
			break;
		}
		this.itemName = name;
	}

	/**
	 * This method is for Getting item's name.
	 * @return String of item's name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * This method is for Getting item's image.
	 * @return item's image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * This method is for Getting item's description.
	 * @return item's description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * This method is for setting item's description
	 * @param description
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * This method is for Getting item's price by item's name in format of "$ price".
	 * @return String of item's price in format of "$ price"
	 */
	public String getPriceText(String itemName) {
		switch (itemName) {
		case "run":
			return "$ " + String.valueOf(RUN_PRICE);
		case "time":
			return "$ " + String.valueOf(TIME_PRICE);
		case "jump":
			return "$ " + String.valueOf(JUMP_PRICE);
		case "lp":
			return "$ " + String.valueOf(LP_PRICE);
		default:
			return "";
		}
	}

	/**
	 * This method is for Getting "run" item's price
	 * @return an integer of "run" item's price.
	 */
	public static int getRunPrice() {
		return RUN_PRICE;
	}

	/**
	 * This method is for Getting "time" item's price
	 * @return an integer of "time" item's price.
	 */
	public static int getTimePrice() {
		return TIME_PRICE;
	}

	/**
	 * This method is for Getting "jump" item's price
	 * @return an integer of "jump" item's price.
	 */
	public static int getJumpPrice() {
		return JUMP_PRICE;
	}

	/**
	 * This method is for Getting "lp" item's price
	 * @return an integer of "lp" item's price.
	 */
	public static int getLpPrice() {
		return LP_PRICE;
	}

}
