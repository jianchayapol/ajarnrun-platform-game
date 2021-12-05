package entity.character;

import entity.base.Attackable;
import entity.base.Damagable;
import entity.base.Direction;
import entity.base.Entity;
import entity.base.Jumpable;
import entity.base.Runable;

public class PlayerCharacter extends Entity implements Attackable, Damagable, Jumpable, Runable {

	public PlayerCharacter(String name) {

		super();

		switch (name) {

		case "VKJ":
			this.setImageUrl("VKJ-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		case "PVK":
			this.setImageUrl("PVK-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		case "NNN":
			this.setImageUrl("NNN-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		case "ATS":
			this.setImageUrl("ATS-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		default:
			this.setImageUrl("VKJ-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;
		}

		this.setName(name);
		this.setDirection(null);
	}

	@Override
	public int attack(Damagable character) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveForward() {
		// TODO Auto-generated method stub
		Direction direction = this.getDirection();
		int x = (int) this.getCurrentPos().getX();
		int y = (int) this.getCurrentPos().getY();
		
		switch (direction){
			case RIGHT:
				if(x+1<480) this.setCurrentPos(x+1, y);
			case LEFT:
				this.setDirection(Direction.RIGHT);
				if(x-1>0) this.setCurrentPos(x-1, y);
			default:
				break;
		}
	}

	@Override
	public void turnBack() {
		// TODO Auto-generated method stub
		Direction direction = this.getDirection();
		switch(direction){
			case RIGHT:
				this.setDirection(Direction.LEFT);
			case LEFT:
				this.setDirection(Direction.RIGHT);
			default:
				this.setDirection(Direction.RIGHT);
		}
	}

}
