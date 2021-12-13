package logic.shop;

import exception.BuyItemFailedException;
import view.ShopScene;

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
			ShopScene.setErrorMessage(e.getMessage());
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
}
