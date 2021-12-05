package entity.enemy;

import entity.base.Damagable;
import entity.base.Jumpable;
import entity.character.PlayerCharacter;

public class VehicleEnemy extends Enemy implements Jumpable {

	VehicleEnemy(String name) {
		
		super();
		switch (name) {
			
		case "car":
			this.setImageUrl("car-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;
			
		case "motorcycle":
			this.setImageUrl("motorcycle-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;
			
		default:
			this.setImageUrl("motorcycle-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;
		}
		
		this.type = EnemyType.VEHICLE;
		this.setName(name);
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int attack(Damagable character) {
		if(character instanceof PlayerCharacter) return this.currentATK;
		return 0;
	}


}
