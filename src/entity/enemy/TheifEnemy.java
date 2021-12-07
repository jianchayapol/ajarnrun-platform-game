package entity.enemy;

import application.logic.GameController;
import entity.base.Damagable;
import entity.base.Direction;
import entity.character.Player;

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

	@Override
	public void moveForward(Direction direction) {
		// TODO Auto-generated method stub
		
	}


}
