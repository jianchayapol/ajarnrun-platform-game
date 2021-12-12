package logic.shop;

import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class ShopItem {

	private String itemName;
	private String Description;
	private Image image;
	private int price;

	public ShopItem(String itemName) {
		switch (itemName) {
		case "run":
			image = RenderableHolder.run_item;
			price = 10;
			Description = "";
			break;
		case "time":
			image = RenderableHolder.time_bonus_item;
			price = 10;
			Description = "";
			break;
		case "jump":
			image = RenderableHolder.jump_item;
			price = 10;
			Description = "";
			break;
		case "lp":
			image = RenderableHolder.lp_bonus_item;
			price = 10;
			Description = "";
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

	public String getPriceText() {
		return "$ " + String.valueOf(price);
	}
}

