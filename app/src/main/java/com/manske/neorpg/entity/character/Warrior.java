package com.manske.neorpg.entity.character;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.attack.*;

public class Warrior extends Character
{
	public Warrior(String name)
	{
		super(
			name,
			Dice.roll("saúde",           3, 15),
			Dice.roll("força",           3, 20),
			Dice.roll("mana",            1, 10),
			Dice.roll("força mágica",    1, 10),
			Dice.roll("agilidade",       1, 15),
			Dice.roll("taxa de crítico", 3, 15),
			Dice.roll("velocidade",      1, 20),
			1
		);

		this.knownAttacks.add(new PhysicalAttack("Ataque regular", "Um ataque comum", 6, 1, 6, 25, 95, 90));
		this.knownAttacks.add(new PhysicalAttack("Ataque rápido", "Um ataque rápido", 1, 0, 0, 10, 95, 92));
		this.knownAttacks.add(new PhysicalAttack("Ataque forte", "Um ataque mais potente que o normal", 5, 3, 6, 25, 75, 75));
	}

	@Override public String getCharacterType() { return "Guerreiro"; }

	/* @Override public long heavyAttack(Character target) */
	/* { */
	/* 	long dealDamage; */

	/* 	long strengthBoost = (4 * (long)(Math.random() * 3)); */ 
	/* 	long agilityBoost  = (5 * (long)(Math.random() * 2)); */ 

	/* 	this.strength += strengthBoost; */
	/* 	this.agility  += agilityBoost; */

	/* 	dealDamage = super.heavyAttack(target); */
		
	/* 	this.strength -= strengthBoost; */
	/* 	this.agility  -= agilityBoost; */

	/* 	return dealDamage; */
	/* } */
};
