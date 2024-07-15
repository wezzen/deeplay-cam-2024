package io.deeplay.camp.engine.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && Objects.equals(planet, cell.planet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, planet);
    }
}
