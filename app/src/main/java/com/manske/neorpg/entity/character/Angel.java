package com.manske.neorpg.entity.character;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.attack.*;

public class Angel extends Character
{
	public Angel(String name)
	{
		super(
			name,
			Dice.roll("saúde",           1, 20),
			Dice.roll("força",           1, 20),
			Dice.roll("mana",            1, 20),
			Dice.roll("força mágica",    1, 20),
			Dice.roll("agilidade",       1, 20),
			Dice.roll("taxa de crítico", 1, 20),
			Dice.roll("velocidade",      1, 20),
			Dice.roll("poder divino",    7, 20)
		);

		this.knownAttacks.add(new PhysicalAttack("Ataque regular", "Um ataque comum", 5L, 0L, 0L, 25L, 95L, 90L));
		this.knownAttacks.add(new PhysicalAttack("Ataque rápido", "Um ataque rápido", 2L, 0L, 0L, 10L, 95L, 92L));
		this.knownAttacks.add(new PhysicalAttack("Ataque forte", "Um ataque mais potente que o normal", 5L, 2L, 6L, 30L, 70L, 85L));
	}
	
	@Override public String getCharacterType() { return "Anjo"; }
};
