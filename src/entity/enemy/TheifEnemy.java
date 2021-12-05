package entity.enemy;

import application.GameController;
import entity.base.Damagable;
import entity.character.PlayerCharacter;

public class TheifEnemy extends Enemy {

	public TheifEnemy() {
		this.setInitialPos(1000, 500);
	}

	public int attack(Damagable player) {
		// TODO Auto-generated method stub
		int stolenMoney = (int) ((Math.random() * (GameController.getCoin())));
		GameController.setCoin(GameController.getCoin() - stolenMoney);
		return stolenMoney;
	}


}
