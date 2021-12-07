package item.potion;

import entity.character.Player;
import exception.ConsumePotionFailedException;

public class TimeBonusPotion extends Potion{

	int bonusTime;
	
	public TimeBonusPotion(int bonusTime) {
		super();
		this.bonusTime = bonusTime;
	}

	@Override
	public void consumed(Player player) throws ConsumePotionFailedException {
		// TODO Auto-generated method stub
		
	}

}
