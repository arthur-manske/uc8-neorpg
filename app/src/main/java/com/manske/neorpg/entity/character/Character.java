package com.manske.neorpg.entity.character;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.Entity;

import com.manske.neorpg.world.battle.BattleAction;

import com.manske.neorpg.entity.character.attack.Attack;
import com.manske.neorpg.entity.character.attack.Spell;
import com.manske.neorpg.entity.character.attack.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Character extends Entity
{
	public static class Attributes
	{
		public static final int LIFE       = 0;
		public static final int MANA       = 1;
		public static final int STRENGTH   = 2;
		public static final int MAGE       = 3;
		public static final int CRIT_RATIO = 4;
		public static final int AGILITY    = 5;
		public static final int VELOCITY   = 6;
		public static final int ANGEL      = 7;
	};

	/* protected static double loveMaking = 2.35; */
	
	protected List<PhysicalAttack>   knownAttacks;
	protected List<Spell>            knownSpells;
	protected Map<Attack, Character> perTurnAttacks;

	protected long maxLife;
	protected long curLife;

	protected long maxMana;
	protected long curMana;

	protected long mage;

	protected long strength;
	protected long agility;

	protected long critRatio;
	protected long velocity;

	protected long angel;

	protected long level        = 0;
	protected long expLevel     = 0;
	protected long points       = 0;
	protected long divinePoints = 0;

	public Character(String name, long life, long strength, long mana, long mage, long agility, long critRatio, long velocity, long angel)
	{
		super(name);

		this.knownAttacks   = new ArrayList<>();
		this.knownSpells    = new ArrayList<>();
		this.perTurnAttacks = new HashMap<Attack, Character>();
		
		this.angel = angel;
		
		this.maxLife = this.curLife = life * angel;
		this.maxMana = this.curMana = mana * angel;

		this.mage      = mage      * angel;
		this.strength  = strength  * angel;
		this.agility   = agility   * angel;
		this.critRatio = critRatio * angel;
		this.velocity  = velocity  * angel;

		/* Argumentos */
		/* String name, String info */
		/* long duration, long manaCostPerMultiplier */
		/* long damage, long variableDamageRolls, long variableDamageFaces, */
		/* long heal, long variableHealRolls, long variableHealFaces, */
		/* long dodgeChances, long accuracyChances, long nonCritRatio */
		this.knownSpells.add(new Spell("Feitiço de cura", "Um feitiço de cura", 0, 1, 0, 0, 0, 1, 0, 0,  0, 95, 100));
		this.knownSpells.add(new Spell("Feitiço de dano", "Um feitiço de dano", 0, 1, 1, 0, 0, 0, 0, 0, 15, 95,  80));
	}
	
	public void heal(long amount)
	{
		if (this.curLife + amount > this.maxLife) {
			this.curLife = this.maxLife;
		} else {
			this.curLife += amount;
		}
	}

	public void addMana(long amount)
	{
		if (this.curMana < 0) this.curMana = 0;

		if (this.curMana + amount > this.maxMana) {
			this.curMana = this.maxMana;
		} else {
			this.curMana += amount;
			if (this.curMana < 0) this.curMana = 0;
		}
	}

	public void addMaxLife(long amount)
	{
		boolean fill = (this.curLife >= this.maxLife);
		this.maxLife += amount;
		
		if (fill) {
			this.heal(amount);
		}
	}
	
	public void addMaxMana(long amount)
	{
		boolean fill = (this.curMana >= this.maxMana);
		this.maxMana += amount;
		
		if (fill) {
			this.addMana(amount);
		}
	}

	public void spendPoints(long amount, boolean divine)
	{
		if (divine) {
			this.divinePoints -= amount;
		} else {
			this.points       -= amount;
		}
	}

	public void addStrength(long amount)  { this.strength  += amount; }
	public void addMage(long amount)      { this.mage      += amount; }
	public void addCritRatio(long amount) { this.critRatio += amount; }
	public void addAgility(long amount)   { this.agility   += amount; }
	public void addVelocity(long amount)  { this.velocity  += amount; }
	public void addAngel(long amount)     { this.angel     += amount; }
	
	public void setCurLife(long curLife)  { this.curLife = curLife; }

	public String getCharacterType() { return "Pansexual"; }

	public long getMaxLife() { return this.maxLife; }
	public long getCurLife() { return this.curLife; }
	public long getMaxMana() { return this.maxMana; }
	public long getCurMana() { return this.curMana; }

	public long getStrength()  { return this.strength;   }
	public long getAgility()   { return this.agility;    }
	public long getLevel()     { return this.level;      }
	public long getExpLevel()  { return this.expLevel;   }
	public long getCritRatio() { return this.critRatio;  }
	public long getVelocity()  { return this.velocity;   }
	public long getAngel()     { return this.angel;      }
	public long getMage()      { return this.mage;       }
	
	public long getPoints()       { return this.points;       }
	public long getDivinePoints() { return this.divinePoints; }

	public List<PhysicalAttack>   getPhysicalAttacks() { return this.knownAttacks; }
	public List<Spell>            getSpells()          { return this.knownSpells;  }
	public Map<Attack, Character> getPerTurnAttacks()  { return this.perTurnAttacks; }

	public boolean isAlive() { return this.curLife > 0; }

	public long addExp(long points)
	{
		boolean hadALevelUp = false;
		this.expLevel += points;

		while (this.expLevel / (500 + this.level * 250) > 0) {
			this.expLevel -= (500 + (this.level * 250));
			this.level    += 1;
			this.points   += 7;

			if (this.level % 10 == 0) this.divinePoints += 3;
		}

		if (hadALevelUp) return 1;
		return 0;
	}

	public void performPerTurnAttacks()
	{
		this.perTurnAttacks.forEach((attack, target) -> {
			attack.perTurn(this, target);
		});
	}

	public long takeDamage(long damage)
	{
		long amount = (damage) - (long) Math.floor(this.angel / 2);
		this.curLife -= amount;

		return amount;
	}

	public long receiveAttack(long damage, long dodgeChances)
	{
		double dodge = this.velocity * 0.5;
		if (dodgeChances > 0) {
			dodge += (Dice.roll("bônus de chance de desvio", 1, 12)) * 0.25 * this.angel;
			if (Dice.roll("tentativa de desvio", 1, dodgeChances) + dodge > 100) return 0;
		}

		return this.takeDamage(damage);
	}
};
