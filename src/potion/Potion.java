package potion;

import entity.character.PlayerCharacter;
import exception.ConsumePotionFailedException;
import item.base.Consumable;

public abstract class Potion implements Consumable {

	@Override
	public abstract void consumed(PlayerCharacter player) throws ConsumePotionFailedException;

}
