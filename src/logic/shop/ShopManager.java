package logic.shop;

import application.logic.GameManager;
import exception.BuyItemFailedException;
import gui.element.GameHUD;
import gui.element.MoneyBox;
import gui.element.ShopPane;
import sharedObject.AudioLoader;

public class ShopManager {

	public static boolean buyShopItem(int price) {
		try {
			if (price <= GameManager.getPlayerCoin()) {
				int coinLeft = GameManager.getPlayerCoin() - price;
				GameManager.setPlayerCoin(coinLeft);
				MoneyBox.updateMoneyText();
				AudioLoader.Pick_Up_Item_Sound.play();
				return true;
			} else {
				throw new BuyItemFailedException();
			}
		} catch (Exception e) {
			ShopPane.setErrorMessage(e.getMessage());
			System.out.println(e.getMessage());
		}
		return false;
	}

}
