package logic.shop;

import exception.BuyItemFailedException;

public class ShopManager {
	
	public static boolean buyShopItem(int price) {
		// check: Enough Money??
		// if enough, buy!
		// if not, response ERROR MESSAGE!
		try {
			if(price<=100) {
				return true;
			}
			else {
				throw new BuyItemFailedException();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return false;
	}
	
	
}
