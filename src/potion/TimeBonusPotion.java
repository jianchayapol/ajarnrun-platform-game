package potion;

import entity.character.PlayerCharacter;
import exception.ConsumePotionFailedException;

public class TimeBonusPotion extends Potion{

	int bonusTime;
	
	public TimeBonusPotion(int bonusTime) {
		super();
		this.bonusTime = bonusTime;
	}

	@Override
	public void consumed(PlayerCharacter player) throws ConsumePotionFailedException {
		// TODO Auto-generated method stub
		
	}

}
