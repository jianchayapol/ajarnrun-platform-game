package enemy;

import base.Jumpable;
import base.Moveable;

public class TheifEnemy extends Enemy implements Jumpable {

	private int vy;
	
	public TheifEnemy(String name, int atk, int hp) {
		super(name, atk, hp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

}
