package com.manske.neorpg;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.character.*;
import com.manske.neorpg.entity.character.Character;
import com.manske.neorpg.entity.character.nonplayable.Enemy;

import com.manske.neorpg.world.Scene;
import com.manske.neorpg.world.Dungeon;
import com.manske.neorpg.world.World;

import java.util.List;
import java.util.ArrayList;

public final class App
{
	private static final void printScene(String prefix, Scene scene, String direction)
	{
		if (scene == null) return; 
		Terminal.println(prefix + scene.getName() + Ansi.BOLD + " - Ir para " + direction + Ansi.RESTORE);
	}

	private static final void playerAction(Character player)
	{
		Scene scene = player.getScene();

		Scene upScene     = scene.getUp();
		Scene downScene   = scene.getDown();
		Scene leftScene   = scene.getLeft();
		Scene rightScene  = scene.getRight();
		Scene insideScene = null;

		if (scene instanceof Dungeon) {
			var dungeon = (Dungeon) scene;
			insideScene = dungeon.getInside();
		}

		if (scene.getName() == "Campos") {
			World.spawnFieldsEnemies();
		} else if (scene.getName() == "Floresta") {
			World.spawnForestEnemies(player);
		}
		
		List<Scene> validScenes = new ArrayList<>();
		List<String> directions = new ArrayList<>();

		Terminal.println(scene.getWhere());
		Terminal.println(Ansi.BOLD + scene.getName() + Ansi.RESTORE);
		Terminal.println(scene.getInfo());
		Terminal.println("\n\n\n");

		if (insideScene != null) {
			validScenes.add(insideScene);
			directions.add("dentro");
		}

		if (upScene != null) {
			validScenes.add(upScene);
			directions.add("cima");
		}

		if (downScene != null) {
			validScenes.add(downScene);
			directions.add("baixo");
		}
		if (leftScene != null) {
			validScenes.add(leftScene);
			directions.add("esquerda");
		}
		if (rightScene != null) {
			validScenes.add(rightScene);
			directions.add("direita");
		}

		for (int i = 0; i < validScenes.size(); i++)
			printScene((i + 1) + ". ", validScenes.get(i), directions.get(i));

		int offset = validScenes.size();

		Terminal.println((offset + 1) + ". Procurar por alguém");
		Terminal.println((offset + 2) + ". Vasculhar itens");
		Terminal.println((offset + 3) + ". Mostrar mapa");
		Terminal.println((offset + 4) + ". Menu de atributos do personagem");
		Terminal.println((offset + 5) + ". Menu principal");

		int answer = Terminal.getInt("\n\n\nEscolha uma das opções acima: ");
		if (answer > 0 && answer <= validScenes.size()) {
			validScenes.get(answer - 1).enter(player);
			return;
		} else if (answer == offset + 1) {
			var enemy = scene.findRandomNPC(1);
			if (enemy != null) {
				var battle = new BattleMenu(player, enemy);
				battle.init();
			} else {
				Terminal.println("Seu personagem não encontrou ninguém.");
			}
		} else if (answer == offset + 2) {
			Terminal.println("Seu personagem não encontrou nenhum item.");
		} else if (answer == offset + 3) {
			World.generateMap(scene);
		} else if (answer == offset + 4) {
			AttributeMenu.init(player);
		} else if (answer == offset + 5) {
			if (Terminal.getString("Você tem certeza (todo o progresso será perdido, digite 's' para sim)? ") == "s") {
				player.setCurLife(-10);
			}
		} else {
			Terminal.println(Ansi.RED + "Opção inválida." + Ansi.RESTORE);
		}
		
		Terminal.getStringHidden("Nota: Pressione <ENTER> para continuar");
	}
	
	public static final void startGame()
	{
		Character player = InitialMenu.init();

		do {
			Terminal.clear();
			App.playerAction(player);
		} while (player.isAlive());

		player = null;
		System.gc();
	}

	public static final void main(String[] _args)
	{
		Terminal.setTerminalSize();

		World.connectScenes();

	 	while (true) {
			App.startGame();

			Terminal.clear();
			Terminal.println(Ansi.RED + Ansi.DIMMED + """
			 .o88o.  o8o                               .o8                     o8o                                    
			 888 `"  `"'                              "888                     `"'                                    
			o888oo  oooo  ooo. .oo.  .oo.         .oooo888   .ooooo.          oooo  .ooooo.   .oooooooo  .ooooo.      
			 888    `888  `888P"Y88bP"Y88b       d88' `888  d88' `88b         `888 d88' `88b 888' `88b  d88' `88b     
			 888     888   888   888   888       888   888  888ooo888          888 888   888 888   888  888   888     
			 888     888   888   888   888       888   888  888    .o          888 888   888 `88bod8P'  888   888 .o. 
			o888o   o888o o888o o888o o888o      `Y8bod88P" `Y8bod8P'          888 `Y8bod8P' `8oooooo.  `Y8bod8P' Y8P 
											   888           d"     YD                
										       .o. 88P           "Y88888P'                
										       `Y888P
			""" + Ansi.RESTORE);
			Terminal.println("\n\n\n");

			Terminal.getStringHidden("Nota: Pressione <ENTER> para recomeçar.");
		}
	}
};
