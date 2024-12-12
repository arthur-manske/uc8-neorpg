package com.manske.neorpg.entity.character.attack;

public class PhysicalAttack extends Attack
{
	public PhysicalAttack(String name, String info, long damage, long variableDamageRolls, long variableDamageFaces, long dodgeChances, long accuracyChances, long nonCritChance)
	{
		super(name, info, 0, damage, variableDamageRolls, variableDamageFaces, 0, 0, 0);

		this.nonCritChance   = nonCritChance;
		this.dodgeChances    = dodgeChances;
		this.accuracyChances = accuracyChances;
	}
};
