package entitypack.creaturepack;

import core.GameMap;
import core.Point;
import entitypack.Entity;

import java.util.LinkedList;

public abstract class Creature extends Entity {

    private int hp;
    private static int speed;

    protected int getHP() {
        return this.hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public static void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public Creature(String type) {
        super(type);
    }

    public abstract void makeMove(GameMap gameMap);

    public abstract void attackTarget(LinkedList<Point> path, GameMap gameMap);

    public void killFrom(GameMap gameMap) {
        gameMap.delEntityFromMap(this);
    };

    public abstract void heal(int heal);

    public abstract boolean isInRange(LinkedList<Point> path);
}
