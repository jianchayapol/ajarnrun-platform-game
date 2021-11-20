package enemy;

import base.Attackable;
import base.Damageable;
import base.Moveable;
import base.Turnable;

public class Enemy implements Attackable, Damageable, Moveable, Turnable{
	
	String name;
	int atk;
	int hp;
	private int vx;
	
	public Enemy(String name, int atk, int hp) {
		super();
		this.name = name;
		this.atk = atk;
		this.hp = hp;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public int attack(Damageable character) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveForward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnBack() {
		// TODO Auto-generated method stub
		
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}
}
