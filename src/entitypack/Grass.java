package entitypack;

import core.GameMap;

public class Grass extends Entity{
    public Grass() {
        super("\uD83C\uDF40");
    }

    public void delFrom(GameMap gameMap) {
        gameMap.delEntityFromMap(this);
    };

}
