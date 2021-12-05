package potion;

import entity.character.PlayerCharacter;
import exception.ConsumePotionFailedException;
import item.base.Consumable;

public class AttackBonusPotion extends Potion {
	int BonusATK;

	public AttackBonusPotion(int bonusATK) {
		super();
		BonusATK = bonusATK;
	}

	@Override
	public void consumed(PlayerCharacter player) throws ConsumePotionFailedException {
		player.setCurrentATK(player.getCurrentATK()+BonusATK);
	}
	
}
