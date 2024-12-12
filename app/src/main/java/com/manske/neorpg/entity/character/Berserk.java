package com.manske.neorpg.entity.character;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.attack.*;

import com.manske.neorpg.entity.character.Character;

public class Berserk extends Character
{
	public Berserk(Warrior warrior)
	{
		super(warrior.getName(), warrior.maxLife, warrior.strength + 7, warrior.maxMana, warrior.mage, warrior.agility + 5, warrior.critRatio + 5, warrior.velocity + 5, warrior.angel);

		this.maxMana = this.curMana = 0;
		
		this.knownAttacks = warrior.knownAttacks;
		this.knownSpells  = warrior.knownSpells;

		/* Por padrão o ataque é fraco, mas ele é potencializado por(vida total - vida atual), em todos seus atributos */
		this.knownAttacks.add(new BerserkAttack("Ataque enfurecido", "Quanto menor sua vida, mais poderoso fica", 6, 1, 6, 15, 55, 75));
		/* TODO: Ataque especiais que custam vida do Berserk */
	}

	@Override public void addMana(long amount)
	{
		this.maxMana = this.curMana = 0;
		this.heal(amount); /* Berserk não usa Mana, mas sim vida */
	}

	@Override public String getCharacterType() { return "Berserk"; }
};
