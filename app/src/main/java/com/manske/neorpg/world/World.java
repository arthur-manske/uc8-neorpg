package com.manske.neorpg.world;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.*;
import com.manske.neorpg.entity.item.*;
import com.manske.neorpg.entity.character.Character;
import com.manske.neorpg.entity.character.*;
import com.manske.neorpg.entity.character.nonplayable.*;

import java.util.*;

public class World
{
	private static Scene forest = new Scene("Floresta", "Uma densa floresta");
	private static Scene fields = new Scene("Campos", "Campos densos e floridos");
	private static Scene lake   = new Scene("Lago", "Um lago");
	private static Scene house  = new Scene("Uma casa abandonada", "Poderia servir de abrigo");

	private static Scene center = new Scene("Centro", "Região de conexão entre diversos pontos importantes");
	private static Scene darkForest = new Scene("Floresta Negra", "Uma região perigosa, sombria e desafiadora");
	private static Scene decayObelisk = new Scene("Obelisco do Decaído", "Um obelisco imponente e misterioso");
	private static Scene jackObelisk = new Scene("Obelisco de Jack", "Um monumento ao infame Jack, o estripador");

	private static Scene village = new Scene("Vila", "Uma vila pacata e acolhedora");
	private static Scene city = new Scene("Cidade", "Uma grande cidade com muitos recursos");
	private static Scene mines = new Scene("Minas", "Minas profundas, ricas em minérios");
	private static Scene cityAbandonedZone = new Scene("Zona Abandonada da Cidade", "Uma área esquecida e perigosa da cidade");
	private static Scene castle = new Scene("Castelo", "Um majestoso castelo, lar de reis e rainhas");
	private static Scene paradisePortal = new Scene("Portal para o Paraíso", "Um portal lendário que leva ao paraíso");

	private static Scene military = new Scene("Base Militar", "Uma base militar fortificada");
	private static Scene southTowerInsides = new Scene("Interior da torre do Sul", "Uma torre escura e abandonada");
	
	private static Scene cavesOutA = new Scene("Saída das cavernas A", "Uma saída das cavernas.");
	private static Scene cavesOutB = new Scene("Saída das cavernas B", "Uma saída das cavernas.");

	private static Scene hellEntrance = new Scene("Saída do Inferno", "O lado de dentro dos portões da Zona D3 do inferno");

	private static Dungeon cavesEntranceA = new Dungeon("Entrada das Cavernas A", "Uma entrada para o labirinto subterrâneo", cavesOutA);
	private static Dungeon cavesEntranceB = new Dungeon("Entrada das Cavernas B", "Outra entrada para as perigosas cavernas", cavesOutB);
	private static Dungeon southTower     = new Dungeon("Torre do Sul", "Uma torre imponente localizada ao sul", southTowerInsides);
	private static Dungeon hellGates      = new Dungeon("Portões do Inferno", "Portões para a Zona D3 do inferno", hellEntrance);

	private static Scene initialScene = new Scene("Início", "Campos abertos e pacatos");

	public static long getRandomRange(long min, long max)
	{
		return Math.max((long) (Math.random() * max), min);
	}

	public static void spawnForestEnemies(Character player)
	{
		var mult = (player.getCharacterType() == "Caçador") ? 0.05 : 1.0;
		var bear = new Enemy("comum", "Urso", "pardo", World.getRandomRange((long) (31 * mult), (long) (67 * mult)),  World.getRandomRange(23, 54),  World.getRandomRange(23, 54),  World.getRandomRange(23, 54),  World.getRandomRange(23, 54),  World.getRandomRange(23, 54),  World.getRandomRange(23, 54), 1, World.getRandomRange(5, 12), 1591);

		bear.setCount(1);
		World.forest.getEntities().add(bear);
	}

