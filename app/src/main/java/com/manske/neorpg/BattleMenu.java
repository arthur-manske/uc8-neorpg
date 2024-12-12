package com.manske.neorpg;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.world.battle.Battle;
import com.manske.neorpg.entity.character.attack.*;
import com.manske.neorpg.entity.character.nonplayable.Enemy;
import com.manske.neorpg.entity.character.Character;

import java.util.Map;
import java.util.List;

public class BattleMenu extends Battle
{
	public BattleMenu(Character player, Character enemy)
	{
		super(player, enemy); 
	}

	private void header()
	{
		Terminal.clear();
		Terminal.println("""
		oooooooooo.                .             oooo  oooo                  
		`888'   `Y8b             .o8             `888  `888                  
		 888     888  .oooo.   .o888oo  .oooo.    888   888 .oo.    .oooo.   
		 888oooo888' `P  )88b    888   `P  )88b   888   888P"Y88b  `P  )88b  
		 888    `88b  .oP"888    888    .oP"888   888   888   888   .oP"888  
		 888    .88P d8(  888    888 . d8(  888   888   888   888  d8(  888  
		o888bood8P'  `Y888""8o   "888" `Y888""8o o888o o888o o888o `Y888""8o 
		""");
		Terminal.println("\n\n\n");
	}
	
	public boolean playerMagic()
	{
		int option;

		Terminal.clear();
		Terminal.println("""
		ooo        ooooo                       o8o            
		`88.       .888'                       `"'            
		 888b     d'888   .oooo.    .oooooooo oooo   .oooo.   
		 8 Y88. .P  888  `P  )88b  888' `88b  `888  `P  )88b  
		 8  `888'   888   .oP"888  888   888   888   .oP"888  
		 8    Y     888  d8(  888  `88bod8P'   888  d8(  888  
		o8o        o888o `Y888""8o `8oooooo.  o888o `Y888""8o 
					   d"     YD                  
					   "Y88888P'                  
		""");
		Terminal.println("\n\n\n");

		Terminal.println("Opções: ");
		Terminal.println("0. Voltar"); 
		for (int i = 0; i < this.player.getSpells().size(); ++i) {
			var spell = this.player.getSpells().get(i);
			Terminal.println((i + 1) + ". " + spell.getName() + " - " + Ansi.BOLD + spell.getInfo() + Ansi.RESTORE);
		}

		do {
			option = Terminal.getInt("Escolha uma das opções acima: ");
		} while (option < 0 || option > this.player.getSpells().size());

		if (option == 0) return false;
		if (!this.player.getSpells().get(option - 1).perform(this.player, this.enemy))
			Terminal.getStringHidden("A magia falhou.");

		return true;
	}
	
	public boolean playerAttack()
	{
		int option;

		Terminal.clear();
		Terminal.println("""
		      .o.           .                                              
		     .888.        .o8                                              
		    .8"888.     .o888oo  .oooo.    .ooooo oo oooo  oooo   .ooooo.  
		   .8' `888.      888   `P  )88b  d88' `888  `888  `888  d88' `88b 
		  .88ooo8888.     888    .oP"888  888   888   888   888  888ooo888 
		 .8'     `888.    888 . d8(  888  888   888   888   888  888    .o 
		o88o     o8888o   "888" `Y888""8o `V8bod888   `V88V"V8P' `Y8bod8P' 
							888.                       
							8P'                        
							"                          
		""");
		Terminal.println("\n\n\n");

		Terminal.println("Opções: ");
		Terminal.println("0. Voltar"); 
		for (int i = 0; i < this.player.getPhysicalAttacks().size(); ++i) {
			var attack = this.player.getPhysicalAttacks().get(i);
			Terminal.println((i + 1) + ". " + attack.getName() + " - " + Ansi.BOLD + attack.getInfo() + Ansi.RESTORE);
		}

		do {
			option = Terminal.getInt("Escolha uma das opções acima: ");
		} while (option < 0 || option > this.player.getPhysicalAttacks().size());

		if (option == 0) return false;
		if (!this.player.getPhysicalAttacks().get(option - 1).perform(this.player, this.enemy)) {
			Terminal.getStringHidden("O ataque falhou.");
		}

		return true;
	}

