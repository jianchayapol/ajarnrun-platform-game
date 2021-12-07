package item.potion;

import entity.character.Player;
import exception.ConsumePotionFailedException;
import item.base.Consumable;

public abstract class Potion implements Consumable {

	@Override
	public abstract void consumed(Player player) throws ConsumePotionFailedException;

}
