package com.manske.neorpg.entity.character.nonplayable;

import com.manske.neorpg.entity.character.attack.*;
import com.manske.neorpg.entity.character.Character;

public class Enemy extends Character
{
	protected String race;
	protected String kind;
	
	public Enemy(String name, String race, String kind, long life, long strength, long mana, long mage, long agility, long critRatio, long velocity, long angel, long level, long expLevel)
	{
		super(name, life, strength, mana, mage, agility, critRatio, velocity, angel);

		this.race = race;
		this.kind = kind;

		this.level    = level;
		this.expLevel = expLevel;

		this.knownAttacks.add(new PhysicalAttack("Ataque regular", "Um ataque comum", 5, 0, 0, 25, 95, 90));
	}

	@Override public String getCharacterType() { return race + " " + kind; }
};
