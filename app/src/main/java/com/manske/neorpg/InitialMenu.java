package com.manske.neorpg;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.world.*;
import com.manske.neorpg.entity.character.*;
import com.manske.neorpg.entity.character.Character;

public final class InitialMenu
{
	public static final Character init()
	{
		int choice = -1;
		
		Terminal.saveState();

		while (choice != 0 && choice != 1) {
			Terminal.clear();
			Terminal.println(Ansi.RED + Ansi.DIMMED + Ansi.UNDERLINE + """
					      .o.        .oooooo..o ooooo ooooo      ooo  .oooooo..o ooooooooo.   ooooo ooooooooo.   oooooooooooo   
					     .888.      d8P'    `Y8 `888' `888b.     `8' d8P'    `Y8 `888   `Y88. `888' `888   `Y88. `888'     `8   
					    .8"888.     Y88bo.       888   8 `88b.    8  Y88bo.       888   .d88'  888   888   .d88'  888           
					   .8' `888.     `"Y8888o.   888   8   `88b.  8   `"Y8888o.   888ooo88P'   888   888ooo88P'   888oooo8      
					  .88ooo8888.        `"Y88b  888   8     `88b.8       `"Y88b  888          888   888`88b.     888    "      
					 .8'     `888.  oo     .d8P  888   8       `888  oo     .d8P  888          888   888  `88b.   888       o   
					o88o     o8888o 8""88888P'  o888o o8o        `8  8""88888P'  o888o        o888o o888o  o888o o888ooooood8   
			\n""");

			Terminal.println(Ansi.RESTORE + Ansi.DIMMED + Ansi.RED);

			Terminal.fillLine('x');
			Terminal.fillLine('8');
			Terminal.fillLine('x');

			Terminal.println(Ansi.RESTORE + "\n\n\n");

			Terminal.println("Opções: ");
			Terminal.println("0. Sair do jogo (restaurar seu terminal)");
			Terminal.println("1. Iniciar um novo jogo");
			
			choice = Terminal.getInt("Escolha uma das opções acima: ");
		}

		if (choice == 0) {
			Terminal.clear();
			Terminal.restoreState();
			System.exit(0);
		}

		return InitialMenu.createPlayer();
	}

	public static Character createPlayer()
	{
		Character player;
		String playerName, playerKind;

		Terminal.clear();
		Terminal.println(Ansi.BOLD + Ansi.BLUE + """
		  .oooooo.             o8o                                                         o8o                                      .o8                         
		 d8P'  `Y8b            `"'                                                         `"'                                     "888                         
		888          oooo d8b oooo   .ooooo.        .oooo.o  .ooooo.  oooo  oooo          oooo  .ooooo.   .oooooooo  .oooo.    .oooo888   .ooooo.  oooo d8b     
		888          `888""8P `888  d88' `88b      d88(  "8 d88' `88b `888  `888          `888 d88' `88b 888' `88b  `P  )88b  d88' `888  d88' `88b `888""8P     
		888           888      888  888ooo888      `"Y88b.  888ooo888  888   888           888 888   888 888   888   .oP"888  888   888  888   888  888         
		`88b    ooo   888      888  888    .o      o.  )88b 888    .o  888   888           888 888   888 `88bod8P'  d8(  888  888   888  888   888  888     .o. 
		 `Y8bood8P'  d888b    o888o `Y8bod8P'      8""888P' `Y8bod8P'  `V88V"V8P'          888 `Y8bod8P' `8oooooo.  `Y888""8o `Y8bod88P" `Y8bod8P' d888b    Y8P 
												   888           d"     YD                                              
											       .o. 88P           "Y88888P'                                              
											       `Y888P
		""" + Ansi.RESTORE);
		Terminal.print("\n\n\n");

		playerName = Terminal.getString("Digite o nome: ");
		player = null;

		do {
			Terminal.clear();
			Terminal.println(Ansi.BOLD + Ansi.BLUE + """
			  .oooooo.             o8o                                                         o8o                                      .o8                         
			 d8P'  `Y8b            `"'                                                         `"'                                     "888                         
			888          oooo d8b oooo   .ooooo.        .oooo.o  .ooooo.  oooo  oooo          oooo  .ooooo.   .oooooooo  .oooo.    .oooo888   .ooooo.  oooo d8b     
			888          `888""8P `888  d88' `88b      d88(  "8 d88' `88b `888  `888          `888 d88' `88b 888' `88b  `P  )88b  d88' `888  d88' `88b `888""8P     
			888           888      888  888ooo888      `"Y88b.  888ooo888  888   888           888 888   888 888   888   .oP"888  888   888  888   888  888         
			`88b    ooo   888      888  888    .o      o.  )88b 888    .o  888   888           888 888   888 `88bod8P'  d8(  888  888   888  888   888  888     .o. 
			 `Y8bood8P'  d888b    o888o `Y8bod8P'      8""888P' `Y8bod8P'  `V88V"V8P'          888 `Y8bod8P' `8oooooo.  `Y888""8o `Y8bod88P" `Y8bod8P' d888b    Y8P 
													   888           d"     YD                                              
												       .o. 88P           "Y88888P'                                              
												       `Y888P                                                                   	
			""" + Ansi.RESTORE);
			Terminal.print("\n\n\n");

			Terminal.println("Classes disponíveis para uso: ");
			Terminal.println("\tMago\n\tBardo\n\tAssassino\n\tCaçador\n\tGuerreiro\n");
			playerKind = Terminal.getString("Escolha uma das classes acima: ");

			switch (playerKind.toLowerCase()) {
			case "mago":      player = new Mage(playerName);     break;
			case "bardo":     player = new Bard(playerName);     break;
			case "assassino": player = new Assassin(playerName); break;
			case "caçador":   player = new Hunter(playerName);   break;
			case "guerreiro": player = new Warrior(playerName);  break;
			case "anjo":      player = new Angel(playerName);    break;
			case "deus":      player = new God(playerName);      break;
			case "berserk":   player = new Berserk(new Warrior(playerName));  break;
			default:
				Terminal.println(Ansi.RED + "Você escolheu uma classe inválida.");
				Terminal.getStringHidden(Ansi.RESTORE + "Nota: Use a tecla <ENTER> para continuar");
			}
		} while (player == null);

		Terminal.clear();

		player.setScene(World.getInitialScene());
		return player;
	}
}; 
