package entity.enemy;


import entity.base.Attackable;
import entity.base.Damagable;
import entity.base.Entity;
import entity.base.Runnable;

public abstract class Enemy extends Entity implements Runnable, Attackable, Damagable {
	
	protected EnemyType type;
	
	@Override
	public abstract int attack(Damagable character);
	
	
}
