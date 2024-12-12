package com.manske.neorpg.utils;

public final class Dice
{
	public static final long roll(String which, long times, long sides)
	{
		int total = 0;
	
		Terminal.clear();
		Terminal.println(Ansi.CYAN + Ansi.BOLD + """
		ooooooooo.             oooo                                                              .o8                       .o8                  .o8                     
		`888   `Y88.           `888                                                             "888                      "888                 "888                     
		 888   .d88'  .ooooo.   888   .oooo.    .oooooooo  .ooooo.  ooo. .oo.  .oo.         .oooo888   .ooooo.        .oooo888   .oooo.    .oooo888   .ooooo.   .oooo.o 
		 888ooo88P'  d88' `88b  888  `P  )88b  888' `88b  d88' `88b `888P"Y88bP"Y88b       d88' `888  d88' `88b      d88' `888  `P  )88b  d88' `888  d88' `88b d88(  "8 
		 888`88b.    888   888  888   .oP"888  888   888  888ooo888  888   888   888       888   888  888ooo888      888   888   .oP"888  888   888  888   888 `"Y88b.  
		 888  `88b.  888   888  888  d8(  888  `88bod8P'  888    .o  888   888   888       888   888  888    .o      888   888  d8(  888  888   888  888   888 o.  )88b 
		o888o  o888o `Y8bod8P' o888o `Y888""8o `8oooooo.  `Y8bod8P' o888o o888o o888o      `Y8bod88P" `Y8bod8P'      `Y8bod88P" `Y888""8o `Y8bod88P" `Y8bod8P' 8""888P' 
						       d"     YD                                                                                                                
						       "Y88888P'                                                                                                                
		""" + Ansi.RESTORE);

		Terminal.println("\n\n\nAgora, para definir " + which + ", você vai rolar " + times + " dados de " + sides + " lados.");
		Terminal.getStringHidden("Nota: Pressione a tecla <ENTER> para começar a rolar os dados"); 

		for (int i = 0; i < times; ++i) {
			int res = (int) ((Math.random() * sides) + 1);
			total += res;
			
			/* Timer.sleep("\nRolagem de dado (" + (i + 1) + "/" + times + ")", 400); */
			Terminal.println("O resultado foi de " + Ansi.BOLD + res + Ansi.RESTORE);
		}

		Terminal.println(Ansi.BOLD + Ansi.CYAN + Ansi.BLINK + "\n\n\nO valor da soma de todos os dados foi de " + total + Ansi.RESTORE); 
		Terminal.getStringHidden("Nota: Pressione a tecla <ENTER> para continuar");
		return total;
	}
};
