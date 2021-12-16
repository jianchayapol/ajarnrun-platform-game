package logic.shop;

import application.logic.GameManager;
import exception.BuyItemFailedException;
import gui.element.MoneyBox;
import gui.element.ShopPane;
import sharedObject.AudioLoader;

/**
 * This public ShopManager class is providing useful public static method for
 * logic and utility of shop and buying item management
 * 
 * @author jianchayapol
 *
 */
public class ShopManager {

	/**
	 * This method will check whether your total money is enough to buy the item
	 * with the price given from the parameter or not. If enough, will automatically
	 * reduce your money and return true
	 * 
	 * @throws BuyItemFailedException If the player doesn't have enough money and
	 *                                return false.
	 * 
	 * @param price
	 * @return boolean of the complete buying
	 */
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
		}
		return false;
	}

}
