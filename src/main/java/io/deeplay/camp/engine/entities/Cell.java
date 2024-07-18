package io.deeplay.camp.engine.entities;

import java.util.Objects;

/**
 * Класс клетки поля
 */
public class Cell {
    public final int x;
    public final int y;
    public final Planet planet;
    private Fleet fleet;

    public Cell(final int x, final int y, final Planet planet) {
        this.planet = planet;
        this.x = x;
        this.y = y;
        this.fleet = null;
    }

    public Cell(final int x, final int y) {
        this.planet = null;
        this.x = x;
        this.y = y;
        this.fleet = null;
    }

    public Cell(final int x, final int y, final Fleet fleet) {
        this.planet = null;
        this.x = x;
        this.y = y;
        this.fleet = fleet;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;
        return x == cell.x && y == cell.y && Objects.equals(planet, cell.planet) && Objects.equals(fleet, cell.fleet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, planet, fleet);
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(final Fleet fleet) {
        this.fleet = fleet;
    }
}
