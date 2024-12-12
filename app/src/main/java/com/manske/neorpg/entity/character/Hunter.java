package com.manske.neorpg.entity.character;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.attack.*;

public class Hunter extends Character
{
	protected long focusCount;

	public Hunter(String name)
	{
		super(
			name,
			Dice.roll("saúde",           1, 20),
			Dice.roll("força",           2, 20),
			Dice.roll("mana",            1, 20),
			Dice.roll("força mágica",    1, 20),
			Dice.roll("agilidade",       3, 20),
			Dice.roll("taxa de crítico", 2, 20),
			Dice.roll("velocidade",      3, 20),
			1
		);

		this.knownAttacks.add(new PhysicalAttack("Ataque rápido", "Um ataque rápido", 5, 0, 0, 10, 95, 75));
		this.knownAttacks.add(new PhysicalAttack("Ataque forte", "Um ataque mais potente que o normal", 5, 2, 6, 30, 70, 85));
//		this.knownAttacks.add(new PhysicalAttack("Ataque ", "Um ataque comum", 0, 5, 0, 0, 25, 95, 90));
//              TODO: Criar o tipo de ataque físico que recupera vida pro caçador
//              TODO: Criar o efeito temporário de Foco no caçador
	}
	
	@Override public String getCharacterType() { return "Caçador"; }

	/* protected void removeFocus(boolean force) */
	/* { */
	/* 	if (!force && this.focusCount == 0) return; */

	/* 	long amount = (this.angel * 2); */

	/* 	this.focusCount = 0; */

	/* 	this.agility   -= amount; */
	/* 	this.velocity  -= amount; */
	/* 	this.critRatio -= amount; */
	/* } */

	/* protected void loseFocus() */
	/* { */
	/* 	if (this.focusCount <= 0) return; */
	/* 	this.focusCount--; */

	/* 	Terminal.println("O caçador " + " está perdendo concentração..."); */
	/* 	Terminal.println("Concentração restante " + this.focusCount); */

	/* 	if (this.focusCount <= 0) this.removeFocus(true); */
	/* } */
	
	/* public long eatRat() */
	/* { */
	/* 	this.loseFocus(); */

	/* 	long acquired = (long) Math.ceil(this.angel * this.level * (this.maxLife / 10)); */

	/* 	if ((Math.random() * this.angel * this.agility) <= (Math.random() * 3) + 1) { */
	/* 		Terminal.println("O caçador " + this.name + " não conseguiu consumir um animal nojento."); */
	/* 		return 0; */
	/* 	} */

	/* 	this.curLife += acquired; */

	/* 	Terminal.println("O caçador " + this.name + " achou e consumiu um animal nojento!"); */
	/* 	Terminal.println("BÔNUS DE VIDA: " + acquired); */

	/* 	return acquired; */
	/* } */

	/* public long focus() */
	/* { */
	/* 	long focusBonus = (this.angel * 2); */
	/* 	if (this.focusCount > 0) { */
	/* 		focusBonus *= 2; */

	/* 		this.curLife   -= focusBonus; */
	/* 		this.agility   -= focusBonus; */
	/* 		this.velocity  -= focusBonus; */
	/* 		this.critRatio -= focusBonus; */

	/* 		Terminal.println("O caçador " + this.name + " foi ganansioso e tentou se focar em dobro."); */
	/* 		Terminal.println("PERDA DE VIDA: "       + focusBonus); */ 
	/* 		Terminal.println("PERDA DE AGILIDADE: "  + focusBonus); */
	/* 		Terminal.println("PERDA DE VELOCIDADE: " + focusBonus); */
	/* 		Terminal.println("PERDA DE CRÍTICO: "    + focusBonus); */ 

	/* 		return 0; */
	/* 	} */


	/* 	long runs = this.level; */
	/* 	if (runs == 0) runs = 1; */

	/* 	this.focusCount = (Dice.roll("Bônus de foco", runs, 3)); */ 
		
	/* 	Terminal.println("O caçador " + this.name + " está focado."); */
	/* 	Terminal.println("BÔNUS DE AGILIDADE: "  + focusBonus); */ 
	/* 	Terminal.println("BÔNUS DE VELOCIDADE: " + focusBonus); */ 
	/* 	Terminal.println("BÔNUS DE CRÍTICO: "    + focusBonus); */

	/* 	this.agility   += focusBonus; */
	/* 	this.velocity  += focusBonus; */
	/* 	this.critRatio += focusBonus; */ 

	/* 	return focusBonus; */
	/* } */

	/* @Override public long attack(Character other) */
	/* { */
	/* 	this.loseFocus(); */
	/* 	return super.attack(other); */
	/* } */
	
	/* @Override public long lightAttack(Character other) */
	/* { */
	/* 	long bonus = (long)(Math.random() * 10); */

	/* 	if (Math.random() > 0.3) this.loseFocus(); */
	/* 	this.strength += bonus; */

	/* 	long res = super.lightAttack(other); */

	/* 	this.strength -= bonus; */
	/* 	return res; */
	/* } */

	/* @Override public long heavyAttack(Character other) */
	/* { */
	/* 	this.loseFocus(); */
	/* 	return super.heavyAttack(other); */
	/* } */

	/* @Override public void stateChange() */
	/* { */
	/* 	this.removeFocus(false); */
	/* 	super.stateChange(); */
	/* } */
};
