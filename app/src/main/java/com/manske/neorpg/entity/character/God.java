package com.manske.neorpg.entity.character;

import com.manske.neorpg.entity.character.attack.*;

public class God extends Character
{
	public God(String name)
	{
		super(
			name,
			1,
			1, 
			1,
			1,
			1,
			1,
			1,
			Long.MAX_VALUE
		);

		this.knownAttacks.add(new PhysicalAttack("Morte", "Mata qualquer coisa", 1, 0, 0, 0, 100, 100));
	}

	@Override public void heal(long amount)       { return; }
	@Override public void addMana(long amount)    { return; }
	@Override public long takeDamage(long amount) { return 0; }
	@Override public long addExp(long points)     { return 0; }
};
