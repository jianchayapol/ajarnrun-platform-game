package potion;

import entity.character.PlayerCharacter;
import exception.ConsumePotionFailedException;

public class HealthPotion extends Potion {

	int bonusLP;
	
	public HealthPotion(int bonusLP) {
		super();
		this.bonusLP = bonusLP;
	}

	@Override
	public void consumed(PlayerCharacter player) throws ConsumePotionFailedException {
		// TODO Auto-generated method stub
		player.setCurrentLP(player.getCurrentATK()+bonusLP);
	}

}
