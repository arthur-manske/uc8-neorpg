package com.manske.neorpg.world.battle;

import com.manske.neorpg.entity.character.Character;

public class Battle
{
	protected long turn = 0;
	protected Character player, enemy;

	public Battle(Character player, Character enemy)
	{
		this.player = player;
		this.enemy  = enemy;
	}

	public Character getPlayer() { return this.player; }
	public Character getenemy()  { return this.enemy;  }

	public boolean isLonging() { return (this.player.isAlive() && this.enemy.isAlive()); }

	public boolean isPlayerWinner() { return  this.player.isAlive() && !this.enemy.isAlive(); }
	public boolean isenemyWinner()  { return !this.player.isAlive() &&  this.enemy.isAlive(); }

	public Character takeTurn()
	{
		turn++;
		if (turn % 2 != 0) return this.player;
		return this.enemy;
	}
};
