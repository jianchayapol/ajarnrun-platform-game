package item.base;

import entity.character.Player;
import exception.EquipItemFailedException;

public interface Equipable {
	public abstract boolean equip(Player player) throws EquipItemFailedException;
}
