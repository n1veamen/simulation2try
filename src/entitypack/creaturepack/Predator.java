package entitypack.creaturepack;

import config.Config;
import core.GameMap;
import core.PathFinder;
import core.Point;
import entitypack.Entity;
import entitypack.Grass;

import java.util.LinkedList;

public class Predator extends Creature {
    private static int damage;

    public Predator() {
        super("\uD83D\uDD34");
        setDamage(Config.PREDATOR_DAMAGE);
        setHP(Config.PREDATOR_HP);
        setSpeed(Config.PREDATOR_SPEED);
    }

    public static void setDamage(int damage) {
        Predator.damage = damage;
    }

    @Override
    public void makeMove(GameMap gameMap) {
        setHP(getHP() - Config.HUNGRY_DAMAGE);

        if (getHP() <= 0) {
            this.killFrom(gameMap);
            return;
        }

        PathFinder pathFinder = new PathFinder(gameMap);
        LinkedList<Point> path = pathFinder.findTarget(this.getPos(), Hebrivore.class);

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
        Point targetPos = path.get(path.size() - 1);
        for (Hebrivore h : gameMap.getAllEntities(Hebrivore.class)) {
            if (targetPos.equals(h.getPos())) {
                if (h.getHP() < Config.PREDATOR_DAMAGE) {
                    heal(h.getHP());
                } else {
                    h.setHP(h.getHP() - Config.PREDATOR_DAMAGE);
                    heal(Config.PREDATOR_DAMAGE);
                    return;
                }
            }
        }
    }

    @Override
    public void heal(int heal) {
        setHP(getHP() + heal);
    }

    @Override
    public boolean isInRange(LinkedList<Point> path) {
        if (path.size() <= Config.PREDATOR_ATTACK_RANGE) {return true;}
        return false;
    }
}
