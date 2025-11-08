package entitypack.creaturepack;

import config.Config;
import core.GameMap;
import core.PathFinder;
import core.Point;
import entitypack.Entity;
import entitypack.Grass;

import java.util.LinkedList;

public class Hebrivore extends Creature {

    public Hebrivore() {
        super("\uD83D\uDD35");
        setHP(Config.HERBIVORE_HP);
        setSpeed(Config.HERBIVORE_SPEED);
    }

    public void heal(int heal) {
        setHP(getHP() + heal);
    }

    @Override
    public boolean isInRange(LinkedList<Point> path) {
        if (path.size() == 2) {return true;}
        return false;
    }

    @Override
    public void makeMove(GameMap gameMap) {
        setHP(getHP() - Config.HUNGRY_DAMAGE);

        if (getHP() <= 0) {
            this.killFrom(gameMap);
            return;
        }

        PathFinder pathFinder = new PathFinder(gameMap);
        LinkedList<Point> path = pathFinder.findTarget(this.getPos(), Grass.class);
        if (path == null || path.isEmpty()) {
            return;
        }

        if (isInRange(path)) {
            attackTarget(path, gameMap);
        } else {
            setPos(path.get(1));
        }
    }

    @Override
    public void attackTarget(LinkedList<Point> path, GameMap gameMap) {
        Point targetPos = path.get(1);
        for (Grass g : gameMap.getAllEntities(Grass.class)) {
            if (targetPos.equals(g.getPos())) {
                g.delFrom(gameMap);
                heal(Config.GRASS_HEAL);
                return;
            }
        }
    }


}
