package core;

import entitypack.Entity;
import java.util.*;

public class PathFinder {
    GameMap gameMap;

    public PathFinder(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public LinkedList<Point> findTarget(Point start, Class<? extends Entity> targetType) {
        Queue<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        Map<Point, Point> parent = new HashMap<>();
        LinkedList<Point> path = new LinkedList<>();

        queue.offer(start);
        visited.add(start);


        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            for (Entity e : gameMap.getAllEntities(targetType)) {
                if (temp.equals(e.getPos())) {
                    Point pos = temp;
                    path.add(pos);
                    while (!pos.equals(start)) {
                        pos = parent.get(pos);
                        path.addFirst(pos);
                    }
                    return path;
                }
            }

            Point[] neighbors = {temp.checkRight(), temp.checkUp(), temp.checkDown(), temp.checkLeft()};
            List<Entity> notAviable = gameMap.getAllEntities(Entity.class);
            for (Point next : neighbors) {
                boolean available = true;

                for (Entity n : notAviable) {
                        if (next.equals(n.getPos())) {
                            available = false;
                            for (Entity e : gameMap.getAllEntities(targetType)) {
                                if (next.equals(e.getPos())) {
                                    available = true;
                                    break;
                                }
                            }
                            break;
                        }
                }

                if (next.getCoordinate()[0] >= gameMap.getMapSize()[0] ||
                        next.getCoordinate()[0] < 0 ||
                        next.getCoordinate()[1] >= gameMap.getMapSize()[1] ||
                        next.getCoordinate()[1] < 0) {
                    available = false;
                }

                if (available && !visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                    parent.put(next, temp);
                }
            }
        }
        return null;
    }
}
