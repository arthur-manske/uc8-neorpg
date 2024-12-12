package com.manske.neorpg.entity.character.attack;

import com.manske.neorpg.entity.character.Character;

public class BerserkAttack extends PhysicalAttack
{
	public BerserkAttack(String name, String info, long damage, long diceRolls, long diceSides, long dodgeChances, long accuracy, long nonCritRatio)
	{
		super(name, info, damage, diceRolls, diceSides, dodgeChances, accuracy, nonCritRatio);
	}

	public long getLifePercentage(Character who)
	{
		if (who == null || who.getMaxLife() == 0) return 0;
		return (long) ((who.getCurLife() * 100) / who.getMaxLife());
	}

	@Override public boolean canPerform(Character performer)
	{
		this.accuracyChances += this.getLifePercentage(performer);
		return this.isAvaliable();
	}


	@Override protected void applyCosts(Character performer)
	{
		if (!performer.isAlive()) return;

		this.accuracyChances -= this.getLifePercentage(performer);
		this.globalMultiplier = (long) this.getLifePercentage(performer);
	}
};
