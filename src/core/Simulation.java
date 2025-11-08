package core;

import config.Config;
import view.Renderer;

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