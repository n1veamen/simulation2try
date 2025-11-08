package core;

import config.Config;
import entitypack.Entity;
import entitypack.Grass;
import entitypack.Rock;
import entitypack.creaturepack.Creature;
import entitypack.creaturepack.Hebrivore;
import entitypack.creaturepack.Predator;
import view.Renderer;

import javax.crypto.SecretKey;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(Config.MAP_SIZE[0], Config.MAP_SIZE[1]);
        PathFinder pf = new PathFinder(gameMap);
        Renderer renderer = new Renderer(gameMap);

        Actions.initActions(gameMap);

        for (int i = 0; i < 200; i++) {
            Actions.turnActions(gameMap, renderer, i);
        }
    }

}