package logic.shop;

import exception.BuyItemFailedException;
import gui.element.ShopPane;

public class ShopManager {
	
	public static boolean buyShopItem(int price) {
		try {
			if(false) {
				return true;
			}
			else {
				throw new BuyItemFailedException();
			}
		}
		catch(Exception e) {
			ShopPane.setErrorMessage(e.getMessage());
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
}