	public boolean playerChoice()
	{
		boolean canExit = false;
		
		do {
			int option;

			this.header();
			
			Terminal.println(this.enemy.getCharacterType() + " " + this.enemy.getName());

			Terminal.println("Atributos do(a): " + this.enemy.getCharacterType() + " " + this.enemy.getName() + " - Nível: " + this.enemy.getLevel());
			Terminal.println("Saúde:           " + this.enemy.getCurLife() + "/" + this.enemy.getMaxLife());
			/* Terminal.println("Mana:            " + this.enemy.getCurMana() + "/" + this.enemy.getMaxMana()); */
			Terminal.println("Força:           " + this.enemy.getStrength());
			/* Terminal.println("Força mágica:    " + this.enemy.getMage()); */
			Terminal.println("Taxa de crítico: " + this.enemy.getCritRatio());
			Terminal.println("Agilidade:       " + this.enemy.getAgility());
			Terminal.println("Velocidade:      " + this.enemy.getVelocity());
			Terminal.println("Divino:          " + this.enemy.getAngel());

			Terminal.println("\n\n\nSeu personagem está com: ");
			Terminal.println("HP: " + this.player.getCurLife() + "/" + this.player.getMaxLife());
			Terminal.println("MP: " + this.player.getCurMana() + "/" + this.player.getCurMana());

			Terminal.println("\n\n\nOpções: ");
			Terminal.println("0. Fugir da batalha");
			Terminal.println("1. Pular jogada");
			Terminal.println("2. Usar magia");
			Terminal.println("3. Usar ataque comum");
			Terminal.println("4. Usar item (só na versão do meu Onlyfans ou do Patreon)");


			do {
				option = Terminal.getInt("Escolha a sua opção: ");
			} while (option < 0 || option > 4);
			
			switch (option) {
			case 0: return false;
			case 1: canExit = true; break;
			case 2: canExit = this.playerMagic(); break;
			case 3: canExit = this.playerAttack(); break;
			default: break;
			}
		} while (!canExit);

		return true;
	}

	public void enemyChoice()
	{
		var action = this.enemy.getPhysicalAttacks().get(0);
		Terminal.println("O " + this.enemy.getCharacterType() + " " + this.enemy.getName() + " irá tentar realizar seu ataque!\n");
		Terminal.getStringHidden("Nota: Pressione <ENTER> para continuar");

		if (this.enemy.getPhysicalAttacks().get(0).perform(this.enemy, this.player)) {
			Terminal.getStringHidden("O inimigo acertou.");
		} else {
			Terminal.getStringHidden("O inimigo errou.");
		}
	}

	public void init()
	{
		while (this.isLonging()) {
			Character current;
			this.header();

			player.performPerTurnAttacks();
			enemy.performPerTurnAttacks();

			current = this.takeTurn();
			if (current == player) {
				if (!this.playerChoice()) return;
			} else {
				this.enemyChoice();		
			}
		}

		if (this.player.isAlive()) {
			long expPoints = (Math.max(1, this.enemy.getLevel()) * this.enemy.getAngel() * 250) + this.enemy.getStrength() + (this.enemy.getMaxLife() * 2) + this.enemy.getMaxMana() + this.enemy.getMage() + this.enemy.getAgility() + this.enemy.getVelocity() + this.enemy.getExpLevel();
			Terminal.clear();
			this.header();

			this.player.addExp(expPoints);

			Terminal.println("O seu personagem ganhou a batalha.");
			Terminal.println("Foram obtidos: " + expPoints + " pontos de experiência"); 
		}
	}
};
