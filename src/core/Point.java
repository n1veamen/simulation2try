package core;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getCoordinate() {
        return new int[]{this.x, this.y};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Point checkUp() {return new Point(this.x - 1, this.y);}
    public Point checkDown() {return new Point(this.x + 1, this.y);}
    public Point checkLeft() {return new Point(this.x, this.y - 1);}
    public Point checkRight() {return new Point(this.x, this.y + 1);}
}
