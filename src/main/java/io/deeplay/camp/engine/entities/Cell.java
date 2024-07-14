package io.deeplay.camp.engine.entities;

/**
 * Класс клетки поля
 */
public class Cell {
    public final int x;
    public final int y;
    public final Planet planet;

    public Cell(final int x, final int y, final Planet planet) {
        this.planet = planet;
        this.x = x;
        this.y = y;
    }

    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.planet = null;
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
