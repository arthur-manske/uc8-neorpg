package com.manske.neorpg.entity.character;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.attack.*;

public class Mage extends Character
{
	public Mage(String name)
	{
		super(
			name,
			Dice.roll("saúde",           2, 20),
			Dice.roll("força",           1, 20),
			Dice.roll("mana",            3, 20),
			Dice.roll("força mágica",    3, 20),
			Dice.roll("agilidade",       2, 20),
			Dice.roll("taxa de crítico", 1, 20),
			Dice.roll("velocidade",      2, 20),
			1
		);

		this.knownAttacks.add(new PhysicalAttack("Ataque regular", "Um ataque comum", 5, 0, 0, 25, 95, 90));
		this.knownAttacks.add(new PhysicalAttack("Ataque rápido", "Um ataque rápido", 2, 0, 0, 10, 95, 92));
		this.knownAttacks.add(new PhysicalAttack("Ataque forte", "Um ataque mais potente que o normal", 5, 2, 6, 30, 70, 85));
	}
	
	@Override public String getCharacterType() { return "Mago"; }

	/* @Override public int useSpell(Character target) */
	/* { */

	/* } */
};
