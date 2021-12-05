package item.base;

import entity.character.PlayerCharacter;
import exception.ConsumePotionFailedException;

public interface Consumable {
	public abstract void consumed(PlayerCharacter player) throws ConsumePotionFailedException;

}