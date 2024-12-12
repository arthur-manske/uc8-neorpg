package com.manske.neorpg.entity.character.attack;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.Character;

import java.util.HashMap;
import java.util.Map;

public class Attack
{
	protected String name;
	protected String info;

	protected long duration;

	/* Chances de ataque NÃO crítico, quanto mais alta, mais díficil é existir um crítico
		Ela é removida da chance original do jogador (ex: jogador tem 40, isso é 30, então o jogador só tem 10 de chance)
		Nota: A chance mínima de crítico é 1% (mesmo com os status do jogador zerados).
	*/
	protected long nonCritChance = 0;
	/* Chances mínima para desvio (0-100) */
	protected long dodgeChances = 0;
	/* Chance mínima para acerto (0-100) */
	protected long accuracyChances = 0;

	protected long damage;
	/* Usado na aleatorização, será jogado um dado entre */
	protected long variableDamageRolls, variableDamageFaces;
	
	/* Caso seja um ataque de roubo de vida (ou movimento de cura) */
	protected long heal;
	protected long variableHealRolls, variableHealFaces;
	
	protected long currentDuration = 0;

	protected long globalMultiplier = 1;
	
	public Attack(String name, String info, long durationInTurns, long damage, long variableDamageRolls, long variableDamageFaces, long heal, long variableHealRolls, long variableHealFaces)
	{
		this.name                = name;
		this.info                = info;
		this.duration            = durationInTurns;
		this.damage              = damage;
		this.variableDamageRolls = variableDamageRolls;
		this.variableDamageFaces = variableDamageFaces;
		this.heal                = heal;
		this.variableHealRolls   = variableHealRolls;
		this.variableHealFaces   = variableHealFaces;
	}

	public boolean isAvaliable()  { return this.currentDuration <= 0;           }
	public boolean isPassive()    { return this.duration        >  0;           }
	public boolean isHealOnly()   { return (this.heal > 0 && this.damage <= 0); }
	public boolean isDamageOnly() { return (this.damage > 0 && this.heal <= 0); }
	public boolean isLifeSteal()  { return (this.damage > 0 && this.heal > 0);  }

	public String getName() { return this.name; }
	public String getInfo() { return this.info; }

	public long getDamage()              { return this.damage;              }
	public long getDuration()            { return this.duration;            }
	public long getVariableDamageRolls() { return this.variableDamageRolls; }
	public long getVariableDamageFaces() { return this.variableDamageFaces; }
	public long getHeal()                { return this.heal;                }
	public long getVariableHealRolls()   { return this.variableHealRolls;   }
	public long getVariableHealFaces()   { return this.variableHealFaces;   }
	
	public long getVariableDamageMin() { return (1 * this.variableDamageRolls);                        }
	public long getVariableDamageMax() { return (this.variableDamageFaces * this.variableDamageRolls); }
	public long getVariableHealMin()   { return (1 * this.variableHealRolls);                          }
	public long getVariableHealMax()   { return (this.variableHealFaces * this.variableHealRolls);     }
	
	protected boolean internalPerform(Character performer, Character target)
	{
		long multiplier = 1;
		long extraHeal = (this.variableHealRolls > 0)
			? Dice.roll("vida extra", this.variableHealRolls, this.variableHealFaces) : 0;
		long extraDamage = (this.variableDamageRolls > 0)
			? Dice.roll("dano extra", this.variableDamageRolls, this.variableDamageFaces) : 0;
		
		if (this.isPassive()) {
			this.nonCritChance = 100;
			this.dodgeChances = 0;
		}
	
		performer.heal((this.heal + extraHeal) * performer.getAngel() * this.globalMultiplier);
		if (this.damage <= 0) return true;

		if (this.nonCritChance != 100) {
			long chances = Dice.roll("chances de ataque crítico", 1, performer.getCritRatio() * performer.getAngel());
			if (chances > this.nonCritChance) multiplier = 2 * performer.getAngel();
		}
	
		/* nota para danos mágicos deveriamos usar performer.getMage() */
		return target.receiveAttack(
			(this.damage + extraDamage + performer.getStrength()) * performer.getAngel() * multiplier * this.globalMultiplier,
			Math.max(dodgeChances - performer.getAngel(), 0)
		) != 0;
	}

	public boolean canPerform(Character performer)
	{
		/* Mais útil em feitiços, onde se checa a mana atual e o custo*/
		return this.isAvaliable();
	}

	protected void applyCosts(Character performer) {}

	public boolean perform(Character performer, Character target)
	{
		if (!this.canPerform(performer)) return false;
		if (this.isPassive() && this.currentDuration == this.duration) {
			this.applyCosts(performer);
			return this.enqueue(performer, target);
		}
	
		if (!this.isPassive()) {
			long chances = Dice.roll("chances de acerto", 2, performer.getAgility() * performer.getAngel());
			this.applyCosts(performer);
			if ((chances + this.accuracyChances) < 100) return false;
		}

		return this.internalPerform(performer, target);
	}

	public void perTurn(Character performer, Character target)
	{
		this.perform(performer, target);
		this.currentDuration -= 1;
	
		/* Só por segurança, vai que currentDuration vira -1 */
		if (this.currentDuration <= 0) {
			this.dequeue(performer, target);
			return;
		}
	}

	/*
		Se põe na fila do performer como uma ação por turno, caso seja passiva.
	*/
	public boolean enqueue(Character performer, Character target) 
	{
		if (!this.isPassive() || !this.isAvaliable())
			return false;

		performer.getPerTurnAttacks().put(this, target);
		return true;
	}

	/*
		Se remove da fila do performer como uma ação por turno, caso seja passiva e esteja na fila.
	*/
	public boolean dequeue(Character performer, Character target)
	{
		if (!this.isPassive() || this.isAvaliable())
			return false;

		this.currentDuration = 0;
		performer.getPerTurnAttacks().remove(this);

		return true;
	}
};
