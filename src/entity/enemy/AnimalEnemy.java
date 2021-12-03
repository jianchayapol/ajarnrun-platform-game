package entity.enemy;

import entity.base.Damagable;
import entity.character.PlayerCharacter;

public class AnimalEnemy extends Enemy {

	AnimalEnemy(String name) {

		super();
		switch (name) {

		case "dog":
			this.setImageUrl("dog-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		case "kingkong":
			this.setImageUrl("kingkong-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		case "lion":
			this.setImageUrl("lion-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		case "bull":
			this.setImageUrl("ATS-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		case "mongoose":
			this.setImageUrl("ATS-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;

		default:
			this.setImageUrl("dog-Character.png");
			this.setMaxLP(120);
			this.setCurrentLP(this.getMaxLP());
			this.setDefaultATK(100);
			this.setCurrentATK(this.getDefaultATK());
			break;
		}

		this.type = EnemyType.ANIMAL;
		this.setName(name);
	}

	@Override
	public int attack(Damagable character) {
		if (character instanceof PlayerCharacter)
			return this.currentATK;
		return 0;
	}

}
