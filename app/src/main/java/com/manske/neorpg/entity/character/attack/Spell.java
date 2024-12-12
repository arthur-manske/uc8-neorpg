package com.manske.neorpg.entity.character.attack;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.Character;

public class Spell extends Attack
{
	protected long manaCost;
	protected long currentManaCost;

	public Spell(String name, String info, long duration, long manaCost, long damage, long variableDamageRolls, long variableDamageFaces, long heal, long variableHealRolls, long variableHealFaces, long dodgeChances, long accuracyChances, long nonCritChance)
	{
		super(name, info, duration, damage, variableDamageRolls, variableDamageFaces, heal, variableHealRolls, variableHealFaces);

		this.nonCritChance     = nonCritChance;
		this.dodgeChances      = dodgeChances;
		this.accuracyChances   = accuracyChances;
		this.manaCost          = manaCost;
	}

	@Override public boolean canPerform(Character performer)
	{
		if (!this.isAvaliable()) return false;
	
		do {
			this.globalMultiplier = Terminal.getLong("Quantos multiplicadores deseja gastar nesse feitiço? (de 1 a " + (long) (performer.getCurMana() / this.manaCost) + "): ");
		}  while (this.globalMultiplier <= 0 || (this.globalMultiplier * this.manaCost) > performer.getCurMana());
		
		this.currentManaCost = this.manaCost * this.globalMultiplier;
		return true;
	}

	@Override protected void applyCosts(Character performer)
	{
		performer.addMana(-this.currentManaCost);
		this.currentManaCost = 0;
	}
};
