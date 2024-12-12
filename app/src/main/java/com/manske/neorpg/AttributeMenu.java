package com.manske.neorpg;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.Character;

public class AttributeMenu
{
	private static void newLevelHeader()
	{
		Terminal.clear();
		Terminal.println("""
		      .o.           .             o8o   .o8                       .                      
		     .888.        .o8             "'  "888                     .o8                      
		    .8"888.     .o888oo oooo d8b oooo   888oooo.  oooo  oooo  .o888oo  .ooooo.   .oooo.o 
		   .8' 888.      888   888""8P 888   d88' 88b 888  888    888   d88' 88b d88(  "8 
		  .88ooo8888.     888    888      888   888   888  888   888    888   888   888 "Y88b.  
		 .8'     888.    888 .  888      888   888   888  888   888    888 . 888   888 o.  )88b 
		o88o     o8888o   "888" d888b    o888o  Y8bod8P'  V88V"V8P'   "888" Y8bod8P' 8""888P' 
		""");
		Terminal.print("\n\n\n");
	}

	private static int chooseAttribute(Character character)
	{
		newLevelHeader();

		Terminal.println("Atributos do(a) " + character.getCharacterType() + " " + character.getName());

		Terminal.println("Nível atual:        " + character.getLevel());
		Terminal.println("Experiência:        " + character.getExpLevel());
		Terminal.println("Pontos:             " + character.getPoints());
		Terminal.println("Pontos divinos      " + character.getDivinePoints());
		Terminal.println("\n\n\nAtributos que podem ser escolhidos para melhorar: ");
		Terminal.println("0. Retornar ao menu anterior.");
		Terminal.println("1. Saúde:           " + character.getCurLife() + "/" + character.getMaxLife());
		Terminal.println("2. Mana:            " + character.getCurMana() + "/" + character.getMaxMana());
		Terminal.println("3. Força:           " + character.getStrength());
		Terminal.println("4. Força mágica:    " + character.getMage());
		Terminal.println("5. Taxa de crítico: " + character.getCritRatio());
		Terminal.println("6. Agilidade:       " + character.getAgility());
		Terminal.println("7. Velocidade:      " + character.getVelocity());
		Terminal.println("8. Divino:          " + character.getAngel());

		/* Corrige os indícies para as constantes em Character */
		return Terminal.getInt("\n\n\nEscolha uma das opções acima como o atributo a melhorar: ");
	}

	public static void init(Character character)
	{
		int which;

		do {
			boolean useDivinePoints = character.getDivinePoints() > 0;
			long amount = 0;
			
			which = chooseAttribute(character);
			if (which == 0) continue;
	
			if (character.getPoints() > 0 && character.getDivinePoints() > 0 && which != 8) {
				int buceta;

				Terminal.println("Opções de tipo de ponto a ser gasto: ");
				Terminal.println("0. Pontos comuns");
				Terminal.println("1. Pontos divinos");

				do {
					buceta = Terminal.getInt("Digite a opção desejada: ");
					useDivinePoints = (buceta == 1);  
				} while (buceta != 0 && buceta != 1);
			}
			
			if (which == 8 && !useDivinePoints) {
				Terminal.println("Você precisa de pontos divinos para melhorar o poder divino.");
				Terminal.getStringHidden("Nota: Pressione <ENTER> para continuar");
				continue;	
			}

			if (character.getPoints() <= 0 && character.getDivinePoints() <= 0) {
				Terminal.println("Você precisa de pontos para melhorar quaisquer atributos.");
				Terminal.getStringHidden("Nota: Pressione <ENTER> para continuar");
				continue;
			}

			do {
				if (amount == -1) break;
				Terminal.println("Intervalo válido para seleção é entre 1 e " + (useDivinePoints ? character.getDivinePoints() : character.getPoints()));
				amount = Terminal.getLong("Quantidade de pontos a por no atributo (selecione -1 para cancelar):");
			} while (amount <= 0 || amount > character.getPoints());

			if (amount == -1) continue;
				
			character.spendPoints(amount, useDivinePoints);

			if (useDivinePoints && which != 8) {
				amount *= 4;
			}

			switch (which - 1) {
			case Character.Attributes.LIFE:
				character.addMaxLife(amount);
				break;
			case Character.Attributes.MANA:
				character.addMaxMana(amount);
				break;
			case Character.Attributes.STRENGTH:
				character.addStrength(amount);
				break;
			case Character.Attributes.MAGE:
				character.addMage(amount);
				break;
			case Character.Attributes.CRIT_RATIO:
				character.addCritRatio(amount);
				break;
			case Character.Attributes.AGILITY:
				character.addAgility(amount);
				break;
			case Character.Attributes.VELOCITY:
				character.addVelocity(amount);
				break;
			case Character.Attributes.ANGEL:
				character.addAngel(amount);
				break;
			default:
				Terminal.println("\n\n\n" + Ansi.RED + "Opção inválida selecionada!" + Ansi.RESTORE);
				Terminal.getStringHidden("Nota: Pressione <ENTER> para continuar");
			}
		} while (which != 0);
	}
};
