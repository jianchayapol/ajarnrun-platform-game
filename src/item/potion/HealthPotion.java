package item.potion;

import entity.character.Player;
import exception.ConsumePotionFailedException;

public class HealthPotion extends Potion {

	int bonusLP;
	
	public HealthPotion(int bonusLP) {
		super();
		this.bonusLP = bonusLP;
	}

	@Override
	public void consumed(Player player) throws ConsumePotionFailedException {
		// TODO Auto-generated method stub
		player.setCurrentLP(player.getCurrentATK()+bonusLP);
	}

}
