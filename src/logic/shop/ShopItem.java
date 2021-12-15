package logic.shop;

import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class ShopItem {

	private String itemName;
	private String Description;
	private Image image;
	private int price;

	private static final int RUN_PRICE = 30 + (int) (Math.random() * 20);
	private static final int TIME_PRICE = 30 + (int) (Math.random() * 20);;
	private static final int JUMP_PRICE = 30 + (int) (Math.random() * 20);;
	private static final int LP_PRICE = 30 + (int) (Math.random() * 20);;

	public ShopItem(String itemName) {
		switch (itemName) {
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
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public int getPrice() {
		return price;
	}

	public Image getImage() {
		return image;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

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
			return "$ " + "1000";
		}
	}

	public static int getRunPrice() {
		return RUN_PRICE;
	}

	public static int getTimePrice() {
		return TIME_PRICE;
	}

	public static int getJumpPrice() {
		return JUMP_PRICE;
	}

	public static int getLpPrice() {
		return LP_PRICE;
	}

}
