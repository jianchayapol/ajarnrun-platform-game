package item.base;

import entity.character.PlayerCharacter;
import exception.EquipItemFailedException;

public interface Equipable {
	public abstract boolean equip(PlayerCharacter player) throws EquipItemFailedException;
}
