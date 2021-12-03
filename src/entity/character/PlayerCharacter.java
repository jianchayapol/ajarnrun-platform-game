package entity.character;

import java.util.ArrayList;

import entity.base.Attackable;
import entity.base.Damagable;
import entity.base.Entity;
import entity.base.EntityStatus;
import item.weapon.Weapon;

public class PlayerCharacter extends Entity implements Attackable, Damagable{
	
	
	PlayerCharacter(String name) {
		
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
	}

	@Override
	public int attack(Damagable character) {
		// TODO Auto-generated method stub
		return 0;
	}
		
	
}
