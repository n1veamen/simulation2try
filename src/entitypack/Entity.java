package entitypack;

import core.GameMap;
import core.Point;
import java.util.Random;

public abstract class Entity {
    Random random = new Random();
    private Point pos;
    private String type;

    public String getType() {
        return type;
    }

    public Point getPos() {
        return this.pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Entity(String type) {
        this.type = type;
    }

    public static Point getRandomFreePos(GameMap gameMap) {
        Point randomPos;
        Random random = new Random();
        while (true) {
            int x = random.nextInt(gameMap.getMapSize()[0] - 1);
            int y = random.nextInt(gameMap.getMapSize()[1] - 1);
            randomPos = new Point(x, y);

            if (gameMap.isFree(randomPos)) {
                return randomPos;
            }
        }
    }
}
