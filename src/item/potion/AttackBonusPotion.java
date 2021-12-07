package item.potion;

import entity.character.Player;
import exception.ConsumePotionFailedException;
import item.base.Consumable;

public class AttackBonusPotion extends Potion {
	int BonusATK;

	public AttackBonusPotion(int bonusATK) {
		super();
		BonusATK = bonusATK;
	}

	@Override
	public void consumed(Player player) throws ConsumePotionFailedException {
		player.setCurrentATK(player.getCurrentATK()+BonusATK);
	}
	
}
