package core;

import config.Config;
import entitypack.Entity;
import entitypack.Grass;
import entitypack.Rock;
import entitypack.creaturepack.Creature;
import entitypack.creaturepack.Hebrivore;
import entitypack.creaturepack.Predator;
import view.Renderer;

public class Actions {

    public static void initActions (GameMap gameMap) {
        final double entityQty = ((Config.MAP_SIZE[0] * Config.MAP_SIZE[1]) * Config.FULLNESS_PERCENT);
        final int obstacleQty = (int) (entityQty * Config.OBSTACLE_PERCENT);
        final int hebrivoreQty = (int) (entityQty * Config.HERBIVORE_PERCENT);
        final int predatorQty = (int) (entityQty * Config.PREDATOR_PERCENT);
        final int grassQty = (int) (entityQty * Config.GRASS_PERCENT);

        for (int k = 0; k < obstacleQty; k++) {
            gameMap.addEntity(new Rock());
        }

        for (int i = 0; i < hebrivoreQty; i++) {
            gameMap.addEntity(new Hebrivore());
        }

        for (int i = 0; i < grassQty; i++) {
            gameMap.addEntity(new Grass());
        }

        for (int i = 0; i < predatorQty; i++) {
            gameMap.addEntity(new Predator());
        }

        for (Entity e : gameMap.getAllEntities(Entity.class)) {
            e.setPos(Entity.getRandomFreePos(gameMap));
        }
    }

    public static void turnActions(GameMap gameMap, Renderer renderer, int counter) {
        renderer.updateMap();

        for (Creature e : gameMap.getAllEntities(Creature.class)) {
            int creatureSpeed = e.getSpeed();
            for (int i = 0; i < creatureSpeed; i++) {
                e.makeMove(gameMap);
            }
        }
        renderer.printMap(counter);

        if (counter % Config.GRASS_GENERATE_EVERY == 0) {
            grassSpawner(gameMap);
        }
    }

    public static void grassSpawner(GameMap gameMap) {
        for (int i = 0; i < Config.GRASS_GENERATE_EVERY; i++) {
            Grass grass = new Grass();
            Point pos = Entity.getRandomFreePos(gameMap);
            if (pos != null) {
                grass.setPos(pos);
                gameMap.addEntity(grass);
            }
        }
    }
}
