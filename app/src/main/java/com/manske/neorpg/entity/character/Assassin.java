package com.manske.neorpg.entity.character;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.attack.*;

public class Assassin extends Character
{
	public Assassin(String name)
	{
		super(
			name,
			Dice.roll("Vida",       2, 20),
			Dice.roll("Força",      2, 20),
			Dice.roll("Mana",       1, 20),
			Dice.roll("Magica",     1, 20),
			Dice.roll("Agilidade",  4, 20),
			Dice.roll("Crítico",    2, 20),
			Dice.roll("Velocidade", 2, 20),
			Dice.roll("Divino",     1, 1)
		);

		/* Argumentos */
		/* String name, String info */
		/* long duration, */
		/* long damage, long variableDamageRolls, long variableDamageFaces, */
		/* long dodgeChances, long accuracyChances, long nonCritRatio */
		this.knownAttacks.add(new PhysicalAttack("Ataque regular", "Um ataque comum", 5, 0, 0, 25, 95, 90));
		this.knownAttacks.add(new PhysicalAttack("Ataque rápido", "Um ataque rápido", 6, 1, 6, 10, 95, 75));
		this.knownAttacks.add(new PhysicalAttack("Ataque forte", "Um ataque mais potente que o normal", 5, 1, 6, 30, 70, 85));
	}
	
	@Override public String getCharacterType() { return "Assassino"; }

	/* @Override public int takeTurn(Character target) */
	/* { */
	/* 	/1* Função do turno do asassino *1/ */
	/* } */

	/* @Override public int useSpell(Character target) */
	/* { */
	/* 	/1* O assassino pode roubar vida... *1/ */
	/* } */
};
