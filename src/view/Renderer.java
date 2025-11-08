package view;

import core.GameMap;
import entitypack.Entity;
import entitypack.Grass;
import entitypack.creaturepack.Creature;
import entitypack.creaturepack.Hebrivore;
import entitypack.creaturepack.Predator;

public class Renderer {

    private GameMap gameMap;
    private String[][] map;

    public Renderer(GameMap gameMap) {
        this.gameMap = gameMap;
        map = new String[gameMap.getMapSize()[0]][gameMap.getMapSize()[1]];
    }

    public void updateMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = null;
            }
        }
        System.out.println();

        for (Entity e : gameMap.getAllEntities(Entity.class)) {
            int x = e.getPos().getCoordinate()[0];
            int y = e.getPos().getCoordinate()[1];

            map[x][y] = e.getType();
        }
    }

    public void printMap(int counter) {
        for (int i = 0; i < map.length; i++) {
            System.out.print("=======");
        }
        System.out.println();

        System.out.print("Ход номер: №" + counter + " | Всего сущностей: "
                + gameMap.getAllEntities(Creature.class).size() +
                " | Всего травоядных: " + gameMap.getAllEntities(Hebrivore.class).size() +
                " | Всего хищников: " + gameMap.getAllEntities(Predator.class).size() +
                " | Всего трав: " + gameMap.getAllEntities(Grass.class).size());
        System.out.println();

        for (int i = 0; i < map.length; i++) {
            System.out.print("=======");
        }
        System.out.println();

        for (int i = 0; i < map.length; i++) {
            for (int k = 0; k < map[0].length; k++) {
                if (map[i][k] == null) {
                    System.out.print("⬛");
                    System.out.print(" ");
                } else {
                    System.out.print(map[i][k]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        for (int i = 0; i < map.length; i++) {
            System.out.print("=======");
        }
        System.out.println();
    }
}
