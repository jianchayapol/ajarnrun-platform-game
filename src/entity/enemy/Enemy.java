package entity.enemy;

import entity.base.*;

public abstract class Enemy extends Entity implements Runable, Attackable, Damagable {
	
	protected EnemyType type;
	
	@Override
	public abstract int attack(Damagable character);

	@Override
	public void moveForward() {
		
	}

	@Override
	public void turnBack() {
		
	}
}
