package core;

import entitypack.Entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameMap {
    private int x;
    private int y;

    public int[] getMapSize() {
        return new int[]{this.x, this.y};
    }

    public GameMap(int width, int height) {
        this.x = width;
        this.y = height;
    }

    private LinkedList<Entity> allEntities = new LinkedList<>();

    public void addEntity(Entity e) {
        allEntities.add(e);
    }

    public void delEntityFromMap(Entity n) {
        LinkedList<Entity> tempList = new LinkedList<>(allEntities);
        for (Entity e : allEntities) {
            if (e.equals(n)) {
                tempList.remove(n);
            }
        }
        this.allEntities = tempList;
    }

    public <T extends Entity> List<T> getAllEntities(Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Entity e : allEntities) {
            if (type.isInstance(e)) {
                result.add(type.cast(e));
            }
        }
        return result;
    }

    public boolean isFree(Point point) {
        if (!allEntities.isEmpty()) {
            for (Entity e : allEntities) {
                if (!(e.getPos() == null) && e.getPos().equals(point)) {
                    return false;
                }
            }
        }
        return true;
    }

}