	/* Põe os inimigos no cenário fields */
	public static void spawnFieldsEnemies()
	{
		var greenSlime  = new Enemy("comum", "Slime", "verde",    World.getRandomRange(1, 13),  World.getRandomRange(0, 13),  World.getRandomRange(0, 13),  World.getRandomRange(0, 13),  World.getRandomRange(0, 13),  World.getRandomRange(0, 13),  World.getRandomRange(0, 13),  1, 0, 0);
		var blueSlime   = new Enemy("comum", "Slime", "azul",     World.getRandomRange(1, 19), World.getRandomRange(0, 19), World.getRandomRange(0, 19), World.getRandomRange(0, 19), World.getRandomRange(0, 19), World.getRandomRange(0, 19), World.getRandomRange(0, 19), 1, 0, 0);
		var redSlime    = new Enemy("comum", "Slime", "vermelho", World.getRandomRange(1, 25), World.getRandomRange(0, 25), World.getRandomRange(0, 25), World.getRandomRange(0, 25), World.getRandomRange(0, 25), World.getRandomRange(0, 25), World.getRandomRange(0, 25), 1, 0, 0);
		var purpleSlime = new Enemy("comum", "Slime", "roxo",     World.getRandomRange(1, 27), World.getRandomRange(0, 27), World.getRandomRange(0, 27), World.getRandomRange(0, 27), World.getRandomRange(0, 27), World.getRandomRange(0, 27), World.getRandomRange(0, 27), 1, 0, 0);
		
		greenSlime.setCount(1);
		blueSlime.setCount(1);
		redSlime.setCount(1);
		purpleSlime.setCount(1);

		World.fields.getEntities().add(greenSlime);
		World.fields.getEntities().add(blueSlime);
		World.fields.getEntities().add(redSlime);
		World.fields.getEntities().add(purpleSlime);
	}

	/* Cria a conexão entre todas as cenas */
	public static void connectScenes()
	{
		forest.setLeft(lake);
		forest.setRight(fields);
		forest.setDown(initialScene);

		fields.setLeft(forest);
		fields.setDown(house);

		initialScene.setUp(forest);
		initialScene.setLeft(southTower);
		initialScene.setRight(house);

		southTower.setRight(initialScene);
		southTower.setDown(cavesEntranceA);

		house.setLeft(initialScene);
		house.setRight(center);
		house.setUp(fields);

		center.setDown(darkForest);
		center.setLeft(house);
		center.setRight(village);
		center.setUp(military);

		darkForest.setUp(center);
		darkForest.setDown(cavesEntranceB);
		darkForest.setLeft(jackObelisk);
		darkForest.setRight(decayObelisk);

		decayObelisk.setRight(darkForest);
		jackObelisk.setLeft(darkForest);

		village.setLeft(center);
		village.setUp(city);

		city.setLeft(mines);
		city.setRight(cityAbandonedZone);
		city.setUp(castle);

		castle.setLeft(paradisePortal);
		castle.setRight(military);
		castle.setDown(city);

		military.setLeft(castle);
		military.setDown(center);

		cavesEntranceA.setUp(southTower);
		cavesEntranceB.setUp(darkForest);

		cavesOutA.setDown(cavesEntranceA);
		cavesOutB.setDown(cavesEntranceB);
	}

public static void generateMap(Scene startScene) {
    Set<Scene> visited = new HashSet<>(); // Rastreia as cenas visitadas
    Queue<Scene> queue = new LinkedList<>(); // Cenas a serem processadas
    Map<Scene, String> scenePaths = new HashMap<>(); // Armazena o "caminho" até a cena

    queue.add(startScene);
    scenePaths.put(startScene, "Início");

    System.out.println("Mapa Gerado:");
    while (!queue.isEmpty()) {
        Scene currentScene = queue.poll();
        visited.add(currentScene);

        // Exibe a cena atual
        System.out.println(" - " + scenePaths.get(currentScene) + ": " + currentScene.getName());

        // Explora as conexões
        addSceneToQueue(queue, visited, scenePaths, currentScene.getUp(), scenePaths.get(currentScene) + " -> Cima");
        addSceneToQueue(queue, visited, scenePaths, currentScene.getDown(), scenePaths.get(currentScene) + " -> Baixo");
        addSceneToQueue(queue, visited, scenePaths, currentScene.getLeft(), scenePaths.get(currentScene) + " -> Esquerda");
        addSceneToQueue(queue, visited, scenePaths, currentScene.getRight(), scenePaths.get(currentScene) + " -> Direita");

        if (currentScene instanceof Dungeon) {
            Dungeon dungeon = (Dungeon) currentScene;
            addSceneToQueue(queue, visited, scenePaths, dungeon.getInside(), scenePaths.get(currentScene) + " -> Dentro");
        }
    }
}

// Função auxiliar para adicionar cenas à fila
private static void addSceneToQueue(Queue<Scene> queue, Set<Scene> visited, Map<Scene, String> scenePaths, Scene scene, String path) {
    if (scene != null && !visited.contains(scene) && !queue.contains(scene)) {
        queue.add(scene);
        scenePaths.put(scene, path);
    }
}

	public static Scene getInitialScene() { return World.initialScene; }
};
